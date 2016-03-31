package org.sitenv.ccdaparsing.processing;

import java.util.ArrayList;

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
import org.w3c.dom.NodeList;

public class SmokingStatusProcessor {
	
	public static CCDASocialHistory retrieveSmokingStatusDetails(XPath xPath , Document doc) throws XPathExpressionException
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
		
			socailHistory.setTobaccoUse(readTobaccoUse(tobaccoUseNodeList , xPath));
		}
		
		return socailHistory;
	}
	
	public static ArrayList<CCDASmokingStatus> readSmokingStatus(NodeList smokingStatusNodeList, XPath xPath) throws XPathExpressionException
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
	
	public static ArrayList<CCDATobaccoUse> readTobaccoUse(NodeList tobaccoUseNodeList, XPath xPath) throws XPathExpressionException
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
