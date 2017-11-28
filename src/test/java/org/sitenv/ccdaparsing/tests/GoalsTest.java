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
import org.sitenv.ccdaparsing.model.CCDAGoals;
import org.sitenv.ccdaparsing.model.CCDAII;
import org.sitenv.ccdaparsing.processing.GoalsProcessor;
import org.w3c.dom.Document;

public class GoalsTest {
	
	private String CCDA_DOC = "src/test/resources/170.315_b1_toc_amb_ccd_r21_sample1_v1.xml";
	private CCDAGoals goals;
	private ArrayList<CCDAII>    templateIds;
	private CCDACode  sectionCode;
	//private CCDADataElement narrativeText;
	private GoalsProcessor goalsProcessor = new GoalsProcessor();
	
	
	@BeforeClass
	public void setUp() throws Exception {
		// removed fields to ensure no side effects with DocumentRoot
		DocumentBuilderFactory factory = 
				DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(CCDA_DOC));
		XPath xPath =  XPathFactory.newInstance().newXPath();
		goals = goalsProcessor.retrieveGoalsDetails(xPath, doc).get();
	}
	
	
	private void setGoalsSectionCode()
	 {
		sectionCode = new CCDACode();
		sectionCode.setCode("61146-7");
		sectionCode.setCodeSystem("2.16.840.1.113883.6.1");
		sectionCode.setCodeSystemName("LOINC");
		sectionCode.setDisplayName("Goals");
	 }
	
	private void setGoalsTemplateIds()
	 {
		templateIds = new ArrayList<CCDAII>();
		CCDAII templateIdOne = new CCDAII();
		templateIdOne.setRootValue("2.16.840.1.113883.10.20.22.2.60");
		templateIds.add(templateIdOne);
	 }
	
	
	@Test
	public void testGoals() throws Exception{
		Assert.assertNotNull(goals);
	}

	@Test
	public void testGoalsSectionCode(){
		setGoalsSectionCode();
		Assert.assertEquals("GoalsSectionCode test case failed",sectionCode,goals.getSectionCode());
	}
	
	@Test
	public void testGoalsTemplateIds(){
		setGoalsTemplateIds();
		Assert.assertEquals("GoalsTemplateID test case failed",templateIds,goals.getTemplateId());
	}
}
