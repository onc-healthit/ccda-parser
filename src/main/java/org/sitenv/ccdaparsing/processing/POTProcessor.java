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
import org.sitenv.ccdaparsing.model.CCDAPOT;
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
public class POTProcessor {
	
	private static final Logger logger = LogManager.getLogger(POTProcessor.class);
	
	@Autowired
	MedicationProcessor medicationProcessor;
	
	@Autowired
	EncounterDiagnosesProcessor encounterDiagnosesProcessor;
	
	@Autowired
	ProcedureProcessor procedureProcessor;
	
	@Async()
	public Future<CCDAPOT> retrievePOTDetails(XPath xPath , Document doc) throws XPathExpressionException,TransformerException
	{
		long startTime = System.currentTimeMillis();
    	logger.info("POT parsing Start time:"+ startTime);
		CCDAPOT pot = null;
		Element sectionElement = (Element) xPath.compile(ApplicationConstants.POT_EXPRESSION).evaluate(doc, XPathConstants.NODE);
		List<CCDAID> idList = new ArrayList<>();
		if(sectionElement != null)
		{
			pot = new CCDAPOT();
			if(ApplicationUtil.checkForNullFlavourNI(sectionElement))
			{
				pot.setSectionNullFlavourWithNI(true);
				return new AsyncResult<CCDAPOT>(pot);
			}
			pot.setTemplateId(ApplicationUtil.readTemplateIdList((NodeList) xPath.compile("./templateId[not(@nullFlavor)]").
									evaluate(sectionElement, XPathConstants.NODESET)));
		
			pot.setSectionCode(ApplicationUtil.readCode((Element) xPath.compile("./code[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
			
			pot.setNarrativeText(ApplicationUtil.readDataElement((Element) xPath.compile("./text[not(@nullFlavor)]").
					evaluate(sectionElement, XPathConstants.NODE)));
			
			NodeList plannedMedicationNodeList = (NodeList) xPath.compile(ApplicationConstants.POT_MEDICATION_EXPRESSION).evaluate(sectionElement, XPathConstants.NODESET);
			pot.setPlannedMedicationActivity(medicationProcessor.readMedication(plannedMedicationNodeList, xPath,idList));
			
			NodeList plannedEncounterNodeList = (NodeList) xPath.compile(ApplicationConstants.POT_ENCOUNTER_EXPRESSION).evaluate(sectionElement, XPathConstants.NODESET);
			pot.setPlannedEncounter(encounterDiagnosesProcessor.readEncounterActivity(plannedEncounterNodeList, xPath,idList));
			
			NodeList plannedProcedureNodeList = (NodeList) xPath.compile(ApplicationConstants.POT_PROCEDURE_EXPRESSION).evaluate(sectionElement, XPathConstants.NODESET);
			pot.setPlannedProcedure(procedureProcessor.readProcedures(plannedProcedureNodeList,xPath,idList));
			pot.setIdList(idList);
		}
		logger.info("POT parsing End time:"+ (System.currentTimeMillis() - startTime));
		return new AsyncResult<CCDAPOT>(pot);
	}
	
	
	

}
