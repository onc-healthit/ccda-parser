package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDALabResult extends CCDAXmlSnippet{

	private ArrayList<CCDAII>			resultSectionTempalteIds;
	private CCDACode					sectionCode;
	private ArrayList<CCDALabResultOrg>	resultOrg;
	private Boolean						isLabTestInsteadOfResult;
	private ArrayList<String> referenceLinks;
	
	public CCDALabResult()
	{
		
	}

	public Boolean getIsLabTestInsteadOfResult() {
		return isLabTestInsteadOfResult;
	}

	public void setIsLabTestInsteadOfResult(Boolean isLabTestInsteadOfResult) {
		this.isLabTestInsteadOfResult = isLabTestInsteadOfResult;
	}

	public ArrayList<CCDAII> getResultSectionTempalteIds() {
		return resultSectionTempalteIds;
	}

	public void setResultSectionTempalteIds(ArrayList<CCDAII> resultSectionTempalteIds) {
		this.resultSectionTempalteIds = resultSectionTempalteIds;
	}

	public CCDACode getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(CCDACode sectionCode) {
		this.sectionCode = sectionCode;
	}

	public ArrayList<CCDALabResultOrg> getResultOrg() {
		if(resultOrg == null)
		{
			resultOrg = new ArrayList<CCDALabResultOrg>();
		}
		return resultOrg;
	}

	public void setResultOrg(ArrayList<CCDALabResultOrg> resultOrg) {
		this.resultOrg = resultOrg;
	}
	
	public ArrayList<String> getReferenceLinks() {
		if(referenceLinks == null)
		{
			this.referenceLinks = new ArrayList<String>();
		}
		return referenceLinks;
	}

	public void setReferenceLinks(ArrayList<String> referenceLinks) {
		this.referenceLinks = referenceLinks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((isLabTestInsteadOfResult == null) ? 0
						: isLabTestInsteadOfResult.hashCode());
		result = prime * result
				+ ((resultOrg == null) ? 0 : resultOrg.hashCode());
		result = prime
				* result
				+ ((resultSectionTempalteIds == null) ? 0
						: resultSectionTempalteIds.hashCode());
		result = prime * result
				+ ((sectionCode == null) ? 0 : sectionCode.hashCode());
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
		CCDALabResult other = (CCDALabResult) obj;
		if (isLabTestInsteadOfResult == null) {
			if (other.isLabTestInsteadOfResult != null)
				return false;
		} else if (!isLabTestInsteadOfResult
				.equals(other.isLabTestInsteadOfResult))
			return false;
		if (resultOrg == null) {
			if (other.resultOrg != null)
				return false;
		} else if (!resultOrg.equals(other.resultOrg))
			return false;
		if (resultSectionTempalteIds == null) {
			if (other.resultSectionTempalteIds != null)
				return false;
		} else if (!resultSectionTempalteIds
				.equals(other.resultSectionTempalteIds))
			return false;
		if (sectionCode == null) {
			if (other.sectionCode != null)
				return false;
		} else if (!sectionCode.equals(other.sectionCode))
			return false;
		return true;
	}
}
