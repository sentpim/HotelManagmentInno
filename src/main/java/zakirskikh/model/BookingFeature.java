package zakirskikh.model;

import zakirskikh.dao.BookingDao;

/**
 * Created by Anvar on 15/11/2016.
 */
public class BookingFeature {

    private int id;

    private int featureId;

    private int bookingId;

    public BookingFeature() {
    }

    public BookingFeature(int featureId, int bookingId) {
        this.featureId = featureId;
        this.bookingId = bookingId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Feature getFeature() {
        return null;
    }

    public Booking getBooking() {
        return BookingDao.get(bookingId);
    }
}
