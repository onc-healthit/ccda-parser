package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDAGoals extends CCDAXmlSnippet {
	
	private boolean isSectionNullFlavourWithNI;
	private ArrayList<CCDAII>    templateId;
	private CCDACode  sectionCode;
	private CCDADataElement narrativeText;
	
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
	public CCDADataElement getNarrativeText() {
		return narrativeText;
	}
	public void setNarrativeText(CCDADataElement narrativeText) {
		this.narrativeText = narrativeText;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((narrativeText == null) ? 0 : narrativeText.hashCode());
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
		CCDAGoals other = (CCDAGoals) obj;
		if (narrativeText == null) {
			if (other.narrativeText != null)
				return false;
		} else if (!narrativeText.equals(other.narrativeText))
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
