package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDAAllergyReaction extends CCDAXmlSnippet {
	
	private ArrayList<CCDAII>			templateIds;
	private CCDACode					reactionCode;
	private CCDADataElement referenceText;
	private CCDAAllergySeverity			severity;
	
	public CCDADataElement getReferenceText() {
		return referenceText;
	}

	public void setReferenceText(CCDADataElement referenceText) {
		this.referenceText = referenceText;
	}

	public ArrayList<CCDAII> getTemplateIds() {
		return templateIds;
	}

	public void setTemplateIds(ArrayList<CCDAII> templateIds) {
		this.templateIds = templateIds;
	}

	public CCDACode getReactionCode() {
		return reactionCode;
	}

	public void setReactionCode(CCDACode reactionCode) {
		this.reactionCode = reactionCode;
	}
	
	public CCDAAllergySeverity getSeverity() {
		return severity;
	}

	public void setSeverity(CCDAAllergySeverity severity) {
		this.severity = severity;
	}

	public CCDAAllergyReaction()
	{
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((reactionCode == null) ? 0 : reactionCode.hashCode());
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
		CCDAAllergyReaction other = (CCDAAllergyReaction) obj;
		if (reactionCode == null) {
			if (other.reactionCode != null)
				return false;
		} else if (!reactionCode.equals(other.reactionCode))
			return false;
		if (templateIds == null) {
			if (other.templateIds != null)
				return false;
		} else if (!templateIds.equals(other.templateIds))
			return false;
		return true;
	}
}
