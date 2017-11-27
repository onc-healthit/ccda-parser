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
import org.sitenv.ccdaparsing.model.CCDASmokingStatus;
import org.sitenv.ccdaparsing.model.CCDASocialHistory;
import org.sitenv.ccdaparsing.model.CCDATobaccoUse;
import org.sitenv.ccdaparsing.processing.SmokingStatusProcessor;
import org.w3c.dom.Document;

public class SmokingStatusTest {
	
	private static String CCDA_DOC = "src/test/resources/170.315_b1_toc_amb_ccd_r21_sample1_v1.xml";
	private static CCDASocialHistory socialHistory;
	private ArrayList<CCDAII>    templateIds;
	private CCDACode  sectionCode;
	private static ArrayList<CCDASmokingStatus>		smokingStatusList;
	private static ArrayList<CCDATobaccoUse>			tobaccoUseList;
	private SmokingStatusProcessor smokingStatusProcessor = new SmokingStatusProcessor();
	
	
	@BeforeClass
	public  void setUp() throws Exception {
		DocumentBuilderFactory factory = 
				DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(CCDA_DOC));
		XPath xPath =  XPathFactory.newInstance().newXPath();
		socialHistory = smokingStatusProcessor.retrieveSmokingStatusDetails(xPath, doc).get();
		
		smokingStatusList = new ArrayList<>();
		CCDASmokingStatus  smokingStatus = new CCDASmokingStatus();
		
		ArrayList<CCDAII> smokingStatusTemplateIds = new ArrayList<CCDAII>();
		CCDAII smokingStatusTemplateIdOne = new CCDAII();
		smokingStatusTemplateIdOne.setRootValue("2.16.840.1.113883.10.20.22.4.78");
		smokingStatusTemplateIdOne.setExtValue("2014-06-09");
		smokingStatusTemplateIds.add(smokingStatusTemplateIdOne);
		CCDAII smokingStatusTemplateIdTwo = new CCDAII();
		smokingStatusTemplateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.4.78");
		smokingStatusTemplateIds.add(smokingStatusTemplateIdTwo);
		
		CCDACode smokingStatusCode = new CCDACode();
		smokingStatusCode.setCode("449868002");
		smokingStatusCode.setCodeSystem("2.16.840.1.113883.6.96");
		smokingStatusCode.setXpath("CD");
		smokingStatusCode.setDisplayName("Current every day smoker");
		
		smokingStatus.setObservationTime(new CCDAEffTime("20110227"));
		smokingStatus.setSmokingStatusCode(smokingStatusCode);
		smokingStatus.setSmokingStatusTemplateIds(smokingStatusTemplateIds);
		smokingStatusList.add(smokingStatus);
		
		tobaccoUseList = new ArrayList<CCDATobaccoUse>();
		CCDATobaccoUse  tobaccoUse = new CCDATobaccoUse();
		
		ArrayList<CCDAII> tobaccoUseTemplateIds = new ArrayList<CCDAII>();
		CCDAII tobaccoUseTemplateIdOne = new CCDAII();
		tobaccoUseTemplateIdOne.setRootValue("2.16.840.1.113883.10.20.22.4.85");
		tobaccoUseTemplateIdOne.setExtValue("2014-06-09");
		tobaccoUseTemplateIds.add(tobaccoUseTemplateIdOne);
		CCDAII tobaccoUseTemplateIdTwo = new CCDAII();
		tobaccoUseTemplateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.4.85");
		tobaccoUseTemplateIds.add(tobaccoUseTemplateIdTwo);
		
		tobaccoUse.setTobaccoUseTemplateIds(tobaccoUseTemplateIds);
		
		CCDACode tobaccoUseCode = new CCDACode();
		tobaccoUseCode.setCode("428071000124103");
		tobaccoUseCode.setCodeSystem("2.16.840.1.113883.6.96");
		tobaccoUseCode.setXpath("CD");
		tobaccoUseCode.setDisplayName("Heavy tobacco smoker");
		
