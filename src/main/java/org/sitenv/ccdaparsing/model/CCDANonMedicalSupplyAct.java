package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <h2>Non-Medicinal Supply Activity</h2>
 * <p>
 * This template records non-medicinal supplies provided, such as medical equipment.
 * </p>
 *
 * @author  Taral Vaghasia
 * @version 1.0
 * @since   2020-05-05
 */
public class CCDANonMedicalSupplyAct extends CCDAXmlSnippet {
    private List<CCDAII>        templateIds;
    private List<CCDAID>        ids;
    private CCDADataElement     referenceText;
    private CCDACode            supplyCode;
    private CCDACode            status;
    private CCDAEffTime         timeOfUse;

    public void setSectionTemplateId(List<CCDAII> templateIds) {
        this.templateIds = templateIds == null ? new ArrayList<CCDAII>() : new ArrayList<>(templateIds);
    }

    public void setReferenceText(CCDADataElement referenceText) {
        this.referenceText = referenceText;
    }

    public void setSupplyCode(CCDACode supplyCode) {
        this.supplyCode = supplyCode;
    }

    public void setStatus(CCDACode status) {
        this.status = status;
    }
    public void setTimeOfUse(CCDAEffTime timeOfUse) {
        this.timeOfUse = timeOfUse;
    }

    public List<CCDAII> getTemplateIds() {
        return templateIds == null ? new ArrayList<CCDAII>() : new ArrayList<>(templateIds);
    }

    public List<CCDAID> getIds() {
        return ids == null ? new ArrayList<CCDAID>() : new ArrayList<>(ids);
    }

    public CCDADataElement getReferenceText() {
        return referenceText;
    }

    public CCDACode getSupplyCode() {
        return supplyCode;
    }

    public CCDACode getStatus() {
        return status;
    }

    public CCDAEffTime getTimeOfUse() {
        return timeOfUse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CCDANonMedicalSupplyAct)) return false;
        if (!super.equals(o)) return false;
        CCDANonMedicalSupplyAct that = (CCDANonMedicalSupplyAct) o;
        return Objects.equals(templateIds, that.templateIds) &&
                Objects.equals(ids, that.ids) &&
                Objects.equals(referenceText, that.referenceText) &&
                Objects.equals(supplyCode, that.supplyCode) &&
                Objects.equals(status, that.status) &&
                Objects.equals(timeOfUse, that.timeOfUse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), templateIds, ids, referenceText, supplyCode, status, timeOfUse);
    }
}
