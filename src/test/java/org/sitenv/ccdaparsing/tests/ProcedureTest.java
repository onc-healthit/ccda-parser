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
import org.sitenv.ccdaparsing.model.CCDAAddress;
import org.sitenv.ccdaparsing.model.CCDAAssignedEntity;
import org.sitenv.ccdaparsing.model.CCDACode;
import org.sitenv.ccdaparsing.model.CCDADataElement;
import org.sitenv.ccdaparsing.model.CCDAII;
import org.sitenv.ccdaparsing.model.CCDAOrganization;
import org.sitenv.ccdaparsing.model.CCDAProcActProc;
import org.sitenv.ccdaparsing.model.CCDAProcedure;
import org.sitenv.ccdaparsing.model.CCDAUDI;
import org.sitenv.ccdaparsing.processing.ProcedureProcessor;
import org.w3c.dom.Document;

public class ProcedureTest {
	
	private String CCDA_DOC = "src/test/resources/170.315_b1_toc_amb_ccd_r21_sample1_v1.xml";
	private CCDAProcedure procedures;
	private ArrayList<CCDAII>    templateIds;
	private CCDACode  sectionCode;
	private ArrayList<CCDAProcActProc>	procActsProcs;
	private ProcedureProcessor procedureProcessor = new ProcedureProcessor();
	
	@BeforeClass
	public void setUp() throws Exception {
		// removed fields to ensure no side effects with DocumentRoot
		DocumentBuilderFactory factory = 
				DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(CCDA_DOC));
		XPath xPath =  XPathFactory.newInstance().newXPath();
		procedures = procedureProcessor.retrievePrcedureDetails(xPath, doc).get();
		
		procActsProcs = new ArrayList<>();
		
		CCDAProcActProc procedureActOne  = new CCDAProcActProc();
		ArrayList<CCDAII> procedureActTemplateIds = new ArrayList<CCDAII>();
		CCDAII procedureActTemplateIdOne = new CCDAII();
		procedureActTemplateIdOne.setRootValue("2.16.840.1.113883.10.20.22.4.14");
		procedureActTemplateIdOne.setExtValue("2014-06-09");
		procedureActTemplateIds.add(procedureActTemplateIdOne);
		CCDAII procedureActTemplateIdTwo = new CCDAII();
		procedureActTemplateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.4.14");
		procedureActTemplateIds.add(procedureActTemplateIdTwo);
		
		procedureActOne.setSectionTemplateId(procedureActTemplateIds);
		
		CCDACode procedureCode = new CCDACode();
		procedureCode.setCode("704708004");
		procedureCode.setCodeSystem("2.16.840.1.113883.6.96");
		procedureCode.setCodeSystemName("SNOMED CT");
		procedureCode.setDisplayName("Cardiac resynchronization therapy implantable pacemaker");
		procedureActOne.setProcCode(procedureCode);
		
		CCDACode statusCode = new CCDACode();
		statusCode.setCode("completed");
		procedureActOne.setProcStatus(statusCode);
		
		CCDACode targetSiteCode = new CCDACode();
		targetSiteCode.setCode("9454009");
		targetSiteCode.setCodeSystem("2.16.840.1.113883.6.96");
		targetSiteCode.setCodeSystemName("SNOMED CT");
		targetSiteCode.setDisplayName("Structure of subclavian vein");
		
		procedureActOne.setTargetSiteCode(targetSiteCode);
		
		ArrayList<CCDAAssignedEntity> performerList = new ArrayList<>();
		CCDAAssignedEntity performer = new CCDAAssignedEntity();
		
		CCDAOrganization representedOrg = new  CCDAOrganization();
		
		ArrayList<CCDAAddress> representedOrgAddressList = new ArrayList<>();
		CCDAAddress representedOrgAddress = new CCDAAddress();
		representedOrgAddress.setAddressLine1(new CCDADataElement("3525 Newberry Avenue"));
		representedOrgAddress.setCity(new CCDADataElement("Beaverton"));
		representedOrgAddress.setState(new CCDADataElement("OR"));
		representedOrgAddress.setCountry(new CCDADataElement("US"));
		representedOrgAddress.setPostalCode(new CCDADataElement("97006"));
		representedOrgAddress.setPostalAddressUse("WP");
		representedOrgAddressList.add(representedOrgAddress);
		
		representedOrg.setAddress(representedOrgAddressList);
		
		ArrayList<CCDADataElement> representedOrgTelecomList = new ArrayList<>();
		CCDADataElement representedOrgTelecomOne = new CCDADataElement();
		representedOrgTelecomOne.setValue("tel:+1(555)-555-5000");
		representedOrgTelecomOne.setUse("WP");
		representedOrgTelecomList.add(representedOrgTelecomOne);
		
		representedOrg.setTelecom(representedOrgTelecomList);
		
		ArrayList<CCDADataElement> names = new ArrayList<>();
		CCDADataElement name = new CCDADataElement("Community Health Hospitals");
		names.add(name);
		representedOrg.setNames(names);
		
		performer.setAddresses(representedOrgAddressList);
		performer.setOrganization(representedOrg);
		performer.setTelecom(representedOrgTelecomList);
		
		performerList.add(performer);
		
		procedureActOne.setPerformer(performerList);
		
