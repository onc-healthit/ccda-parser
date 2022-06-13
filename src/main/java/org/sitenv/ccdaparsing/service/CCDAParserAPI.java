package org.sitenv.ccdaparsing.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.sitenv.ccdaparsing.model.CCDAAdvanceDirective;
import org.sitenv.ccdaparsing.model.CCDAAllergy;
import org.sitenv.ccdaparsing.model.CCDACareTeamMember;
import org.sitenv.ccdaparsing.model.CCDAEncounter;
import org.sitenv.ccdaparsing.model.CCDAFamilyHistory;
import org.sitenv.ccdaparsing.model.CCDAGoals;
import org.sitenv.ccdaparsing.model.CCDAHealthConcerns;
import org.sitenv.ccdaparsing.model.CCDAID;
import org.sitenv.ccdaparsing.model.CCDAImmunization;
import org.sitenv.ccdaparsing.model.CCDALabResult;
import org.sitenv.ccdaparsing.model.CCDAMedicalEquipment;
import org.sitenv.ccdaparsing.model.CCDAMedication;
import org.sitenv.ccdaparsing.model.CCDAPOT;
import org.sitenv.ccdaparsing.model.CCDAPatient;
import org.sitenv.ccdaparsing.model.CCDAProblem;
import org.sitenv.ccdaparsing.model.CCDAProcedure;
import org.sitenv.ccdaparsing.model.CCDARefModel;
import org.sitenv.ccdaparsing.model.CCDASocialHistory;
import org.sitenv.ccdaparsing.model.CCDAVitalSigns;
import org.sitenv.ccdaparsing.model.UsrhSubType;
import org.sitenv.ccdaparsing.processing.AdvanceDirectiveProcesser;
import org.sitenv.ccdaparsing.processing.CareTeamMemberProcessor;
import org.sitenv.ccdaparsing.processing.EncounterDiagnosesProcessor;
import org.sitenv.ccdaparsing.processing.FamilyHistoryProcessor;
import org.sitenv.ccdaparsing.processing.GoalsProcessor;
import org.sitenv.ccdaparsing.processing.HealthConcernsProcessor;
import org.sitenv.ccdaparsing.processing.ImmunizationProcessor;
import org.sitenv.ccdaparsing.processing.LaboratoryResultsProcessor;
import org.sitenv.ccdaparsing.processing.LaboratoryTestProcessor;
import org.sitenv.ccdaparsing.processing.MediactionAllergiesProcessor;
import org.sitenv.ccdaparsing.processing.MedicalEquipmentProcessor;
import org.sitenv.ccdaparsing.processing.MedicationProcessor;
import org.sitenv.ccdaparsing.processing.POTProcessor;
import org.sitenv.ccdaparsing.processing.PatientProcessor;
import org.sitenv.ccdaparsing.processing.ProblemProcessor;
import org.sitenv.ccdaparsing.processing.ProcedureProcessor;
import org.sitenv.ccdaparsing.processing.SmokingStatusProcessor;
import org.sitenv.ccdaparsing.processing.UDIProcessor;
import org.sitenv.ccdaparsing.processing.UsrhSubTypeProcessor;
import org.sitenv.ccdaparsing.processing.VitalSignProcessor;
import org.sitenv.ccdaparsing.util.PositionalXMLReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

@Service
public class CCDAParserAPI {

	private static final Logger logger = LogManager.getLogger(CCDAParserAPI.class);
	
	private static XPath xPath = XPathFactory.newInstance().newXPath();
	
	//private static String filePath = "C:/Projects/Dragon/CCDAParser/170.315_b1_toc_amb_ccd_r21_sample1_v1.xml";
	
	@Autowired
	PatientProcessor patientProcessor;
	
	@Autowired
	EncounterDiagnosesProcessor encounterDiagnosesProcessor;
	
	@Autowired
	ProblemProcessor problemProcessor;
	
	@Autowired
	MedicationProcessor medicationProcessor;
	
	@Autowired
	MediactionAllergiesProcessor mediactionAllergiesProcessor;
	
	@Autowired
	SmokingStatusProcessor smokingStatusProcessor;
	
	@Autowired
	LaboratoryTestProcessor laboratoryTestProcessor;
	
