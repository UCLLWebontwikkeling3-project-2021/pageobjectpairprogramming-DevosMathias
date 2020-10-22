package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnectionManager {
    public Connection connection;
    private static DbConnectionManager dbConnectionManager_instance = null;

    private DbConnectionManager(String dbURL) {
        Properties dbProperties = new Properties();
        try {
            Class.forName("util.Secret");
            Secret.setPass(dbProperties);
        } catch (ClassNotFoundException e) {
            System.out.println("Class Secret with credentials not found!");
        }
        dbProperties.setProperty("ssl", "true");
        dbProperties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        dbProperties.setProperty("sslmode", "prefer");

        try {
            System.out.println("connecting to database ...");
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(dbURL, dbProperties);

            System.out.println("done");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getException().getMessage());
        } catch (SQLException e) {
            System.out.println("connection troubles");
            System.out.println(e.getErrorCode());
        }
    }

    public static DbConnectionManager getInstance(String dbURL) {
        if (dbConnectionManager_instance == null) {
            dbConnectionManager_instance = new DbConnectionManager(dbURL);
        }
        return dbConnectionManager_instance;
    }

    /*public static void connect (String dbURL, String schema) throws SQLException {
        getInstance(dbURL, schema);
    }

    public static void disconnect () throws SQLException {
        getConnection().close();
    }*/

    public Connection getConnection() {
        return this.connection;
    }
}
