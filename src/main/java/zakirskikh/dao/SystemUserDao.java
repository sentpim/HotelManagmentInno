package zakirskikh.dao;

import org.apache.log4j.Logger;
import zakirskikh.model.SystemUser;
import zakirskikh.model.SystemUserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static zakirskikh.connection.ConnectionManager.getConnection;

/**
 * Created by Anvar on 16/11/2016.
 */
public class SystemUserDao {

    final static Logger logger = Logger.getLogger(SystemUserDao.class);

    public static SystemUser save(SystemUser systemUser) {
        if (systemUser.getId() > 0) {
            // Editing

            PreparedStatement stmt = null;
            Connection con = getConnection();
            try {
                stmt = con.prepareStatement("UPDATE SystemUser SET email=?, password=?, role=?, hotel_id=?" +
                        "WHERE id=?");
                stmt.setString(1, systemUser.getEmail());
                stmt.setString(2, systemUser.getPassword());
                stmt.setInt(3, systemUser.getRole().getRoleId());
                stmt.setInt(4, systemUser.getHotelId());
                stmt.setInt(5, systemUser.getId());

                stmt.execute();

                logger.trace("OK: Edited SystemUser with id " + systemUser.getId());

                stmt.close();

                return systemUser;
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: SystemUser with id " + systemUser.getId() + " was NOT edited: " + e.getLocalizedMessage());
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
                stmt = con.prepareStatement("INSERT INTO SystemUser "
                        + "(email, password, role, hotel_id)"
                        + "VALUES(?,?,?,?) RETURNING id");
                stmt.setString(1, systemUser.getEmail());
                stmt.setString(2, systemUser.getPassword());
                stmt.setInt(3, systemUser.getRole().getRoleId());
                stmt.setInt(4, systemUser.getHotelId());

                ResultSet rs = stmt.executeQuery();

                logger.error("OK: Added new SystemUser with email " + systemUser.getEmail());

                try {
                    rs.next();

                    int id = rs.getInt("id");
                    systemUser.setId(id);
                } catch (Exception ex) {
                    logger.error("FAILURE: can't get id from added SystemUser!");
                }

                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: can't add a new SystemUser with email " + systemUser.getEmail());
            } finally {
                try {
                    stmt.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return systemUser;
    }

    public static SystemUser get(String email) {
        String sql = "SELECT * FROM SystemUser WHERE email = ?";
        SystemUser systemUser = null;

        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            rs.next();

            systemUser = new SystemUser();

            systemUser.setId(rs.getInt("id"));
            systemUser.setEmail(rs.getString("email"));
            systemUser.setPassword(rs.getString("password"));
            systemUser.setRole(SystemUserRole.getSystemUserRole(rs.getInt("role")));
            systemUser.setHotelId(rs.getInt("hotel_id"));
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

        return systemUser;
    }
    
}
