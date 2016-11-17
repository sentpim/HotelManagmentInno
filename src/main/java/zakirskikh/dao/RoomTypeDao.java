package zakirskikh.dao;

import org.apache.log4j.Logger;
import zakirskikh.model.Room;
import zakirskikh.model.RoomType;

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
public class RoomTypeDao {

    final static Logger logger = Logger.getLogger(RoomTypeDao.class);

    public static RoomType save(RoomType roomType) {
        if (roomType.getId() > 0) {
            // Editing

            PreparedStatement stmt = null;
            Connection con = getConnection();
            try {
                stmt = con.prepareStatement("UPDATE RoomType SET name=?, price=?, beds_count=?, hotel_id=?" +
                        "WHERE id=?");
                stmt.setString(1, roomType.getName());
                stmt.setInt(2, roomType.getPrice());
                stmt.setInt(3, roomType.getBedsCount());
                stmt.setInt(4, roomType.getHotelId());
                stmt.setInt(5, roomType.getId());

                stmt.execute();

                logger.trace("OK: Edited RoomType with id " + roomType.getId());

                stmt.close();

                return roomType;
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: RoomType with id " + roomType.getId() + " was NOT edited: " + e.getLocalizedMessage());
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
                stmt = con.prepareStatement("INSERT INTO RoomType "
                        + "(name, price, beds_count, hotel_id)"
                        + "VALUES(?,?,?,?) RETURNING id");
                stmt.setString(1, roomType.getName());
                stmt.setInt(2, roomType.getPrice());
                stmt.setInt(3, roomType.getBedsCount());
                stmt.setInt(4, roomType.getHotelId());

                ResultSet rs = stmt.executeQuery();

                logger.trace("OK: Added new RoomType with id " + roomType.getId());

                try {
                    rs.next();

                    int id = rs.getInt("id");
                    roomType.setId(id);
                } catch (Exception ex) {
                    logger.error("FAILURE: can't get id from added RoomType!");
                }

                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: can't add a new RoomType with name " + roomType.getName());
            } finally {
                try {
                    stmt.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return roomType;
    }

    public static RoomType get(int id) {
        String sql = "SELECT * FROM RoomType WHERE id = ?";
        RoomType roomType = null;

        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            roomType = new RoomType();
            roomType.setId(rs.getInt("id"));
            roomType.setName(rs.getString("name"));
            roomType.setPrice(rs.getInt("price"));
            roomType.setBedsCount(rs.getInt("beds_count"));
            roomType.setHotelId(rs.getInt("hotel_id"));

            logger.trace("OK: RoomType was taken with id " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: can't get RoomType with id " + id);
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return roomType;
    }

    public static void delete(int id) {
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement("DELETE FROM RoomType WHERE id =  ?");
            stmt.setInt(1, id);

            stmt.execute();

            logger.trace("OK: RoomType was deleted");

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: RoomType was not deleted!");
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<RoomType> getAll() {
        String sql = "SELECT * FROM RoomType";
        List<RoomType> list = new ArrayList<>();
        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            RoomType roomType;
            while (rs.next()) {
                roomType = new RoomType();

                roomType.setId(rs.getInt("id"));
                roomType.setName(rs.getString("name"));
                roomType.setPrice(rs.getInt("price"));
                roomType.setBedsCount(rs.getInt("beds_count"));
                roomType.setHotelId(rs.getInt("hotel_id"));

                list.add(roomType);
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

    public static List<RoomType> getAll(int hotelId) {
        String sql = "SELECT * FROM RoomType WHERE hotel_id=?";
        List<RoomType> list = new ArrayList<>();
        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, hotelId);
            ResultSet rs = stm.executeQuery();
            RoomType roomType;
            while (rs.next()) {
                roomType = new RoomType();

                roomType.setId(rs.getInt("id"));
                roomType.setName(rs.getString("name"));
                roomType.setPrice(rs.getInt("price"));
                roomType.setBedsCount(rs.getInt("beds_count"));
                roomType.setHotelId(rs.getInt("hotel_id"));

                list.add(roomType);
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
