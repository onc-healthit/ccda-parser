package org.sitenv.ccdaparsing.tests;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sitenv.ccdaparsing.model.CCDACode;
import org.sitenv.ccdaparsing.model.CCDADataElement;
import org.sitenv.ccdaparsing.model.CCDAEffTime;
import org.sitenv.ccdaparsing.model.CCDAID;
import org.sitenv.ccdaparsing.model.CCDAII;
import org.sitenv.ccdaparsing.model.CCDAPQ;
import org.sitenv.ccdaparsing.model.CCDAVitalObs;
import org.sitenv.ccdaparsing.model.CCDAVitalOrg;
import org.sitenv.ccdaparsing.model.CCDAVitalSigns;
import org.sitenv.ccdaparsing.processing.VitalSignProcessor;
import org.w3c.dom.Document;

public class VitalSignTest {
	
	private static String CCDA_DOC = "src/test/resources/170.315_b1_toc_amb_ccd_r21_sample1_v1.xml";
	private static CCDAVitalSigns vitalSigns;
	private ArrayList<CCDAII>    templateIds;
	private CCDACode  sectionCode;
	private static ArrayList<CCDAVitalOrg> vitalsOrg;
	private static List<CCDAID> idList = new ArrayList<CCDAID>();
	
	
	@BeforeClass
	public static void setUp() throws Exception {
		// removed fields to ensure no side effects with DocumentRoot
		DocumentBuilderFactory factory = 
				DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(CCDA_DOC));
		XPath xPath =  XPathFactory.newInstance().newXPath();
		vitalSigns = VitalSignProcessor.retrieveVitalSigns(xPath, doc,idList);
		
		vitalsOrg = new ArrayList<CCDAVitalOrg>();
		
		CCDAVitalOrg  vitalsOrgOne = new CCDAVitalOrg();
		
		CCDAEffTime effectiveTime = new CCDAEffTime();
		effectiveTime.setLow(new CCDADataElement("20150622"));
		effectiveTime.setHigh(new CCDADataElement("20150622"));
		effectiveTime.setLowPresent(true);
		effectiveTime.setHighPresent(true);
		vitalsOrgOne.setEffTime(effectiveTime);
		
		CCDACode orgCode = new CCDACode();
		orgCode.setCode("46680005");
		orgCode.setCodeSystem("2.16.840.1.113883.6.96");
		orgCode.setCodeSystemName("SNOMED CT");
		orgCode.setDisplayName("Vital signs");
		
		vitalsOrgOne.setOrgCode(orgCode);
		CCDACode statusCode = new CCDACode();
		statusCode.setCode("completed");
		vitalsOrgOne.setStatusCode(statusCode);
		
		ArrayList<CCDAII> vitalsOrgTemplateIds = new ArrayList<CCDAII>();
		CCDAII templateIdOne = new CCDAII();
		templateIdOne.setRootValue("2.16.840.1.113883.10.20.22.4.26");
		templateIdOne.setExtValue("2015-08-01");
		vitalsOrgTemplateIds.add(templateIdOne);
		CCDAII templateIdTwo = new CCDAII();
		templateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.4.26");
		vitalsOrgTemplateIds.add(templateIdTwo);
		
		vitalsOrgOne.setTemplateIds(vitalsOrgTemplateIds);
		
		CCDACode translationCode = new CCDACode();
		translationCode.setCode("74728-7");
		translationCode.setCodeSystem("2.16.840.1.113883.6.1");
		translationCode.setCodeSystemName("LOINC");
		translationCode.setDisplayName("Vital signs");
		
		vitalsOrgOne.setTranslationCode(translationCode);
		
		ArrayList<CCDAVitalObs> vitalObsList = new ArrayList<CCDAVitalObs>();
		CCDAVitalObs vitalObsOne = new CCDAVitalObs();
		
		CCDACode interpretationCode = new CCDACode();
		interpretationCode.setCode("N");
		interpretationCode.setCodeSystem("2.16.840.1.113883.5.83");
		
		vitalObsOne.setInterpretationCode(interpretationCode);
		
		CCDAEffTime measurementTime = new CCDAEffTime("20150622");
		vitalObsOne.setMeasurementTime(measurementTime);
		vitalObsOne.setStatusCode(statusCode);
		
		ArrayList<CCDAII> vitalsObsTemplateIds = new ArrayList<CCDAII>();
		CCDAII vitalsObsTemplateIdOne = new CCDAII();
		vitalsObsTemplateIdOne.setRootValue("2.16.840.1.113883.10.20.22.4.27");
		vitalsObsTemplateIdOne.setExtValue("2014-06-09");
		vitalsObsTemplateIds.add(vitalsObsTemplateIdOne);
		CCDAII vitalsObsTemplateIdTwo = new CCDAII();
		vitalsObsTemplateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.4.27");
		vitalsObsTemplateIds.add(vitalsObsTemplateIdTwo);
		
		vitalObsOne.setTemplateIds(vitalsObsTemplateIds);
		
		CCDACode vsCode = new CCDACode();
		vsCode.setCode("8302-2");
		vsCode.setCodeSystem("2.16.840.1.113883.6.1");
		vsCode.setCodeSystemName("LOINC");
		vsCode.setDisplayName("Height");
		vitalObsOne.setVsCode(vsCode);
		
		CCDAPQ vsResult = new CCDAPQ();
		vsResult.setUnits("cm");
		vsResult.setValue("177");
		vitalObsOne.setVsResult(vsResult);
		
