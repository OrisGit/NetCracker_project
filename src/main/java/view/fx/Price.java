package view.fx;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.entities.DrugEntity;
import model.entities.DrugstoreEntity;

public class Price {
    private final StringProperty drug;
    private final StringProperty drugstore;
    private final LongProperty cost;

    public Price(DrugEntity drug, DrugstoreEntity drugstore, long cost) {
        this.drug = new SimpleStringProperty(
                String.format("%s, %s, %s", drug.getName(), drug.getReleaseForm(), drug.getManufacturer()));
        this.drugstore = new SimpleStringProperty(
                String.format("%s, %s, %s %s", drugstore.getName(), drugstore.getDistrict(), drugstore.getStreet(), drugstore.getBuilding()));
        this.cost = new SimpleLongProperty(cost);
    }

    public String getDrug() {
        return drug.get();
    }

    public StringProperty drugProperty() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug.set(drug);
    }

    public String getDrugstore() {
        return drugstore.get();
    }

    public StringProperty drugstoreProperty() {
        return drugstore;
    }

    public void setDrugstore(String drugstore) {
        this.drugstore.set(drugstore);
    }

    public long getCost() {
        return cost.get();
    }

    public LongProperty costProperty() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost.set(cost);
    }
}
