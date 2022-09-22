package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;


public class CCDACarePlanSections {
	
	
	private static String INTERVENTIONS_SECTION_V3 = "Interventions Section (V3) 2.16.840.1.113883.10.20.21.2.3:2015-08-01";
	private static String HEALTH_STATUS_EVALUATIONS_AND_OUTCOMES_SECTION = 
			"Health Status Evaluations and Outcomes Section 2.16.840.1.113883.10.20.22.2.61";
	public static String REQUIRED_SECTIONS = INTERVENTIONS_SECTION_V3 + " and " + HEALTH_STATUS_EVALUATIONS_AND_OUTCOMES_SECTION;
	
	private ArrayList<CCDAII> templateIds;
	private boolean interventionsSectionV3;
	private boolean healthStatusEvaluationsAndOutcomesSection; 
	
	private CCDAAuthor	author;
	
	public CCDACarePlanSections() {
		templateIds = new ArrayList<CCDAII>();
	}
	
	public CCDACarePlanSections(CCDAII ...templateIds) {
		this();
		for(CCDAII curII : templateIds) {
			this.templateIds.add(curII);
		}
	}
	
	

	public ArrayList<CCDAII> getTemplateIds() {
		return templateIds;
	}
	
	public void setInterventionsSectionV3(boolean interventionsSectionV3) {
		this.interventionsSectionV3 = interventionsSectionV3;
	}
	
	public void setHealthStatusEvaluationsAndOutcomesSection(boolean healthStatusEvaluationsAndOutcomesSection) {
		this.healthStatusEvaluationsAndOutcomesSection = healthStatusEvaluationsAndOutcomesSection;
	}

	public void setTemplateIds(ArrayList<CCDAII> templateIds) {		
		if(templateIds != null) {
			this.templateIds = templateIds;
		}
	}

	public CCDAAuthor getAuthor() {
		return author;
	}

	public void setAuthor(CCDAAuthor author) {
		this.author = author;
	}

	
}
