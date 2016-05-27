package org.sitenv.ccdaparsing.model;

import org.sitenv.ccdaparsing.model.CCDACode;
import org.sitenv.ccdaparsing.model.CCDADataElement;

public class CCDAPL extends CCDAXmlSnippet{
	
	private CCDACode languageCode ;
	private CCDACode modeCode;
	private CCDADataElement preferenceInd;
	
	public CCDACode getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(CCDACode languageCode) {
		this.languageCode = languageCode;
	}
	public CCDACode getModeCode() {
		return modeCode;
	}
	public void setModeCode(CCDACode modeCode) {
		this.modeCode = modeCode;
	}
	public CCDADataElement getPreferenceInd() {
		return preferenceInd;
	}
	public void setPreferenceInd(CCDADataElement preferenceInd) {
		this.preferenceInd = preferenceInd;
	}
	
	@Override
	public boolean equals(Object obj) {

		if (obj == this) { 
			return true; 
		} 
		if (obj == null || obj.getClass() != this.getClass()) { 
			return false; 
		} 
		CCDAPL obj2 = (CCDAPL) obj; 
		return  (this.languageCode == obj2.getLanguageCode() || (this.languageCode != null && this.languageCode.equals(obj2.getLanguageCode()))) && 
				(this.modeCode == obj2.getModeCode() || (this.modeCode != null && this.modeCode .equals(obj2.getModeCode())))&&				
				(this.preferenceInd == obj2.getPreferenceInd() || (this.preferenceInd != null && this.preferenceInd .equals(obj2.getPreferenceInd())));
		
	}
	
}
