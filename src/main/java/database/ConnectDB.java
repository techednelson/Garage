package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    protected Connection conn;

    final String URL_DB = "jdbc:postgresql://localhost:5432/garageDB";

    final String USER = "postgres";
    final String PASSWORD = "";

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

//    CREATE TABLE IF NOT EXISTS customer (
//        id serial,
//        customer VARCHAR(255) NOT NULL,
//    PRIMARY KEY(id)
//);
//
//        INSERT INTO Customer
//        VALUES  (default, 'Javier Lopez'),
//        (default, 'Miguel Gonzalez');
//
//
//        CREATE TABLE IF NOT EXISTS employee (
//        id serial ,
//        employee VARCHAR(255) NOT NULL,
//        PRIMARY KEY(id)
//        );
//
//        INSERT INTO employee
//        VALUES  (default, 'Alicia Keys'),
//        (default, 'Jose Rodriguez'),
//        (default, 'Will Smith');
//
//        CREATE TABLE IF NOT EXISTS Vehicle (
//        id serial ,
//        vehicle_type VARCHAR(60) NOT NULL,
//        price real NOT NULL,
//        spot INT NOT NULL,
//        customerid serial,
//        employeeid INT,
//        plate_number varchar(255),
//        time_stamp timestamp,
//        PRIMARY KEY(id),
//
//        CONSTRAINT fkVehicleCustomer
//        FOREIGN KEY (customerid)
//        REFERENCES Customer(id),
//
//        CONSTRAINT fkVehicleEmployee
//        FOREIGN KEY (employeeid)
//        REFERENCES Employee(id)
//        );
