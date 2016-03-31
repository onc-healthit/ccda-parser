package org.sitenv.ccdaparsing.processing;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.sitenv.ccdaparsing.model.CCDAGoals;
import org.sitenv.ccdaparsing.util.ApplicationConstants;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class GoalsProcessor {
	
	public static CCDAGoals retrieveGoalsDetails(XPath xPath , Document doc) throws XPathExpressionException
	{
		CCDAGoals goals = null;
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.GOALS_EXPRESSION).evaluate(doc, XPathConstants.NODE);
		
		if(sectionElement != null)
		{
			goals = new CCDAGoals();
			goals.setTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
										evaluate(sectionElement, XPathConstants.NODESET)));
			
			goals.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
			
			goals.setNarrativeText(ApplicationUtil.readTextContext((Element) xPath.compile("./text[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
		}
		
		return goals;
	}

}
