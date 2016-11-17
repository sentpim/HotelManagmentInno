package zakirskikh.dao;

import org.apache.log4j.Logger;
import zakirskikh.controller.AuthController;
import zakirskikh.form.PersonProfileForm;
import zakirskikh.model.Address;
import zakirskikh.model.Gender;
import zakirskikh.model.Person;
import zakirskikh.model.SystemUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static zakirskikh.connection.ConnectionManager.getConnection;

/**
 * Created by Anvar on 15/11/2016.
 */
public class PersonDao {

    final static Logger logger = Logger.getLogger(PersonDao.class);

    public static void save(PersonProfileForm personProfileForm) {
        Address address = AddressDao.get(SystemUser.getCurrent().getPerson().getAddressId());
        address.setCountry(personProfileForm.getCountry());
        address.setCity(personProfileForm.getCity());
        address.setPostcode(personProfileForm.getPostcode());
        address.setAddress(personProfileForm.getAddress());
        AddressDao.save(address);

        Person person = SystemUser.getCurrent().getPerson();
        person.setFirstName(personProfileForm.getFirstName());
        person.setLastName(personProfileForm.getLastName());
        person.setGender(Gender.getGender(personProfileForm.getGenderId()));
        person.setPassportId(personProfileForm.getPassportId());
        person.setEmail(personProfileForm.getEmail());
        PersonDao.save(person);
    }

    public static Person save(Person person) {
        if (person.getId() > 0) {
            // Editing

            PreparedStatement stmt = null;
            Connection con = getConnection();
            try {
                stmt = con.prepareStatement("UPDATE Person SET first_name=?, last_name=?, gender=?, phone_number=?, passport_id=?, email=?, address_id=?" +
                        "WHERE id=?");
                stmt.setString(1, person.getFirstName());
                stmt.setString(2, person.getLastName());
                stmt.setInt(3, person.getGender().getGenderId());
                stmt.setString(4, person.getPhoneNumber());
                stmt.setString(5, person.getPassportId());
                stmt.setString(6, person.getEmail());
                stmt.setInt(7, person.getAddressId());
                stmt.setInt(8, person.getId());

                stmt.execute();

                logger.trace("OK: Edited Person with email " + person.getEmail());

                stmt.close();

                return person;
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: Person with email " + person.getEmail() + " was NOT edited: " + e.getLocalizedMessage());
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
                stmt = con.prepareStatement("INSERT INTO Person "
                        + "(first_name, last_name, gender, phone_number, passport_id, email, address_id)"
                        + "VALUES(?,?,?,?,?,?,?) RETURNING id");
                stmt.setString(1, person.getFirstName());
                stmt.setString(2, person.getLastName());
                stmt.setInt(3, person.getGender().getGenderId());
                stmt.setString(4, person.getPhoneNumber());
                stmt.setString(5, person.getPassportId());
                stmt.setString(6, person.getEmail());
                stmt.setInt(7, person.getAddressId());

                ResultSet rs = stmt.executeQuery();

                logger.trace("OK: Added new Person with email " + person.getEmail());

                try {
                    rs.next();

                    int id = rs.getInt("id");
                    person.setId(id);
                } catch (Exception ex) {
                    logger.error("FAILURE: can't get id from added Person!");
                }

                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: can't add a new Person with email " + person.getEmail());
            } finally {
                try {
                    stmt.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return person;
    }

    public static Person get(int id) {
        String sql = "SELECT * FROM Person WHERE id = ?";
        Person person = null;

        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            rs.next();

            person = new Person();

            person.setId(rs.getInt("id"));
            person.setEmail(rs.getString("email"));
            person.setFirstName(rs.getString("first_name"));
            person.setLastName(rs.getString("last_name"));
            person.setPhoneNumber(rs.getString("phone_number"));
            person.setPassportId(rs.getString("passport_id"));
            person.setGender(Gender.getGender(rs.getInt("gender")));
            person.setAddressId(rs.getInt("address_id"));
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

        return person;
    }

    public static Person get(String email) {
        String sql = "SELECT * FROM Person WHERE email = ?";
        Person person = null;

        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            rs.next();

            person = new Person();

            person.setId(rs.getInt("id"));
            person.setEmail(rs.getString("email"));
            person.setFirstName(rs.getString("first_name"));
            person.setLastName(rs.getString("last_name"));
            person.setPhoneNumber(rs.getString("phone_number"));
            person.setPassportId(rs.getString("passport_id"));
            person.setGender(Gender.getGender(rs.getInt("gender")));
            person.setAddressId(rs.getInt("address_id"));
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

        return person;
    }

    public static void delete(Person person) {
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement("DELETE FROM Person WHERE id =  ?");
            stmt.setInt(1, person.getId());

            stmt.execute();

            logger.trace("OK: Person was deleted");

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: Person was not deleted!");
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
            stmt = con.prepareStatement("DELETE FROM Person WHERE id =  ?");
            stmt.setInt(1, id);

            stmt.execute();

            logger.trace("OK: Person was deleted");

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: Person was not deleted!");
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Person> getAll() {
        String sql = "SELECT * FROM Person";
        List<Person> list = new ArrayList<Person>();
        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            Person person;
            while (rs.next()) {
                person = new Person();

                person.setId(rs.getInt("id"));
                person.setEmail(rs.getString("email"));
                person.setFirstName(rs.getString("first_name"));
                person.setLastName(rs.getString("last_name"));
                person.setPhoneNumber(rs.getString("phone_number"));
                person.setPassportId(rs.getString("passport_id"));
                person.setGender(Gender.getGender(rs.getInt("gender")));
                person.setAddressId(rs.getInt("address_id"));
                list.add(person);
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
