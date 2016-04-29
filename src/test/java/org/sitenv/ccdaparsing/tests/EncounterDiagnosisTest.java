package org.sitenv.ccdaparsing.tests;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sitenv.ccdaparsing.model.CCDAAddress;
import org.sitenv.ccdaparsing.model.CCDACode;
import org.sitenv.ccdaparsing.model.CCDADataElement;
import org.sitenv.ccdaparsing.model.CCDAEffTime;
import org.sitenv.ccdaparsing.model.CCDAEncounter;
import org.sitenv.ccdaparsing.model.CCDAEncounterActivity;
import org.sitenv.ccdaparsing.model.CCDAII;
import org.sitenv.ccdaparsing.model.CCDAProblemObs;
import org.sitenv.ccdaparsing.model.CCDAServiceDeliveryLoc;
import org.sitenv.ccdaparsing.processing.EncounterDiagnosesProcessor;
import org.w3c.dom.Document;


public class EncounterDiagnosisTest {
	
	private static String CCDA_DOC = "src/test/resources/170.315_b1_toc_amb_ccd_r21_sample1_v1.xml";
	private static CCDAEncounter encounter;
	private ArrayList<CCDAII>    templateIds;
	private CCDACode  sectionCode;
	private static ArrayList<CCDAEncounterActivity> encActivities;
	
	
	@BeforeClass
	public static void setUp() throws Exception {
		// removed fields to ensure no side effects with DocumentRoot
		DocumentBuilderFactory factory = 
				DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(CCDA_DOC));
		XPath xPath =  XPathFactory.newInstance().newXPath();
		encounter = EncounterDiagnosesProcessor.retrieveEncounterDetails(xPath, doc);
		
		encActivities = new ArrayList<CCDAEncounterActivity>();
		CCDAEncounterActivity encActivityOne = new CCDAEncounterActivity();
		
		//Creating Encounter Template Ids list
		ArrayList<CCDAII> templateIds = new ArrayList<CCDAII>();
		CCDAII templateIdOne = new CCDAII();
		templateIdOne.setRootValue("2.16.840.1.113883.10.20.22.4.49");
		templateIdOne.setExtValue("2015-08-01");
		templateIds.add(templateIdOne);
		CCDAII templateIdTwo = new CCDAII();
		templateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.4.49");
		templateIds.add(templateIdTwo);
		
		encActivityOne.setTemplateId(templateIds);
		
		//Creating encounter Type code
		CCDACode encounterTypeCode = new CCDACode();
		encounterTypeCode.setCode("99213");
		encounterTypeCode.setCodeSystem("2.16.840.1.113883.6.12");
		encounterTypeCode.setCodeSystemName("CPT-4");
		encounterTypeCode.setDisplayName("Office outpatient visit 15 minutes");
		
		encActivityOne.setEncounterTypeCode(encounterTypeCode);
		
		// Creating Effective time Object
		CCDADataElement effectiveTime = new CCDADataElement();
		effectiveTime.setValue("20150622");
		encActivityOne.setEffectiveTime(effectiveTime);
		
		
		// Creating Service Delivery Location objects
		ArrayList<CCDAServiceDeliveryLoc> sdLocs = new ArrayList<>();
		CCDAServiceDeliveryLoc sdLocOne = new CCDAServiceDeliveryLoc();
		ArrayList<CCDAII> locTemplateIds = new ArrayList<CCDAII>();
		CCDAII locTemplateIdOne = new CCDAII();
		locTemplateIdOne.setRootValue("2.16.840.1.113883.10.20.22.4.32");
		locTemplateIds.add(locTemplateIdOne);
		
		sdLocOne.setTemplateId(locTemplateIds);
		
		CCDACode sdlLocationCode = new CCDACode();
		sdlLocationCode.setCode("1160-1");
		sdlLocationCode.setCodeSystem("2.16.840.1.113883.6.259");
		sdlLocationCode.setCodeSystemName("HL7 HealthcareServiceLocation");
		sdlLocationCode.setDisplayName("Urgent Care Center");
		
		sdLocOne.setLocationCode(sdlLocationCode);
		
		CCDADataElement sdlName = new CCDADataElement();
		sdlName.setValue("Neighborhood Physicians Practice");
		
		sdLocOne.setName(sdlName);
		
		ArrayList<CCDADataElement> sdlTelecomList = new ArrayList<>();
		CCDADataElement sdlTelecomOne = new CCDADataElement();
		sdlTelecomOne.setValue("tel:+1(555)-555-1002");
		sdlTelecomOne.setUse("WP");
		sdlTelecomList.add(sdlTelecomOne);
		
