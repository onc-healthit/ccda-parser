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
import org.sitenv.ccdaparsing.model.CCDAConsumable;
import org.sitenv.ccdaparsing.model.CCDADataElement;
import org.sitenv.ccdaparsing.model.CCDAEffTime;
import org.sitenv.ccdaparsing.model.CCDAFrequency;
import org.sitenv.ccdaparsing.model.CCDAII;
import org.sitenv.ccdaparsing.model.CCDAMedication;
import org.sitenv.ccdaparsing.model.CCDAMedicationActivity;
import org.sitenv.ccdaparsing.model.CCDAPQ;
import org.sitenv.ccdaparsing.processing.MedicationProcessor;
import org.w3c.dom.Document;

public class MedicationTest {
	
	private static String CCDA_DOC = "src/test/resources/170.315_b1_toc_amb_ccd_r21_sample1_v1.xml";
	private static CCDAMedication medication;
	private ArrayList<CCDAII>    templateIds;
	private CCDACode  sectionCode;
	private ArrayList<CCDAMedicationActivity> medActivities;
	
	
	@BeforeClass
	public static void setUp() throws Exception {
		// removed fields to ensure no side effects with DocumentRoot
		DocumentBuilderFactory factory = 
				DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(CCDA_DOC));
		XPath xPath =  XPathFactory.newInstance().newXPath();
		medication = MedicationProcessor.retrieveMedicationDetails(xPath, doc);
	}
	
	private void setMedicationSectionCode()
	{
		sectionCode = new CCDACode();
		sectionCode.setCode("10160-0");
		sectionCode.setCodeSystem("2.16.840.1.113883.6.1");
		sectionCode.setCodeSystemName("LOINC");
		sectionCode.setDisplayName("HISTORY OF MEDICATION USE");
	}
	
	private void setMedicationTemplateIds()
	{
		templateIds = new ArrayList<CCDAII>();
		CCDAII templateIdOne = new CCDAII();
		templateIdOne.setRootValue("2.16.840.1.113883.10.20.22.2.1.1");
		templateIdOne.setExtValue("2014-06-09");
		templateIds.add(templateIdOne);
		CCDAII templateIdTwo = new CCDAII();
		templateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.2.1.1");
		templateIds.add(templateIdTwo);
	}
	
	private void setMedicationActivities()
	{
		
		medActivities = new ArrayList<>();
		
		CCDAMedicationActivity medActivitiyOne  = new CCDAMedicationActivity();
		ArrayList<CCDAII> medicationActTemplateIds = new ArrayList<CCDAII>();
		CCDAII medicationActTemplateIdOne = new CCDAII();
		medicationActTemplateIdOne.setRootValue("2.16.840.1.113883.10.20.22.4.16");
		medicationActTemplateIdOne.setExtValue("2014-06-09");
		medicationActTemplateIds.add(medicationActTemplateIdOne);
		CCDAII medicationActTemplateIdTwo = new CCDAII();
		medicationActTemplateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.4.16");
		medicationActTemplateIds.add(medicationActTemplateIdTwo);
		
		medActivitiyOne.setTemplateIds(medicationActTemplateIds);
		
		CCDACode routeCode = new CCDACode();
		routeCode.setCode("C38276");
		routeCode.setCodeSystem("2.16.840.1.113883.3.26.1.1");
		routeCode.setCodeSystemName("NCI Thesaurus");
		routeCode.setDisplayName("INTRAVENOUS");
		medActivitiyOne.setRouteCode(routeCode);
		
		CCDAPQ doseQuantity = new CCDAPQ();
		doseQuantity.setValue("250");
		doseQuantity.setUnits("mg/mL");
		medActivitiyOne.setDoseQuantity(doseQuantity);
		
		CCDAPQ rateQuantity = new CCDAPQ();
		rateQuantity.setValue("2");
		rateQuantity.setUnits("ml/min");
		medActivitiyOne.setRateQuantity(rateQuantity);
		
		CCDAEffTime duration = new CCDAEffTime();
		duration.setLow(new CCDADataElement("20150622"));
		medActivitiyOne.setDuration(duration);
		
		CCDAFrequency frequency = new CCDAFrequency();
		frequency.setUnit("h");
		frequency.setValue("12");
		frequency.setInstitutionSpecified(true);
		frequency.setOperator("A");
		medActivitiyOne.setFrequency(frequency);
		
		CCDAConsumable consumable = new CCDAConsumable();
		ArrayList<CCDAII> consumableTemplateIdList = new ArrayList<CCDAII>();
		CCDAII consumableTemplateIdOne = new CCDAII();
		consumableTemplateIdOne.setRootValue("2.16.840.1.113883.10.20.22.4.23");
		consumableTemplateIdOne.setExtValue("2014-06-09");
		consumableTemplateIdList.add(consumableTemplateIdOne);
		CCDAII consumableTemplateIdTwo = new CCDAII();
		consumableTemplateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.4.23");
		consumableTemplateIdList.add(consumableTemplateIdTwo);
		
		consumable.setTemplateIds(consumableTemplateIdList);
		
		CCDACode consumableMedCode = new CCDACode();
		consumableMedCode.setCode("563973");
		consumableMedCode.setCodeSystem("2.16.840.1.113883.6.88");
		consumableMedCode.setDisplayName("Ceftriaxone 250MG/ML");
		
		consumable.setMedcode(consumableMedCode);
		
		medActivitiyOne.setConsumable(consumable);
		
		medActivities.add(medActivitiyOne);
	}
	
	@Test
	public void testMedication() throws Exception{
		Assert.assertNotNull(medication);
	}

	@Test
	public void testMedicationSectionCode(){
		setMedicationSectionCode();
		Assert.assertEquals("Medication  SectionCode test case failed",sectionCode,medication.getSectionCode());
	}
	
	@Test
	public void testMedicationTemplateIds(){
		setMedicationTemplateIds();
		Assert.assertEquals("Medication  teamplet Id test case failed",templateIds,medication.getTemplateIds());
	}
	
	@Test
	public void testMedicationAct(){
		setMedicationActivities();
		Assert.assertEquals("Medication Activity test case failed",medActivities.get(0),medication.getMedActivities().get(0));
	}
	
	@Test
	public void testMedicationActTemplateIds(){
		setMedicationActivities();
		Assert.assertEquals("Medication Activity Teamplate Id test case failed",medActivities.get(0).getTemplateIds(),medication.getMedActivities().get(0).getTemplateIds());
	}
	
	@Test
	public void testMedicationActRouteCode(){
		setMedicationActivities();
		Assert.assertEquals("Medication Activity Route Code test case failed",medActivities.get(0).getRouteCode(),medication.getMedActivities().get(0).getRouteCode());
	}
	
	@Test
	public void testMedicationActDoseQuantity(){
		setMedicationActivities();
		Assert.assertEquals("Medication Activity Dose Quantity test case failed",medActivities.get(0).getDoseQuantity(),medication.getMedActivities().get(0).getDoseQuantity());
	}
	
	@Test
	public void testMedicationActRateQuantity(){
		setMedicationActivities();
		Assert.assertEquals("Medication Activity Rate Quantity test case failed",medActivities.get(0).getRateQuantity(),medication.getMedActivities().get(0).getRateQuantity());
	}
	
	@Test
	public void testMedicationActDuration(){
		setMedicationActivities();
		Assert.assertEquals("Medication Activity duration test case failed",medActivities.get(0).getDuration(),medication.getMedActivities().get(0).getDuration());
	}
	
	@Test
	public void testMedicationActFrequency(){
		setMedicationActivities();
		Assert.assertEquals("Medication Activity frequency test case failed",medActivities.get(0).getFrequency(),medication.getMedActivities().get(0).getFrequency());
	}
	
	@Test
	public void testMedicationActConsumable(){
		setMedicationActivities();
		Assert.assertEquals("Medication Activity consumable test case failed",medActivities.get(0).getConsumable(),medication.getMedActivities().get(0).getConsumable());
	}

}
