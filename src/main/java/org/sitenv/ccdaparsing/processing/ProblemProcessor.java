package org.sitenv.ccdaparsing.processing;

import java.util.ArrayList;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.sitenv.ccdaparsing.model.CCDAProblem;
import org.sitenv.ccdaparsing.model.CCDAProblemConcern;
import org.sitenv.ccdaparsing.model.CCDAProblemObs;
import org.sitenv.ccdaparsing.util.ApplicationConstants;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ProblemProcessor {
	
	public static CCDAProblem retrieveProblemDetails(XPath xPath , Document doc) throws XPathExpressionException,TransformerException
	{
		CCDAProblem problems = null;
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.PROBLEM_EXPRESSION).evaluate(doc, XPathConstants.NODE);
		if(sectionElement != null)
		{
			problems = new CCDAProblem();
			problems.setSectionTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
											evaluate(sectionElement, XPathConstants.NODESET)));
			problems.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
			problems.setProblemConcerns(readProblemConcern((NodeList) xPath.compile("./entry/act[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODESET), xPath));
			
			sectionElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			problems.setLineNumber(sectionElement.getUserData("lineNumber") + " - " + sectionElement.getUserData("endLineNumber") );
			problems.setXmlString(ApplicationUtil.nodeToString((Node)sectionElement));
			
			Element textElement = (Element) xPath.compile("./text[not(@nullFlavor)]").evaluate(sectionElement, XPathConstants.NODE);
			
			if(textElement!=null)
			{
				problems.getReferenceLinks().addAll((ApplicationUtil.readSectionTextReferences((NodeList) xPath.compile(".//td[not(@nullFlavor)]").
					evaluate(textElement, XPathConstants.NODESET))));
			
				problems.getReferenceLinks().addAll((ApplicationUtil.readSectionTextReferences((NodeList) xPath.compile(".//content[not(@nullFlavor)]").
					evaluate(textElement, XPathConstants.NODESET))));
			}
		}
		return problems;
	}
	
	public static ArrayList<CCDAProblemConcern> readProblemConcern(NodeList problemConcernNodeList, XPath xPath) throws XPathExpressionException,TransformerException
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
			
			problemConcern.getReferenceTexts().addAll(ApplicationUtil.readTextReferences((NodeList) xPath.compile(".//originalText/reference[not(@nullFlavor)]").
					evaluate(problemConcernElement, XPathConstants.NODESET)));

			problemConcern.getReferenceTexts().addAll(ApplicationUtil.readTextReferences((NodeList) xPath.compile(".//text/reference[not(@nullFlavor)]").
					evaluate(problemConcernElement, XPathConstants.NODESET)));
			
			problemConcern.setConcernCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(problemConcernElement, XPathConstants.NODE)));
			
			problemConcern.setStatusCode(ApplicationUtil.readCode((Element) xPath.compile("./statusCode[not(@nullFlavor)]").
					evaluate(problemConcernElement, XPathConstants.NODE)));
			
			problemConcern.setEffTime(ApplicationUtil.readEffectivetime((Element) xPath.compile("./effectiveTime[not(@nullFlavor)]").
								evaluate(problemConcernElement, XPathConstants.NODE), xPath));
			
			problemConcern.setProblemObservations(readProblemObservation((NodeList) xPath.compile(ApplicationConstants.PROBLEM_OBS_EXPRESSION).
					evaluate(problemConcernElement, XPathConstants.NODESET), xPath));
			problemConcernList.add(problemConcern);
		}
		return problemConcernList;
	}
	
	public static ArrayList<CCDAProblemObs> readProblemObservation(NodeList problemObservationNodeList , XPath xPath) throws XPathExpressionException,TransformerException
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
			
			problemObservationElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			problemObservation.setLineNumber(problemObservationElement.getUserData("lineNumber") + " - " + problemObservationElement.getUserData("endLineNumber") );
			problemObservation.setXmlString(ApplicationUtil.nodeToString((Node)problemObservationElement));
			
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
			problemObservationList.add(problemObservation);
		}
		
		return problemObservationList;
		 
	}

}
