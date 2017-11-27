package org.sitenv.ccdaparsing.processing;

import java.util.concurrent.Future;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;
import org.sitenv.ccdaparsing.model.CCDAHealthConcerns;
import org.sitenv.ccdaparsing.util.ApplicationConstants;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@Service
public class HealthConcernsProcessor {
	
	private static final Logger logger = Logger.getLogger(CareTeamMemberProcessor.class);
	
	@Async()
	public Future<CCDAHealthConcerns> retrieveHealthConcernDetails(XPath xPath , Document doc) throws XPathExpressionException,TransformerException
	{
		long startTime = System.currentTimeMillis();
    	logger.info("Health concern parsing Start time:"+ startTime);
		CCDAHealthConcerns healthConcern = null;
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.HEALTHCONCERN_EXPRESSION).evaluate(doc, XPathConstants.NODE);
		if (sectionElement != null)
		{
			healthConcern = new CCDAHealthConcerns();
			if(ApplicationUtil.checkForNullFlavourNI(sectionElement))
			{
				healthConcern.setSectionNullFlavourWithNI(true);
				return new AsyncResult<CCDAHealthConcerns>(healthConcern);
			}
			healthConcern.setTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODESET)));
			
			healthConcern.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
			
			healthConcern.setNarrativeText(ApplicationUtil.readTextContext((Element) xPath.compile("./text[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
		}
		
		logger.info("Health concern parsing End time:"+ (System.currentTimeMillis() - startTime));
		return new AsyncResult<CCDAHealthConcerns>(healthConcern);
	}

}
