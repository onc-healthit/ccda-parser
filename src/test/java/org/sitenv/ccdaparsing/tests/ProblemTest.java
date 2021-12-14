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
import org.sitenv.ccdaparsing.model.CCDACode;
import org.sitenv.ccdaparsing.model.CCDADataElement;
import org.sitenv.ccdaparsing.model.CCDAEffTime;
import org.sitenv.ccdaparsing.model.CCDAII;
import org.sitenv.ccdaparsing.model.CCDAProblem;
import org.sitenv.ccdaparsing.model.CCDAProblemConcern;
import org.sitenv.ccdaparsing.model.CCDAProblemObs;
import org.sitenv.ccdaparsing.processing.ProblemProcessor;
import org.w3c.dom.Document;

public class ProblemTest {
	
	private static String CCDA_DOC = "src/test/resources/170.315_b1_toc_amb_ccd_r21_sample1_v1.xml";
	private static CCDAProblem problems;
	private ArrayList<CCDAII>    templateIds;
	private CCDACode  sectionCode;
	private static ArrayList<CCDAProblemConcern> problemConcernList;
	private static ProblemProcessor problemProcessor = new ProblemProcessor();
	
	@BeforeClass
	public static void setUp() throws Exception {
		// removed fields to ensure no side effects with DocumentRoot
		DocumentBuilderFactory factory = 
				DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(CCDA_DOC));
		XPath xPath =  XPathFactory.newInstance().newXPath();
		problems = problemProcessor.retrieveProblemDetails(xPath, doc).get();
		
		problemConcernList = new ArrayList<>();
		
		CCDAProblemConcern problemConcernOne  = new CCDAProblemConcern();
		ArrayList<CCDAII> problemConcernTemplateIds = new ArrayList<CCDAII>();
		CCDAII problemConcernTemplateIdOne = new CCDAII();
		problemConcernTemplateIdOne.setRootValue("2.16.840.1.113883.10.20.22.4.3");
		problemConcernTemplateIdOne.setExtValue("2015-08-01");
		problemConcernTemplateIds.add(problemConcernTemplateIdOne);
		CCDAII problemConcernTemplateIdTwo = new CCDAII();
		problemConcernTemplateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.4.3");
		problemConcernTemplateIds.add(problemConcernTemplateIdTwo);
		
		problemConcernOne.setTemplateId(problemConcernTemplateIds);
		
		CCDACode concernCode = new CCDACode();
		concernCode.setCode("CONC");
		concernCode.setCodeSystem("2.16.840.1.113883.5.6");
		concernCode.setDisplayName("Concern");
		concernCode = (CCDACode) ApplicationUtilTest.setXmlString(concernCode,"code");		
		problemConcernOne.setConcernCode(concernCode);
		
		CCDACode statusCode = new CCDACode();
		statusCode.setCode("active");
		statusCode = (CCDACode) ApplicationUtilTest.setXmlString(statusCode,"statusCode");	
		problemConcernOne.setStatusCode(statusCode);
		
		CCDAEffTime effectiveTime = new CCDAEffTime();
		effectiveTime.setLow( (CCDADataElement) ApplicationUtilTest.setXmlString(new CCDADataElement("20111005"),"low") );
		effectiveTime.setLowPresent(true);
		effectiveTime.setHighPresent(false);
		problemConcernOne.setEffTime(effectiveTime);
		
		ArrayList<CCDAProblemObs> problemObsList = new ArrayList<>();
		
		CCDAProblemObs problemObsOne = new CCDAProblemObs();
		
		ArrayList<CCDAII> problemObsTemplateIds = new ArrayList<CCDAII>();
		CCDAII problemObsTemplateIdOne = new CCDAII();
		problemObsTemplateIdOne.setRootValue("2.16.840.1.113883.10.20.22.4.4");
		problemObsTemplateIdOne.setExtValue("2015-08-01");
		problemObsTemplateIds.add(problemObsTemplateIdOne);
		CCDAII problemObsTemplateIdTwo = new CCDAII();
		problemObsTemplateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.4.4");
		problemObsTemplateIds.add(problemObsTemplateIdTwo);
		
