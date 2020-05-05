package org.sitenv.ccdaparsing.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.sitenv.ccdaparsing.configuration.CCDAParsingAPIConfiguration;
import org.sitenv.ccdaparsing.model.CCDARefModel;
import org.sitenv.ccdaparsing.service.CCDAParserAPI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RunParser {

	public static String fileLocation = "C:/Projects/Drajer/mounika/mounika/Nullflavour/Scenario_1_Epic.xml";

	public static void main(String[] args)throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(CCDAParsingAPIConfiguration.class);
		CCDAParserAPI cCDAParserAPI = context.getBean(CCDAParserAPI.class);

		InputStream inputStream = new FileInputStream(new File(fileLocation));
		CCDARefModel refModel = cCDAParserAPI.parseCCDA2_1(inputStream);

		System.out.println(refModel);
	}

}
