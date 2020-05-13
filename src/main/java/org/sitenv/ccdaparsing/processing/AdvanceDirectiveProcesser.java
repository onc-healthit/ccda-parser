package org.sitenv.ccdaparsing.processing;

import org.apache.log4j.Logger;
import org.sitenv.ccdaparsing.model.CCDAAdvanceDirective;
import org.sitenv.ccdaparsing.model.CCDAAdvanceDirectiveObs;
import org.sitenv.ccdaparsing.model.CCDAAdvanceDirectiveOrg;
import org.sitenv.ccdaparsing.model.CCDAID;
import org.sitenv.ccdaparsing.model.CCDAParticipant;
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
public class AdvanceDirectiveProcesser {
	private static final Logger logger = Logger.getLogger(AdvanceDirectiveProcesser.class);

	@Async()
	public Future<CCDAAdvanceDirective> retrieveAdvanceDirectiveDetails(XPath xPath, Document doc) throws XPathExpressionException, TransformerException {
		long startTime = System.currentTimeMillis();
		logger.info("Advance Directive parsing Start time:" + startTime);

		CCDAAdvanceDirective advanceDirective = null;
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.ADV_DIRECTIVE_EXPRESSION)
				.evaluate(doc, XPathConstants.NODE);
		List<CCDAID> idList = new ArrayList<>();
		if (sectionElement != null) {
			advanceDirective = new CCDAAdvanceDirective();
			if (ApplicationUtil.checkForNullFlavourNI(sectionElement)) {
				advanceDirective.setSectionNullFlavourWithNI(true);
				return new AsyncResult<CCDAAdvanceDirective>(advanceDirective);
			}
			advanceDirective.setTemplateIds(ApplicationUtil
					.readTemplateIdList((NodeList) xPath
							.compile("./templateId[not(@nullFlavor)]").
							evaluate(sectionElement, XPathConstants.NODESET)));
			advanceDirective.setSectionCode(ApplicationUtil.readCode((Element) xPath
					.compile("./code[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));

			advanceDirective.setAdvanceDirectiveOrgs(readAdvanceDirectiveOrganizers((NodeList) xPath
					.compile("./entry/organizer[not(@nullFlavor)] | ./entry/observation[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODESET), xPath, idList));

			sectionElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			advanceDirective.setLineNumber(sectionElement.getUserData("lineNumber") + " - " + sectionElement.getUserData("endLineNumber"));
			advanceDirective.setXmlString(ApplicationUtil.nodeToString((Node) sectionElement));

			Element textElement = (Element) xPath.compile("./text[not(@nullFlavor)]")
					.evaluate(sectionElement, XPathConstants.NODE);

			if (textElement != null) {
				advanceDirective.getReferenceLinks().addAll((ApplicationUtil
						.readSectionTextReferences((NodeList) xPath.compile(".//*[not(@nullFlavor) and @ID]").
								evaluate(textElement, XPathConstants.NODESET))));
			}
			advanceDirective.setIdList(idList);
		}
		logger.info("Advance Directive parsing End time:" + (System.currentTimeMillis() - startTime));
		return new AsyncResult<CCDAAdvanceDirective>(advanceDirective);
	}

	private ArrayList<CCDAAdvanceDirectiveOrg> readAdvanceDirectiveOrganizers(NodeList advDirectiveOrgNodeList, XPath xPath, List<CCDAID> idList) throws XPathExpressionException, TransformerException {
		ArrayList<CCDAAdvanceDirectiveOrg> advDirectiveOrgList = new ArrayList<>();
		CCDAAdvanceDirectiveOrg advDirectiveOrg;
		for (int i = 0; i < advDirectiveOrgNodeList.getLength(); i++) {
			advDirectiveOrg = new CCDAAdvanceDirectiveOrg();

			Element advDirectiveOrgElement = (Element) advDirectiveOrgNodeList.item(i);

			if (advDirectiveOrgElement.getTagName().equalsIgnoreCase("organizer")) {
				setAdvDirectiveOrg(advDirectiveOrg, advDirectiveOrgElement, xPath, idList);
			} else {
				advDirectiveOrg.setAdvanceDirectiveObs(readAdvDirectiveObservation(advDirectiveOrgElement, xPath, idList));
			}
			advDirectiveOrgList.add(advDirectiveOrg);
		}
		return advDirectiveOrgList;
	}

	private ArrayList<CCDAAdvanceDirectiveObs> readAdvDirectiveObservation(Element advDirectiveObsElement, XPath xPath, List<CCDAID> idList) throws XPathExpressionException, TransformerException {
		CCDAAdvanceDirectiveObs advanceDirectiveObs = readObservations(advDirectiveObsElement, xPath, idList);
		ArrayList<CCDAAdvanceDirectiveObs> obsList = new ArrayList<>();
		obsList.add(advanceDirectiveObs);

		return obsList;
	}

	private void setAdvDirectiveOrg(CCDAAdvanceDirectiveOrg advDirectiveOrg, Element advDirectiveOrgElement
			, XPath xPath, List<CCDAID> idList) throws XPathExpressionException, TransformerException {
		advDirectiveOrgElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		advDirectiveOrg.setLineNumber(advDirectiveOrgElement.getUserData("lineNumber") + " - " + advDirectiveOrgElement.getUserData("endLineNumber"));
		advDirectiveOrg.setXmlString(ApplicationUtil.nodeToString((Node) advDirectiveOrgElement));

		advDirectiveOrg.setTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath
				.compile("./templateId[not(@nullFlavor)]").
				evaluate(advDirectiveOrgElement, XPathConstants.NODESET)));

		advDirectiveOrg.setReferenceText(ApplicationUtil.readTextReference((Element) xPath
				.compile(ApplicationConstants.REFERENCE_TEXT_EXPRESSION).
				evaluate(advDirectiveOrgElement, XPathConstants.NODE)));

		if (ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
				evaluate(advDirectiveOrgElement, XPathConstants.NODE), "organizer") != null) {
			idList.add(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(advDirectiveOrgElement, XPathConstants.NODE), "organizer"));
		}

		advDirectiveOrg.setStatusCode(ApplicationUtil.readCode((Element) xPath
				.compile("./statusCode[not(@nullFlavor)]")
				.evaluate(advDirectiveOrgElement, XPathConstants.NODE)));

		advDirectiveOrg.setOrgCode(ApplicationUtil.readCode((Element) xPath
				.compile("./code[not(@nullFlavor)]").
				evaluate(advDirectiveOrgElement, XPathConstants.NODE)));

		NodeList obsNodeList = (NodeList) xPath.compile("./component/observation").
				evaluate(advDirectiveOrgElement, XPathConstants.NODESET);
		ArrayList<CCDAAdvanceDirectiveObs> obsList = new ArrayList<>();
		for (int i = 0; i < obsNodeList.getLength(); i++) {
			Element advDirectiveObsElement = (Element) obsNodeList.item(i);
			CCDAAdvanceDirectiveObs advanceDirectiveObs = readObservations(advDirectiveObsElement, xPath, idList);
			obsList.add(advanceDirectiveObs);
		}
		advDirectiveOrg.setAdvanceDirectiveObs(obsList);
	}

	private CCDAAdvanceDirectiveObs readObservations(Element advDirectiveObsElement, XPath xPath, List<CCDAID> idList) throws XPathExpressionException, TransformerException {
		CCDAAdvanceDirectiveObs advDirectiveObs = new CCDAAdvanceDirectiveObs();

		advDirectiveObsElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		advDirectiveObs.setLineNumber(advDirectiveObsElement.getUserData("lineNumber") + " - " + advDirectiveObsElement.getUserData("endLineNumber"));
		advDirectiveObs.setXmlString(ApplicationUtil.nodeToString((Node) advDirectiveObsElement));

		advDirectiveObs.setTemplateIds(ApplicationUtil.readTemplateIdList((NodeList) xPath
				.compile("./templateId[not(@nullFlavor)]").
				evaluate(advDirectiveObsElement, XPathConstants.NODESET)));

		advDirectiveObs.setReferenceText(ApplicationUtil.readTextReference((Element) xPath
				.compile(ApplicationConstants.REFERENCE_TEXT_EXPRESSION).
				evaluate(advDirectiveObsElement, XPathConstants.NODE)));

		if (ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
				evaluate(advDirectiveObsElement, XPathConstants.NODE), "observation") != null) {
			idList.add(ApplicationUtil.readID((Element) xPath.compile("./id[not(@nullFlavor)]").
					evaluate(advDirectiveObsElement, XPathConstants.NODE), "observation"));
		}

		advDirectiveObs.setStatusCode(ApplicationUtil.readCode((Element) xPath
				.compile("./statusCode[not(@nullFlavor)]").
				evaluate(advDirectiveObsElement, XPathConstants.NODE)));

		advDirectiveObs.setObservationTypeCode(ApplicationUtil.readCode((Element) xPath
				.compile("./code[not(@nullFlavor)]").
				evaluate(advDirectiveObsElement, XPathConstants.NODE)));

		advDirectiveObs.setObservationTime(ApplicationUtil.readEffectivetime((Element) xPath
				.compile("./effectiveTime[not(@nullFlavor)]").
				evaluate(advDirectiveObsElement, XPathConstants.NODE), xPath));

		advDirectiveObs.setObservationValue(ApplicationUtil.readCode((Element) xPath
				.compile("./value[not(@nullFlavor)]").
				evaluate(advDirectiveObsElement, XPathConstants.NODE)));

		advDirectiveObs.setVerifier(readParticipant((Element) xPath
				.compile("./participant[@typeCode='VRF']").
				evaluate(advDirectiveObsElement, XPathConstants.NODE), xPath));

		advDirectiveObs.setCareAgent(readParticipant((Element) xPath
				.compile("./participant[@typeCode='CST']").
				evaluate(advDirectiveObsElement, XPathConstants.NODE), xPath));

		Element referenceDocElement = (Element) xPath
				.compile("./reference[@typeCode='REFR']/externalDocument").
				evaluate(advDirectiveObsElement, XPathConstants.NODE);

		if(referenceDocElement != null)
			advDirectiveObs.setReferenceDocText(ApplicationUtil.readTextReference((Element) xPath
					.compile(ApplicationConstants.REFERENCE_TEXT_EXPRESSION).
					evaluate(referenceDocElement, XPathConstants.NODE)));

		return advDirectiveObs;
	}

