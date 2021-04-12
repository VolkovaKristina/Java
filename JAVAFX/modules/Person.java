package program.modules;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Person {
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty street;
    private StringProperty city;
    private IntegerProperty postalCode;
    private ObjectProperty<LocalDate> birthDay;
    public Person(){
        this(null,null);
    }

    public Person(String firstName,String lastName){
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);

        this.street = new SimpleStringProperty("dfghj");
        this.city = new SimpleStringProperty("Moscow");
        this.postalCode = new SimpleIntegerProperty(12345);
        this.birthDay = new SimpleObjectProperty<LocalDate>(LocalDate.of(1995,12,12));
    }
    public String getFirstName() {
        return firstName.get();
    }
    public int getPostalCode() {
        return postalCode.get();
    }
    public String getCity() {
        return city.get();
    }
    public String getLastName() {
        return lastName.get();
    }
    public LocalDate getBirthDay() {
        return birthDay.get();
    }
    public String getStreet() {
        return street.get();
    }

    public StringProperty getFirstNameProperty() {
        return firstName;
    }
    public IntegerProperty getPostalCodeProperty() {
        return postalCode;
    }
    public StringProperty getCityProperty() {
        return city;
    }
    public StringProperty getLastNameProperty() { return lastName;}
    public ObjectProperty<LocalDate> getBirthDayProperty() { return birthDay; }
    public StringProperty getStreetProperty() { return street;}

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    public void setBirthDay(LocalDate birthDay) {
        this.birthDay.set(birthDay);
    }
    public void setCity(String city) {
        this.city.set(city);
    }
    public void setPostalCode(int postalCode) {
        this.postalCode.set(postalCode);
    }
    public void setStreet(String street) {
        this.street.set(street);
    }
}
