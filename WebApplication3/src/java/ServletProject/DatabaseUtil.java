package ServletProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tour_shop";
    private static final String USER = "root";  // Use your DB username
    private static final String PASSWORD = "root";  // Use your DB password

    // Get database connection
    public static Connection getConnection() throws SQLException {
        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found", e);
        }
    }
}