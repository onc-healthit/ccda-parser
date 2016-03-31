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
}
