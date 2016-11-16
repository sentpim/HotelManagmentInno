package zakirskikh.model;

import zakirskikh.dao.AddressDao;

/**
 * Created by Anvar on 15/11/2016.
 */
public class Person {

    private int id;

    private String firstName;

    private String lastName;

    private Gender gender;

    private String phoneNumber;

    private String email;

    private String passportId;

    private int addressId;

    public Person() {
    }

    public Person(String firstName, String lastName, Gender gender, String phoneNumber, String email, String passportId, int addressId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passportId = passportId;
        this.addressId = addressId;
    }

    public Person(int id, String firstName, String lastName, Gender gender, String phoneNumber, String email, String passportId, int addressId) {
        this(firstName, lastName, gender, phoneNumber, email, passportId, addressId);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public Address getAddress() {
        return AddressDao.get(getAddressId());
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", passportId='" + passportId + '\'' +
                ", addressId=" + addressId +
                '}';
    }
}
