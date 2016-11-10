package org.sitenv.ccdaparsing.processing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.sitenv.ccdaparsing.model.UsrhSubType;

final class UsrhIISubTypes {
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
	protected static final List<UsrhII> ccdaDocumentTypes = new ArrayList<UsrhII>();
	static {
		Collections.addAll(ccdaDocumentTypes, CONSULTATION_NOTE,
				CONTINUITY_OF_CARE_DOCUMENT, DIAGNOSTIC_IMAGING_REPORT,
				DISCHARGE_SUMMARY, HISTORY_AND_PHYSICAL_NOTE,
				OPERATIVE_NOTE, PROCEDURE_NOTE, PROGRESS_NOTE,
				UNSTRUCTURED_DOCUMENT, CARE_PLAN, REFERRAL_NOTE,
				TRANSFER_SUMMARY,
				US_REALM_HEADER_PATIENT_GENERATED_DOCUMENT);
	}	
}
