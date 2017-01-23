package org.sitenv.ccdaparsing.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sitenv.ccdaparsing.model.UsrhII;
import org.sitenv.ccdaparsing.model.UsrhSubType;

public class ApplicationConstants {
	
	public static String USRH_SUB_TYPE_EXPRESSION = "/ClinicalDocument/templateId[starts-with(@root, '2.16.840.1.113883.10.20') and @root!='2.16.840.1.113883.10.20.22.1.1']";
	
	public static String SDTC_EXPRESSION = "/ClinicalDocument/templateId[@root='2.16.840.1.113883.10.20.22.1.1' and @extension='2015-08-01']/"
			+ "ancestor::ClinicalDocument[1]/recordTarget/patientRole/patient/sdtc:raceCode";
	
	public static String RACE_CODES = "/ClinicalDocument/templateId[@root='2.16.840.1.113883.10.20.22.1.1' and @extension='2015-08-01']/"
			+ "ancestor::ClinicalDocument[1]/recordTarget/patientRole/patient/raceCode[not(@nullFlavor)]";
	
	public static String ALLERGIES_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[(not(@nullFlavor) or @nullFlavor='NI') and templateId[@root='2.16.840.1.113883.10.20.22.2.6.1']]";
    public static String ALLERGY_REACTION_EXPRESSION ="./entryRelationship/observation[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.4.9']]";
    public static String ALLERGY_SEVERITY_EXPRESSION ="./entryRelationship/observation[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.4.8']]";
	
	
    public static String ENCOUNTER_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[(not(@nullFlavor) or @nullFlavor='NI') and templateId[@root='2.16.840.1.113883.10.20.22.2.22.1']]";
    
    public static String PROBLEM_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[(not(@nullFlavor) or @nullFlavor='NI') and templateId[@root='2.16.840.1.113883.10.20.22.2.5.1']]";
    public static String PROBLEM_OBS_EXPRESSION = "./entryRelationship/observation[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.4.4']]";
    
    public static String MEDICATION_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[(not(@nullFlavor) or @nullFlavor='NI') and templateId[@root='2.16.840.1.113883.10.20.22.2.1.1']]";
    
    public static String SMOKING_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[(not(@nullFlavor) or @nullFlavor='NI') and templateId[@root='2.16.840.1.113883.10.20.22.2.17']]";
    public static String SMOKING_STATUS_EXPRESSION = "./entry/observation[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.4.78']]";
    public static String TOBACCOUSE_EXPRESSION = "./entry/observation[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.4.85']]";
    
    public static String RESULTS_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[(not(@nullFlavor) or @nullFlavor='NI') and templateId[@root='2.16.840.1.113883.10.20.22.2.3.1']]";
    public static String LAB_RESULTS_EXPRESSION = "./entry/organizer[not(@nullFlavor) and statusCode[@code='completed']]";
    public static String LAB_TESTS_EXPRESSION = "./entry/organizer[not(@nullFlavor) and statusCode[@code='active']]";
    public static String IMMUNIZATION_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[(not(@nullFlavor) or @nullFlavor='NI') and templateId[@root='2.16.840.1.113883.10.20.22.2.2.1']]";
    public static String VITALSIGNS_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[(not(@nullFlavor) or @nullFlavor='NI') and templateId[@root='2.16.840.1.113883.10.20.22.2.4.1']]";
    
    
    public static String PROCEDURE_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[(not(@nullFlavor) or @nullFlavor='NI') and templateId[@root='2.16.840.1.113883.10.20.22.2.7.1']]";
    public static String PROCEDURE_UDI_EXPRESSION ="./participant[not(@nullFlavor) and @typeCode='DEV']/participantRole[not(@nullFlavor)]";
    public static String PROCEDURE_SDL_EXPRESSION ="./participant[not(@nullFlavor) and @typeCode='LOC']/participantRole[not(@nullFlavor)]";
    
    public static String PATIENT_EXPRESSION = "/ClinicalDocument/recordTarget/patientRole[not(@nullFlavor)]";
    public static String POT_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[(not(@nullFlavor) or @nullFlavor='NI') and templateId[@root='2.16.840.1.113883.10.20.22.2.10']]";
    public static String POT_MEDICATION_EXPRESSION = "./entry/substanceAdministration[not(@nullFlavor) and @classCode='SBADM']";
    public static String POT_ENCOUNTER_EXPRESSION = "./entry/encounter[not(@nullFlavor) and @classCode='ENC']";
    public static String POT_PROCEDURE_EXPRESSION = "./entry/procedure [not(@nullFlavor) and @classCode='PROC']";
    public static String GOALS_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[(not(@nullFlavor) or @nullFlavor='NI') and templateId[@root='2.16.840.1.113883.10.20.22.2.60']]";
    public static String HEALTHCONCERN_EXPRESSION = "/ClinicalDocument/component/structuredBody/component/section[(not(@nullFlavor) or @nullFlavor='NI') and templateId[@root='2.16.840.1.113883.10.20.22.2.58']]";
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
    
