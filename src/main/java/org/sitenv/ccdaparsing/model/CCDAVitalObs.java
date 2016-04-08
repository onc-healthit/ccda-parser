package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDAVitalObs {
	private ArrayList<CCDAII>   			templateIds;
	private CCDACode						vsCode;
	private CCDACode						statusCode;
	private CCDADataElement					measurementTime;
	private CCDAPQ							vsResult;
	private CCDACode						interpretationCode;
	private ArrayList<CCDAEffTime>			referenceValue;
	
	public CCDAVitalObs()
	{
		
	}

	public ArrayList<CCDAII> getTemplateIds() {
		return templateIds;
	}

	public void setTemplateIds(ArrayList<CCDAII> templateIds) {
		this.templateIds = templateIds;
	}

	public CCDACode getVsCode() {
		return vsCode;
	}

	public void setVsCode(CCDACode vsCode) {
		this.vsCode = vsCode;
	}

	public CCDACode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(CCDACode statusCode) {
		this.statusCode = statusCode;
	}

	public CCDADataElement getMeasurementTime() {
		return measurementTime;
	}

	public void setMeasurementTime(CCDADataElement measurementTime) {
		this.measurementTime = measurementTime;
	}

	public CCDAPQ getVsResult() {
		return vsResult;
	}

	public void setVsResult(CCDAPQ vsResult) {
		this.vsResult = vsResult;
	}

	public CCDACode getInterpretationCode() {
		return interpretationCode;
	}

	public void setInterpretationCode(CCDACode interpretationCode) {
		this.interpretationCode = interpretationCode;
	}

	public ArrayList<CCDAEffTime> getReferenceValue() {
		return referenceValue;
	}

	public void setReferenceValue(ArrayList<CCDAEffTime> referenceValue) {
		this.referenceValue = referenceValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((interpretationCode == null) ? 0 : interpretationCode
						.hashCode());
		result = prime * result
				+ ((measurementTime == null) ? 0 : measurementTime.hashCode());
		result = prime * result
				+ ((referenceValue == null) ? 0 : referenceValue.hashCode());
		result = prime * result
				+ ((statusCode == null) ? 0 : statusCode.hashCode());
		result = prime * result
				+ ((templateIds == null) ? 0 : templateIds.hashCode());
		result = prime * result + ((vsCode == null) ? 0 : vsCode.hashCode());
		result = prime * result
				+ ((vsResult == null) ? 0 : vsResult.hashCode());
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
		CCDAVitalObs other = (CCDAVitalObs) obj;
		if (interpretationCode == null) {
			if (other.interpretationCode != null)
				return false;
		} else if (!interpretationCode.equals(other.interpretationCode))
			return false;
		if (measurementTime == null) {
			if (other.measurementTime != null)
				return false;
		} else if (!measurementTime.equals(other.measurementTime))
			return false;
		if (referenceValue == null) {
			if (other.referenceValue != null)
				return false;
		} else if (!referenceValue.equals(other.referenceValue))
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
		if (vsCode == null) {
			if (other.vsCode != null)
				return false;
		} else if (!vsCode.equals(other.vsCode))
			return false;
		if (vsResult == null) {
			if (other.vsResult != null)
				return false;
		} else if (!vsResult.equals(other.vsResult))
			return false;
		return true;
	}

}

