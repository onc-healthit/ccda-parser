package org.sitenv.ccdaparsing.model;

public class CCDAAuthor extends CCDAXmlSnippet{

	private CCDAII templateId;
	private CCDAEffTime time;

	public CCDAII getTemplateId() {
		return templateId;
	}

	public void setTemplateId(CCDAII templateId) {
		this.templateId = templateId;
	}

	public CCDAEffTime getTime() {
		return time;
	}

	public void setTime(CCDAEffTime time) {
		this.time = time;
	}
}
