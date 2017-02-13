package org.sitenv.ccdaparsing.processing;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.sitenv.ccdaparsing.model.CCDAID;
import org.sitenv.ccdaparsing.model.CCDAImmunization;
import org.sitenv.ccdaparsing.model.CCDAImmunizationActivity;
import org.sitenv.ccdaparsing.model.CCDAOrganization;
import org.sitenv.ccdaparsing.util.ApplicationConstants;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ImmunizationProcessor {
	
	public static CCDAImmunization retrieveImmunizationDetails(XPath xPath , Document doc,List<CCDAID> idList) throws XPathExpressionException,TransformerException
	{
		CCDAImmunization immunizations = null;
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.IMMUNIZATION_EXPRESSION).evaluate(doc, XPathConstants.NODE);
		
		if(sectionElement != null)
		{
			immunizations = new CCDAImmunization();
			if(ApplicationUtil.checkForNullFlavourNI(sectionElement))
			{
				immunizations.setSectionNullFlavourWithNI(true);
				return immunizations;
			}
			immunizations.setTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODESET)));
			
			immunizations.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
			immunizations.setImmActivity(readImmunization((NodeList) xPath.compile("./entry[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODESET), xPath,idList));
			sectionElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			immunizations.setLineNumber(sectionElement.getUserData("lineNumber") + " - " + sectionElement.getUserData("endLineNumber") );
			immunizations.setXmlString(ApplicationUtil.nodeToString((Node)sectionElement));
			
			Element textElement = (Element) xPath.compile("./text[not(@nullFlavor)]").evaluate(sectionElement, XPathConstants.NODE);
			
			if(textElement!=null)
			{
				immunizations.getReferenceLinks().addAll((ApplicationUtil.readSectionTextReferences((NodeList) xPath.compile(".//*[not(@nullFlavor) and @ID]").
					evaluate(textElement, XPathConstants.NODESET))));
			}
		}
		return immunizations;
	}
	
	public static ArrayList<CCDAImmunizationActivity> readImmunization(NodeList entryNodeList, XPath xPath,List<CCDAID> idList) throws XPathExpressionException,TransformerException
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
			
				immunizationActivityElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
				immunizationActivity.setLineNumber(immunizationActivityElement.getUserData("lineNumber") + " - " + immunizationActivityElement.getUserData("endLineNumber") );
				immunizationActivity.setXmlString(ApplicationUtil.nodeToString((Node)immunizationActivityElement));
				
				if(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
						evaluate(immunizationActivityElement, XPathConstants.NODE),"immunizatonActivity")!= null)
				{
					idList.add(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
						evaluate(immunizationActivityElement, XPathConstants.NODE),"immunizatonActivity"));
				}
				
				immunizationActivity.setTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
													evaluate(immunizationActivityElement, XPathConstants.NODESET)));
				
				immunizationActivity.getReferenceTexts().addAll(ApplicationUtil.readTextReferences((NodeList) xPath.compile(".//originalText/reference[not(@nullFlavor)]").
						evaluate(immunizationActivityElement, XPathConstants.NODESET)));

				immunizationActivity.getReferenceTexts().addAll(ApplicationUtil.readTextReferences((NodeList) xPath.compile(".//text/reference[not(@nullFlavor)]").
						evaluate(immunizationActivityElement, XPathConstants.NODESET)));
				
				immunizationActivity.setTime(ApplicationUtil.readEffectivetime((Element) xPath.compile("./effectiveTime[not(@nullFlavor)]").
						evaluate(immunizationActivityElement, XPathConstants.NODE),xPath));
				
				immunizationActivity.setRouteCode(ApplicationUtil.readCode((Element) xPath.compile("./routeCode[not(@nullFlavor)]").
						evaluate(immunizationActivityElement, XPathConstants.NODE)));
				
				immunizationActivity.setDoseQuantity(ApplicationUtil.readQuantity((Element) xPath.compile("./doseQuantity[not(@nullFlavor)]").
						evaluate(immunizationActivityElement, XPathConstants.NODE)));
				
				immunizationActivity.setApproachSiteCode(ApplicationUtil.readCode((Element) xPath.compile("./approachSiteCode[not(@nullFlavor)]").
							evaluate(immunizationActivityElement, XPathConstants.NODE)));
				
				immunizationActivity.setAdminUnitCode(ApplicationUtil.readCode((Element) xPath.compile("./administrationUnitCode[not(@nullFlavor)]").
							evaluate(immunizationActivityElement, XPathConstants.NODE)));
				
				immunizationActivity.setConsumable(MedicationProcessor.readMedicationInformation((Element) xPath.compile("./consumable/manufacturedProduct[not(@nullFlavor)]").
						   evaluate(immunizationActivityElement, XPathConstants.NODE), xPath,idList));
				
				
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
