package Prameters;

import java.math.BigDecimal;

public class ParametersBuilder {
    private String drugstore_name = null;
    private String drugstore_address_district = null;
    private String drugstore_address_street = null;
    private String drugstore_address_building = null;
    private Boolean drugstore_is_round_the_clock = null;
    private String drug_name = null;
    private String drug_manufacturer = null;
    private String drug_active_ingredient = null;
    private BigDecimal drug_max_cost = null;
    private String drug_p_effect = null;
    private String drug_t_effect = null;
    private Long drug_id = null;
    private Long drugstore_id = null;

    public ParametersBuilder setDrugstore_name(String drugstore_name) {
        this.drugstore_name = drugstore_name;
        return this;
    }

    public ParametersBuilder setDrugstore_address_district(String drugstore_address_district) {
        this.drugstore_address_district = drugstore_address_district;
        return this;
    }

    public ParametersBuilder setDrugstore_address_street(String drugstore_address_street) {
        this.drugstore_address_street = drugstore_address_street;
        return this;
    }

    public ParametersBuilder setDrugstore_address_building(String drugstore_address_building) {
        this.drugstore_address_building = drugstore_address_building;
        return this;
    }

    public ParametersBuilder setDrugstore_is_round_the_clock(Boolean drugstore_is_round_the_clock) {
        this.drugstore_is_round_the_clock = drugstore_is_round_the_clock;
        return this;
    }

    public ParametersBuilder setDrug_name(String drug_name) {
        this.drug_name = drug_name;
        return this;
    }

    public ParametersBuilder setDrug_manufacturer(String drug_manufacturer) {
        this.drug_manufacturer = drug_manufacturer;
        return this;
    }

    public ParametersBuilder setDrug_active_ingredient(String drug_active_ingredient) {
        this.drug_active_ingredient = drug_active_ingredient;
        return this;
    }

    public ParametersBuilder setDrug_max_cost(BigDecimal drug_max_cost) {
        this.drug_max_cost = drug_max_cost;
        return this;
    }

    public ParametersBuilder setDrug_p_effect(String drug_p_effect) {
        this.drug_p_effect = drug_p_effect;
        return this;
    }

    public ParametersBuilder setDrug_t_effect(String drug_t_effect) {
        this.drug_t_effect = drug_t_effect;
        return this;
    }

    public ParametersBuilder setDrug_id(Long drug_id) {
        this.drug_id = drug_id;
        return this;
    }

    public ParametersBuilder setDrugstore_id(Long drugstore_id) {
        this.drugstore_id = drugstore_id;
        return this;
    }

    public Parameters createParameters() {
        return new Parameters(drugstore_name, drugstore_address_district, drugstore_address_street, drugstore_address_building, drugstore_is_round_the_clock, drug_name, drug_manufacturer, drug_active_ingredient, drug_max_cost, drug_p_effect, drug_t_effect, drug_id, drugstore_id);
    }
}