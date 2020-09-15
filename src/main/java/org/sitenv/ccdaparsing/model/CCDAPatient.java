package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

public class CCDAPatient extends CCDAXmlSnippet{
	private CCDADataElement firstName;
	private CCDADataElement lastName;
	private CCDADataElement middleName;
	private CCDADataElement previousName;
	private CCDADataElement suffix;
	private CCDADataElement dob;
	private CCDAEffTime dod;
	private ArrayList<CCDAAddress> addresses;
	private ArrayList<CCDAPL> languageCommunication;
	private CCDACode raceCodes;
	private ArrayList<CCDACode> sdtcRaceCodes;
	private CCDACode administrativeGenderCode;
	private CCDACode maritalStatusCode;
	private CCDACode ethnicity;
	private CCDACode religiousAffiliationCode;
	private CCDADataElement sex;
	private ArrayList<CCDADataElement> telecom;
	private CCDAAddress birthPlace;
	private List<Element> givenNameElementList; 
	private boolean givenNameContainsQualifier;
	private CCDADataElement patientLegalNameElement;
	private List<CCDAPatientName> patientNames;
	
	public CCDAPatient()
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

	public void setMiddleName(CCDADataElement middleName) {
		this.middleName = middleName;
	}

	public CCDADataElement getPreviousName() {
		return previousName;
	}

	public void setPreviousName(CCDADataElement previousName) {
		this.previousName = previousName;
	}

	public CCDADataElement getSuffix() {
		return suffix;
	}

	public void setSuffix(CCDADataElement suffix) {
		this.suffix = suffix;
	}

	public CCDADataElement getDob() {
		return dob;
	}

	public void setDob(CCDADataElement dob) {
		this.dob = dob;
	}

	public ArrayList<CCDAAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(ArrayList<CCDAAddress> addresses) {
		this.addresses = addresses;
	}

	public ArrayList<CCDAPL> getLanguageCommunication() {
		return languageCommunication;
	}

	public void setLanguageCommunication(ArrayList<CCDAPL> languageCommunication) {
		this.languageCommunication = languageCommunication;
	}

	public CCDACode getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(CCDACode ethnicity) {
		this.ethnicity = ethnicity;
	}

	public CCDADataElement getSex() {
		return sex;
	}

	public void setSex(CCDADataElement sex) {
		this.sex = sex;
	}
	
	public ArrayList<CCDADataElement> getTelecom() {
		return telecom;
	}

	public void setTelecom(ArrayList<CCDADataElement> telecom) {
		this.telecom = telecom;
	}

	public CCDACode getAdministrativeGenderCode() {
		return administrativeGenderCode;
	}

	public void setAdministrativeGenderCode(CCDACode administrativeGenderCode) {
		this.administrativeGenderCode = administrativeGenderCode;
	}

	public CCDACode getMaritalStatusCode() {
		return maritalStatusCode;
	}

	public void setMaritalStatusCode(CCDACode maritalStatusCode) {
		this.maritalStatusCode = maritalStatusCode;
	}

	public CCDACode getReligiousAffiliationCode() {
		return religiousAffiliationCode;
	}

	public void setReligiousAffiliationCode(CCDACode religiousAffiliationCode) {
		this.religiousAffiliationCode = religiousAffiliationCode;
	}

	public CCDAAddress getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(CCDAAddress birthPlace) {
		this.birthPlace = birthPlace;
	}
	
	public CCDACode getRaceCodes() {
		return raceCodes;
	}

	public void setRaceCodes(CCDACode raceCodes) {
		this.raceCodes = raceCodes;
	}

	public ArrayList<CCDACode> getSdtcRaceCodes() {
		if(sdtcRaceCodes == null)
		{
			sdtcRaceCodes = new ArrayList<CCDACode>();
		}
		return sdtcRaceCodes;
	}

	public void setSdtcRaceCodes(ArrayList<CCDACode> sdtcRaceCodes) {
		this.sdtcRaceCodes = sdtcRaceCodes;
	}
	
