package org.sitenv.ccdaparsing.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sitenv.ccdaparsing.configuration.CCDAParsingAPIConfiguration;
import org.sitenv.ccdaparsing.model.CCDAAssignedEntity;
import org.sitenv.ccdaparsing.model.CCDACode;
import org.sitenv.ccdaparsing.model.CCDAEffTime;
import org.sitenv.ccdaparsing.model.CCDAID;
import org.sitenv.ccdaparsing.model.CCDAII;
import org.sitenv.ccdaparsing.model.CCDAMedicalEquipment;
import org.sitenv.ccdaparsing.processing.MedicalEquipmentProcessor;
import org.sitenv.ccdaparsing.util.PositionalXMLReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CCDAParsingAPIConfiguration.class})
public class MedicalEquipmentTest {

    private final static String CCDA_DOC_WITH_MULTIPLE_IMPLANTS = "src/test/resources/CCD_MedicalEquipment_With_Organizer_Procedure_Supply.xml";

    @Autowired
    private MedicalEquipmentProcessor medicalEquipmentProcessor;
    private static CCDAMedicalEquipment medicalEquipment;
    private static XPath xPath;
    private static Document doc;

    @BeforeClass
    public static void setUp() throws IOException, SAXException {
        doc = PositionalXMLReader.readXML(new FileInputStream(new File(CCDA_DOC_WITH_MULTIPLE_IMPLANTS)));
        xPath = XPathFactory.newInstance().newXPath();
    }

    @Before
    public void setUpMedicalEquipmentProcessor() throws XPathExpressionException, TransformerException, ExecutionException, InterruptedException {
        if (medicalEquipment == null) {
            medicalEquipment = medicalEquipmentProcessor.retrieveMedicalEquipments(xPath, doc).get();
        }
    }

    @Test
    public void returnsValidMedicalEquipment() {
        Assert.assertNotNull(medicalEquipment);
        Assert.assertFalse(medicalEquipment.isSectionNullFlavourWithNI());
    }

    @Test
    public void returnsNoProcedureActPatientUdi() {
        Assert.assertNull(medicalEquipment.getProcedureActs().get(0).getPatientUDI());
    }

    @Test
    public void returnsNoProcedureActScopingEntityId() {
        Assert.assertNull(medicalEquipment.getProcedureActs().get(0).getScopingEntityId());
    }

    @Test
    public void returnsNoProcedureActDeviceCode() {
        Assert.assertNull(medicalEquipment.getProcedureActs().get(0).getDeviceCode());
    }

    @Test
    public void returnsNoProcedureActUdi() {
        Assert.assertNull(medicalEquipment.getProcedureActs().get(0).getUdi());
    }

    @Test
    public void returnsNoProcedureActPiTemplateId() {
        Assert.assertNull(medicalEquipment.getProcedureActs().get(0).getPiTemplateId());
    }

    @Test
    public void returnsValidProcedureActPerformers() {
        ArrayList<CCDAAssignedEntity> performers = medicalEquipment.getProcedureActs().get(0).getPerformer();
        Assert.assertNotNull(performers);
        Assert.assertEquals(1, performers.size());
        CCDAAssignedEntity firstPerformer = performers.get(0);

        Assert.assertNotNull(firstPerformer.getTelecom());
        Assert.assertEquals(1, firstPerformer.getTelecom().size());
        Assert.assertEquals("tel: +1(555)-555-5004", firstPerformer.getTelecom().get(0).getValue());
        Assert.assertEquals("WP", firstPerformer.getTelecom().get(0).getUse());

        Assert.assertNotNull(firstPerformer.getAddresses());
        Assert.assertEquals(1, firstPerformer.getAddresses().size());
        Assert.assertEquals("1004 Health Care Drive", firstPerformer.getAddresses().get(0).getAddressLine1().getValue());
        Assert.assertNull(firstPerformer.getAddresses().get(0).getAddressLine2());
        Assert.assertEquals("Ann Arbor", firstPerformer.getAddresses().get(0).getCity().getValue());
        Assert.assertEquals("MI", firstPerformer.getAddresses().get(0).getState().getValue());
        Assert.assertEquals("02368", firstPerformer.getAddresses().get(0).getPostalCode().getValue());
        Assert.assertEquals("US", firstPerformer.getAddresses().get(0).getCountry().getValue());
        Assert.assertNull(firstPerformer.getAddresses().get(0).getPostalAddressUse());

        Assert.assertNotNull(firstPerformer.getOrganization());
        Assert.assertNotNull(firstPerformer.getOrganization().getNames());
        Assert.assertNotNull(firstPerformer.getOrganization().getTelecom());
        Assert.assertNotNull(firstPerformer.getOrganization().getAddress());

        Assert.assertEquals(1, firstPerformer.getOrganization().getNames().size());
        Assert.assertEquals("Community Health and Hospitals", firstPerformer.getOrganization().getNames().get(0).getValue());


        Assert.assertEquals(1, firstPerformer.getOrganization().getTelecom().size());
        Assert.assertEquals("tel:+1(555)-555-5005", firstPerformer.getOrganization().getTelecom().get(0).getValue());
        Assert.assertEquals("WP", firstPerformer.getOrganization().getTelecom().get(0).getUse());

        Assert.assertEquals(1, firstPerformer.getOrganization().getAddress().size());
        Assert.assertEquals("1003 Health Care Drive", firstPerformer.getOrganization().getAddress().get(0).getAddressLine1().getValue());
        Assert.assertNull(firstPerformer.getOrganization().getAddress().get(0).getAddressLine2());
        Assert.assertEquals("Ann Arbor", firstPerformer.getOrganization().getAddress().get(0).getCity().getValue());
        Assert.assertEquals("MI", firstPerformer.getOrganization().getAddress().get(0).getState().getValue());
        Assert.assertEquals("02368", firstPerformer.getOrganization().getAddress().get(0).getPostalCode().getValue());
        Assert.assertEquals("US", firstPerformer.getOrganization().getAddress().get(0).getCountry().getValue());
        Assert.assertNull(firstPerformer.getOrganization().getAddress().get(0).getPostalAddressUse());
    }

