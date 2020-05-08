package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDAFamilyHxOrg extends CCDAXmlSnippet {

	private ArrayList<CCDAII> templateIds;
	private CCDACode statusCode;
	private CCDACode relationCode;
	private CCDAEffTime birthTime;
	private CCDACode genderCode;
	private ArrayList<CCDAFamilyHxObs> familyHxObs;

	public ArrayList<CCDAII> getTemplateIds() {
		return templateIds;
	}

	public void setTemplateIds(ArrayList<CCDAII> templateIds) {
		this.templateIds = templateIds;
	}

	public CCDACode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(CCDACode statusCode) {
		this.statusCode = statusCode;
	}

	public CCDACode getRelationCode() {
		return relationCode;
	}

	public void setRelationCode(CCDACode relationCode) {
		this.relationCode = relationCode;
	}

	public CCDAEffTime getBirthTime() {
		return birthTime;
	}

	public void setBirthTime(CCDAEffTime birthTime) {
		this.birthTime = birthTime;
	}

	public CCDACode getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(CCDACode genderCode) {
		this.genderCode = genderCode;
	}

	public ArrayList<CCDAFamilyHxObs> getFamilyHxObs() {
		return familyHxObs;
	}

	public void setFamilyHxObs(ArrayList<CCDAFamilyHxObs> familyHxObs) {
		this.familyHxObs = familyHxObs;
	}
}
