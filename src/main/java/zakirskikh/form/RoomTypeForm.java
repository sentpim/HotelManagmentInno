package zakirskikh.form;

/**
 * Created by Anvar on 17/11/2016.
 */
public class RoomTypeForm {

    private int id;

    private String name;

    private int price;

    private int bedsCount;

//    private int hotelId;

    public RoomTypeForm() {
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

    public int getBedsCount() {
        return bedsCount;
    }

    public void setBedsCount(int bedsCount) {
        this.bedsCount = bedsCount;
    }
}
