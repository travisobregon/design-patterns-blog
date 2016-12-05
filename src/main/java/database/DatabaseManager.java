package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private Connection connection;

    public Connection connect() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/java/database/database.sqlite");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return this.connection;
    }

    public void disconnect() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
