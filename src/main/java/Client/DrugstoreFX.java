package Client;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DrugstoreFX {
    private final StringProperty name;
    private final StringProperty district;
    private final StringProperty address;
    private final StringProperty phone;
    private final StringProperty workingHours;
    private final BooleanProperty isRoundTheClock;

    public DrugstoreFX(String name, String district, String street, String building, String phone,
                       String workingHours, Boolean isRoundTheClock) {
        this.name = new SimpleStringProperty(name);
        this.district = new SimpleStringProperty(district);
        this.address = new SimpleStringProperty(street + ", " + building);
        this.phone = new SimpleStringProperty(phone);
        this.workingHours = new SimpleStringProperty(workingHours);
        this.isRoundTheClock = new SimpleBooleanProperty(isRoundTheClock);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getDistrict() {
        return district.get();
    }

    public StringProperty districtProperty() {
        return district;
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public String getWorkingHours() {
        return workingHours.get();
    }

    public StringProperty workingHoursProperty() {
        return workingHours;
    }

    public boolean getIsRoundTheClock() {
        return isRoundTheClock.get();
    }

    public BooleanProperty isRoundTheClockProperty() {
        return isRoundTheClock;
    }
}
