package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDAMedicationActivity extends CCDAXmlSnippet{

	private ArrayList<CCDAII>     				templateIds;
	private CCDAEffTime							duration;
	private CCDAFrequency						frequency;
	private CCDACode							routeCode;
	private CCDACode							approachSiteCode;
	private CCDAPQ								doseQuantity;
	private CCDAPQ								rateQuantity;
	private CCDACode							adminUnitCode;
	private CCDAConsumable						consumable;
	
	public ArrayList<CCDAII> getTemplateIds() {
		return templateIds;
	}

	public void setTemplateIds(ArrayList<CCDAII> templateIds) {
		this.templateIds = templateIds;
	}

	public CCDAEffTime getDuration() {
		return duration;
	}

	public void setDuration(CCDAEffTime duration) {
		this.duration = duration;
	}

	public CCDAFrequency getFrequency() {
		return frequency;
	}

	public void setFrequency(CCDAFrequency frequency) {
		this.frequency = frequency;
	}

	public CCDACode getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(CCDACode routeCode) {
		this.routeCode = routeCode;
	}

	public CCDACode getApproachSiteCode() {
		return approachSiteCode;
	}

	public void setApproachSiteCode(CCDACode approachSiteCode) {
		this.approachSiteCode = approachSiteCode;
	}

	public CCDAPQ getDoseQuantity() {
		return doseQuantity;
	}

	public void setDoseQuantity(CCDAPQ doseQuantity) {
		this.doseQuantity = doseQuantity;
	}

	public CCDAPQ getRateQuantity() {
		return rateQuantity;
	}

	public void setRateQuantity(CCDAPQ rateQuantity) {
		this.rateQuantity = rateQuantity;
	}

	public CCDACode getAdminUnitCode() {
		return adminUnitCode;
	}

	public void setAdminUnitCode(CCDACode adminUnitCode) {
		this.adminUnitCode = adminUnitCode;
	}

	public CCDAConsumable getConsumable() {
		return consumable;
	}

	public void setConsumable(CCDAConsumable consumable) {
		this.consumable = consumable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((adminUnitCode == null) ? 0 : adminUnitCode.hashCode());
		result = prime
				* result
				+ ((approachSiteCode == null) ? 0 : approachSiteCode.hashCode());
		result = prime * result
				+ ((consumable == null) ? 0 : consumable.hashCode());
		result = prime * result
				+ ((doseQuantity == null) ? 0 : doseQuantity.hashCode());
		result = prime * result
				+ ((duration == null) ? 0 : duration.hashCode());
		result = prime * result
				+ ((frequency == null) ? 0 : frequency.hashCode());
		result = prime * result
				+ ((rateQuantity == null) ? 0 : rateQuantity.hashCode());
		result = prime * result
				+ ((routeCode == null) ? 0 : routeCode.hashCode());
		result = prime * result
				+ ((templateIds == null) ? 0 : templateIds.hashCode());
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
		CCDAMedicationActivity other = (CCDAMedicationActivity) obj;
		if (adminUnitCode == null) {
			if (other.adminUnitCode != null)
				return false;
		} else if (!adminUnitCode.equals(other.adminUnitCode))
			return false;
		if (approachSiteCode == null) {
			if (other.approachSiteCode != null)
				return false;
		} else if (!approachSiteCode.equals(other.approachSiteCode))
			return false;
		if (consumable == null) {
			if (other.consumable != null)
				return false;
		} else if (!consumable.equals(other.consumable))
			return false;
		if (doseQuantity == null) {
			if (other.doseQuantity != null)
				return false;
		} else if (!doseQuantity.equals(other.doseQuantity))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (frequency == null) {
			if (other.frequency != null)
				return false;
		} else if (!frequency.equals(other.frequency))
			return false;
		if (rateQuantity == null) {
			if (other.rateQuantity != null)
				return false;
		} else if (!rateQuantity.equals(other.rateQuantity))
			return false;
		if (routeCode == null) {
			if (other.routeCode != null)
				return false;
		} else if (!routeCode.equals(other.routeCode))
			return false;
		if (templateIds == null) {
			if (other.templateIds != null)
				return false;
		} else if (!templateIds.equals(other.templateIds))
			return false;
		return true;
	}
	
	

}
