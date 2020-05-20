package org.sitenv.ccdaparsing.tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sitenv.ccdaparsing.model.CCDACode;
import org.sitenv.ccdaparsing.model.CCDADataElement;
import org.sitenv.ccdaparsing.model.CCDAEffTime;
import org.sitenv.ccdaparsing.model.CCDAFamilyHistory;
import org.sitenv.ccdaparsing.model.CCDAFamilyHxObs;
import org.sitenv.ccdaparsing.model.CCDAFamilyHxOrg;
import org.sitenv.ccdaparsing.model.CCDAII;
import org.sitenv.ccdaparsing.model.CCDAPQ;
import org.sitenv.ccdaparsing.processing.FamilyHistoryProcessor;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;

public class FamilyHistoryTest {


	private static CCDAFamilyHistory familyHistory;
	private ArrayList<CCDAII> templateIds;
	private CCDACode sectionCode;
	private static ArrayList<CCDAFamilyHxOrg> familyHxOrgs;

	@BeforeClass
	public static void setUp() throws Exception {
		final String CCDA_DOC = "src/test/resources/C-CDA_2-1_remaning_section_test.xml";
		FamilyHistoryProcessor familyHistoryProcessor = new FamilyHistoryProcessor();
		// removed fields to ensure no side effects with DocumentRoot
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(CCDA_DOC));
		XPath xPath = XPathFactory.newInstance().newXPath();
		familyHistory = familyHistoryProcessor.retrieveFamilyHistoryDetails(xPath, doc).get();

		familyHxOrgs = new ArrayList<CCDAFamilyHxOrg>();

		CCDAFamilyHxOrg familyHxOrgOne = new CCDAFamilyHxOrg();

		ArrayList<CCDAII> familyHxOrgTemplateIds = new ArrayList<CCDAII>();
		CCDAII templateIdOne = new CCDAII();
		templateIdOne.setRootValue("2.16.840.1.113883.10.20.22.4.45");
		familyHxOrgTemplateIds.add(templateIdOne);
		CCDAII templateIdTwo = new CCDAII();
		templateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.4.45");
		templateIdTwo.setExtValue("2015-08-01");
		familyHxOrgTemplateIds.add(templateIdTwo);

		familyHxOrgOne.setTemplateIds(familyHxOrgTemplateIds);

		CCDACode statusCode = new CCDACode();
		statusCode.setCode("completed");
		statusCode.setXmlString("<statusCode xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" code=\"completed\"/>");
		familyHxOrgOne.setStatusCode(statusCode);

		CCDACode relationCode = new CCDACode();
		relationCode.setCode("FTH");
		relationCode.setCodeSystem("2.16.840.1.113883.5.111");
		relationCode.setCodeSystemName("HL7 RoleCode");
		relationCode.setDisplayName("Natural Parent");
		relationCode.setXmlString("<code xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" code=\"FTH\" codeSystem=\"2.16.840.1.113883.5.111\" codeSystemName=\"HL7 RoleCode\" displayName=\"Natural Parent\">\r\n" +
				"                                        <originalText>\r\n" +
				"                                            <reference value=\"#FH1rel\"/>\r\n" +
				"                                        </originalText>\r\n" +
				"                                    </code>");
		familyHxOrgOne.setRelationCode(relationCode);

		CCDACode genderCode = new CCDACode();
		genderCode.setCode("M");
		genderCode.setCodeSystem("2.16.840.1.113883.5.1");
		genderCode.setXmlString("<administrativeGenderCode xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" code=\"M\" codeSystem=\"2.16.840.1.113883.5.1\"/>");
		familyHxOrgOne.setGenderCode(genderCode);

		familyHxOrgOne.setBirthTime(null);

		ArrayList<CCDAFamilyHxObs> familyHxObservationList = new ArrayList<>();

		CCDAFamilyHxObs familyHxObsOne = new CCDAFamilyHxObs();

		CCDACode obsTypeCode  = new CCDACode();
		obsTypeCode.setCode("64572001");
		obsTypeCode.setCodeSystem("2.16.840.1.113883.6.96");
		obsTypeCode.setDisplayName("Condition");
		obsTypeCode.setXmlString("<code xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" code=\"64572001\" codeSystem=\"2.16.840.1.113883.6.96\" displayName=\"Condition\">\r\n" +
				"                                        <translation code=\"75315-2\" codeSystem=\"2.16.840.1.113883.6.1\" displayName=\"Condition Family Member\"/>\r\n" +
				"                                    </code>");
		familyHxObsOne.setObservationTypeCode(obsTypeCode);

		CCDADataElement referenceText =new CCDADataElement();
		referenceText.setValue("#FH1prob1");
		referenceText.setXmlString("<reference value=\"#FH1prob1\"/>");
		familyHxObsOne.setReferenceText(referenceText);

