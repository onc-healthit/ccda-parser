package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDAMedication extends CCDAXmlSnippet{

	private ArrayList<CCDAII>     				templateIds;
	private CCDACode                 			sectionCode;
	private ArrayList<CCDAMedicationActivity>  	medActivities;
	private ArrayList<String> referenceLinks;
	
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

	public CCDAMedication()
	{
		
	}

	public ArrayList<CCDAMedicationActivity> getMedActivities() {
		return medActivities;
	}

	public void setMedActivities(ArrayList<CCDAMedicationActivity> medActivities) {
		this.medActivities = medActivities;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((medActivities == null) ? 0 : medActivities.hashCode());
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
		CCDAMedication other = (CCDAMedication) obj;
		if (medActivities == null) {
			if (other.medActivities != null)
				return false;
		} else if (!medActivities.equals(other.medActivities))
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
