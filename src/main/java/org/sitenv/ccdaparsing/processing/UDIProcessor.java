package org.sitenv.ccdaparsing.processing;

import java.util.ArrayList;

import javax.xml.xpath.XPathExpressionException;

import org.sitenv.ccdaparsing.model.CCDAProcActProc;
import org.sitenv.ccdaparsing.model.CCDAProcedure;
import org.sitenv.ccdaparsing.model.CCDAUDI;
import org.springframework.stereotype.Service;

@Service
public class UDIProcessor {
	
	public ArrayList<CCDAUDI> retrieveUDIDetails(CCDAProcedure procedures) throws XPathExpressionException
	{
		ArrayList<CCDAUDI> patientUDIList = null;
		
		if(procedures != null && procedures.getProcActsProcs()!= null)
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