		sdLocOne.setTelecom(sdlTelecomList);
		
		ArrayList<CCDAAddress> sdlAddressList = new ArrayList<>();
		CCDAAddress sdlAddressOne = new CCDAAddress();
		sdlAddressOne.setAddressLine1(new CCDADataElement("2472 Rocky place"));
		sdlAddressOne.setCity(new CCDADataElement("Beaverton"));
		sdlAddressOne.setState(new CCDADataElement("OR"));
		sdlAddressOne.setCountry(new CCDADataElement("US"));
		sdlAddressOne.setPostalCode(new CCDADataElement("97006"));
		sdlAddressList.add(sdlAddressOne);
		 
		sdLocOne.setAddress(sdlAddressList);
		sdLocs.add(sdLocOne);
		
		encActivityOne.setSdLocs(sdLocs);
		
		//Creating Indication object
		
		ArrayList<CCDAProblemObs> probObsList = new ArrayList<>();
		CCDAProblemObs probObsOne = new CCDAProblemObs();
		
		ArrayList<CCDAII> probObsTemplateIdList = new ArrayList<CCDAII>();
		CCDAII probObsTemplateIdOne = new CCDAII();
		probObsTemplateIdOne.setRootValue("2.16.840.1.113883.10.20.22.4.19");
		probObsTemplateIdOne.setExtValue("2014-06-09");
		probObsTemplateIdList.add(probObsTemplateIdOne);
		CCDAII probObsTemplateIdTwo = new CCDAII();
		probObsTemplateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.4.19");
		probObsTemplateIdList.add(probObsTemplateIdTwo);
		
		probObsOne.setTemplateId(probObsTemplateIdList);
		
		CCDACode problemType = new CCDACode();
		problemType.setCode("404684003");
		problemType.setCodeSystem("2.16.840.1.113883.6.96");
		problemType.setCodeSystemName("SNOMED-CT");
		problemType.setDisplayName("Finding");
		probObsOne.setProblemType(problemType);
		
		CCDAEffTime probObsEffectiveTime = new CCDAEffTime();
		probObsEffectiveTime.setLow(new CCDADataElement("20150622"));
		probObsEffectiveTime.setLowPresent(true);
		probObsEffectiveTime.setHighPresent(false);
		probObsOne.setEffTime(probObsEffectiveTime);
		
		CCDACode problemCode = new CCDACode();
		problemCode.setCode("386661006");
		problemCode.setCodeSystem("2.16.840.1.113883.6.96");
		problemCode.setXpath("CD");
		problemCode.setDisplayName("Fever");
		
		probObsOne.setProblemCode(problemCode);
		probObsList.add(probObsOne);

		encActivityOne.setIndications(probObsList);
		
