package org.sitenv.ccdaparsing.processing;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.sitenv.ccdaparsing.model.CCDAAssignedEntity;
import org.sitenv.ccdaparsing.model.CCDAID;
import org.sitenv.ccdaparsing.model.CCDAOrganization;
import org.sitenv.ccdaparsing.model.CCDAProcActProc;
import org.sitenv.ccdaparsing.model.CCDAProcedure;
import org.sitenv.ccdaparsing.model.CCDAServiceDeliveryLoc;
import org.sitenv.ccdaparsing.model.CCDAUDI;
import org.sitenv.ccdaparsing.util.ApplicationConstants;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ProcedureProcessor {
	
	public static CCDAProcedure retrievePrcedureDetails(XPath xPath , Document doc, List<CCDAID> idList) throws XPathExpressionException,TransformerException
	{
		CCDAProcedure procedures = null;
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.PROCEDURE_EXPRESSION).evaluate(doc, XPathConstants.NODE);
		if(sectionElement !=null)
		{
			procedures = new CCDAProcedure();
			if(ApplicationUtil.checkForNullFlavourNI(sectionElement))
			{
				procedures.setSectionNullFlavourWithNI(true);
				return procedures;
			}
			procedures.setSectionTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.
							compile("./templateId[not(@nullFlavor)]").evaluate(sectionElement, XPathConstants.NODESET)));
			procedures.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
			procedures.setProcActsProcs(readProcedures((NodeList) xPath.compile("./entry/procedure[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODESET), xPath,idList));
			
			sectionElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			procedures.setLineNumber(sectionElement.getUserData("lineNumber") + " - " + sectionElement.getUserData("endLineNumber") );
			procedures.setXmlString(ApplicationUtil.nodeToString((Node)sectionElement));
			
			Element textElement = (Element) xPath.compile("./text[not(@nullFlavor)]").evaluate(sectionElement, XPathConstants.NODE);
			
			if(textElement!=null)
			{
				procedures.getReferenceLinks().addAll((ApplicationUtil.readSectionTextReferences((NodeList) xPath.compile(".//*[not(@nullFlavor) and @ID]").
					evaluate(textElement, XPathConstants.NODESET))));
			}

		}
		return procedures;
	}
	
	public static ArrayList<CCDAProcActProc> readProcedures(NodeList proceduresNodeList , XPath xPath , List<CCDAID> idList) throws XPathExpressionException,TransformerException
	{
		ArrayList<CCDAProcActProc> proceduresList = null;
		if(!ApplicationUtil.isNodeListEmpty(proceduresNodeList))
		{
			proceduresList = new ArrayList<>();
		}
		CCDAProcActProc procedure;
		for (int i = 0; i < proceduresNodeList.getLength(); i++) {
			
			procedure = new CCDAProcActProc();
			Element procedureElement = (Element) proceduresNodeList.item(i);
			
			procedureElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			procedure.setLineNumber(procedureElement.getUserData("lineNumber") + " - " + procedureElement.getUserData("endLineNumber") );
			procedure.setXmlString(ApplicationUtil.nodeToString((Node)procedureElement));
			
			if(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(procedureElement, XPathConstants.NODE),"procedure")!= null)
			{
				idList.add(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(procedureElement, XPathConstants.NODE),"procedure"));
			}

			
			procedure.setSectionTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
										evaluate(procedureElement, XPathConstants.NODESET)));
			
			procedure.getReferenceTexts().addAll(ApplicationUtil.readTextReferences((NodeList) xPath.compile(".//originalText/reference[not(@nullFlavor)]").
					evaluate(procedureElement, XPathConstants.NODESET)));

			procedure.getReferenceTexts().addAll(ApplicationUtil.readTextReferences((NodeList) xPath.compile(".//text/reference[not(@nullFlavor)]").
					evaluate(procedureElement, XPathConstants.NODESET)));
			
			procedure.setProcCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(procedureElement, XPathConstants.NODE)));
			
			procedure.setProcStatus(ApplicationUtil.readCode((Element) xPath.compile("./statusCode[not(@nullFlavor)]").
					evaluate(procedureElement, XPathConstants.NODE)));
			
			procedure.setTargetSiteCode(ApplicationUtil.readCode((Element) xPath.compile("./targetSiteCode[not(@nullFlavor)]").
					evaluate(procedureElement, XPathConstants.NODE)));
			
			NodeList performerNodeList = (NodeList) xPath.compile("./performer/assignedEntity[not(@nullFlavor)]").
						evaluate(procedureElement, XPathConstants.NODESET);
			procedure.setPerformer(readPerformerList(performerNodeList, xPath,idList));
			
			NodeList deviceNodeList = (NodeList) xPath.compile(ApplicationConstants.PROCEDURE_UDI_EXPRESSION).
						evaluate(procedureElement, XPathConstants.NODESET);
			procedure.setPatientUDI(readUDI(deviceNodeList, xPath,idList));
			
			NodeList serviceDeliveryNodeList = (NodeList) xPath.compile(ApplicationConstants.PROCEDURE_SDL_EXPRESSION).
						evaluate(procedureElement, XPathConstants.NODESET);
			procedure.setSdLocs(readServiceDeliveryLocators(serviceDeliveryNodeList, xPath,idList));
			
			
			proceduresList.add(procedure);
		}
		return proceduresList;
	}
	
	public static ArrayList<CCDAAssignedEntity> readPerformerList(NodeList performerEntityNodeList , XPath xPath, List<CCDAID> idList) throws XPathExpressionException,TransformerException
	{ 
		ArrayList<CCDAAssignedEntity> assignedEntityList = null;
		if(!ApplicationUtil.isNodeListEmpty(performerEntityNodeList))
		{
			assignedEntityList = new ArrayList<>();
		}
		CCDAAssignedEntity assignedEntity;
		
		for (int i = 0; i < performerEntityNodeList.getLength(); i++) {
			
			Element performerEntityElement = (Element) performerEntityNodeList.item(i);
			assignedEntity = new CCDAAssignedEntity();
			
			if(performerEntityElement != null)
			{
				assignedEntity.setAddresses(ApplicationUtil.readAddressList((NodeList) xPath.compile("./addr[not(@nullFlavor)]").
													evaluate(performerEntityElement, XPathConstants.NODESET), xPath));
				assignedEntity.setTelecom(ApplicationUtil.readDataElementList((NodeList) xPath.compile("./telecom[not(@nullFlavor)]").
													evaluate(performerEntityElement, XPathConstants.NODESET)));
				
				/*if(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
						evaluate(performerEntityElement, XPathConstants.NODE),"procedurePerformer")!= null)
				{
					idList.add(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
						evaluate(performerEntityElement, XPathConstants.NODE),"procedurePerformer"));
				}*/
					
				Element represntOrgElement = (Element) xPath.compile("./representedOrganization[not(@nullFlavor)]").
													evaluate(performerEntityElement, XPathConstants.NODE);
				if(represntOrgElement != null)
				{
					CCDAOrganization representedOrg = new  CCDAOrganization();
						
					representedOrg.setAddress(ApplicationUtil.readAddressList((NodeList) xPath.compile("./addr[not(@nullFlavor)]").
								evaluate(represntOrgElement, XPathConstants.NODESET), xPath));
						
					representedOrg.setTelecom(ApplicationUtil.readDataElementList((NodeList) xPath.compile("./telecom[not(@nullFlavor)]").
								evaluate(represntOrgElement, XPathConstants.NODESET)));
						
					representedOrg.setNames( ApplicationUtil.readTextContentList((NodeList) xPath.compile("./name[not(@nullFlavor)]").
								evaluate(represntOrgElement, XPathConstants.NODESET)));
						
					assignedEntity.setOrganization(representedOrg);
				}
			}
			
			assignedEntityList.add(assignedEntity);
		}
		
		return assignedEntityList;
		
	}
	
	public static ArrayList<CCDAUDI> readUDI(NodeList deviceNodeList, XPath xPath, List<CCDAID> idList) throws XPathExpressionException,TransformerException
	{
		ArrayList<CCDAUDI> deviceList =  null;
		if(!ApplicationUtil.isNodeListEmpty(deviceNodeList))
		{
			deviceList = new ArrayList<>();
		}
		CCDAUDI device;
		for (int i = 0; i < deviceNodeList.getLength(); i++) {
			
			device = new CCDAUDI();
			
			Element deviceElement = (Element) deviceNodeList.item(i);
			device.setTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
											evaluate(deviceElement, XPathConstants.NODESET)));
			
			if(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(deviceElement, XPathConstants.NODE),"procedureUID")!= null)
			{
				idList.add(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(deviceElement, XPathConstants.NODE),"procedureUID"));
			}
			device.setUDIValue(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./id[not(@nullFlavor)]").
											evaluate(deviceElement, XPathConstants.NODESET)));
			device.setDeviceCode(ApplicationUtil.readCode((Element) xPath.compile("./playingDevice/code[not(@nullFlavor)]").
					evaluate(deviceElement, XPathConstants.NODE)));
			device.setScopingEntityId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./scopingEntity/id[not(@nullFlavor)]").
					evaluate(deviceElement, XPathConstants.NODESET)));
			
			
			deviceList.add(device);
			
		}
		
		return deviceList;
		
	}
	
	public static ArrayList<CCDAServiceDeliveryLoc> readServiceDeliveryLocators(NodeList serviceDeliveryLocNodeList, XPath xPath, List<CCDAID> idList) throws XPathExpressionException,TransformerException
	{
		ArrayList<CCDAServiceDeliveryLoc> serviceDeliveryLocsList = null;
		if(!ApplicationUtil.isNodeListEmpty(serviceDeliveryLocNodeList))
		{
			serviceDeliveryLocsList = new ArrayList<>();
		}
		CCDAServiceDeliveryLoc serviceDeliveryLoc;
		for (int i = 0; i < serviceDeliveryLocNodeList.getLength(); i++) {
			
			serviceDeliveryLoc = new CCDAServiceDeliveryLoc();
			
			Element serviceDeliveryLocElement = (Element) serviceDeliveryLocNodeList.item(i);
			serviceDeliveryLoc.setTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
											evaluate(serviceDeliveryLocElement, XPathConstants.NODESET)));
			
			serviceDeliveryLoc.setLocationCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(serviceDeliveryLocElement, XPathConstants.NODE)));
			
			serviceDeliveryLoc.setName(ApplicationUtil.readCode((Element) xPath.compile("./playingEntity/name[not(@nullFlavor)]").
					evaluate(serviceDeliveryLocElement, XPathConstants.NODE)));
			
			serviceDeliveryLoc.setTelecom(ApplicationUtil.readDataElementList((NodeList) xPath.compile("./telecom[not(@nullFlavor)]").
					evaluate(serviceDeliveryLocElement, XPathConstants.NODESET)));
			serviceDeliveryLoc.setAddress(ApplicationUtil.readAddressList((NodeList) xPath.compile("./addr[not(@nullFlavor)]").
					evaluate(serviceDeliveryLocElement, XPathConstants.NODESET), xPath));
			
			serviceDeliveryLocsList.add(serviceDeliveryLoc);
		}
		
		return serviceDeliveryLocsList;
		
	}

}
