package org.sitenv.ccdaparsing.processing;

import java.util.ArrayList;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.sitenv.ccdaparsing.model.CCDASmokingStatus;
import org.sitenv.ccdaparsing.model.CCDASocialHistory;
import org.sitenv.ccdaparsing.model.CCDATobaccoUse;
import org.sitenv.ccdaparsing.util.ApplicationConstants;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SmokingStatusProcessor {
	
	public static CCDASocialHistory retrieveSmokingStatusDetails(XPath xPath , Document doc) throws XPathExpressionException,TransformerException
	{
		CCDASocialHistory socailHistory = null;
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.SMOKING_EXPRESSION).evaluate(doc, XPathConstants.NODE);
		if(sectionElement != null)
		{
			socailHistory = new CCDASocialHistory();
			socailHistory.setSectionTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
						evaluate(sectionElement, XPathConstants.NODESET)));
			
			socailHistory.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
			
			NodeList smokingStatusNodeList = (NodeList) xPath.compile(ApplicationConstants.SMOKING_STATUS_EXPRESSION).
				evaluate(sectionElement, XPathConstants.NODESET);
		
			socailHistory.setSmokingStatus(readSmokingStatus(smokingStatusNodeList , xPath));
		
			NodeList tobaccoUseNodeList = (NodeList) xPath.compile(ApplicationConstants.TOBACCOUSE_EXPRESSION).
				evaluate(sectionElement, XPathConstants.NODESET);
			
			sectionElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			socailHistory.setLineNumber(sectionElement.getUserData("lineNumber") + " - " + sectionElement.getUserData("endLineNumber") );
			socailHistory.setXmlString(ApplicationUtil.nodeToString((Node)sectionElement));
			
			socailHistory.setReferenceLinks(ApplicationUtil.readSectionTextReferences((NodeList) xPath.compile("./text/table/tbody/tr[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODESET),xPath));
		
			socailHistory.setTobaccoUse(readTobaccoUse(tobaccoUseNodeList , xPath));
		}
		
		return socailHistory;
	}
	
	public static ArrayList<CCDASmokingStatus> readSmokingStatus(NodeList smokingStatusNodeList, XPath xPath) throws XPathExpressionException,TransformerException
	{
		ArrayList<CCDASmokingStatus> smokingStatusList = null;
		if(!ApplicationUtil.isNodeListEmpty(smokingStatusNodeList))
		{
			smokingStatusList = new ArrayList<>();
		}
		CCDASmokingStatus smokingStatus;
		for (int i = 0; i < smokingStatusNodeList.getLength(); i++) {
			smokingStatus = new CCDASmokingStatus();
			
			Element smokingStatusElement = (Element) smokingStatusNodeList.item(i);
			
			smokingStatusElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			smokingStatus.setLineNumber(smokingStatusElement.getUserData("lineNumber") + " - " + smokingStatusElement.getUserData("endLineNumber") );
			smokingStatus.setXmlString(ApplicationUtil.nodeToString((Node)smokingStatusElement));
			
			smokingStatus.getReferenceTexts().addAll(ApplicationUtil.readTextReferences((NodeList) xPath.compile(".//originalText/reference[not(@nullFlavor)]").
					evaluate(smokingStatusElement, XPathConstants.NODESET)));

			smokingStatus.getReferenceTexts().addAll(ApplicationUtil.readTextReferences((NodeList) xPath.compile(".//text/reference[not(@nullFlavor)]").
					evaluate(smokingStatusElement, XPathConstants.NODESET)));
			
			smokingStatus.setSmokingStatusTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
														evaluate(smokingStatusElement, XPathConstants.NODESET)));
			
			smokingStatus.setSmokingStatusCode(ApplicationUtil.readCode((Element) xPath.compile("./value[not(@nullFlavor)]").
					evaluate(smokingStatusElement, XPathConstants.NODE)));
			
			smokingStatus.setObservationTime(ApplicationUtil.readDataElement((Element) xPath.compile("./effectiveTime[not(@nullFlavor)]").
					evaluate(smokingStatusElement, XPathConstants.NODE)));
			
			smokingStatusList.add(smokingStatus);
		}
		return smokingStatusList;
	}
	
	public static ArrayList<CCDATobaccoUse> readTobaccoUse(NodeList tobaccoUseNodeList, XPath xPath) throws XPathExpressionException,TransformerException
	{
		ArrayList<CCDATobaccoUse> tobaccoUseList = null;
		if(!ApplicationUtil.isNodeListEmpty(tobaccoUseNodeList))
		{
			tobaccoUseList = new ArrayList<>();
		}
		CCDATobaccoUse tobaccoUse;
		for (int i = 0; i < tobaccoUseNodeList.getLength(); i++) {
			tobaccoUse = new CCDATobaccoUse();
			
			Element tobaccoUseElement = (Element) tobaccoUseNodeList.item(i);
			
			tobaccoUseElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			tobaccoUse.setLineNumber(tobaccoUseElement.getUserData("lineNumber") + " - " + tobaccoUseElement.getUserData("endLineNumber") );
			tobaccoUse.setXmlString(ApplicationUtil.nodeToString((Node)tobaccoUseElement));
			
			tobaccoUse.setTobaccoUseTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
														evaluate(tobaccoUseElement, XPathConstants.NODESET)));
			
			tobaccoUse.setTobaccoUseCode(ApplicationUtil.readCode((Element) xPath.compile("./value[not(@nullFlavor)]").
					evaluate(tobaccoUseElement, XPathConstants.NODE)));
			
			tobaccoUse.setTobaccoUseTime(ApplicationUtil.readEffectivetime((Element) xPath.compile("./effectiveTime[not(@nullFlavor)]").
					evaluate(tobaccoUseElement, XPathConstants.NODE), xPath));
			
			tobaccoUseList.add(tobaccoUse);
		}
		return tobaccoUseList;
	}

}
