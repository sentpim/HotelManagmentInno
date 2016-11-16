package zakirskikh.dao;

import org.apache.log4j.Logger;
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
public class PostDao {

    final static Logger logger = Logger.getLogger(PostDao.class);

    public static Post save(Post post) {
        if (post.getId() > 0) {
            // Editing

            PreparedStatement stmt = null;
            Connection con = getConnection();
            try {
                stmt = con.prepareStatement("UPDATE Post SET name=?" +
                        "WHERE id=?");
                stmt.setString(1, post.getName());
                stmt.setInt(2, post.getId());

                stmt.execute();

                logger.trace("OK: Edited Post with id " + post.getId());

                stmt.close();

                return post;
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: Post with id " + post.getId() + " was NOT edited: " + e.getLocalizedMessage());
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
                stmt = con.prepareStatement("INSERT INTO Post "
                        + "(name)"
                        + "VALUES(?) RETURNING id");
                stmt.setString(1, post.getName());

                ResultSet rs = stmt.executeQuery();

                logger.trace("OK: Added new Post with name " + post.getName());

                try {
                    rs.next();

                    int id = rs.getInt("id");
                    post.setId(id);
                } catch (Exception ex) {
                    logger.error("FAILURE: can't get id from added Post!");
                }

                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("FAILURE: can't add a new Post with name " + post.getName());
            } finally {
                try {
                    stmt.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return post;
    }

    public static Post get(int id) {
        String sql = "SELECT * FROM Post WHERE id = ?";
        Post post = null;

        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            post = new Post();
            post.setId(rs.getInt("id"));
            post.setName(rs.getString("name"));

            logger.trace("OK: Post was taken with id " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: can't get Post with id " + id);
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return post;
    }

    public static void delete(int id) {
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try {
            stmt = con.prepareStatement("DELETE FROM Post WHERE id =  ?");
            stmt.setInt(1, id);

            stmt.execute();

            logger.trace("OK: Post was deleted");

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("FAILURE: Post was not deleted!");
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Post> getAll() {
        String sql = "SELECT * FROM Post";
        List<Post> list = new ArrayList<Post>();
        PreparedStatement stm = null;
        Connection con = getConnection();
        try {
            stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            Post post;
            while (rs.next()) {
                post = new Post();

                post.setId(rs.getInt("id"));
                post.setName(rs.getString("name"));
                list.add(post);
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
