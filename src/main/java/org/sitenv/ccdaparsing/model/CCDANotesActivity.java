package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CCDANotesActivity {

	private ArrayList<CCDAII> templateId;
	private CCDACode activityCode;
	private CCDADataElement statusCode;
	private CCDADataElement text;
	private CCDAEffTime effTime;
	private CCDAAuthor author;
	private CCDANotes parent;

	public CCDANotesActivity() {

		templateId = new ArrayList<CCDAII>();

	}

	public CCDANotes getParent() {
		return parent;
	}

	public void setParent(CCDANotes parent) {
		this.parent = parent;
	}

	public ArrayList<CCDAII> getTemplateId() {
		return templateId;
	}

	public void setTemplateId(ArrayList<CCDAII> templateId) {
		this.templateId = templateId;
	}

	public CCDACode getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(CCDACode activityCode) {
		this.activityCode = activityCode;
	}

	public CCDADataElement getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(CCDADataElement statusCode) {
		this.statusCode = statusCode;
	}

	public CCDADataElement getText() {
		return text;
	}

	public void setText(CCDADataElement text) {
		this.text = text;
	}

	public CCDAEffTime getEffTime() {
		return effTime;
	}

	public void setEffTime(CCDAEffTime effTime) {
		this.effTime = effTime;
	}

	public CCDAAuthor getAuthor() {
		return author;
	}

	public void setAuthor(CCDAAuthor author) {
		this.author = author;
	}

}
