package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDAEncounterDiagnosis extends CCDAXmlSnippet {
   
	private ArrayList<CCDAII>         templateId;
	private CCDACode                  entryCode;
	private ArrayList<CCDAProblemObs> problemObs;

	
	public ArrayList<CCDAII> getTemplateId() {
		return templateId;
	}


	public void setTemplateId(ArrayList<CCDAII> templateId) {
		this.templateId = templateId;
	}


	public CCDACode getEntryCode() {
		return entryCode;
	}


	public void setEntryCode(CCDACode entryCode) {
		this.entryCode = entryCode;
	}


	public ArrayList<CCDAProblemObs> getProblemObs() {
		return problemObs;
	}


	public void setProblemObs(ArrayList<CCDAProblemObs> problemObs) {
		this.problemObs = problemObs;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((entryCode == null) ? 0 : entryCode.hashCode());
		result = prime * result
				+ ((problemObs == null) ? 0 : problemObs.hashCode());
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
		CCDAEncounterDiagnosis other = (CCDAEncounterDiagnosis) obj;
		if (entryCode == null) {
			if (other.entryCode != null)
				return false;
		} else if (!entryCode.equals(other.entryCode))
			return false;
		if (problemObs == null) {
			if (other.problemObs != null)
				return false;
		} else if (!problemObs.equals(other.problemObs))
			return false;
		if (templateId == null) {
			if (other.templateId != null)
				return false;
		} else if (!templateId.equals(other.templateId))
			return false;
		return true;
	}
	
}
