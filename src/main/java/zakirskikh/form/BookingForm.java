package zakirskikh.form;

import java.sql.Date;

/**
 * Created by Anvar on 17/11/2016.
 */
public class BookingForm {

    private int id;

    private int hotelId;

    private int days;

    private int personCount;

    private Date checkIn;

    private Date checkOut;

    private int personId;

    private boolean isPayed;

    private int roomTypeId;

    public BookingForm() {
    }

    public BookingForm(int id, int hotelId, int days, int personCount, Date checkIn, int personId, boolean isPayed, int roomTypeId) {
        this.id = id;
        this.hotelId = hotelId;
        this.days = days;
        this.personCount = personCount;
        this.checkIn = checkIn;
        this.personId = personId;
        this.isPayed = isPayed;
        this.roomTypeId = roomTypeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public boolean getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(boolean isPayed) {
        this.isPayed = isPayed;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    @Override
    public String toString() {
        return "BookingForm{" +
                "id=" + id +
                ", hotelId=" + hotelId +
                ", days=" + days +
                ", personCount=" + personCount +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", personId=" + personId +
                ", isPayed=" + isPayed +
                ", roomTypeId=" + roomTypeId +
                '}';
    }
}
