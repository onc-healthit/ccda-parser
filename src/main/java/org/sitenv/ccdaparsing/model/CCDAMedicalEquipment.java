package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Medical Equipment Section</h2>
 * <p>
 * The Medical Equipment section defines a patient's implanted and external medical devices and equipment that their health status depends on,
 * as well as any pertinent equipment or device history. This section is also used to itemize any pertinent current or historical durable
 * medical equipment (DME) used to help maintain the patient's health status.
 * All pertinent equipment relevant to the diagnosis, care, and treatment of a patient should be included.
 * </p>
 *
 * @author  Taral Vaghasia
 * @version 1.0
 * @since   2020-05-05
 */
public class CCDAMedicalEquipment extends CCDAXmlSnippet {
    private boolean                             isSectionNullFlavourWithNI;
    private ArrayList<CCDAII>                   templateIds;
    private CCDACode                            sectionCode;
    private List<CCDAID>                        ids;
    private List<CCDAMedicalEquipmentOrg>       equipmentOrgs;
    private List<CCDANonMedicalSupplyAct>       supplyActivities;
    private List<CCDAProcActProc>               procedureActs;

    public List<CCDAID> getIds() {
        return ids == null ? new ArrayList<CCDAID>() : new ArrayList<>(ids);
    }

    public void setSectionNullFlavourWithNI(boolean isSectionNullFlavourWithNI) {
        this.isSectionNullFlavourWithNI = isSectionNullFlavourWithNI;
    }

    public void setSectionTemplateId(ArrayList<CCDAII> templateIds) {
        this.templateIds = templateIds == null ? new ArrayList<CCDAII>() : new ArrayList<>(templateIds);
    }

    public void setSectionCode(CCDACode sectionCode) {
        this.sectionCode = sectionCode;
    }

    public void setIds(List<CCDAID> ids) {
        this.ids = ids == null ? new ArrayList<CCDAID>() : new ArrayList<>(ids);
    }

    public void setProcActsProcs(ArrayList<CCDAProcActProc> procedureActs) {
        this.procedureActs = procedureActs == null ? new ArrayList<CCDAProcActProc>() : new ArrayList<>(procedureActs);
    }

    public void setSupplyActivities(List<CCDANonMedicalSupplyAct> supplyActivities) {
        this.supplyActivities = supplyActivities == null ? new ArrayList<CCDANonMedicalSupplyAct>() : new ArrayList<>(supplyActivities);
    }

    public void setOrganizers(List<CCDAMedicalEquipmentOrg> equipmentOrgs) {
        this.equipmentOrgs = equipmentOrgs == null ? new ArrayList<CCDAMedicalEquipmentOrg>() : new ArrayList<>(equipmentOrgs);
    }

    public boolean isSectionNullFlavourWithNI() {
        return isSectionNullFlavourWithNI;
    }

    public ArrayList<CCDAII> getTemplateIds() {
        return templateIds == null ? new ArrayList<CCDAII>() : new ArrayList<>(templateIds);
    }

    public CCDACode getSectionCode() {
        return sectionCode;
    }

    public List<CCDAMedicalEquipmentOrg> getEquipmentOrgs() {
        return equipmentOrgs == null ? new ArrayList<CCDAMedicalEquipmentOrg>() : new ArrayList<>(equipmentOrgs);
    }

    public List<CCDANonMedicalSupplyAct> getSupplyActivities() {
        return supplyActivities == null ? new ArrayList<CCDANonMedicalSupplyAct>() : new ArrayList<>(supplyActivities);
    }

    public List<CCDAProcActProc> getProcedureActs() {
        return procedureActs == null ? new ArrayList<CCDAProcActProc>() : new ArrayList<>(procedureActs);
    }
}
