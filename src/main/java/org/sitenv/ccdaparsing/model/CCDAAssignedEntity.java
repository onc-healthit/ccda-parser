package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDAAssignedEntity {

	private ArrayList<CCDADataElement>			telecom;
	private ArrayList<CCDAAddress>			addresses;
	private CCDAOrganization					organization;
	
	public ArrayList<CCDADataElement> getTelecom() {
		return telecom;
	}

	public void setTelecom(ArrayList<CCDADataElement> telecom) {
		this.telecom = telecom;
	}

	public ArrayList<CCDAAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(ArrayList<CCDAAddress> addresses) {
		this.addresses = addresses;
	}

	public CCDAOrganization getOrganization() {
		return organization;
	}

	public void setOrganization(CCDAOrganization organization) {
		this.organization = organization;
	}

	public CCDAAssignedEntity()
	{
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((addresses == null) ? 0 : addresses.hashCode());
		result = prime * result
				+ ((organization == null) ? 0 : organization.hashCode());
		result = prime * result + ((telecom == null) ? 0 : telecom.hashCode());
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
		CCDAAssignedEntity other = (CCDAAssignedEntity) obj;
		if (addresses == null) {
			if (other.addresses != null)
				return false;
		} else if (!addresses.equals(other.addresses))
			return false;
		if (organization == null) {
			if (other.organization != null)
				return false;
		} else if (!organization.equals(other.organization))
			return false;
		if (telecom == null) {
			if (other.telecom != null)
				return false;
		} else if (!telecom.equals(other.telecom))
			return false;
		return true;
	}
	
}