	private CCDAParticipant readParticipant(Element participantElement, XPath xPath) throws XPathExpressionException, TransformerException {
		if(participantElement == null)
			return null;

		CCDAParticipant participant = new CCDAParticipant();

		participantElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		participant.setLineNumber(participantElement.getUserData("lineNumber") + " - " + participantElement.getUserData("endLineNumber"));
		participant.setXmlString(ApplicationUtil.nodeToString((Node) participantElement));

		Element nameElement = (Element) xPath.compile("./participantRole/playingEntity/name").
				evaluate(participantElement, XPathConstants.NODE);
		if(nameElement != null)
		{
			NodeList giveNameNodeList = (NodeList) xPath.compile("./given[not(@nullFlavor)]").
					evaluate(nameElement, XPathConstants.NODESET);
			for (int i = 0; i < giveNameNodeList.getLength(); i++) {
				Element givenNameElement = (Element) giveNameNodeList.item(i);
				if(!ApplicationUtil.isEmpty(givenNameElement.getAttribute("qualifier")))
				{
					participant.setPreviousName(ApplicationUtil.readTextContext(givenNameElement));
				}else if (i == 0) {
					participant.setFirstName(ApplicationUtil.readTextContext(givenNameElement));
				}else {
					participant.setMiddleName(ApplicationUtil.readTextContext(givenNameElement));
				}
			}

			participant.setLastName(ApplicationUtil.readTextContext((Element) xPath.compile("./family[not(@nullFlavor)]").
					evaluate(nameElement, XPathConstants.NODE)));
			participant.setSuffix(ApplicationUtil.readTextContext((Element) xPath.compile("./suffix[not(@nullFlavor)]").
					evaluate(nameElement, XPathConstants.NODE)));
		}

		participant.setAddress(ApplicationUtil.readAddress((Element) xPath
				.compile("./participantRole/addr[not(@nullFlavor)]").
				evaluate(participantElement, XPathConstants.NODE), xPath));

		participant.setTelecom(ApplicationUtil.readDataElement((Element) xPath
				.compile("./participantRole/telecom[not(@nullFlavor)]").
				evaluate(participantElement, XPathConstants.NODE)));

		return participant;
	}
}
