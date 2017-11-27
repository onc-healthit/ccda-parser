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
import org.sitenv.ccdaparsing.model.CCDAAllergy;
import org.sitenv.ccdaparsing.model.CCDAAllergyConcern;
import org.sitenv.ccdaparsing.model.CCDAAllergyObs;
import org.sitenv.ccdaparsing.model.CCDAAllergyReaction;
import org.sitenv.ccdaparsing.model.CCDAAllergySeverity;
import org.sitenv.ccdaparsing.model.CCDACode;
import org.sitenv.ccdaparsing.model.CCDADataElement;
import org.sitenv.ccdaparsing.model.CCDAEffTime;
import org.sitenv.ccdaparsing.model.CCDAII;
import org.sitenv.ccdaparsing.processing.MediactionAllergiesProcessor;
import org.w3c.dom.Document;

public class MedicationAllergies {
	
	private String CCDA_DOC = "src/test/resources/170.315_b1_toc_amb_ccd_r21_sample1_v1.xml";
	private CCDAAllergy allergies;
	private ArrayList<CCDAII>    templateIds;
	private CCDACode  sectionCode;
	private ArrayList<CCDAAllergyConcern> allergyConcernList;
	private MediactionAllergiesProcessor mediactionAllergiesProcessor = new MediactionAllergiesProcessor();
	
	
	@BeforeClass
	public void setUp() throws Exception {
		// removed fields to ensure no side effects with DocumentRoot
		DocumentBuilderFactory factory = 
				DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(CCDA_DOC));
		XPath xPath =  XPathFactory.newInstance().newXPath();
		allergies = mediactionAllergiesProcessor.retrieveAllergiesDetails(xPath, doc).get();
		
		allergyConcernList = new ArrayList<>();
		CCDAAllergyConcern allergyConcern = new CCDAAllergyConcern();
		
		ArrayList<CCDAII> allergyConcernTemplateIds = new ArrayList<CCDAII>();
		CCDAII allergyConcernTemplateIdOne = new CCDAII();
		allergyConcernTemplateIdOne.setRootValue("2.16.840.1.113883.10.20.22.4.30");
		allergyConcernTemplateIdOne.setExtValue("2015-08-01");
		allergyConcernTemplateIds.add(allergyConcernTemplateIdOne);
		CCDAII allergyConcernTemplateIdTwo = new CCDAII();
		allergyConcernTemplateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.4.30");
		allergyConcernTemplateIds.add(allergyConcernTemplateIdTwo);
		
		allergyConcern.setTemplateId(allergyConcernTemplateIds);
		
		CCDACode concernCode = new CCDACode();
		concernCode.setCode("CONC");
		concernCode.setCodeSystem("2.16.840.1.113883.5.6");
		allergyConcern.setConcernCode(concernCode);
		
		CCDACode statusCode = new CCDACode();
		statusCode.setCode("active");
		allergyConcern.setStatusCode(statusCode);
		
		CCDAEffTime effectiveTime = new CCDAEffTime();
		effectiveTime.setLow(new CCDADataElement("19800501"));
		effectiveTime.setLowPresent(true);
		effectiveTime.setHighPresent(false);
		
		allergyConcern.setEffTime(effectiveTime);
		
		ArrayList<CCDAAllergyObs> allergyObservationList = new ArrayList<>();
		CCDAAllergyObs allergyObservationOne = new CCDAAllergyObs();
		
		ArrayList<CCDAII> allergyObservationTemplateIds = new ArrayList<CCDAII>();
		CCDAII allergyObservationTemplateIdOne = new CCDAII();
		allergyObservationTemplateIdOne.setRootValue("2.16.840.1.113883.10.20.22.4.7");
		allergyObservationTemplateIdOne.setExtValue("2014-06-09");
		allergyObservationTemplateIds.add(allergyObservationTemplateIdOne);
		CCDAII allergyObservationTemplateIdTwo = new CCDAII();
		allergyObservationTemplateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.4.7");
		allergyObservationTemplateIds.add(allergyObservationTemplateIdTwo);
		
		allergyObservationOne.setTemplateId(allergyObservationTemplateIds);
		
