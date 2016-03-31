package org.sitenv.ccdaparsing.model;

public class CCDAPQ {
	
	private String  value;
	private String  units;
	
	public String getValue() {
		return value;
	}



	public void setValue(String value) {
		this.value = value;
	}



	public String getUnits() {
		return units;
	}



	public void setUnits(String units) {
		this.units = units;
	}

	public CCDAPQ(String value)
	{
	  this.value = value;
	}

	public CCDAPQ()
	{
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((units == null) ? 0 : units.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		CCDAPQ other = (CCDAPQ) obj;
		if (units == null) {
			if (other.units != null)
				return false;
		} else if (!units.equals(other.units))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
