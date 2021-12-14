package org.sitenv.ccdaparsing.tests;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.sitenv.ccdaparsing.model.CCDACode;
import org.sitenv.ccdaparsing.model.CCDADataElement;
import org.sitenv.ccdaparsing.model.CCDAEffTime;
import org.sitenv.ccdaparsing.model.CCDAII;
import org.sitenv.ccdaparsing.model.CCDALabResult;
import org.sitenv.ccdaparsing.model.CCDALabResultObs;
import org.sitenv.ccdaparsing.model.CCDALabResultOrg;
import org.sitenv.ccdaparsing.model.CCDAPQ;
import org.sitenv.ccdaparsing.processing.LaboratoryResultsProcessor;
import org.w3c.dom.Document;

public class LaboratoryTestTestCases {
	
	private static String CCDA_DOC = "src/test/resources/170.315_b1_toc_amb_ccd_r21_sample1_v1.xml";
	private static CCDALabResult labTests;
	private ArrayList<CCDAII>    templateIds;
	private CCDACode  sectionCode;
	private static ArrayList<CCDALabResultOrg> resultOrgList;
	private static LaboratoryResultsProcessor laboratoryResultsProcessor = new LaboratoryResultsProcessor();
	
	
	@BeforeClass
	public static void setUp() throws Exception {
		// removed fields to ensure no side effects with DocumentRoot
		DocumentBuilderFactory factory = 
				DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(CCDA_DOC));
		XPath xPath =  XPathFactory.newInstance().newXPath();
		labTests = laboratoryResultsProcessor.retrieveLabResults(xPath, doc).get();
		
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
		orgCode = (CCDACode) ApplicationUtilTest.setXmlString(orgCode,"code");
		resultOrg.setOrgCode(orgCode);
		
		CCDACode statusCode = new CCDACode();
		statusCode.setCode("completed");
		statusCode = (CCDACode) ApplicationUtilTest.setXmlString(statusCode,"statusCode");
		resultOrg.setStatusCode(statusCode);
		
		CCDAEffTime effectiveTime = new CCDAEffTime();
		effectiveTime.setLow((CCDADataElement) ApplicationUtilTest.setXmlString(new CCDADataElement("20150622"),"low"));
		effectiveTime.setHigh((CCDADataElement) ApplicationUtilTest.setXmlString(new CCDADataElement("20150622"),"high"));
		effectiveTime.setHighPresent(true);
		effectiveTime.setLowPresent(true);
		resultOrg.setEffTime(effectiveTime);
		
		ArrayList<CCDALabResultObs>	 resultsObsList = new ArrayList<>();
		
		CCDALabResultObs resultsObsOne = new CCDALabResultObs();
		CCDALabResultObs resultsObsTwo = new CCDALabResultObs();
		CCDALabResultObs resultsObsThree = new CCDALabResultObs();
		CCDALabResultObs resultsObsFour = new CCDALabResultObs();
		CCDALabResultObs resultsObsFive = new CCDALabResultObs();
		CCDALabResultObs resultsObsSix = new CCDALabResultObs();
		CCDALabResultObs resultsObsSeven = new CCDALabResultObs();
		
		
		ArrayList<CCDAII> resultsObsTemplateIds = new ArrayList<CCDAII>();
		CCDAII resultsObsTemplateIdOne = new CCDAII();
		resultsObsTemplateIdOne.setRootValue("2.16.840.1.113883.10.20.22.4.2");
		resultsObsTemplateIdOne.setExtValue("2015-08-01");
		resultsObsTemplateIds.add(resultsObsTemplateIdOne);
		CCDAII resultsObsTemplateIdTwo = new CCDAII();
		resultsObsTemplateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.4.2");
		resultsObsTemplateIds.add(resultsObsTemplateIdTwo);
		
