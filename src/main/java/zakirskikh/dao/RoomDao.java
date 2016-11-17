package zakirskikh.dao;

import org.apache.log4j.Logger;
import zakirskikh.model.Hotel;
import zakirskikh.model.Room;

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
public class RoomDao {

    final static Logger logger = Logger.getLogger(RoomDao.class);

    public static Room save(Room room) {
        if (room.getId() > 0) {
            // Editing

            PreparedStatement stmt = null;
            Connection con = getConnection();
            try {
                stmt = con.prepareStatement("UPDATE Room SET number=?, room_type_id=?, hotel_id=?" +
                        "WHERE id=?");
                stmt.setString(1, room.getNumber());
                stmt.setInt(2, room.getRoomTypeId());
                stmt.setInt(3, room.getHotelId());
                stmt.setInt(4, room.getId());

                stmt.execute();

                logger.trace("OK: Edited Room with id " + room.getId());

                stmt.close();

                return room;
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: Room with id " + room.getId() + " was NOT edited: " + e.getLocalizedMessage());
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
                stmt = con.prepareStatement("INSERT INTO Room "
                        + "(number, room_type_id, hotel_id)"
                        + "VALUES(?,?,?) RETURNING id");
                stmt.setString(1, room.getNumber());
                stmt.setInt(2, room.getRoomTypeId());
                stmt.setInt(3, room.getHotelId());

                ResultSet rs = stmt.executeQuery();

                try {
                    rs.next();

                    int id = rs.getInt("id");
                    room.setId(id);
                } catch (Exception ex) {
                    logger.error("FAILURE: can't get id from added Room!");
                }

                logger.trace("OK: Added new Room with id " + room.getId());

                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: can't add a new Room with number " + room.getNumber());
            } finally {
                try {
                    stmt.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return room;
    }

    public static void delete(int id) {
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement("DELETE FROM Room WHERE id =  ?");
            stmt.setInt(1, id);

            stmt.execute();

            logger.trace("OK: Room was deleted");

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: Room was not deleted!");
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Room> getAll() {
        String sql = "SELECT * FROM Room";
        List<Room> list = new ArrayList<>();
        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            Room room;
            while (rs.next()) {
                room = new Room();

                room.setId(rs.getInt("id"));
                room.setNumber(rs.getString("number"));
                room.setRoomTypeId(rs.getInt("room_type_id"));
                room.setHotelId(rs.getInt("hotel_id"));

                list.add(room);
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

    public static List<Room> getAll(int hotelId) {
//        String sql = "SELECT r.id as id, number, room_type_id FROM Room r, RoomType rt WHERE rt.id=r.room_type_id and rt.hotel_id=?";
        String sql = "SELECT * FROM Room WHERE hotel_id=?";
        List<Room> list = new ArrayList<>();
        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, hotelId);
            ResultSet rs = stm.executeQuery();
            Room room;
            while (rs.next()) {
                room = new Room();

                room.setId(rs.getInt("id"));
                room.setNumber(rs.getString("number"));
                room.setRoomTypeId(rs.getInt("room_type_id"));
                room.setHotelId(rs.getInt("hotel_id"));

                list.add(room);
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
