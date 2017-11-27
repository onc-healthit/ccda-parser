package org.sitenv.ccdaparsing.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.sitenv.ccdaparsing.service.CCDAParserAPI;

public class RunParser {

	public String fileLocation = "C:/Projects/Drajer/mounika/mounika/Nullflavour/Scenario_1_Epic.xml";
	private CCDAParserAPI cCDAParserAPI = new CCDAParserAPI();
	public void main(String[] args)throws Exception {
		
		InputStream inputStream = new FileInputStream(new File(fileLocation));
		cCDAParserAPI.parseCCDA2_1(inputStream);
	}

}
