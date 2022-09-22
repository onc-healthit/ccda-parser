package org.sitenv.ccdaparsing.processing;

import java.util.ArrayList;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sitenv.ccdaparsing.model.CCDAAuthor;
import org.sitenv.ccdaparsing.model.CCDARefModel;
import org.sitenv.ccdaparsing.util.ApplicationConstants;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class AuthorParser {

	private static final Logger logger = LogManager.getLogger(AuthorParser.class);
	
	public static void parse(XPath xPath , Document doc, CCDARefModel model) throws XPathExpressionException, TransformerException {    	
		logger.info(" *** Parsing Author *** ");
    	model.setAuthorsFromHeader(retrieveAuthorsFromHeader(doc, xPath));
    	// TODO: For performance reasons, consider only running on sub model, not ref models.
    	// I'm not sure a need yet for the linked references on the scenarios, but there may be one.
    	// However, since scenarios are only loaded once, it's not all that important for performance either. 
    	// Subs are loaded every time, but there's only one (and we need that data). That's the current purpose of the call.
    	model.setAuthorsWithLinkedReferenceData(retrieveAuthorsWithLinkedReferenceData(doc,xPath));    	
	}
	
	public static ArrayList<CCDAAuthor> retrieveAuthorsFromHeader(Document doc, XPath xpath) throws XPathExpressionException, TransformerException {		
		ArrayList<CCDAAuthor> auths = new ArrayList<CCDAAuthor>();
		CCDAAuthor auth = null;
		
		
		NodeList docAuths = (NodeList)xpath.compile(ApplicationConstants.AUTHORS_FROM_HEADER_EXP).evaluate(doc, XPathConstants.NODESET);
		
		for (int i = 0; i < docAuths.getLength(); i++) {
			
			logger.info("Parsing Author at document level ");
			Element authElement = (Element) docAuths.item(i);
			
			auth = ApplicationUtil.readAuthor(authElement,xpath);
			
			if(auth != null) {
				auths.add(auth);
			}				
		}
		
		return auths;
	}
	
	public static ArrayList<CCDAAuthor> retrieveAuthorsWithLinkedReferenceData(Document doc, XPath xpath)
			throws XPathExpressionException, TransformerException {
		ArrayList<CCDAAuthor> auths = new ArrayList<CCDAAuthor>();
		CCDAAuthor auth = null;
		NodeList bodyAuths = (NodeList) xpath.compile(ApplicationConstants.AUTHORS_WITH_LINKED_REFERENCE_DATA_EXP).evaluate(doc, XPathConstants.NODESET);

		for (int i = 0; i < bodyAuths.getLength(); i++) {
			Element authElement = (Element) bodyAuths.item(i);
			auth = ApplicationUtil.readAuthor(authElement,xpath);
			if (auth != null) {
				auths.add(auth);
			}
		}

		return auths;
	}	

}
