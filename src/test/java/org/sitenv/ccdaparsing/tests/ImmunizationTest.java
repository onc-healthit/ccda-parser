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
import org.sitenv.ccdaparsing.model.CCDACode;
import org.sitenv.ccdaparsing.model.CCDAConsumable;
import org.sitenv.ccdaparsing.model.CCDADataElement;
import org.sitenv.ccdaparsing.model.CCDAEffTime;
import org.sitenv.ccdaparsing.model.CCDAII;
import org.sitenv.ccdaparsing.model.CCDAImmunization;
import org.sitenv.ccdaparsing.model.CCDAImmunizationActivity;
import org.sitenv.ccdaparsing.model.CCDAOrganization;
import org.sitenv.ccdaparsing.model.CCDAPQ;
import org.sitenv.ccdaparsing.processing.ImmunizationProcessor;
import org.w3c.dom.Document;

public class ImmunizationTest {
	
	private static String CCDA_DOC = "src/test/resources/170.315_b1_toc_amb_ccd_r21_sample1_v1.xml";
	private static CCDAImmunization immunizations;
	private ArrayList<CCDAII>    templateIds;
	private CCDACode  sectionCode;
	private static ArrayList<CCDAImmunizationActivity> immuActivities;
	private static ImmunizationProcessor immunizationProcessor = new ImmunizationProcessor();
	
	
	@BeforeClass
	public static void setUp() throws Exception {
		// removed fields to ensure no side effects with DocumentRoot
		DocumentBuilderFactory factory = 
				DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(CCDA_DOC));
		XPath xPath =  XPathFactory.newInstance().newXPath();
		immunizations = immunizationProcessor.retrieveImmunizationDetails(xPath, doc).get();
		
		immuActivities = new ArrayList<>();
		CCDAImmunizationActivity immuActivityOne = new CCDAImmunizationActivity();
		
		ArrayList<CCDAII> immuActivitiesTemplateIds = new ArrayList<CCDAII>();
		CCDAII immuActivitiesTemplateIdOne = new CCDAII();
		immuActivitiesTemplateIdOne.setRootValue("2.16.840.1.113883.10.20.22.4.52");
		immuActivitiesTemplateIdOne.setExtValue("2015-08-01");
		immuActivitiesTemplateIds.add(immuActivitiesTemplateIdOne);
		CCDAII immuActivitiesTemplateIdTwo = new CCDAII();
		immuActivitiesTemplateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.4.52");
		immuActivitiesTemplateIds.add(immuActivitiesTemplateIdTwo);
		
		immuActivityOne.setTemplateIds(immuActivitiesTemplateIds);
		immuActivityOne.setTime(new CCDAEffTime("20140510"));
		
		CCDACode routeCode = new CCDACode();
		routeCode.setCode("C28161");
		routeCode.setCodeSystem("2.16.840.1.113883.3.26.1.1");
		routeCode.setCodeSystemName("National Cancer Institute (NCI) Thesaurus");
		routeCode.setDisplayName("Intramuscular injection");
		immuActivityOne.setRouteCode(routeCode);
		
		CCDAPQ doseQuantity = new CCDAPQ();
		doseQuantity.setValue("50");
		doseQuantity.setUnits("ug");
		immuActivityOne.setDoseQuantity(doseQuantity);
		
		CCDAConsumable consumable = new CCDAConsumable();
		ArrayList<CCDAII> consumableTemplateIdList = new ArrayList<CCDAII>();
		CCDAII consumableTemplateIdOne = new CCDAII();
		consumableTemplateIdOne.setRootValue("2.16.840.1.113883.10.20.22.4.54");
		consumableTemplateIdOne.setExtValue("2014-06-09");
		consumableTemplateIdList.add(consumableTemplateIdOne);
		CCDAII consumableTemplateIdTwo = new CCDAII();
		consumableTemplateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.4.54");
		consumableTemplateIdList.add(consumableTemplateIdTwo);
		
		consumable.setTemplateIds(consumableTemplateIdList);
		
		CCDACode consumableMedCode = new CCDACode();
		consumableMedCode.setCode("88");
		consumableMedCode.setCodeSystem("2.16.840.1.113883.6.59");
		consumableMedCode.setCodeSystemName("CVX");
		consumableMedCode.setDisplayName("Influenza virus vaccine");
		
		consumable.setMedcode(consumableMedCode);
		consumable.setManufacturingOrg(new CCDADataElement("Immuno Inc."));
		consumable.setLotNumberText(new CCDADataElement("1"));
		
		immuActivityOne.setConsumable(consumable);
		
		CCDAOrganization representedOrg = new  CCDAOrganization();
		
		ArrayList<CCDAAddress> representedOrgAddressList = new ArrayList<>();
		CCDAAddress representedOrgAddress = new CCDAAddress();
		representedOrgAddress.setAddressLine1(new CCDADataElement("1007 Health Drive"));
		representedOrgAddress.setCity(new CCDADataElement("Portland"));
		representedOrgAddress.setState(new CCDADataElement("OR"));
		representedOrgAddress.setCountry(new CCDADataElement("US"));
		representedOrgAddress.setPostalCode(new CCDADataElement("99123"));
		representedOrgAddressList.add(representedOrgAddress);
		
