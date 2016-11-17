package zakirskikh.model;

import zakirskikh.dao.CustomerDao;
import zakirskikh.dao.HotelDao;
import zakirskikh.dao.PersonDao;

import java.sql.Date;

/**
 * Created by Anvar on 15/11/2016.
 */
public class Booking {

    private int id;

    private Date createdAt;

    private Date checkIn;

    private Date checkOut;

    private int days;

    private boolean isPayed;

    private int personCount;

    private int personId;

    private int hotelId;

    private Hotel hotel;

    private Person person;

    private int roomTypeId;

    public Booking() {
    }

    public Booking(Date createdAt, Date checkIn, Date checkOut, int days, boolean isPayed, int personCount, int personId, int hotelId, int roomTypeId) {
        this.createdAt = createdAt;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.days = days;
        this.isPayed = isPayed;
        this.personCount = personCount;
        this.personId = personId;
        this.hotelId = hotelId;
        this.roomTypeId = roomTypeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }

    public String getIsPayed() {
        return (isPayed()) ? "+" : "";
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public Person getPerson() {
        return (person == null) ? person = PersonDao.get(personId) : person;
    }

    public Hotel getHotel() {
        return (hotel == null) ? hotel = HotelDao.get(hotelId) : hotel;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", days=" + days +
                ", isPayed=" + isPayed +
                ", personCount=" + personCount +
                ", personId=" + personId +
                ", hotelId=" + hotelId +
                ", hotel=" + hotel +
                ", person=" + person +
                '}';
    }
}
