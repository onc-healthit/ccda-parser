package org.sitenv.ccdaparsing.processing;

import java.util.ArrayList;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.sitenv.ccdaparsing.model.CCDACareTeamMember;
import org.sitenv.ccdaparsing.model.CCDAParticipant;
import org.sitenv.ccdaparsing.util.ApplicationConstants;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class CareTeamMemberProcessor {
	
	public static CCDACareTeamMember retrieveCTMDetails(XPath xPath , Document doc) throws XPathExpressionException,TransformerException
	{
		NodeList performerNodeList = (NodeList) xPath.compile(ApplicationConstants.CTM_EXPRESSION).evaluate(doc, XPathConstants.NODESET);
		CCDACareTeamMember careTeamMember = new CCDACareTeamMember();
		ArrayList<CCDAParticipant> participantList = new ArrayList<>();
		CCDAParticipant participant ;
		for (int i = 0; i < performerNodeList.getLength(); i++) {
			
			participant = new CCDAParticipant();
			Element performerElement = (Element) performerNodeList.item(i);
			
			participant.setAddress(ApplicationUtil.readAddress((Element) xPath.compile("./assignedEntity/addr[not(@nullFlavor)]").
					evaluate(performerElement, XPathConstants.NODE), xPath));
			
			readName((Element) xPath.compile("./assignedEntity/assignedPerson/name[not(@nullFlavor)]").
	    				evaluate(performerElement, XPathConstants.NODE), participant , xPath);
			
			participant.setTelecom(ApplicationUtil.readDataElement((Element) xPath.compile("./assignedEntity/telecom[not(@nullFlavor)]").
					evaluate(performerElement, XPathConstants.NODE)));
			participantList.add(participant);
		}
		careTeamMember.setMembers(participantList);
		
		return careTeamMember;
	}
	
	public static void readName(Element nameElement,CCDAParticipant participant,XPath xPath) throws XPathExpressionException
	{
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
	}

}
