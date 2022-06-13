package org.sitenv.ccdaparsing.processing;

import java.util.concurrent.Future;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sitenv.ccdaparsing.model.CCDAGoals;
import org.sitenv.ccdaparsing.util.ApplicationConstants;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@Service
public class GoalsProcessor {
	
	private static final Logger logger = LogManager.getLogger(GoalsProcessor.class);
	
	@Async()
	public Future<CCDAGoals> retrieveGoalsDetails(XPath xPath , Document doc) throws XPathExpressionException,TransformerException
	{
		long startTime = System.currentTimeMillis();
    	logger.info("Goals parsing Start time:"+ startTime);
		
		CCDAGoals goals = null;
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.GOALS_EXPRESSION).evaluate(doc, XPathConstants.NODE);
		
		if(sectionElement != null)
		{
			goals = new CCDAGoals();
			if(ApplicationUtil.checkForNullFlavourNI(sectionElement))
			{
				goals.setSectionNullFlavourWithNI(true);
				return new AsyncResult<CCDAGoals>(goals);
			}
			goals.setTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
										evaluate(sectionElement, XPathConstants.NODESET)));
			
			goals.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
			
			goals.setNarrativeText(ApplicationUtil.readTextContent((Element) xPath.compile("./text[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
		}
		
		logger.info("Goals parsing End time:"+ (System.currentTimeMillis() - startTime));
		
		return new AsyncResult<CCDAGoals>(goals);
	}

}
