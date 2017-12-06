package model.entities;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

public class PriceEntityPK implements Serializable{
    private DrugEntity drug;
    private DrugstoreEntity drugstore;

    public PriceEntityPK() {
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "DRUG_ID")
    public DrugEntity getDrug() {
        return drug;
    }

    public void setDrug(DrugEntity drug) {
        this.drug = drug;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "DRUGSTORE_ID")
    public DrugstoreEntity getDrugstore() {
        return drugstore;
    }

    public void setDrugstore(DrugstoreEntity drugstore) {
        this.drugstore = drugstore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PriceEntityPK that = (PriceEntityPK) o;

        if (!drug.equals(that.drug)) return false;
        return drugstore.equals(that.drugstore);
    }

    @Override
    public int hashCode() {
        int result = drug.hashCode();
        result = 31 * result + drugstore.hashCode();
        return result;
    }
}