		problemObsOne.setTemplateId(problemObsTemplateIds);
		
		CCDACode problemType = new CCDACode();
		problemType.setCode("64572001");
		problemType.setCodeSystem("2.16.840.1.113883.6.96");
		problemType.setCodeSystemName("SNOMED-CT");
		problemType.setDisplayName("Condition");
		
		problemObsOne.setProblemType(problemType);
		
		CCDACode problemCode = new CCDACode();
		problemCode.setCode("59621000");
		problemCode.setCodeSystem("2.16.840.1.113883.6.96");
		problemCode.setDisplayName("Essential hypertension");
		problemCode.setCodeSystemName("SNOMED-CT");
		problemCode.setXpath("CD");
		problemCode = (CCDACode) ApplicationUtilTest.setXmlString(problemCode,"value");
		problemObsOne.setProblemCode(problemCode);
		
		CCDAEffTime problemObsEffectiveTime = new CCDAEffTime();
		problemObsEffectiveTime.setLow( (CCDADataElement) ApplicationUtilTest.setXmlString(new CCDADataElement("20111005"),"low") );
		problemObsEffectiveTime.setLowPresent(true);
		problemObsEffectiveTime.setHighPresent(false);
		problemObsOne.setEffTime(problemObsEffectiveTime);
		
		ArrayList<CCDACode> translationList = new ArrayList<>();
		CCDACode translation = new CCDACode();
		translation.setCode("75323-6");
		translation.setCodeSystem("2.16.840.1.113883.6.1");
		translation.setCodeSystemName("LOINC");
		translation.setDisplayName("Condition");
		translation = (CCDACode) ApplicationUtilTest.setXmlString(translation,"translation");
		translationList.add(translation);
		
		problemObsOne.setTranslationProblemType(translationList);
		CCDACode completedCode = new CCDACode();
		completedCode.setCode("completed");
		completedCode = (CCDACode) ApplicationUtilTest.setXmlString(completedCode,"statusCode");		
		
		problemObsOne.setStatusCode(completedCode);
		problemObsList.add(problemObsOne);
		
		problemConcernOne.setProblemObservations(problemObsList);
		
