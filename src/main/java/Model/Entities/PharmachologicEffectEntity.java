package Model.Entities;

import javax.persistence.*;

@Entity
@Table(name = "PHARMACHOLOGIC_EFFECT", schema = "SYSTEM")
public class PharmachologicEffectEntity {
    private long pEffectId;
    private String pname;
    private String pdescription;

    @Id
    @Column(name = "P_EFFECT_ID")
    public long getpEffectId() {
        return pEffectId;
    }

    public void setpEffectId(long pEffectId) {
        this.pEffectId = pEffectId;
    }

    @Basic
    @Column(name = "PNAME")
    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Basic
    @Column(name = "PDESCRIPTION")
    public String getPdescription() {
        return pdescription;
    }

    public void setPdescription(String pdescription) {
        this.pdescription = pdescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PharmachologicEffectEntity that = (PharmachologicEffectEntity) o;

        if (pEffectId != that.pEffectId) return false;
        if (pname != null ? !pname.equals(that.pname) : that.pname != null) return false;
        if (pdescription != null ? !pdescription.equals(that.pdescription) : that.pdescription != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (pEffectId ^ (pEffectId >>> 32));
        result = 31 * result + (pname != null ? pname.hashCode() : 0);
        result = 31 * result + (pdescription != null ? pdescription.hashCode() : 0);
        return result;
    }
}
