package zakirskikh.model;

import zakirskikh.dao.AddressDao;

/**
 * Created by Anvar on 15/11/2016.
 */
public class Hotel {

    private int id;

    private String name;

    private int budget;

    private int starsCount;

    private int addressId;

    public Hotel() {
    }

    public Hotel(String name, int budget, int starsCount, int addressId) {
        this.name = name;
        this.budget = budget;
        this.starsCount = starsCount;
        this.addressId = addressId;
    }

    public Hotel(int id, String name, int budget, int starsCount, int addressId) {
        this(name, budget, starsCount, addressId);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(int starsCount) {
        this.starsCount = starsCount;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public Address getAddress() {
        return AddressDao.get(addressId);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", starsCount=" + starsCount +
                ", addressId=" + addressId +
                '}';
    }
}
