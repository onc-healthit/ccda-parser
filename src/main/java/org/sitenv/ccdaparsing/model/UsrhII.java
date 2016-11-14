package org.sitenv.ccdaparsing.model;

import org.sitenv.ccdaparsing.model.CCDAII;
import org.sitenv.ccdaparsing.model.UsrhSubType;


public class UsrhII extends CCDAII {
	
	UsrhSubType usrhSubType;
	
	public UsrhII(String rootValue, UsrhSubType usrhSubType) {
		super(rootValue);
		this.usrhSubType = usrhSubType;
	}
	
	public UsrhSubType getUsrhSubType() {
		return usrhSubType;
	}
	
}
