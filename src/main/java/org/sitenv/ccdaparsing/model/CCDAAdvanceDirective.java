package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;
import java.util.List;

public class CCDAAdvanceDirective extends CCDAXmlSnippet {
	private boolean isSectionNullFlavourWithNI;
	private ArrayList<CCDAII> templateIds;
	private CCDACode sectionCode;
	private ArrayList<CCDAAdvanceDirectiveOrg> advanceDirectiveOrgs;
	private ArrayList<String> referenceLinks;
	private List<CCDAID> idList;

	public boolean isSectionNullFlavourWithNI() {
		return isSectionNullFlavourWithNI;
	}

	public void setSectionNullFlavourWithNI(boolean sectionNullFlavourWithNI) {
		isSectionNullFlavourWithNI = sectionNullFlavourWithNI;
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

	public ArrayList<CCDAAdvanceDirectiveOrg> getAdvanceDirectiveOrgs() {
		return advanceDirectiveOrgs;
	}

	public void setAdvanceDirectiveOrgs(ArrayList<CCDAAdvanceDirectiveOrg> advanceDirectiveOrgs) {
		this.advanceDirectiveOrgs = advanceDirectiveOrgs;
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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((advanceDirectiveOrgs == null) ? 0 : advanceDirectiveOrgs.hashCode());
		result = prime * result + ((sectionCode == null) ? 0 : sectionCode.hashCode());
		result = prime * result + ((templateIds == null) ? 0 : templateIds.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CCDAAdvanceDirective other = (CCDAAdvanceDirective) obj;
		if (advanceDirectiveOrgs == null) {
			if (other.advanceDirectiveOrgs != null)
				return false;
		} else if (!advanceDirectiveOrgs.equals(other.advanceDirectiveOrgs))
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
