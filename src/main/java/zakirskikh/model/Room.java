package zakirskikh.model;

/**
 * Created by Anvar on 15/11/2016.
 */
public class Room {

    private int id;

    private String number;

    private int roomTypeId;

    public Room(String number, int roomTypeId) {
        this.number = number;
        this.roomTypeId = roomTypeId;
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
        return null;
    }
}