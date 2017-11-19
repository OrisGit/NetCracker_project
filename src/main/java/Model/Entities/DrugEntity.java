package Model.Entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "DRUGS")
public class DrugEntity {

    private Long id;
    private String name;
    private String releaseForm;
    private String manufacturer;
    private String activeIngredient;
    private PharmachologicEffectEntity pharmachologicEffect;
    private TherapeuticEffectEntity therapeuticEffect;
    private String description;

    private Set<PriceEntity> prices = new HashSet<>();

    public DrugEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DRUG_ID", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DNAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "RELEASEFORM")
    public String getReleaseForm() {
        return releaseForm;
    }

    public void setReleaseForm(String releaseForm) {
        this.releaseForm = releaseForm;
    }

    @Basic
    @Column(name = "MANUFACTURER")
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }


    @Basic
    @Column(name = "ACTIVEINGRIDIENT", nullable = false)
    public String getActiveIngredient() {
        return activeIngredient;
    }

    public void setActiveIngredient(String activeIngredient) {
        this.activeIngredient = activeIngredient;
    }

    @ManyToOne
    @JoinColumn(name = "P_EFFECT_ID")
    public PharmachologicEffectEntity getPharmachologicEffect() {
        return pharmachologicEffect;
    }

    public void setPharmachologicEffect(PharmachologicEffectEntity pharmachologicEffect) {
        this.pharmachologicEffect = pharmachologicEffect;
    }

    @ManyToOne()
    @JoinColumn(name = "T_EFFECT_ID")
    public TherapeuticEffectEntity getTherapeuticEffect() {
        return therapeuticEffect;
    }

    public void setTherapeuticEffect(TherapeuticEffectEntity therapeuticEffect) {
        this.therapeuticEffect = therapeuticEffect;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "drug")
    public Set<PriceEntity> getPrices() {
        return prices;
    }

    public void setPrices(Set<PriceEntity> prices) {
        this.prices = prices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DrugEntity that = (DrugEntity) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (releaseForm != null ? !releaseForm.equals(that.releaseForm) : that.releaseForm != null) return false;
        if (manufacturer != null ? !manufacturer.equals(that.manufacturer) : that.manufacturer != null) return false;
        if (!activeIngredient.equals(that.activeIngredient)) return false;
        if (pharmachologicEffect != null ? !pharmachologicEffect.equals(that.pharmachologicEffect) : that.pharmachologicEffect != null)
            return false;
        if (therapeuticEffect != null ? !therapeuticEffect.equals(that.therapeuticEffect) : that.therapeuticEffect != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return prices != null ? prices.equals(that.prices) : that.prices == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (releaseForm != null ? releaseForm.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + activeIngredient.hashCode();
        result = 31 * result + (pharmachologicEffect != null ? pharmachologicEffect.hashCode() : 0);
        result = 31 * result + (therapeuticEffect != null ? therapeuticEffect.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (prices != null ? prices.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DrugEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseForm='" + releaseForm + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", activeIngredient='" + activeIngredient + '\'' +
                ", pharmachologicEffect=" + pharmachologicEffect +
                ", therapeuticEffect=" + therapeuticEffect +
                ", description='" + description + '\'' +
                ", drugstores=" + prices +
                '}';
    }
}
