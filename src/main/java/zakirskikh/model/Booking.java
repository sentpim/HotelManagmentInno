package zakirskikh.model;

import zakirskikh.dao.CustomerDao;
import zakirskikh.dao.HotelDao;

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

    private int customerId;

    private int hotelId;

    private Hotel hotel;

    private Customer customer;

    public Booking() {
    }

    public Booking(Date createdAt, Date checkIn, Date checkOut, int days, boolean isPayed, int personCount, int customerId, int hotelId) {
        this.createdAt = createdAt;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.days = days;
        this.isPayed = isPayed;
        this.personCount = personCount;
        this.customerId = customerId;
        this.hotelId = hotelId;
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

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public Customer getCustomer() {
        return (customer == null) ? customer = CustomerDao.get(customerId) : customer;
    }

    public Hotel getHotel() {
        return (hotel == null) ? hotel = HotelDao.get(hotelId) : hotel;
    }
}
