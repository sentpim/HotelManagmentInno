package zakirskikh.dao;

import org.apache.log4j.Logger;
import zakirskikh.model.Address;
import zakirskikh.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static zakirskikh.connection.ConnectionManager.getConnection;

/**
 * Created by Anvar on 15/11/2016.
 */
public class AddressDao {

    final static Logger logger = Logger.getLogger(AddressDao.class);

    public static Address save(Address address) {
        if (address.getId() > 0) {
            // Editing

            PreparedStatement stmt = null;
            Connection con = getConnection();
            try {
                stmt = con.prepareStatement("UPDATE Address SET country=?, city=?, address=?, post_code=?" +
                        "WHERE id=?");
                stmt.setString(1, address.getCountry());
                stmt.setString(2, address.getCity());
                stmt.setString(3, address.getAddress());
                stmt.setString(4, address.getPostcode());
                stmt.setInt(5, address.getId());

                stmt.execute();

                logger.trace("OK: Edited Address with id " + address.getId());

                stmt.close();

                return address;
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: Address with id " + address.getId() + " was NOT edited: " + e.getLocalizedMessage());
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
                stmt = con.prepareStatement("INSERT INTO Address "
                        + "(country, city, address, post_code)"
                        + "VALUES(?,?,?,?) RETURNING id");
                stmt.setString(1, address.getCountry());
                stmt.setString(2, address.getCity());
                stmt.setString(3, address.getAddress());
                stmt.setString(4, address.getPostcode());

                ResultSet rs = stmt.executeQuery();

                logger.error("OK: Added new Address with id " + address.getId());

                try {
                    rs.next();

                    int id = rs.getInt("id");
                    address.setId(id);
                } catch (Exception ex) {
                    logger.error("FAILURE: can't get id from added Address!");
                }

                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: can't add a new Address with postcode " + address.getPostcode());
            } finally {
                try {
                    stmt.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return address;
    }

    public static Address get(int id) {
        String sql = "SELECT * FROM Address WHERE id = ?";
        Address address = null;

        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            rs.next();

            address = new Address();
            address.setId(rs.getInt("id"));
            address.setCountry(rs.getString("country"));
            address.setCity(rs.getString("city"));
            address.setAddress(rs.getString("address"));
            address.setPostcode(rs.getString("post_code"));
            logger.trace("OK: Address was taken with id " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: can't get address with id " + id);
        } finally {
            try {
                stm.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return address;
    }

    public static void delete(Address address) {
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement("DELETE FROM Address WHERE id =  ?");
            stmt.setInt(1, address.getId());

            stmt.execute();

            logger.trace("OK: Address was deleted");

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: Address was not deleted!");
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void delete(int id) {
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement("DELETE FROM Address WHERE id =  ?");
            stmt.setInt(1, id);

            stmt.execute();

            logger.trace("OK: Address was deleted");

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: Address was not deleted!");
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
