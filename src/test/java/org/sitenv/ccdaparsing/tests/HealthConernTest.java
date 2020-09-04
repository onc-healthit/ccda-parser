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
import org.sitenv.ccdaparsing.model.CCDAHealthConcerns;
import org.sitenv.ccdaparsing.model.CCDAII;
import org.sitenv.ccdaparsing.processing.HealthConcernsProcessor;
import org.w3c.dom.Document;

public class HealthConernTest {
	
	private static String CCDA_DOC = "src/test/resources/170.315_b1_toc_amb_ccd_r21_sample1_v1.xml";
	private static CCDAHealthConcerns healthConcerns;
	private ArrayList<CCDAII>    templateIds;
	private CCDACode  sectionCode;
	//private CCDADataElement narrativeText;
	private static HealthConcernsProcessor healthConcernsProcessor = new HealthConcernsProcessor();
	
	
	@BeforeClass
	public static void setUp() throws Exception {
		// removed fields to ensure no side effects with DocumentRoot
		DocumentBuilderFactory factory = 
				DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(CCDA_DOC));
		XPath xPath =  XPathFactory.newInstance().newXPath();
		healthConcerns = healthConcernsProcessor.retrieveHealthConcernDetails(xPath, doc).get();
	}
	
	
	private void setHealthConcernsSectionCode()
	 {
		sectionCode = new CCDACode();
		sectionCode.setCode("75310-3");
		sectionCode.setCodeSystem("2.16.840.1.113883.6.1");
		sectionCode.setCodeSystemName("LOINC");
		sectionCode.setDisplayName("Health Concerns Document");
		sectionCode.setXmlString("<code xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" code=\"75310-3\" codeSystem=\"2.16.840.1.113883.6.1\" codeSystemName=\"LOINC\" displayName=\"Health Concerns Document\"/>");
	 }
	
	private void setHealthConcernsTemplateIds()
	 {
		templateIds = new ArrayList<CCDAII>();
		CCDAII templateIdOne = new CCDAII();
		templateIdOne.setRootValue("2.16.840.1.113883.10.20.22.2.58");
		templateIdOne.setExtValue("2015-08-01");
		templateIds.add(templateIdOne);
	 }
	
	
	@Test
	public void testHealthConcern() throws Exception{
		Assert.assertNotNull(healthConcerns);
	}

	@Test
	public void testHealthConcernSectionCode(){
		setHealthConcernsSectionCode();
		Assert.assertEquals("HealthConcern section Code test case failed",sectionCode,healthConcerns.getSectionCode());
	}
	
	@Test
	public void testHealthConcernTemplateIds(){
		setHealthConcernsTemplateIds();
		Assert.assertEquals("HealthConcern Template Id test case failed",templateIds,healthConcerns.getTemplateId());
	}

}