    public static final class UsrhIISubTypes {
    	private static final String HL7_GENERAL_PREFIX = "2.16.840.1.113883.10.20";
    	private static final String HL7_STANDARD_DOC_PREFIX = HL7_GENERAL_PREFIX
    			+ ".22.1";
    	
    	// C-CDA R1.0 - R2.1 document types
    	private static final UsrhII CONSULTATION_NOTE = new UsrhII(
    			HL7_STANDARD_DOC_PREFIX + ".4", UsrhSubType.CONSULTATION_NOTE);
    	private static final UsrhII CONTINUITY_OF_CARE_DOCUMENT = new UsrhII(
    			HL7_STANDARD_DOC_PREFIX + ".2", UsrhSubType.CONTINUITY_OF_CARE_DOCUMENT);
    	private static final UsrhII DIAGNOSTIC_IMAGING_REPORT = new UsrhII(
    			HL7_STANDARD_DOC_PREFIX + ".5", UsrhSubType.DIAGNOSTIC_IMAGING_REPORT);
    	private static final UsrhII DISCHARGE_SUMMARY = new UsrhII(
    			HL7_STANDARD_DOC_PREFIX + ".8", UsrhSubType.DISCHARGE_SUMMARY);
    	private static final UsrhII HISTORY_AND_PHYSICAL_NOTE = new UsrhII(
    			HL7_STANDARD_DOC_PREFIX + ".3", UsrhSubType.HISTORY_AND_PHYSICAL_NOTE);
    	private static final UsrhII OPERATIVE_NOTE = new UsrhII(
    			HL7_STANDARD_DOC_PREFIX + ".7", UsrhSubType.OPERATIVE_NOTE);
    	private static final UsrhII PROCEDURE_NOTE = new UsrhII(
    			HL7_STANDARD_DOC_PREFIX + ".6", UsrhSubType.PROCEDURE_NOTE);
    	private static final UsrhII PROGRESS_NOTE = new UsrhII(
    			HL7_STANDARD_DOC_PREFIX + ".9", UsrhSubType.PROGRESS_NOTE);
    	private static final UsrhII UNSTRUCTURED_DOCUMENT = new UsrhII(
    			HL7_STANDARD_DOC_PREFIX + ".10", UsrhSubType.UNSTRUCTURED_DOCUMENT);
    	// C-CDA R2.0/1 document types only
    	private static final UsrhII CARE_PLAN = new UsrhII(
    			HL7_STANDARD_DOC_PREFIX + ".15", UsrhSubType.CARE_PLAN);
    	private static final UsrhII REFERRAL_NOTE = new UsrhII(
    			HL7_STANDARD_DOC_PREFIX + ".14", UsrhSubType.REFERRAL_NOTE);
    	private static final UsrhII TRANSFER_SUMMARY = new UsrhII(
    			HL7_STANDARD_DOC_PREFIX + ".13", UsrhSubType.TRANSFER_SUMMARY);
    	private static final UsrhII US_REALM_HEADER_PATIENT_GENERATED_DOCUMENT = new UsrhII(
    			HL7_GENERAL_PREFIX + ".29.1", UsrhSubType.US_REALM_HEADER_PATIENT_GENERATED_DOCUMENT);
    	
    	// All C-CDA document types in an externally accessible list
    	public static final List<UsrhII> ccdaDocumentTypes = new ArrayList<UsrhII>(Arrays.asList(CONSULTATION_NOTE,
				CONTINUITY_OF_CARE_DOCUMENT, DIAGNOSTIC_IMAGING_REPORT,
				DISCHARGE_SUMMARY, HISTORY_AND_PHYSICAL_NOTE,
				OPERATIVE_NOTE, PROCEDURE_NOTE, PROGRESS_NOTE,
				UNSTRUCTURED_DOCUMENT, CARE_PLAN, REFERRAL_NOTE,
				TRANSFER_SUMMARY,
				US_REALM_HEADER_PATIENT_GENERATED_DOCUMENT));
    }
    
}
