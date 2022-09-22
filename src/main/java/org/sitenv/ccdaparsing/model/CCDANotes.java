package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CCDANotes {
	
	private ArrayList<CCDAII>       		sectionTemplateId;
	private CCDACode                 		sectionCode;
	private ArrayList<CCDANotesActivity>	notesActivity;
	
	private CCDAAuthor author;
	
	public CCDANotes() { 
		sectionTemplateId = new ArrayList<CCDAII>();
		notesActivity = new ArrayList<CCDANotesActivity>();
	}
	
	public CCDAAuthor getAuthor() {
		return author;
	}
	public void setAuthor(CCDAAuthor author) {
		this.author = author;
	}

	public ArrayList<CCDAII> getSectionTemplateId() {
		return sectionTemplateId;
	}
	public void setSectionTemplateId(ArrayList<CCDAII> sectionTemplateId) {
		this.sectionTemplateId = sectionTemplateId;
	}
	public CCDACode getSectionCode() {
		return sectionCode;
	}
	public void setSectionCode(CCDACode sectionCode) {
		this.sectionCode = sectionCode;
	}
	public ArrayList<CCDANotesActivity> getNotesActivity() {
		return notesActivity;
	}
	public void setNotesActivity(ArrayList<CCDANotesActivity> notesActivity) {
		this.notesActivity = notesActivity;
	}
}
