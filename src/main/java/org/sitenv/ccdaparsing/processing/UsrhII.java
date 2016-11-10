package org.sitenv.ccdaparsing.processing;

import org.sitenv.ccdaparsing.model.CCDAII;
import org.sitenv.ccdaparsing.model.UsrhSubType;


public class UsrhII extends CCDAII {
	
	UsrhSubType usrhSubType;
	
	protected UsrhII(String rootValue, UsrhSubType usrhSubType) {
		super(rootValue);
		this.usrhSubType = usrhSubType;
	}
	
	protected UsrhSubType getUsrhSubType() {
		return usrhSubType;
	}
	
}