		CCDACode obsCode  = new CCDACode();
		obsCode.setCode("230690007");
		obsCode.setCodeSystem("2.16.840.1.113883.6.96");
		obsCode.setDisplayName("Stroke");
		obsCode.setXpath("CD");
		obsCode.setXmlString("<value xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" code=\"230690007\" codeSystem=\"2.16.840.1.113883.6.96\" displayName=\"Stroke\" xsi:type=\"CD\">\r\n" +
				"                                        <originalText>\r\n" +
				"                                            <reference value=\"#FH1prob1\"/>\r\n" +
				"                                        </originalText>\r\n" +
				"                                    </value>");
		familyHxObsOne.setObservationValue(obsCode);

		CCDAEffTime observationTime = new CCDAEffTime();
		observationTime.setValuePresent(true);
		observationTime.setLowPresent(false);
		observationTime.setHighPresent(false);
		observationTime.setValue("200301");
		observationTime.setLineNumber("null - null");
		observationTime.setXmlString("<effectiveTime xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" value=\"200301\"/>");
		familyHxObsOne.setObservationTime(observationTime);


		CCDAPQ obsAgeValue  = new CCDAPQ();
		obsAgeValue.setValue("72");
		obsAgeValue.setUnits("a");
		obsAgeValue.setXmlString("<value xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" unit=\"a\" value=\"72\" xsi:type=\"PQ\"/>");
		familyHxObsOne.setAgeOnSetValue(obsAgeValue);

		CCDACode obsCauseValue  = new CCDACode();
		obsCauseValue.setCode("419099009");
		obsCauseValue.setCodeSystem("2.16.840.1.113883.6.96");
		obsCauseValue.setCodeSystemName("SNOMED CT");
		obsCauseValue.setXpath("CD");
		obsCauseValue.setDisplayName("Dead");
		obsCauseValue.setXmlString("<value xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" code=\"419099009\" codeSystem=\"2.16.840.1.113883.6.96\" codeSystemName=\"SNOMED CT\" displayName=\"Dead\" xsi:type=\"CD\"/>");
		familyHxObsOne.setCausedDeathValue(obsCauseValue);

		CCDADataElement ageReferenceText =new CCDADataElement();
		ageReferenceText.setValue("#FH1prob1age");
		ageReferenceText.setXmlString("<reference value=\"#FH1prob1age\"/>");
		familyHxObsOne.setAgeOnSetReferenceText(ageReferenceText);

		CCDADataElement causeReferenceText =new CCDADataElement();
		causeReferenceText.setValue("#FH1prob1comment");
		causeReferenceText.setXmlString("<reference value=\"#FH1prob1comment\"/>");
		familyHxObsOne.setCausedDeathReferenceText(causeReferenceText);

