package org.sitenv.ccdaparsing.model;


public class CCDAEncompassingEncounter extends CCDAXmlSnippet {

	private CCDAII id;
	private CCDACode code;
	private CCDAEffTime effectiveTime;

	public CCDAII getId() {
		return id;
	}

	public void setId(CCDAII id) {
		this.id = id;
	}

	public CCDACode getCode() {
		return code;
	}

	public void setCode(CCDACode code) {
		this.code = code;
	}

	public CCDAEffTime getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(CCDAEffTime effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

}
