package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;
import java.util.List;

public class CCDAFamilyHistory extends CCDAXmlSnippet {

	private boolean isSectionNullFlavourWithNI;
	private ArrayList<CCDAII> templateIds;
	private CCDACode sectionCode;
	private ArrayList<CCDAFamilyHxOrg> familyHxOrg;
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

	public ArrayList<CCDAFamilyHxOrg> getFamilyHxOrg() {
		return familyHxOrg;
	}

	public void setFamilyHxOrg(ArrayList<CCDAFamilyHxOrg> familyHxOrg) {
		this.familyHxOrg = familyHxOrg;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;

		CCDAFamilyHistory that = (CCDAFamilyHistory) o;

		if (templateIds != null ? !templateIds.equals(that.templateIds) : that.templateIds != null) return false;
		if (sectionCode != null ? !sectionCode.equals(that.sectionCode) : that.sectionCode != null) return false;
		return familyHxOrg != null ? familyHxOrg.equals(that.familyHxOrg) : that.familyHxOrg == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (templateIds != null ? templateIds.hashCode() : 0);
		result = 31 * result + (sectionCode != null ? sectionCode.hashCode() : 0);
		result = 31 * result + (familyHxOrg != null ? familyHxOrg.hashCode() : 0);
		return result;
	}
}
