package zakirskikh.dao;

import org.apache.log4j.Logger;
import zakirskikh.form.BookingForm;
import zakirskikh.model.Address;
import zakirskikh.model.Booking;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static zakirskikh.connection.ConnectionManager.getConnection;

/**
 * Created by Anvar on 16/11/2016.
 */
public class BookingDao {

    final static Logger logger = Logger.getLogger(BookingDao.class);

    public static void save(BookingForm bookingForm) {

        Booking booking = new Booking(
                Date.valueOf(LocalDate.now()),
                bookingForm.getCheckIn(),
                Date.valueOf(bookingForm.getCheckIn().toLocalDate().plusDays(bookingForm.getDays())),
                bookingForm.getDays(),
                false,
                bookingForm.getPersonCount(),
                bookingForm.getPersonId(),
                bookingForm.getHotelId()
        );

        save(booking);

    }

    public static Booking save(Booking booking) {
        if (booking.getId() > 0) {
            // Editing

            PreparedStatement stmt = null;
            Connection con = getConnection();
            try {
                stmt = con.prepareStatement("UPDATE Booking SET created_at=?, check_in=?, check_out=?, days=?, is_payed=?, person_count=?, person_id=?" +
                        "WHERE id=?");
                stmt.setDate(1, booking.getCreatedAt());
                stmt.setDate(2, booking.getCheckIn());
                stmt.setDate(3, booking.getCheckOut());
                stmt.setInt(4, booking.getDays());
                stmt.setBoolean(5, booking.isPayed());
                stmt.setInt(6, booking.getPersonCount());
                stmt.setInt(7, booking.getPersonId());
                stmt.setInt(8, booking.getId());

                stmt.execute();

                logger.trace("OK: Edited Booking with id " + booking.getId());

                stmt.close();

                return booking;
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: Booking with id " + booking.getId() + " was NOT edited: " + e.getLocalizedMessage());
            } finally {
                try {
                    stmt.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // Adding

            PreparedStatement stmt = null;
            Connection con = getConnection();
            try {
                stmt = con.prepareStatement("INSERT INTO Booking "
                        + "(created_at, check_in, check_out, days, is_payed, person_count, person_id)"
                        + "VALUES(?,?,?,?,?,?,?) RETURNING id");
                stmt.setDate(1, booking.getCreatedAt());
                stmt.setDate(2, booking.getCheckIn());
                stmt.setDate(3, booking.getCheckOut());
                stmt.setInt(4, booking.getDays());
                stmt.setBoolean(5, booking.isPayed());
                stmt.setInt(6, booking.getPersonCount());
                stmt.setInt(7, booking.getPersonId());

                ResultSet rs = stmt.executeQuery();

                logger.error("OK: Added new Booking with customerId " + booking.getPersonId());

                try {
                    rs.next();

                    int id = rs.getInt("id");
                    booking.setId(id);
                } catch (Exception ex) {
                    logger.error("FAILURE: can't get id from added Booking!");
                }

                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: can't add a new Booking with customerId " + booking.getPersonId());
            } finally {
                try {
                    stmt.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return booking;
    }

    public static Booking get(int id) {
        String sql = "SELECT * FROM Booking WHERE id = ?";
        Booking booking = null;

        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            rs.next();

            booking = new Booking();
            booking.setId(rs.getInt("id"));
            booking.setCreatedAt(rs.getDate("created_at"));
            booking.setCheckIn(rs.getDate("check_in"));
            booking.setCheckOut(rs.getDate("check_out"));
            booking.setDays(rs.getInt("days"));
            booking.setPayed(rs.getBoolean("is_payed"));
            booking.setPersonCount(rs.getInt("person_count"));
            booking.setPersonId(rs.getInt("person_id"));

            logger.trace("OK: Booking was taken with id " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: can't get booking with id " + id);
        } finally {
            try {
                stm.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return booking;
    }

    public static void delete(Booking booking) {
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement("DELETE FROM Booking WHERE id =  ?");
            stmt.setInt(1, booking.getId());

            stmt.execute();

            logger.trace("OK: Booking was deleted");

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: Booking was not deleted!");
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Booking> getAll() {
        String sql = "SELECT * FROM Booking";
        List<Booking> list = new ArrayList<>();
        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            Booking booking;
            while (rs.next()) {

                booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setCreatedAt(rs.getDate("created_at"));
                booking.setCheckIn(rs.getDate("check_in"));
                booking.setCheckOut(rs.getDate("check_out"));
                booking.setDays(rs.getInt("days"));
                booking.setPayed(rs.getBoolean("is_payed"));
                booking.setPersonCount(rs.getInt("person_count"));
                booking.setPersonId(rs.getInt("person_id"));

                list.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stm.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<Booking> getAll(int hotelId) {
        String sql = "SELECT * FROM Booking WHERE hotel_id=?";
        List<Booking> list = new ArrayList<>();
        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, hotelId);
            ResultSet rs = stm.executeQuery();
            Booking booking;
            while (rs.next()) {

                booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setCreatedAt(rs.getDate("created_at"));
                booking.setCheckIn(rs.getDate("check_in"));
                booking.setCheckOut(rs.getDate("check_out"));
                booking.setDays(rs.getInt("days"));
                booking.setPayed(rs.getBoolean("is_payed"));
                booking.setPersonCount(rs.getInt("person_count"));
                booking.setPersonId(rs.getInt("person_id"));

                list.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stm.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
}