		encActivities.add(encActivityOne);
	}
	
	private void setEncounterSectionCode()
	 {
		sectionCode = new CCDACode();
		sectionCode.setCode("46240-8");
		sectionCode.setCodeSystem("2.16.840.1.113883.6.1");
		sectionCode.setCodeSystemName("LOINC");
		sectionCode.setDisplayName("History of encounters");
	 }
	
	private void setEncounterTemplateIds()
	 {
		templateIds = new ArrayList<CCDAII>();
		CCDAII templateIdOne = new CCDAII();
		templateIdOne.setRootValue("2.16.840.1.113883.10.20.22.2.22.1");
		templateIdOne.setExtValue("2015-08-01");
		templateIds.add(templateIdOne);
		CCDAII templateIdTwo = new CCDAII();
		templateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.2.22.1");
		templateIds.add(templateIdTwo);
	 }
	
	@Test
	public void testEncounter() throws Exception{
		Assert.assertNotNull(encounter);
	}

	@Test
	public void testEncounterSectionCode(){
		setEncounterSectionCode();
		Assert.assertEquals("EncounterSectionCode test case failed",sectionCode,encounter.getSectionCode());
	}
	
	@Test
	public void testEncounterTemplateIds(){
		setEncounterTemplateIds();
		Assert.assertEquals("EncounterTemplateID test case failed",templateIds,encounter.getTemplateId());
	}
	
	@Test
	public void testEncounterActivities(){
		Assert.assertEquals("EncounterActivity test case failed",encActivities,encounter.getEncActivities());
	}
	
	@Test
	public void testEncounterActivitiyTemplateId(){
		Assert.assertEquals("EncounterActivity Template Id test case failed",encActivities.get(0).getTemplateId(),encounter.getEncActivities().get(0).getTemplateId());
	}
	
	@Test
	public void testEncounterActivitiyTypeCode(){
		Assert.assertEquals("EncounterActivity Type Code test case failed",encActivities.get(0).getEncounterTypeCode(),
												encounter.getEncActivities().get(0).getEncounterTypeCode());
	}
	
	@Test
	public void testEncounterActivitiyEffectiveTime(){
		Assert.assertEquals("EncounterActivity Effective time test case failed",encActivities.get(0).getEffectiveTime(),
											encounter.getEncActivities().get(0).getEffectiveTime());
	}
	
	@Test
	public void testEncounterActivitiyDiagnoses(){
		Assert.assertEquals("EncounterActivity Diagnoses test case failed",encActivities.get(0).getDiagnoses(),encounter.getEncActivities().get(0).getDiagnoses());
	}
	
	@Test
	public void testEncounterActivitiySdlocs(){
		Assert.assertEquals("EncounterActivity SdLocs test case failed",encActivities.get(0).getSdLocs(),encounter.getEncActivities().get(0).getSdLocs());
	}
	
	@Test
	public void testEncounterActivitiyIndications(){
		Assert.assertEquals("EncounterActivity Indications test case failed",encActivities.get(0).getIndications(),encounter.getEncActivities().get(0).getIndications());
	}
	
	@Test
	public void testEncounterActivitiySdlocsTemplateId(){
		Assert.assertEquals("EncounterActivity SdLocs Template Ids test case failed",encActivities.get(0).getSdLocs().get(0).getTemplateId(),
									encounter.getEncActivities().get(0).getSdLocs().get(0).getTemplateId());
	}
	
	@Test
	public void testEncounterActivitiySdlocsLocationCode(){
		Assert.assertEquals("EncounterActivity SdLocs location code Template Ids test case failed",encActivities.get(0).getSdLocs().get(0).getLocationCode(),
									encounter.getEncActivities().get(0).getSdLocs().get(0).getLocationCode());
	}
	
	@Test
	public void testEncounterActivitiySdlocsAddress(){
		Assert.assertEquals("EncounterActivity SdLocs Address test case failed",encActivities.get(0).getSdLocs().get(0).getAddress(),
									encounter.getEncActivities().get(0).getSdLocs().get(0).getAddress());
	}
	
	@Test
	public void testEncounterActivitiySdlocsTelecom(){
		Assert.assertEquals("EncounterActivity SdLocs Telecom test case failed",encActivities.get(0).getSdLocs().get(0).getTelecom(),
									encounter.getEncActivities().get(0).getSdLocs().get(0).getTelecom());
	}
	
	@Test
	public void testEncounterActivitiySdlocsName(){
		Assert.assertEquals("EncounterActivity SdLocs Name test case failed",encActivities.get(0).getSdLocs().get(0).getName(),
									encounter.getEncActivities().get(0).getSdLocs().get(0).getName());
	}
	
	@Test
	public void testEncounterActivitiyProbObsTemplateId(){
		Assert.assertEquals("EncounterActivity Prob obs Template Id test case failed",encActivities.get(0).getIndications().get(0).getTemplateId(),
									encounter.getEncActivities().get(0).getIndications().get(0).getTemplateId());
	}
	
	
	@Test
	public void testEncounterActivitiyProbObsProblemType(){
		Assert.assertEquals("EncounterActivity Prob obs problem type test case failed",encActivities.get(0).getIndications().get(0).getProblemType(),
									encounter.getEncActivities().get(0).getIndications().get(0).getProblemType());
	}
	
	
	@Test
	public void testEncounterActivitiyProbObsProblemCode(){
		Assert.assertEquals("EncounterActivity Prob obs problem code test case failed",encActivities.get(0).getIndications().get(0).getProblemCode(),
									encounter.getEncActivities().get(0).getIndications().get(0).getProblemCode());
	}
	

	@Test
	public void testEncounterActivitiyProbObsEffectivetTime(){
		Assert.assertEquals("EncounterActivity Prob obs Effective Time test case failed",encActivities.get(0).getIndications().get(0).getEffTime(),
									encounter.getEncActivities().get(0).getIndications().get(0).getEffTime());
	}
	
	@Test
	public void testEncounterActivitiyProbObsTransProbType(){
		Assert.assertEquals("EncounterActivity Prob obs Translation Problem type test case failed",encActivities.get(0).getIndications().get(0).getTranslationProblemType(),
									encounter.getEncActivities().get(0).getIndications().get(0).getTranslationProblemType());
	}
	
	
	
}
