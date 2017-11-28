package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;
import java.util.List;

public class CCDAAllergy extends CCDAXmlSnippet {

	private boolean isSectionNullFlavourWithNI;
	private ArrayList<CCDAII>       		sectionTemplateId;
	private CCDACode                 		sectionCode;
	private ArrayList<CCDAAllergyConcern>	allergyConcern;
	private ArrayList<String> referenceLinks;
	private List<CCDAID> idList;
	
	public boolean isSectionNullFlavourWithNI() {
		return isSectionNullFlavourWithNI;
	}

	public void setSectionNullFlavourWithNI(boolean isSectionNullFlavourWithNI) {
		this.isSectionNullFlavourWithNI = isSectionNullFlavourWithNI;
	}
	
	public ArrayList<CCDAII> getSectionTemplateId() {
		return sectionTemplateId;
	}

	public void setSectionTemplateId(ArrayList<CCDAII> sectionTemplateId) {
		this.sectionTemplateId = sectionTemplateId;
	}

	public CCDACode getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(CCDACode sectionCode) {
		this.sectionCode = sectionCode;
	}

	public ArrayList<CCDAAllergyConcern> getAllergyConcern() {
		return allergyConcern;
	}

	public void setAllergyConcern(ArrayList<CCDAAllergyConcern> allergyConcern) {
		this.allergyConcern = allergyConcern;
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

	public CCDAAllergy()
	{
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((allergyConcern == null) ? 0 : allergyConcern.hashCode());
		result = prime * result
				+ ((sectionCode == null) ? 0 : sectionCode.hashCode());
		result = prime
				* result
				+ ((sectionTemplateId == null) ? 0 : sectionTemplateId
						.hashCode());
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
		CCDAAllergy other = (CCDAAllergy) obj;
		if (allergyConcern == null) {
			if (other.allergyConcern != null)
				return false;
		} else if (!allergyConcern.equals(other.allergyConcern))
			return false;
		if (sectionCode == null) {
			if (other.sectionCode != null)
				return false;
		} else if (!sectionCode.equals(other.sectionCode))
			return false;
		if (sectionTemplateId == null) {
			if (other.sectionTemplateId != null)
				return false;
		} else if (!sectionTemplateId.equals(other.sectionTemplateId))
			return false;
		return true;
	}
	
}
