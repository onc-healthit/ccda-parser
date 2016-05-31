package org.sitenv.ccdaparsing.model;



public class CCDAXmlSnippet {
	
	private String lineNumber;
	private String xmlString;
	
	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
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
		result = prime * result
				+ ((lineNumber == null) ? 0 : lineNumber.hashCode());
		result = prime * result
				+ ((xmlString == null) ? 0 : xmlString.hashCode());
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
		CCDAXmlSnippet other = (CCDAXmlSnippet) obj;
		if (lineNumber == null) {
			if (other.lineNumber != null)
				return false;
		} else if (!lineNumber.equals(other.lineNumber))
			return false;
		if (xmlString == null) {
			if (other.xmlString != null)
				return false;
		} else if (!xmlString.equals(other.xmlString))
			return false;
		return true;
	}
	
}
