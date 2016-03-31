package org.sitenv.ccdaparsing.processing;

import java.util.ArrayList;

import javax.xml.xpath.XPathExpressionException;

import org.sitenv.ccdaparsing.model.CCDAProcActProc;
import org.sitenv.ccdaparsing.model.CCDAProcedure;
import org.sitenv.ccdaparsing.model.CCDAUDI;

public class UDIProcessor {
	
	public static ArrayList<CCDAUDI> retrieveUDIDetails(CCDAProcedure procedures) throws XPathExpressionException
	{
		ArrayList<CCDAUDI> patientUDIList = null;
		
		if(procedures != null)
		{
			patientUDIList = new ArrayList<>();
			for ( CCDAProcActProc procedureActivity : procedures.getProcActsProcs())
			{
				if(procedureActivity.getPatientUDI() != null)
				{
					patientUDIList.addAll(procedureActivity.getPatientUDI());
				}
				
			}
		}
		
		return patientUDIList;
	}

}
