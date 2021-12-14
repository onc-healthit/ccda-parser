package org.sitenv.ccdaparsing.model;

public class CCDADataElement {

	private String value;
	private String lineNumber;
	private String xpath;
	private String use;
	private String xmlString;
	
	public CCDADataElement(String value)
	{
		this.value = value;
	}
	
	public CCDADataElement()
	{
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getXpath() {
		return xpath;
	}

	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}
	
	public String getXmlString() {
		return xmlString;
	}

	public void setXmlString(String xmlString) {
		this.xmlString = xmlString;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lineNumber == null) ? 0 : lineNumber.hashCode());
		result = prime * result + ((use == null) ? 0 : use.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result + ((xmlString == null) ? 0 : xmlString.hashCode());
		result = prime * result + ((xpath == null) ? 0 : xpath.hashCode());
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
		CCDADataElement other = (CCDADataElement) obj;
		if (lineNumber == null) {
			if (other.lineNumber != null)
				return false;
		} else if (!lineNumber.equals(other.lineNumber))
			return false;
		if (use == null) {
			if (other.use != null)
				return false;
		} else if (!use.equals(other.use))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (xmlString == null) {
			if (other.xmlString != null)
				return false;
		} else if (!xmlString.equals(other.xmlString))
			return false;
		if (xpath == null) {
			if (other.xpath != null)
				return false;
		} else if (!xpath.equals(other.xpath))
			return false;
		return true;
	}

	public boolean isValueAndUseEqual(CCDADataElement ccdaDataElement) {
		return value.equals(ccdaDataElement.getValue()) && use.equals(ccdaDataElement.getUse());		
	}
}
