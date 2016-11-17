package zakirskikh.model;

import zakirskikh.dao.HotelDao;
import zakirskikh.dao.RoomTypeDao;

/**
 * Created by Anvar on 15/11/2016.
 */
public class Room {

    private int id;

    private String number;

    private int roomTypeId;

    private int hotelId;

    public Room() {
    }

    public Room(String number, int roomTypeId, int hotelId) {
        this.number = number;
        this.roomTypeId = roomTypeId;
        this.hotelId = hotelId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public RoomType getRoomType() {
        return RoomTypeDao.get(roomTypeId);
    }

    public Hotel getHotel() {
        return HotelDao.get(hotelId);
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", roomTypeId=" + roomTypeId +
                '}';
    }
}
