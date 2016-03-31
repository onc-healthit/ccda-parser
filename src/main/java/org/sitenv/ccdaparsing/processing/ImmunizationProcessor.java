package org.sitenv.ccdaparsing.processing;

import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.sitenv.ccdaparsing.model.CCDAImmunization;
import org.sitenv.ccdaparsing.model.CCDAImmunizationActivity;
import org.sitenv.ccdaparsing.model.CCDAOrganization;
import org.sitenv.ccdaparsing.util.ApplicationConstants;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ImmunizationProcessor {
	
	public static CCDAImmunization retrieveImmunizationDetails(XPath xPath , Document doc) throws XPathExpressionException
	{
		CCDAImmunization immunizations = null;
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.IMMUNIZATION_EXPRESSION).evaluate(doc, XPathConstants.NODE);
		
		if(sectionElement != null)
		{
			immunizations = new CCDAImmunization();
			immunizations.setTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODESET)));
			
			immunizations.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
			immunizations.setImmActivity(readImmunization((NodeList) xPath.compile("./entry[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODESET), xPath));
		}
		return immunizations;
	}
	
	public static ArrayList<CCDAImmunizationActivity> readImmunization(NodeList entryNodeList, XPath xPath) throws XPathExpressionException
	{
		ArrayList<CCDAImmunizationActivity> immunizationActivityList = new ArrayList<>();
		CCDAImmunizationActivity immunizationActivity;
		
		for (int i = 0; i < entryNodeList.getLength(); i++) {
			
			immunizationActivity = new CCDAImmunizationActivity();
			Element entryElement = (Element) entryNodeList.item(i);
			
			Element immunizationActivityElement = (Element) xPath.compile("./substanceAdministration[not(@nullFlavor)]").
					evaluate(entryElement, XPathConstants.NODE);
			
			if(immunizationActivityElement != null)
			{
			
				immunizationActivity.setTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
													evaluate(immunizationActivityElement, XPathConstants.NODESET)));
				
				immunizationActivity.setTime(ApplicationUtil.readDataElement((Element) xPath.compile("./effectiveTime[not(@nullFlavor)]").
						evaluate(immunizationActivityElement, XPathConstants.NODE)));
				
				immunizationActivity.setRouteCode(ApplicationUtil.readCode((Element) xPath.compile("./routeCode[not(@nullFlavor)]").
						evaluate(immunizationActivityElement, XPathConstants.NODE)));
				
				immunizationActivity.setDoseQuantity(ApplicationUtil.readQuantity((Element) xPath.compile("./doseQuantity[not(@nullFlavor)]").
						evaluate(immunizationActivityElement, XPathConstants.NODE)));
				
				immunizationActivity.setApproachSiteCode(ApplicationUtil.readCode((Element) xPath.compile("./approachSiteCode[not(@nullFlavor)]").
							evaluate(immunizationActivityElement, XPathConstants.NODE)));
				
				immunizationActivity.setAdminUnitCode(ApplicationUtil.readCode((Element) xPath.compile("./administrationUnitCode[not(@nullFlavor)]").
							evaluate(immunizationActivityElement, XPathConstants.NODE)));
				
				immunizationActivity.setConsumable(MedicationProcessor.readMedicationInformation((Element) xPath.compile("./consumable/manufacturedProduct[not(@nullFlavor)]").
						   evaluate(immunizationActivityElement, XPathConstants.NODE), xPath));
				
				
				Element represntOrgElement = (Element) xPath.compile("./performer/assignedEntity/representedOrganization[not(@nullFlavor)]").
											evaluate(immunizationActivityElement, XPathConstants.NODE);
				if(represntOrgElement != null)
				{
					CCDAOrganization representedOrg = new  CCDAOrganization();
					
					representedOrg.setAddress(ApplicationUtil.readAddressList((NodeList) xPath.compile("./addr[not(@nullFlavor)]").
							evaluate(represntOrgElement, XPathConstants.NODESET), xPath));
					
					representedOrg.setTelecom(ApplicationUtil.readDataElementList((NodeList) xPath.compile("./telecom[not(@nullFlavor)]").
							evaluate(represntOrgElement, XPathConstants.NODESET)));
					
					representedOrg.setNames( ApplicationUtil.readTextContentList((NodeList) xPath.compile("./name[not(@nullFlavor)]").
							evaluate(represntOrgElement, XPathConstants.NODESET)));
					
					immunizationActivity.setOrganization(representedOrg);
				}
				immunizationActivityList.add(immunizationActivity);
			}
		}
		return immunizationActivityList;
	}

}
