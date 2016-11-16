package zakirskikh.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Anvar on 15/11/2016.
 */
public class ConnectionManager {

    public static final String PSQL_RESOURCE="jdbc:postgresql://localhost/hms";


    public static Connection getConnection() {
        return createPSQLConnection();
    }

    public static Connection createPSQLConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            return DriverManager.getConnection(PSQL_RESOURCE, "hms", "12345678");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
