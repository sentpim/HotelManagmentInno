package zakirskikh.form;

/**
 * Created by Anvar on 17/11/2016.
 */
public class FeatureForm {

    private String name;

    private int price;

    private int hotelId;

    public FeatureForm() {
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

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString() {
        return "FeatureForm{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", hotelId=" + hotelId +
                '}';
    }
}