		ArrayList<CCDAUDI> udiList = new ArrayList<>();
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
		
		procedureActOne.setPatientUDI(udiList);
		
		procActsProcs.add(procedureActOne);
	}
	
	private void setProcedureSectionCode()
	{
		sectionCode = new CCDACode();
		sectionCode.setCode("47519-4");
		sectionCode.setCodeSystem("2.16.840.1.113883.6.1");
		sectionCode.setCodeSystemName("LOINC");
		sectionCode.setDisplayName("HISTORY OF PROCEDURES");
	}
	
	private void setProcedureTemplateIds()
	{
		templateIds = new ArrayList<CCDAII>();
		CCDAII templateIdOne = new CCDAII();
		templateIdOne.setRootValue("2.16.840.1.113883.10.20.22.2.7.1");
		templateIdOne.setExtValue("2014-06-09");
		templateIds.add(templateIdOne);
		CCDAII templateIdTwo = new CCDAII();
		templateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.2.7.1");
		templateIds.add(templateIdTwo);
	}
	
	
	@Test
	public void testProcedures() throws Exception{
		Assert.assertNotNull(procedures);
	}

	@Test
	public void testProceduresSectionCode(){
		setProcedureSectionCode();
		Assert.assertEquals("Procedures  SectionCode test case failed",sectionCode,procedures.getSectionCode());
	}
	
	@Test
	public void testProceduresTeamplateId(){
		setProcedureTemplateIds();
		Assert.assertEquals("Procedures  teamplet Id test case failed",templateIds,procedures.getSectionTemplateId());
	}
	
	@Test
	public void testProcedureActivity(){
		Assert.assertEquals("Procedures Activity Code test case failed",procActsProcs.get(0),procedures.getProcActsProcs().get(2));
	}
	
	@Test
	public void testProcedureActivityTemplateId(){
		Assert.assertEquals("Procedures Activity Template Id test case failed",procActsProcs.get(0).getSectionTemplateId(),
				procedures.getProcActsProcs().get(2).getSectionTemplateId());
	}
	
	@Test
	public void testProcedureActivityCode(){
		Assert.assertEquals("Procedures Activity Code test case failed",procActsProcs.get(0).getProcCode(),procedures.getProcActsProcs().get(2).getProcCode());
	}
	
	@Test
	public void testProcedureActivityStatusCode(){
		Assert.assertEquals("Procedures Activity status code test case failed",procActsProcs.get(0).getProcStatus(),procedures.getProcActsProcs().get(2).getProcStatus());
	}
	
	@Test
	public void testProcedureActivityTargetSiteCode(){
		Assert.assertEquals("Procedures Activity Target site code test case failed",procActsProcs.get(0).getTargetSiteCode(),
				procedures.getProcActsProcs().get(2).getTargetSiteCode());
	}
	
	@Test
	public void testProcedureActivityPerformer(){
		Assert.assertEquals("Procedures Activity performer test case failed",procActsProcs.get(0).getPerformer(),procedures.getProcActsProcs().get(2).getPerformer());
	}
	
	@Test
	public void testProcedureActivityPerformerAddress(){
		Assert.assertEquals("Procedures Activity performer Address test case failed",procActsProcs.get(0).getPerformer().get(0).getAddresses(),
									procedures.getProcActsProcs().get(2).getPerformer().get(0).getAddresses());
	}
	
	@Test
	public void testProcedureActivityPerformerTelecom(){
		Assert.assertEquals("Procedures Activity performer Telecom test case failed",procActsProcs.get(0).getPerformer().get(0).getTelecom(),
									procedures.getProcActsProcs().get(2).getPerformer().get(0).getTelecom());
	}
	
	@Test
	public void testProcedureActivityPerformerOrganization(){
		Assert.assertEquals("Procedures Activity performer Organization test case failed",procActsProcs.get(0).getPerformer().get(0).getOrganization(),
									procedures.getProcActsProcs().get(2).getPerformer().get(0).getOrganization());
	}
	
	@Test
	public void testProcedureActivityPerformerOrganizationName(){
		Assert.assertEquals("Procedures Activity performer Organization Names test case failed",procActsProcs.get(0).getPerformer().get(0).getOrganization().getNames(),
									procedures.getProcActsProcs().get(2).getPerformer().get(0).getOrganization().getNames());
	}
	
	@Test
	public void testProcedureActivityPerformerOrganizationAddress(){
		Assert.assertEquals("Procedures Activity performer Organization address test case failed",procActsProcs.get(0).getPerformer().get(0).getOrganization().getAddress(),
									procedures.getProcActsProcs().get(2).getPerformer().get(0).getOrganization().getAddress());
	}
	
	@Test
	public void testProcedureActivityPerformerOrganizationTelecom(){
		Assert.assertEquals("Procedures Activity performer Organization telecom test case failed",procActsProcs.get(0).getPerformer().get(0).getOrganization().getTelecom(),
									procedures.getProcActsProcs().get(2).getPerformer().get(0).getOrganization().getTelecom());
	}
	
	@Test
	public void testProcedureActivityPatientUDI(){
		Assert.assertEquals("Procedures Activity Patient UDI test case failed",procActsProcs.get(0).getPatientUDI(),procedures.getProcActsProcs().get(2).getPatientUDI());
	}
	

}
