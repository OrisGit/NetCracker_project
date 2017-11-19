package Model.Entities;


import javax.persistence.*;

@Entity
@Table(name = "DRUGS_STORES", schema = "SYSTEM")
@IdClass(DrugStoreEntityPK.class)
public class DrugToStoreEntity {
    private long drugstoreId;
    private long drugId;

    @Id
    @Column(name = "DRUGSTORE_ID")
    public long getDrugstoreId() {
        return drugstoreId;
    }

    public void setDrugstoreId(long drugstoreId) {
        this.drugstoreId = drugstoreId;
    }

    @Id
    @Column(name = "DRUG_ID")
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

        DrugToStoreEntity that = (DrugToStoreEntity) o;

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
