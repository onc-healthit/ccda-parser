package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;
import java.util.List;

public class CCDAEncounter extends CCDAXmlSnippet {

	private boolean isSectionNullFlavourWithNI;
	private ArrayList<CCDAII>    templateId;
	private CCDACode  sectionCode;
	private ArrayList<CCDAEncounterActivity> encActivities;
	private ArrayList<String> referenceLinks;
	private List<CCDAID> idLIst;
	
	public boolean isSectionNullFlavourWithNI() {
		return isSectionNullFlavourWithNI;
	}

	public void setSectionNullFlavourWithNI(boolean isSectionNullFlavourWithNI) {
		this.isSectionNullFlavourWithNI = isSectionNullFlavourWithNI;
	}

	public ArrayList<CCDAII> getTemplateId() {
		return templateId;
	}

	public void setTemplateId(ArrayList<CCDAII> templateId) {
		this.templateId = templateId;
	}

	public CCDACode getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(CCDACode sectionCode) {
		this.sectionCode = sectionCode;
	}

	public ArrayList<CCDAEncounterActivity> getEncActivities() {
		return encActivities;
	}

	public void setEncActivities(ArrayList<CCDAEncounterActivity> encActivities) {
		this.encActivities = encActivities;
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
	
	public List<CCDAID> getIdLIst() {
		return idLIst;
	}

	public void setIdLIst(List<CCDAID> idLIst) {
		this.idLIst = idLIst;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((encActivities == null) ? 0 : encActivities.hashCode());
		result = prime * result
				+ ((sectionCode == null) ? 0 : sectionCode.hashCode());
		result = prime * result
				+ ((templateId == null) ? 0 : templateId.hashCode());
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
		CCDAEncounter other = (CCDAEncounter) obj;
		if (encActivities == null) {
			if (other.encActivities != null)
				return false;
		} else if (!encActivities.equals(other.encActivities))
			return false;
		if (sectionCode == null) {
			if (other.sectionCode != null)
				return false;
		} else if (!sectionCode.equals(other.sectionCode))
			return false;
		if (templateId == null) {
			if (other.templateId != null)
				return false;
		} else if (!templateId.equals(other.templateId))
			return false;
		return true;
	}

}
