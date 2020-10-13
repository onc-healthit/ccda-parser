package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDAFamilyHxObs extends CCDAXmlSnippet{
	private ArrayList<CCDAII> templateIds;
	private CCDACode observationTypeCode;
	private CCDAEffTime observationTime;
	private CCDACode statusCode;
	private CCDACode observationValue;
	private CCDADataElement referenceText;
	private CCDAPQ ageOnSetValue;
	private CCDADataElement ageOnSetReferenceText;
	private CCDACode causedDeathValue;
	private CCDADataElement causedDeathReferenceText;

	public ArrayList<CCDAII> getTemplateIds() {
		return templateIds;
	}

	public void setTemplateIds(ArrayList<CCDAII> templateIds) {
		this.templateIds = templateIds;
	}

	public CCDACode getObservationTypeCode() {
		return observationTypeCode;
	}

	public void setObservationTypeCode(CCDACode observationTypeCode) {
		this.observationTypeCode = observationTypeCode;
	}

	public CCDAEffTime getObservationTime() {
		return observationTime;
	}

	public void setObservationTime(CCDAEffTime observationTime) {
		this.observationTime = observationTime;
	}

	public CCDACode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(CCDACode statusCode) {
		this.statusCode = statusCode;
	}

	public CCDACode getObservationValue() {
		return observationValue;
	}

	public void setObservationValue(CCDACode observationValue) {
		this.observationValue = observationValue;
	}

	public CCDADataElement getReferenceText() {
		return referenceText;
	}

	public void setReferenceText(CCDADataElement referenceText) {
		this.referenceText = referenceText;
	}

	public CCDAPQ getAgeOnSetValue() {
		return ageOnSetValue;
	}

	public void setAgeOnSetValue(CCDAPQ ageOnSetValue) {
		this.ageOnSetValue = ageOnSetValue;
	}

	public CCDADataElement getAgeOnSetReferenceText() {
		return ageOnSetReferenceText;
	}

	public void setAgeOnSetReferenceText(CCDADataElement ageOnSetReferenceText) {
		this.ageOnSetReferenceText = ageOnSetReferenceText;
	}

	public CCDACode getCausedDeathValue() {
		return causedDeathValue;
	}

	public void setCausedDeathValue(CCDACode causedDeathValue) {
		this.causedDeathValue = causedDeathValue;
	}

	public CCDADataElement getCausedDeathReferenceText() {
		return causedDeathReferenceText;
	}

	public void setCausedDeathReferenceText(CCDADataElement causedDeathReferenceText) {
		this.causedDeathReferenceText = causedDeathReferenceText;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ageOnSetReferenceText == null) ? 0 : ageOnSetReferenceText.hashCode());
		result = prime * result + ((ageOnSetValue == null) ? 0 : ageOnSetValue.hashCode());
		result = prime * result + ((causedDeathReferenceText == null) ? 0 : causedDeathReferenceText.hashCode());
		result = prime * result + ((causedDeathValue == null) ? 0 : causedDeathValue.hashCode());
		result = prime * result + ((observationTime == null) ? 0 : observationTime.hashCode());
		result = prime * result + ((observationTypeCode == null) ? 0 : observationTypeCode.hashCode());
		result = prime * result + ((observationValue == null) ? 0 : observationValue.hashCode());
		result = prime * result + ((referenceText == null) ? 0 : referenceText.hashCode());
		result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
		result = prime * result + ((templateIds == null) ? 0 : templateIds.hashCode());
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
		CCDAFamilyHxObs other = (CCDAFamilyHxObs) obj;
		if (ageOnSetReferenceText == null) {
			if (other.ageOnSetReferenceText != null)
				return false;
		} else if (!ageOnSetReferenceText.equals(other.ageOnSetReferenceText))
			return false;
		if (ageOnSetValue == null) {
			if (other.ageOnSetValue != null)
				return false;
		} else if (!ageOnSetValue.equals(other.ageOnSetValue))
			return false;
		if (causedDeathReferenceText == null) {
			if (other.causedDeathReferenceText != null)
				return false;
		} else if (!causedDeathReferenceText.equals(other.causedDeathReferenceText))
			return false;
		if (causedDeathValue == null) {
			if (other.causedDeathValue != null)
				return false;
		} else if (!causedDeathValue.equals(other.causedDeathValue))
			return false;
		if (observationTime == null) {
			if (other.observationTime != null)
				return false;
		} else if (!observationTime.equals(other.observationTime))
			return false;
		if (observationTypeCode == null) {
			if (other.observationTypeCode != null)
				return false;
		} else if (!observationTypeCode.equals(other.observationTypeCode))
			return false;
		if (observationValue == null) {
			if (other.observationValue != null)
				return false;
		} else if (!observationValue.equals(other.observationValue))
			return false;
		if (referenceText == null) {
			if (other.referenceText != null)
				return false;
		} else if (!referenceText.equals(other.referenceText))
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
		return true;
	}
}
