package org.sitenv.ccdaparsing.processing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;
import org.sitenv.ccdaparsing.model.CCDAAllergy;
import org.sitenv.ccdaparsing.model.CCDAAllergyConcern;
import org.sitenv.ccdaparsing.model.CCDAAllergyObs;
import org.sitenv.ccdaparsing.model.CCDAAllergyReaction;
import org.sitenv.ccdaparsing.model.CCDAAllergySeverity;
import org.sitenv.ccdaparsing.model.CCDAAuthor;
import org.sitenv.ccdaparsing.model.CCDAID;
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
public class MediactionAllergiesProcessor {
	
	private static final Logger logger = Logger.getLogger(MediactionAllergiesProcessor.class);
	
	@Async()
	public Future<CCDAAllergy> retrieveAllergiesDetails(XPath xPath , Document doc) throws XPathExpressionException,TransformerException
	{
		long startTime = System.currentTimeMillis();
    	logger.info("Allergies parsing Start time:"+ startTime);
		
		CCDAAllergy allergies = null;
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.ALLERGIES_EXPRESSION).evaluate(doc, XPathConstants.NODE);
		List<CCDAID> idList = new ArrayList<>();
		if(sectionElement != null)
		{
			allergies = new CCDAAllergy();
			if(ApplicationUtil.checkForNullFlavourNI(sectionElement))
			{
				allergies.setSectionNullFlavourWithNI(true);
				return new AsyncResult<CCDAAllergy>(allergies);
			}
			allergies.setSectionTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
							evaluate(sectionElement, XPathConstants.NODESET)));
			allergies.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
			allergies.setAllergyConcern(readAllergyConcern((NodeList) xPath.compile("./entry/act[not(@nullFlavor) and templateId[@root!='2.16.840.1.113883.10.20.22.4.64']]").
					evaluate(sectionElement, XPathConstants.NODESET), xPath,idList));
			sectionElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			allergies.setLineNumber(sectionElement.getUserData("lineNumber") + " - " + sectionElement.getUserData("endLineNumber") );
			allergies.setXmlString(ApplicationUtil.nodeToString((Node)sectionElement));
			
			Element textElement = (Element) xPath.compile("./text[not(@nullFlavor)]").evaluate(sectionElement, XPathConstants.NODE);
			
			if(textElement!=null)
			{
				allergies.getReferenceLinks().addAll((ApplicationUtil.readSectionTextReferences((NodeList) xPath.compile(".//*[not(@nullFlavor) and @ID]").
					evaluate(textElement, XPathConstants.NODESET))));
			}
			allergies.setIdList(idList);
			
		}
		logger.info("Allergies parsing End time:"+ (System.currentTimeMillis() - startTime));
		return new AsyncResult<CCDAAllergy>(allergies);
	}
	
	public ArrayList<CCDAAllergyConcern> readAllergyConcern(NodeList allergyConcernNodeList, XPath xPath, List<CCDAID> idList) throws XPathExpressionException,TransformerException
	{
		ArrayList<CCDAAllergyConcern> allergyConcernList = null;
		if(!ApplicationUtil.isNodeListEmpty(allergyConcernNodeList))
		{
			allergyConcernList = new ArrayList<>();
		}
		CCDAAllergyConcern allergyConcern;
		for (int i = 0; i < allergyConcernNodeList.getLength(); i++) {
			
			allergyConcern = new CCDAAllergyConcern();
			Element allergyConcernElement = (Element) allergyConcernNodeList.item(i);
			
			allergyConcernElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			allergyConcern.setLineNumber(allergyConcernElement.getUserData("lineNumber") + " - " + allergyConcernElement.getUserData("endLineNumber") );
			allergyConcern.setXmlString(ApplicationUtil.nodeToString((Node)allergyConcernElement));
			
			allergyConcern.setTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
																evaluate(allergyConcernElement, XPathConstants.NODESET)));
			
			if(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(allergyConcernElement, XPathConstants.NODE),"allergyConcern")!=null)
			{
				idList.add(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(allergyConcernElement, XPathConstants.NODE),"allergyConcern"));
			}
			
			allergyConcern.setReferenceText(ApplicationUtil.readTextReference((Element) xPath.compile(ApplicationConstants.REFERENCE_TEXT_EXPRESSION).
					evaluate(allergyConcernElement, XPathConstants.NODE)));
			
			allergyConcern.setConcernCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(allergyConcernElement, XPathConstants.NODE)));
			
			allergyConcern.setStatusCode(ApplicationUtil.readCode((Element) xPath.compile("./statusCode[not(@nullFlavor)]").
					evaluate(allergyConcernElement, XPathConstants.NODE)));
			
			allergyConcern.setEffTime(ApplicationUtil.readEffectivetime((Element) xPath.compile("./effectiveTime[not(@nullFlavor)]").
					evaluate(allergyConcernElement, XPathConstants.NODE), xPath));
			
			Element authorElement = (Element) xPath.compile("./author[not(@nullFlavor)]").evaluate(allergyConcernElement, XPathConstants.NODE);
			CCDAAuthor ccdaAuthor = null;
			if(authorElement!=null) {
				ccdaAuthor = new CCDAAuthor();
				ccdaAuthor.setTemplateId(ApplicationUtil.readTemplateID((Element) xPath.compile("./templateId[not(@nullFlavor)]").
					evaluate(authorElement, XPathConstants.NODE)));
				
				ccdaAuthor.setTime(ApplicationUtil.readEffectivetime((Element) xPath.compile("./time[not(@nullFlavor)]").
					evaluate(authorElement, XPathConstants.NODE),xPath));
				
			}
			allergyConcern.setAuthor(ccdaAuthor);
			
			NodeList allergyObservationNodeList = (NodeList) xPath.compile("./entryRelationship/observation[not(@nullFlavor)]").
								evaluate(allergyConcernElement, XPathConstants.NODESET);
			allergyConcern.setAllergyObs(readAllergyObservation(allergyObservationNodeList,xPath,idList));
			allergyConcernList.add(allergyConcern);
		}
		return allergyConcernList;
	}
	
	
	public ArrayList<CCDAAllergyObs> readAllergyObservation(NodeList allergyObservationNodeList,XPath xPath, List<CCDAID> idList) throws XPathExpressionException,TransformerException
	{
		ArrayList<CCDAAllergyObs> allergyObservationList = null;
		if(!ApplicationUtil.isNodeListEmpty(allergyObservationNodeList))
		{
			allergyObservationList = new ArrayList<>();
		}
		CCDAAllergyObs allergyObservation;
		for (int i = 0; i < allergyObservationNodeList.getLength(); i++) {
			
			allergyObservation = new CCDAAllergyObs();
			Element allergyObservationElement = (Element) allergyObservationNodeList.item(i);
			allergyObservationElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			allergyObservation.setLineNumber(allergyObservationElement.getUserData("lineNumber") + " - " + allergyObservationElement.getUserData("endLineNumber") );
			allergyObservation.setXmlString(ApplicationUtil.nodeToString((Node)allergyObservationElement));
			
			allergyObservation.setNegationInd(Boolean.parseBoolean(allergyObservationElement.getAttribute("negationInd")));
			
			if(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(allergyObservationElement, XPathConstants.NODE),"allergyObservation")!= null)
			{
				idList.add(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(allergyObservationElement, XPathConstants.NODE),"allergyObservation"));
			}
			
			allergyObservation.setReferenceText(ApplicationUtil.readTextReference((Element) xPath.compile(ApplicationConstants.REFERENCE_TEXT_EXPRESSION).
					evaluate(allergyObservationElement, XPathConstants.NODE)));
			
			allergyObservation.setTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
												evaluate(allergyObservationElement, XPathConstants.NODESET)));
			
			allergyObservation.setAllergyIntoleranceType(ApplicationUtil.readCode((Element) xPath.compile("./value[not(@nullFlavor)]").
					evaluate(allergyObservationElement, XPathConstants.NODE)));
			
			allergyObservation.setAllergySubstance(ApplicationUtil.readCode((Element) xPath.compile("./participant/participantRole/playingEntity/code[not(@nullFlavor)]").
					evaluate(allergyObservationElement, XPathConstants.NODE)));
			
			allergyObservation.setEffTime(ApplicationUtil.readEffectivetime((Element) xPath.compile("./effectiveTime[not(@nullFlavor)]").
					evaluate(allergyObservationElement, XPathConstants.NODE), xPath));
			
			Element authorElement = (Element) xPath.compile("./author[not(@nullFlavor)]").evaluate(allergyObservationElement, XPathConstants.NODE);
			CCDAAuthor ccdaAuthor = null;
			if(authorElement!=null) {
				ccdaAuthor = new CCDAAuthor();
				ccdaAuthor.setTemplateId(ApplicationUtil.readTemplateID((Element) xPath.compile("./templateId[not(@nullFlavor)]").
					evaluate(authorElement, XPathConstants.NODE)));
				
				ccdaAuthor.setTime(ApplicationUtil.readEffectivetime((Element) xPath.compile("./time[not(@nullFlavor)]").
					evaluate(authorElement, XPathConstants.NODE),xPath));
				
			}
			
			allergyObservation.setAuthor(ccdaAuthor);
			
			NodeList allergyReactionsNodeList = (NodeList) xPath.compile(ApplicationConstants.ALLERGY_REACTION_EXPRESSION).
																evaluate(allergyObservationElement, XPathConstants.NODESET);
			
			CCDAAllergyReaction allergyReaction = null;
			Element allergyReactionElement = null;
			ArrayList<CCDAAllergyReaction> allergyReactionList = null;
			if(!ApplicationUtil.isNodeListEmpty(allergyReactionsNodeList))
			{
				allergyReactionList = new ArrayList<>();
			}
			for (int j = 0; j < allergyReactionsNodeList.getLength(); j++) {
				allergyReaction = new CCDAAllergyReaction();
				allergyReactionElement = (Element) allergyReactionsNodeList.item(j);
				if(ApplicationUtil.checkForNullFlavourNI(allergyReactionElement))
				{
					allergyReaction.setSectionNullFlavourWithNI(true);
				}else {
					allergyReaction.setTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
													evaluate(allergyReactionElement, XPathConstants.NODESET)));
				
					if(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
							evaluate(allergyReactionElement, XPathConstants.NODE),"allergyReaction")!= null)
					{
						idList.add(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
								evaluate(allergyReactionElement, XPathConstants.NODE),"allergyReaction"));
					}
				
					allergyReaction.setReferenceText(ApplicationUtil.readTextReference((Element) xPath.compile(ApplicationConstants.REFERENCE_TEXT_EXPRESSION).
							evaluate(allergyReactionElement, XPathConstants.NODE)));
				
					allergyReaction.setReactionCode(ApplicationUtil.readCode((Element) xPath.compile("./value[not(@nullFlavor)]").
							evaluate(allergyReactionElement, XPathConstants.NODE)));
				
					Element allergySeverityElement = (Element) xPath.compile(ApplicationConstants.ALLERGY_SEVERITY_EXPRESSION).
							evaluate(allergyObservationElement, XPathConstants.NODE);
				
					if(allergySeverityElement != null)
					{
						CCDAAllergySeverity allergySeverity = new CCDAAllergySeverity();
						allergySeverity.setTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
								evaluate(allergySeverityElement, XPathConstants.NODESET)));
						allergySeverity.setSeverity(ApplicationUtil.readCode((Element) xPath.compile("./value[not(@nullFlavor)]").
								evaluate(allergySeverityElement, XPathConstants.NODE)));
						
						allergySeverity.setReferenceText(ApplicationUtil.readTextReference((Element) xPath.compile(ApplicationConstants.REFERENCE_TEXT_EXPRESSION).
								evaluate(allergySeverityElement, XPathConstants.NODE)));
						
						if(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
								evaluate(allergySeverityElement, XPathConstants.NODE),"allergySeverity")!= null)
						{
							idList.add(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
								evaluate(allergySeverityElement, XPathConstants.NODE),"allergySeverity"));
						}
						
						allergyReaction.setSeverity(allergySeverity);
					}
				}
				allergyReactionList.add(allergyReaction);
			}
			
			allergyObservation.setReactions(allergyReactionList);
			
			
			
			allergyObservationList.add(allergyObservation);
		}
		return allergyObservationList;
	}

}