		vitalObsList.add(vitalObsOne);
		
		vitalsOrgOne.setVitalObs(vitalObsList);
		vitalsOrg.add(vitalsOrgOne);
	}
	
	private void setVitalsignSectionCode()
	 {
		sectionCode = new CCDACode();
		sectionCode.setCode("8716-3");
		sectionCode.setCodeSystem("2.16.840.1.113883.6.1");
		sectionCode.setCodeSystemName("LOINC");
		sectionCode.setDisplayName("VITAL SIGNS");
	 }
	
	private void setVitalsignTemplateIds()
	 {
		templateIds = new ArrayList<CCDAII>();
		CCDAII templateIdOne = new CCDAII();
		templateIdOne.setRootValue("2.16.840.1.113883.10.20.22.2.4.1");
		templateIdOne.setExtValue("2015-08-01");
		templateIds.add(templateIdOne);
		CCDAII templateIdTwo = new CCDAII();
		templateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.2.4.1");
		templateIds.add(templateIdTwo);
	 }
	
	@Test
	public void testVitalSigns() throws Exception{
		Assert.assertNotNull(vitalSigns);
	}

	@Test
	public void testVitalsignsSectionCode(){
		setVitalsignSectionCode();
		Assert.assertEquals("VitalSign section code test case failed",sectionCode,vitalSigns.getSectionCode());
	}
	
	@Test
	public void testVitalsignsTemplateIds(){
		setVitalsignTemplateIds();
		Assert.assertEquals("VitalSign Template Ids test case failed",templateIds,vitalSigns.getTemplateIds());
	}
	
	@Test
	public void testVitalsignsVitalOrg(){
		Assert.assertNotNull(vitalsOrg);
	}
	
	@Test
	public void testVitalsignsVitalOrgTemplateId(){
		setVitalsignTemplateIds();
		Assert.assertEquals("VitalOrg Template Ids test case failed",vitalSigns.getVitalsOrg().get(0).getTemplateIds(),vitalsOrg.get(0).getTemplateIds());
	}
	
	@Test
	public void testVitalsignsVitalOrgStatusCode(){
		setVitalsignTemplateIds();
		Assert.assertEquals("VitalOrg Status code test case failed",vitalSigns.getVitalsOrg().get(0).getStatusCode(),vitalsOrg.get(0).getStatusCode());
	}
	
	@Test
	public void testVitalsignsVitalOrgcode(){
		setVitalsignTemplateIds();
		Assert.assertEquals("VitalOrg Org Code test case failed",vitalSigns.getVitalsOrg().get(0).getOrgCode(),vitalsOrg.get(0).getOrgCode());
	}
	
	@Test
	public void testVitalsignsVitalOrgTranslationCode(){
		setVitalsignTemplateIds();
		Assert.assertEquals("VitalOrg Translation code test case failed",vitalSigns.getVitalsOrg().get(0).getTranslationCode(),vitalsOrg.get(0).getTranslationCode());
	}
	
	@Test
	public void testVitalsignsVitalOrgEffectiveTime(){
		setVitalsignTemplateIds();
		Assert.assertEquals("VitalOrg  effective time test case failed",vitalSigns.getVitalsOrg().get(0).getEffTime(),vitalsOrg.get(0).getEffTime());
	}
	
	@Test
	public void testVitalsignsVitalObsTemplateIDs(){
		setVitalsignTemplateIds();
		Assert.assertEquals("VitalObs  Template Id test case failed",vitalSigns.getVitalsOrg().get(0).getVitalObs().get(0).getTemplateIds(),
																	vitalsOrg.get(0).getVitalObs().get(0).getTemplateIds());
	}
	
	@Test
	public void testVitalsignsVitalObsCode(){
		setVitalsignTemplateIds();
		Assert.assertEquals("VitalObs Code test case failed",vitalSigns.getVitalsOrg().get(0).getVitalObs().get(0).getVsCode(),
																	vitalsOrg.get(0).getVitalObs().get(0).getVsCode());
	}
	
	@Test
	public void testVitalsignsVitalObsStatusCode(){
		setVitalsignTemplateIds();
		Assert.assertEquals("VitalObs Status Code test case failed",vitalSigns.getVitalsOrg().get(0).getVitalObs().get(0).getStatusCode(),
																	vitalsOrg.get(0).getVitalObs().get(0).getStatusCode());
	}
	
	@Test
	public void testVitalsignsVitalObsMeasurmentTime(){
		setVitalsignTemplateIds();
		Assert.assertEquals("VitalObs Measurment Time test case failed",vitalSigns.getVitalsOrg().get(0).getVitalObs().get(0).getMeasurementTime(),
																	vitalsOrg.get(0).getVitalObs().get(0).getMeasurementTime());
	}
	
	@Test
	public void testVitalsignsVitalObsResult(){
		setVitalsignTemplateIds();
		Assert.assertEquals("VitalObs Result test case failed",vitalSigns.getVitalsOrg().get(0).getVitalObs().get(0).getVsResult(),
																	vitalsOrg.get(0).getVitalObs().get(0).getVsResult());
	}
	
	@Test
	public void testVitalsignsVitalObsInterpretationCode(){
		setVitalsignTemplateIds();
		Assert.assertEquals("VitalObs Interpretation Code test case failed",vitalSigns.getVitalsOrg().get(0).getVitalObs().get(0).getInterpretationCode(),
																	vitalsOrg.get(0).getVitalObs().get(0).getInterpretationCode());
	}


}
