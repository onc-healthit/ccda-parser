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
import org.sitenv.ccdaparsing.model.CCDAII;
import org.sitenv.ccdaparsing.model.CCDASmokingStatus;
import org.sitenv.ccdaparsing.model.CCDASocialHistory;
import org.sitenv.ccdaparsing.processing.SmokingStatusProcessor;
import org.w3c.dom.Document;

public class SmokingStatusTest {
	
	private static String CCDA_DOC = "src/test/resources/170.315_b1_toc_amb_ccd_r21_sample1_v1.xml";
	private static CCDASocialHistory socialHistory;
	private ArrayList<CCDAII>    templateIds;
	private CCDACode  sectionCode;
	private ArrayList<CCDASmokingStatus>		smokingStatusList;
	//private ArrayList<CCDATobaccoUse>			tobaccoUseList;
	
	
	@BeforeClass
	public static void setUp() throws Exception {
		// removed fields to ensure no side effects with DocumentRoot
		DocumentBuilderFactory factory = 
				DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(CCDA_DOC));
		XPath xPath =  XPathFactory.newInstance().newXPath();
		socialHistory = SmokingStatusProcessor.retrieveSmokingStatusDetails(xPath, doc);
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
	
	private void setSmokingStatus()
	{
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
		
		smokingStatus.setObservationTime(new CCDADataElement("20110227"));
		smokingStatus.setSmokingStatusCode(smokingStatusCode);
		smokingStatus.setSmokingStatusTemplateIds(smokingStatusTemplateIds);
		smokingStatusList.add(smokingStatus);
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
		setSmokingStatus();
		Assert.assertEquals("Smoking status test case failed",smokingStatusList.get(0),socialHistory.getSmokingStatus().get(0));
	}
	
	@Test
	public void testSmokingStatusTeamplateIds(){
		setSmokingStatus();
		Assert.assertEquals("Smoking status Teamplate IDs test case failed",smokingStatusList.get(0).getSmokingStatusTemplateIds(),
															socialHistory.getSmokingStatus().get(0).getSmokingStatusTemplateIds());
	}
	
	@Test
	public void testSmokingStatusCode(){
		setSmokingStatus();
		Assert.assertEquals("Smoking status Code  test case failed",smokingStatusList.get(0).getSmokingStatusCode(),
															socialHistory.getSmokingStatus().get(0).getSmokingStatusCode());
	}
	
	@Test
	public void testSmokingStatusObservationTime(){
		setSmokingStatus();
		Assert.assertEquals("Smoking status observation time test case failed",smokingStatusList.get(0).getObservationTime(),
															socialHistory.getSmokingStatus().get(0).getObservationTime());
	}
	

}
