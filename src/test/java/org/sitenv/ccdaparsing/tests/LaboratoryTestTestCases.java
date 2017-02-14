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
import org.sitenv.ccdaparsing.model.CCDALabResult;
import org.sitenv.ccdaparsing.model.CCDALabResultObs;
import org.sitenv.ccdaparsing.model.CCDALabResultOrg;
import org.sitenv.ccdaparsing.processing.LaboratoryTestProcessor;
import org.w3c.dom.Document;

public class LaboratoryTestTestCases {
	
	private static String CCDA_DOC = "src/test/resources/170.315_b1_toc_amb_ccd_r21_sample1_v1.xml";
	private static CCDALabResult labTests;
	private ArrayList<CCDAII>    templateIds;
	private CCDACode  sectionCode;
	private static ArrayList<CCDALabResultOrg> resultOrgList;
	private static List<CCDAID> idList = new ArrayList<CCDAID>();
	
	
	@BeforeClass
	public static void setUp() throws Exception {
		// removed fields to ensure no side effects with DocumentRoot
		DocumentBuilderFactory factory = 
				DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(CCDA_DOC));
		XPath xPath =  XPathFactory.newInstance().newXPath();
		labTests = LaboratoryTestProcessor.retrieveLabTests(xPath, doc,idList);
		
		resultOrgList = new ArrayList<>();
		CCDALabResultOrg resultOrg = new CCDALabResultOrg();
		
		ArrayList<CCDAII> resultOrgTemplateIds = new ArrayList<CCDAII>();
		CCDAII templateIdOne = new CCDAII();
		templateIdOne.setRootValue("2.16.840.1.113883.10.20.22.4.1");
		templateIdOne.setExtValue("2015-08-01");
		resultOrgTemplateIds.add(templateIdOne);
		CCDAII templateIdTwo = new CCDAII();
		templateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.4.1");
		resultOrgTemplateIds.add(templateIdTwo);
		
		resultOrg.setTemplateIds(resultOrgTemplateIds);
		
		CCDACode orgCode = new CCDACode();
		orgCode.setCode("27171005");
		orgCode.setCodeSystem("2.16.840.1.113883.6.96");
		orgCode.setCodeSystemName("SNOMED CT");
		orgCode.setDisplayName("Urinalysis (procedure)");
		
		resultOrg.setOrgCode(orgCode);
		
		CCDACode statusCode = new CCDACode();
		statusCode.setCode("active");
		
		resultOrg.setStatusCode(statusCode);
		
		CCDAEffTime effectiveTime = new CCDAEffTime();
		effectiveTime.setLow(new CCDADataElement("20150622"));
		effectiveTime.setHigh(new CCDADataElement("20150622"));
		effectiveTime.setHighPresent(true);
		effectiveTime.setLowPresent(true);
		resultOrg.setEffTime(effectiveTime);
		
		ArrayList<CCDALabResultObs>	 resultsObsList = new ArrayList<>();
		
		CCDALabResultObs resultsObsOne = new CCDALabResultObs();
		
		ArrayList<CCDAII> resultsObsTemplateIds = new ArrayList<CCDAII>();
		CCDAII resultsObsTemplateIdOne = new CCDAII();
		resultsObsTemplateIdOne.setRootValue("2.16.840.1.113883.10.20.22.4.2");
		resultsObsTemplateIdOne.setExtValue("2015-08-01");
		resultsObsTemplateIds.add(resultsObsTemplateIdOne);
		CCDAII resultsObsTemplateIdTwo = new CCDAII();
		resultsObsTemplateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.4.2");
		resultsObsTemplateIds.add(resultsObsTemplateIdTwo);
		
		resultsObsOne.setTemplateIds(resultsObsTemplateIds);
		
		CCDACode resultCode = new CCDACode();
		resultCode.setCode("24357-6");
		resultCode.setCodeSystem("2.16.840.1.113883.6.1");
		resultCode.setCodeSystemName("LOINC");
		resultCode.setDisplayName("Urinanalysis macro (dipstick) panel");
		
		resultsObsOne.setResultCode(resultCode);
		resultsObsOne.setStatusCode(statusCode);
		resultsObsOne.setMeasurementTime(new CCDAEffTime("20150622"));
		
		
		resultsObsList.add(resultsObsOne);
		
		resultOrg.setResultObs(resultsObsList);
		