    @Test
    public void returnsValidProcedureActTargetSiteCode() {
        CCDACode targetSiteCode = medicalEquipment.getProcedureActs().get(0).getTargetSiteCode();
        Assert.assertEquals("28273000", targetSiteCode.getCode());
        Assert.assertEquals("2.16.840.1.113883.6.96", targetSiteCode.getCodeSystem());
        Assert.assertEquals("SNOMED CT", targetSiteCode.getCodeSystemName());
        Assert.assertEquals("bile duct", targetSiteCode.getDisplayName());
    }

    @Test
    public void returnsValidProcedureActStatuc() {
        Assert.assertEquals("completed", medicalEquipment.getProcedureActs().get(0).getProcStatus().getCode());
    }

    @Test
    public void returnsValidProcedureActProcCode() {
        CCDACode procCode = medicalEquipment.getProcedureActs().get(0).getProcCode();
        Assert.assertEquals("103716009", procCode.getCode());
        Assert.assertEquals("2.16.840.1.113883.6.96", procCode.getCodeSystem());
        Assert.assertEquals("SNOMED CT", procCode.getCodeSystemName());
        Assert.assertEquals("Stent Placement", procCode.getDisplayName());
    }

    @Test
    public void returnsValidProcedureActTemplateIds() {
        ArrayList<CCDAII> procActProcSectionTemplateId = medicalEquipment.getProcedureActs().get(0).getSectionTemplateId();
        Assert.assertNotNull(procActProcSectionTemplateId);
        Assert.assertEquals(2, procActProcSectionTemplateId.size());

        Assert.assertEquals("2.16.840.1.113883.10.20.22.4.14", procActProcSectionTemplateId.get(0).getRootValue());
        Assert.assertEquals("2014-06-09", procActProcSectionTemplateId.get(0).getExtValue());
        Assert.assertEquals("2.16.840.1.113883.10.20.22.4.14", procActProcSectionTemplateId.get(1).getRootValue());
        Assert.assertNull(procActProcSectionTemplateId.get(1).getExtValue());
    }

    @Test
    public void returnsOneProcedureAct() {
        Assert.assertEquals(1, medicalEquipment.getProcedureActs().size());
    }

    @Test
    public void returnsValidSupplyActTimeOfUse() {
        CCDAEffTime timeOfUseNonMedicalSupplyAct = medicalEquipment.getSupplyActivities().get(0).getTimeOfUse();
        Assert.assertFalse(timeOfUseNonMedicalSupplyAct.getLowPresent());
        Assert.assertTrue(timeOfUseNonMedicalSupplyAct.getHighPresent());
        Assert.assertEquals("20030202", timeOfUseNonMedicalSupplyAct.getHigh().getValue());
    }

    @Test
    public void returnsValidSupplyActStatus() {
        Assert.assertEquals("completed", medicalEquipment.getSupplyActivities().get(0).getStatus().getCode());
    }

    @Test
    public void returnsNoSupplyActSupplyCode() {
        Assert.assertNull(medicalEquipment.getSupplyActivities().get(0).getSupplyCode());
    }

    @Test
    public void returnsNoSupplyActReferenceText() {
        Assert.assertNull(medicalEquipment.getSupplyActivities().get(0).getReferenceText());
    }

    public void returnsNoSupplyActIds() {
        Assert.assertNull(medicalEquipment.getSupplyActivities().get(0).getIds());
    }

