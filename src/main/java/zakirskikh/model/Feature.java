package zakirskikh.model;

/**
 * Created by Anvar on 15/11/2016.
 */
public class Feature {

    private int id;

    private String name;

    private int price;

    private int hotelId;

    public Feature(String name, int price, int hotelId) {
        this.name = name;
        this.price = price;
        this.hotelId = hotelId;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Hotel getHotel() {
        return null;
    }

}
