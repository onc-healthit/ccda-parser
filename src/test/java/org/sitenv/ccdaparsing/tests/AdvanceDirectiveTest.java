package org.sitenv.ccdaparsing.tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sitenv.ccdaparsing.model.CCDAAdvanceDirective;
import org.sitenv.ccdaparsing.model.CCDAAdvanceDirectiveObs;
import org.sitenv.ccdaparsing.model.CCDAAdvanceDirectiveOrg;
import org.sitenv.ccdaparsing.model.CCDACode;
import org.sitenv.ccdaparsing.model.CCDADataElement;
import org.sitenv.ccdaparsing.model.CCDAEffTime;
import org.sitenv.ccdaparsing.model.CCDAII;
import org.sitenv.ccdaparsing.processing.AdvanceDirectiveProcesser;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;

public class AdvanceDirectiveTest {

	private static CCDAAdvanceDirective advanceDirective;
	private ArrayList<CCDAII> templateIds;
	private CCDACode sectionCode;
	private static ArrayList<CCDAAdvanceDirectiveOrg> advanceDirectiveOrgs;

	@BeforeClass
	public static void setUp() throws Exception {
		final String CCDA_DOC = "src/test/resources/C-CDA_2-1_remaning_section_test.xml";
		AdvanceDirectiveProcesser advanceDirectiveProcesser = new AdvanceDirectiveProcesser();
		// removed fields to ensure no side effects with DocumentRoot
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(CCDA_DOC));
		XPath xPath = XPathFactory.newInstance().newXPath();
		advanceDirective = advanceDirectiveProcesser.retrieveAdvanceDirectiveDetails(xPath, doc).get();

		advanceDirectiveOrgs = new ArrayList<CCDAAdvanceDirectiveOrg>();

		CCDAAdvanceDirectiveOrg advanceDirectiveOrg = new CCDAAdvanceDirectiveOrg();

		ArrayList<CCDAII> advanceDirectiveOrgTemplateIds = new ArrayList<CCDAII>();
		CCDAII templateIdOne = new CCDAII();
		templateIdOne.setRootValue("2.16.840.1.113883.10.20.22.4.108");
		advanceDirectiveOrgTemplateIds.add(templateIdOne);
		advanceDirectiveOrg.setTemplateIds(advanceDirectiveOrgTemplateIds);
		
		CCDACode orgStatusCode = new CCDACode();
		orgStatusCode.setCode("completed");
		orgStatusCode.setXmlString("<statusCode xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" code=\"completed\"/>");
		advanceDirectiveOrg.setStatusCode(orgStatusCode);
		
		CCDACode orgCode  = new CCDACode();
		orgCode.setCode("45473-6");
		orgCode.setCodeSystem("2.16.840.1.113883.6.1");
		orgCode.setCodeSystemName("LOINC");
		orgCode.setDisplayName("advance directive - living will");
		orgCode.setXmlString("<code xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" code=\"45473-6\" codeSystem=\"2.16.840.1.113883.6.1\" codeSystemName=\"LOINC\" displayName=\"advance directive - living will\"/>");
		advanceDirectiveOrg.setOrgCode(orgCode);

		advanceDirectiveOrg.setReferenceText(null);

		ArrayList<CCDAAdvanceDirectiveObs> advanceDirectiveObsList = new ArrayList<>();

		CCDAAdvanceDirectiveObs advDirectiveObsOne = new CCDAAdvanceDirectiveObs();

		CCDACode statusCode = new CCDACode();
		statusCode.setCode("completed");
		statusCode.setXmlString("<statusCode xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" code=\"completed\"/>");
		advDirectiveObsOne.setStatusCode(statusCode);

		CCDACode obsTypeCode  = new CCDACode();
		obsTypeCode.setCode("75278-2");
		obsTypeCode.setCodeSystem("2.16.840.1.113883.6.1");
		obsTypeCode.setCodeSystemName("LOINC");
		obsTypeCode.setDisplayName("Advance directive status");
		obsTypeCode.setXmlString("<code xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" code=\"75278-2\" codeSystem=\"2.16.840.1.113883.6.1\" codeSystemName=\"LOINC\" displayName=\"Advance directive status\"/>");
		advDirectiveObsOne.setObservationTypeCode(obsTypeCode);

		CCDACode obsCode  = new CCDACode();
		obsCode.setCode("304253006");
		obsCode.setCodeSystem("2.16.840.1.113883.6.96");
		obsCode.setDisplayName("Do not resuscitate");
		obsCode.setXpath("CD");
		obsCode.setXmlString("<value xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" code=\"304253006\" codeSystem=\"2.16.840.1.113883.6.96\" displayName=\"Do not resuscitate\" xsi:type=\"CD\"/>");
		advDirectiveObsOne.setObservationValue(obsCode);

		CCDAEffTime observationTime = new CCDAEffTime();
		observationTime.setValuePresent(false);
		observationTime.setLowPresent(true);
		observationTime.setHighPresent(false);
		CCDADataElement low = new CCDADataElement();
		low.setValue("20110219");
		low.setXmlString("<low xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" value=\"20110219\"/>");
		observationTime.setLow(low);
		observationTime.setLineNumber("null - null");
		observationTime.setXmlString("<effectiveTime xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" +
				"                                        <low value=\"20110219\"/>\r\n" +
				"                                        <high nullFlavor=\"NA\"/>\r\n" +
				"                                    </effectiveTime>");
		advDirectiveObsOne.setObservationTime(observationTime);

