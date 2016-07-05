package org.sitenv.ccdaparsing.processing;

import java.util.ArrayList;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.sitenv.ccdaparsing.model.CCDACode;
import org.sitenv.ccdaparsing.model.CCDALabResult;
import org.sitenv.ccdaparsing.model.CCDALabResultObs;
import org.sitenv.ccdaparsing.model.CCDALabResultOrg;
import org.sitenv.ccdaparsing.model.CCDAPQ;
import org.sitenv.ccdaparsing.util.ApplicationConstants;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LaboratoryResultsProcessor {
	
	public static CCDALabResult retrieveLabResults(XPath xPath , Document doc) throws XPathExpressionException,TransformerException
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
			sectionElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			labResults.setLineNumber(sectionElement.getUserData("lineNumber") + " - " + sectionElement.getUserData("endLineNumber") );
			labResults.setXmlString(ApplicationUtil.nodeToString((Node)sectionElement));
			
			Element textElement = (Element) xPath.compile("./text[not(@nullFlavor)]").evaluate(sectionElement, XPathConstants.NODE);
			
			if(textElement!=null)
			{
				labResults.getReferenceLinks().addAll((ApplicationUtil.readSectionTextReferences((NodeList) xPath.compile(".//td[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODESET))));
			
				labResults.getReferenceLinks().addAll((ApplicationUtil.readSectionTextReferences((NodeList) xPath.compile(".//content[not(@nullFlavor)]").
					evaluate(textElement, XPathConstants.NODESET))));
			}
			labResults.setIsLabTestInsteadOfResult(false);
		}
		return labResults;
	}
	
	
	public static ArrayList<CCDALabResultOrg> readResultOrganizer(NodeList resultOrganizerNodeList, XPath xPath) throws XPathExpressionException,TransformerException
	{
		ArrayList<CCDALabResultOrg> labResultOrgList = new ArrayList<>();
		CCDALabResultOrg labResultOrg;
		for (int i = 0; i < resultOrganizerNodeList.getLength(); i++) {
			labResultOrg = new CCDALabResultOrg();
			
			Element labResultOrgElement = (Element) resultOrganizerNodeList.item(i);
			
			labResultOrgElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			labResultOrg.setLineNumber(labResultOrgElement.getUserData("lineNumber") + " - " + labResultOrgElement.getUserData("endLineNumber") );
			
			
			labResultOrg.setXmlString(ApplicationUtil.nodeToString((Node)labResultOrgElement));
			
			labResultOrg.setTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
										evaluate(labResultOrgElement, XPathConstants.NODESET)));
			
			labResultOrg.getReferenceTexts().addAll(ApplicationUtil.readTextReferences((NodeList) xPath.compile(".//originalText/reference[not(@nullFlavor)]").
					evaluate(labResultOrgElement, XPathConstants.NODESET)));

			labResultOrg.getReferenceTexts().addAll(ApplicationUtil.readTextReferences((NodeList) xPath.compile(".//text/reference[not(@nullFlavor)]").
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
	
	public static ArrayList<CCDALabResultObs> readResultObservation(NodeList resultObservationNodeList , XPath xPath) throws XPathExpressionException,TransformerException
	{
		
		ArrayList<CCDALabResultObs> resultObservationList = new ArrayList<>();
		CCDALabResultObs resultObservation;
		for (int i = 0; i < resultObservationNodeList.getLength(); i++) {
			
			resultObservation = new CCDALabResultObs();
			
			Element resultObservationElement = (Element) resultObservationNodeList.item(i);
			
			resultObservationElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			resultObservation.setLineNumber(resultObservationElement.getUserData("lineNumber") + " - " + resultObservationElement.getUserData("endLineNumber") );
			resultObservation.setXmlString(ApplicationUtil.nodeToString((Node)resultObservationElement));
			
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
						CCDACode valueCode =  ApplicationUtil.readCode(resultValue);
						resultObservation.setResults(new CCDAPQ(valueCode.getCode(),"CD"));
					}else if(xsiType.equalsIgnoreCase("PQ"))
					{
						resultObservation.setResults(ApplicationUtil.readQuantity(resultValue));
					}else if (xsiType.equalsIgnoreCase("ST"))
					{
						resultObservation.setResults(new CCDAPQ(resultValue.getTextContent(),"ST"));
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
