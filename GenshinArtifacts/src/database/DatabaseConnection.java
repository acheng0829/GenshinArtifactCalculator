package database;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class DatabaseConnection {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "genshin_artifacts";
    //private static final String jdbcUrl = "jdbc:mysql://localhost/client_schedule?connectionTimeZone = SERVER";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "fox.falco.34";
    private static String password = "420Dragonblade";
    public static Connection connection;  // Connection Interface

    /**
     * Attempts to open a connection with a database.
     *
     * @return true if a connection was established and false otherwise.
     */
    public static Boolean openConnection() {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
            return true;
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }

    /**
     * Closes an opened connection with a database.
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}