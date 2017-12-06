package model.entities;

import javax.persistence.*;

@Entity
@Table(name = "DRUGS_STORES")
@IdClass(PriceEntityPK.class)
public class PriceEntity {
    private DrugEntity drug;
    private DrugstoreEntity drugstore;
    private Long cost;

    public PriceEntity() {
    }

    public PriceEntity(DrugEntity drug, DrugstoreEntity drugstore, Long cost) {
        this.drug = drug;
        this.drugstore = drugstore;
        this.cost = cost;
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

    @Basic
    @Column(name = "COST")
    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PriceEntity that = (PriceEntity) o;

        if (!drug.equals(that.drug)) return false;
        if (!drugstore.equals(that.drugstore)) return false;
        return cost != null ? cost.equals(that.cost) : that.cost == null;
    }

    @Override
    public int hashCode() {
        int result = drug.hashCode();
        result = 31 * result + drugstore.hashCode();
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PriceEntity{" +
                "drug=" + drug +
                ", drugstore=" + drugstore +
                ", cost=" + cost +
                '}';
    }
}
