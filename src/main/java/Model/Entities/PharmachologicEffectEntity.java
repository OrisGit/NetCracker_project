package Model.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PHARMACHOLOGIC_EFFECT")
public class PharmachologicEffectEntity {
    private Long id;
    private String name;
    private String description;

    private Set<DrugEntity> drugs = new HashSet<DrugEntity>();

    public PharmachologicEffectEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "P_EFFECT_ID", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PNAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "PDESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "pharmachologicEffect")
    public Set<DrugEntity> getDrugs() {
        return drugs;
    }

    public void setDrugs(Set<DrugEntity> drugs) {
        this.drugs = drugs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PharmachologicEffectEntity that = (PharmachologicEffectEntity) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return drugs != null ? drugs.equals(that.drugs) : that.drugs == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (drugs != null ? drugs.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PharmachologicEffectEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", drugs=" + drugs +
                '}';
    }
}