		resultsObsOne.setTemplateIds(resultsObsTemplateIds);
		resultsObsTwo.setTemplateIds(resultsObsTemplateIds);
		resultsObsThree.setTemplateIds(resultsObsTemplateIds);
		resultsObsFour.setTemplateIds(resultsObsTemplateIds);
		resultsObsFive.setTemplateIds(resultsObsTemplateIds);
		resultsObsSix.setTemplateIds(resultsObsTemplateIds);
		resultsObsSeven.setTemplateIds(resultsObsTemplateIds);
		
		
		CCDACode resultCode = new CCDACode();
		resultCode.setCode("5778-6");
		resultCode.setCodeSystem("2.16.840.1.113883.6.1");
		resultCode.setCodeSystemName("LOINC");
		resultCode.setDisplayName("Color of Urine");
		resultCode = (CCDACode) ApplicationUtilTest.setXmlString(resultCode,"code");
		resultsObsOne.setResultCode(resultCode);
		resultsObsOne.setStatusCode(statusCode);
		resultsObsOne.setMeasurementTime(new CCDAEffTime("20150622"));
		
		CCDACode resultCodeTwo = new CCDACode();
		resultCodeTwo.setCode("5767-9");
		resultCodeTwo.setCodeSystem("2.16.840.1.113883.6.1");
		resultCodeTwo.setCodeSystemName("LOINC");
		resultCodeTwo.setDisplayName("Appearance of Urine");
		resultCodeTwo = (CCDACode) ApplicationUtilTest.setXmlString(resultCodeTwo,"code");		
		resultsObsTwo.setResultCode(resultCodeTwo);
		resultsObsTwo.setStatusCode(statusCode);
		resultsObsTwo.setMeasurementTime(new CCDAEffTime("20150622"));		
		
		CCDACode resultCodeThree = new CCDACode();
		resultCodeThree.setCode("5767-9");
		resultCodeThree.setCodeSystem("2.16.840.1.113883.6.1");
		resultCodeThree.setCodeSystemName("LOINC");
		resultCodeThree.setDisplayName("Appearance of Urine");
		resultCodeThree = (CCDACode) ApplicationUtilTest.setXmlString(resultCodeTwo,"code");		
		resultsObsThree.setResultCode(resultCodeThree);
		resultsObsThree.setStatusCode(statusCode);
		resultsObsThree.setMeasurementTime(new CCDAEffTime("20150622"));		
		
		CCDACode resultCodeFour = new CCDACode();
		resultCodeFour.setCode("5767-9");
		resultCodeFour.setCodeSystem("2.16.840.1.113883.6.1");
		resultCodeFour.setCodeSystemName("LOINC");
		resultCodeFour.setDisplayName("Appearance of Urine");
		resultCodeFour = (CCDACode) ApplicationUtilTest.setXmlString(resultCodeTwo,"code");		
		resultsObsFour.setResultCode(resultCodeFour);
		resultsObsFour.setStatusCode(statusCode);
		resultsObsFour.setMeasurementTime(new CCDAEffTime("20150622"));		
		
		CCDACode resultCodeFive = new CCDACode();
		resultCodeFive.setCode("5767-9");
		resultCodeFive.setCodeSystem("2.16.840.1.113883.6.1");
		resultCodeFive.setCodeSystemName("LOINC");
		resultCodeFive.setDisplayName("Appearance of Urine");
		resultCodeFive = (CCDACode) ApplicationUtilTest.setXmlString(resultCodeTwo,"code");		
		resultsObsFive.setResultCode(resultCodeFive);
		resultsObsFive.setStatusCode(statusCode);
		resultsObsFive.setMeasurementTime(new CCDAEffTime("20150622"));		
		
		CCDACode resultCodeSix = new CCDACode();
		resultCodeSix.setCode("5767-9");
		resultCodeSix.setCodeSystem("2.16.840.1.113883.6.1");
		resultCodeSix.setCodeSystemName("LOINC");
		resultCodeSix.setDisplayName("Appearance of Urine");
		resultCodeSix = (CCDACode) ApplicationUtilTest.setXmlString(resultCodeTwo,"code");		
		resultsObsSix.setResultCode(resultCodeSix);
		resultsObsSix.setStatusCode(statusCode);
		resultsObsSix.setMeasurementTime(new CCDAEffTime("20150622"));		
		
