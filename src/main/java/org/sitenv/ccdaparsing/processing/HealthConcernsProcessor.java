package org.sitenv.ccdaparsing.processing;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.sitenv.ccdaparsing.model.CCDAHealthConcerns;
import org.sitenv.ccdaparsing.util.ApplicationConstants;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class HealthConcernsProcessor {
	
	public static CCDAHealthConcerns retrieveHealthConcernDetails(XPath xPath , Document doc) throws XPathExpressionException
	{
		CCDAHealthConcerns healthConcern = null;
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.HEALTHCONCERN_EXPRESSION).evaluate(doc, XPathConstants.NODE);
		if (sectionElement != null)
		{
			healthConcern = new CCDAHealthConcerns();
			healthConcern.setTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODESET)));
			
			healthConcern.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
			
			healthConcern.setNarrativeText(ApplicationUtil.readTextContext((Element) xPath.compile("./text[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
		}
		return healthConcern;
	}

}
