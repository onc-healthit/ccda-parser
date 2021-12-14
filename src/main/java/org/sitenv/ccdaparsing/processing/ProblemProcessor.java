package org.sitenv.ccdaparsing.processing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;
import org.sitenv.ccdaparsing.model.CCDAAuthor;
import org.sitenv.ccdaparsing.model.CCDAID;
import org.sitenv.ccdaparsing.model.CCDAProblem;
import org.sitenv.ccdaparsing.model.CCDAProblemConcern;
import org.sitenv.ccdaparsing.model.CCDAProblemObs;
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
public class ProblemProcessor {
	
	private static final Logger logger = Logger.getLogger(ProblemProcessor.class);
	
	@Async()
	public Future<CCDAProblem> retrieveProblemDetails(XPath xPath , Document doc) throws XPathExpressionException,TransformerException
	{
		long startTime = System.currentTimeMillis();
    	logger.info("Problems parsing Start time:"+ startTime);
		CCDAProblem problems = null;
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.PROBLEM_EXPRESSION).evaluate(doc, XPathConstants.NODE);
		List<CCDAID> idList = new ArrayList<>();
		if(sectionElement != null)
		{
			problems = new CCDAProblem();
			if(ApplicationUtil.checkForNullFlavourNI(sectionElement))
			{
				problems.setSectionNullFlavourWithNI(true);
				return new AsyncResult<CCDAProblem>(problems);
			}
			problems.setSectionTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
											evaluate(sectionElement, XPathConstants.NODESET)));
			problems.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
			problems.setProblemConcerns(readProblemConcern((NodeList) xPath.compile("./entry/act[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.4.3']]").
					evaluate(sectionElement, XPathConstants.NODESET), xPath,idList));
			
			sectionElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			problems.setLineNumber(sectionElement.getUserData("lineNumber") + " - " + sectionElement.getUserData("endLineNumber") );
			problems.setXmlString(ApplicationUtil.nodeToString((Node)sectionElement));
			
			Element textElement = (Element) xPath.compile("./text[not(@nullFlavor)]").evaluate(sectionElement, XPathConstants.NODE);
			
			if(textElement!=null)
			{
				problems.getReferenceLinks().addAll((ApplicationUtil.readSectionTextReferences((NodeList) xPath.compile(".//*[not(@nullFlavor) and @ID]").
					evaluate(textElement, XPathConstants.NODESET))));
			}
			problems.setIdList(idList);
		}
		logger.info("Problems parsing End time:"+ (System.currentTimeMillis() - startTime));
		return new AsyncResult<CCDAProblem>(problems);
	}
	
	public ArrayList<CCDAProblemConcern> readProblemConcern(NodeList problemConcernNodeList, XPath xPath, List<CCDAID> idList) throws XPathExpressionException,TransformerException
	{
		ArrayList<CCDAProblemConcern> problemConcernList = new ArrayList<>();
		CCDAProblemConcern problemConcern;
		for (int i = 0; i < problemConcernNodeList.getLength(); i++) {
			problemConcern = new CCDAProblemConcern();
			Element problemConcernElement = (Element) problemConcernNodeList.item(i);
			
			problemConcernElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			problemConcern.setLineNumber(problemConcernElement.getUserData("lineNumber") + " - " + problemConcernElement.getUserData("endLineNumber") );
			problemConcern.setXmlString(ApplicationUtil.nodeToString((Node)problemConcernElement));
			
			problemConcern.setTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
										evaluate(problemConcernElement, XPathConstants.NODESET)));
			
			problemConcern.setReferenceText(ApplicationUtil.readTextReference((Element) xPath.compile(ApplicationConstants.REFERENCE_TEXT_EXPRESSION).
					evaluate(problemConcernElement, XPathConstants.NODE)));
			
			problemConcern.setConcernCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(problemConcernElement, XPathConstants.NODE)));
			
			problemConcern.setStatusCode(ApplicationUtil.readCode((Element) xPath.compile("./statusCode[not(@nullFlavor)]").
					evaluate(problemConcernElement, XPathConstants.NODE)));
			
			problemConcern.setEffTime(ApplicationUtil.readEffectivetime((Element) xPath.compile("./effectiveTime[not(@nullFlavor)]").
								evaluate(problemConcernElement, XPathConstants.NODE), xPath));
			
			if(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(problemConcernElement, XPathConstants.NODE),"problemConcern")!= null)
			{
				idList.add(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(problemConcernElement, XPathConstants.NODE),"problemConcern"));
			}
			Element authorElement = (Element) xPath.compile("./author[not(@nullFlavor)]").evaluate(problemConcernElement, XPathConstants.NODE);
			CCDAAuthor ccdaAuthor = null;
			if(authorElement!=null) {
				ccdaAuthor = new CCDAAuthor();
				ccdaAuthor.setTemplateId(ApplicationUtil.readTemplateID((Element) xPath.compile("./templateId[not(@nullFlavor)]").
					evaluate(authorElement, XPathConstants.NODE)));
				
				ccdaAuthor.setTime(ApplicationUtil.readEffectivetime((Element) xPath.compile("./time[not(@nullFlavor)]").
					evaluate(authorElement, XPathConstants.NODE),xPath));
				
			}
			
			problemConcern.setAuthor(ccdaAuthor);
			
			problemConcern.setProblemObservations(readProblemObservation((NodeList) xPath.compile(ApplicationConstants.PROBLEM_OBS_EXPRESSION).
					evaluate(problemConcernElement, XPathConstants.NODESET), xPath,idList));
			problemConcernList.add(problemConcern);
		}
		return problemConcernList;
	}
	
	public ArrayList<CCDAProblemObs> readProblemObservation(NodeList problemObservationNodeList , XPath xPath, List<CCDAID> idList) throws XPathExpressionException,TransformerException
	{
		
		ArrayList<CCDAProblemObs> problemObservationList = null;
		if(!ApplicationUtil.isNodeListEmpty(problemObservationNodeList))
		{
			problemObservationList = new ArrayList<>();
		}
		CCDAProblemObs problemObservation;
		for (int i = 0; i < problemObservationNodeList.getLength(); i++) {
			
			problemObservation = new CCDAProblemObs();
			
			Element problemObservationElement = (Element) problemObservationNodeList.item(i);
			
			problemObservation.setNegationInd(Boolean.parseBoolean(problemObservationElement.getAttribute("negationInd")));
			
			problemObservationElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			problemObservation.setLineNumber(problemObservationElement.getUserData("lineNumber") + " - " + problemObservationElement.getUserData("endLineNumber") );
			problemObservation.setXmlString(ApplicationUtil.nodeToString((Node)problemObservationElement));
			
			problemObservation.setReferenceText(ApplicationUtil.readTextReference((Element) xPath.compile(ApplicationConstants.REFERENCE_TEXT_EXPRESSION).
					evaluate(problemObservationElement, XPathConstants.NODE)));
			
			
			problemObservation.setTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
					evaluate(problemObservationElement, XPathConstants.NODESET)));
			
			problemObservation.setProblemType(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(problemObservationElement, XPathConstants.NODE)));
			
			problemObservation.setTranslationProblemType(ApplicationUtil.readCodeList((NodeList) xPath.compile("./code/translation[not(@nullFlavor)]").
						evaluate(problemObservationElement, XPathConstants.NODESET)));
			
			problemObservation.setEffTime(ApplicationUtil.readEffectivetime((Element) xPath.compile("./effectiveTime[not(@nullFlavor)]").
					evaluate(problemObservationElement, XPathConstants.NODE), xPath));
			
			problemObservation.setProblemCode(ApplicationUtil.readCode((Element) xPath.compile("./value[not(@nullFlavor)]").
					evaluate(problemObservationElement, XPathConstants.NODE)));
			problemObservation.setStatusCode(ApplicationUtil.readCode((Element) xPath.compile("./statusCode[not(@nullFlavor)]").
					evaluate(problemObservationElement, XPathConstants.NODE)));
			
			Element authorElement = (Element) xPath.compile("./author[not(@nullFlavor)]").evaluate(problemObservationElement, XPathConstants.NODE);
			CCDAAuthor ccdaAuthor = null;
			if(authorElement!=null) {
				ccdaAuthor = new CCDAAuthor();
				ccdaAuthor.setTemplateId(ApplicationUtil.readTemplateID((Element) xPath.compile("./templateId[not(@nullFlavor)]").
					evaluate(authorElement, XPathConstants.NODE)));
				
				ccdaAuthor.setTime(ApplicationUtil.readEffectivetime((Element) xPath.compile("./time[not(@nullFlavor)]").
					evaluate(authorElement, XPathConstants.NODE),xPath));
				
			}
			problemObservation.setAuthor(ccdaAuthor);
			
			if(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(problemObservationElement, XPathConstants.NODE),"problemObservation")!= null)
			{
				idList.add(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(problemObservationElement, XPathConstants.NODE),"problemObservation"));
			}
			problemObservationList.add(problemObservation);
		}
		
		return problemObservationList;
		 
	}

}
