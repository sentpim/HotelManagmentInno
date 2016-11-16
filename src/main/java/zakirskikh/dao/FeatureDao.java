package zakirskikh.dao;

import org.apache.log4j.Logger;
import zakirskikh.model.Feature;
import zakirskikh.model.Post;

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
public class FeatureDao {

    final static Logger logger = Logger.getLogger(FeatureDao.class);

    public static Feature save(Feature feature) {
        if (feature.getId() > 0) {
            // Editing

            PreparedStatement stmt = null;
            Connection con = getConnection();
            try {
                stmt = con.prepareStatement("UPDATE Feature SET name=?, price=?, hotel_id=?" +
                        "WHERE id=?");
                stmt.setString(1, feature.getName());
                stmt.setInt(2, feature.getPrice());
                stmt.setInt(3, feature.getHotelId());
                stmt.setInt(4, feature.getId());

                stmt.execute();

                logger.trace("OK: Edited Feature with id " + feature.getId());

                stmt.close();

                return feature;
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: Feature with id " + feature.getId() + " was NOT edited: " + e.getLocalizedMessage());
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
                stmt = con.prepareStatement("INSERT INTO Feature "
                        + "(name, price, hotel_id)"
                        + "VALUES(?,?,?) RETURNING id");
                stmt.setString(1, feature.getName());
                stmt.setInt(2, feature.getPrice());
                stmt.setInt(3, feature.getHotelId());

                ResultSet rs = stmt.executeQuery();

                logger.trace("OK: Added new Feature with name " + feature.getName());

                try {
                    rs.next();

                    int id = rs.getInt("id");
                    feature.setId(id);
                } catch (Exception ex) {
                    logger.error("FAILURE: can't get id from added Feature!");
                }

                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: can't add a new Feature with name " + feature.getName());
            } finally {
                try {
                    stmt.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return feature;
    }

    public static Feature get(int id) {
        String sql = "SELECT * FROM Feature WHERE id = ?";
        Feature feature = null;

        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            feature = new Feature();
            feature.setId(rs.getInt("id"));
            feature.setName(rs.getString("name"));
            feature.setPrice(rs.getInt("price"));
            feature.setHotelId(rs.getInt("hotel_id"));

            logger.trace("OK: Feature was taken with id " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: can't get Feature with id " + id);
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return feature;
    }

    public static void delete(int id) {
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement("DELETE FROM Feature WHERE id =  ?");
            stmt.setInt(1, id);

            stmt.execute();

            logger.trace("OK: Feature was deleted");

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: Feature was not deleted!");
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Feature> getAll() {
        String sql = "SELECT * FROM Feature";
        List<Feature> list = new ArrayList<>();
        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            Feature feature;
            while (rs.next()) {
                feature = new Feature();

                feature.setId(rs.getInt("id"));
                feature.setName(rs.getString("name"));
                feature.setPrice(rs.getInt("price"));
                feature.setHotelId(rs.getInt("hotel_id"));

                list.add(feature);
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

    public static List<Feature> getAll(int hotelId) {
        String sql = "SELECT * FROM Feature WHERE hotel_id=?";
        List<Feature> list = new ArrayList<>();
        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, hotelId
            );
            ResultSet rs = stm.executeQuery();
            Feature feature;
            while (rs.next()) {
                feature = new Feature();

                feature.setId(rs.getInt("id"));
                feature.setName(rs.getString("name"));
                feature.setPrice(rs.getInt("price"));
                feature.setHotelId(rs.getInt("hotel_id"));

                list.add(feature);
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
