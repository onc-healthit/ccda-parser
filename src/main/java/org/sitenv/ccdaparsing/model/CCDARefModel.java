package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDARefModel {
	
	private CCDAPatient        patient;
	private CCDACareTeamMember members;
	private CCDAEncounter      encounter;
	private CCDAAllergy        allergy;
	private CCDAMedication     medication;
	private CCDAImmunization   immunization;
	private CCDALabResult      labResults;
	private CCDALabResult  	   labTests;
	private CCDAProcedure      procedure;
	private CCDASocialHistory  smokingStatus;
	private CCDAVitalSigns     vitalSigns;
	private CCDAProblem        problem;
	private CCDAPOT            planOfTreatment;
	private CCDAGoals          goals;
	private CCDAHealthConcerns hcs;
	private ArrayList<CCDAUDI> udi;
	private boolean isEmpty;
	
	
	public CCDAPatient getPatient() {
		return patient;
	}
	public void setPatient(CCDAPatient patient) {
		this.patient = patient;
	}
	public CCDACareTeamMember getMembers() {
		return members;
	}
	public void setMembers(CCDACareTeamMember members) {
		this.members = members;
	}
	public CCDAEncounter getEncounter() {
		return encounter;
	}
	public void setEncounter(CCDAEncounter encounter) {
		this.encounter = encounter;
	}
	public CCDAAllergy getAllergy() {
		return allergy;
	}
	public void setAllergy(CCDAAllergy allergy) {
		this.allergy = allergy;
	}
	public CCDAMedication getMedication() {
		return medication;
	}
	public void setMedication(CCDAMedication medication) {
		this.medication = medication;
	}
	public CCDAImmunization getImmunization() {
		return immunization;
	}
	public void setImmunization(CCDAImmunization immunization) {
		this.immunization = immunization;
	}
	public CCDALabResult getLabResults() {
		return labResults;
	}
	public void setLabResults(CCDALabResult labResults) {
		this.labResults = labResults;
	}
	public CCDALabResult getLabTests() {
		return labTests;
	}
	public void setLabTests(CCDALabResult labTests) {
		this.labTests = labTests;
	}
	public CCDAProcedure getProcedure() {
		return procedure;
	}
	public void setProcedure(CCDAProcedure procedure) {
		this.procedure = procedure;
	}
	public CCDASocialHistory getSmokingStatus() {
		return smokingStatus;
	}
	public void setSmokingStatus(CCDASocialHistory smokingStatus) {
		this.smokingStatus = smokingStatus;
	}
	public CCDAVitalSigns getVitalSigns() {
		return vitalSigns;
	}
	public void setVitalSigns(CCDAVitalSigns vitalSigns) {
		this.vitalSigns = vitalSigns;
	}
	public CCDAProblem getProblem() {
		return problem;
	}
	public void setProblem(CCDAProblem problem) {
		this.problem = problem;
	}
	public CCDAPOT getPlanOfTreatment() {
		return planOfTreatment;
	}
	public void setPlanOfTreatment(CCDAPOT planOfTreatment) {
		this.planOfTreatment = planOfTreatment;
	}
	public CCDAGoals getGoals() {
		return goals;
	}
	public void setGoals(CCDAGoals goals) {
		this.goals = goals;
	}
	public CCDAHealthConcerns getHcs() {
		return hcs;
	}
	public void setHcs(CCDAHealthConcerns hcs) {
		this.hcs = hcs;
	}
	public ArrayList<CCDAUDI> getUdi() {
		return udi;
	}
	public void setUdi(ArrayList<CCDAUDI> udi) {
		this.udi = udi;
	}
	public boolean isEmpty() {
		return isEmpty;
	}
	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allergy == null) ? 0 : allergy.hashCode());
		result = prime * result
				+ ((encounter == null) ? 0 : encounter.hashCode());
		result = prime * result + ((goals == null) ? 0 : goals.hashCode());
		result = prime * result + ((hcs == null) ? 0 : hcs.hashCode());
		result = prime * result
				+ ((immunization == null) ? 0 : immunization.hashCode());
		result = prime * result
				+ ((labResults == null) ? 0 : labResults.hashCode());
		result = prime * result
				+ ((labTests == null) ? 0 : labTests.hashCode());
		result = prime * result
				+ ((medication == null) ? 0 : medication.hashCode());
		result = prime * result + ((members == null) ? 0 : members.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		result = prime * result
				+ ((planOfTreatment == null) ? 0 : planOfTreatment.hashCode());
		result = prime * result + ((problem == null) ? 0 : problem.hashCode());
		result = prime * result
				+ ((procedure == null) ? 0 : procedure.hashCode());
		result = prime * result
				+ ((smokingStatus == null) ? 0 : smokingStatus.hashCode());
		result = prime * result + ((udi == null) ? 0 : udi.hashCode());
		result = prime * result
				+ ((vitalSigns == null) ? 0 : vitalSigns.hashCode());
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
		CCDARefModel other = (CCDARefModel) obj;
		if (allergy == null) {
			if (other.allergy != null)
				return false;
		} else if (!allergy.equals(other.allergy))
			return false;
		if (encounter == null) {
			if (other.encounter != null)
				return false;
		} else if (!encounter.equals(other.encounter))
			return false;
		if (goals == null) {
			if (other.goals != null)
				return false;
		} else if (!goals.equals(other.goals))
			return false;
		if (hcs == null) {
			if (other.hcs != null)
				return false;
		} else if (!hcs.equals(other.hcs))
			return false;
		if (immunization == null) {
			if (other.immunization != null)
				return false;
		} else if (!immunization.equals(other.immunization))
			return false;
		if (labResults == null) {
			if (other.labResults != null)
				return false;
		} else if (!labResults.equals(other.labResults))
			return false;
		if (labTests == null) {
			if (other.labTests != null)
				return false;
		} else if (!labTests.equals(other.labTests))
			return false;
		if (medication == null) {
			if (other.medication != null)
				return false;
		} else if (!medication.equals(other.medication))
			return false;
		if (members == null) {
			if (other.members != null)
				return false;
		} else if (!members.equals(other.members))
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		if (planOfTreatment == null) {
			if (other.planOfTreatment != null)
				return false;
		} else if (!planOfTreatment.equals(other.planOfTreatment))
			return false;
		if (problem == null) {
			if (other.problem != null)
				return false;
		} else if (!problem.equals(other.problem))
			return false;
		if (procedure == null) {
			if (other.procedure != null)
				return false;
		} else if (!procedure.equals(other.procedure))
			return false;
		if (smokingStatus == null) {
			if (other.smokingStatus != null)
				return false;
		} else if (!smokingStatus.equals(other.smokingStatus))
			return false;
		if (udi == null) {
			if (other.udi != null)
				return false;
		} else if (!udi.equals(other.udi))
			return false;
		if (vitalSigns == null) {
			if (other.vitalSigns != null)
				return false;
		} else if (!vitalSigns.equals(other.vitalSigns))
			return false;
		return true;
	}
	
	
}
