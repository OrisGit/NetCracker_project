package Model.Entities;

import javax.persistence.*;

@Entity
@Table(name = "THERAPEUTIC_EFFECT", schema = "SYSTEM")
public class TherapeuticEffectEntity {
    private long tEffectId;
    private String tname;
    private String tdescription;

    @Id
    @Column(name = "T_EFFECT_ID")
    public long gettEffectId() {
        return tEffectId;
    }

    public void settEffectId(long tEffectId) {
        this.tEffectId = tEffectId;
    }

    @Basic
    @Column(name = "TNAME")
    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    @Basic
    @Column(name = "TDESCRIPTION")
    public String getTdescription() {
        return tdescription;
    }

    public void setTdescription(String tdescription) {
        this.tdescription = tdescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TherapeuticEffectEntity that = (TherapeuticEffectEntity) o;

        if (tEffectId != that.tEffectId) return false;
        if (tname != null ? !tname.equals(that.tname) : that.tname != null) return false;
        if (tdescription != null ? !tdescription.equals(that.tdescription) : that.tdescription != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (tEffectId ^ (tEffectId >>> 32));
        result = 31 * result + (tname != null ? tname.hashCode() : 0);
        result = 31 * result + (tdescription != null ? tdescription.hashCode() : 0);
        return result;
    }
}
