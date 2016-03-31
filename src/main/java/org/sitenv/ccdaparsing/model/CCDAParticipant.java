package org.sitenv.ccdaparsing.model;

public class CCDAParticipant {
	
	private CCDADataElement firstName;
	private CCDADataElement lastName;
	private CCDADataElement middleName;
	private CCDADataElement previousName;
	private CCDADataElement suffix;
	private CCDAAddress     address;
	private CCDADataElement telecom;
	
	public CCDAParticipant()
	{
		
	}

	public CCDADataElement getFirstName() {
		return firstName;
	}

	public void setFirstName(CCDADataElement firstName) {
		this.firstName = firstName;
	}

	public CCDADataElement getLastName() {
		return lastName;
	}

	public void setLastName(CCDADataElement lastName) {
		this.lastName = lastName;
	}

	public CCDADataElement getMiddleName() {
		return middleName;
	}
	
	public CCDADataElement getPreviousName() {
		return previousName;
	}

	public void setPreviousName(CCDADataElement previousName) {
		this.previousName = previousName;
	}

	public void setMiddleName(CCDADataElement middleName) {
		this.middleName = middleName;
	}
	
	public CCDADataElement getSuffix() {
		return suffix;
	}

	public void setSuffix(CCDADataElement suffix) {
		this.suffix = suffix;
	}

	public CCDAAddress getAddress() {
		return address;
	}

	public void setAddress(CCDAAddress address) {
		this.address = address;
	}

	public CCDADataElement getTelecom() {
		return telecom;
	}

	public void setTelecom(CCDADataElement telecom) {
		this.telecom = telecom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result
				+ ((previousName == null) ? 0 : previousName.hashCode());
		result = prime * result + ((suffix == null) ? 0 : suffix.hashCode());
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
		CCDAParticipant other = (CCDAParticipant) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (previousName == null) {
			if (other.previousName != null)
				return false;
		} else if (!previousName.equals(other.previousName))
			return false;
		if (suffix == null) {
			if (other.suffix != null)
				return false;
		} else if (!suffix.equals(other.suffix))
			return false;
		if (telecom == null) {
			if (other.telecom != null)
				return false;
		} else if (!telecom.equals(other.telecom))
			return false;
		return true;
	}
	
	
}
