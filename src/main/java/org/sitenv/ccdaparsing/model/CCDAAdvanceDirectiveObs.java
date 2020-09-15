package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;
public class CCDAAdvanceDirectiveObs extends CCDAXmlSnippet{

	private ArrayList<CCDAII> templateIds;
	private CCDACode statusCode;
	private CCDACode observationTypeCode;
	private CCDAEffTime observationTime;
	private CCDACode observationValue;
	private CCDAParticipant careAgent;
	private CCDAParticipant verifier;
	private CCDADataElement referenceDocText;
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

	public CCDACode getObservationValue() {
		return observationValue;
	}

	public void setObservationValue(CCDACode observationValue) {
		this.observationValue = observationValue;
	}

	public CCDAParticipant getCareAgent() {
		return careAgent;
	}

	public void setCareAgent(CCDAParticipant careAgent) {
		this.careAgent = careAgent;
	}

	public CCDAParticipant getVerifier() {
		return verifier;
	}

	public void setVerifier(CCDAParticipant verifier) {
		this.verifier = verifier;
	}

	public CCDADataElement getReferenceDocText() {
		return referenceDocText;
	}

	public void setReferenceDocText(CCDADataElement referenceDocText) {
		this.referenceDocText = referenceDocText;
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
		result = prime * result + ((careAgent == null) ? 0 : careAgent.hashCode());
		result = prime * result + ((observationTime == null) ? 0 : observationTime.hashCode());
		result = prime * result + ((observationTypeCode == null) ? 0 : observationTypeCode.hashCode());
		result = prime * result + ((observationValue == null) ? 0 : observationValue.hashCode());
		result = prime * result + ((referenceDocText == null) ? 0 : referenceDocText.hashCode());
		result = prime * result + ((referenceText == null) ? 0 : referenceText.hashCode());
		result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
		result = prime * result + ((templateIds == null) ? 0 : templateIds.hashCode());
		result = prime * result + ((verifier == null) ? 0 : verifier.hashCode());
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
		CCDAAdvanceDirectiveObs other = (CCDAAdvanceDirectiveObs) obj;
		if (careAgent == null) {
			if (other.careAgent != null)
				return false;
		} else if (!careAgent.equals(other.careAgent))
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
		if (referenceDocText == null) {
			if (other.referenceDocText != null)
				return false;
		} else if (!referenceDocText.equals(other.referenceDocText))
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
		if (verifier == null) {
			if (other.verifier != null)
				return false;
		} else if (!verifier.equals(other.verifier))
			return false;
		return true;
	}
}
