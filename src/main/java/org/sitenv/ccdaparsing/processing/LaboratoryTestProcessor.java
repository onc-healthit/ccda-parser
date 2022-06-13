package org.sitenv.ccdaparsing.processing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sitenv.ccdaparsing.model.CCDAID;
import org.sitenv.ccdaparsing.model.CCDALabResult;
import org.sitenv.ccdaparsing.util.ApplicationConstants;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@Service
public class LaboratoryTestProcessor {
	
	private static final Logger logger = LogManager.getLogger(LaboratoryTestProcessor.class);
	
	@Autowired
	LaboratoryResultsProcessor laboratoryResultsProcessor;
	
	@Async()
	public Future<CCDALabResult> retrieveLabTests(XPath xPath , Document doc ) throws XPathExpressionException,TransformerException
	{
		long startTime = System.currentTimeMillis();
    	logger.info("Lab tests parsing Start time:"+ startTime);
    	
		CCDALabResult labTests = null;
		List<CCDAID> idList = new ArrayList<>();
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.RESULTS_EXPRESSION).evaluate(doc, XPathConstants.NODE);
		if(sectionElement != null)
		{
			labTests = new CCDALabResult();
			if(ApplicationUtil.checkForNullFlavourNI(sectionElement))
			{
				labTests.setSectionNullFlavourWithNI(true);
				return new AsyncResult<CCDALabResult>(labTests);
			}
			labTests.setResultSectionTempalteIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
								evaluate(sectionElement, XPathConstants.NODESET)));
			
			labTests.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
		
			labTests.setResultOrg(laboratoryResultsProcessor.readResultOrganizer((NodeList) xPath.compile(ApplicationConstants.LAB_TESTS_EXPRESSION).
													evaluate(sectionElement, XPathConstants.NODESET),xPath,idList));
			labTests.setIsLabTestInsteadOfResult(true);
			labTests.setIdList(idList);
		}
		logger.info("Lab tests parsing End time:"+ (System.currentTimeMillis() - startTime));
		return new AsyncResult<CCDALabResult>(labTests);
	}

}
