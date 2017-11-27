package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;
import java.util.List;

public class CCDAImmunization extends CCDAXmlSnippet {
	
	private boolean isSectionNullFlavourWithNI;
	private ArrayList<CCDAII>     				templateIds;
	private CCDACode                 			sectionCode;
	private ArrayList<CCDAImmunizationActivity> immActivity;
	private ArrayList<String> referenceLinks;
	private List<CCDAID> idList;
	
	public boolean isSectionNullFlavourWithNI() {
		return isSectionNullFlavourWithNI;
	}

	public void setSectionNullFlavourWithNI(boolean isSectionNullFlavourWithNI) {
		this.isSectionNullFlavourWithNI = isSectionNullFlavourWithNI;
	}
	
	public ArrayList<CCDAII> getTemplateIds() {
		return templateIds;
	}

	public void setTemplateIds(ArrayList<CCDAII> templateIds) {
		this.templateIds = templateIds;
	}

	public CCDACode getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(CCDACode sectionCode) {
		this.sectionCode = sectionCode;
	}

	public ArrayList<CCDAImmunizationActivity> getImmActivity() {
		return immActivity;
	}

	public void setImmActivity(ArrayList<CCDAImmunizationActivity> immActivity) {
		this.immActivity = immActivity;
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
	
	public List<CCDAID> getIdList() {
		return idList;
	}

	public void setIdList(List<CCDAID> idList) {
		this.idList = idList;
	}

	public CCDAImmunization() 
	{
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((immActivity == null) ? 0 : immActivity.hashCode());
		result = prime * result
				+ ((sectionCode == null) ? 0 : sectionCode.hashCode());
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
		CCDAImmunization other = (CCDAImmunization) obj;
		if (immActivity == null) {
			if (other.immActivity != null)
				return false;
		} else if (!immActivity.equals(other.immActivity))
			return false;
		if (sectionCode == null) {
			if (other.sectionCode != null)
				return false;
		} else if (!sectionCode.equals(other.sectionCode))
			return false;
		if (templateIds == null) {
			if (other.templateIds != null)
				return false;
		} else if (!templateIds.equals(other.templateIds))
			return false;
		return true;
	}
}
