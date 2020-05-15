package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDAFamilyHxOrg extends CCDAXmlSnippet {

	private ArrayList<CCDAII> templateIds;
	private CCDACode statusCode;
	private CCDACode relationCode;
	private CCDAEffTime birthTime;
	private CCDACode genderCode;
	private ArrayList<CCDAFamilyHxObs> familyHxObs;
	private CCDADataElement referenceText;

	public ArrayList<CCDAII> getTemplateIds() {
		return templateIds;
	}

	public void setTemplateIds(ArrayList<CCDAII> templateIds) {
		this.templateIds = templateIds;
	}

	public CCDACode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(CCDACode statusCode) {
		this.statusCode = statusCode;
	}

	public CCDACode getRelationCode() {
		return relationCode;
	}

	public void setRelationCode(CCDACode relationCode) {
		this.relationCode = relationCode;
	}

	public CCDAEffTime getBirthTime() {
		return birthTime;
	}

	public void setBirthTime(CCDAEffTime birthTime) {
		this.birthTime = birthTime;
	}

	public CCDACode getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(CCDACode genderCode) {
		this.genderCode = genderCode;
	}

	public ArrayList<CCDAFamilyHxObs> getFamilyHxObs() {
		return familyHxObs;
	}

	public void setFamilyHxObs(ArrayList<CCDAFamilyHxObs> familyHxObs) {
		this.familyHxObs = familyHxObs;
	}

	public CCDADataElement getReferenceText() {
		return referenceText;
	}

	public void setReferenceText(CCDADataElement referenceText) {
		this.referenceText = referenceText;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((birthTime == null) ? 0 : birthTime.hashCode());
		result = prime * result + ((familyHxObs == null) ? 0 : familyHxObs.hashCode());
		result = prime * result + ((genderCode == null) ? 0 : genderCode.hashCode());
		result = prime * result + ((relationCode == null) ? 0 : relationCode.hashCode());
		result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
		result = prime * result + ((templateIds == null) ? 0 : templateIds.hashCode());
		result = prime * result + ((referenceText == null) ? 0 : referenceText.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CCDAFamilyHxOrg other = (CCDAFamilyHxOrg) obj;
		if (birthTime == null) {
			if (other.birthTime != null)
				return false;
		} else if (!birthTime.equals(other.birthTime))
			return false;
		if (familyHxObs == null) {
			if (other.familyHxObs != null)
				return false;
		} else if (!familyHxObs.equals(other.familyHxObs))
			return false;
		if (genderCode == null) {
			if (other.genderCode != null)
				return false;
		} else if (!genderCode.equals(other.genderCode))
			return false;
		if (relationCode == null) {
			if (other.relationCode != null)
				return false;
		} else if (!relationCode.equals(other.relationCode))
			return false;
		if (statusCode == null) {
			if (other.statusCode != null)
				return false;
		} else if (!statusCode.equals(other.statusCode))
			return false;
		if (templateIds == null) {
			if (other.templateIds != null)
				return false;
		} else if (!templateIds.equals(other.templateIds))
			return false;
		if (referenceText == null) {
			if (other.referenceText != null)
				return false;
		} else if (!referenceText.equals(other.referenceText))
			return false;
		return true;
	}
}