		resultOrgList.add(resultOrg);
	}
	
	private void setLabTestsSectionCode()
	 {
		sectionCode = new CCDACode();
		sectionCode.setCode("30954-2");
		sectionCode.setCodeSystem("2.16.840.1.113883.6.1");
		sectionCode.setCodeSystemName("LOINC");
		sectionCode.setDisplayName("RESULTS");
	 }
	
	private void setLabTestsTemplateIds()
	 {
		templateIds = new ArrayList<CCDAII>();
		CCDAII templateIdOne = new CCDAII();
		templateIdOne.setRootValue("2.16.840.1.113883.10.20.22.2.3.1");
		templateIdOne.setExtValue("2015-08-01");
		templateIds.add(templateIdOne);
		CCDAII templateIdTwo = new CCDAII();
		templateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.2.3.1");
		templateIds.add(templateIdTwo);
	 }
	
	
	@Test
	public void testLabTests() throws Exception{
		Assert.assertNotNull(labTests);
	}

	@Test
	public void testLabTestsSectionCode(){
		setLabTestsSectionCode();
		Assert.assertEquals("LabTests SectionCode test case failed",sectionCode,labTests.getSectionCode());
	}
	
	@Test
	public void testLabTestTemplateIds(){
		setLabTestsTemplateIds();
		Assert.assertEquals("LabResults TemplateID test case failed",templateIds,labTests.getResultSectionTempalteIds());
	}
	
	@Test
	public void testLabTestOrg(){
		Assert.assertEquals("Lab Tests test case failed",resultOrgList,labTests.getResultOrg());
	}
	
	@Test
	public void testLabTestOrgTemplateId(){
		Assert.assertEquals("Lab Tests Template Id test case failed",resultOrgList.get(0).getTemplateIds(),labTests.getResultOrg().get(0).getTemplateIds());
	}
	
	@Test
	public void testLabTestOrgStatusCode(){
		Assert.assertEquals("Lab Tests status code test case failed",resultOrgList.get(0).getStatusCode(),labTests.getResultOrg().get(0).getStatusCode());
	}
	
	@Test
	public void testLabTestOrgCode(){
		Assert.assertEquals("Lab Tests code test case failed",resultOrgList.get(0).getOrgCode(),labTests.getResultOrg().get(0).getOrgCode());
	}
	
	@Test
	public void testLabTestOrgEffectiveTime(){
		Assert.assertEquals("Lab Tests effective time test case failed",resultOrgList.get(0).getEffTime(),labTests.getResultOrg().get(0).getEffTime());
	}
	
	@Test
	public void testLabTestOrgResultObsTemplateId(){
		Assert.assertEquals("Lab Tests result observation Template Id test case failed",resultOrgList.get(0).getResultObs().get(0).getTemplateIds(),
								labTests.getResultOrg().get(0).getResultObs().get(0).getTemplateIds());
	}
	
	@Test
	public void testLabTestOrgResultObsLabcode(){
		Assert.assertEquals("Lab Tests result observation Lab code test case failed",resultOrgList.get(0).getResultObs().get(0).getLabCode(),
								labTests.getResultOrg().get(0).getResultObs().get(0).getLabCode());
	}
	
	@Test
	public void testLabTestOrgResultObsStatusCode(){
		Assert.assertEquals("Lab Tests result observation Status code test case failed",resultOrgList.get(0).getResultObs().get(0).getStatusCode(),
								labTests.getResultOrg().get(0).getResultObs().get(0).getStatusCode());
	}
	
	@Test
	public void testLabTestOrgResultObsMeasurementTime(){
		Assert.assertEquals("Lab Tests result observation Measurement Time test case failed",resultOrgList.get(0).getResultObs().get(0).getMeasurementTime(),
								labTests.getResultOrg().get(0).getResultObs().get(0).getMeasurementTime());
	}
	
	@Test
	public void testLabTestOrgResultObsMeasurementResults(){
		Assert.assertEquals("Lab Tests result observation results test case failed",resultOrgList.get(0).getResultObs().get(0).getResults(),
								labTests.getResultOrg().get(0).getResultObs().get(0).getResults());
	}
	
	@Test
	public void testLabTestOrgResultObsMeasurementResultsCode(){
		Assert.assertEquals("Lab Tests result observation results code test case failed",resultOrgList.get(0).getResultObs().get(0).getResultCode(),
								labTests.getResultOrg().get(0).getResultObs().get(0).getResultCode());
	}
	
	@Test
	public void testLabTestOrgResultObsMeasurementIntrepretationCode(){
		Assert.assertEquals("Lab Tests result observation Interpretation code test case failed",resultOrgList.get(0).getResultObs().get(0).getInterpretationCode(),
								labTests.getResultOrg().get(0).getResultObs().get(0).getInterpretationCode());
	}
	
	@Test
	public void testLabTestOrgResultObsMeasurementReferenceRange(){
		Assert.assertEquals("Lab Tests result observation reference range test case failed",resultOrgList.get(0).getResultObs().get(0).getReferenceRange(),
								labTests.getResultOrg().get(0).getResultObs().get(0).getReferenceRange());
	}

	@Test
	public void testIsLabTestInsteadOfResult(){
		Assert.assertEquals("IsLabTestInsteadOfResult test case failed",new Boolean(true),labTests.getIsLabTestInsteadOfResult());
	}

}
