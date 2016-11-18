package zakirskikh.dao;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import zakirskikh.form.SystemUserForm;
import zakirskikh.model.*;

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
public class SystemUserDao {

    final static Logger logger = Logger.getLogger(SystemUserDao.class);

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static SystemUser save(SystemUserForm systemUserForm) {
        System.out.println(systemUserForm);

        SystemUser systemUser = new SystemUser();
        systemUser.setId(systemUserForm.getId());
        systemUser.setPassword(systemUserForm.getPassword());
        systemUser.setEmail(systemUserForm.getEmail());
        systemUser.setPersonId(systemUserForm.getPersonId());
        systemUser.setRole(SystemUserRole.getSystemUserRole(systemUserForm.getRoleId()));

        Person person;

        if (systemUserForm.getPersonId() == 0){
            Address address = AddressDao.save(new Address(
                    "",
                    "",
                    "",
                    ""
            ));

            person = PersonDao.save(new Person(
                    "",
                    "",
                    Gender.MALE,
                    "",
                    systemUserForm.getEmail(),
                    "",
                    address.getId()
            ));

            systemUser.setPersonId(person.getId());
        }



        System.out.println(systemUser);

        return save(systemUser);
    }

    public static SystemUser save(SystemUser systemUser) {
        if (systemUser.getId() > 0) {
            // Editing

            PreparedStatement stmt = null;
            Connection con = getConnection();
            try {
                stmt = con.prepareStatement("UPDATE SystemUser SET email=?, password=?, role=?, person_id=?" +
                        "WHERE id=?");
                stmt.setString(1, systemUser.getEmail());
                stmt.setString(2, encoder.encode(systemUser.getPassword()));
                stmt.setInt(3, systemUser.getRole().getRoleId());
                stmt.setInt(4, systemUser.getPersonId());
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
                        + "(email, password, role, person_id)"
                        + "VALUES(?,?,?,?) RETURNING id");
                stmt.setString(1, systemUser.getEmail());
                stmt.setString(2, encoder.encode(systemUser.getPassword()));
                stmt.setInt(3, systemUser.getRole().getRoleId());
                stmt.setInt(4, systemUser.getPersonId());

                ResultSet rs = stmt.executeQuery();

                logger.trace("OK: Added new SystemUser with email " + systemUser.getEmail());

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
            systemUser.setPersonId(rs.getInt("person_id"));
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

    public static void delete(int id) {
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement("DELETE FROM SystemUser WHERE id =  ?");
            stmt.setInt(1, id);

            stmt.execute();

            logger.trace("OK: SystemUser was deleted");

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: SystemUser was not deleted!");
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<SystemUser> getAll() {
        String sql = "SELECT * FROM SystemUser";
        List<SystemUser> list = new ArrayList<SystemUser>();
        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            SystemUser systemUser;
            while (rs.next()) {
                systemUser = new SystemUser();

                systemUser.setId(rs.getInt("id"));
                systemUser.setEmail(rs.getString("email"));
                systemUser.setPassword(rs.getString("password"));
                systemUser.setRole(SystemUserRole.getSystemUserRole(rs.getInt("role")));
                systemUser.setPersonId(rs.getInt("person_id"));

                list.add(systemUser);
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
