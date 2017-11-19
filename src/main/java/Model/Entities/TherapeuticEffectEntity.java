package Model.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "THERAPEUTIC_EFFECT")
public class TherapeuticEffectEntity {
    private Long id;
    private String name;
    private String description;

    private Set<DrugEntity> drugs = new HashSet<>();

    public TherapeuticEffectEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "T_EFFECT_ID", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TNAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "TDESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "therapeuticEffect")
    public Set<DrugEntity> getDrugs() {
        return drugs;
    }

    public void setDrugs(Set<DrugEntity> drugs) {
        this.drugs = drugs;
    }
}
