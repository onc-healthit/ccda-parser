package org.sitenv.ccdaparsing.processing;

import java.util.ArrayList;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sitenv.ccdaparsing.model.CCDANotes;
import org.sitenv.ccdaparsing.model.CCDANotesActivity;
import org.sitenv.ccdaparsing.model.CCDARefModel;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class NotesParser {

	private static final Logger logger = LogManager.getLogger(NotesParser.class);
	
	public static void parse(Document doc, CCDARefModel model, XPath xPath) throws XPathExpressionException, TransformerException {
    	
    	model.setNotes(retrieveNotesDetails(doc,xPath));
    	
    	model.setNotesEntries(retrieveNotesActivities(doc,xPath));
    	
	}
	
	public static ArrayList<CCDANotesActivity> retrieveNotesActivities(Document doc,XPath xPath) throws XPathExpressionException, TransformerException {
		
		ArrayList<CCDANotesActivity> notesActivities = null;
		NodeList notesActivityNodes = (NodeList) xPath.compile("//act[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.4.202']]").evaluate(doc, XPathConstants.NODESET);
		
		if( notesActivityNodes!= null && notesActivityNodes.getLength()!= 0) {		
		
			logger.info(" Found Notes Activities Entries ");
			
			
			// Grab the notes activities
			notesActivities = ApplicationUtil.readNotesActivity(notesActivityNodes, null,xPath);
				
		}
		
		return notesActivities;
	}
	
	public static ArrayList<CCDANotes> retrieveNotesDetails(Document doc,XPath xPath) throws XPathExpressionException, TransformerException {
		
		ArrayList<CCDANotes> notes = null;
		NodeList sectionNodes = (NodeList) xPath.compile("/ClinicalDocument/component/structuredBody/component/section[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.2.65']]").
				evaluate(doc, XPathConstants.NODESET);
		
		if(sectionNodes!=null && sectionNodes.getLength()!=0) {		
		
			notes = new ArrayList<CCDANotes>();
			
			for(int i = 0; i < sectionNodes.getLength(); i++) {
				
				Element elem = (Element)sectionNodes.item(i);
				
				CCDANotes note = new CCDANotes();
				
				note.setSectionTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
						evaluate(elem, XPathConstants.NODESET)));
				
				note.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(elem, XPathConstants.NODE)));
				
				
				// Grab the notes activities
				note.setNotesActivity(ApplicationUtil.readNotesActivity((NodeList) xPath.compile("./entryRelationship/act[not(@nullFlavor) and templateId[@root='2.16.840.1.113883.10.20.22.4.202']]").
						evaluate(elem, XPathConstants.NODESET), note, xPath));
				
				note.setAuthor(ApplicationUtil.readAuthor((Element) xPath.compile("./author[not(@nullFlavor)]").evaluate(elem, XPathConstants.NODE),xPath));
				
				notes.add(note);
			}
			
		}
		
		return notes;
	}
	
	
}
