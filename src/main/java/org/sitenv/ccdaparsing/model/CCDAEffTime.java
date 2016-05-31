package org.sitenv.ccdaparsing.model;

public class CCDAEffTime extends CCDAXmlSnippet{

	private CCDADataElement low;
	private Boolean         lowPresent;
	private CCDADataElement high;
	private Boolean         highPresent;
	private CCDADataElement value;
	private Boolean         valuePresent;
	private String singleAdministration;
	
	
	public CCDADataElement getLow() {
		return low;
	}

	public void setLow(CCDADataElement low) {
		this.low = low;
	}

	public Boolean getLowPresent() {
		return lowPresent;
	}

	public void setLowPresent(Boolean lowPresent) {
		this.lowPresent = lowPresent;
	}

	public CCDADataElement getHigh() {
		return high;
	}

	public void setHigh(CCDADataElement high) {
		this.high = high;
	}

	public Boolean getHighPresent() {
		return highPresent;
	}

	public void setHighPresent(Boolean highPresent) {
		this.highPresent = highPresent;
	}

	public CCDADataElement getValue() {
		return value;
	}

	public void setValue(CCDADataElement value) {
		this.value = value;
	}

	public Boolean getValuePresent() {
		return valuePresent;
	}

	public void setValuePresent(Boolean valuePresent) {
		this.valuePresent = valuePresent;
	}
	
	public String getSingleAdministration() {
		return singleAdministration;
	}

	public void setSingleAdministration(String singleAdministration) {
		this.singleAdministration = singleAdministration;
	}

	public CCDAEffTime()
	{
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((high == null) ? 0 : high.hashCode());
		result = prime * result
				+ ((highPresent == null) ? 0 : highPresent.hashCode());
		result = prime * result + ((low == null) ? 0 : low.hashCode());
		result = prime * result
				+ ((lowPresent == null) ? 0 : lowPresent.hashCode());
		result = prime
				* result
				+ ((singleAdministration == null) ? 0 : singleAdministration
						.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result
				+ ((valuePresent == null) ? 0 : valuePresent.hashCode());
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
		CCDAEffTime other = (CCDAEffTime) obj;
		if (high == null) {
			if (other.high != null)
				return false;
		} else if (!high.equals(other.high))
			return false;
		if (highPresent == null) {
			if (other.highPresent != null)
				return false;
		} else if (!highPresent.equals(other.highPresent))
			return false;
		if (low == null) {
			if (other.low != null)
				return false;
		} else if (!low.equals(other.low))
			return false;
		if (lowPresent == null) {
			if (other.lowPresent != null)
				return false;
		} else if (!lowPresent.equals(other.lowPresent))
			return false;
		if (singleAdministration == null) {
			if (other.singleAdministration != null)
				return false;
		} else if (!singleAdministration.equals(other.singleAdministration))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (valuePresent == null) {
			if (other.valuePresent != null)
				return false;
		} else if (!valuePresent.equals(other.valuePresent))
			return false;
		return true;
	}
	
	
}
