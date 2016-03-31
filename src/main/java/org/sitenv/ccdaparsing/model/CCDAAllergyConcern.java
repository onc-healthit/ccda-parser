package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDAAllergyConcern {

	private ArrayList<CCDAII>     		templateId;
	private CCDACode         	   		concernCode;
	private CCDACode  					statusCode;
	private CCDAEffTime      			effTime;
	private ArrayList<CCDAAllergyObs>  	allergyObs;
	
	public CCDAAllergyConcern()
	{
		
	}

	public ArrayList<CCDAII> getTemplateId() {
		return templateId;
	}

	public void setTemplateId(ArrayList<CCDAII> templateId) {
		this.templateId = templateId;
	}

	public CCDACode getConcernCode() {
		return concernCode;
	}

	public void setConcernCode(CCDACode concernCode) {
		this.concernCode = concernCode;
	}

	public CCDACode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(CCDACode statusCode) {
		this.statusCode = statusCode;
	}

	public CCDAEffTime getEffTime() {
		return effTime;
	}

	public void setEffTime(CCDAEffTime effTime) {
		this.effTime = effTime;
	}

	public ArrayList<CCDAAllergyObs> getAllergyObs() {
		return allergyObs;
	}

	public void setAllergyObs(ArrayList<CCDAAllergyObs> allergyObs) {
		this.allergyObs = allergyObs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((allergyObs == null) ? 0 : allergyObs.hashCode());
		result = prime * result
				+ ((concernCode == null) ? 0 : concernCode.hashCode());
		result = prime * result + ((effTime == null) ? 0 : effTime.hashCode());
		result = prime * result
				+ ((statusCode == null) ? 0 : statusCode.hashCode());
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
		CCDAAllergyConcern other = (CCDAAllergyConcern) obj;
		if (allergyObs == null) {
			if (other.allergyObs != null)
				return false;
		} else if (!allergyObs.equals(other.allergyObs))
			return false;
		if (concernCode == null) {
			if (other.concernCode != null)
				return false;
		} else if (!concernCode.equals(other.concernCode))
			return false;
		if (effTime == null) {
			if (other.effTime != null)
				return false;
		} else if (!effTime.equals(other.effTime))
			return false;
		if (statusCode == null) {
			if (other.statusCode != null)
				return false;
		} else if (!statusCode.equals(other.statusCode))
			return false;
		if (templateId == null) {
			if (other.templateId != null)
				return false;
		} else if (!templateId.equals(other.templateId))
			return false;
		return true;
	}
	
	
}
