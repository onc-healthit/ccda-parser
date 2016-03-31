package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;


public class CCDAProcActProc {
	private ArrayList<CCDAII>       		sectionTemplateId;
	private CCDACode                 		procCode;
	private CCDACode						procStatus;
	private CCDACode						targetSiteCode;
	private ArrayList<CCDAAssignedEntity>  	performer;
	private ArrayList<CCDAServiceDeliveryLoc>  	sdLocs;
	private CCDAII								piTemplateId;
	private CCDAII								udi;
	private CCDACode							deviceCode;
	private CCDAII								scopingEntityId;
	private ArrayList<CCDAUDI>                 patientUDI;
	
	public CCDAProcActProc()
	{
		
	}

	public ArrayList<CCDAII> getSectionTemplateId() {
		return sectionTemplateId;
	}

	public void setSectionTemplateId(ArrayList<CCDAII> sectionTemplateId) {
		this.sectionTemplateId = sectionTemplateId;
	}

	public CCDACode getProcCode() {
		return procCode;
	}

	public void setProcCode(CCDACode procCode) {
		this.procCode = procCode;
	}

	public CCDACode getProcStatus() {
		return procStatus;
	}

	public void setProcStatus(CCDACode procStatus) {
		this.procStatus = procStatus;
	}

	public CCDACode getTargetSiteCode() {
		return targetSiteCode;
	}

	public void setTargetSiteCode(CCDACode targetSiteCode) {
		this.targetSiteCode = targetSiteCode;
	}

	public ArrayList<CCDAAssignedEntity> getPerformer() {
		return performer;
	}

	public void setPerformer(ArrayList<CCDAAssignedEntity> performer) {
		this.performer = performer;
	}

	public ArrayList<CCDAServiceDeliveryLoc> getSdLocs() {
		return sdLocs;
	}

	public void setSdLocs(ArrayList<CCDAServiceDeliveryLoc> sdLocs) {
		this.sdLocs = sdLocs;
	}

	public CCDAII getPiTemplateId() {
		return piTemplateId;
	}

	public void setPiTemplateId(CCDAII piTemplateId) {
		this.piTemplateId = piTemplateId;
	}

	public CCDAII getUdi() {
		return udi;
	}

	public void setUdi(CCDAII udi) {
		this.udi = udi;
	}

	public CCDACode getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(CCDACode deviceCode) {
		this.deviceCode = deviceCode;
	}

	public CCDAII getScopingEntityId() {
		return scopingEntityId;
	}

	public void setScopingEntityId(CCDAII scopingEntityId) {
		this.scopingEntityId = scopingEntityId;
	}

	public ArrayList<CCDAUDI> getPatientUDI() {
		return patientUDI;
	}

	public void setPatientUDI(ArrayList<CCDAUDI> patientUDI) {
		this.patientUDI = patientUDI;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deviceCode == null) ? 0 : deviceCode.hashCode());
		result = prime * result
				+ ((patientUDI == null) ? 0 : patientUDI.hashCode());
		result = prime * result
				+ ((performer == null) ? 0 : performer.hashCode());
		result = prime * result
				+ ((piTemplateId == null) ? 0 : piTemplateId.hashCode());
		result = prime * result
				+ ((procCode == null) ? 0 : procCode.hashCode());
		result = prime * result
				+ ((procStatus == null) ? 0 : procStatus.hashCode());
		result = prime * result
				+ ((scopingEntityId == null) ? 0 : scopingEntityId.hashCode());
		result = prime * result + ((sdLocs == null) ? 0 : sdLocs.hashCode());
		result = prime
				* result
				+ ((sectionTemplateId == null) ? 0 : sectionTemplateId
						.hashCode());
		result = prime * result
				+ ((targetSiteCode == null) ? 0 : targetSiteCode.hashCode());
		result = prime * result + ((udi == null) ? 0 : udi.hashCode());
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
		CCDAProcActProc other = (CCDAProcActProc) obj;
		if (deviceCode == null) {
			if (other.deviceCode != null)
				return false;
		} else if (!deviceCode.equals(other.deviceCode))
			return false;
		if (patientUDI == null) {
			if (other.patientUDI != null)
				return false;
		} else if (!patientUDI.equals(other.patientUDI))
			return false;
		if (performer == null) {
			if (other.performer != null)
				return false;
		} else if (!performer.equals(other.performer))
			return false;
		if (piTemplateId == null) {
			if (other.piTemplateId != null)
				return false;
		} else if (!piTemplateId.equals(other.piTemplateId))
			return false;
		if (procCode == null) {
			if (other.procCode != null)
				return false;
		} else if (!procCode.equals(other.procCode))
			return false;
		if (procStatus == null) {
			if (other.procStatus != null)
				return false;
		} else if (!procStatus.equals(other.procStatus))
			return false;
		if (scopingEntityId == null) {
			if (other.scopingEntityId != null)
				return false;
		} else if (!scopingEntityId.equals(other.scopingEntityId))
			return false;
		if (sdLocs == null) {
			if (other.sdLocs != null)
				return false;
		} else if (!sdLocs.equals(other.sdLocs))
			return false;
		if (sectionTemplateId == null) {
			if (other.sectionTemplateId != null)
				return false;
		} else if (!sectionTemplateId.equals(other.sectionTemplateId))
			return false;
		if (targetSiteCode == null) {
			if (other.targetSiteCode != null)
				return false;
		} else if (!targetSiteCode.equals(other.targetSiteCode))
			return false;
		if (udi == null) {
			if (other.udi != null)
				return false;
		} else if (!udi.equals(other.udi))
			return false;
		return true;
	}
	
	
}
