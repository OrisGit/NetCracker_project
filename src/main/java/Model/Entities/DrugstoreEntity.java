package Model.Entities;

import javax.persistence.*;

@Entity
@Table(name = "DRUGSTORES")
public class DrugstoreEntity {
    private Long id;
    private String name;
    private String district;
    private String street;
    private String building;
    private Long phone;
    private String workingHours;
    private Short isRoundTheClock;

    public DrugstoreEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DRUGSTORE_ID", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SNAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Basic
    @Column(name = "ADDRESS_DISTRICT")
    public String getDistrict() {
        return district;
    }


    public void setDistrict(String district) {
        this.district = district;
    }

    @Basic
    @Column(name = "ADDRESS_STREET")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "ADDRESS_BUILDING")
    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
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

    @Basic
    @Column(name = "IS_ROUND_THE_CLOCK")
    public Short getIsRoundTheClock() {
        return isRoundTheClock;
    }

    public void setIsRoundTheClock(Short isRoundTheClock) {
        this.isRoundTheClock = isRoundTheClock;
    }
}
