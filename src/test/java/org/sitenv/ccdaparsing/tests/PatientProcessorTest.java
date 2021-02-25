package org.sitenv.ccdaparsing.tests;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.sitenv.ccdaparsing.model.CCDAAddress;
import org.sitenv.ccdaparsing.model.CCDACode;
import org.sitenv.ccdaparsing.model.CCDADataElement;
import org.sitenv.ccdaparsing.model.CCDAPL;
import org.sitenv.ccdaparsing.model.CCDAPatient;
import org.sitenv.ccdaparsing.processing.PatientProcessor;
import org.w3c.dom.Document;

public class PatientProcessorTest {

	private static String CCDA_DOC = "src/test/resources/170.315_b1_toc_amb_ccd_r21_sample1_v1.xml";
	private static CCDAPatient patient;
	private CCDADataElement firstName;
	private CCDADataElement lastName;
	private CCDADataElement middleName;
	private CCDADataElement previousName;
	private CCDADataElement suffix;
	private CCDADataElement dob;
	private ArrayList<CCDAAddress> addressList;
	private ArrayList<CCDAPL> languageCommunication;
	private CCDACode raceCode;
	private ArrayList<CCDACode> sdtcRaceCodes;
	private CCDACode administrativeGenderCode;
	private CCDACode maritalStatusCode;
	private CCDACode ethnicity;
	private CCDACode religiousAffiliationCode;
	private ArrayList<CCDADataElement> telecomList;
	private CCDAAddress birthPlace;

	@BeforeClass
	public static void setUp() throws Exception {
		PatientProcessor patientProcessor = new PatientProcessor();
		// removed fields to ensure no side effects with DocumentRoot
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(false);
		DocumentBuilder builder = factory.newDocumentBuilder();
		final Document doc = builder.parse(new File(CCDA_DOC));
		XPath xPath = XPathFactory.newInstance().newXPath();
		patient = patientProcessor.retrievePatientDetails(xPath, doc).get();
	}

	private void setNames() {
		firstName = new CCDADataElement("Alice");
		lastName = new CCDADataElement("Newman");
		middleName = new CCDADataElement("Jones");
		previousName = new CCDADataElement("Alicia");
		suffix = new CCDADataElement("Jr");
	}

	private void setTelecomData() {
		telecomList = new ArrayList<>();
		CCDADataElement telecomOne = new CCDADataElement();
		telecomOne.setValue("tel:+1(555)-777-1234");
		telecomOne.setUse("MC");
		String xmlstring = "<telecom xmlns:xsi=\\\"http://www.w3.org/2001/XMLSchema-instance\\\" use=\\\"MC\\\" value=\\\"tel:+1(555)-777-1234\\\"/>";
		telecomOne.setXmlString(xmlstring.replaceAll("\\\\", ""));
		
		telecomList.add(telecomOne);

		CCDADataElement telecomTwo = new CCDADataElement();
		telecomTwo.setValue("tel:+1(555)-723-1544");
		telecomTwo.setUse("HP");
		telecomList.add(telecomTwo);
	}

	private void setAddressData() {
		addressList = new ArrayList<>();
		CCDAAddress addressOne = new CCDAAddress();
		addressOne.setAddressLine1(new CCDADataElement("1357 Amber Dr"));
		addressOne.setCity(new CCDADataElement("Beaverton"));
		addressOne.setState(new CCDADataElement("OR"));
		addressOne.setCountry(new CCDADataElement("US"));
		addressOne.setPostalCode(new CCDADataElement("97006"));
		addressOne.setPostalAddressUse("HP");
		addressList.add(addressOne);
	}

	private void setDob() {
		dob = new CCDADataElement("19700501");
	}

	private void setLanguageCommunication() {
		languageCommunication = new ArrayList<>();
		CCDAPL languageCommunicationOne = new CCDAPL();

		CCDACode modeCode = new CCDACode();
		modeCode.setCode("ESP");
		modeCode.setCodeSystem("2.16.840.1.113883.5.60");
		modeCode.setCodeSystemName("LanguageAbilityMode");
		modeCode.setDisplayName("Expressed spoken");

		CCDACode lanCommCode = new CCDACode();
		lanCommCode.setCode("eng");
		languageCommunicationOne.setLanguageCode(lanCommCode);
		languageCommunicationOne.setModeCode(modeCode);
		languageCommunicationOne.setPreferenceInd(new CCDADataElement("true"));
		languageCommunication.add(languageCommunicationOne);
	}

	private void setRaceCode() {
		raceCode = new CCDACode();
		raceCode.setCode("2106-3");
		raceCode.setCodeSystem("2.16.840.1.113883.6.238");
		raceCode.setCodeSystemName("Race & Ethnicity - CDC");
		raceCode.setDisplayName("White");

	}

	private void setSdtcRaceCode() {
		sdtcRaceCodes = new ArrayList<CCDACode>();
		CCDACode sdtcRaceCodeOne = new CCDACode();
		sdtcRaceCodeOne.setCode("1019-9");
		sdtcRaceCodeOne.setCodeSystem("2.16.840.1.113883.6.238");
		sdtcRaceCodeOne.setCodeSystemName("Race & Ethnicity - CDC");
		sdtcRaceCodeOne.setDisplayName("White Mountain Apache");
		sdtcRaceCodes.add(sdtcRaceCodeOne);
	}

	private void setAdministrativeGenderCode() {
		administrativeGenderCode = new CCDACode();
		administrativeGenderCode.setCode("F");
		administrativeGenderCode.setCodeSystem("2.16.840.1.113883.5.1");
		administrativeGenderCode.setCodeSystemName("AdministrativeGender");
		administrativeGenderCode.setDisplayName("Female");
	}