		familyHxObservationList.add(familyHxObsOne);
		familyHxOrgOne.setFamilyHxObs(familyHxObservationList);
		familyHxOrgs.add(familyHxOrgOne);
	}

	private void setFamilyHistorySectionCode() {
		sectionCode = new CCDACode();
		sectionCode.setCode("10157-6");
		sectionCode.setCodeSystem("2.16.840.1.113883.6.1");
		sectionCode.setCodeSystemName("LOINC");
		sectionCode.setDisplayName("Family History");
		sectionCode.setXmlString("<code xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" code=\"10157-6\" codeSystem=\"2.16.840.1.113883.6.1\" codeSystemName=\"LOINC\" displayName=\"Family History\"/>");
	}

	private void setFamilyHistoryTemplateIds() {
		templateIds = new ArrayList<CCDAII>();
		CCDAII templateIdOne = new CCDAII();
		templateIdOne.setRootValue("2.16.840.1.113883.10.20.22.2.15");
		templateIds.add(templateIdOne);
		CCDAII templateIdTwo = new CCDAII();
		templateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.2.15");
		templateIdTwo.setExtValue("2015-08-01");
		templateIds.add(templateIdTwo);
	}

	@Test
	public void testFamilyHistory() {
		Assert.assertNotNull(familyHistory);
	}

	@Test
	public void testFamilyHistorySectionCode() {
		setFamilyHistorySectionCode();
		Assert.assertEquals("Family History section code test case failed", sectionCode, familyHistory.getSectionCode());
	}

	@Test
	public void testFamilyHistoryTemplateIds() {
		setFamilyHistoryTemplateIds();
		Assert.assertEquals("Family History Template Ids test case failed", templateIds, familyHistory.getTemplateIds());
	}

	@Test
	public void testFamilyHistoryFamilyHxOrg() {
		Assert.assertNotNull(familyHxOrgs);
	}

	@Test
	public void testFamilyHistoryFamilyHxOrgTemplateId() {
		setFamilyHistoryTemplateIds();
		Assert.assertEquals("Family History Template Ids test case failed", familyHistory.getFamilyHxOrg().get(0).getTemplateIds(), familyHxOrgs.get(0).getTemplateIds());
	}

	@Test
	public void testFamilyHistoryFamilyHxOrgStatusCode() {
		setFamilyHistoryTemplateIds();
		Assert.assertEquals("Family History Status code test case failed", familyHistory.getFamilyHxOrg().get(0).getStatusCode(), familyHxOrgs.get(0).getStatusCode());
	}

	@Test
	public void testFamilyHistoryFamilyHxOrgRelationCode() {
		setFamilyHistoryTemplateIds();
		Assert.assertEquals("Family History Relation Code test case failed", familyHistory.getFamilyHxOrg().get(0).getRelationCode(), familyHxOrgs.get(0).getRelationCode());
	}

	@Test
	public void testFamilyHistoryFamilyHxOrgAdministrativeGenderCode() {
		setFamilyHistoryTemplateIds();
		Assert.assertEquals("Family History Gender Code test case failed", familyHistory.getFamilyHxOrg().get(0).getGenderCode(), familyHxOrgs.get(0).getGenderCode());
	}

	@Test
	public void testFamilyHistoryFamilyHxOrgBirthTime() {
		setFamilyHistoryTemplateIds();
		Assert.assertEquals("Family History Birth Time test case failed", familyHistory.getFamilyHxOrg().get(0).getBirthTime(), familyHxOrgs.get(0).getBirthTime());
	}

	@Test
	public void testFamilyHistoryFamilyHxObsObservationType() {
		setFamilyHistoryTemplateIds();
		Assert.assertEquals("Family History Observation Type test case failed"
				, familyHistory.getFamilyHxOrg().get(0).getFamilyHxObs().get(0).getObservationTypeCode(), familyHxOrgs.get(0).getFamilyHxObs().get(0).getObservationTypeCode());
	}

	@Test
	public void testFamilyHistoryFamilyHxObsObservationValue() {
		setFamilyHistoryTemplateIds();
		Assert.assertEquals("Family History Observation Value test case failed"
				, familyHistory.getFamilyHxOrg().get(0).getFamilyHxObs().get(0).getObservationValue(), familyHxOrgs.get(0).getFamilyHxObs().get(0).getObservationValue());
	}

	@Test
	public void testFamilyHistoryFamilyHxObsReferenceText() {
		setFamilyHistoryTemplateIds();
		Assert.assertEquals("Family History Observation Reference Text test case failed"
				, familyHistory.getFamilyHxOrg().get(0).getFamilyHxObs().get(0).getReferenceText(), familyHxOrgs.get(0).getFamilyHxObs().get(0).getReferenceText());
	}

	@Test
	public void testFamilyHistoryFamilyHxObservationTime() {
		setFamilyHistoryTemplateIds();
		Assert.assertEquals("Family History Observation Time test case failed"
				, familyHistory.getFamilyHxOrg().get(0).getFamilyHxObs().get(0).getObservationTime(), familyHxOrgs.get(0).getFamilyHxObs().get(0).getObservationTime());
	}

	@Test
	public void testFamilyHistoryFamilyHxObsAgeOnSetValue() {
		setFamilyHistoryTemplateIds();
		Assert.assertEquals("Family History Observation Age OnSet Value test case failed"
				, familyHistory.getFamilyHxOrg().get(0).getFamilyHxObs().get(0).getAgeOnSetValue(), familyHxOrgs.get(0).getFamilyHxObs().get(0).getAgeOnSetValue());
	}

	@Test
	public void testFamilyHistoryFamilyHxObsAgeOnSetReferenceText() {
		setFamilyHistoryTemplateIds();
		Assert.assertEquals("Family History Observation Age OnSet Reference Text test case failed"
				, familyHistory.getFamilyHxOrg().get(0).getFamilyHxObs().get(0).getAgeOnSetReferenceText(), familyHxOrgs.get(0).getFamilyHxObs().get(0).getAgeOnSetReferenceText());
	}

	@Test
	public void testFamilyHistoryFamilyHxObsCausedDeathValue() {
		setFamilyHistoryTemplateIds();
		Assert.assertEquals("Family History Observation Caused Death Value test case failed"
				, familyHistory.getFamilyHxOrg().get(0).getFamilyHxObs().get(0).getCausedDeathValue(), familyHxOrgs.get(0).getFamilyHxObs().get(0).getCausedDeathValue());
	}

	@Test
	public void testFamilyHistoryFamilyHxObsCausedDeathReferenceText() {
		setFamilyHistoryTemplateIds();
		Assert.assertEquals("Family History Observation Caused Death Reference Text test case failed"
				, familyHistory.getFamilyHxOrg().get(0).getFamilyHxObs().get(0).getCausedDeathReferenceText(), familyHxOrgs.get(0).getFamilyHxObs().get(0).getCausedDeathReferenceText());
	}

}
