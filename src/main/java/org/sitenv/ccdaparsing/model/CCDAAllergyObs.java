package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDAAllergyObs {

	private ArrayList<CCDAII>    			templateId;
	private CCDACode             			allergyIntoleranceType;
	private CCDACode             			allergySubstance;
	private CCDAEffTime          			effTime;
	private ArrayList<CCDAAllergyReaction>  reactions;
	private CCDAAllergySeverity				severity;
	private Boolean							negationInd;
	
	
	public Boolean getNegationInd() {
		return negationInd;
	}

	public void setNegationInd(Boolean negationInd) {
		this.negationInd = negationInd;
	}


	public ArrayList<CCDAII> getTemplateId() {
		return templateId;
	}


	public void setTemplateId(ArrayList<CCDAII> templateId) {
		this.templateId = templateId;
	}


	public CCDACode getAllergyIntoleranceType() {
		return allergyIntoleranceType;
	}


	public void setAllergyIntoleranceType(CCDACode allergyIntoleranceType) {
		this.allergyIntoleranceType = allergyIntoleranceType;
	}


	public CCDACode getAllergySubstance() {
		return allergySubstance;
	}


	public void setAllergySubstance(CCDACode allergySubstance) {
		this.allergySubstance = allergySubstance;
	}


	public CCDAEffTime getEffTime() {
		return effTime;
	}


	public void setEffTime(CCDAEffTime effTime) {
		this.effTime = effTime;
	}


	public ArrayList<CCDAAllergyReaction> getReactions() {
		return reactions;
	}


	public void setReactions(ArrayList<CCDAAllergyReaction> reactions) {
		this.reactions = reactions;
	}


	public CCDAAllergySeverity getSeverity() {
		return severity;
	}


	public void setSeverity(CCDAAllergySeverity severity) {
		this.severity = severity;
	}


	public CCDAAllergyObs()
	{
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((allergyIntoleranceType == null) ? 0
						: allergyIntoleranceType.hashCode());
		result = prime
				* result
				+ ((allergySubstance == null) ? 0 : allergySubstance.hashCode());
		result = prime * result + ((effTime == null) ? 0 : effTime.hashCode());
		result = prime * result
				+ ((negationInd == null) ? 0 : negationInd.hashCode());
		result = prime * result
				+ ((reactions == null) ? 0 : reactions.hashCode());
		result = prime * result
				+ ((severity == null) ? 0 : severity.hashCode());
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
		CCDAAllergyObs other = (CCDAAllergyObs) obj;
		if (allergyIntoleranceType == null) {
			if (other.allergyIntoleranceType != null)
				return false;
		} else if (!allergyIntoleranceType.equals(other.allergyIntoleranceType))
			return false;
		if (allergySubstance == null) {
			if (other.allergySubstance != null)
				return false;
		} else if (!allergySubstance.equals(other.allergySubstance))
			return false;
		if (effTime == null) {
			if (other.effTime != null)
				return false;
		} else if (!effTime.equals(other.effTime))
			return false;
		if (negationInd == null) {
			if (other.negationInd != null)
				return false;
		} else if (!negationInd.equals(other.negationInd))
			return false;
		if (reactions == null) {
			if (other.reactions != null)
				return false;
		} else if (!reactions.equals(other.reactions))
			return false;
		if (severity == null) {
			if (other.severity != null)
				return false;
		} else if (!severity.equals(other.severity))
			return false;
		if (templateId == null) {
			if (other.templateId != null)
				return false;
		} else if (!templateId.equals(other.templateId))
			return false;
		return true;
	}
	
}
