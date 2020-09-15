package org.sitenv.ccdaparsing.model;

public class CCDAPatientNameElement extends CCDAXmlSnippet {

	private String value;
	private Boolean isQualifierPresent;
	private String qualifierValue;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getIsQualifierPresent() {
		return isQualifierPresent;
	}

	public void setIsQualifierPresent(Boolean isQualifierPresent) {
		this.isQualifierPresent = isQualifierPresent;
	}

	public String getQualifierValue() {
		return qualifierValue;
	}

	public void setQualifierValue(String qualifierValue) {
		this.qualifierValue = qualifierValue;
	}

}
