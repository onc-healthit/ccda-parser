package org.sitenv.ccdaparsing.processing;

import java.util.List;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.sitenv.ccdaparsing.model.CCDAID;
import org.sitenv.ccdaparsing.model.CCDAPOT;
import org.sitenv.ccdaparsing.util.ApplicationConstants;
import org.sitenv.ccdaparsing.util.ApplicationUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class POTProcessor {
	
	public static CCDAPOT retrievePOTDetails(XPath xPath , Document doc, List<CCDAID> idList) throws XPathExpressionException,TransformerException
	{
		CCDAPOT pot = null;
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.POT_EXPRESSION).evaluate(doc, XPathConstants.NODE);
		if(sectionElement != null)
		{
			pot = new CCDAPOT();
			pot.setTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
									evaluate(sectionElement, XPathConstants.NODESET)));
		
			pot.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
			
			pot.setNarrativeText(ApplicationUtil.readDataElement((Element) xPath.compile("./text[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
			
			NodeList plannedMedicationNodeList = (NodeList) xPath.compile(ApplicationConstants.POT_MEDICATION_EXPRESSION).evaluate(sectionElement, XPathConstants.NODESET);
			pot.setPlannedMedicationActivity(MedicationProcessor.readMedication(plannedMedicationNodeList, xPath,idList));
			
			NodeList plannedEncounterNodeList = (NodeList) xPath.compile(ApplicationConstants.POT_ENCOUNTER_EXPRESSION).evaluate(sectionElement, XPathConstants.NODESET);
			pot.setPlannedEncounter(EncounterDiagnosesProcessor.readEncounterActivity(plannedEncounterNodeList, xPath,idList));
			
			NodeList plannedProcedureNodeList = (NodeList) xPath.compile(ApplicationConstants.POT_PROCEDURE_EXPRESSION).evaluate(sectionElement, XPathConstants.NODESET);
			pot.setPlannedProcedure(ProcedureProcessor.readProcedures(plannedProcedureNodeList,xPath,idList));
		}
		
		return pot;
	}
	
	
	

}
