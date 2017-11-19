package Model.Entities;

import javax.persistence.*;

@Entity
@Table(name = "DRUGS", schema = "SYSTEM")
public class DrugEntity {
    private long drugId;
    private String dname;
    private String releaseform;
    private String manufacturer;
    private String activeingridient;
    private String description;

    @Id
    @Column(name = "DRUG_ID")
    public long getDrugId() {
        return drugId;
    }

    public void setDrugId(long drugId) {
        this.drugId = drugId;
    }

    @Basic
    @Column(name = "DNAME")
    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Basic
    @Column(name = "RELEASEFORM")
    public String getReleaseform() {
        return releaseform;
    }

    public void setReleaseform(String releaseform) {
        this.releaseform = releaseform;
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
    @Column(name = "ACTIVEINGRIDIENT")
    public String getActiveingridient() {
        return activeingridient;
    }

    public void setActiveingridient(String activeingridient) {
        this.activeingridient = activeingridient;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DrugEntity that = (DrugEntity) o;

        if (drugId != that.drugId) return false;
        if (dname != null ? !dname.equals(that.dname) : that.dname != null) return false;
        if (releaseform != null ? !releaseform.equals(that.releaseform) : that.releaseform != null) return false;
        if (manufacturer != null ? !manufacturer.equals(that.manufacturer) : that.manufacturer != null) return false;
        if (activeingridient != null ? !activeingridient.equals(that.activeingridient) : that.activeingridient != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (drugId ^ (drugId >>> 32));
        result = 31 * result + (dname != null ? dname.hashCode() : 0);
        result = 31 * result + (releaseform != null ? releaseform.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (activeingridient != null ? activeingridient.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DrugEntity{" +
                "drugId=" + drugId +
                ", dname='" + dname + '\'' +
                ", releaseform='" + releaseform + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", activeingridient='" + activeingridient + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
