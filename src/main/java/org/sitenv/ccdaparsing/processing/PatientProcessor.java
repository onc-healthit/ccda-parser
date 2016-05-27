package org.sitenv.ccdaparsing.processing;

import java.util.ArrayList;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.sitenv.ccdaparsing.model.CCDAPL;
import org.sitenv.ccdaparsing.model.CCDAPatient;
import org.sitenv.ccdaparsing.util.ApplicationConstants;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class PatientProcessor {
	
	
	public static CCDAPatient retrievePatientDetails(XPath xPath , Document doc) throws XPathExpressionException,TransformerException
	{
		CCDAPatient patient = null;
		NodeList nodeList = (NodeList) xPath.compile(ApplicationConstants.PATIENT_EXPRESSION).evaluate(doc, XPathConstants.NODESET);
		
		for (int i = 0; i < nodeList.getLength(); i++) {
	    	
			Element patientRoleElement = (Element) nodeList.item(i);
		    
	        	
	        	patient= new CCDAPatient();
	            patient.setAddresses(ApplicationUtil.readAddressList((NodeList) xPath.compile("./addr[not(@nullFlavor)]").
    					evaluate(patientRoleElement, XPathConstants.NODESET), xPath));
	            
	            
	            patient.setBirthPlace(ApplicationUtil.readAddress((Element) xPath.compile("./patient/birthplace/place/addr[not(@nullFlavor)]").
	    				evaluate(patientRoleElement, XPathConstants.NODE), xPath));
	            
	            //Getting name of the patient
	            readName((Element) xPath.compile("./patient/name[not(@nullFlavor)]").
	    				evaluate(patientRoleElement, XPathConstants.NODE), patient , xPath);
	           
	            //Get Gender of the patient
	            patient.setAdministrativeGenderCode(ApplicationUtil.readCode((Element) xPath.compile("./patient/administrativeGenderCode[not(@nullFlavor)]").
	    				evaluate(patientRoleElement, XPathConstants.NODE)));
	            
	            //Get Birth time of the patient
	            patient.setDob(ApplicationUtil.readDataElement((Element) xPath.compile("./patient/birthTime[not(@nullFlavor)]").
	    				evaluate(patientRoleElement, XPathConstants.NODE)));
	            
	            //Get Marital status of the patient 
	            patient.setMaritalStatusCode(ApplicationUtil.readCode((Element) xPath.compile("./patient/maritalStatusCode[not(@nullFlavor)]").
	    				evaluate(patientRoleElement, XPathConstants.NODE)));
	            
	            //Get religious affiliation Code
	            patient.setReligiousAffiliationCode(ApplicationUtil.readCode((Element) xPath.compile("./patient/religiousAffiliationCode[not(@nullFlavor)]").
	    				evaluate(patientRoleElement, XPathConstants.NODE)));
	            
	            
	            readRaceCodes((NodeList) xPath.compile("./patient/raceCode[not(@nullFlavor)]").
	    				evaluate(patientRoleElement, XPathConstants.NODESET), patient);
	            
	            //Get ethnic Group code
	            patient.setEthnicity(ApplicationUtil.readCode((Element) xPath.compile("./patient/ethnicGroupCode[not(@nullFlavor)]").
	    				evaluate(patientRoleElement, XPathConstants.NODE)));
	            
	            patient.setLanguageCommunication(readPreferredLanguage((NodeList) xPath.compile("./patient/languageCommunication[not(@nullFlavor)]").
	    				evaluate(patientRoleElement, XPathConstants.NODESET), xPath));	
	            
	            patient.setTelecom(ApplicationUtil.readDataElementList((NodeList) xPath.compile("./telecom[not(@nullFlavor)]").
	            					evaluate(patientRoleElement, XPathConstants.NODESET)));
	   }
	    
	   return patient;
	}
	
	public static void readRaceCodes(NodeList raceCodeList, CCDAPatient patient)throws TransformerException
	{
		Element raceCodeElement= null;
		for (int i = 0; i < raceCodeList.getLength(); i++) {
			
			raceCodeElement = (Element) raceCodeList.item(i);
			if(raceCodeElement.getTagName().equals("raceCode"))
			{
				patient.setRaceCodes(ApplicationUtil.readCode(raceCodeElement));
			}else
			{
				patient.getSdtcRaceCodes().add(ApplicationUtil.readCode(raceCodeElement));
			}
		}
		
	}
	
	
	public static void readName(Element nameElement,CCDAPatient patient,XPath xPath) throws XPathExpressionException
	{
		if(nameElement != null)
		{
			NodeList giveNameNodeList = (NodeList) xPath.compile("./given[not(@nullFlavor)]").
					evaluate(nameElement, XPathConstants.NODESET);
			for (int i = 0; i < giveNameNodeList.getLength(); i++) {
				Element givenNameElement = (Element) giveNameNodeList.item(i);
				if(!ApplicationUtil.isEmpty(givenNameElement.getAttribute("qualifier")))
				{
					patient.setPreviousName(ApplicationUtil.readTextContext(givenNameElement));
				}else if (i == 0) {
					patient.setFirstName(ApplicationUtil.readTextContext(givenNameElement));
				}else {
					patient.setMiddleName(ApplicationUtil.readTextContext(givenNameElement));
				}
			}
			
			patient.setLastName(ApplicationUtil.readTextContext((Element) xPath.compile("./family[not(@nullFlavor)]").
					evaluate(nameElement, XPathConstants.NODE)));
			patient.setSuffix(ApplicationUtil.readTextContext((Element) xPath.compile("./suffix[not(@nullFlavor)]").
					evaluate(nameElement, XPathConstants.NODE)));
		}
	}
	
	
	public static ArrayList<CCDAPL> readPreferredLanguage(NodeList languageCommElementList , XPath xPath) throws XPathExpressionException,TransformerException
	{
		ArrayList<CCDAPL> preferredLanguageList = new ArrayList<>();
		CCDAPL preferredLanguage = null;
		for (int i = 0; i < languageCommElementList.getLength(); i++) {
			Element languageCommElement = (Element) languageCommElementList.item(i);
			if(languageCommElement != null)
			{
				preferredLanguage = new CCDAPL();
				preferredLanguage.setLanguageCode(ApplicationUtil.readCode((Element) xPath.compile("./languageCode[not(@nullFlavor)]").
    				evaluate(languageCommElement, XPathConstants.NODE)));
				preferredLanguage.setModeCode(ApplicationUtil.readCode((Element) xPath.compile("./modeCode[not(@nullFlavor)]").
    				evaluate(languageCommElement, XPathConstants.NODE)));
				preferredLanguage.setPreferenceInd(ApplicationUtil.readDataElement((Element) xPath.compile("./preferenceInd[not(@nullFlavor)]").
    				evaluate(languageCommElement, XPathConstants.NODE)));
				preferredLanguageList.add(preferredLanguage);
			}
		}
		return preferredLanguageList;
	}
}
