package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDAProblemObs {

	private ArrayList<CCDAII>    templateId;
	private CCDACode             problemType;
	private ArrayList<CCDACode>  translationProblemType;
	private CCDAEffTime          effTime;
	private CCDACode             problemCode;
	
	public ArrayList<CCDAII> getTemplateId() {
		return templateId;
	}

	public void setTemplateId(ArrayList<CCDAII> templateId) {
		this.templateId = templateId;
	}

	public CCDACode getProblemType() {
		return problemType;
	}

	public void setProblemType(CCDACode problemType) {
		this.problemType = problemType;
	}

	public ArrayList<CCDACode> getTranslationProblemType() {
		return translationProblemType;
	}

	public void setTranslationProblemType(ArrayList<CCDACode> translationProblemType) {
		this.translationProblemType = translationProblemType;
	}

	public CCDAEffTime getEffTime() {
		return effTime;
	}

	public void setEffTime(CCDAEffTime effTime) {
		this.effTime = effTime;
	}

	public CCDACode getProblemCode() {
		return problemCode;
	}

	public void setProblemCode(CCDACode problemCode) {
		this.problemCode = problemCode;
	}

	public CCDAProblemObs()
	{
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((effTime == null) ? 0 : effTime.hashCode());
		result = prime * result
				+ ((problemCode == null) ? 0 : problemCode.hashCode());
		result = prime * result
				+ ((problemType == null) ? 0 : problemType.hashCode());
		result = prime * result
				+ ((templateId == null) ? 0 : templateId.hashCode());
		result = prime
				* result
				+ ((translationProblemType == null) ? 0
						: translationProblemType.hashCode());
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
		CCDAProblemObs other = (CCDAProblemObs) obj;
		if (effTime == null) {
			if (other.effTime != null)
				return false;
		} else if (!effTime.equals(other.effTime))
			return false;
		if (problemCode == null) {
			if (other.problemCode != null)
				return false;
		} else if (!problemCode.equals(other.problemCode))
			return false;
		if (problemType == null) {
			if (other.problemType != null)
				return false;
		} else if (!problemType.equals(other.problemType))
			return false;
		if (templateId == null) {
			if (other.templateId != null)
				return false;
		} else if (!templateId.equals(other.templateId))
			return false;
		if (translationProblemType == null) {
			if (other.translationProblemType != null)
				return false;
		} else if (!translationProblemType.equals(other.translationProblemType))
			return false;
		return true;
	}
	
	
}
