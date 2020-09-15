package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDAAdvanceDirectiveOrg extends CCDAXmlSnippet {
	private ArrayList<CCDAII> templateIds;
	private CCDACode orgCode;
	private CCDACode statusCode;
	private ArrayList<CCDAAdvanceDirectiveObs> advanceDirectiveObs;
	private CCDADataElement referenceText;

	public ArrayList<CCDAII> getTemplateIds() {
		return templateIds;
	}

	public void setTemplateIds(ArrayList<CCDAII> templateIds) {
		this.templateIds = templateIds;
	}

	public CCDACode getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(CCDACode orgCode) {
		this.orgCode = orgCode;
	}

	public CCDACode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(CCDACode statusCode) {
		this.statusCode = statusCode;
	}

	public ArrayList<CCDAAdvanceDirectiveObs> getAdvanceDirectiveObs() {
		return advanceDirectiveObs;
	}

	public void setAdvanceDirectiveObs(ArrayList<CCDAAdvanceDirectiveObs> advanceDirectiveObs) {
		this.advanceDirectiveObs = advanceDirectiveObs;
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
		int result = super.hashCode();
		result = prime * result + ((advanceDirectiveObs == null) ? 0 : advanceDirectiveObs.hashCode());
		result = prime * result + ((orgCode == null) ? 0 : orgCode.hashCode());
		result = prime * result + ((referenceText == null) ? 0 : referenceText.hashCode());
		result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
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
		CCDAAdvanceDirectiveOrg other = (CCDAAdvanceDirectiveOrg) obj;
		if (advanceDirectiveObs == null) {
			if (other.advanceDirectiveObs != null)
				return false;
		} else if (!advanceDirectiveObs.equals(other.advanceDirectiveObs))
			return false;
		if (orgCode == null) {
			if (other.orgCode != null)
				return false;
		} else if (!orgCode.equals(other.orgCode))
			return false;
		if (referenceText == null) {
			if (other.referenceText != null)
				return false;
		} else if (!referenceText.equals(other.referenceText))
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
		return true;
	}
}