	private void setMaritalStatusCode() {
		maritalStatusCode = new CCDACode();
		maritalStatusCode.setCode("M");
		maritalStatusCode.setCodeSystem("2.16.840.1.113883.5.2");
		maritalStatusCode.setCodeSystemName("MaritalStatusCode");
		maritalStatusCode.setDisplayName("Married");
	}

	private void setEthnicity() {
		ethnicity = new CCDACode();
		ethnicity.setCode("2186-5");
		ethnicity.setCodeSystem("2.16.840.1.113883.6.238");
		ethnicity.setCodeSystemName("Race & Ethnicity - CDC");
		ethnicity.setDisplayName("Not Hispanic or Latino");
	}

	private void setReligiousAffiliationCode() {
		religiousAffiliationCode = new CCDACode();
		religiousAffiliationCode.setCode("1013");
		religiousAffiliationCode.setCodeSystem("2.16.840.1.113883.5.1076");
		religiousAffiliationCode.setCodeSystemName("HL7 Religious Affiliation");
		religiousAffiliationCode.setDisplayName("Christian (non-Catholic, non-specific)");
	}

	private void setBirthPlace() {
		birthPlace = new CCDAAddress();
		birthPlace.setAddressLine1(new CCDADataElement("1357 Amber Dr"));
		birthPlace.setCity(new CCDADataElement("Beaverton"));
		birthPlace.setState(new CCDADataElement("OR"));
		birthPlace.setCountry(new CCDADataElement("US"));
		birthPlace.setPostalCode(new CCDADataElement("97006"));
	}

	@Test
	public void testPatient() throws Exception {
		Assert.assertNotNull(patient);
	}

	@Test
	public void testPatientName() {
		setNames();
		Assert.assertEquals("Patient first name test case failed", firstName, patient.getFirstName());
		Assert.assertEquals("Patient Last name test case failed", lastName, patient.getLastName());
		Assert.assertEquals("Patient Middle name test case failed", middleName, patient.getMiddleName());
		Assert.assertEquals("Patient Previous name test case failed", previousName, patient.getPreviousName());
		Assert.assertEquals("Patient Suffix test case failed", suffix, patient.getSuffix());
	}

	@Test
	public void testPatientAddress() {
		setAddressData();
		Assert.assertEquals("Patient Address test case failed", addressList, patient.getAddresses());
	}

	@Test
	public void testPatientTelecom() {
		setTelecomData();
		
		Assert.assertTrue(telecomList != null);
		Assert.assertTrue(patient.getTelecom() != null);
		
		Assert.assertTrue(patient.getTelecom().size() == telecomList.size());
					
		for (int i = 0; i < patient.getTelecom().size(); i++) {
			CCDADataElement setTelecom = telecomList.get(i);
			CCDADataElement parsedTelecom = patient.getTelecom().get(i);
			Assert.assertTrue("telecom @use or @value are not equal", setTelecom.isValueAndUseEqual(parsedTelecom));
		}		
	}

	@Test
	public void testDob() {
		setDob();
		Assert.assertEquals("DOB test case failed", dob.getValue(), patient.getDob().getValue());
	}

	// TODO: Find out why this fails and either fix test and/or fix source
	@Ignore
	@Test
	public void testLanguageCommunications() {
		setLanguageCommunication();
		Assert.assertEquals("Language Communications test case failed", languageCommunication,
				patient.getLanguageCommunication());
	}

	// TODO: Find out why this fails and either fix test and/or fix source
	@Ignore
	@Test
	public void testRaceCodes() {
		setRaceCode();
		Assert.assertEquals("raceCodes test case failed", raceCode, patient.getRaceCodes());
	}

	// TODO: Find out why this fails and either fix test and/or fix source
	@Ignore
	@Test
	public void testSdtcRaceCodes() {
		setSdtcRaceCode();
		Assert.assertEquals("sdtcRaceCodes test case failed", sdtcRaceCodes, patient.getSdtcRaceCodes());
	}

	// TODO: Find out why this fails and either fix test and/or fix source
	@Ignore
	@Test
	public void testAdministrativeGenderCode() {
		setAdministrativeGenderCode();
		Assert.assertEquals("Administrative Gender Code test case failed", administrativeGenderCode,
				patient.getAdministrativeGenderCode());
	}

	// TODO: Find out why this fails and either fix test and/or fix source
	@Ignore
	@Test
	public void testMaritalStatusCode() {
		setMaritalStatusCode();
		Assert.assertEquals("MaritalStatusCode test case failed", maritalStatusCode, patient.getMaritalStatusCode());
	}

	// TODO: Find out why this fails and either fix test and/or fix source
	@Ignore
	@Test
	public void testEthnicity() {
		setEthnicity();
		Assert.assertEquals("Ethnicity test case failed", ethnicity, patient.getEthnicity());
	}
	

	// TODO: Find out why this fails and either fix test and/or fix source
	@Ignore
	@Test
	public void testReligiousAffiliationCode() {
		setReligiousAffiliationCode();
		Assert.assertEquals("ReligiousAffiliationCode test case failed", religiousAffiliationCode,
				patient.getReligiousAffiliationCode());
	}

	// TODO: Find out why this fails and either fix test and/or fix source
	@Ignore
	@Test
	public void testBirthPlace() {
		setBirthPlace();
		Assert.assertEquals("BirthPlace test case failed", birthPlace, patient.getBirthPlace());
	}

}
