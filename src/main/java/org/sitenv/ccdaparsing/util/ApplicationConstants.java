package org.sitenv.ccdaparsing.util;

public class ApplicationConstants {
	
	public static String USRH_SUB_TYPE_EXPRESSION = "/ClinicalDocument/templateId[starts-with(@root, '2.16.840.1.113883.10.20') and @root!='2.16.840.1.113883.10.20.22.1.1']";
	
	public static String SDTC_EXPRESSION = "/ClinicalDocument/templateId[@root='2.16.840.1.113883.10.20.22.1.1' and @extension='2015-08-01']/"
			+ "ancestor::ClinicalDocument[1]/recordTarget/patientRole/patient/sdtc:raceCode";
	
	public static String RACE_CODES = "/ClinicalDocument/templateId[@root='2.16.840.1.113883.10.20.22.1.1' and @extension='2015-08-01']/"
			+ "ancestor::ClinicalDocument[1]/recordTarget/patientRole/patient/raceCode[not(@nullFlavor)]";
	
	public static String ALLERGIES_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.2.6.1']]";
    public static String ALLERGY_REACTION_EXPRESSION ="./entryRelationship/observation[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.4.9']]";
    public static String ALLERGY_SEVERITY_EXPRESSION ="./entryRelationship/observation[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.4.8']]";
	
	
    public static String ENCOUNTER_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.2.22.1']]";
    
    public static String PROBLEM_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.2.5.1']]";
    public static String PROBLEM_OBS_EXPRESSION = "./entryRelationship/observation[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.4.4']]";
    
    public static String MEDICATION_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.2.1.1']]";
    
    public static String SMOKING_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.2.17']]";
    public static String SMOKING_STATUS_EXPRESSION = "./entry/observation[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.4.78']]";
    public static String TOBACCOUSE_EXPRESSION = "./entry/observation[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.4.85']]";
    
    public static String RESULTS_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.2.3.1']]";
    public static String LAB_RESULTS_EXPRESSION = "./entry/organizer[not(@nullFlavor) and statusCode[@code='completed']]";
    public static String LAB_TESTS_EXPRESSION = "./entry/organizer[not(@nullFlavor) and statusCode[@code='active']]";
    public static String IMMUNIZATION_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.2.2.1']]";
    public static String VITALSIGNS_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.2.4.1']]";
    
    
    public static String PROCEDURE_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.2.7.1']]";
    public static String PROCEDURE_UDI_EXPRESSION ="./participant[not(@nullFlavor) and @typeCode='DEV']/participantRole[not(@nullFlavor)]";
    public static String PROCEDURE_SDL_EXPRESSION ="./participant[not(@nullFlavor) and @typeCode='LOC']/participantRole[not(@nullFlavor)]";
    
    public static String PATIENT_EXPRESSION = "/ClinicalDocument/recordTarget/patientRole[not(@nullFlavor)]";
    public static String POT_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.2.10']]";
    public static String POT_MEDICATION_EXPRESSION = "./entry/substanceAdministration[not(@nullFlavor) and @classCode='SBADM']";
    public static String POT_ENCOUNTER_EXPRESSION = "./entry/encounter[not(@nullFlavor) and @classCode='ENC']";
    public static String POT_PROCEDURE_EXPRESSION = "./entry/procedure [not(@nullFlavor) and @classCode='PROC']";
    public static String GOALS_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.2.60']]";
    public static String HEALTHCONCERN_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.2.58']]";
    public static String CTM_EXPRESSION = "/ClinicalDocument/documentationOf/serviceEvent/performer[not(@nullFlavor)]";
    
    
    public static String PROBLEMS_TEM_ID = "2.16.840.1.113883.10.20.22.2.5.1";
	public static String MEDICATION_TEM_ID = "2.16.840.1.113883.10.20.22.2.1.1";
	public static String ENCOUNTER_TEM_ID = "2.16.840.1.113883.10.20.22.2.22.1";
	
	public static String ALLERGIES_TEM_ID = "2.16.840.1.113883.10.20.22.2.6.1";
	public static String ALLERGY_OBS_TEM_ID = "2.16.840.1.113883.10.20.22.4.7";
	public static String ALLERGY_SEVERITY_TEM_ID="2.16.840.1.113883.10.20.22.4.8";
	public static String ALLERGY_REACTION_TEM_ID="2.16.840.1.113883.10.20.22.4.9";
	
	public final static String LINE_NUMBER_KEY_NAME = "lineNumber";
    public final static String END_LINE_NUMBER_KEY_NAME = "endLineNumber";
    
}
