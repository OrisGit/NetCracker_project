package Client;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DrugFX {
    private final StringProperty name;
    private final StringProperty releaseForm;
    private final StringProperty manufacturer;
    private final StringProperty activeIngredient;
    private final StringProperty pharmacologicalEffect;
    private final StringProperty therapeuticEffect;
    private final StringProperty description;

    public DrugFX(String name, String releaseForm, String manufacturer, String activeIngredient,
                  String pharmacologicalEffect, String therapeuticEffect, String description) {
        this.name = new SimpleStringProperty(name);
        this.releaseForm = new SimpleStringProperty(releaseForm);
        this.manufacturer = new SimpleStringProperty(manufacturer);
        this.activeIngredient = new SimpleStringProperty(activeIngredient);
        this.pharmacologicalEffect = new SimpleStringProperty(pharmacologicalEffect);
        this.therapeuticEffect = new SimpleStringProperty(therapeuticEffect);
        this.description = new SimpleStringProperty(description);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getReleaseForm() {
        return releaseForm.get();
    }

    public StringProperty releaseFormProperty() {
        return releaseForm;
    }

    public String getManufacturer() {
        return manufacturer.get();
    }

    public StringProperty manufacturerProperty() {
        return manufacturer;
    }

    public String getActiveIngredient() {
        return activeIngredient.get();
    }

    public StringProperty activeIngredientProperty() {
        return activeIngredient;
    }

    public String getPharmacologicalEffect() {
        return pharmacologicalEffect.get();
    }

    public StringProperty pharmacologicalEffectProperty() {
        return pharmacologicalEffect;
    }

    public String getTherapeuticEffect() {
        return therapeuticEffect.get();
    }

    public StringProperty therapeuticEffectProperty() {
        return therapeuticEffect;
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }
}
