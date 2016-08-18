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
import org.sitenv.ccdaparsing.model.CCDAPQ;
import org.sitenv.ccdaparsing.processing.LaboratoryResultsProcessor;
import org.w3c.dom.Document;

public class LaboratoryResultsTest {
	private static String CCDA_DOC = "src/test/resources/170.315_b1_toc_amb_ccd_r21_sample1_v1.xml";
	private static CCDALabResult labResults;
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
		labResults = LaboratoryResultsProcessor.retrieveLabResults(xPath, doc,idList);
	}
	
	private void setLabResultsSectionCode()
	 {
		sectionCode = new CCDACode();
		sectionCode.setCode("30954-2");
		sectionCode.setCodeSystem("2.16.840.1.113883.6.1");
		sectionCode.setCodeSystemName("LOINC");
		sectionCode.setDisplayName("RESULTS");
	 }
	
	private void setLabResultsTemplateIds()
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
	
	@BeforeClass
	public static void setLabResultsOrg()
	{
		
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
		statusCode.setCode("completed");
		
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
		resultCode.setCode("5778-6");
		resultCode.setCodeSystem("2.16.840.1.113883.6.1");
		resultCode.setCodeSystemName("LOINC");
		resultCode.setDisplayName("Color of Urine");
		
		resultsObsOne.setResultCode(resultCode);
		resultsObsOne.setStatusCode(statusCode);
		resultsObsOne.setMeasurementTime(new CCDADataElement("20150622"));
		
		CCDACode interpretationCode = new CCDACode();
		interpretationCode.setCode("N");
		interpretationCode.setCodeSystem("2.16.840.1.113883.5.83");
		
		resultsObsOne.setInterpretationCode(interpretationCode);
		
		resultsObsList.add(resultsObsOne);
		
		CCDALabResultObs resultsObsTwo = new CCDALabResultObs();
		
		resultsObsTwo.setTemplateIds(resultsObsTemplateIds);
		resultsObsTwo.setInterpretationCode(interpretationCode);
		resultsObsTwo.setMeasurementTime(new CCDADataElement("20150622"));
		
		ArrayList<CCDAPQ> referenceRangeList = new ArrayList<CCDAPQ>();
		CCDAPQ referenceRangeOne = new CCDAPQ();
		referenceRangeOne.setValue("5.0");
		referenceRangeOne.setUnits("[pH]");
		referenceRangeList.add(referenceRangeOne);
		
		CCDAPQ referenceRangeTwo = new CCDAPQ();
		referenceRangeTwo.setValue("8.0");
		referenceRangeTwo.setUnits("[pH]");
		referenceRangeList.add(referenceRangeTwo);
		
		resultsObsTwo.setReferenceRange(referenceRangeList);
		CCDACode resultCodeTwo = new CCDACode();
		resultCodeTwo.setCode("5803-2");
		resultCodeTwo.setCodeSystem("2.16.840.1.113883.6.1");
		resultCodeTwo.setCodeSystemName("LOINC");
		resultCodeTwo.setDisplayName("pH of Urine by Test strip");
		resultsObsTwo.setResultCode(resultCodeTwo);
		CCDAPQ results = new CCDAPQ();
		results.setValue("5.0");
		results.setUnits("[pH]");
		resultsObsTwo.setResults(results);
		resultsObsTwo.setStatusCode(statusCode);
		
		resultsObsList.add(resultsObsTwo);
		
		resultOrg.setResultObs(resultsObsList);
		
		
		resultOrgList.add(resultOrg);
	}
	
	
	@Test
	public void testLabResults() throws Exception{
		Assert.assertNotNull(labResults);
	}

	@Test
	public void testLabResultsSectionCode(){
		setLabResultsSectionCode();
		Assert.assertEquals("LabResults SectionCode test case failed",sectionCode,labResults.getSectionCode());
	}
	
	@Test
	public void testLabResultsTemplateIds(){
		setLabResultsTemplateIds();
		Assert.assertEquals("LabResults TemplateID test case failed",templateIds,labResults.getResultSectionTempalteIds());
	}
	
	@Test
	public void testResultsOrganizerTemplateIds(){
		Assert.assertEquals("Result Organizer Template Ids test case failed",labResults.getResultOrg().get(0).getTemplateIds(),resultOrgList.get(0).getTemplateIds());
	}
	
	@Test
	public void testResultsOrganizerStatusCode(){
		Assert.assertEquals("Result Organizer Status Code test case failed",labResults.getResultOrg().get(0).getStatusCode(),resultOrgList.get(0).getStatusCode());
	}
	
	@Test
	public void testResultsOrganizerOrgCode(){
		Assert.assertEquals("Result Organizer Org Code test case failed",labResults.getResultOrg().get(0).getOrgCode(),resultOrgList.get(0).getOrgCode());
	}
	
	@Test
	public void testResultsOrganizerOrgEffectiveTime(){
		Assert.assertEquals("Result Organizer Effective time test case failed",labResults.getResultOrg().get(0).getEffTime(),resultOrgList.get(0).getEffTime());
	}
	
	@Test
	public void testResultsResultObservationTemplateIds(){
		Assert.assertEquals("Result Observation template Ids test case failed",labResults.getResultOrg().get(0).getResultObs().get(0).getTemplateIds(),
											resultOrgList.get(0).getResultObs().get(0).getTemplateIds());
	}
	
	@Test
	public void testResultsResultObservationLabCode(){
		Assert.assertEquals("Result Observation Lab Code test case failed",labResults.getResultOrg().get(0).getResultObs().get(0).getLabCode(),
											resultOrgList.get(0).getResultObs().get(0).getLabCode());
	}
	
	@Test
	public void testResultsResultObservationStatusCode(){
		Assert.assertEquals("Result Observation Status Code test case failed",labResults.getResultOrg().get(0).getResultObs().get(0).getStatusCode(),
											resultOrgList.get(0).getResultObs().get(0).getStatusCode());
	}
	
	@Test
	public void testResultsResultObservationMeasurementTime(){
		Assert.assertEquals("Result Observation Measurement time test case failed",labResults.getResultOrg().get(0).getResultObs().get(0).getMeasurementTime(),
											resultOrgList.get(0).getResultObs().get(0).getMeasurementTime());
	}
	
	@Test
	public void testResultsResultObservationResults(){
		Assert.assertEquals("Result Observation Results test case failed",labResults.getResultOrg().get(0).getResultObs().get(0).getResults(),
											resultOrgList.get(0).getResultObs().get(0).getResults());
	}
	
	@Test
	public void testResultsResultObservationResultsCode(){
		Assert.assertEquals("Result Observation Results Code test case failed",labResults.getResultOrg().get(0).getResultObs().get(0).getResultCode(),
											resultOrgList.get(0).getResultObs().get(0).getResultCode());
	}
	
	@Test
	public void testResultsResultObservationInterpretationCode(){
		Assert.assertEquals("Result Observation Interpretation Code test case failed",labResults.getResultOrg().get(0).getResultObs().get(0).getInterpretationCode(),
											resultOrgList.get(0).getResultObs().get(0).getInterpretationCode());
	}
	
	@Test
	public void testResultsResultObservationReferenceRange(){
		Assert.assertEquals("Result Observation reference Range test case failed",labResults.getResultOrg().get(0).getResultObs().get(0).getReferenceRange(),
											resultOrgList.get(0).getResultObs().get(0).getReferenceRange());
	}
	
	@Test
	public void testResultsResultObservationReferenceRangeTwo(){
		Assert.assertEquals("Result Observation reference Range Second test case failed",labResults.getResultOrg().get(0).getResultObs().get(3).getReferenceRange(),
											resultOrgList.get(0).getResultObs().get(1).getReferenceRange());
	}
	
	@Test
	public void testIsLabTestInsteadOfResult(){
		Assert.assertEquals("IsLabTestInsteadOfResult test case failed",new Boolean(false),labResults.getIsLabTestInsteadOfResult());
	}

}
