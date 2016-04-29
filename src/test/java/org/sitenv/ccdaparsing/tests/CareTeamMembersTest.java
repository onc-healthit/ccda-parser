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
import org.sitenv.ccdaparsing.model.CCDACareTeamMember;
import org.sitenv.ccdaparsing.model.CCDADataElement;
import org.sitenv.ccdaparsing.model.CCDAParticipant;
import org.sitenv.ccdaparsing.processing.CareTeamMemberProcessor;
import org.w3c.dom.Document;

public class CareTeamMembersTest {
	
	private static String CCDA_DOC = "src/test/resources/170.315_b1_toc_amb_ccd_r21_sample1_v1.xml";
	private static CCDACareTeamMember careTeamMember;
	private static ArrayList<CCDAParticipant> members;
	
	@BeforeClass
	public static void setUp() throws Exception {
		// removed fields to ensure no side effects with DocumentRoot
		DocumentBuilderFactory factory = 
				DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(CCDA_DOC));
		XPath xPath =  XPathFactory.newInstance().newXPath();
		careTeamMember = CareTeamMemberProcessor.retrieveCTMDetails(xPath, doc);
		
		members = new ArrayList<CCDAParticipant>();
		CCDAParticipant memberOne = new CCDAParticipant();
		CCDAAddress memberOneAddress = new CCDAAddress();
		
		memberOneAddress.setAddressLine1(new CCDADataElement("2472 Rocky place"));
		memberOneAddress.setCity(new CCDADataElement("Beaverton"));
		memberOneAddress.setCountry(new CCDADataElement("US"));
		memberOneAddress.setPostalCode(new CCDADataElement("97006"));
		memberOneAddress.setState(new CCDADataElement("OR"));
		
		memberOne.setAddress(memberOneAddress);
		memberOne.setFirstName(new CCDADataElement("Albert"));
		memberOne.setLastName(new CCDADataElement("Davis"));
		
		CCDADataElement memeberOneTelecom = new CCDADataElement();
		memeberOneTelecom.setUse("WP");
		memeberOneTelecom.setValue("tel:+1(555)-555-1002");
		
		memberOne.setTelecom(memeberOneTelecom);
		
		CCDAParticipant memberTwo = new CCDAParticipant();
		
		memberTwo.setAddress(memberOneAddress);
		memberTwo.setFirstName(new CCDADataElement("Tracy"));
		memberTwo.setLastName(new CCDADataElement("Davis"));
		memberTwo.setTelecom(memeberOneTelecom);
		
		members.add(memberOne);
		members.add(memberTwo);
	}
	
	@Test
	public void testCareTeamMembers() throws Exception{
		Assert.assertNotNull(careTeamMember);
	}

	@Test
	public void testCareTeamMembersCount() throws Exception{
		Assert.assertEquals(" Care Team Members Count test case failed",careTeamMember.getMembers().size(),members.size());
	}
	
	@Test
	public void testCareTeamMembersValue() throws Exception{
		Assert.assertEquals(" Care Team Members Value test case failed",careTeamMember.getMembers(),members);
	}
	
	@Test
	public void testCareTeamMemberAddress() throws Exception{
		Assert.assertEquals(" Care Team Member Address test case failed",careTeamMember.getMembers().get(0).getAddress(),members.get(0).getAddress());
	}
	
	@Test
	public void testCareTeamMemberAddressLineOne() throws Exception{
		Assert.assertEquals(" Care Team Member Address Line one test case failed",careTeamMember.getMembers().get(0).getAddress().getAddressLine1(),
										members.get(0).getAddress().getAddressLine1());
	}
	
	@Test
	public void testCareTeamMemberCity() throws Exception{
		Assert.assertEquals(" Care Team Member Address City test case failed",careTeamMember.getMembers().get(0).getAddress().getCity(),
										members.get(0).getAddress().getCity());
	}
	
	@Test
	public void testCareTeamMemberState() throws Exception{
		Assert.assertEquals(" Care Team Member Address State test case failed",careTeamMember.getMembers().get(0).getAddress().getState(),
												members.get(0).getAddress().getState());
	}
	
	@Test
	public void testCareTeamMemberPostalcode() throws Exception{
		Assert.assertEquals(" Care Team Member Address Postalcode test case failed",careTeamMember.getMembers().get(0).getAddress().getPostalCode(),
												members.get(0).getAddress().getPostalCode());
	}

	@Test
	public void testCareTeamMemberFirstName() throws Exception{
		Assert.assertEquals(" Care Team Member First Name test case failed",careTeamMember.getMembers().get(0).getFirstName(),members.get(0).getFirstName());
	}
	
	@Test
	public void testCareTeamMemberLastName() throws Exception{
		Assert.assertEquals(" Care Team Member Last Name test case failed",careTeamMember.getMembers().get(0).getLastName(),members.get(0).getLastName());
	}

	@Test
	public void testCareTeamMemberTelecom() throws Exception{
		Assert.assertEquals(" Care Team Member Telecom test case failed",careTeamMember.getMembers().get(0).getTelecom(),members.get(0).getTelecom());
	}
	

}
