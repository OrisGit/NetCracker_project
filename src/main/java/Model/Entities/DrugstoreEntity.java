package Model.Entities;

import javax.persistence.*;

@Entity
@Table(name = "DRUGSTORES", schema = "SYSTEM")
public class DrugstoreEntity {
    private long drugstoreId;
    private String tname;
    private String addressDistrict;
    private String addressStreet;
    private String addressBuilding;
    private Long phone;
    private String workingHours;

    @Id
    @Column(name = "DRUGSTORE_ID")
    public long getDrugstoreId() {
        return drugstoreId;
    }

    public void setDrugstoreId(long drugstoreId) {
        this.drugstoreId = drugstoreId;
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
    @Column(name = "ADDRESS_DISTRICT")
    public String getAddressDistrict() {
        return addressDistrict;
    }

    public void setAddressDistrict(String addressDistrict) {
        this.addressDistrict = addressDistrict;
    }

    @Basic
    @Column(name = "ADDRESS_STREET")
    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    @Basic
    @Column(name = "ADDRESS_BUILDING")
    public String getAddressBuilding() {
        return addressBuilding;
    }

    public void setAddressBuilding(String addressBuilding) {
        this.addressBuilding = addressBuilding;
    }

    @Basic
    @Column(name = "PHONE")
    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "WORKING_HOURS")
    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DrugstoreEntity that = (DrugstoreEntity) o;

        if (drugstoreId != that.drugstoreId) return false;
        if (tname != null ? !tname.equals(that.tname) : that.tname != null) return false;
        if (addressDistrict != null ? !addressDistrict.equals(that.addressDistrict) : that.addressDistrict != null)
            return false;
        if (addressStreet != null ? !addressStreet.equals(that.addressStreet) : that.addressStreet != null)
            return false;
        if (addressBuilding != null ? !addressBuilding.equals(that.addressBuilding) : that.addressBuilding != null)
            return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (workingHours != null ? !workingHours.equals(that.workingHours) : that.workingHours != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (drugstoreId ^ (drugstoreId >>> 32));
        result = 31 * result + (tname != null ? tname.hashCode() : 0);
        result = 31 * result + (addressDistrict != null ? addressDistrict.hashCode() : 0);
        result = 31 * result + (addressStreet != null ? addressStreet.hashCode() : 0);
        result = 31 * result + (addressBuilding != null ? addressBuilding.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (workingHours != null ? workingHours.hashCode() : 0);
        return result;
    }
}
