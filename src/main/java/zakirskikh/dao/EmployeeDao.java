package zakirskikh.dao;

import org.apache.log4j.Logger;
import zakirskikh.model.Employee;

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
public class EmployeeDao {

    final static Logger logger = Logger.getLogger(EmployeeDao.class);

    public static Employee save(Employee employee) {
        if (employee.getId() > 0) {
            // Editing

            PreparedStatement stmt = null;
            Connection con = getConnection();
            try {
                stmt = con.prepareStatement("UPDATE Employee SET salary=?, start_date=?, post_id=?, hotel_id=?, person_id=?" +
                        "WHERE id=?");
                stmt.setInt(1, employee.getSalary());
                stmt.setDate(2, employee.getStartDate());
                stmt.setInt(3, employee.getPostId());
                stmt.setInt(4, employee.getHotelId());
                stmt.setInt(5, employee.getPersonId());
                stmt.setInt(6, employee.getHotelId());

                stmt.execute();

                logger.trace("OK: Edited Employee with id " + employee.getId());

                stmt.close();

                return employee;
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: Employee with id " + employee.getId() + " was NOT edited: " + e.getLocalizedMessage());
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
                stmt = con.prepareStatement("INSERT INTO Employee "
                        + "(salary, start_date, post_id, hotel_id, person_id)"
                        + "VALUES(?,?,?,?,?) RETURNING id");
                stmt.setInt(1, employee.getSalary());
                stmt.setDate(2, employee.getStartDate());
                stmt.setInt(3, employee.getPostId());
                stmt.setInt(4, employee.getHotelId());
                stmt.setInt(5, employee.getPersonId());

                ResultSet rs = stmt.executeQuery();

                logger.trace("OK: Added new Employee with personId " + employee.getPersonId());

                try {
                    rs.next();

                    int id = rs.getInt("id");
                    employee.setId(id);
                } catch (Exception ex) {
                    logger.error("FAILURE: can't get id from added Employee!");
                }

                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: can't add a new Employee with personId " + employee.getPersonId());
            } finally {
                try {
                    stmt.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return employee;
    }

    public static Employee get(int id) {
        String sql = "SELECT * FROM Employee WHERE id = ?";
        Employee employee = null;

        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setSalary(rs.getInt("salary"));
            employee.setStartDate(rs.getDate("start_date"));
            employee.setPostId(rs.getInt("post_id"));
            employee.setPersonId(rs.getInt("person_id"));
            employee.setHotelId(rs.getInt("hotel_id"));

            logger.trace("OK: Employee was taken with id " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: can't get Employee with id " + id);
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return employee;
    }

    public static List<Employee> getAll() {
        String sql = "SELECT * FROM Employee";
        List<Employee> list = new ArrayList<>();
        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            Employee employee;
            while (rs.next()) {
                employee = new Employee();

                employee.setId(rs.getInt("id"));
                employee.setSalary(rs.getInt("salary"));
                employee.setPostId(rs.getInt("post_id"));
                employee.setPersonId(rs.getInt("person_id"));
                employee.setHotelId(rs.getInt("hotel_id"));
                employee.setStartDate(rs.getDate("start_date"));

                list.add(employee);
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
