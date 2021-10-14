package org.sitenv.ccdaparsing.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@ComponentScan(basePackages = {"org.sitenv.ccdaparsing"})
@Configuration
@EnableAsync
public class CCDAParsingAPIConfiguration {
}
