package org.sitenv.ccdaparsing.processing;

import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.sitenv.ccdaparsing.model.CCDAPQ;
import org.sitenv.ccdaparsing.model.CCDAVitalObs;
import org.sitenv.ccdaparsing.model.CCDAVitalOrg;
import org.sitenv.ccdaparsing.model.CCDAVitalSigns;
import org.sitenv.ccdaparsing.util.ApplicationConstants;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class VitalSignProcessor {
	
	public static CCDAVitalSigns retrieveVitalSigns(XPath xPath , Document doc) throws XPathExpressionException
	{
		CCDAVitalSigns vitalSigns = null;
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.VITALSIGNS_EXPRESSION).evaluate(doc, XPathConstants.NODE);
		if(sectionElement != null)
		{
			vitalSigns = new CCDAVitalSigns();
			vitalSigns.setTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODESET)));
			vitalSigns.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
			vitalSigns.setVitalsOrg(readVitalOrganizer((NodeList) xPath.compile("./entry/organizer[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODESET), xPath));
		}
		return vitalSigns;
	}
	
	
	public static ArrayList<CCDAVitalOrg> readVitalOrganizer(NodeList vitalOrganizerNodeList, XPath xPath) throws XPathExpressionException
	{
		ArrayList<CCDAVitalOrg> vitalOrganizerList = new ArrayList<>();
		CCDAVitalOrg vitalOrganizer;
		for (int i = 0; i < vitalOrganizerNodeList.getLength(); i++) {
			vitalOrganizer = new CCDAVitalOrg();
			
			Element vitalOrganizerElement = (Element) vitalOrganizerNodeList.item(i);
			
			vitalOrganizer.setTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
										evaluate(vitalOrganizerElement, XPathConstants.NODESET)));
			
			vitalOrganizer.setOrgCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(vitalOrganizerElement, XPathConstants.NODE)));
			
			vitalOrganizer.setTranslationCode(ApplicationUtil.readCode((Element) xPath.compile("./code/translation[not(@nullFlavor)]").
					evaluate(vitalOrganizerElement, XPathConstants.NODE)));
			
			vitalOrganizer.setStatusCode(ApplicationUtil.readCode((Element) xPath.compile("./statusCode[not(@nullFlavor)]").
					evaluate(vitalOrganizerElement, XPathConstants.NODE)));
			
			vitalOrganizer.setEffTime(ApplicationUtil.readEffectivetime((Element) xPath.compile("./effectiveTime[not(@nullFlavor)]").
					evaluate(vitalOrganizerElement, XPathConstants.NODE), xPath));
			
			vitalOrganizer.setVitalObs(readVitalObservation((NodeList) xPath.compile("./component/observation[not(@nullFlavor)]").
					evaluate(vitalOrganizerElement, XPathConstants.NODESET), xPath));
			vitalOrganizerList.add(vitalOrganizer);
		}
		return vitalOrganizerList;
	}
	
	public static ArrayList<CCDAVitalObs> readVitalObservation(NodeList vitalObservationNodeList , XPath xPath) throws XPathExpressionException
	{
		
		ArrayList<CCDAVitalObs> vitalObservationList = new ArrayList<>();
		CCDAVitalObs vitalObservation;
		for (int i = 0; i < vitalObservationNodeList.getLength(); i++) {
			
			vitalObservation = new CCDAVitalObs();
			
			Element resultObservationElement = (Element) vitalObservationNodeList.item(i);
			vitalObservation.setTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
					evaluate(resultObservationElement, XPathConstants.NODESET)));
			
			vitalObservation.setVsCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(resultObservationElement, XPathConstants.NODE)));
			
			vitalObservation.setStatusCode(ApplicationUtil.readCode((Element) xPath.compile("./statusCode[not(@nullFlavor)]").
					evaluate(resultObservationElement, XPathConstants.NODE)));
			
			vitalObservation.setMeasurementTime(ApplicationUtil.readDataElement((Element) xPath.compile("./effectiveTime[not(@nullFlavor)]").
					evaluate(resultObservationElement, XPathConstants.NODE)));
			
			vitalObservation.setInterpretationCode(ApplicationUtil.readCode((Element) xPath.compile("./interpretationCode[not(@nullFlavor)]").
					evaluate(resultObservationElement, XPathConstants.NODE)));
			
			Element vsResult = (Element) xPath.compile("./value[not(@nullFlavor)]").
					evaluate(resultObservationElement, XPathConstants.NODE);
			
			if(vsResult != null)
			{
				if(!ApplicationUtil.isEmpty(vsResult.getAttribute("xsi:type")))
				{
					String xsiType = vsResult.getAttribute("xsi:type");
					if (xsiType.equalsIgnoreCase("CD"))
					{
						vitalObservation.setVsResult(new CCDAPQ(vsResult.getTextContent(),"CD"));
					}else if(xsiType.equalsIgnoreCase("PQ"))
					{
						vitalObservation.setVsResult(ApplicationUtil.readQuantity(vsResult));
					}
				}
			}
			
			NodeList referenceRangeNodeList = (NodeList) xPath.compile("./referenceRange/observationRange[value[@type='IVL_PQ']]").
					evaluate(resultObservationElement, XPathConstants.NODESET);
			
			ArrayList<CCDAPQ> referenceValueList = new ArrayList<>();
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
			vitalObservation.setReferenceValue(referenceValueList);
			vitalObservationList.add(vitalObservation);
		}
		return vitalObservationList;
	}
}
