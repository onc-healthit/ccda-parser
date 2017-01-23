package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDAVitalSigns extends CCDAXmlSnippet{
	
	private boolean isSectionNullFlavourWithNI;
	private ArrayList<CCDAII>			templateIds;
	private CCDACode					sectionCode;
	private ArrayList<CCDAVitalOrg>		vitalsOrg;
	private ArrayList<String> referenceLinks;
	
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

	public ArrayList<CCDAVitalOrg> getVitalsOrg() {
		return vitalsOrg;
	}

	public void setVitalsOrg(ArrayList<CCDAVitalOrg> vitalsOrg) {
		this.vitalsOrg = vitalsOrg;
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

	public CCDAVitalSigns()
	{
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sectionCode == null) ? 0 : sectionCode.hashCode());
		result = prime * result
				+ ((templateIds == null) ? 0 : templateIds.hashCode());
		result = prime * result
				+ ((vitalsOrg == null) ? 0 : vitalsOrg.hashCode());
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
		CCDAVitalSigns other = (CCDAVitalSigns) obj;
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
		if (vitalsOrg == null) {
			if (other.vitalsOrg != null)
				return false;
		} else if (!vitalsOrg.equals(other.vitalsOrg))
			return false;
		return true;
	}
	
	
}
