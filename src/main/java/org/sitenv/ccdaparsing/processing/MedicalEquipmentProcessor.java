package org.sitenv.ccdaparsing.processing;

import org.apache.log4j.Logger;
import org.sitenv.ccdaparsing.model.CCDAID;
import org.sitenv.ccdaparsing.model.CCDAMedicalEquipment;
import org.sitenv.ccdaparsing.model.CCDAMedicalEquipmentOrg;
import org.sitenv.ccdaparsing.model.CCDANonMedicalSupplyAct;
import org.sitenv.ccdaparsing.util.ApplicationConstants;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class MedicalEquipmentProcessor {
    private static final Logger logger = Logger.getLogger(MedicalEquipmentProcessor.class);

    @Autowired
    ProcedureProcessor procedureProcessor;

    @Async()
    public Future<CCDAMedicalEquipment> retrieveMedicalEquipment(XPath xPath , Document doc) throws XPathExpressionException, TransformerException {
        long startTime = System.currentTimeMillis();
        logger.info("medical equipment parsing Start time:"+ startTime);
        CCDAMedicalEquipment medicalEquipments = null;
        Element sectionElement = (Element) xPath.compile(ApplicationConstants.MEDICAL_EQUIPMENT_EXPRESSION).evaluate(doc, XPathConstants.NODE);
        List<CCDAID> ids = new ArrayList<>();
        if(sectionElement != null){
            medicalEquipments = new CCDAMedicalEquipment();
            if(ApplicationUtil.checkForNullFlavourNI(sectionElement)) {
                medicalEquipments.setSectionNullFlavourWithNI(true);
                return new AsyncResult<>(medicalEquipments);
            }
            medicalEquipments.setSectionTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile(ApplicationConstants.TEMPLATE_ID_EXPRESSION).
                    evaluate(sectionElement, XPathConstants.NODESET)));
            medicalEquipments.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile(ApplicationConstants.CODE_EXPRESSION).
                    evaluate(sectionElement, XPathConstants.NODE)));
            medicalEquipments.setProcActsProcs(procedureProcessor.readProcedures((NodeList) xPath.compile("./entry/procedure[not(@nullFlavor)]").
                    evaluate(sectionElement, XPathConstants.NODESET), xPath,ids));
            medicalEquipments.setSupplyActivities(readSupplyActivities((NodeList) xPath.compile("./entry/supply[not(@nullFlavor)]").
                    evaluate(sectionElement, XPathConstants.NODESET), xPath,ids));
            medicalEquipments.setOrganizers(readOrganizers((NodeList) xPath.compile("./entry/organizer[not(@nullFlavor)]").
                    evaluate(sectionElement, XPathConstants.NODESET), xPath,ids));
            medicalEquipments.setLineNumber(sectionElement.getUserData("lineNumber") + " - " + sectionElement.getUserData("endLineNumber"));
            medicalEquipments.setXmlString(ApplicationUtil.nodeToString((Node) sectionElement));
            Element textElement = (Element) xPath.compile("./text[not(@nullFlavor)]").evaluate(sectionElement, XPathConstants.NODE);

            if (textElement != null) {
                medicalEquipments.getReferenceLinks().addAll((ApplicationUtil.readSectionTextReferences((NodeList) xPath.compile(".//*[not(@nullFlavor) and @ID]").
                        evaluate(textElement, XPathConstants.NODESET))));
            }
            medicalEquipments.setIds(ids);
        }
        logger.info("medical equipment parsing End time:"+ (System.currentTimeMillis() - startTime));
        return new AsyncResult<>(medicalEquipments);
    }

    private List<CCDAMedicalEquipmentOrg> readOrganizers(NodeList orgNodes, XPath xPath, List<CCDAID> ids) throws TransformerException, XPathExpressionException {
        if (ApplicationUtil.isNodeListEmpty(orgNodes)) {
            return new ArrayList<>();
        }
        List<CCDAMedicalEquipmentOrg> medicalEquipmentOrgs = null;
        CCDAMedicalEquipmentOrg medicalEquipmentOrg;
        for (int i = 0; i < orgNodes.getLength(); i++) {
            medicalEquipmentOrg = new CCDAMedicalEquipmentOrg();
            Element orgElement = (Element) orgNodes.item(i);
        orgElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            medicalEquipmentOrg.setLineNumber(orgElement.getUserData("lineNumber") + " - " + orgElement.getUserData("endLineNumber") );
            medicalEquipmentOrg.setXmlString(ApplicationUtil.nodeToString((Node)orgElement));

        if(ApplicationUtil.readID((Element) xPath.compile(ApplicationConstants.ID_EXPRESSION).
                evaluate(orgElement, XPathConstants.NODE),"procedure")!= null) {
            ids.add(ApplicationUtil.readID((Element) xPath.compile(ApplicationConstants.ID_EXPRESSION).
                    evaluate(orgElement, XPathConstants.NODE),"procedure"));
        }
            medicalEquipmentOrg.setSectionTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile(ApplicationConstants.TEMPLATE_ID_EXPRESSION).
                evaluate(orgElement, XPathConstants.NODESET)));
            medicalEquipmentOrg.setReferenceText(ApplicationUtil.readTextReference((Element) xPath.compile(ApplicationConstants.REFERENCE_TEXT_EXPRESSION).
                evaluate(orgElement, XPathConstants.NODE)));
            medicalEquipmentOrg.setSupplyCode(ApplicationUtil.readCode((Element) xPath.compile(ApplicationConstants.CODE_EXPRESSION).
                evaluate(orgElement, XPathConstants.NODE)));
            medicalEquipmentOrg.setStatus(ApplicationUtil.readCode((Element) xPath.compile(ApplicationConstants.STATUS_EXPRESSION).
                evaluate(orgElement, XPathConstants.NODE)));
            medicalEquipmentOrg.setTimeOfUse(ApplicationUtil.readEffectivetime((Element) xPath.compile(ApplicationConstants.EFFECTIVE_EXPRESSION).
                evaluate(orgElement, XPathConstants.NODE),xPath));
            medicalEquipmentOrgs = new ArrayList<>();
            medicalEquipmentOrgs.add(medicalEquipmentOrg);
        }
        return medicalEquipmentOrgs;
    }

    private List<CCDANonMedicalSupplyAct> readSupplyActivities(NodeList supplyNodes, XPath xPath, List<CCDAID> ids) throws TransformerException, XPathExpressionException {
        if (ApplicationUtil.isNodeListEmpty(supplyNodes)) {
            return new ArrayList<>();
        }
        List<CCDANonMedicalSupplyAct> supplyActs = null;
        CCDANonMedicalSupplyAct supplyAct;
        for (int i = 0; i < supplyNodes.getLength(); i++) {
            supplyAct = new CCDANonMedicalSupplyAct();
            Element supplyElement = (Element) supplyNodes.item(i);
            supplyElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            supplyAct.setLineNumber(supplyElement.getUserData(ApplicationConstants.LINE_NUMBER_KEY_NAME) + " - " + supplyElement.getUserData(ApplicationConstants.END_LINE_NUMBER_KEY_NAME) );
            supplyAct.setXmlString(ApplicationUtil.nodeToString((Node)supplyElement));
            if(ApplicationUtil.readID((Element) xPath.compile(ApplicationConstants.ID_EXPRESSION).
                    evaluate(supplyElement, XPathConstants.NODE),"procedure")!= null) {
                ids.add(ApplicationUtil.readID((Element) xPath.compile(ApplicationConstants.ID_EXPRESSION).
                        evaluate(supplyElement, XPathConstants.NODE),"procedure"));
            }
            supplyAct.setSectionTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile(ApplicationConstants.TEMPLATE_ID_EXPRESSION).
                    evaluate(supplyElement, XPathConstants.NODESET)));
            supplyAct.setReferenceText(ApplicationUtil.readTextReference((Element) xPath.compile(ApplicationConstants.REFERENCE_TEXT_EXPRESSION).
                    evaluate(supplyElement, XPathConstants.NODE)));
            supplyAct.setSupplyCode(ApplicationUtil.readCode((Element) xPath.compile(ApplicationConstants.CODE_EXPRESSION).
                    evaluate(supplyElement, XPathConstants.NODE)));
            supplyAct.setStatus(ApplicationUtil.readCode((Element) xPath.compile(ApplicationConstants.STATUS_EXPRESSION).
                    evaluate(supplyElement, XPathConstants.NODE)));
            supplyAct.setTimeOfUse(ApplicationUtil.readEffectivetime((Element) xPath.compile(ApplicationConstants.EFFECTIVE_EXPRESSION).
                    evaluate(supplyElement, XPathConstants.NODE),xPath));
            supplyActs = new ArrayList<>();
            supplyActs.add(supplyAct);
        }
        return supplyActs;
    }
}
