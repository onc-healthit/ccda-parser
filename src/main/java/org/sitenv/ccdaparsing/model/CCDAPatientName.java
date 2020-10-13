package org.sitenv.ccdaparsing.model;

import java.util.List;

public class CCDAPatientName extends CCDAXmlSnippet {

	private List<CCDAPatientNameElement> givenName;
	private List<CCDAPatientNameElement> familyName;
	private List<CCDAPatientNameElement> suffix;
	private String useContext;


	public List<CCDAPatientNameElement> getGivenName() {
		return givenName;
	}

	public void setGivenName(List<CCDAPatientNameElement> givenName) {
		this.givenName = givenName;
	}

	public List<CCDAPatientNameElement> getFamilyName() {
		return familyName;
	}

	public void setFamilyName(List<CCDAPatientNameElement> familyName) {
		this.familyName = familyName;
	}
	
	public List<CCDAPatientNameElement> getSuffix() {
		return suffix;
	}

	public void setSuffix(List<CCDAPatientNameElement> suffix) {
		this.suffix = suffix;
	}

	public String getUseContext() {
		return useContext;
	}

	public void setUseContext(String useContext) {
		this.useContext = useContext;
	}

}
