package org.sitenv.ccdaparsing.processing;

import org.apache.log4j.Logger;
import org.sitenv.ccdaparsing.model.CCDAFamilyHistory;
import org.sitenv.ccdaparsing.model.CCDAFamilyHxObs;
import org.sitenv.ccdaparsing.model.CCDAFamilyHxOrg;
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
public class FamilyHistoryProcessor {
	private static final Logger logger = Logger.getLogger(FamilyHistoryProcessor.class);

	@Async()
	public Future<CCDAFamilyHistory> retrieveFamilyHistoryDetails(XPath xPath, Document doc) throws XPathExpressionException, TransformerException {
		long startTime = System.currentTimeMillis();
		logger.info("Family History parsing Start time:" + startTime);

		CCDAFamilyHistory familyHistory = null;
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.FAMILYHX_EXPRESSION).evaluate(doc, XPathConstants.NODE);
		List<CCDAID> idList = new ArrayList<>();
		if (sectionElement != null) {
			familyHistory = new CCDAFamilyHistory();
			if (ApplicationUtil.checkForNullFlavourNI(sectionElement)) {
				familyHistory.setSectionNullFlavourWithNI(true);
				return new AsyncResult<CCDAFamilyHistory>(familyHistory);
			}
			familyHistory.setTemplateIds(ApplicationUtil
					.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
							evaluate(sectionElement, XPathConstants.NODESET)));
			familyHistory.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
			familyHistory.setFamilyHxOrg(readFamilyHxOrganizer((NodeList) xPath.compile("./entry/organizer[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODESET), xPath, idList));

			sectionElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			familyHistory.setLineNumber(sectionElement.getUserData("lineNumber") + " - " + sectionElement.getUserData("endLineNumber"));
			familyHistory.setXmlString(ApplicationUtil.nodeToString((Node) sectionElement));

			Element textElement = (Element) xPath.compile("./text[not(@nullFlavor)]").evaluate(sectionElement, XPathConstants.NODE);

			if (textElement != null) {
				familyHistory.getReferenceLinks().addAll((ApplicationUtil.readSectionTextReferences((NodeList) xPath.compile(".//*[not(@nullFlavor) and @ID]").
						evaluate(textElement, XPathConstants.NODESET))));
			}
			familyHistory.setIdList(idList);
		}
		return new AsyncResult<CCDAFamilyHistory>(familyHistory);
	}

	private ArrayList<CCDAFamilyHxOrg> readFamilyHxOrganizer(NodeList familyHxOrgNodeList, XPath xPath, List<CCDAID> idList) throws TransformerException, XPathExpressionException {

		ArrayList<CCDAFamilyHxOrg> familyHxOrganizerList = new ArrayList<>();
		CCDAFamilyHxOrg familyHxOrganizer;
		for (int i = 0; i < familyHxOrgNodeList.getLength(); i++) {
			familyHxOrganizer = new CCDAFamilyHxOrg();

			Element familyHxOrganizerElement = (Element) familyHxOrgNodeList.item(i);

			familyHxOrganizerElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			familyHxOrganizer.setLineNumber(familyHxOrganizerElement.getUserData("lineNumber") + " - " + familyHxOrganizerElement.getUserData("endLineNumber"));
			familyHxOrganizer.setXmlString(ApplicationUtil.nodeToString((Node) familyHxOrganizerElement));

			familyHxOrganizer.setTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath
					.compile("./templateId[not(@nullFlavor)]")
					.evaluate(familyHxOrganizerElement, XPathConstants.NODESET)));

			familyHxOrganizer.setStatusCode(ApplicationUtil.readCode((Element) xPath
					.compile("./statusCode[not(@nullFlavor)]")
					.evaluate(familyHxOrganizerElement, XPathConstants.NODE)));

			familyHxOrganizer.setRelationCode(ApplicationUtil.readCode((Element) xPath
					.compile("./subject/relatedSubject[@classCode=\"PRS\"]/code[not(@nullFlavor)]")
					.evaluate(familyHxOrganizerElement, XPathConstants.NODE)));

			familyHxOrganizer.setGenderCode(ApplicationUtil.readCode((Element) xPath
					.compile("./subject/relatedSubject[@classCode=\"PRS\"]/subject/administrativeGenderCode[not(@nullFlavor)]")
					.evaluate(familyHxOrganizerElement, XPathConstants.NODE)));

			familyHxOrganizer.setBirthTime(ApplicationUtil.readEffectivetime((Element) xPath
					.compile("./subject/relatedSubject[@classCode=\"PRS\"]/subject/birthTime[not(@nullFlavor)]")
					.evaluate(familyHxOrganizerElement, XPathConstants.NODE), xPath));

			familyHxOrganizer.setFamilyHxObs(readFamilyHxObservation((NodeList) xPath
					.compile("./component/observation[not(@nullFlavor)]")
					.evaluate(familyHxOrganizerElement, XPathConstants.NODESET), xPath, idList));

			familyHxOrganizerList.add(familyHxOrganizer);
		}
		return familyHxOrganizerList;
	}

	private ArrayList<CCDAFamilyHxObs> readFamilyHxObservation(NodeList familyHxObsNodeList, XPath xPath, List<CCDAID> idList) throws TransformerException, XPathExpressionException {
		ArrayList<CCDAFamilyHxObs> familyHxObservationList = new ArrayList<>();
		CCDAFamilyHxObs familyHxObservation;
		for (int i = 0; i < familyHxObsNodeList.getLength(); i++) {

			familyHxObservation = new CCDAFamilyHxObs();

			Element familyHxObsElement = (Element) familyHxObsNodeList.item(i);

			familyHxObsElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			familyHxObservation.setLineNumber(familyHxObsElement.getUserData("lineNumber") + " - " + familyHxObsElement.getUserData("endLineNumber"));
			familyHxObservation.setXmlString(ApplicationUtil.nodeToString((Node) familyHxObsElement));

			if (ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]")
					.evaluate(familyHxObsElement, XPathConstants.NODE), "observation") != null) {
				idList.add(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]")
					.evaluate(familyHxObsElement, XPathConstants.NODE), "observation"));
			}

			familyHxObservation.setReferenceText(ApplicationUtil.readTextReference((Element) xPath
					.compile(ApplicationConstants.REFERENCE_TEXT_EXPRESSION)
					.evaluate(familyHxObsElement, XPathConstants.NODE)));

			familyHxObservation.setTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath
					.compile("./templateId[not(@nullFlavor)]")
					.evaluate(familyHxObsElement, XPathConstants.NODESET)));

			familyHxObservation.setObservationTypeCode(ApplicationUtil.readCode((Element) xPath
					.compile("./code[not(@nullFlavor)]")
					.evaluate(familyHxObsElement, XPathConstants.NODE)));

			familyHxObservation.setObservationTime(ApplicationUtil.readEffectivetime((Element) xPath
					.compile("./effectiveTime[not(@nullFlavor)]")
					.evaluate(familyHxObsElement, XPathConstants.NODE), xPath));

			familyHxObservation.setStatusCode(ApplicationUtil.readCode((Element) xPath
					.compile("./statusCode[not(@nullFlavor)]")
					.evaluate(familyHxObsElement, XPathConstants.NODE)));

			familyHxObservation.setObservationValue(ApplicationUtil.readCode((Element) xPath
					.compile("./value[not(@nullFlavor)]")
					.evaluate(familyHxObsElement, XPathConstants.NODE)));

			Element ageObservationElement = (Element) xPath.compile("./entryRelationship[@typeCode=\"SUBJ\"]/observation")
					.evaluate(familyHxObsElement, XPathConstants.NODE);

			if(ageObservationElement !=null) {
				familyHxObservation.setAgeOnSetValue(ApplicationUtil.readQuantity((Element) xPath
						.compile("./value[not(@nullFlavor)]")
						.evaluate(ageObservationElement, XPathConstants.NODE)));

				familyHxObservation.setAgeOnSetReferenceText(ApplicationUtil.readTextReference((Element) xPath
						.compile(ApplicationConstants.REFERENCE_TEXT_EXPRESSION)
						.evaluate(ageObservationElement, XPathConstants.NODE)));
			}

			Element causeObservationElement = (Element) xPath.compile("./entryRelationship[@typeCode=\"CAUS\"]/observation")
					.evaluate(familyHxObsElement, XPathConstants.NODE);

			if(causeObservationElement != null) {
				familyHxObservation.setCausedDeathValue(ApplicationUtil.readCode((Element) xPath
						.compile("./value[not(@nullFlavor)]")
						.evaluate(causeObservationElement, XPathConstants.NODE)));

				familyHxObservation.setCausedDeathReferenceText(ApplicationUtil.readTextReference((Element) xPath
						.compile(ApplicationConstants.REFERENCE_TEXT_EXPRESSION)
						.evaluate(causeObservationElement, XPathConstants.NODE)));
			}

			familyHxObservationList.add(familyHxObservation);
		}
		return familyHxObservationList;
	}
}
