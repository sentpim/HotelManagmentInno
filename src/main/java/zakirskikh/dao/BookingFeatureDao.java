package zakirskikh.dao;

import org.apache.log4j.Logger;
import zakirskikh.model.BookingFeature;
import zakirskikh.model.Feature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static zakirskikh.connection.ConnectionManager.getConnection;

/**
 * Created by Anvar on 16/11/2016.
 */
public class BookingFeatureDao {

    final static Logger logger = Logger.getLogger(BookingFeatureDao.class);

    public static BookingFeature save(BookingFeature bookingFeature) {
        if (bookingFeature.getId() > 0) {
            // Editing

            PreparedStatement stmt = null;
            Connection con = getConnection();
            try {
                stmt = con.prepareStatement("UPDATE BookingFeature SET booking_id=?, feature_id=?" +
                        "WHERE id=?");
                stmt.setInt(1, bookingFeature.getBookingId());
                stmt.setInt(2, bookingFeature.getFeatureId());
                stmt.setInt(3, bookingFeature.getId());

                stmt.execute();

                logger.trace("OK: Edited BookingFeature with id " + bookingFeature.getId());

                stmt.close();

                return bookingFeature;
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: BookingFeature with id " + bookingFeature.getId() + " was NOT edited: " + e.getLocalizedMessage());
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
                stmt = con.prepareStatement("INSERT INTO BookingFeature "
                        + "(booking_id, feature_id)"
                        + "VALUES(?,?) RETURNING id");
                stmt.setInt(1, bookingFeature.getBookingId());
                stmt.setInt(2, bookingFeature.getFeatureId());

                ResultSet rs = stmt.executeQuery();

                logger.trace("OK: Added new BookingFeature");

                try {
                    rs.next();

                    int id = rs.getInt("id");
                    bookingFeature.setId(id);
                } catch (Exception ex) {
                    logger.error("FAILURE: can't get id from added BookingFeature!");
                }

                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: can't add a new BookingFeature");
            } finally {
                try {
                    stmt.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return bookingFeature;
    }

    public static BookingFeature get(int id) {
        String sql = "SELECT * FROM BookingFeature WHERE id = ?";
        BookingFeature bookingFeature = null;

        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            bookingFeature = new BookingFeature();
            bookingFeature.setId(rs.getInt("id"));
            bookingFeature.setBookingId(rs.getInt("booking_id"));
            bookingFeature.setFeatureId(rs.getInt("feature_id"));

            logger.trace("OK: BookingFeature was taken with id " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: can't get BookingFeature with id " + id);
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return bookingFeature;
    }

    public static void delete(int id) {
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement("DELETE FROM BookingFeature WHERE id =  ?");
            stmt.setInt(1, id);

            stmt.execute();

            logger.trace("OK: BookingFeature was deleted");

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: BookingFeature was not deleted!");
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<BookingFeature> getAll(int bookingId) {
        String sql = "SELECT * FROM BookingFeature WHERE booking_id=?";
        List<BookingFeature> list = new ArrayList<>();
        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, bookingId);
            ResultSet rs = stm.executeQuery();
            BookingFeature bookingFeature;
            while (rs.next()) {
                bookingFeature = new BookingFeature();

                bookingFeature.setId(rs.getInt("id"));
                bookingFeature.setBookingId(rs.getInt("booking_id"));
                bookingFeature.setFeatureId(rs.getInt("feature_id"));

                list.add(bookingFeature);
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
