package org.sitenv.ccdaparsing.processing;

import java.util.List;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.sitenv.ccdaparsing.model.CCDAID;
import org.sitenv.ccdaparsing.model.CCDALabResult;
import org.sitenv.ccdaparsing.util.ApplicationConstants;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class LaboratoryTestProcessor {
	
	public static CCDALabResult retrieveLabTests(XPath xPath , Document doc, List<CCDAID> idList) throws XPathExpressionException,TransformerException
	{
		CCDALabResult labTests = null;
		
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.RESULTS_EXPRESSION).evaluate(doc, XPathConstants.NODE);
		if(sectionElement != null)
		{
			labTests = new CCDALabResult();
			if(ApplicationUtil.checkForNullFlavourNI(sectionElement))
			{
				labTests.setSectionNullFlavourWithNI(true);
				return labTests;
			}
			labTests.setResultSectionTempalteIds(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
								evaluate(sectionElement, XPathConstants.NODESET)));
			
			labTests.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
		
			labTests.setResultOrg(LaboratoryResultsProcessor.readResultOrganizer((NodeList) xPath.compile(ApplicationConstants.LAB_TESTS_EXPRESSION).
													evaluate(sectionElement, XPathConstants.NODESET),xPath,idList));
			labTests.setIsLabTestInsteadOfResult(true);
		}
		return labTests;
	}

}
