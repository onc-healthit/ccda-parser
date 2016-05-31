package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDATobaccoUse extends CCDAXmlSnippet{
	private ArrayList<CCDAII>					tobaccoUseTemplateIds;
	private CCDACode							tobaccoUseSectionCode;
	private CCDACode							tobaccoUseCode;
	private CCDAEffTime							tobaccoUseTime;
	
	public ArrayList<CCDAII> getTobaccoUseTemplateIds() {
		return tobaccoUseTemplateIds;
	}

	public void setTobaccoUseTemplateIds(ArrayList<CCDAII> tobaccoUseTemplateIds) {
		this.tobaccoUseTemplateIds = tobaccoUseTemplateIds;
	}

	public CCDACode getTobaccoUseSectionCode() {
		return tobaccoUseSectionCode;
	}

	public void setTobaccoUseSectionCode(CCDACode tobaccoUseSectionCode) {
		this.tobaccoUseSectionCode = tobaccoUseSectionCode;
	}

	public CCDACode getTobaccoUseCode() {
		return tobaccoUseCode;
	}

	public void setTobaccoUseCode(CCDACode tobaccoUseCode) {
		this.tobaccoUseCode = tobaccoUseCode;
	}

	public CCDAEffTime getTobaccoUseTime() {
		return tobaccoUseTime;
	}

	public void setTobaccoUseTime(CCDAEffTime tobaccoUseTime) {
		this.tobaccoUseTime = tobaccoUseTime;
	}

	public CCDATobaccoUse()
	{
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tobaccoUseCode == null) ? 0 : tobaccoUseCode.hashCode());
		result = prime
				* result
				+ ((tobaccoUseSectionCode == null) ? 0 : tobaccoUseSectionCode
						.hashCode());
		result = prime
				* result
				+ ((tobaccoUseTemplateIds == null) ? 0 : tobaccoUseTemplateIds
						.hashCode());
		result = prime * result
				+ ((tobaccoUseTime == null) ? 0 : tobaccoUseTime.hashCode());
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
		CCDATobaccoUse other = (CCDATobaccoUse) obj;
		if (tobaccoUseCode == null) {
			if (other.tobaccoUseCode != null)
				return false;
		} else if (!tobaccoUseCode.equals(other.tobaccoUseCode))
			return false;
		if (tobaccoUseSectionCode == null) {
			if (other.tobaccoUseSectionCode != null)
				return false;
		} else if (!tobaccoUseSectionCode.equals(other.tobaccoUseSectionCode))
			return false;
		if (tobaccoUseTemplateIds == null) {
			if (other.tobaccoUseTemplateIds != null)
				return false;
		} else if (!tobaccoUseTemplateIds.equals(other.tobaccoUseTemplateIds))
			return false;
		if (tobaccoUseTime == null) {
			if (other.tobaccoUseTime != null)
				return false;
		} else if (!tobaccoUseTime.equals(other.tobaccoUseTime))
			return false;
		return true;
	}
	
	
}
