package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDAImmunizationActivity extends CCDAXmlSnippet {
	private ArrayList<CCDAII>     				templateIds;
	private CCDAEffTime							time;
	private CCDACode							routeCode;
	private CCDACode							approachSiteCode;
	private CCDAPQ								doseQuantity;
	private CCDACode							adminUnitCode;
	private CCDAConsumable						consumable;
	private CCDAOrganization					organization;
	private ArrayList<CCDADataElement> referenceTexts;
	
	public CCDAImmunizationActivity()
	{
		
	}

	public ArrayList<CCDAII> getTemplateIds() {
		return templateIds;
	}

	public void setTemplateIds(ArrayList<CCDAII> templateIds) {
		this.templateIds = templateIds;
	}

	public CCDAEffTime getTime() {
		return time;
	}

	public void setTime(CCDAEffTime time) {
		this.time = time;
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

	public CCDAOrganization getOrganization() {
		return organization;
	}

	public void setOrganization(CCDAOrganization organization) {
		this.organization = organization;
	}
	
	public ArrayList<CCDADataElement> getReferenceTexts() {
		if(this.referenceTexts == null)
		{
			this.referenceTexts = new ArrayList<CCDADataElement>();
		}
		
		return referenceTexts;
	}

	public void setReferenceTexts(ArrayList<CCDADataElement> referenceTexts) {
		this.referenceTexts = referenceTexts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
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
				+ ((organization == null) ? 0 : organization.hashCode());
		result = prime * result
				+ ((referenceTexts == null) ? 0 : referenceTexts.hashCode());
		result = prime * result
				+ ((routeCode == null) ? 0 : routeCode.hashCode());
		result = prime * result
				+ ((templateIds == null) ? 0 : templateIds.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CCDAImmunizationActivity other = (CCDAImmunizationActivity) obj;
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
		if (organization == null) {
			if (other.organization != null)
				return false;
		} else if (!organization.equals(other.organization))
			return false;
		if (referenceTexts == null) {
			if (other.referenceTexts != null)
				return false;
		} else if (!referenceTexts.equals(other.referenceTexts))
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
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
}
