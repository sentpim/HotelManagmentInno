package zakirskikh.model;

import zakirskikh.dao.HotelDao;

/**
 * Created by Anvar on 15/11/2016.
 */
public class RoomType {

    private int id;

    private String name;

    private int price;

    private int bedsCount;

    private int hotelId;

    public RoomType(String name, int price, int bedsCount, int hotelId) {
        this.name = name;
        this.price = price;
        this.bedsCount = bedsCount;
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

    public int getBedsCount() {
        return bedsCount;
    }

    public void setBedsCount(int bedsCount) {
        this.bedsCount = bedsCount;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public Hotel getHotel() {
        return HotelDao.get(hotelId);
    }
}
