package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDASocialHistory extends CCDAXmlSnippet{

	private boolean isSectionNullFlavourWithNI;
	private ArrayList<CCDAII>					sectionTemplateIds;
	private CCDACode							sectionCode;
	private ArrayList<CCDASmokingStatus>		smokingStatus;
	private ArrayList<CCDATobaccoUse>			tobaccoUse;
	private CCDASocialHistoryGenderObs          socialHistoryGenderObs;
	private ArrayList<String> referenceLinks;
	
	public boolean isSectionNullFlavourWithNI() {
		return isSectionNullFlavourWithNI;
	}

	public void setSectionNullFlavourWithNI(boolean isSectionNullFlavourWithNI) {
		this.isSectionNullFlavourWithNI = isSectionNullFlavourWithNI;
	}
	
	public CCDASocialHistory()
	{
		
	}

	public ArrayList<CCDAII> getSectionTemplateIds() {
		return sectionTemplateIds;
	}

	public void setSectionTemplateIds(ArrayList<CCDAII> sectionTemplateIds) {
		this.sectionTemplateIds = sectionTemplateIds;
	}

	public CCDACode getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(CCDACode sectionCode) {
		this.sectionCode = sectionCode;
	}

	public ArrayList<CCDASmokingStatus> getSmokingStatus() {
		return smokingStatus;
	}

	public void setSmokingStatus(ArrayList<CCDASmokingStatus> smokingStatus) {
		this.smokingStatus = smokingStatus;
	}

	public ArrayList<CCDATobaccoUse> getTobaccoUse() {
		return tobaccoUse;
	}

	public void setTobaccoUse(ArrayList<CCDATobaccoUse> tobaccoUse) {
		this.tobaccoUse = tobaccoUse;
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
	
	public CCDASocialHistoryGenderObs getSocialHistoryGenderObs() {
		return socialHistoryGenderObs;
	}

	public void setSocialHistoryGenderObs(CCDASocialHistoryGenderObs socialHistoryGenderObs) {
		this.socialHistoryGenderObs = socialHistoryGenderObs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sectionCode == null) ? 0 : sectionCode.hashCode());
		result = prime
				* result
				+ ((sectionTemplateIds == null) ? 0 : sectionTemplateIds
						.hashCode());
		result = prime * result
				+ ((smokingStatus == null) ? 0 : smokingStatus.hashCode());
		result = prime * result
				+ ((tobaccoUse == null) ? 0 : tobaccoUse.hashCode());
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
		CCDASocialHistory other = (CCDASocialHistory) obj;
		if (sectionCode == null) {
			if (other.sectionCode != null)
				return false;
		} else if (!sectionCode.equals(other.sectionCode))
			return false;
		if (sectionTemplateIds == null) {
			if (other.sectionTemplateIds != null)
				return false;
		} else if (!sectionTemplateIds.equals(other.sectionTemplateIds))
			return false;
		if (smokingStatus == null) {
			if (other.smokingStatus != null)
				return false;
		} else if (!smokingStatus.equals(other.smokingStatus))
			return false;
		if (tobaccoUse == null) {
			if (other.tobaccoUse != null)
				return false;
		} else if (!tobaccoUse.equals(other.tobaccoUse))
			return false;
		return true;
	}
	
	
	
}
