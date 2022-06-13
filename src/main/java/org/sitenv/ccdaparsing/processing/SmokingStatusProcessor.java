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
import org.sitenv.ccdaparsing.model.CCDAID;
import org.sitenv.ccdaparsing.model.CCDASmokingStatus;
import org.sitenv.ccdaparsing.model.CCDASocialHistory;
import org.sitenv.ccdaparsing.model.CCDASocialHistoryGenderObs;
import org.sitenv.ccdaparsing.model.CCDATobaccoUse;
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
public class SmokingStatusProcessor {
	
	private static final Logger logger = LogManager.getLogger(SmokingStatusProcessor.class);
	
	@Async()
	public Future<CCDASocialHistory> retrieveSmokingStatusDetails(XPath xPath , Document doc) throws XPathExpressionException,TransformerException
	{
		long startTime = System.currentTimeMillis();
    	logger.info("Smoking status parsing Start time:"+ startTime);
    	
		CCDASocialHistory socailHistory = null;
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.SMOKING_EXPRESSION).evaluate(doc, XPathConstants.NODE);
		List<CCDAID> idList = new ArrayList<>();
		if(sectionElement != null)
		{
			socailHistory = new CCDASocialHistory();
			if(ApplicationUtil.checkForNullFlavourNI(sectionElement))
			{
				socailHistory.setSectionNullFlavourWithNI(true);
				return new AsyncResult<CCDASocialHistory>(socailHistory);
			}
			socailHistory.setSectionTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
						evaluate(sectionElement, XPathConstants.NODESET)));
			
			socailHistory.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
			
			NodeList smokingStatusNodeList = (NodeList) xPath.compile(ApplicationConstants.SMOKING_STATUS_EXPRESSION).
				evaluate(sectionElement, XPathConstants.NODESET);
		
			socailHistory.setSmokingStatus(readSmokingStatus(smokingStatusNodeList , xPath, idList));
		
			NodeList tobaccoUseNodeList = (NodeList) xPath.compile(ApplicationConstants.TOBACCOUSE_EXPRESSION).
				evaluate(sectionElement, XPathConstants.NODESET);
			
			Element genderObsElement = (Element) xPath.compile(ApplicationConstants.SOCIAL_HISTORY_GENDER_EXPRESSION).
					evaluate(sectionElement, XPathConstants.NODE);
			
			sectionElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			socailHistory.setLineNumber(sectionElement.getUserData("lineNumber") + " - " + sectionElement.getUserData("endLineNumber") );
			socailHistory.setXmlString(ApplicationUtil.nodeToString((Node)sectionElement));
			
			Element textElement = (Element) xPath.compile("./text[not(@nullFlavor)]").evaluate(sectionElement, XPathConstants.NODE);
			
			if(textElement!=null)
			{
				socailHistory.getReferenceLinks().addAll((ApplicationUtil.readSectionTextReferences((NodeList) xPath.compile(".//*[not(@nullFlavor) and @ID]").
					evaluate(textElement, XPathConstants.NODESET))));
			}
		
			socailHistory.setTobaccoUse(readTobaccoUse(tobaccoUseNodeList , xPath,idList));
			
			socailHistory.setSocialHistoryGenderObs(readGenderCode(genderObsElement , xPath,idList));
			
			socailHistory.setIdList(idList);
		}
		logger.info("Smoking status parsing End time:"+ (System.currentTimeMillis() - startTime));
		return new AsyncResult<CCDASocialHistory>(socailHistory);
	}
	
	public ArrayList<CCDASmokingStatus> readSmokingStatus(NodeList smokingStatusNodeList, XPath xPath, List<CCDAID> idList) throws XPathExpressionException,TransformerException
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
			
			if(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(smokingStatusElement, XPathConstants.NODE),"smokingStatus")!= null)
			{
				idList.add(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(smokingStatusElement, XPathConstants.NODE),"smokingStatus"));
			}
			
			smokingStatus.setReferenceText(ApplicationUtil.readTextReference((Element) xPath.compile(ApplicationConstants.REFERENCE_TEXT_EXPRESSION).
					evaluate(smokingStatusElement, XPathConstants.NODE)));
			
			smokingStatus.setSmokingStatusTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
														evaluate(smokingStatusElement, XPathConstants.NODESET)));
			
			smokingStatus.setSmokingStatusCode(ApplicationUtil.readCode((Element) xPath.compile("./value[not(@nullFlavor)]").
					evaluate(smokingStatusElement, XPathConstants.NODE)));
			
			smokingStatus.setObservationTime(ApplicationUtil.readEffectivetime((Element) xPath.compile("./effectiveTime[not(@nullFlavor)]").
					evaluate(smokingStatusElement, XPathConstants.NODE),xPath));
			
			smokingStatusList.add(smokingStatus);
		}
		return smokingStatusList;
	}
	
	public ArrayList<CCDATobaccoUse> readTobaccoUse(NodeList tobaccoUseNodeList, XPath xPath, List<CCDAID> idList) throws XPathExpressionException,TransformerException
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
			
			tobaccoUse.setReferenceText(ApplicationUtil.readTextReference((Element) xPath.compile(ApplicationConstants.REFERENCE_TEXT_EXPRESSION).
					evaluate(tobaccoUseElement, XPathConstants.NODE)));
			
			if(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(tobaccoUseElement, XPathConstants.NODE),"TobaccoUse")!= null)
			{
				idList.add(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(tobaccoUseElement, XPathConstants.NODE),"TobaccoUse"));
			}
			
			tobaccoUse.setTobaccoUseCode(ApplicationUtil.readCode((Element) xPath.compile("./value[not(@nullFlavor)]").
					evaluate(tobaccoUseElement, XPathConstants.NODE)));
			
			tobaccoUse.setTobaccoUseTime(ApplicationUtil.readEffectivetime((Element) xPath.compile("./effectiveTime[not(@nullFlavor)]").
					evaluate(tobaccoUseElement, XPathConstants.NODE), xPath));
			
			tobaccoUseList.add(tobaccoUse);
		}
		return tobaccoUseList;
	}
	
	public CCDASocialHistoryGenderObs readGenderCode(Element genderObsElement, XPath xPath,List<CCDAID> idList)
			throws XPathExpressionException, TransformerException {
		CCDASocialHistoryGenderObs genderObs = null;
		Element idElement =null;
		if (genderObsElement != null) {

			genderObs = new CCDASocialHistoryGenderObs();

			genderObsElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			genderObs.setLineNumber(
					genderObsElement.getUserData("lineNumber") + " - " + genderObsElement.getUserData("endLineNumber"));
			genderObs.setXmlString(ApplicationUtil.nodeToString((Node) genderObsElement));
			
			genderObs.setReferenceText(ApplicationUtil.readTextReference((Element) xPath.compile(ApplicationConstants.REFERENCE_TEXT_EXPRESSION).
					evaluate(genderObsElement, XPathConstants.NODE)));
			
			idElement = (Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(genderObsElement, XPathConstants.NODE);
			if(idElement!= null)
			{
				idList.add(ApplicationUtil.readID(idElement,"SocialHistoryGenderObservation"));
			}

			genderObs.setGenderObservationTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath
					.compile("./templateId[not(@nullFlavor)]").evaluate(genderObsElement, XPathConstants.NODESET)));

			genderObs.setGenderCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]")
					.evaluate(genderObsElement, XPathConstants.NODE)));

			genderObs.setGenderValue(ApplicationUtil.readCode((Element) xPath.compile("./value")
					.evaluate(genderObsElement, XPathConstants.NODE)));

		}
		return genderObs;
	}

}
