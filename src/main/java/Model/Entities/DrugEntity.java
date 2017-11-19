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

    private Set<DrugstoreEntity> drugstores = new HashSet<>();

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

    @OneToMany(mappedBy = "")
    public Set<DrugstoreEntity> getDrugstores() {
        return drugstores;
    }

    public void setDrugstores(Set<DrugstoreEntity> drugstores) {
        this.drugstores = drugstores;
    }
}
