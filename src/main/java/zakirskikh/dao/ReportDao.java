package zakirskikh.dao;

import org.apache.log4j.Logger;
import zakirskikh.model.Post;
import zakirskikh.model.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static zakirskikh.connection.ConnectionManager.getConnection;

/**
 * Created by Anvar on 17/11/2016.
 */
public class ReportDao {

    final static Logger logger = Logger.getLogger(ReportDao.class);

    public static Report save(Report report) {
        if (report.getId() > 0) {
            // Editing

            PreparedStatement stmt = null;
            Connection con = getConnection();
            try {
                stmt = con.prepareStatement("UPDATE Report SET text=?, person_id=?" +
                        "WHERE id=?");
                stmt.setString(1, report.getText());
                stmt.setInt(2, report.getPersonId());
                stmt.setInt(3, report.getId());

                stmt.execute();

                logger.trace("OK: Edited Report with id " + report.getId());

                stmt.close();

                return report;
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: Report with id " + report.getId() + " was NOT edited: " + e.getLocalizedMessage());
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
                stmt = con.prepareStatement("INSERT INTO Report "
                        + "(text, person_id)"
                        + "VALUES(?,?) RETURNING id");
                stmt.setString(1, report.getText());
                stmt.setInt(2, report.getPersonId());

                ResultSet rs = stmt.executeQuery();

                logger.trace("OK: Added new Report with text " + report.getText());

                try {
                    rs.next();

                    int id = rs.getInt("id");
                    report.setId(id);
                } catch (Exception ex) {
                    logger.error("FAILURE: can't get id from added Report!");
                }

                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: can't add a new Report with text " + report.getText());
            } finally {
                try {
                    stmt.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return report;
    }

    public static void delete(int id) {
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement("DELETE FROM Report WHERE id =  ?");
            stmt.setInt(1, id);

            stmt.execute();

            logger.trace("OK: Report was deleted");

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: Report was not deleted!");
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Report> getAll() {
        String sql = "SELECT * FROM Report";
        List<Report> list = new ArrayList<Report>();
        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            Report report;
            while (rs.next()) {
                report = new Report();

                report.setId(rs.getInt("id"));
                report.setText(rs.getString("text"));
                report.setPersonId(rs.getInt("person_id"));
                list.add(report);
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