		CCDACode intoleranceType = new CCDACode();
		intoleranceType.setCode("419511003");
		intoleranceType.setCodeSystem("2.16.840.1.113883.6.96");
		intoleranceType.setCodeSystemName("SNOMED CT");
		intoleranceType.setDisplayName("Propensity to adverse reaction to drug");
		intoleranceType.setXpath("CD");
		
		allergyObservationOne.setAllergyIntoleranceType(intoleranceType);
		
		CCDACode allergySubstance = new CCDACode();
		allergySubstance.setCode("7982");
		allergySubstance.setCodeSystem("2.16.840.1.113883.6.88");
		allergySubstance.setCodeSystemName("RxNorm");
		allergySubstance.setDisplayName("Penicillin G benzathine");
		
		allergyObservationOne.setAllergySubstance(allergySubstance);
		
		CCDAEffTime obsEffectiveTime = new CCDAEffTime();
		obsEffectiveTime.setLow(new CCDADataElement("19980501"));
		obsEffectiveTime.setLowPresent(true);
		obsEffectiveTime.setHighPresent(false);
		allergyObservationOne.setEffTime(obsEffectiveTime);
		
		ArrayList<CCDAAllergyReaction> allergyReactionList = new ArrayList<>();
		CCDAAllergyReaction allergyReactionOne = new CCDAAllergyReaction();
		
		ArrayList<CCDAII> allergyReactionTemplateIds = new ArrayList<CCDAII>();
		CCDAII allergyReactionTemplateIdOne = new CCDAII();
		allergyReactionTemplateIdOne.setRootValue("2.16.840.1.113883.10.20.22.4.9");
		allergyReactionTemplateIdOne.setExtValue("2014-06-09");
		allergyReactionTemplateIds.add(allergyReactionTemplateIdOne);
		CCDAII allergyReactionTemplateIdTwo = new CCDAII();
		allergyReactionTemplateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.4.9");
		allergyReactionTemplateIds.add(allergyReactionTemplateIdTwo);
		
		allergyReactionOne.setTemplateIds(allergyReactionTemplateIds);
		
		CCDACode reactionCode = new CCDACode();
		reactionCode.setCode("247472004");
		reactionCode.setCodeSystem("2.16.840.1.113883.6.96");
		reactionCode.setDisplayName("Hives");
		reactionCode.setXpath("CD");
		
		allergyReactionOne.setReactionCode(reactionCode);
		allergyReactionList.add(allergyReactionOne);
		
		allergyObservationOne.setReactions(allergyReactionList);
		
		CCDAAllergySeverity allergySeverity = new CCDAAllergySeverity();
		
		ArrayList<CCDAII> allergySeverityTemplateIds = new ArrayList<CCDAII>();
		CCDAII allergySeverityTemplateIdOne = new CCDAII();
		allergySeverityTemplateIdOne.setRootValue("2.16.840.1.113883.10.20.22.4.8");
		allergySeverityTemplateIdOne.setExtValue("2014-06-09");
		allergySeverityTemplateIds.add(allergySeverityTemplateIdOne);
		CCDAII allergySeverityTemplateIdTwo = new CCDAII();
		allergySeverityTemplateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.4.8");
		allergySeverityTemplateIds.add(allergySeverityTemplateIdTwo);
		
		allergySeverity.setTemplateIds(allergySeverityTemplateIds);
		
