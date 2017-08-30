package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDASmokingStatus extends CCDAXmlSnippet{

	private ArrayList<CCDAII>					smokingStatusTemplateIds;
	private CCDACode							smokingStatusCode;
	private CCDAEffTime						observationTime;
	private CCDADataElement referenceText;
	
	public CCDASmokingStatus()
	{
		
	}

	public ArrayList<CCDAII> getSmokingStatusTemplateIds() {
		return smokingStatusTemplateIds;
	}

	public void setSmokingStatusTemplateIds(ArrayList<CCDAII> smokingStatusTemplateIds) {
		this.smokingStatusTemplateIds = smokingStatusTemplateIds;
	}

	public CCDACode getSmokingStatusCode() {
		return smokingStatusCode;
	}

	public void setSmokingStatusCode(CCDACode smokingStatusCode) {
		this.smokingStatusCode = smokingStatusCode;
	}

	public CCDAEffTime getObservationTime() {
		return observationTime;
	}

	public void setObservationTime(CCDAEffTime observationTime) {
		this.observationTime = observationTime;
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
		result = prime * result
				+ ((observationTime == null) ? 0 : observationTime.hashCode());
		result = prime
				* result
				+ ((smokingStatusCode == null) ? 0 : smokingStatusCode
						.hashCode());
		result = prime
				* result
				+ ((smokingStatusTemplateIds == null) ? 0
						: smokingStatusTemplateIds.hashCode());
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
		CCDASmokingStatus other = (CCDASmokingStatus) obj;
		if (observationTime == null) {
			if (other.observationTime != null)
				return false;
		} else if (!observationTime.equals(other.observationTime))
			return false;
		if (referenceText == null) {
			if (other.referenceText != null)
				return false;
		} else if (!referenceText.equals(other.referenceText))
			return false;
		if (smokingStatusCode == null) {
			if (other.smokingStatusCode != null)
				return false;
		} else if (!smokingStatusCode.equals(other.smokingStatusCode))
			return false;
		if (smokingStatusTemplateIds == null) {
			if (other.smokingStatusTemplateIds != null)
				return false;
		} else if (!smokingStatusTemplateIds
				.equals(other.smokingStatusTemplateIds))
			return false;
		return true;
	}
}
