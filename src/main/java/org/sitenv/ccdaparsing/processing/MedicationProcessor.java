package org.sitenv.ccdaparsing.processing;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.sitenv.ccdaparsing.model.CCDAConsumable;
import org.sitenv.ccdaparsing.model.CCDAEffTime;
import org.sitenv.ccdaparsing.model.CCDAID;
import org.sitenv.ccdaparsing.model.CCDAMedication;
import org.sitenv.ccdaparsing.model.CCDAMedicationActivity;
import org.sitenv.ccdaparsing.util.ApplicationConstants;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MedicationProcessor {
	
	public static CCDAMedication retrieveMedicationDetails(XPath xPath , Document doc, List<CCDAID> idList) throws XPathExpressionException,TransformerException
	{
		CCDAMedication medications = null;
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.MEDICATION_EXPRESSION).evaluate(doc, XPathConstants.NODE);
		if(sectionElement != null)
		{
			medications = new CCDAMedication();
			medications.setTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
						evaluate(sectionElement, XPathConstants.NODESET)));
			medications.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
			medications.setMedActivities(readMedication((NodeList) xPath.compile("./entry/substanceAdministration[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODESET), xPath,idList));
			
			sectionElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			medications.setLineNumber(sectionElement.getUserData("lineNumber") + " - " + sectionElement.getUserData("endLineNumber") );
			medications.setXmlString(ApplicationUtil.nodeToString((Node)sectionElement));
			
			Element textElement = (Element) xPath.compile("./text[not(@nullFlavor)]").evaluate(sectionElement, XPathConstants.NODE);
			
			if(textElement!=null)
			{
				medications.getReferenceLinks().addAll((ApplicationUtil.readSectionTextReferences((NodeList) xPath.compile(".//td[not(@nullFlavor)]").
					evaluate(textElement, XPathConstants.NODESET))));
			
				medications.getReferenceLinks().addAll((ApplicationUtil.readSectionTextReferences((NodeList) xPath.compile(".//content[not(@nullFlavor)]").
					evaluate(textElement, XPathConstants.NODESET))));
			}
		}
		return medications;
	}
	
	public static ArrayList<CCDAMedicationActivity> readMedication(NodeList entryNodeList, XPath xPath, List<CCDAID> idList) throws XPathExpressionException,TransformerException
	{
		ArrayList<CCDAMedicationActivity> medicationList = null;
		if(!ApplicationUtil.isNodeListEmpty(entryNodeList))
		{
			medicationList = new ArrayList<>();
		}
		CCDAMedicationActivity medicationActivity;
		for (int i = 0; i < entryNodeList.getLength(); i++) {
			medicationActivity = new CCDAMedicationActivity();
			Element entryElement = (Element) entryNodeList.item(i);
			
			entryElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			medicationActivity.setLineNumber(entryElement.getUserData("lineNumber") + " - " + entryElement.getUserData("endLineNumber") );
			medicationActivity.setXmlString(ApplicationUtil.nodeToString((Node)entryElement));
			
			medicationActivity.setTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
									evaluate(entryElement, XPathConstants.NODESET)));
			
			if(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(entryElement, XPathConstants.NODE),"medicationActivity")!= null)
			{
				idList.add(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(entryElement, XPathConstants.NODE),"medicationActivity"));
			}
			
			medicationActivity.getReferenceTexts().addAll(ApplicationUtil.readTextReferences((NodeList) xPath.compile(".//originalText/reference[not(@nullFlavor)]").
					evaluate(entryElement, XPathConstants.NODESET)));

			medicationActivity.getReferenceTexts().addAll(ApplicationUtil.readTextReferences((NodeList) xPath.compile(".//text/reference[not(@nullFlavor)]").
					evaluate(entryElement, XPathConstants.NODESET)));
			
			NodeList effectiveTime = (NodeList) xPath.compile("./effectiveTime[not(@nullFlavor)]").evaluate(entryElement, XPathConstants.NODESET);
			
			for (int j = 0; j < effectiveTime.getLength(); j++) {
				Element effectiveTimeElement = (Element) effectiveTime.item(j);
				if(effectiveTimeElement.getAttribute("xsi:type").equalsIgnoreCase("IVL_TS"))
				{
					medicationActivity.setDuration(readDuration(effectiveTimeElement, xPath));
				}else
				{
					medicationActivity.setFrequency(ApplicationUtil.readFrequency(effectiveTimeElement));
				}
			}
			
			medicationActivity.setRouteCode(ApplicationUtil.readCode((Element) xPath.compile("./routeCode[not(@nullFlavor)]").
					evaluate(entryElement, XPathConstants.NODE)));
			
			medicationActivity.setDoseQuantity(ApplicationUtil.readQuantity((Element) xPath.compile("./doseQuantity[not(@nullFlavor)]").
					evaluate(entryElement, XPathConstants.NODE)));
			
			medicationActivity.setRateQuantity(ApplicationUtil.readQuantity((Element) xPath.compile("./rateQuantity[not(@nullFlavor)]").
						evaluate(entryElement, XPathConstants.NODE)));
			
			medicationActivity.setApproachSiteCode(ApplicationUtil.readCode((Element) xPath.compile("./approachSiteCode[not(@nullFlavor)]").
						evaluate(entryElement, XPathConstants.NODE)));
			
			medicationActivity.setAdminUnitCode(ApplicationUtil.readCode((Element) xPath.compile("./administrationUnitCode[not(@nullFlavor)]").
						evaluate(entryElement, XPathConstants.NODE)));
			
			medicationActivity.setConsumable(readMedicationInformation((Element) xPath.compile("./consumable/manufacturedProduct[not(@nullFlavor)]").
					evaluate(entryElement, XPathConstants.NODE), xPath,idList));
			medicationList.add(medicationActivity);
		}
		return medicationList;
	}
	
	public static CCDAEffTime readDuration(Element duration, XPath xPath)throws XPathExpressionException,TransformerException
	{
		CCDAEffTime medicationDuration = null;
		if(duration != null)
		{
			medicationDuration = new CCDAEffTime();
		}
		
		if (!ApplicationUtil.isEmpty(duration.getAttribute("value")))
		{
			medicationDuration.setSingleAdministration(duration.getAttribute("value"));
		}else
		{
			medicationDuration = ApplicationUtil.readEffectivetime(duration,xPath);
		}
		return medicationDuration;
		
	}
	
	public static CCDAConsumable readMedicationInformation(Element medicationInforamtionElement,XPath xPath, List<CCDAID> idList) throws XPathExpressionException,TransformerException
	{
		
		CCDAConsumable consumable = null;
		
		if(medicationInforamtionElement != null)
		{
			consumable = new CCDAConsumable();
			
			medicationInforamtionElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			consumable.setLineNumber(medicationInforamtionElement.getUserData("lineNumber") + " - " + medicationInforamtionElement.getUserData("endLineNumber") );
			consumable.setXmlString(ApplicationUtil.nodeToString((Node)medicationInforamtionElement));
		
			consumable.setTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
							evaluate(medicationInforamtionElement, XPathConstants.NODESET)));
			
			consumable.setMedcode(ApplicationUtil.readCode((Element) xPath.compile("./manufacturedMaterial/code[not(@nullFlavor)]").
					evaluate(medicationInforamtionElement, XPathConstants.NODE)));
			
			consumable.setTranslations(ApplicationUtil.readCodeList((NodeList) xPath.compile("./manufacturedMaterial/code/translation[not(@nullFlavor)]").
						evaluate(medicationInforamtionElement, XPathConstants.NODESET)));
			
			consumable.setManufacturingOrg(ApplicationUtil.readTextContext((Element) xPath.compile("./manufacturerOrganization/name[not(@nullFlavor)]").
						evaluate(medicationInforamtionElement, XPathConstants.NODE)));
			
			consumable.setLotNumberText(ApplicationUtil.readTextContext((Element) xPath.compile("./manufacturedMaterial/lotNumberText[not(@nullFlavor)]").
						evaluate(medicationInforamtionElement, XPathConstants.NODE)));
			
			if(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(medicationInforamtionElement, XPathConstants.NODE),"medicationInformation")!= null)
			{
			
				idList.add(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(medicationInforamtionElement, XPathConstants.NODE),"medicationInformation"));
			}
		}
		
		
		return consumable;
	}

}
