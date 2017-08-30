package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDASocialHistoryGenderObs extends CCDAXmlSnippet {

	private ArrayList<CCDAII> genderObservationTemplateIds;
	private CCDACode genderCode;
	private CCDACode genderValue;
	private CCDADataElement referenceText;

	public ArrayList<CCDAII> getGenderObservationTemplateIds() {
		return genderObservationTemplateIds;
	}

	public void setGenderObservationTemplateIds(ArrayList<CCDAII> genderObservationTemplateIds) {
		this.genderObservationTemplateIds = genderObservationTemplateIds;
	}

	public CCDACode getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(CCDACode genderCode) {
		this.genderCode = genderCode;
	}

	public CCDACode getGenderValue() {
		return genderValue;
	}

	public void setGenderValue(CCDACode genderValue) {
		this.genderValue = genderValue;
	}

	public CCDADataElement getReferenceText() {
		return referenceText;
	}

	public void setReferenceText(CCDADataElement referenceText) {
		this.referenceText = referenceText;
	}
}