	@Autowired
	LaboratoryResultsProcessor laboratoryResultsProcessor;
	
	@Autowired
	VitalSignProcessor vitalSignProcessor;
	
	@Autowired
	ProcedureProcessor procedureProcessor;
	
	@Autowired
	CareTeamMemberProcessor careTeamMemberProcessor;
	
	@Autowired
	ImmunizationProcessor immunizationProcessor;
	
	@Autowired
	UDIProcessor uDIProcessor;
	
	@Autowired
	POTProcessor pOTProcessor;
	
	@Autowired
	GoalsProcessor goalsProcessor;
	
	@Autowired
	HealthConcernsProcessor healthConcernsProcessor;
	
	@Autowired
	UsrhSubTypeProcessor usrhSubTypeProcessor;

	@Autowired
	FamilyHistoryProcessor familyHistoryProcessor;

	@Autowired
	AdvanceDirectiveProcesser advanceDirectiveProcesser;



	@Autowired
	MedicalEquipmentProcessor medicalEquipmentProcessor;


	public CCDARefModel parseCCDA2_1(InputStream inputStream) {
		
		CCDARefModel refModel = new CCDARefModel();
		Future<CCDAPatient> patient=null;
		Future<CCDAEncounter> encounters=null;
		Future<CCDAProblem> problems=null;
		Future<CCDAMedication> medications=null;
		Future<CCDAAllergy> allergies=null;
		Future<CCDASocialHistory> smokingStatus=null;
		Future<CCDALabResult> labTests=null;
		Future<CCDALabResult> labResults=null;
		Future<CCDAVitalSigns> vitals=null;
		Future<CCDAProcedure> procedures=null;
		Future<CCDACareTeamMember> careTeamMembers=null;
		Future<CCDAImmunization> immunizations=null;
		Future<CCDAPOT> pot=null;
		Future<CCDAGoals> goals=null;
		Future<CCDAHealthConcerns> healthConcerns=null;
		Future<CCDAFamilyHistory> familyHistory=null;
		Future<CCDAMedicalEquipment> medicalEquipments=null;
		Future<CCDAAdvanceDirective> advanceDirective=null;
		Future<UsrhSubType> usrhSubType=null;
		ArrayList<CCDAID> idList = new ArrayList<>();
		logger.info("Parsing CCDA document");
    	long startTime = System.currentTimeMillis();
    	logger.info("All section parsing Start time:"+ startTime);
		long maxWaitTime = 300000;
		long minWaitTime = 5000;
		boolean isTimeOut = false;
		
	    try {
			Document doc = PositionalXMLReader.readXML(inputStream);
			if(doc.getDocumentElement()!= null && doc.getDocumentElement().getChildNodes().getLength()>1)
			{
				refModel.setDocTemplateId(patientProcessor.retrieveDocTemplateId(xPath, doc));
				refModel.setEncompassingEncounter(patientProcessor.retrieveEncompassingEncounter(xPath, doc));
				patient=patientProcessor.retrievePatientDetails(xPath, doc);
				encounters = encounterDiagnosesProcessor.retrieveEncounterDetails(xPath, doc);
				problems = problemProcessor.retrieveProblemDetails(xPath, doc);
				medications = medicationProcessor.retrieveMedicationDetails(xPath, doc);
				allergies = mediactionAllergiesProcessor.retrieveAllergiesDetails(xPath, doc);
				smokingStatus = smokingStatusProcessor.retrieveSmokingStatusDetails(xPath, doc);
				labTests = laboratoryTestProcessor.retrieveLabTests(xPath, doc);
				labResults = laboratoryResultsProcessor.retrieveLabResults(xPath, doc);
				vitals = vitalSignProcessor.retrieveVitalSigns(xPath, doc);
				procedures = procedureProcessor.retrievePrcedureDetails(xPath, doc);
				careTeamMembers = careTeamMemberProcessor.retrieveCTMDetails(xPath, doc);
				immunizations = immunizationProcessor.retrieveImmunizationDetails(xPath, doc);
				pot = pOTProcessor.retrievePOTDetails(xPath, doc);
				goals = goalsProcessor.retrieveGoalsDetails(xPath, doc);
				healthConcerns = healthConcernsProcessor.retrieveHealthConcernDetails(xPath, doc);
				usrhSubType = usrhSubTypeProcessor.retrieveUsrhSubTypeDetails(xPath, doc);
				familyHistory = familyHistoryProcessor.retrieveFamilyHistoryDetails(xPath, doc);
				medicalEquipments = medicalEquipmentProcessor.retrieveMedicalEquipment(xPath, doc);
				advanceDirective = advanceDirectiveProcesser.retrieveAdvanceDirectiveDetails(xPath, doc);

				if(patient!=null){
					try{
						refModel.setPatient(patient.get(maxWaitTime, TimeUnit.MILLISECONDS));
					}catch (Exception e) {
						isTimeOut = true;
					}
				}
				
				if(encounters!=null){
					try{
						refModel.setEncounter(encounters.get(isTimeOut?minWaitTime:maxWaitTime, TimeUnit.MILLISECONDS));
						if(refModel.getEncounter()!=null){
							idList.addAll(refModel.getEncounter().getIdLIst());
						}
					}catch (Exception e) {
						isTimeOut = true;
					}
				}
				
				if(problems!=null){
					try{
						refModel.setProblem(problems.get(isTimeOut?minWaitTime:maxWaitTime, TimeUnit.MILLISECONDS));
						if(refModel.getProblem()!=null){
							idList.addAll(refModel.getProblem().getIdList());
						}
					}catch (Exception e) {
						isTimeOut = true;
					}
				}
				
				if(medications!=null){
					try{
						refModel.setMedication(medications.get(isTimeOut?minWaitTime:maxWaitTime, TimeUnit.MILLISECONDS));
						if(refModel.getMedication()!=null){
							idList.addAll(refModel.getMedication().getIdList());
						}
					}catch (Exception e) {
						isTimeOut = true;
					}
				}
				
				if(allergies!=null){
					try{
						refModel.setAllergy(allergies.get(isTimeOut?minWaitTime:maxWaitTime, TimeUnit.MILLISECONDS));
						if(refModel.getAllergy()!=null){
							idList.addAll(refModel.getAllergy().getIdList());
						}
					}catch (Exception e) {
						isTimeOut = true;
					}
				}
				
				if(smokingStatus!=null){
					try{
						refModel.setSmokingStatus(smokingStatus.get(isTimeOut?minWaitTime:maxWaitTime, TimeUnit.MILLISECONDS));
						if(refModel.getSmokingStatus()!=null){
							idList.addAll(refModel.getSmokingStatus().getIdList());
						}
					}catch (Exception e) {
						isTimeOut = true;
					}
				}
				
				if(labTests!=null){
					try{
						refModel.setLabTests(labTests.get(isTimeOut?minWaitTime:maxWaitTime, TimeUnit.MILLISECONDS));
						if(refModel.getLabTests()!=null){
							idList.addAll(refModel.getLabTests().getIdList());
						}
					}catch (Exception e) {
						isTimeOut = true;
					}
				}
				
				if(labResults!=null){
					try{
						refModel.setLabResults(labResults.get(isTimeOut?minWaitTime:maxWaitTime, TimeUnit.MILLISECONDS));
						if(refModel.getLabResults()!=null){
							idList.addAll(refModel.getLabResults().getIdList());
						}
					}catch (Exception e) {
						isTimeOut = true;
					}
				}
				
				if(vitals!=null){
					try{
						refModel.setVitalSigns(vitals.get(isTimeOut?minWaitTime:maxWaitTime, TimeUnit.MILLISECONDS));
						if(refModel.getVitalSigns()!=null){
							idList.addAll(refModel.getVitalSigns().getIdList());
						}
					}catch (Exception e) {
						isTimeOut = true;
					}
				}
				
				if(procedures!=null){
					try{
						refModel.setProcedure(procedures.get(isTimeOut?minWaitTime:maxWaitTime, TimeUnit.MILLISECONDS));
						if(refModel.getProcedure()!=null){
							idList.addAll(refModel.getProcedure().getIdList());
						}
					}catch (Exception e) {
						isTimeOut = true;
					}
				}
				
				if(careTeamMembers!=null){
					try{
						refModel.setMembers(careTeamMembers.get(isTimeOut?minWaitTime:maxWaitTime, TimeUnit.MILLISECONDS));
					}catch (Exception e) {
						isTimeOut = true;
					}
				}
				
				if(immunizations!=null){
					try{
						refModel.setImmunization(immunizations.get(isTimeOut?minWaitTime:maxWaitTime, TimeUnit.MILLISECONDS));
						if(refModel.getImmunization()!=null){
							idList.addAll(refModel.getImmunization().getIdList());
						}
					}catch (Exception e) {
						isTimeOut = true;
					}
				}
				refModel.setUdi(uDIProcessor.retrieveUDIDetails(refModel.getProcedure()));
				
				if(pot!=null){
					try{
						refModel.setPlanOfTreatment(pot.get(isTimeOut?minWaitTime:maxWaitTime, TimeUnit.MILLISECONDS));
						if(refModel.getPlanOfTreatment()!=null){
							idList.addAll(refModel.getPlanOfTreatment().getIdList());
						}
					}catch (Exception e) {
						isTimeOut = true;
					}
				}
				
				if(goals!=null){
					try{
						refModel.setGoals(goals.get(isTimeOut?minWaitTime:maxWaitTime, TimeUnit.MILLISECONDS));
					}catch (Exception e) {
						isTimeOut = true;
					}
				}
				
				if(healthConcerns!=null){
					try{
						refModel.setHcs(healthConcerns.get(isTimeOut?minWaitTime:maxWaitTime, TimeUnit.MILLISECONDS));
					}catch (Exception e) {
						isTimeOut = true;
					}
				}
				
				if(usrhSubType!=null){
					try{
						refModel.setUsrhSubType(usrhSubType.get(isTimeOut?minWaitTime:maxWaitTime, TimeUnit.MILLISECONDS));
					}catch (Exception e) {
						isTimeOut = true;
					}
				}

				if(familyHistory!=null){
					try{
						refModel.setFamilyHistory(familyHistory.get(isTimeOut?minWaitTime:maxWaitTime, TimeUnit.MILLISECONDS));
						if(refModel.getFamilyHistory()!=null){
							idList.addAll(refModel.getFamilyHistory().getIdList());
						}
					}catch (Exception e) {
						isTimeOut = true;
					}
				}

				if(medicalEquipments!=null){
					try {
						refModel.setMedicalEquipment(medicalEquipments.get(isTimeOut?minWaitTime:maxWaitTime, TimeUnit.MILLISECONDS));
					} catch (InterruptedException | ExecutionException | TimeoutException e) {
						isTimeOut = true;
					}
					if(refModel.getMedicalEquipment()!=null){
						idList.addAll(refModel.getMedicalEquipment().getIds());
					}
				}

				if(advanceDirective!=null){
					try{
						refModel.setAdvanceDirective(advanceDirective.get(isTimeOut?minWaitTime:maxWaitTime, TimeUnit.MILLISECONDS));
						if(refModel.getAdvanceDirective()!=null){
							idList.addAll(refModel.getAdvanceDirective().getIdList());
						}
					}catch (Exception e) {
						isTimeOut = true;
					}
				}

				refModel.setIdList(idList);
			}
			else
			{
				refModel.setEmpty(true);
			}
			
			logger.info("Parsing CCDA document completed");
		    }
	    	catch (FileNotFoundException fnfException) 
	    	{
	    		logger.error(fnfException);
			}
	    	catch(XPathExpressionException xpeException)
			{
	    		logger.error(xpeException);
			}
	    	catch (IOException ioException) 
	    	{
	    		logger.error(ioException);
			}
	    	catch (SAXException saxException) 
	    	{
	    		logger.info("Parsing CCDA document failed");
	    		logger.error(saxException);
	    		refModel.setEmpty(true);
			}
	    	catch (NullPointerException npException) 
	    	{
	    		logger.error(npException);
			}
	        catch(TransformerException te)
	    	{
	            logger.error(te);
	    	}
	    logger.info("All Section End time:"+ (System.currentTimeMillis() - startTime));
	    
	    return refModel;
	
	}
}
