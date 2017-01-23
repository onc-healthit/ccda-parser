package org.sitenv.ccdaparsing.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.sitenv.ccdaparsing.service.CCDAParserAPI;

public class RunParser {

	public static String fileLocation = "C:/Projects/Drajer/mounika/mounika/Nullflavour/Scenario_1_Epic.xml";
	public static void main(String[] args)throws Exception {
		
		InputStream inputStream = new FileInputStream(new File(fileLocation));
		CCDAParserAPI.parseCCDA2_1(inputStream);
	}

}
