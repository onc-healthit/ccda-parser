package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDALabResultObs extends CCDAXmlSnippet{

	private ArrayList<CCDAII>   			templateIds;
	private CCDACode						labCode;
	private CCDACode						statusCode;
	private CCDADataElement					measurementTime;
	private CCDAPQ							results;
	private CCDACode						resultCode;
	private CCDACode						interpretationCode;
	private ArrayList<CCDAPQ>		    	referenceRange;
	
	public CCDALabResultObs()
	{
		
	}

	public ArrayList<CCDAII> getTemplateIds() {
		return templateIds;
	}

	public void setTemplateIds(ArrayList<CCDAII> templateIds) {
		this.templateIds = templateIds;
	}

	public CCDACode getLabCode() {
		return labCode;
	}

	public void setLabCode(CCDACode labCode) {
		this.labCode = labCode;
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

	public CCDAPQ getResults() {
		return results;
	}

	public void setResults(CCDAPQ results) {
		this.results = results;
	}

	public CCDACode getResultCode() {
		return resultCode;
	}

	public void setResultCode(CCDACode resultCode) {
		this.resultCode = resultCode;
	}

	public CCDACode getInterpretationCode() {
		return interpretationCode;
	}

	public void setInterpretationCode(CCDACode interpretationCode) {
		this.interpretationCode = interpretationCode;
	}

	public ArrayList<CCDAPQ> getReferenceRange() {
		return referenceRange;
	}

	public void setReferenceRange(ArrayList<CCDAPQ> referenceRange) {
		this.referenceRange = referenceRange;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((interpretationCode == null) ? 0 : interpretationCode
						.hashCode());
		result = prime * result + ((labCode == null) ? 0 : labCode.hashCode());
		result = prime * result
				+ ((measurementTime == null) ? 0 : measurementTime.hashCode());
		result = prime * result
				+ ((referenceRange == null) ? 0 : referenceRange.hashCode());
		result = prime * result
				+ ((resultCode == null) ? 0 : resultCode.hashCode());
		result = prime * result + ((results == null) ? 0 : results.hashCode());
		result = prime * result
				+ ((statusCode == null) ? 0 : statusCode.hashCode());
		result = prime * result
				+ ((templateIds == null) ? 0 : templateIds.hashCode());
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
		CCDALabResultObs other = (CCDALabResultObs) obj;
		if (interpretationCode == null) {
			if (other.interpretationCode != null)
				return false;
		} else if (!interpretationCode.equals(other.interpretationCode))
			return false;
		if (labCode == null) {
			if (other.labCode != null)
				return false;
		} else if (!labCode.equals(other.labCode))
			return false;
		if (measurementTime == null) {
			if (other.measurementTime != null)
				return false;
		} else if (!measurementTime.equals(other.measurementTime))
			return false;
		if (referenceRange == null) {
			if (other.referenceRange != null)
				return false;
		} else if (!referenceRange.equals(other.referenceRange))
			return false;
		if (resultCode == null) {
			if (other.resultCode != null)
				return false;
		} else if (!resultCode.equals(other.resultCode))
			return false;
		if (results == null) {
			if (other.results != null)
				return false;
		} else if (!results.equals(other.results))
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

