package org.sitenv.ccdaparsing.processing;

import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.sitenv.ccdaparsing.model.CCDALabResult;
import org.sitenv.ccdaparsing.model.CCDALabResultObs;
import org.sitenv.ccdaparsing.model.CCDALabResultOrg;
import org.sitenv.ccdaparsing.model.CCDAPQ;
import org.sitenv.ccdaparsing.util.ApplicationConstants;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class LaboratoryResultsProcessor {
	
	public static CCDALabResult retrieveLabResults(XPath xPath , Document doc) throws XPathExpressionException
	{
		CCDALabResult labResults = null;
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.RESULTS_EXPRESSION).evaluate(doc, XPathConstants.NODE);
		if(sectionElement != null)
		{
			labResults = new CCDALabResult();
			labResults.setResultSectionTempalteIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
													evaluate(sectionElement, XPathConstants.NODESET)));
			
			labResults.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
						evaluate(sectionElement, XPathConstants.NODE)));
			
			labResults.setResultOrg(readResultOrganizer((NodeList) xPath.compile(ApplicationConstants.LAB_RESULTS_EXPRESSION).
									evaluate(sectionElement, XPathConstants.NODESET),xPath));
			labResults.setIsLabTestInsteadOfResult(false);
		}
		return labResults;
	}
	
	
	public static ArrayList<CCDALabResultOrg> readResultOrganizer(NodeList resultOrganizerNodeList, XPath xPath) throws XPathExpressionException
	{
		ArrayList<CCDALabResultOrg> labResultOrgList = new ArrayList<>();
		CCDALabResultOrg labResultOrg;
		for (int i = 0; i < resultOrganizerNodeList.getLength(); i++) {
			labResultOrg = new CCDALabResultOrg();
			
			Element labResultOrgElement = (Element) resultOrganizerNodeList.item(i);
			
			labResultOrg.setTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
										evaluate(labResultOrgElement, XPathConstants.NODESET)));
			
			labResultOrg.setOrgCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(labResultOrgElement, XPathConstants.NODE)));
			
			labResultOrg.setStatusCode(ApplicationUtil.readCode((Element) xPath.compile("./statusCode[not(@nullFlavor)]").
					evaluate(labResultOrgElement, XPathConstants.NODE)));
			
			labResultOrg.setEffTime(ApplicationUtil.readEffectivetime((Element) xPath.compile("./effectiveTime[not(@nullFlavor)]").
					evaluate(labResultOrgElement, XPathConstants.NODE), xPath));
			
			labResultOrg.setResultObs(readResultObservation((NodeList) xPath.compile("./component/observation[not(@nullFlavor)]").
					evaluate(labResultOrgElement, XPathConstants.NODESET), xPath));
			labResultOrgList.add(labResultOrg);
		}
		return labResultOrgList;
	}
	
	public static ArrayList<CCDALabResultObs> readResultObservation(NodeList resultObservationNodeList , XPath xPath) throws XPathExpressionException
	{
		
		ArrayList<CCDALabResultObs> resultObservationList = new ArrayList<>();
		CCDALabResultObs resultObservation;
		for (int i = 0; i < resultObservationNodeList.getLength(); i++) {
			
			resultObservation = new CCDALabResultObs();
			
			Element resultObservationElement = (Element) resultObservationNodeList.item(i);
			resultObservation.setTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId").
					evaluate(resultObservationElement, XPathConstants.NODESET)));
			
			resultObservation.setResultCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(resultObservationElement, XPathConstants.NODE)));
			
			resultObservation.setStatusCode(ApplicationUtil.readCode((Element) xPath.compile("./statusCode[not(@nullFlavor)]").
					evaluate(resultObservationElement, XPathConstants.NODE)));
			
			resultObservation.setMeasurementTime(ApplicationUtil.readDataElement((Element) xPath.compile("./effectiveTime[not(@nullFlavor)]").
					evaluate(resultObservationElement, XPathConstants.NODE)));
			
			resultObservation.setInterpretationCode(ApplicationUtil.readCode((Element) xPath.compile("./interpretationCode[not(@nullFlavor)]").
					evaluate(resultObservationElement, XPathConstants.NODE)));
			
			Element resultValue = (Element) xPath.compile("./value[not(@nullFlavor)]").
					evaluate(resultObservationElement, XPathConstants.NODE);
			
			if(resultValue != null)
			{
				if(!ApplicationUtil.isEmpty(resultValue.getAttribute("xsi:type")))
				{
					String xsiType = resultValue.getAttribute("xsi:type");
					if (xsiType.equalsIgnoreCase("CD"))
					{
						resultObservation.setResults(new CCDAPQ(resultValue.getTextContent()));
					}else if(xsiType.equalsIgnoreCase("PQ"))
					{
						resultObservation.setResults(ApplicationUtil.readQuantity(resultValue));
					}
				}
			}
			
			NodeList referenceRangeNodeList = (NodeList) xPath.compile("./referenceRange/observationRange/value[@type='IVL_PQ']").
			evaluate(resultObservationElement, XPathConstants.NODESET);
			
			ArrayList<CCDAPQ> referenceValueList =null;
			
			if(! ApplicationUtil.isNodeListEmpty(referenceRangeNodeList))
			{
				referenceValueList = new ArrayList<>();
			}
			
			for (int j = 0; j < referenceRangeNodeList.getLength(); j++) { 
				
				Element referenceRangeElement = (Element) referenceRangeNodeList.item(j);
				
				if(referenceRangeElement != null)
				{
					referenceValueList.add(ApplicationUtil.readQuantity((Element) xPath.compile("./low[not(@nullFlavor)]").
		    				evaluate(referenceRangeElement, XPathConstants.NODE)));
					referenceValueList.add(ApplicationUtil.readQuantity((Element) xPath.compile("./high[not(@nullFlavor)]").
		    				evaluate(referenceRangeElement, XPathConstants.NODE)));
				}
			}
			resultObservation.setReferenceRange(referenceValueList);
			resultObservationList.add(resultObservation);
			
		}
		
		return resultObservationList;
	}

}
