package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDAEncounterActivity extends CCDAXmlSnippet {

	private ArrayList<CCDAII>                 templateId;
	private CCDACode                          encounterTypeCode;
	private CCDADataElement                       effectiveTime;
	private ArrayList<CCDAEncounterDiagnosis> diagnoses;
	private ArrayList<CCDAServiceDeliveryLoc> sdLocs;
	private ArrayList<CCDAProblemObs> indications;
	
	public ArrayList<CCDAII> getTemplateId() {
		return templateId;
	}

	public void setTemplateId(ArrayList<CCDAII> templateId) {
		this.templateId = templateId;
	}

	public CCDACode getEncounterTypeCode() {
		return encounterTypeCode;
	}

	public void setEncounterTypeCode(CCDACode encounterTypeCode) {
		this.encounterTypeCode = encounterTypeCode;
	}

	public CCDADataElement getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(CCDADataElement effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public ArrayList<CCDAEncounterDiagnosis> getDiagnoses() {
		return diagnoses;
	}

	public void setDiagnoses(ArrayList<CCDAEncounterDiagnosis> diagnoses) {
		this.diagnoses = diagnoses;
	}

	public ArrayList<CCDAServiceDeliveryLoc> getSdLocs() {
		return sdLocs;
	}

	public void setSdLocs(ArrayList<CCDAServiceDeliveryLoc> sdLocs) {
		this.sdLocs = sdLocs;
	}

	public ArrayList<CCDAProblemObs> getIndications() {
		return indications;
	}

	public void setIndications(ArrayList<CCDAProblemObs> indications) {
		this.indications = indications;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((diagnoses == null) ? 0 : diagnoses.hashCode());
		result = prime * result
				+ ((effectiveTime == null) ? 0 : effectiveTime.hashCode());
		result = prime
				* result
				+ ((encounterTypeCode == null) ? 0 : encounterTypeCode
						.hashCode());
		result = prime * result
				+ ((indications == null) ? 0 : indications.hashCode());
		result = prime * result + ((sdLocs == null) ? 0 : sdLocs.hashCode());
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
		CCDAEncounterActivity other = (CCDAEncounterActivity) obj;
		if (diagnoses == null) {
			if (other.diagnoses != null)
				return false;
		} else if (!diagnoses.equals(other.diagnoses))
			return false;
		if (effectiveTime == null) {
			if (other.effectiveTime != null)
				return false;
		} else if (!effectiveTime.equals(other.effectiveTime))
			return false;
		if (encounterTypeCode == null) {
			if (other.encounterTypeCode != null)
				return false;
		} else if (!encounterTypeCode.equals(other.encounterTypeCode))
			return false;
		if (indications == null) {
			if (other.indications != null)
				return false;
		} else if (!indications.equals(other.indications))
			return false;
		if (sdLocs == null) {
			if (other.sdLocs != null)
				return false;
		} else if (!sdLocs.equals(other.sdLocs))
			return false;
		if (templateId == null) {
			if (other.templateId != null)
				return false;
		} else if (!templateId.equals(other.templateId))
			return false;
		return true;
	}
}
