package Prameters;

import java.math.BigDecimal;

/**
 * Параметры запроса которые мы получаем из View, каждое поле соответсвует одному
 * из возможных критериев те критерии которые не заданны устанавливаются как null
 * автоматически в ParameterBuilder. см. паттерн Builder
 * @see ParametersBuilder
 */
public class Parameters {

    private final String drugstore_name;
    private final String drugstore_address_district;
    private final String drugstore_address_street;
    private final String drugstore_address_building;
    private final Boolean drugstore_is_round_the_clock;
    private final String drug_name;
    private final String drug_manufacturer;
    private final String drug_active_ingredient;
    private final BigDecimal drug_max_cost;
    private final String drug_p_effect;
    private final String drug_t_effect;
    private final Long drug_id;
    private final Long drugstore_id;

    public Parameters(String drugstore_name, String drugstore_address_district, String drugstore_address_street, String drugstore_address_building, Boolean drugstore_is_round_the_clock, String drug_name, String drug_manufacturer, String drug_active_ingredient, BigDecimal drug_max_cost, String drug_p_effect, String drug_t_effect, Long drug_id, Long drugstore_id) {
        this.drugstore_name = drugstore_name;
        this.drugstore_address_district = drugstore_address_district;
        this.drugstore_address_street = drugstore_address_street;
        this.drugstore_address_building = drugstore_address_building;
        this.drugstore_is_round_the_clock = drugstore_is_round_the_clock;
        this.drug_name = drug_name;
        this.drug_manufacturer = drug_manufacturer;
        this.drug_active_ingredient = drug_active_ingredient;
        this.drug_max_cost = drug_max_cost;
        this.drug_p_effect = drug_p_effect;
        this.drug_t_effect = drug_t_effect;
        this.drug_id = drug_id;
        this.drugstore_id = drugstore_id;
    }

    public String getDrugstore_name() {
        return drugstore_name;
    }

    public String getDrugstore_address_district() {
        return drugstore_address_district;
    }

    public String getDrugstore_address_street() {
        return drugstore_address_street;
    }

    public String getDrugstore_address_building() {
        return drugstore_address_building;
    }

    public Boolean getDrugstore_is_round_the_clock() {
        return drugstore_is_round_the_clock;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public String getDrug_manufacturer() {
        return drug_manufacturer;
    }

    public String getDrug_active_ingredient() {
        return drug_active_ingredient;
    }

    public BigDecimal getDrug_max_cost() {
        return drug_max_cost;
    }

    public String getDrug_p_effect() {
        return drug_p_effect;
    }

    public String getDrug_t_effect() {
        return drug_t_effect;
    }

    public Long getDrug_id() {
        return drug_id;
    }

    public Long getDrugstore_id() {
        return drugstore_id;
    }
}