		problemConcernList.add(problemConcernOne);
	}
	
	private void setProblemsSectionCode()
	{
		sectionCode = new CCDACode();
		sectionCode.setCode("11450-4");
		sectionCode.setCodeSystem("2.16.840.1.113883.6.1");
		sectionCode.setCodeSystemName("LOINC");
		sectionCode.setDisplayName("PROBLEM LIST");
		sectionCode = (CCDACode) ApplicationUtilTest.setXmlString(sectionCode,"code");		
	}
	
	private void setProblemsTemplateIds()
	{
		templateIds = new ArrayList<CCDAII>();
		CCDAII templateIdOne = new CCDAII();
		templateIdOne.setRootValue("2.16.840.1.113883.10.20.22.2.5.1");
		templateIdOne.setExtValue("2015-08-01");
		templateIds.add(templateIdOne);
		CCDAII templateIdTwo = new CCDAII();
		templateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.2.5.1");
		templateIds.add(templateIdTwo);
	}
	
	
	@Test
	public void testProblems() throws Exception{
		Assert.assertNotNull(problems);
	}

	@Test
	public void testProblemsSectionCode(){
		setProblemsSectionCode();
		Assert.assertEquals("Problems  SectionCode test case failed",sectionCode,problems.getSectionCode());
	}
	
	@Test
	public void testProblemsTemplateIds(){
		setProblemsTemplateIds();
		Assert.assertEquals("Problems  teamplet Id test case failed",templateIds,problems.getSectionTemplateId());
	}
	
	@Test
	public void testProblemConcern(){
		Assert.assertEquals("Problems  Concern  test case failed",problemConcernList.get(0),problems.getProblemConcerns().get(0));
	}
	
	@Test
	public void testProblemConcernTeamplateId(){
		Assert.assertEquals("Problems  Concern Teamplate Id test case failed",problemConcernList.get(0).getTemplateId(),
														problems.getProblemConcerns().get(0).getTemplateId());
	}
	
	@Test
	public void testProblemConcernCode(){
		Assert.assertEquals("Problems  Concern Code  test case failed",problemConcernList.get(0).getConcernCode(),problems.getProblemConcerns().get(0).getConcernCode());
	}
	
	@Test
	public void testProblemConcernStatusCode(){
		Assert.assertEquals("Problems  Concern  test case failed",problemConcernList.get(0).getStatusCode(),problems.getProblemConcerns().get(0).getStatusCode());
	}
	
	@Test
	public void testProblemConcerneffectiveTime(){
		problems.getProblemConcerns().get(0).getEffTime().setXmlString(null);
		problems.getProblemConcerns().get(0).getEffTime().setLineNumber(null);
		problems.getProblemConcerns().get(0).getEffTime().setValuePresent(null);
		Assert.assertEquals("Problems  Concern  Effective Time test case failed",problemConcernList.get(0).getEffTime(),problems.getProblemConcerns().get(0).getEffTime());
	}
	
	@Test
	public void testProblemObservations(){
		problems.getProblemConcerns().get(0).getProblemObservations().get(0).setXmlString(null);
		problems.getProblemConcerns().get(0).getProblemObservations().get(0).setLineNumber(null);
		problems.getProblemConcerns().get(0).getProblemObservations().get(0).setNegationInd(null);
		Assert.assertEquals("Problems  Observations  test case failed",problemConcernList.get(0).getProblemObservations(),
													problems.getProblemConcerns().get(0).getProblemObservations());
	}
	
	@Test
	public void testProblemObservationsTemplateId(){
		Assert.assertEquals("Problems  Observations template Id  test case failed",problemConcernList.get(0).getProblemObservations().get(0).getTemplateId(),
													problems.getProblemConcerns().get(0).getProblemObservations().get(0).getTemplateId());
	}
	
	@Test
	public void testProblemObservationsProblemType(){
		problems.getProblemConcerns().get(0).getProblemObservations().get(0).getProblemType().setXmlString(null);
		Assert.assertEquals("Problems  Observations  problem type test case failed",problemConcernList.get(0).getProblemObservations().get(0).getProblemType(),
													problems.getProblemConcerns().get(0).getProblemObservations().get(0).getProblemType());
	}
	
	@Test
	public void testProblemObservationsProblemCode(){		
		Assert.assertEquals("Problems  Observations  problem code test case failed",problemConcernList.get(0).getProblemObservations().get(0).getProblemCode(),
													problems.getProblemConcerns().get(0).getProblemObservations().get(0).getProblemCode());
	}
	
	@Test
	public void testProblemObservationsTranslationProblemType(){
		Assert.assertEquals("Problems  Observations translation problem type test case failed",problemConcernList.get(0).getProblemObservations().get(0).getTranslationProblemType(),
													problems.getProblemConcerns().get(0).getProblemObservations().get(0).getTranslationProblemType());
	}
	
	@Test
	public void testProblemObservationsEffectiveTime(){
		problems.getProblemConcerns().get(0).getProblemObservations().get(0).getEffTime().setXmlString(null);
		problems.getProblemConcerns().get(0).getProblemObservations().get(0).getEffTime().setLineNumber(null);
		problems.getProblemConcerns().get(0).getProblemObservations().get(0).getEffTime().setValuePresent(null);
		Assert.assertEquals("Problems  Observations effective time test case failed",problemConcernList.get(0).getProblemObservations().get(0).getEffTime(),
													problems.getProblemConcerns().get(0).getProblemObservations().get(0).getEffTime());
	}

}
