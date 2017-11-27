package org.sitenv.ccdaparsing.processing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;
import org.sitenv.ccdaparsing.model.CCDAEncounter;
import org.sitenv.ccdaparsing.model.CCDAEncounterActivity;
import org.sitenv.ccdaparsing.model.CCDAEncounterDiagnosis;
import org.sitenv.ccdaparsing.model.CCDAID;
import org.sitenv.ccdaparsing.model.CCDAProblemObs;
import org.sitenv.ccdaparsing.model.CCDAServiceDeliveryLoc;
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
public class EncounterDiagnosesProcessor {
	
	private static final Logger logger = Logger.getLogger(EncounterDiagnosesProcessor.class);
	
	@Async()
	public Future<CCDAEncounter> retrieveEncounterDetails(XPath xPath , Document doc) throws XPathExpressionException,TransformerException
	{
		long startTime = System.currentTimeMillis();
    	logger.info("encounter parsing Start time:"+ startTime);
    	
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.ENCOUNTER_EXPRESSION).evaluate(doc, XPathConstants.NODE);
		CCDAEncounter encounters = null;
		List<CCDAID> idLIst = new ArrayList<>();
		if(sectionElement != null)
		{
			encounters = new CCDAEncounter();
			if(ApplicationUtil.checkForNullFlavourNI(sectionElement))
			{
				encounters.setSectionNullFlavourWithNI(true);
				return new AsyncResult<CCDAEncounter>(encounters);
			}
			encounters.setTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODESET)));
			encounters.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
			encounters.setEncActivities(readEncounterActivity((NodeList) xPath.compile("./entry/encounter[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODESET), xPath,idLIst));
			sectionElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			encounters.setLineNumber(sectionElement.getUserData("lineNumber") + " - " + sectionElement.getUserData("endLineNumber") );
			encounters.setXmlString(ApplicationUtil.nodeToString((Node)sectionElement));
			
			Element textElement = (Element) xPath.compile("./text[not(@nullFlavor)]").evaluate(sectionElement, XPathConstants.NODE);
			
			if(textElement!=null)
			{
			
				encounters.getReferenceLinks().addAll((ApplicationUtil.readSectionTextReferences((NodeList) xPath.compile(".//*[not(@nullFlavor) and @ID]").
					evaluate(textElement, XPathConstants.NODESET))));
			}
			encounters.setIdLIst(idLIst);
		}
		logger.info("encounter parsing End time:"+ (System.currentTimeMillis() - startTime));
		return new AsyncResult<CCDAEncounter>(encounters);
	}
	
	
	public ArrayList<CCDAEncounterActivity> readEncounterActivity(NodeList encounterActivityNodeList , XPath xPath,List<CCDAID> idList) throws XPathExpressionException,TransformerException
	{
		ArrayList<CCDAEncounterActivity> encounterActivityList = new ArrayList<>();
		CCDAEncounterActivity encounterActivity;
		for (int i = 0; i < encounterActivityNodeList.getLength(); i++) {
			
			Element encounterActivityElement = (Element) encounterActivityNodeList.item(i);
			
			if(encounterActivityElement != null)
			{
				encounterActivity = new CCDAEncounterActivity();
				encounterActivity.setTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
						evaluate(encounterActivityElement, XPathConstants.NODESET)));
				
				encounterActivity.setEncounterTypeCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
										evaluate(encounterActivityElement, XPathConstants.NODE)));
				
				if(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
								evaluate(encounterActivityElement, XPathConstants.NODE),"encounterActivity") != null)
				{
				
					idList.add(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
								evaluate(encounterActivityElement, XPathConstants.NODE),"encounterActivity"));
				}
				
				encounterActivityElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
				encounterActivity.setLineNumber(encounterActivityElement.getUserData("lineNumber") + " - " + encounterActivityElement.getUserData("endLineNumber") );
				encounterActivity.setXmlString(ApplicationUtil.nodeToString((Node)encounterActivityElement));
				
				
				encounterActivity.setReferenceText(ApplicationUtil.readTextReference((Element) xPath.compile(ApplicationConstants.REFERENCE_TEXT_EXPRESSION).
															evaluate(encounterActivityElement, XPathConstants.NODE)));
				
				encounterActivity.setEffectiveTime(ApplicationUtil.readEffectivetime((Element) xPath.compile("./effectiveTime[not(@nullFlavor)]").
															evaluate(encounterActivityElement, XPathConstants.NODE),xPath));
				
				encounterActivity.setSdLocs(readServiceDeliveryLocators((NodeList) xPath.compile("./participant/participantRole[not(@nullFlavor)]").
																evaluate(encounterActivityElement, XPathConstants.NODESET), xPath, idList));
				
				NodeList encounterDiagnosisNodeList = (NodeList) xPath.compile("./entryRelationship/act[not(@nullFlavor)]").
								evaluate(encounterActivityElement, XPathConstants.NODESET);
				
				encounterActivity.setDiagnoses(readEncounterDiagnosis(encounterDiagnosisNodeList,xPath,idList));
				
				NodeList indicationNodeList = (NodeList) xPath.compile("./entryRelationship/observation[not(@nullFlavor)]").
								evaluate(encounterActivityElement, XPathConstants.NODESET);
				
				encounterActivity.setIndications(readProblemObservation(indicationNodeList, xPath,idList));
				encounterActivityList.add(encounterActivity);
			}
		}
		return encounterActivityList;
	}
	
	public ArrayList<CCDAEncounterDiagnosis> readEncounterDiagnosis(NodeList encounterDiagnosisNodeList, XPath xPath,List<CCDAID> idList) throws XPathExpressionException,TransformerException
	{
		ArrayList<CCDAEncounterDiagnosis> encounterDiagnosisList = null;
		if(encounterDiagnosisNodeList.getLength() > 0)
		{
			encounterDiagnosisList = new ArrayList<>();
		}
		CCDAEncounterDiagnosis encounterDiagnosis;
		for (int i = 0; i < encounterDiagnosisNodeList.getLength(); i++) {
			
			Element encounterDiagnosisElement = (Element) encounterDiagnosisNodeList.item(i);
			encounterDiagnosis = new CCDAEncounterDiagnosis();
			encounterDiagnosisElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			encounterDiagnosis.setLineNumber(encounterDiagnosisElement.getUserData("lineNumber") + " - " + encounterDiagnosisElement.getUserData("endLineNumber") );
			encounterDiagnosis.setXmlString(ApplicationUtil.nodeToString((Node)encounterDiagnosisElement));
			encounterDiagnosis.setTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
					evaluate(encounterDiagnosisElement, XPathConstants.NODESET)));
			
			encounterDiagnosis.setReferenceText(ApplicationUtil.readTextReference((Element) xPath.compile(ApplicationConstants.REFERENCE_TEXT_EXPRESSION).
					evaluate(encounterDiagnosisElement, XPathConstants.NODE)));

			
			if(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(encounterDiagnosisElement, XPathConstants.NODE),"encounterDiagnosis")!= null)
			{
			
				idList.add(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(encounterDiagnosisElement, XPathConstants.NODE),"encounterDiagnosis"));
			}
			
			encounterDiagnosis.setEntryCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
									evaluate(encounterDiagnosisElement, XPathConstants.NODE)));
			
			NodeList problemObservationNodeList = (NodeList) xPath.compile("./entryRelationship/observation[not(@nullFlavor)]").
										evaluate(encounterDiagnosisElement, XPathConstants.NODESET);
			encounterDiagnosis.setProblemObs(readProblemObservation(problemObservationNodeList,xPath,idList));
			encounterDiagnosisList.add(encounterDiagnosis);
		}
		
		return encounterDiagnosisList;
	}
	
	public ArrayList<CCDAServiceDeliveryLoc> readServiceDeliveryLocators(NodeList serviceDeliveryLocNodeList, XPath xPath,List<CCDAID> idList) throws XPathExpressionException,TransformerException
	{
		ArrayList<CCDAServiceDeliveryLoc> serviceDeliveryLocsList = null;
		if(serviceDeliveryLocNodeList.getLength() > 0)
		{
			serviceDeliveryLocsList = new ArrayList<>();
		}
		CCDAServiceDeliveryLoc serviceDeliveryLoc;
		for (int i = 0; i < serviceDeliveryLocNodeList.getLength(); i++) {
			
			serviceDeliveryLoc = new CCDAServiceDeliveryLoc();
			
			Element serviceDeliveryLocElement = (Element) serviceDeliveryLocNodeList.item(i);
			serviceDeliveryLoc.setTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
											evaluate(serviceDeliveryLocElement, XPathConstants.NODESET)));
			
			serviceDeliveryLoc.setLocationCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
											evaluate(serviceDeliveryLocElement, XPathConstants.NODE)));
			
			/*if(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(serviceDeliveryLocElement, XPathConstants.NODE),"encounterServiceDeliveryLocation")!= null)
			{
			
				idList.add(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(serviceDeliveryLocElement, XPathConstants.NODE),"encounterServiceDeliveryLocation"));
			}*/
			
			serviceDeliveryLoc.setName(ApplicationUtil.readTextContext((Element) xPath.compile("./playingEntity/name[not(@nullFlavor)]").
					evaluate(serviceDeliveryLocElement, XPathConstants.NODE)));
			
			serviceDeliveryLoc.setTelecom(ApplicationUtil.readDataElementList((NodeList) xPath.compile("./telecom[not(@nullFlavor)]").
											evaluate(serviceDeliveryLocElement, XPathConstants.NODESET)));
			serviceDeliveryLoc.setAddress(ApplicationUtil.readAddressList((NodeList) xPath.compile("./addr[not(@nullFlavor)]").
											evaluate(serviceDeliveryLocElement, XPathConstants.NODESET), xPath));
			
			serviceDeliveryLocsList.add(serviceDeliveryLoc);
		}
		
		return serviceDeliveryLocsList;
	}
	
	public ArrayList<CCDAProblemObs> readProblemObservation(NodeList problemObservationNodeList, XPath xPath,List<CCDAID> idList) throws XPathExpressionException,TransformerException
	{
		ArrayList<CCDAProblemObs> problemObservationList = null;
		if(problemObservationNodeList.getLength() > 0)
		{
			problemObservationList = new ArrayList<>();
		}
		CCDAProblemObs problemObservation;
		for (int i = 0; i < problemObservationNodeList.getLength(); i++) {
			
			problemObservation = new CCDAProblemObs();
			
			Element problemObservationElement = (Element) problemObservationNodeList.item(i);
			problemObservationElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			problemObservation.setLineNumber(problemObservationElement.getUserData("lineNumber") + " - " + problemObservationElement.getUserData("endLineNumber") );
			problemObservation.setXmlString(ApplicationUtil.nodeToString((Node)problemObservationElement));
			problemObservation.setTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
					evaluate(problemObservationElement, XPathConstants.NODESET)));
			
			problemObservation.setProblemType(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
									evaluate(problemObservationElement, XPathConstants.NODE)));
			
			problemObservation.setReferenceText(ApplicationUtil.readTextReference((Element) xPath.compile(ApplicationConstants.REFERENCE_TEXT_EXPRESSION).
					evaluate(problemObservationElement, XPathConstants.NODE)));
			
			if(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(problemObservationElement, XPathConstants.NODE),"encounterServiceDeliveryLocation")!= null)
			{
				idList.add(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(problemObservationElement, XPathConstants.NODE),"encounterServiceDeliveryLocation"));
			}
			
			problemObservation.setTranslationProblemType(ApplicationUtil.readCodeList((NodeList) xPath.compile("./code/translation[not(@nullFlavor)]").
									evaluate(problemObservationElement, XPathConstants.NODESET)));
			
			problemObservation.setEffTime(ApplicationUtil.readEffectivetime((Element) xPath.compile("./effectiveTime[not(@nullFlavor)]").
										evaluate(problemObservationElement, XPathConstants.NODE), xPath));
			
			problemObservation.setProblemCode(ApplicationUtil.readCode((Element) xPath.compile("./value[not(@nullFlavor)]").
					evaluate(problemObservationElement, XPathConstants.NODE)));
			
			problemObservationList.add(problemObservation);
		}
		
		return problemObservationList;
	}

}
