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
}