		CCDACode resultCodeSeven = new CCDACode();
		resultCodeSeven.setCode("5767-9");
		resultCodeSeven.setCodeSystem("2.16.840.1.113883.6.1");
		resultCodeSeven.setCodeSystemName("LOINC");
		resultCodeSeven.setDisplayName("Appearance of Urine");
		resultCodeSeven = (CCDACode) ApplicationUtilTest.setXmlString(resultCodeTwo,"code");		
		resultsObsSeven.setResultCode(resultCodeSeven);
		resultsObsSeven.setStatusCode(statusCode);
		resultsObsSeven.setMeasurementTime(new CCDAEffTime("20150622"));		
		
		CCDACode interpretationCode = new CCDACode();
		interpretationCode.setCode("N");
		interpretationCode.setCodeSystem("2.16.840.1.113883.5.83");
		interpretationCode = (CCDACode) ApplicationUtilTest.setXmlString(interpretationCode,"interpretationCode");
		resultsObsOne.setInterpretationCode(interpretationCode);
		resultsObsTwo.setInterpretationCode(interpretationCode);
		resultsObsThree.setInterpretationCode(interpretationCode);
		resultsObsFour.setInterpretationCode(interpretationCode);
		resultsObsFive.setInterpretationCode(interpretationCode);
		resultsObsSix.setInterpretationCode(interpretationCode);
		resultsObsSeven.setInterpretationCode(interpretationCode);
		
		CCDAPQ rateQuantity = new CCDAPQ();
		rateQuantity.setValue("YELLOW");
		rateQuantity.setXsiType("ST");
		resultsObsOne.setResults(rateQuantity);
		resultsObsTwo.setResults(rateQuantity);
		resultsObsThree.setResults(rateQuantity);
		resultsObsFour.setResults(rateQuantity);
		resultsObsFive.setResults(rateQuantity);
		resultsObsSix.setResults(rateQuantity);
		resultsObsSeven.setResults(rateQuantity);
		
		
		resultsObsList.add(resultsObsOne);
		resultsObsList.add(resultsObsTwo);
		resultsObsList.add(resultsObsThree);
		resultsObsList.add(resultsObsFour);
		resultsObsList.add(resultsObsFive);
		resultsObsList.add(resultsObsSix);
		resultsObsList.add(resultsObsSeven);
		
		
		resultsObsList.get(0).setReferenceText(new CCDADataElement("#result2"));
		resultsObsList.get(1).setReferenceText(new CCDADataElement("#result3"));
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
		sectionCode = (CCDACode) ApplicationUtilTest.setXmlString(sectionCode,"code");
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

	@Ignore
	@Test
	public void testLabTestOrg(){
		Assert.assertEquals("Lab Tests test case failed",resultOrgList.get(0).getResultObs().get(0),labTests.getResultOrg().get(0));
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
		labTests.getResultOrg().get(0).getEffTime().setXmlString(null);
		labTests.getResultOrg().get(0).getEffTime().setLineNumber(null);
		labTests.getResultOrg().get(0).getEffTime().setValuePresent(null);
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
		labTests.getResultOrg().get(0).getResultObs().get(0).getMeasurementTime().setXmlString(null);
		labTests.getResultOrg().get(0).getResultObs().get(0).getMeasurementTime().setLineNumber(null);
		labTests.getResultOrg().get(0).getResultObs().get(0).getMeasurementTime().setValuePresent(null);
		labTests.getResultOrg().get(0).getResultObs().get(0).getMeasurementTime().setHighPresent(null);
		labTests.getResultOrg().get(0).getResultObs().get(0).getMeasurementTime().setLowPresent(null);
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
		Assert.assertEquals("IsLabTestInsteadOfResult test case failed",Boolean.FALSE,labTests.getIsLabTestInsteadOfResult());
	}

}
