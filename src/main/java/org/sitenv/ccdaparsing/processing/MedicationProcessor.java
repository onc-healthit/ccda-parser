package org.sitenv.ccdaparsing.processing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sitenv.ccdaparsing.model.CCDAAuthor;
import org.sitenv.ccdaparsing.model.CCDAConsumable;
import org.sitenv.ccdaparsing.model.CCDAEffTime;
import org.sitenv.ccdaparsing.model.CCDAID;
import org.sitenv.ccdaparsing.model.CCDAMedication;
import org.sitenv.ccdaparsing.model.CCDAMedicationActivity;
import org.sitenv.ccdaparsing.model.CCDAMedicationSubstanceAdminstration;
import org.sitenv.ccdaparsing.util.ApplicationConstants;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Service
public class MedicationProcessor {
	
	private static final Logger logger = LogManager.getLogger(MedicationProcessor.class);
	
	@Async()
	public Future<CCDAMedication> retrieveMedicationDetails(XPath xPath , Document doc) throws XPathExpressionException,TransformerException
	{
		long startTime = System.currentTimeMillis();
    	logger.info("Medications parsing Start time:"+ startTime);
		
		CCDAMedication medications = null;
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.MEDICATION_EXPRESSION).evaluate(doc, XPathConstants.NODE);
		List<CCDAID> idList = new ArrayList<>();
		if(sectionElement != null)
		{
			medications = new CCDAMedication();
			if(ApplicationUtil.checkForNullFlavourNI(sectionElement))
			{
				medications.setSectionNullFlavourWithNI(true);
				return new AsyncResult<CCDAMedication>(medications);
			}
			medications.setTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
						evaluate(sectionElement, XPathConstants.NODESET)));
			medications.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
			medications.setMedActivities(readMedication((NodeList) xPath.compile("./entry/substanceAdministration[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODESET), xPath,idList));
			
			sectionElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			medications.setLineNumber(sectionElement.getUserData("lineNumber") + " - " + sectionElement.getUserData("endLineNumber") );
			medications.setXmlString(ApplicationUtil.nodeToString((Node)sectionElement));
			
			Element textElement = (Element) xPath.compile("./text[not(@nullFlavor)]").evaluate(sectionElement, XPathConstants.NODE);
			
			if(textElement!=null)
			{
				medications.getReferenceLinks().addAll((ApplicationUtil.readSectionTextReferences((NodeList) xPath.compile(".//*[not(@nullFlavor) and @ID]").
					evaluate(textElement, XPathConstants.NODESET))));
			}
			medications.setIdList(idList);
			
		}
		logger.info("Medications parsing End time:"+ (System.currentTimeMillis() - startTime));
		return new AsyncResult<CCDAMedication>(medications);
	}
	
	public ArrayList<CCDAMedicationActivity> readMedication(NodeList entryNodeList, XPath xPath, List<CCDAID> idList) throws XPathExpressionException,TransformerException
	{
		ArrayList<CCDAMedicationActivity> medicationList = null;
		if(!ApplicationUtil.isNodeListEmpty(entryNodeList))
		{
			medicationList = new ArrayList<>();
		}
		CCDAMedicationActivity medicationActivity;
		for (int i = 0; i < entryNodeList.getLength(); i++) {
			medicationActivity = new CCDAMedicationActivity();
			Element entryElement = (Element) entryNodeList.item(i);
			
			entryElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			medicationActivity.setLineNumber(entryElement.getUserData("lineNumber") + " - " + entryElement.getUserData("endLineNumber") );
			medicationActivity.setXmlString(ApplicationUtil.nodeToString((Node)entryElement));
			
			medicationActivity.setNegationInd(Boolean.parseBoolean(entryElement.getAttribute("negationInd")));
			
			medicationActivity.setTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
									evaluate(entryElement, XPathConstants.NODESET)));
			
			if(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(entryElement, XPathConstants.NODE),"medicationActivity")!= null)
			{
				idList.add(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(entryElement, XPathConstants.NODE),"medicationActivity"));
			}
			
			medicationActivity.setReferenceText(ApplicationUtil.readTextReference((Element) xPath.compile(ApplicationConstants.REFERENCE_TEXT_EXPRESSION).
					evaluate(entryElement, XPathConstants.NODE)));
			
			NodeList effectiveTime = (NodeList) xPath.compile("./effectiveTime[not(@nullFlavor)]").evaluate(entryElement, XPathConstants.NODESET);
			
			for (int j = 0; j < effectiveTime.getLength(); j++) {
				Element effectiveTimeElement = (Element) effectiveTime.item(j);
				if(effectiveTimeElement.getAttribute("xsi:type").equalsIgnoreCase("IVL_TS"))
				{
					medicationActivity.setDuration(readDuration(effectiveTimeElement, xPath));
				}else
				{
					medicationActivity.setFrequency(ApplicationUtil.readFrequency(effectiveTimeElement));
				}
			}
			
			medicationActivity.setStatusCode(ApplicationUtil.readCode((Element) xPath.compile("./statusCode[not(@nullFlavor)]").
					evaluate(entryElement, XPathConstants.NODE)));
			
			medicationActivity.setRouteCode(ApplicationUtil.readCode((Element) xPath.compile("./routeCode[not(@nullFlavor)]").
					evaluate(entryElement, XPathConstants.NODE)));
			
			medicationActivity.setDoseQuantity(ApplicationUtil.readQuantity((Element) xPath.compile("./doseQuantity[not(@nullFlavor)]").
					evaluate(entryElement, XPathConstants.NODE)));
			
			medicationActivity.setRateQuantity(ApplicationUtil.readQuantity((Element) xPath.compile("./rateQuantity[not(@nullFlavor)]").
						evaluate(entryElement, XPathConstants.NODE)));
			
			medicationActivity.setApproachSiteCode(ApplicationUtil.readCode((Element) xPath.compile("./approachSiteCode[not(@nullFlavor)]").
						evaluate(entryElement, XPathConstants.NODE)));
			
			medicationActivity.setAdminUnitCode(ApplicationUtil.readCode((Element) xPath.compile("./administrationUnitCode[not(@nullFlavor)]").
						evaluate(entryElement, XPathConstants.NODE)));
			
			medicationActivity.setConsumable(readMedicationInformation((Element) xPath.compile("./consumable/manufacturedProduct[not(@nullFlavor)]").
					evaluate(entryElement, XPathConstants.NODE), xPath,idList));
			
			Element authorElement = (Element) xPath.compile("./author[not(@nullFlavor)]").evaluate(entryElement, XPathConstants.NODE);
			CCDAAuthor ccdaAuthor = null;
			if(authorElement!=null) {
				ccdaAuthor = new CCDAAuthor();
				ccdaAuthor.setTemplateId(ApplicationUtil.readTemplateID((Element) xPath.compile("./templateId[not(@nullFlavor)]").
					evaluate(authorElement, XPathConstants.NODE)));
				
				ccdaAuthor.setTime(ApplicationUtil.readEffectivetime((Element) xPath.compile("./time[not(@nullFlavor)]").
					evaluate(authorElement, XPathConstants.NODE),xPath));
				
			}
			
			medicationActivity.setAuthor(ccdaAuthor);
			
			medicationActivity.setMedSubAdmin(readMedicationSubAdmin((Element) xPath.compile(ApplicationConstants.MEDICATION_SUBSTANCE_EXPRESSION).
					evaluate(entryElement, XPathConstants.NODE), xPath));
			
			medicationList.add(medicationActivity);
		}
		return medicationList;
	}
	
	public CCDAEffTime readDuration(Element duration, XPath xPath)throws XPathExpressionException,TransformerException
	{
		CCDAEffTime medicationDuration = null;
		if(duration != null)
		{
			medicationDuration = new CCDAEffTime();
		}
		
		if (!ApplicationUtil.isEmpty(duration.getAttribute("value")))
		{
			medicationDuration.setSingleAdministration(duration.getAttribute("value"));
			medicationDuration.setSingleAdministrationValuePresent(true);
		}else
		{
			medicationDuration = ApplicationUtil.readEffectivetime(duration,xPath);
		}
		return medicationDuration;
		
	}
	
	public CCDAConsumable readMedicationInformation(Element medicationInforamtionElement,XPath xPath, List<CCDAID> idList) throws XPathExpressionException,TransformerException
	{
		
		CCDAConsumable consumable = null;
		
		if(medicationInforamtionElement != null)
		{
			consumable = new CCDAConsumable();
			
			medicationInforamtionElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			consumable.setLineNumber(medicationInforamtionElement.getUserData("lineNumber") + " - " + medicationInforamtionElement.getUserData("endLineNumber") );
			consumable.setXmlString(ApplicationUtil.nodeToString((Node)medicationInforamtionElement));
		
			consumable.setTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
							evaluate(medicationInforamtionElement, XPathConstants.NODESET)));
			
			consumable.setMedcode(ApplicationUtil.readCode((Element) xPath.compile("./manufacturedMaterial/code[not(@nullFlavor)]").
					evaluate(medicationInforamtionElement, XPathConstants.NODE)));
			
			consumable.setTranslations(ApplicationUtil.readCodeList((NodeList) xPath.compile("./manufacturedMaterial/code/translation[not(@nullFlavor)]").
						evaluate(medicationInforamtionElement, XPathConstants.NODESET)));
			
			consumable.setManufacturingOrg(ApplicationUtil.readTextContent((Element) xPath.compile("./manufacturerOrganization/name[not(@nullFlavor)]").
						evaluate(medicationInforamtionElement, XPathConstants.NODE)));
			
			consumable.setLotNumberText(ApplicationUtil.readTextContent((Element) xPath.compile("./manufacturedMaterial/lotNumberText[not(@nullFlavor)]").
						evaluate(medicationInforamtionElement, XPathConstants.NODE)));
			
			if(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(medicationInforamtionElement, XPathConstants.NODE),"medicationInformation")!= null)
			{
			
				idList.add(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(medicationInforamtionElement, XPathConstants.NODE),"medicationInformation"));
			}
		}
		return consumable;
	}
	
	public CCDAMedicationSubstanceAdminstration readMedicationSubAdmin(Element subAdminElement, XPath xPath)throws XPathExpressionException,TransformerException
	{
		CCDAMedicationSubstanceAdminstration medicationSubstanceAdmin = null;
		if(subAdminElement != null)
		{
			medicationSubstanceAdmin = new CCDAMedicationSubstanceAdminstration();
			medicationSubstanceAdmin.setLineNumber(subAdminElement.getUserData("lineNumber") + " - " + subAdminElement.getUserData("endLineNumber") );
			medicationSubstanceAdmin.setXmlString(ApplicationUtil.nodeToString((Node)subAdminElement));
			
			medicationSubstanceAdmin.setReferenceText(ApplicationUtil.readTextReference((Element) xPath.compile("./text/reference[not(@nullFlavor)]").
					evaluate(subAdminElement, XPathConstants.NODE)));
		}
		return medicationSubstanceAdmin;
	}
}