    @Test
    public void returnsValidSupplyActTemplateIds() {
        List<CCDAII> nonMedicalEquipmentOrgTemplateIds = medicalEquipment.getSupplyActivities().get(0).getTemplateIds();
        Assert.assertEquals(2, nonMedicalEquipmentOrgTemplateIds.size());
        Assert.assertEquals("2.16.840.1.113883.10.20.22.4.50", nonMedicalEquipmentOrgTemplateIds.get(0).getRootValue());
        Assert.assertEquals("2014-06-09", nonMedicalEquipmentOrgTemplateIds.get(0).getExtValue());
        Assert.assertEquals("2.16.840.1.113883.10.20.22.4.50", nonMedicalEquipmentOrgTemplateIds.get(1).getRootValue());
        Assert.assertNull(nonMedicalEquipmentOrgTemplateIds.get(1).getExtValue());
    }

    @Test
    public void returnsOneSupplyActivity() {
        Assert.assertEquals(1, medicalEquipment.getSupplyActivities().size());
    }

    @Test
    public void returnsValidEquipmentOrgsTimeOfUse() {
        CCDAEffTime timeOfUseMedicalEquipmentOrg = medicalEquipment.getEquipmentOrgs().get(0).getTimeOfUse();
        Assert.assertTrue(timeOfUseMedicalEquipmentOrg.getLowPresent());
        Assert.assertFalse(timeOfUseMedicalEquipmentOrg.getHighPresent());
        Assert.assertEquals("20070103", timeOfUseMedicalEquipmentOrg.getLow().getValue());
    }

    @Test
    public void returnsValidEquipmentOrgsStatus() {
        Assert.assertNotNull(medicalEquipment.getEquipmentOrgs().get(0).getStatus());
        Assert.assertEquals("completed", medicalEquipment.getEquipmentOrgs().get(0).getStatus().getCode());
    }

    @Test
    public void returnsValidEquipmentOrgsSupplyCode() {
        CCDACode supplyCode = medicalEquipment.getEquipmentOrgs().get(0).getSupplyCode();
        Assert.assertEquals("40388003", supplyCode.getCode());
        Assert.assertEquals("2.16.840.1.113883.6.96", supplyCode.getCodeSystem());
        Assert.assertEquals("SNOMED CT", supplyCode.getCodeSystemName());
        Assert.assertEquals("Implant", supplyCode.getDisplayName());
    }

    @Test
    public void returnsNoEquipmentOrgsReferenceText() {
        Assert.assertNull(medicalEquipment.getEquipmentOrgs().get(0).getReferenceText());
    }

    @Test
    public void returnsNoEquipmentOrgsIds() {
        Assert.assertEquals(0, medicalEquipment.getEquipmentOrgs().get(0).getIds().size());
    }

    @Test
    public void returnsValidEquipmentOrgsTemplateIds() {
        List<CCDAII> medicalEquipmentOrgTemplateIds = medicalEquipment.getEquipmentOrgs().get(0).getTemplateIds();
        Assert.assertEquals(1, medicalEquipmentOrgTemplateIds.size());
        Assert.assertEquals("2.16.840.1.113883.10.20.22.4.135", medicalEquipmentOrgTemplateIds.get(0).getRootValue());
        Assert.assertNull(medicalEquipmentOrgTemplateIds.get(0).getExtValue());
    }

    @Test
    public void returnsOneEquipmentOrg() {
        Assert.assertEquals(1, medicalEquipment.getEquipmentOrgs().size());
    }

    @Test
    public void returnsValidMedicalEquipmentIds() {
        List<CCDAID> ids = medicalEquipment.getIds();
        Assert.assertEquals(3, ids.size());
        Assert.assertEquals("d5b614bd-01ce-410d-8726-e1fd01dcc72a", ids.get(0).getRoot());
        Assert.assertNull(ids.get(0).getExtension());
        Assert.assertEquals("cf75f5be-1da0-4256-8276-94b7fc73f9f9", ids.get(1).getRoot());
        Assert.assertNull(ids.get(0).getExtension());
        Assert.assertEquals("3e414708-0e61-4d48-8863-484a2d473a02", ids.get(2).getRoot());
        Assert.assertNull(ids.get(0).getExtension());
    }

    @Test
    public void returnsValidMedicalEquipmentSectionCode() {
        CCDACode sectionCode = medicalEquipment.getSectionCode();
        Assert.assertEquals("46264-8", sectionCode.getCode());
        Assert.assertEquals("2.16.840.1.113883.6.1", sectionCode.getCodeSystem());
        Assert.assertEquals("LOINC", sectionCode.getCodeSystemName());
        Assert.assertNull(sectionCode.getDisplayName());
    }

    @Test
    public void returnsValidMedicalEquipmentTemplateIds() {
        ArrayList<CCDAII> templateIds = medicalEquipment.getTemplateIds();
        Assert.assertEquals(2, templateIds.size());
        Assert.assertEquals("2.16.840.1.113883.10.20.22.2.23", templateIds.get(0).getRootValue());
        Assert.assertEquals("2014-06-09", templateIds.get(0).getExtValue());
        Assert.assertEquals("2.16.840.1.113883.10.20.22.2.23", templateIds.get(1).getRootValue());
        Assert.assertNull(templateIds.get(1).getExtValue());
    }

}