		representedOrg.setAddress(representedOrgAddressList);
		
		ArrayList<CCDADataElement> representedOrgTelecomList = new ArrayList<>();
		CCDADataElement representedOrgTelecomOne = new CCDADataElement();
		representedOrgTelecomOne.setValue("tel: +1(555)555-1030");
		representedOrgTelecomOne.setUse("WP");
		String xmlstring = "<telecom xmlns:xsi=\\\"http://www.w3.org/2001/XMLSchema-instance\\\" use=\\\"WP\\\" value=\\\"tel: +1(555)555-1030\\\"/>";
		representedOrgTelecomOne.setXmlString(xmlstring.replaceAll("\\\\", ""));
		
		representedOrgTelecomList.add(representedOrgTelecomOne);
		
		representedOrg.setTelecom(representedOrgTelecomList);
		
		ArrayList<CCDADataElement> names = new ArrayList<>();
		CCDADataElement name = new CCDADataElement("Good Health Clinic");
		names.add(name);
		representedOrg.setNames(names);
		
		immuActivityOne.setOrganization(representedOrg);
		immuActivities.add(immuActivityOne);
		
		CCDAImmunizationActivity immuActivityTwo = new CCDAImmunizationActivity();
		
		immuActivityTwo.setTemplateIds(immuActivitiesTemplateIds);
		immuActivityTwo.setTime(new CCDAEffTime("20120104"));
		immuActivityTwo.setRouteCode(routeCode);
		immuActivityTwo.setDoseQuantity(doseQuantity);
		
		CCDAConsumable consumableTwo = new CCDAConsumable();
		consumableTwo.setTemplateIds(consumableTemplateIdList);
		
		CCDACode consumableMedCodeTwo = new CCDACode();
		consumableMedCodeTwo.setCode("103");
		consumableMedCodeTwo.setCodeSystem("2.16.840.1.113883.6.59");
		consumableMedCodeTwo.setCodeSystemName("CVX");
		consumableMedCodeTwo.setDisplayName("Tetanus and diphtheria toxoids");
		
		consumableTwo.setMedcode(consumableMedCodeTwo);
		consumableTwo.setManufacturingOrg(new CCDADataElement("Immuno Inc."));
		consumableTwo.setLotNumberText(new CCDADataElement("2"));
		
		immuActivityTwo.setConsumable(consumableTwo);
		
		CCDAOrganization representedOrgTwo = new  CCDAOrganization();
		representedOrgTwo.setTelecom(representedOrgTelecomList);
		representedOrgTwo.setNames(names);
		
		immuActivityTwo.setOrganization(representedOrgTwo);
		immuActivities.add(immuActivityTwo);
		
		
		CCDAImmunizationActivity immuActivityThree = new CCDAImmunizationActivity();
		
		immuActivityThree.setTemplateIds(immuActivitiesTemplateIds);
		immuActivityThree.setTime(new CCDAEffTime("20150622"));
		immuActivityThree.setRouteCode(routeCode);
		
		CCDAConsumable consumableThree = new CCDAConsumable();
		consumableThree.setTemplateIds(consumableTemplateIdList);
		
		CCDACode consumableMedCodeThree = new CCDACode();
		consumableMedCodeThree.setCode("166");
		consumableMedCodeThree.setCodeSystem("2.16.840.1.113883.12.292");
		consumableMedCodeThree.setCodeSystemName("CVX");
		consumableMedCodeThree.setDisplayName("influenza, intradermal, quadrivalent, preservative free");
		
		consumableThree.setMedcode(consumableMedCodeThree);
		consumableThree.setManufacturingOrg(new CCDADataElement("Immuno Inc."));
		consumableThree.setLotNumberText(new CCDADataElement("1"));
		
