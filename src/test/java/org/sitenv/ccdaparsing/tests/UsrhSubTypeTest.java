package org.sitenv.ccdaparsing.tests;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.junit.Assert;
import org.junit.Test;
import org.sitenv.ccdaparsing.model.UsrhSubType;
import org.sitenv.ccdaparsing.processing.UsrhSubTypeProcessor;
import org.w3c.dom.Document;

public class UsrhSubTypeTest {
	//Before parsing for 170.315_b1_toc_amb_ccd_r21_sample1_v1.xml we are expecting these results:
	//iiElements -> size = 2
	//		CCDAII
	//		rootValue: 2.16.840.1.113883.10.20.22.1.2
	//		extValue:  2015-08-01
	//		CCDAII
	//		rootValue: 2.16.840.1.113883.10.20.22.1.2
	//		extValue:  null
	//Post parsing we would expect:
	//	UsrhIISubTypes.ccdaDocumentTypes.size() 13
	//	And a break at this equal comparison:
	//	xmlII.getRootValue() 2.16.840.1.113883.10.20.22.1.2
	//	constantUsrhII.getRootValue() 2.16.840.1.113883.10.20.22.1.2
	private final String PATH = "src/test/resources/";
	private final String REAL_CCDA_DOC_CCD = PATH + "170.315_b1_toc_amb_ccd_r21_sample1_v1.xml";
	private final String TEST_DOC_NO_TYPE = PATH + "UsrhSubTypeTest_NoDocType.xml";
	private final String TEST_DOC_UD = PATH + "UsrhSubTypeTest_UnstructuredDocument.xml";
	private final String TEST_DOC_CP = PATH + "UsrhSubTypeTest_CarePlan.xml";
	private static UsrhSubType usrhSubType;
	private static UsrhSubTypeProcessor usrhSubTypeProcessor = new UsrhSubTypeProcessor();
	
	private static void setup(String ccdaDoc) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(ccdaDoc));
		XPath xPath = XPathFactory.newInstance().newXPath();
		usrhSubType = usrhSubTypeProcessor.retrieveUsrhSubTypeDetails(xPath, doc).get();
	}

	@Test
	public void testDocumentTypeCCDIsNotNull() throws Exception {
		setup(REAL_CCDA_DOC_CCD);
		Assert.assertNotNull("The document type is null", usrhSubType);
	}

	@Test
	public void testDocumentTypeCCDIsTheSameAsTheXML() throws Exception {
		setup(REAL_CCDA_DOC_CCD);
		Assert.assertEquals(
				"The document type extracted does not match the expected type in the actual XML",
				UsrhSubType.CONTINUITY_OF_CARE_DOCUMENT, usrhSubType);
	}
	
	@Test
	public void testDocumentWithoutType() throws Exception {
		setup(TEST_DOC_NO_TYPE);
		Assert.assertNull(
				"The result should be null since the XML document does not have type specified", usrhSubType);
	}
	
	@Test
	public void testUnstructuredDocumentType() throws Exception {
		setup(TEST_DOC_UD);
		Assert.assertEquals(
				"The document type extracted does not match the expected type in the actual XML",
				UsrhSubType.UNSTRUCTURED_DOCUMENT, usrhSubType);
	}
	
	@Test
	public void testCarePlanType() throws Exception {
		setup(TEST_DOC_CP);
		Assert.assertEquals(
				"The document type extracted does not match the expected type in the actual XML",
				UsrhSubType.CARE_PLAN, usrhSubType);
	}	

}
