package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDAPOT {
	
	private ArrayList<CCDAII>    templateId;
	private CCDACode  sectionCode;
	private CCDADataElement  narrativeText;
	private ArrayList<CCDALabResult> plannedlabTest; 
	private ArrayList<CCDAProcActProc> plannedProcedure; 
	private ArrayList<CCDAMedicationActivity> plannedMedicationActivity; 
	private ArrayList<CCDAEncounterActivity> plannedEncounter;
	
	
	public ArrayList<CCDAII> getTemplateId() {
		return templateId;
	}
	public void setTemplateId(ArrayList<CCDAII> templateId) {
		this.templateId = templateId;
	}
	public CCDACode getSectionCode() {
		return sectionCode;
	}
	public CCDADataElement getNarrativeText() {
		return narrativeText;
	}
	public void setNarrativeText(CCDADataElement narrativeText) {
		this.narrativeText = narrativeText;
	}
	public void setSectionCode(CCDACode sectionCode) {
		this.sectionCode = sectionCode;
	}
	public ArrayList<CCDALabResult> getPlannedlabTest() {
		return plannedlabTest;
	}
	public void setPlannedlabTest(ArrayList<CCDALabResult> plannedlabTest) {
		this.plannedlabTest = plannedlabTest;
	}
	public ArrayList<CCDAProcActProc> getPlannedProcedure() {
		return plannedProcedure;
	}
	public void setPlannedProcedure(ArrayList<CCDAProcActProc> plannedProcedure) {
		this.plannedProcedure = plannedProcedure;
	}
	public ArrayList<CCDAMedicationActivity> getPlannedMedicationActivity() {
		return plannedMedicationActivity;
	}
	public void setPlannedMedicationActivity(
			ArrayList<CCDAMedicationActivity> plannedMedicationActivity) {
		this.plannedMedicationActivity = plannedMedicationActivity;
	}
	public ArrayList<CCDAEncounterActivity> getPlannedEncounter() {
		return plannedEncounter;
	}
	public void setPlannedEncounter(
			ArrayList<CCDAEncounterActivity> plannedEncounter) {
		this.plannedEncounter = plannedEncounter;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((narrativeText == null) ? 0 : narrativeText.hashCode());
		result = prime
				* result
				+ ((plannedEncounter == null) ? 0 : plannedEncounter.hashCode());
		result = prime
				* result
				+ ((plannedMedicationActivity == null) ? 0
						: plannedMedicationActivity.hashCode());
		result = prime
				* result
				+ ((plannedProcedure == null) ? 0 : plannedProcedure.hashCode());
		result = prime * result
				+ ((plannedlabTest == null) ? 0 : plannedlabTest.hashCode());
		result = prime * result
				+ ((sectionCode == null) ? 0 : sectionCode.hashCode());
		result = prime * result
				+ ((templateId == null) ? 0 : templateId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CCDAPOT other = (CCDAPOT) obj;
		if (narrativeText == null) {
			if (other.narrativeText != null)
				return false;
		} else if (!narrativeText.equals(other.narrativeText))
			return false;
		if (plannedEncounter == null) {
			if (other.plannedEncounter != null)
				return false;
		} else if (!plannedEncounter.equals(other.plannedEncounter))
			return false;
		if (plannedMedicationActivity == null) {
			if (other.plannedMedicationActivity != null)
				return false;
		} else if (!plannedMedicationActivity
				.equals(other.plannedMedicationActivity))
			return false;
		if (plannedProcedure == null) {
			if (other.plannedProcedure != null)
				return false;
		} else if (!plannedProcedure.equals(other.plannedProcedure))
			return false;
		if (plannedlabTest == null) {
			if (other.plannedlabTest != null)
				return false;
		} else if (!plannedlabTest.equals(other.plannedlabTest))
			return false;
		if (sectionCode == null) {
			if (other.sectionCode != null)
				return false;
		} else if (!sectionCode.equals(other.sectionCode))
			return false;
		if (templateId == null) {
			if (other.templateId != null)
				return false;
		} else if (!templateId.equals(other.templateId))
			return false;
		return true;
	}  
	
	
}