		tobaccoUse.setTobaccoUseCode(tobaccoUseCode);
		
		CCDAEffTime tobaccoUseTime = new CCDAEffTime();
		tobaccoUseTime.setLow(new CCDADataElement("20050501"));
		tobaccoUseTime.setHigh(new CCDADataElement("20110227"));
		tobaccoUseTime.setHighPresent(true);
		tobaccoUseTime.setLowPresent(true);
		
		tobaccoUse.setTobaccoUseTime(tobaccoUseTime);
		
		tobaccoUseList.add(tobaccoUse);
	}
	
	
	private void setSocialHistorySectionCode()
	{
		sectionCode = new CCDACode();
		sectionCode.setCode("29762-2");
		sectionCode.setCodeSystem("2.16.840.1.113883.6.1");
		sectionCode.setCodeSystemName("LOINC");
		sectionCode.setDisplayName("Social History");
	}
	
	private void setSocialHistoryTemplateIds()
	{
		templateIds = new ArrayList<CCDAII>();
		CCDAII templateIdOne = new CCDAII();
		templateIdOne.setRootValue("2.16.840.1.113883.10.20.22.2.17");
		templateIdOne.setExtValue("2015-08-01");
		templateIds.add(templateIdOne);
		CCDAII templateIdTwo = new CCDAII();
		templateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.2.17");
		templateIds.add(templateIdTwo);
	}
	
	
	@Test
	public void testSocialHistory() throws Exception{
		Assert.assertNotNull(socialHistory);
	}

	@Test
	public void testSocialHistorySectionCode(){
		setSocialHistorySectionCode();
		Assert.assertEquals("Social History SectionCode test case failed",sectionCode,socialHistory.getSectionCode());
	}
	
	@Test
	public void testSocialHistoryTemplateId(){
		setSocialHistoryTemplateIds();
		Assert.assertEquals("Social History teamplet Id test case failed",templateIds,socialHistory.getSectionTemplateIds());
	}
	
	@Test
	public void testSmokingStatus(){
		Assert.assertEquals("Smoking status test case failed",smokingStatusList.get(0),socialHistory.getSmokingStatus().get(0));
	}
	
	@Test
	public void testSmokingStatusTeamplateIds(){
		Assert.assertEquals("Smoking status Teamplate IDs test case failed",smokingStatusList.get(0).getSmokingStatusTemplateIds(),
															socialHistory.getSmokingStatus().get(0).getSmokingStatusTemplateIds());
	}
	
	@Test
	public void testSmokingStatusCode(){
		Assert.assertEquals("Smoking status Code  test case failed",smokingStatusList.get(0).getSmokingStatusCode(),
															socialHistory.getSmokingStatus().get(0).getSmokingStatusCode());
	}
	
	@Test
	public void testSmokingStatusObservationTime(){
		Assert.assertEquals("Smoking status observation time test case failed",smokingStatusList.get(0).getObservationTime(),
															socialHistory.getSmokingStatus().get(0).getObservationTime());
	}
	
	@Test
	public void testTobaccoUse(){
		Assert.assertEquals("Tobacco use test case failed",tobaccoUseList.get(0),socialHistory.getTobaccoUse().get(0));
	}
	
	@Test
	public void testTobaccoUseTemplateId(){
		Assert.assertEquals("Tobacco use template Id test case failed",tobaccoUseList.get(0).getTobaccoUseTemplateIds(),
																	socialHistory.getTobaccoUse().get(0).getTobaccoUseTemplateIds());
	}
	
	@Test
	public void testTobaccoUseCode(){
		Assert.assertEquals("Tobacco use Code test case failed",tobaccoUseList.get(0).getTobaccoUseCode(),
																	socialHistory.getTobaccoUse().get(0).getTobaccoUseCode());
	}
	
	@Test
	public void testTobaccoUseTime(){
		Assert.assertEquals("Tobacco use Time test case failed",tobaccoUseList.get(0).getTobaccoUseTime(),
																	socialHistory.getTobaccoUse().get(0).getTobaccoUseTime());
	}
}
