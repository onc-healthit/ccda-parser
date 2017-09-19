package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDAVitalOrg extends CCDAXmlSnippet{

	private ArrayList<CCDAII>   			templateIds;
	private CCDACode						statusCode;
	private CCDACode						orgCode;
	private CCDACode						translationCode;
	private CCDAEffTime						effTime;
	private ArrayList<CCDAVitalObs>			vitalObs;
	private CCDADataElement referenceText;
	
	public CCDAVitalOrg()
	{
		
	}

	public ArrayList<CCDAII> getTemplateIds() {
		return templateIds;
	}

	public void setTemplateIds(ArrayList<CCDAII> templateIds) {
		this.templateIds = templateIds;
	}

	public CCDACode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(CCDACode statusCode) {
		this.statusCode = statusCode;
	}

	public CCDACode getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(CCDACode orgCode) {
		this.orgCode = orgCode;
	}

	public CCDACode getTranslationCode() {
		return translationCode;
	}

	public void setTranslationCode(CCDACode translationCode) {
		this.translationCode = translationCode;
	}

	public CCDAEffTime getEffTime() {
		return effTime;
	}

	public void setEffTime(CCDAEffTime effTime) {
		this.effTime = effTime;
	}

	public ArrayList<CCDAVitalObs> getVitalObs() {
		return vitalObs;
	}

	public void setVitalObs(ArrayList<CCDAVitalObs> vitalObs) {
		this.vitalObs = vitalObs;
	}
	
	public CCDADataElement getReferenceText() {
		return referenceText;
	}

	public void setReferenceText(CCDADataElement referenceText) {
		this.referenceText = referenceText;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((effTime == null) ? 0 : effTime.hashCode());
		result = prime * result + ((orgCode == null) ? 0 : orgCode.hashCode());
		result = prime * result
				+ ((statusCode == null) ? 0 : statusCode.hashCode());
		result = prime * result
				+ ((templateIds == null) ? 0 : templateIds.hashCode());
		result = prime * result
				+ ((translationCode == null) ? 0 : translationCode.hashCode());
		result = prime * result
				+ ((vitalObs == null) ? 0 : vitalObs.hashCode());
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
		CCDAVitalOrg other = (CCDAVitalOrg) obj;
		if (effTime == null) {
			if (other.effTime != null)
				return false;
		} else if (!effTime.equals(other.effTime))
			return false;
		if (orgCode == null) {
			if (other.orgCode != null)
				return false;
		} else if (!orgCode.equals(other.orgCode))
			return false;
		if (statusCode == null) {
			if (other.statusCode != null)
				return false;
		} else if (!statusCode.equals(other.statusCode))
			return false;
		if (templateIds == null) {
			if (other.templateIds != null)
				return false;
		} else if (!templateIds.equals(other.templateIds))
			return false;
		if (translationCode == null) {
			if (other.translationCode != null)
				return false;
		} else if (!translationCode.equals(other.translationCode))
			return false;
		if (vitalObs == null) {
			if (other.vitalObs != null)
				return false;
		} else if (!vitalObs.equals(other.vitalObs))
			return false;
		return true;
	}
	
	
	
	
}
