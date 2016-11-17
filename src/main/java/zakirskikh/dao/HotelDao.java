package zakirskikh.dao;

import org.apache.log4j.Logger;
import zakirskikh.form.HotelForm;
import zakirskikh.model.Address;
import zakirskikh.model.Hotel;

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
public class HotelDao {

    final static Logger logger = Logger.getLogger(HotelDao.class);

    public static void save(HotelForm hotelForm) {

        Address address = new Address(
                hotelForm.getCountry(),
                hotelForm.getCity(),
                hotelForm.getAddress(),
                hotelForm.getPostcode()
        );

        address = AddressDao.save(address);

        HotelDao.save(new Hotel(
                hotelForm.getName(),
                hotelForm.getBudget(),
                hotelForm.getStarsCount(),
                address.getId()
        ));

    }

    public static Hotel save(Hotel hotel) {
        if (hotel.getId() > 0) {
            // Editing

            PreparedStatement stmt = null;
            Connection con = getConnection();
            try {
                stmt = con.prepareStatement("UPDATE Hotel SET name=?, budget=?, stars_count=?, address_id=?" +
                        "WHERE id=?");
                stmt.setString(1, hotel.getName());
                stmt.setInt(2, hotel.getBudget());
                stmt.setInt(3, hotel.getStarsCount());
                stmt.setInt(4, hotel.getAddressId());
                stmt.setInt(5, hotel.getId());

                stmt.execute();

                logger.trace("OK: Edited Hotel with id " + hotel.getId());

                stmt.close();

                return hotel;
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: Hotel with id " + hotel.getId() + " was NOT edited: " + e.getLocalizedMessage());
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
                stmt = con.prepareStatement("INSERT INTO Hotel "
                        + "(name, budget, stars_count, address_id)"
                        + "VALUES(?,?,?,?) RETURNING id");
                stmt.setString(1, hotel.getName());
                stmt.setInt(2, hotel.getBudget());
                stmt.setInt(3, hotel.getStarsCount());
                stmt.setInt(4, hotel.getAddressId());

                ResultSet rs = stmt.executeQuery();

                logger.error("OK: Added new Hotel with id " + hotel.getId());

                try {
                    rs.next();

                    int id = rs.getInt("id");
                    hotel.setId(id);
                } catch (Exception ex) {
                    logger.error("FAILURE: can't get id from added Hotel!");
                }

                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: can't add a new Hotel with name " + hotel.getName());
            } finally {
                try {
                    stmt.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return hotel;
    }

    public static Hotel get(int id) {
        String sql = "SELECT * FROM Hotel WHERE id = ?";
        Hotel hotel = null;

        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            hotel = new Hotel();
            hotel.setId(rs.getInt("id"));
            hotel.setName(rs.getString("name"));
            hotel.setBudget(rs.getInt("budget"));
            hotel.setStarsCount(rs.getInt("stars_count"));
            hotel.setAddressId(rs.getInt("address_id"));

            logger.trace("OK: Hotel was taken with id " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: can't get Hotel with id " + id);
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return hotel;
    }

    public static void delete(int id) {
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement("DELETE FROM Hotel WHERE id =  ?");
            stmt.setInt(1, id);

            stmt.execute();

            logger.trace("OK: Hotel was deleted");

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: Hotel was not deleted!");
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Hotel> getAll() {
        String sql = "SELECT * FROM Hotel";
        List<Hotel> list = new ArrayList<>();
        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            Hotel hotel;
            while (rs.next()) {
                hotel = new Hotel();

                hotel.setId(rs.getInt("id"));
                hotel.setName(rs.getString("name"));
                hotel.setAddressId(rs.getInt("address_id"));
                hotel.setStarsCount(rs.getInt("stars_count"));
                hotel.setBudget(rs.getInt("budget"));

                list.add(hotel);
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
