package org.sitenv.ccdaparsing.processing;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sitenv.ccdaparsing.model.CCDACarePlanSections;
import org.sitenv.ccdaparsing.model.CCDAII;
import org.sitenv.ccdaparsing.model.CCDARefModel;
import org.sitenv.ccdaparsing.util.ApplicationConstants;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CarePlanSectionsParser {
	
	private static final Logger logger = LogManager.getLogger(CarePlanSectionsParser.class);
	
	private static final CCDAII INTERVENTIONS_SECTION_V3 = 
			new CCDAII("2.16.840.1.113883.10.20.21.2.3", "2015-08-01");
	private static final CCDAII HEALTH_STATUS_EVALUATIONS_AND_OUTCOMES_SECTION = 
			new CCDAII("2.16.840.1.113883.10.20.22.2.61"); 
	
	public static void parse(Document doc, CCDARefModel model, XPath xPath) throws XPathExpressionException, TransformerException {
		logger.info(" *** Parsing CarePlan Sections *** ");
		model.setCarePlanSections(getSuggestedSections(doc,xPath));		
	}
	
	private static CCDACarePlanSections getSuggestedSections(Document doc,XPath xPath) throws XPathExpressionException, TransformerException {
		CCDACarePlanSections carePlanSections = new CCDACarePlanSections();
		
		Element interventions = (Element) xPath.compile(ApplicationConstants.INTERVENTIONS_SECTION_V3_EXP).evaluate(doc, XPathConstants.NODE);				
		if(interventions != null) {
			logger.info("interventions tagName: " + interventions.getTagName());
			logger.info("Setting: Document HAS Interventions Section (V3) 2.16.840.1.113883.10.20.21.2.3:2015-08-01");
			carePlanSections.setInterventionsSectionV3(true);
			
			carePlanSections.setAuthor(ApplicationUtil.readAuthor((Element) xPath.compile("./author[not(@nullFlavor)]").
					evaluate(interventions, XPathConstants.NODE),xPath));
		} else {
			logger.info("Document does NOT have Interventions Section (V3) 2.16.840.1.113883.10.20.21.2.3:2015-08-01");
		}
		
		Element healthStatusEvals = (Element) xPath.compile(ApplicationConstants.HEALTH_STATUS_EVALUATIONS_AND_OUTCOMES_SECTION_EXP)
				.evaluate(doc, XPathConstants.NODE);
		if(healthStatusEvals != null) {
			logger.info("healthStatusEvals tagName: " + healthStatusEvals.getTagName());
			logger.info("Setting: Document HAS Health Status Evaluations and Outcomes Section 2.16.840.1.113883.10.20.22.2.61");
			carePlanSections.setHealthStatusEvaluationsAndOutcomesSection(true);	
			
			carePlanSections.setAuthor(ApplicationUtil.readAuthor((Element) xPath.compile("./author[not(@nullFlavor)]").
					evaluate(healthStatusEvals, XPathConstants.NODE),xPath));
		} else {
			logger.info("Document does NOT have Health Status Evaluations and Outcomes Section 2.16.840.1.113883.10.20.22.2.61");
		}
		
		return carePlanSections;
	}
	
}
