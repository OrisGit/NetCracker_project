package Model.Entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class DrugStoreEntityPK implements Serializable {
    private long drugstoreId;
    private long drugId;

    @Column(name = "DRUGSTORE_ID")
    @Id
    public long getDrugstoreId() {
        return drugstoreId;
    }

    public void setDrugstoreId(long drugstoreId) {
        this.drugstoreId = drugstoreId;
    }

    @Column(name = "DRUG_ID")
    @Id
    public long getDrugId() {
        return drugId;
    }

    public void setDrugId(long drugId) {
        this.drugId = drugId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DrugStoreEntityPK that = (DrugStoreEntityPK) o;

        if (drugstoreId != that.drugstoreId) return false;
        if (drugId != that.drugId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (drugstoreId ^ (drugstoreId >>> 32));
        result = 31 * result + (int) (drugId ^ (drugId >>> 32));
        return result;
    }
}
