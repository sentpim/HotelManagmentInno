package zakirskikh.dao;

import org.apache.log4j.Logger;
import zakirskikh.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static zakirskikh.connection.ConnectionManager.getConnection;

/**
 * Created by Anvar on 16/11/2016.
 */
public class CustomerDao {

    final static Logger logger = Logger.getLogger(CustomerDao.class);

    public static Customer save(Customer customer) {
        if (customer.getId() > 0) {
            // Editing

            PreparedStatement stmt = null;
            Connection con = getConnection();
            try {
                stmt = con.prepareStatement("UPDATE Customer SET password=?, person_id=?" +
                        "WHERE id=?");
                stmt.setString(1, customer.getPassword());
                stmt.setInt(2, customer.getPersonId());
                stmt.setInt(3, customer.getId());

                stmt.execute();

                logger.trace("OK: Edited Customer with id " + customer.getId());

                stmt.close();

                return customer;
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: Customer with id " + customer.getId() + " was NOT edited: " + e.getLocalizedMessage());
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
                stmt = con.prepareStatement("INSERT INTO Customer "
                        + "(password, person_id)"
                        + "VALUES(?,?) RETURNING id");
                stmt.setString(1, customer.getPassword());
                stmt.setInt(2, customer.getPersonId());

                ResultSet rs = stmt.executeQuery();

                logger.error("OK: Added new Customer with personId " + customer.getPersonId());

                try {
                    rs.next();

                    int id = rs.getInt("id");
                    customer.setId(id);
                } catch (Exception ex) {
                    logger.error("FAILURE: can't get id from added Customer!");
                }

                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: can't add a new Customer with personId " + customer.getPersonId());
            } finally {
                try {
                    stmt.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return customer;
    }

    public static Customer get(int id) {
        String sql = "SELECT * FROM Customer WHERE id = ?";
        Customer customer = null;

        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setPassword(rs.getString("password"));
            customer.setPersonId(rs.getInt("person_id"));

            logger.trace("OK: Customer was taken with id " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: can't get Customer with id " + id);
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return customer;
    }

    public static void delete(int id) {
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement("DELETE FROM Customer WHERE id =  ?");
            stmt.setInt(1, id);

            stmt.execute();

            logger.trace("OK: Customer was deleted");

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: Customer was not deleted!");
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
