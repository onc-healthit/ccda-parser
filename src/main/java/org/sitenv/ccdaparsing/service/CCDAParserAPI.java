package org.sitenv.ccdaparsing.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.sitenv.ccdaparsing.model.CCDARefModel;
import org.sitenv.ccdaparsing.processing.CareTeamMemberProcessor;
import org.sitenv.ccdaparsing.processing.EncounterDiagnosesProcessor;
import org.sitenv.ccdaparsing.processing.GoalsProcessor;
import org.sitenv.ccdaparsing.processing.HealthConcernsProcessor;
import org.sitenv.ccdaparsing.processing.ImmunizationProcessor;
import org.sitenv.ccdaparsing.processing.LaboratoryResultsProcessor;
import org.sitenv.ccdaparsing.processing.LaboratoryTestProcessor;
import org.sitenv.ccdaparsing.processing.MediactionAllergiesProcessor;
import org.sitenv.ccdaparsing.processing.MedicationProcessor;
import org.sitenv.ccdaparsing.processing.POTProcessor;
import org.sitenv.ccdaparsing.processing.PatientProcessor;
import org.sitenv.ccdaparsing.processing.ProblemProcessor;
import org.sitenv.ccdaparsing.processing.ProcedureProcessor;
import org.sitenv.ccdaparsing.processing.SmokingStatusProcessor;
import org.sitenv.ccdaparsing.processing.UDIProcessor;
import org.sitenv.ccdaparsing.processing.VitalSignProcessor;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class CCDAParserAPI {

	private static final Logger logger = Logger.getLogger(CCDAParserAPI.class);
	
	private static XPath xPath = XPathFactory.newInstance().newXPath();
	 
	public static CCDARefModel parseCCDA2_1(String filePath) {
		
		DocumentBuilderFactory factory = 
				DocumentBuilderFactory.newInstance();
		CCDARefModel refModel = new CCDARefModel();
	    try {
			    
	    	logger.info("Parsing CCDA document");
	    	DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new File(filePath));
			refModel.setPatient(PatientProcessor.retrievePatientDetails(xPath, doc));
			refModel.setEncounter(EncounterDiagnosesProcessor.retrieveEncounterDetails(xPath, doc));
			refModel.setProblem(ProblemProcessor.retrieveProblemDetails(xPath, doc));
			refModel.setMedication(MedicationProcessor.retrieveMedicationDetails(xPath, doc));
			refModel.setAllergy(MediactionAllergiesProcessor.retrieveAllergiesDetails(xPath, doc));
			refModel.setSmokingStatus(SmokingStatusProcessor.retrieveSmokingStatusDetails(xPath, doc));
			refModel.setLabTests(LaboratoryTestProcessor.retrieveLabTests(xPath, doc));
			refModel.setLabResults(LaboratoryResultsProcessor.retrieveLabResults(xPath, doc));
			refModel.setVitalSigns(VitalSignProcessor.retrieveVitalSigns(xPath, doc));
			refModel.setProcedure(ProcedureProcessor.retrievePrcedureDetails(xPath, doc));
			refModel.setMembers(CareTeamMemberProcessor.retrieveCTMDetails(xPath, doc));
			refModel.setImmunization(ImmunizationProcessor.retrieveImmunizationDetails(xPath, doc));
			refModel.setUdi(UDIProcessor.retrieveUDIDetails(refModel.getProcedure()));
			refModel.setPlanOfTreatment(POTProcessor.retrievePOTDetails(xPath, doc));
			refModel.setGoals(GoalsProcessor.retrieveGoalsDetails(xPath, doc));
			refModel.setHcs(HealthConcernsProcessor.retrieveHealthConcernDetails(xPath, doc));
			logger.info("Parsing CCDA document completed");
		    }
	    	catch (FileNotFoundException fnfException) 
	    	{
		    	logger.error(fnfException);
			}
	    	catch(XPathExpressionException xpeException)
			{
	    		logger.error(xpeException);
			}
	    	catch (IOException ioException) 
	    	{
	    		logger.error(ioException);
			}
	    	catch (SAXException saxException) 
	    	{
	    		logger.error(saxException);
			}
	    	catch (ParserConfigurationException pcException)
	    	{
	    		logger.error(pcException);
			}
	    	catch (NullPointerException npException) 
	    	{
	    		logger.error(npException);
			}
	    
	    return refModel;
	
	}
}
