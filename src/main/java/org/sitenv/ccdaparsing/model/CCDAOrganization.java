package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDAOrganization {

	private ArrayList<CCDADataElement> 				names;
	private ArrayList<CCDADataElement>			    telecom;
	private ArrayList<CCDAAddress>					address;
	
	public CCDAOrganization()
	{
		
	}

	public ArrayList<CCDADataElement> getNames() {
		return names;
	}

	public void setNames(ArrayList<CCDADataElement> names) {
		this.names = names;
	}

	public ArrayList<CCDADataElement> getTelecom() {
		return telecom;
	}

	public void setTelecom(ArrayList<CCDADataElement> telecom) {
		this.telecom = telecom;
	}

	public ArrayList<CCDAAddress> getAddress() {
		return address;
	}

	public void setAddress(ArrayList<CCDAAddress> address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((names == null) ? 0 : names.hashCode());
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
		CCDAOrganization other = (CCDAOrganization) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (names == null) {
			if (other.names != null)
				return false;
		} else if (!names.equals(other.names))
			return false;
		if (telecom == null) {
			if (other.telecom != null)
				return false;
		} else if (!telecom.equals(other.telecom))
			return false;
		return true;
	}
}
