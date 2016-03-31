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
import org.sitenv.ccdaparsing.model.CCDAII;
import org.sitenv.ccdaparsing.model.CCDAProcedure;
import org.sitenv.ccdaparsing.model.CCDAUDI;
import org.sitenv.ccdaparsing.processing.ProcedureProcessor;
import org.sitenv.ccdaparsing.processing.UDIProcessor;
import org.w3c.dom.Document;

public class UDITest {
	
	private static String CCDA_DOC = "src/test/resources/170.315_b1_toc_amb_ccd_r21_sample1_v1.xml";
	private static CCDAProcedure procedures;
	private static ArrayList<CCDAUDI>  patientUDIList;
	private  ArrayList<CCDAUDI>  udiList;
	
	
	@BeforeClass
	public static void setUp() throws Exception {
		// removed fields to ensure no side effects with DocumentRoot
		DocumentBuilderFactory factory = 
				DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(CCDA_DOC));
		XPath xPath =  XPathFactory.newInstance().newXPath();
		procedures = ProcedureProcessor.retrievePrcedureDetails(xPath, doc);
		patientUDIList = UDIProcessor.retrieveUDIDetails(procedures);
	}
	
	private void setProcedureAct()
	{
		udiList = new ArrayList<>();
		CCDAUDI udiOne = new CCDAUDI();
		
		ArrayList<CCDAII> udiTemplateIds = new ArrayList<CCDAII>();
		CCDAII udiTemplateId = new CCDAII();
		udiTemplateId.setRootValue("2.16.840.1.113883.10.20.22.4.37");
		udiTemplateIds.add(udiTemplateId);
		
		udiOne.setTemplateIds(udiTemplateIds);
		
		CCDACode deviceCodeOne = new CCDACode();
		deviceCodeOne.setCode("704708004");
		deviceCodeOne.setCodeSystem("2.16.840.1.113883.6.96");
		deviceCodeOne.setCodeSystemName("SNOMED CT");
		deviceCodeOne.setDisplayName("Cardiac resynchronization therapy implantable pacemaker");

		udiOne.setDeviceCode(deviceCodeOne);
		
		ArrayList<CCDAII> scopingEntityIdList = new ArrayList<CCDAII>();
		CCDAII scopingEntityId = new CCDAII();
		scopingEntityId.setRootValue("2.16.840.1.113883.3.3719");
		scopingEntityIdList.add(scopingEntityId);
		udiOne.setScopingEntityId(scopingEntityIdList);
		
		udiList.add(udiOne);
		udiList.add(udiOne);
	}
	
	@Test
	public void testUDI() throws Exception{
		Assert.assertNotNull(patientUDIList);
	}

	@Test
	public void testUdiList(){
		setProcedureAct();
		Assert.assertEquals("UDI test case failed",udiList,patientUDIList);
	}

}
