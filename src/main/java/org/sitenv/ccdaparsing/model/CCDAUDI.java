package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDAUDI extends CCDAXmlSnippet{
	
	private ArrayList<CCDAII>     templateIds;
	private ArrayList<CCDAII>     UDIValue;
	private CCDACode deviceCode;
	private ArrayList<CCDAII> scopingEntityId;
	
	public ArrayList<CCDAII> getTemplateIds() {
		return templateIds;
	}
	public void setTemplateIds(ArrayList<CCDAII> templateIds) {
		this.templateIds = templateIds;
	}
	public CCDACode getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(CCDACode deviceCode) {
		this.deviceCode = deviceCode;
	}
	public ArrayList<CCDAII> getUDIValue() {
		return UDIValue;
	}
	public void setUDIValue(ArrayList<CCDAII> uDIValue) {
		UDIValue = uDIValue;
	}
	public ArrayList<CCDAII> getScopingEntityId() {
		return scopingEntityId;
	}
	public void setScopingEntityId(ArrayList<CCDAII> scopingEntityId) {
		this.scopingEntityId = scopingEntityId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((UDIValue == null) ? 0 : UDIValue.hashCode());
		result = prime * result
				+ ((deviceCode == null) ? 0 : deviceCode.hashCode());
		result = prime * result
				+ ((scopingEntityId == null) ? 0 : scopingEntityId.hashCode());
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
		CCDAUDI other = (CCDAUDI) obj;
		if (UDIValue == null) {
			if (other.UDIValue != null)
				return false;
		} else if (!UDIValue.equals(other.UDIValue))
			return false;
		if (deviceCode == null) {
			if (other.deviceCode != null)
				return false;
		} else if (!deviceCode.equals(other.deviceCode))
			return false;
		if (scopingEntityId == null) {
			if (other.scopingEntityId != null)
				return false;
		} else if (!scopingEntityId.equals(other.scopingEntityId))
			return false;
		if (templateIds == null) {
			if (other.templateIds != null)
				return false;
		} else if (!templateIds.equals(other.templateIds))
			return false;
		return true;
	}
	
	
}
