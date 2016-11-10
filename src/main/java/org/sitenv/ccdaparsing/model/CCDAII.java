package org.sitenv.ccdaparsing.model;

public class CCDAII extends CCDADataElement{
	private String  rootValue;
	private String  extValue;
	
	public String getRootValue() {
		return rootValue;
	}

	public void setRootValue(String rootValue) {
		this.rootValue = rootValue;
	}

	public String getExtValue() {
		return extValue;
	}

	public void setExtValue(String extValue) {
		this.extValue = extValue;
	}

	public CCDAII()
	{
	}
	
	public CCDAII(String rootValue) {
		this.rootValue = rootValue;
	}
	
	public CCDAII(String rootValue, String extValue) {
		this(rootValue);
		this.extValue = extValue;
	}
	
	@Override
	public boolean equals(Object obj) {

		if (obj == this) { 
			return true; 
		} 
		if (obj == null || obj.getClass() != this.getClass()) { 
			return false; 
		} 
		CCDAII obj2 = (CCDAII) obj; 
		return  (this.rootValue == obj2.getRootValue() || (this.rootValue != null && this.rootValue.equals(obj2.getRootValue()))) && 
				(this.extValue == obj2.getExtValue() || (this.extValue != null && this.extValue.equals(obj2.getExtValue())));
		
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getClass().getSimpleName() + System.lineSeparator());
		sb.append("rootValue: " + rootValue + System.lineSeparator());
		sb.append("extValue:  " + extValue);
		return sb.toString();
	}
}
