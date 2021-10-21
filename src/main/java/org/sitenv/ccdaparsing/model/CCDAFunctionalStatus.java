package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;
import java.util.List;

public class CCDAFunctionalStatus extends CCDAXmlSnippet {
	private boolean isSectionNullFlavourWithNI;
	private ArrayList<CCDAII> templateIds;
	private CCDACode sectionCode;

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

	public ArrayList<String> getReferenceLinks() {
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
}
