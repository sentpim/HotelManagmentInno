package zakirskikh.model;

/**
 * Created by Anvar on 15/11/2016.
 */
public class Address {

    private int id;

    private String country;

    private String city;

    private String address;

    private String postcode;

    public Address() {
    }

    public Address(String country, String city, String address, String postcode) {
        this.country = country;
        this.city = city;
        this.address = address;
        this.postcode = postcode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }
}
