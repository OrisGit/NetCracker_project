package Prameters;

public class ParametersBuilder {
    private String drugstoreName = null;
    private String drugstoreAddressDistrict = null;
    private String drugstoreAddressStreet = null;
    private String drugstoreAddressBuilding = null;
    private Boolean drugstoreIsRoundTheClock = null;
    private String drugName = null;
    private String drugManufacturer = null;
    private String drugActiveIngredient = null;
    private Long drugMaxCost = null;
    private String drugPEffect = null;
    private String drugTEffect = null;
    private Long drugId = null;
    private Long drugstoreId = null;

    public ParametersBuilder setDrugstoreName(String drugstoreName) {
        this.drugstoreName = drugstoreName;
        return this;
    }

    public ParametersBuilder setDrugstoreAddressDistrict(String drugstoreAddressDistrict) {
        this.drugstoreAddressDistrict = drugstoreAddressDistrict;
        return this;
    }

    public ParametersBuilder setDrugstoreAddressStreet(String drugstoreAddressStreet) {
        this.drugstoreAddressStreet = drugstoreAddressStreet;
        return this;
    }

    public ParametersBuilder setDrugstoreAddressBuilding(String drugstoreAddressBuilding) {
        this.drugstoreAddressBuilding = drugstoreAddressBuilding;
        return this;
    }

    public ParametersBuilder setDrugstoreIsRoundTheClock(Boolean drugstoreIsRoundTheClock) {
        this.drugstoreIsRoundTheClock = drugstoreIsRoundTheClock;
        return this;
    }

    public ParametersBuilder setDrugName(String drugName) {
        this.drugName = drugName;
        return this;
    }

    public ParametersBuilder setDrugManufacturer(String drugManufacturer) {
        this.drugManufacturer = drugManufacturer;
        return this;
    }

    public ParametersBuilder setDrugActiveIngredient(String drugActiveIngredient) {
        this.drugActiveIngredient = drugActiveIngredient;
        return this;
    }

    public ParametersBuilder setDrugMaxCost(Long drugMaxCost) {
        this.drugMaxCost = drugMaxCost;
        return this;
    }

    public ParametersBuilder setDrugPEffect(String drugPEffect) {
        this.drugPEffect = drugPEffect;
        return this;
    }

    public ParametersBuilder setDrugTEffect(String drugTEffect) {
        this.drugTEffect = drugTEffect;
        return this;
    }

    public ParametersBuilder setDrugId(Long drugId) {
        this.drugId = drugId;
        return this;
    }

    public ParametersBuilder setDrugstoreId(Long drugstoreId) {
        this.drugstoreId = drugstoreId;
        return this;
    }

    public Parameters createParameters() {
        return new Parameters(drugstoreName, drugstoreAddressDistrict, drugstoreAddressStreet, drugstoreAddressBuilding, drugstoreIsRoundTheClock, drugName, drugManufacturer, drugActiveIngredient, drugMaxCost, drugPEffect, drugTEffect, drugId, drugstoreId);
    }
}