		immuActivityThree.setConsumable(consumableThree);
		immuActivities.add(immuActivityThree);
	}
	
	private void setEncounterSectionCode()
	 {
		sectionCode = new CCDACode();
		sectionCode.setCode("11369-6");
		sectionCode.setCodeSystem("2.16.840.1.113883.6.1");
		sectionCode.setCodeSystemName("LOINC");
		sectionCode.setDisplayName("History of immunizations");
	 }
	
	private void setEncounterTemplateIds()
	 {
		templateIds = new ArrayList<CCDAII>();
		CCDAII templateIdOne = new CCDAII();
		templateIdOne.setRootValue("2.16.840.1.113883.10.20.22.2.2.1");
		templateIdOne.setExtValue("2014-06-09");
		templateIds.add(templateIdOne);
		CCDAII templateIdTwo = new CCDAII();
		templateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.2.2.1");
		templateIds.add(templateIdTwo);
	 }
	
	
	@Test
	public void testImmunization() throws Exception{
		Assert.assertNotNull(immunizations);
	}

	@Test
	public void testImmunizationSectionCode(){
		setEncounterSectionCode();
		Assert.assertEquals("Immunization SectionCode test case failed",sectionCode,immunizations.getSectionCode());
	}
	
	@Test
	public void testImmunizationTemplateIds(){
		setEncounterTemplateIds();
		Assert.assertEquals("Immunization TemplateID test case failed",templateIds,immunizations.getTemplateIds());
	}
	
	@Test
	public void testImmunizationActivities(){
		Assert.assertEquals("Immunization Activity test case failed",immuActivities,immunizations.getImmActivity());
	}
	
	@Test
	public void testImmunizationActivitiesTemplateId(){
		Assert.assertEquals("Immunization Activity Template Ids test case failed",immuActivities.get(0).getTemplateIds(),immunizations.getImmActivity().get(0).getTemplateIds());
	}
	
	@Test
	public void testImmunizationActivitiesTime(){
		Assert.assertEquals("Immunization Activity time test case failed",immuActivities.get(0).getTime(),immunizations.getImmActivity().get(0).getTime());
	}
	
	@Test
	public void testImmunizationRouteCode(){
		Assert.assertEquals("Immunization Activity route code test case failed",immuActivities.get(0).getRouteCode(),immunizations.getImmActivity().get(0).getRouteCode());
	}
	
	@Test
	public void testImmunizationAppraochsiteCode(){
		Assert.assertEquals("Immunization Activity appraochsite code test case failed",immuActivities.get(0).getApproachSiteCode(),
									immunizations.getImmActivity().get(0).getApproachSiteCode());
	}
	
	@Test
	public void testImmunizationDoseQuantity(){
		Assert.assertEquals("Immunization Activity dose quantity test case failed",immuActivities.get(0).getDoseQuantity(),
									immunizations.getImmActivity().get(0).getDoseQuantity());
	}
	
	@Test
	public void testImmunizationAdminUnitCode(){
		Assert.assertEquals("Immunization Activity Admin unit code test case failed",immuActivities.get(0).getAdminUnitCode(),
									immunizations.getImmActivity().get(0).getAdminUnitCode());
	}
	
	@Test
	public void testImmunizationConsumable(){
		Assert.assertEquals("Immunization Activity consumable test case failed",immuActivities.get(0).getConsumable(),
									immunizations.getImmActivity().get(0).getConsumable());
	}
	
	@Test
	public void testImmunizationOrganization(){
		Assert.assertEquals("Immunization Activity organization test case failed",immuActivities.get(0).getOrganization(),
									immunizations.getImmActivity().get(0).getOrganization());
	}
	
	@Test
	public void testImmunizationConsumableTemplateIds(){
		Assert.assertEquals("Immunization Activity consumable  template ids test case failed",immuActivities.get(0).getConsumable().getTemplateIds(),
									immunizations.getImmActivity().get(0).getConsumable().getTemplateIds());
	}
	
	@Test
	public void testImmunizationConsumableMedcode(){
		Assert.assertEquals("Immunization Activity consumable medcode test case failed",immuActivities.get(0).getConsumable().getMedcode(),
									immunizations.getImmActivity().get(0).getConsumable().getMedcode());
	}
	
	@Test
	public void testImmunizationConsumableTranslations(){
		Assert.assertEquals("Immunization Activity consumable translations test case failed",immuActivities.get(0).getConsumable().getTranslations(),
									immunizations.getImmActivity().get(0).getConsumable().getTranslations());
	}
	
	@Test
	public void testImmunizationConsumableLotNumberText(){
		Assert.assertEquals("Immunization Activity consumable lot number text test case failed",immuActivities.get(0).getConsumable().getLotNumberText(),
									immunizations.getImmActivity().get(0).getConsumable().getLotNumberText());
	}
	
	@Test
	public void testImmunizationConsumableManufacturingOrg(){
		Assert.assertEquals("Immunization Activity consumable lot number text test case failed",immuActivities.get(0).getConsumable().getManufacturingOrg(),
									immunizations.getImmActivity().get(0).getConsumable().getManufacturingOrg());
	}
	
	@Test
	public void testImmunizationOrganizationName(){
		Assert.assertEquals("Immunization Activity organization name test case failed",immuActivities.get(0).getOrganization().getNames(),
									immunizations.getImmActivity().get(0).getOrganization().getNames());
	}
	
	@Test
	public void testImmunizationOrganizationAddress(){
		Assert.assertEquals("Immunization Activity organization Address test case failed",immuActivities.get(0).getOrganization().getAddress(),
									immunizations.getImmActivity().get(0).getOrganization().getAddress());
	}
	
	@Test
	public void testImmunizationOrganizationTelecom(){
		Assert.assertEquals("Immunization Activity organization Telecom test case failed",immuActivities.get(0).getOrganization().getTelecom(),
									immunizations.getImmActivity().get(0).getOrganization().getTelecom());
	}

}
