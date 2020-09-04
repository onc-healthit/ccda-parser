package org.sitenv.ccdaparsing.tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sitenv.ccdaparsing.model.CCDACode;
import org.sitenv.ccdaparsing.model.CCDAFunctionalStatus;
import org.sitenv.ccdaparsing.model.CCDAII;
import org.sitenv.ccdaparsing.processing.FunctionalStatusProcessor;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;

public class FunctionalStatusTest {


	private static CCDAFunctionalStatus functionalStatus;
	private ArrayList<CCDAII> templateIds;
	private CCDACode sectionCode;

	@BeforeClass
	public static void setUp() throws Exception {
		final String CCDA_DOC = "src/test/resources/C-CDA_2-1_remaning_section_test.xml";
		FunctionalStatusProcessor functionalStatusProcessor = new FunctionalStatusProcessor();
		// removed fields to ensure no side effects with DocumentRoot
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(CCDA_DOC));
		XPath xPath = XPathFactory.newInstance().newXPath();
		functionalStatus = functionalStatusProcessor.retrieveFunctionalStatusDetails(xPath, doc).get();
	}

	private void setFunctionalStatusSectionCode() {
		sectionCode = new CCDACode();
		sectionCode.setCode("47420-5");
		sectionCode.setCodeSystem("2.16.840.1.113883.6.1");
		sectionCode.setCodeSystemName("LOINC");
		sectionCode.setDisplayName("Functional Status");
		sectionCode.setXmlString("<code xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" code=\"47420-5\" codeSystem=\"2.16.840.1.113883.6.1\" codeSystemName=\"LOINC\" displayName=\"Functional Status\"/>");
	}

	private void setFunctionalStatusTemplateIds() {
		templateIds = new ArrayList<CCDAII>();
		CCDAII templateIdOne = new CCDAII();
		templateIdOne.setRootValue("2.16.840.1.113883.10.20.22.2.14");
		templateIds.add(templateIdOne);
		CCDAII templateIdTwo = new CCDAII();
		templateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.2.14");
		templateIdTwo.setExtValue("2014-06-09");
		templateIds.add(templateIdTwo);
	}

	@Test
	public void testFunctionalStatus() {
		Assert.assertNotNull(functionalStatus);
	}

	@Test
	public void testFunctionalStatusSectionCode() {
		setFunctionalStatusSectionCode();
		Assert.assertEquals("Functional Status section code test case failed", sectionCode, functionalStatus.getSectionCode());
	}

	@Test
	public void testFunctionalStatusTemplateIds() {
		setFunctionalStatusTemplateIds();
		Assert.assertEquals("Functional Status Template Ids test case failed", templateIds, functionalStatus.getTemplateIds());
	}
}
