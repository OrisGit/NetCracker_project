package Prameters;

/**
 * Параметры запроса которые мы получаем из View, каждое поле соответсвует одному
 * из возможных критериев те критерии которые не заданны устанавливаются как null
 * автоматически в ParameterBuilder. см. паттерн Builder
 * @see ParametersBuilder
 */
public class Parameters {

    private final String drugstoreName;
    private final String drugstoreAddressDistrict;
    private final String drugstoreAddressStreet;
    private final String drugstoreAddressBuilding;
    private final Boolean drugstoreIsRoundTheClock;
    private final String drugName;
    private final String drugManufacturer;
    private final String drugActiveIngredient;
    private final Long drugMaxCost;
    private final String drugPEffect;
    private final String drugTEffect;
    private final Long drugId;
    private final Long drugstoreId;

    public Parameters(String drugstoreName, String drugstoreAddressDistrict, String drugstoreAddressStreet, String drugstoreAddressBuilding, Boolean drugstoreIsRoundTheClock, String drugName, String drugManufacturer, String drugActiveIngredient, Long drugMaxCost, String drugPEffect, String drugTEffect, Long drugId, Long drugstoreId) {
        this.drugstoreName = drugstoreName;
        this.drugstoreAddressDistrict = drugstoreAddressDistrict;
        this.drugstoreAddressStreet = drugstoreAddressStreet;
        this.drugstoreAddressBuilding = drugstoreAddressBuilding;
        this.drugstoreIsRoundTheClock = drugstoreIsRoundTheClock;
        this.drugName = drugName;
        this.drugManufacturer = drugManufacturer;
        this.drugActiveIngredient = drugActiveIngredient;
        this.drugMaxCost = drugMaxCost;
        this.drugPEffect = drugPEffect;
        this.drugTEffect = drugTEffect;
        this.drugId = drugId;
        this.drugstoreId = drugstoreId;
    }

    public String getDrugstoreName() {
        return drugstoreName;
    }

    public String getDrugstoreAddressDistrict() {
        return drugstoreAddressDistrict;
    }

    public String getDrugstoreAddressStreet() {
        return drugstoreAddressStreet;
    }

    public String getDrugstoreAddressBuilding() {
        return drugstoreAddressBuilding;
    }

    public Boolean getDrugstoreIsRoundTheClock() {
        return drugstoreIsRoundTheClock;
    }

    public String getDrugName() {
        return drugName;
    }

    public String getDrugManufacturer() {
        return drugManufacturer;
    }

    public String getDrugActiveIngredient() {
        return drugActiveIngredient;
    }

    public Long getDrugMaxCost() {
        return drugMaxCost;
    }

    public String getDrugPEffect() {
        return drugPEffect;
    }

    public String getDrugTEffect() {
        return drugTEffect;
    }

    public Long getDrugId() {
        return drugId;
    }

    public Long getDrugstoreId() {
        return drugstoreId;
    }
}