		CCDACode severity = new CCDACode();
		severity.setCode("6736007");
		severity.setCodeSystem("2.16.840.1.113883.6.96");
		severity.setCodeSystemName("SNOMED CT");
		severity.setDisplayName("Moderate");
		severity.setXpath("CD");
		allergySeverity.setSeverity(severity);
		
		
		allergyObservationList.add(allergyObservationOne);
		allergyConcern.setAllergyObs(allergyObservationList);
		allergyConcernList.add(allergyConcern);
	}
	
	private void setAllergiesSectionCode()
	{
		sectionCode = new CCDACode();
		sectionCode.setCode("48765-2");
		sectionCode.setCodeSystem("2.16.840.1.113883.6.1");
		sectionCode.setCodeSystemName("LOINC");
	}
	
	private void setAllergiesTemplateIds()
	{
		templateIds = new ArrayList<CCDAII>();
		CCDAII templateIdOne = new CCDAII();
		templateIdOne.setRootValue("2.16.840.1.113883.10.20.22.2.6.1");
		templateIdOne.setExtValue("2015-08-01");
		templateIds.add(templateIdOne);
		CCDAII templateIdTwo = new CCDAII();
		templateIdTwo.setRootValue("2.16.840.1.113883.10.20.22.2.6.1");
		templateIds.add(templateIdTwo);
	}
	
	
	@Test
	public void testAllergies() throws Exception{
		Assert.assertNotNull(allergies);
	}

	@Test
	public void testAllergiesSectionCode(){
		setAllergiesSectionCode();
		Assert.assertEquals("Medication Allergies SectionCode test case failed",sectionCode,allergies.getSectionCode());
	}
	
	@Test
	public void testAllergiesTemplateIds(){
		setAllergiesTemplateIds();
		Assert.assertEquals("Medication Allergies teamplet Id test case failed",templateIds,allergies.getSectionTemplateId());
	}
	
	@Test
	public void testAllergyConcern(){
		Assert.assertEquals("Medication Allergies test case failed",allergyConcernList.get(0),allergies.getAllergyConcern().get(0));
	}
	
	@Test
	public void testAllergyConcernTemplateId(){
		Assert.assertEquals("Allergy concern Template Id test case failed",allergyConcernList.get(0).getTemplateId(),allergies.getAllergyConcern().get(0).getTemplateId());
	}
	
	@Test
	public void testAllergyConcernCode(){
		Assert.assertEquals("Allergy concern code test case failed",allergyConcernList.get(0).getConcernCode(),
									allergies.getAllergyConcern().get(0).getConcernCode());
	}
	
	@Test
	public void testAllergyConcernStatusCode(){
		Assert.assertEquals("Allergy concern status code test case failed",allergyConcernList.get(0).getStatusCode(),
									allergies.getAllergyConcern().get(0).getStatusCode());
	}
	
	@Test
	public void testAllergyConcernEffectiveTime(){
		Assert.assertEquals("Allergy concern Effective time test case failed",allergyConcernList.get(0).getEffTime(),
									allergies.getAllergyConcern().get(0).getEffTime());
	}
	
	@Test
	public void testAllergyConcernObsTemplateId(){
		Assert.assertEquals("Allergy concern obs teamplate Ids test case failed",allergyConcernList.get(0).getAllergyObs().get(0).getTemplateId(),
									allergies.getAllergyConcern().get(0).getAllergyObs().get(0).getTemplateId());
	}
	
	@Test
	public void testAllergyConcernObsIntoleranceType(){
		Assert.assertEquals("Allergy concern obs intolerance type test case failed",allergyConcernList.get(0).getAllergyObs().get(0).getAllergyIntoleranceType(),
									allergies.getAllergyConcern().get(0).getAllergyObs().get(0).getAllergyIntoleranceType());
	}
	
	@Test
	public void testAllergyConcernObsSubstance(){
		Assert.assertEquals("Allergy concern obs substance test case failed",allergyConcernList.get(0).getAllergyObs().get(0).getAllergySubstance(),
									allergies.getAllergyConcern().get(0).getAllergyObs().get(0).getAllergySubstance());
	}
	
	@Test
	public void testAllergyConcernObsEffectiveTime(){
		Assert.assertEquals("Allergy concern obs  effective time test case failed",allergyConcernList.get(0).getAllergyObs().get(0).getEffTime(),
									allergies.getAllergyConcern().get(0).getAllergyObs().get(0).getEffTime());
	}
	
	@Test
	public void testAllergyConcernObsReactions(){
		Assert.assertEquals("Allergy concern obs reactions test case failed",allergyConcernList.get(0).getAllergyObs().get(0).getReactions(),
									allergies.getAllergyConcern().get(0).getAllergyObs().get(0).getReactions());
	}
	
	/*@Test
	public void testAllergyConcernObsSeverity(){
		Assert.assertEquals("Allergy concern obs severity test case failed",allergyConcernList.get(0).getAllergyObs().get(0).getSeverity(),
									allergies.getAllergyConcern().get(0).getAllergyObs().get(0).getSeverity());
	}
	
	@Test
	public void testAllergyConcernObsNegationInd(){
		Assert.assertEquals("Allergy concern obs negation Ind test case failed",allergyConcernList.get(0).getAllergyObs().get(0).getNegationInd(),
									allergies.getAllergyConcern().get(0).getAllergyObs().get(0).getNegationInd());
	}*/
}
