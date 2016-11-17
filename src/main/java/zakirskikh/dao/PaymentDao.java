package zakirskikh.dao;

import org.apache.log4j.Logger;
import zakirskikh.model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static zakirskikh.connection.ConnectionManager.getConnection;

/**
 * Created by Anvar on 17/11/2016.
 */
public class PaymentDao {

    final static Logger logger = Logger.getLogger(PaymentDao.class);

    public static Payment save(Payment payment) {
        if (payment.getId() > 0) {
            // Editing

            PreparedStatement stmt = null;
            Connection con = getConnection();
            try {
                stmt = con.prepareStatement("UPDATE Payment SET card_number=?, first_name=?, last_name=?, expire_year=?, expire_month=?, booking_id=?" +
                        "WHERE id=?");
                stmt.setString(1, payment.getCardNumber());
                stmt.setString(2, payment.getFirstName());
                stmt.setString(3, payment.getLastName());
                stmt.setInt(4, payment.getExpireYear());
                stmt.setInt(5, payment.getExpireMonth());
                stmt.setInt(6, payment.getBookingId());
                stmt.setInt(7, payment.getId());

                stmt.execute();

                logger.trace("OK: Edited Payment with id " + payment.getId());

                stmt.close();

                return payment;
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: Payment with id " + payment.getId() + " was NOT edited: " + e.getLocalizedMessage());
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
                stmt = con.prepareStatement("INSERT INTO Payment "
                        + "(card_number, first_name, last_name, expire_year, expire_month, booking_id)"
                        + "VALUES(?,?,?,?,?,?) RETURNING id");
                stmt.setString(1, payment.getCardNumber());
                stmt.setString(2, payment.getFirstName());
                stmt.setString(3, payment.getLastName());
                stmt.setInt(4, payment.getExpireYear());
                stmt.setInt(5, payment.getExpireMonth());
                stmt.setInt(6, payment.getBookingId());

                ResultSet rs = stmt.executeQuery();

                logger.trace("OK: Added new Payment with cardNumber " + payment.getCardNumber());

                try {
                    rs.next();

                    int id = rs.getInt("id");
                    payment.setId(id);
                } catch (Exception ex) {
                    logger.error("FAILURE: can't get id from added Payment!");
                }

                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: can't add a new Payment with cardNumber " + payment.getCardNumber());
            } finally {
                try {
                    stmt.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return payment;
    }

    public static Payment get(int id) {
        String sql = "SELECT * FROM Payment WHERE id = ?";
        Payment payment = null;

        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            payment = new Payment();
            payment.setId(rs.getInt("id"));
            payment.setCardNumber(rs.getString("card_number"));
            payment.setFirstName(rs.getString("first_name"));
            payment.setLastName(rs.getString("last_name"));
            payment.setExpireYear(rs.getInt("expire_year"));
            payment.setExpireMonth(rs.getInt("expire_month"));
            payment.setBookingId(rs.getInt("booking_id"));

            logger.trace("OK: Payment was taken with id " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: can't get Payment with id " + id);
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return payment;
    }

    public static Payment getByBookingId(int bookingId) {
        String sql = "SELECT * FROM Payment WHERE booking_id = ?";
        Payment payment = null;

        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            payment = new Payment();
            payment.setId(rs.getInt("id"));
            payment.setCardNumber(rs.getString("card_number"));
            payment.setFirstName(rs.getString("first_name"));
            payment.setLastName(rs.getString("last_name"));
            payment.setExpireYear(rs.getInt("expire_year"));
            payment.setExpireMonth(rs.getInt("expire_month"));
            payment.setBookingId(rs.getInt("booking_id"));

            logger.trace("OK: Payment was taken with bookingId " + bookingId);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: can't get Payment with bookingId " + bookingId);
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return payment;
    }

}