		advDirectiveObsOne.setVerifier(null);

		advDirectiveObsOne.setReferenceText(null);

		advDirectiveObsOne.setReferenceDocText(null);

		advanceDirectiveObsList.add(advDirectiveObsOne);

		advanceDirectiveOrg.setAdvanceDirectiveObs(advanceDirectiveObsList);
		advanceDirectiveOrgs.add(advanceDirectiveOrg);
	}

	private void setAdvanceDirectiveSectionCode() {
		sectionCode = new CCDACode();
		sectionCode.setCode("42348-3");
		sectionCode.setCodeSystem("2.16.840.1.113883.6.1");
		sectionCode.setCodeSystemName("LOINC");
		sectionCode.setXmlString("<code xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" code=\"42348-3\" codeSystem=\"2.16.840.1.113883.6.1\" codeSystemName=\"LOINC\"/>");
	}

	private void setAdvanceDirectiveTemplateIds() {
		templateIds = new ArrayList<CCDAII>();
		CCDAII templateIdOne = new CCDAII();
		templateIdOne.setRootValue("2.16.840.1.113883.10.20.22.2.21");
		templateIds.add(templateIdOne);
		CCDAII templateIdTwo = new CCDAII();
		templateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.2.21");
		templateIdTwo.setExtValue("2015-08-01");
		templateIds.add(templateIdTwo);
		CCDAII templateIdThree = new CCDAII();
		templateIdThree.setRootValue("2.16.840.1.113883.10.20.22.2.21.1");
		templateIds.add(templateIdThree);
		CCDAII templateIdFour = new CCDAII();
		templateIdFour.setRootValue("2.16.840.1.113883.10.20.22.2.21.1");
		templateIdFour.setExtValue("2015-08-01");
		templateIds.add(templateIdFour);
	}

	@Test
	public void testAdvanceDirective() {
		Assert.assertNotNull(advanceDirective);
	}

	@Test
	public void testAdvanceDirectiveSectionCode() {
		setAdvanceDirectiveSectionCode();
		Assert.assertEquals("Advance Directive section code test case failed", sectionCode, advanceDirective.getSectionCode());
	}

	@Test
	public void testAdvanceDirectiveTemplateIds() {
		setAdvanceDirectiveTemplateIds();
		Assert.assertEquals("Advance Directive Template Ids test case failed", templateIds, advanceDirective.getTemplateIds());
	}

	@Test
	public void testAdvanceDirectiveOrganizer() {
		Assert.assertNotNull(advanceDirectiveOrgs);
	}

	@Test
	public void testAdvanceDirectiveFamilyHxOrgTemplateId() {
		setAdvanceDirectiveTemplateIds();
		Assert.assertEquals("Advance Directive Template Ids test case failed", advanceDirective.getAdvanceDirectiveOrgs().get(0).getTemplateIds(), advanceDirectiveOrgs.get(0).getTemplateIds());
	}

	@Test
	public void testAdvanceDirectiveOrganizerStatusCode() {
		setAdvanceDirectiveTemplateIds();
		Assert.assertEquals("Advance Directive Status code test case failed", advanceDirective.getAdvanceDirectiveOrgs().get(0).getStatusCode(), advanceDirectiveOrgs.get(0).getStatusCode());
	}

	@Test
	public void testAdvanceDirectiveOrganizerOrgCode() {
		setAdvanceDirectiveTemplateIds();
		Assert.assertEquals("Advance Directive Relation Code test case failed", advanceDirective.getAdvanceDirectiveOrgs().get(0).getOrgCode(), advanceDirectiveOrgs.get(0).getOrgCode());
	}

	@Test
	public void testAdvanceDirectiveAdvDirectiveObsObservationType() {
		setAdvanceDirectiveTemplateIds();
		Assert.assertEquals("Advance Directive Observation Type test case failed"
				, advanceDirective.getAdvanceDirectiveOrgs().get(0).getAdvanceDirectiveObs().get(0).getObservationTypeCode(), advanceDirectiveOrgs.get(0).getAdvanceDirectiveObs().get(0).getObservationTypeCode());
	}

	@Test
	public void testAdvanceDirectiveAdvDirectiveObsObservationValue() {
		setAdvanceDirectiveTemplateIds();
		Assert.assertEquals("Advance Directive Observation Value test case failed"
				, advanceDirective.getAdvanceDirectiveOrgs().get(0).getAdvanceDirectiveObs().get(0).getObservationValue(), advanceDirectiveOrgs.get(0).getAdvanceDirectiveObs().get(0).getObservationValue());
	}

	@Test
	public void testAdvanceDirectiveAdvDirectiveObsReferenceText() {
		setAdvanceDirectiveTemplateIds();
		Assert.assertEquals("Advance Directive Observation Reference Text test case failed"
				, advanceDirective.getAdvanceDirectiveOrgs().get(0).getAdvanceDirectiveObs().get(0).getReferenceText(), advanceDirectiveOrgs.get(0).getAdvanceDirectiveObs().get(0).getReferenceText());
	}

	@Test
	public void testAdvanceDirectiveAdvDirectiveObservationTime() {
		setAdvanceDirectiveTemplateIds();
		Assert.assertEquals("Advance Directive Observation Time test case failed"
				, advanceDirective.getAdvanceDirectiveOrgs().get(0).getAdvanceDirectiveObs().get(0).getObservationTime(), advanceDirectiveOrgs.get(0).getAdvanceDirectiveObs().get(0).getObservationTime());
	}

}
