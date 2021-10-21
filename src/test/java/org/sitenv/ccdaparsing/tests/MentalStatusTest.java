package org.sitenv.ccdaparsing.tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sitenv.ccdaparsing.model.CCDACode;
import org.sitenv.ccdaparsing.model.CCDAMentalStatus;
import org.sitenv.ccdaparsing.model.CCDAII;
import org.sitenv.ccdaparsing.processing.MentalStatusProcessor;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;

public class MentalStatusTest {


	private static CCDAMentalStatus functionalStatus;
	private ArrayList<CCDAII> templateIds;
	private CCDACode sectionCode;

	@BeforeClass
	public static void setUp() throws Exception {
		final String CCDA_DOC = "src/test/resources/C-CDA_2-1_remaning_section_test.xml";
		MentalStatusProcessor functionalStatusProcessor = new MentalStatusProcessor();
		// removed fields to ensure no side effects with DocumentRoot
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(CCDA_DOC));
		XPath xPath = XPathFactory.newInstance().newXPath();
		functionalStatus = functionalStatusProcessor.retrieveMentalStatusDetails(xPath, doc).get();
	}

	private void setMentalStatusSectionCode() {
		sectionCode = new CCDACode();
		sectionCode.setCode("10190-7");
		sectionCode.setCodeSystem("2.16.840.1.113883.6.1");
		sectionCode.setCodeSystemName("LOINC");
		sectionCode.setDisplayName("MENTAL STATUS");
		sectionCode.setXmlString("<code xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" code=\"10190-7\" codeSystem=\"2.16.840.1.113883.6.1\" codeSystemName=\"LOINC\" displayName=\"MENTAL STATUS\"/>");
	}

	private void setMentalStatusTemplateIds() {
		templateIds = new ArrayList<CCDAII>();
		CCDAII templateIdTwo = new CCDAII();
		templateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.2.56");
		templateIdTwo.setExtValue("2015-08-01");
		templateIds.add(templateIdTwo);
	}

	@Test
	public void testMentalStatus() {
		Assert.assertNotNull(functionalStatus);
	}

	@Test
	public void testMentalStatusSectionCode() {
		setMentalStatusSectionCode();
		Assert.assertEquals("Mental Status section code test case failed", sectionCode, functionalStatus.getSectionCode());
	}

	@Test
	public void testMentalStatusTemplateIds() {
		setMentalStatusTemplateIds();
		Assert.assertEquals("Mental Status Template Ids test case failed", templateIds, functionalStatus.getTemplateIds());
	}
}
