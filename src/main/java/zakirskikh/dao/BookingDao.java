package zakirskikh.dao;

import org.apache.log4j.Logger;
import zakirskikh.model.Address;
import zakirskikh.model.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static zakirskikh.connection.ConnectionManager.getConnection;

/**
 * Created by Anvar on 16/11/2016.
 */
public class BookingDao {

    final static Logger logger = Logger.getLogger(BookingDao.class);

    public static Booking save(Booking booking) {
        if (booking.getId() > 0) {
            // Editing

            PreparedStatement stmt = null;
            Connection con = getConnection();
            try {
                stmt = con.prepareStatement("UPDATE Booking SET created_at=?, check_in=?, check_out=?, days=?, is_payed=?, person_count=?, customer_id=?" +
                        "WHERE id=?");
                stmt.setDate(1, booking.getCreatedAt());
                stmt.setDate(2, booking.getCheckIn());
                stmt.setDate(3, booking.getCheckOut());
                stmt.setInt(4, booking.getDays());
                stmt.setBoolean(5, booking.isPayed());
                stmt.setInt(6, booking.getPersonCount());
                stmt.setInt(7, booking.getCustomerId());
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
                        + "(created_at, check_in, check_out, days, is_payed, person_count, customer_id)"
                        + "VALUES(?,?,?,?,?,?,?) RETURNING id");
                stmt.setDate(1, booking.getCreatedAt());
                stmt.setDate(2, booking.getCheckIn());
                stmt.setDate(3, booking.getCheckOut());
                stmt.setInt(4, booking.getDays());
                stmt.setBoolean(5, booking.isPayed());
                stmt.setInt(6, booking.getPersonCount());
                stmt.setInt(7, booking.getCustomerId());

                ResultSet rs = stmt.executeQuery();

                logger.error("OK: Added new Booking with customerId " + booking.getCustomerId());

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
                logger.error("FAILURE: can't add a new Booking with customerId " + booking.getCustomerId());
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
            booking.setDays(rs.getInt("post_code"));
            booking.setPayed(rs.getBoolean("is_payed"));
            booking.setPersonCount(rs.getInt("person_count"));
            booking.setCustomerId(rs.getInt("customer_id"));

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
    
}