	public List<Element> getGivenNameElementList() {
		if(givenNameElementList == null)
		{
			givenNameElementList = new ArrayList<Element>();
		}
		return givenNameElementList;
	}

	public void setGivenNameElementList(List<Element> givenNameElementList) {
		this.givenNameElementList = givenNameElementList;
	}

	public boolean isGivenNameContainsQualifier() {
		return givenNameContainsQualifier;
	}

	public void setGivenNameContainsQualifier(boolean givenNameContainsQualifier) {
		this.givenNameContainsQualifier = givenNameContainsQualifier;
	}
	
	public CCDADataElement getPatientLegalNameElement() {
		return patientLegalNameElement;
	}

	public void setPatientLegalNameElement(CCDADataElement patientLegalNameElement) {
		this.patientLegalNameElement = patientLegalNameElement;
	}
	
	public CCDAEffTime getDod() {
		return dod;
	}

	public void setDod(CCDAEffTime dod) {
		this.dod = dod;
	}
	
	public List<CCDAPatientName> getPatientNames() {
		if(patientNames == null)
		{
			patientNames = new ArrayList<CCDAPatientName>();
		}
		return patientNames;
	}

	public void setPatientNames(List<CCDAPatientName> patientNames) {
		this.patientNames = patientNames;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result	
				+ ((addresses == null) ? 0 : addresses.hashCode());
		result = prime
				* result
				+ ((administrativeGenderCode == null) ? 0
						: administrativeGenderCode.hashCode());
		result = prime * result
				+ ((birthPlace == null) ? 0 : birthPlace.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result
				+ ((ethnicity == null) ? 0 : ethnicity.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime
				* result
				+ ((languageCommunication == null) ? 0 : languageCommunication
						.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime
				* result
				+ ((maritalStatusCode == null) ? 0 : maritalStatusCode
						.hashCode());
		result = prime * result
				+ ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result
				+ ((previousName == null) ? 0 : previousName.hashCode());
		result = prime * result
				+ ((raceCodes == null) ? 0 : raceCodes.hashCode());
		result = prime
				* result
				+ ((religiousAffiliationCode == null) ? 0
						: religiousAffiliationCode.hashCode());
		result = prime * result
				+ ((sdtcRaceCodes == null) ? 0 : sdtcRaceCodes.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
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
		CCDAPatient other = (CCDAPatient) obj;
		if (addresses == null) {
			if (other.addresses != null)
				return false;
		} else if (!addresses.equals(other.addresses))
			return false;
		if (administrativeGenderCode == null) {
			if (other.administrativeGenderCode != null)
				return false;
		} else if (!administrativeGenderCode
				.equals(other.administrativeGenderCode))
			return false;
		if (birthPlace == null) {
			if (other.birthPlace != null)
				return false;
		} else if (!birthPlace.equals(other.birthPlace))
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (ethnicity == null) {
			if (other.ethnicity != null)
				return false;
		} else if (!ethnicity.equals(other.ethnicity))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (languageCommunication == null) {
			if (other.languageCommunication != null)
				return false;
		} else if (!languageCommunication.equals(other.languageCommunication))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (maritalStatusCode == null) {
			if (other.maritalStatusCode != null)
				return false;
		} else if (!maritalStatusCode.equals(other.maritalStatusCode))
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
		if (raceCodes == null) {
			if (other.raceCodes != null)
				return false;
		} else if (!raceCodes.equals(other.raceCodes))
			return false;
		if (religiousAffiliationCode == null) {
			if (other.religiousAffiliationCode != null)
				return false;
		} else if (!religiousAffiliationCode
				.equals(other.religiousAffiliationCode))
			return false;
		if (sdtcRaceCodes == null) {
			if (other.sdtcRaceCodes != null)
				return false;
		} else if (!sdtcRaceCodes.equals(other.sdtcRaceCodes))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
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