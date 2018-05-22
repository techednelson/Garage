package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    protected Connection conn;

    final String URL_DB = "jdbc:postgresql://localhost:5432/garageDB";

    final String USER = "postgres";
    final String PASSWORD = "!@#321Nel";

    public void open() {
        try {
            conn = DriverManager.getConnection(URL_DB, USER, PASSWORD);
            System.out.println("Connected to PostgreSQL server successfully!");
        } catch(SQLException e) {
            System.out.println("Couldn't connect to database " + e.getMessage());
        }
    }

    public void close() throws SQLException {
        if(conn != null && !conn.isClosed()) conn.close();
    }
}
