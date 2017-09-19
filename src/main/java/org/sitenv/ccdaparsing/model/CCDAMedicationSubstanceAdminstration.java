package org.sitenv.ccdaparsing.model;

public class CCDAMedicationSubstanceAdminstration extends CCDAXmlSnippet {
	
	private CCDAII templateId;
	private CCDACode medInstructions;
	private CCDADataElement referenceText;
	
	public CCDAII getTemplateId() {
		return templateId;
	}
	public void setTemplateId(CCDAII templateId) {
		this.templateId = templateId;
	}
	public CCDACode getMedInstructions() {
		return medInstructions;
	}
	public void setMedInstructions(CCDACode medInstructions) {
		this.medInstructions = medInstructions;
	}
	public CCDADataElement getReferenceText() {
		return referenceText;
	}
	public void setReferenceText(CCDADataElement referenceText) {
		this.referenceText = referenceText;
	}
}
