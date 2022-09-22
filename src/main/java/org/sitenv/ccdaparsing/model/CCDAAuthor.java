package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;



public class CCDAAuthor extends CCDAXmlSnippet{

	private CCDAII templateId;
	private CCDAEffTime time;
	private ArrayList<CCDAII> templateIds;
	private ArrayList<CCDAII> authorIds;
	private CCDADataElement	authorFirstName;
	private CCDADataElement	authorLastName;
	private CCDADataElement	authorName;
	private ArrayList<CCDAII> repOrgIds;
	private ArrayList<CCDADataElement> telecoms;
	private CCDADataElement	orgName;
	
	public CCDAAuthor() {
		templateIds = new ArrayList<CCDAII>(); 
		authorIds = new ArrayList<CCDAII>();
		repOrgIds = new ArrayList<CCDAII>();
		telecoms = new ArrayList<CCDADataElement>();
	}

	public CCDAII getTemplateId() {
		return templateId;
	}

	public void setTemplateId(CCDAII templateId) {
		this.templateId = templateId;
	}

	public CCDAEffTime getTime() {
		return time;
	}

	public void setTime(CCDAEffTime time) {
		this.time = time;
	}
	
	public ArrayList<CCDAII> getTemplateIds() {
		return templateIds;
	}

	public void setTemplateIds(ArrayList<CCDAII> templateIds) {
		this.templateIds = templateIds;
	}

	public ArrayList<CCDAII> getAuthorIds() {
		return authorIds;
	}

	public void setAuthorIds(ArrayList<CCDAII> authorIds) {
		this.authorIds = authorIds;
	}

	public CCDADataElement getAuthorFirstName() {
		return authorFirstName;
	}

	public void setAuthorFirstName(CCDADataElement authorFirstName) {
		this.authorFirstName = authorFirstName;
	}

	public CCDADataElement getAuthorLastName() {
		return authorLastName;
	}

	public void setAuthorLastName(CCDADataElement authorLastName) {
		this.authorLastName = authorLastName;
	}

	public CCDADataElement getAuthorName() {
		return authorName;
	}

	public void setAuthorName(CCDADataElement authorName) {
		this.authorName = authorName;
	}

	public ArrayList<CCDAII> getRepOrgIds() {
		return repOrgIds;
	}

	public void setRepOrgIds(ArrayList<CCDAII> repOrgIds) {
		this.repOrgIds = repOrgIds;
	}

	public ArrayList<CCDADataElement> getTelecoms() {
		return telecoms;
	}

	public void setTelecoms(ArrayList<CCDADataElement> telecoms) {
		this.telecoms = telecoms;
	}

	public CCDADataElement getOrgName() {
		return orgName;
	}

	public void setOrgName(CCDADataElement orgName) {
		this.orgName = orgName;
	}
	
}
