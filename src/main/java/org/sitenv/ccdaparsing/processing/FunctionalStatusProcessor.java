package org.sitenv.ccdaparsing.processing;

import org.apache.log4j.Logger;
import org.sitenv.ccdaparsing.model.CCDAFunctionalStatus;
import org.sitenv.ccdaparsing.model.CCDAID;
import org.sitenv.ccdaparsing.util.ApplicationConstants;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class FunctionalStatusProcessor {
	private static final Logger logger = Logger.getLogger(FunctionalStatusProcessor.class);

	@Async()
	public Future<CCDAFunctionalStatus> retrieveFunctionalStatusDetails(XPath xPath, Document doc) throws XPathExpressionException, TransformerException {
		long startTime = System.currentTimeMillis();
		logger.info("Advance Directive parsing Start time:" + startTime);

		CCDAFunctionalStatus functionalStatus = null;
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.FUNCTIONAL_STS_EXPRESSION).evaluate(doc, XPathConstants.NODE);
		List<CCDAID> idList = new ArrayList<>();
		if (sectionElement != null) {
			functionalStatus = new CCDAFunctionalStatus();
			if (ApplicationUtil.checkForNullFlavourNI(sectionElement)) {
				functionalStatus.setSectionNullFlavourWithNI(true);
				return new AsyncResult<CCDAFunctionalStatus>(functionalStatus);
			}
			functionalStatus.setTemplateIds(ApplicationUtil
					.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
							evaluate(sectionElement, XPathConstants.NODESET)));
			functionalStatus.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));

			sectionElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			functionalStatus.setLineNumber(sectionElement.getUserData("lineNumber") + " - " + sectionElement.getUserData("endLineNumber"));
			functionalStatus.setXmlString(ApplicationUtil.nodeToString((Node) sectionElement));
		}

		return new AsyncResult<CCDAFunctionalStatus>(functionalStatus);
	}
}
