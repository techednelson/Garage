package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectDB {
    protected Connection conn;

    final String URL_DB = "jdbc:postgresql://localhost:5432/garageDB";

    final String USER = "postgres";
    final String PASSWORD = "!@#321Nel";

    public void createDatabase() {
        String sql;
        PreparedStatement statement;
        try {
            this.openConnectionDB();

            // Delete tables every time program is run
            sql = "drop table if exists customer cascade ";
            statement = this.conn.prepareStatement(sql);
            statement.executeUpdate();

            sql = "drop table if exists employee cascade ";
            statement = this.conn.prepareStatement(sql);
            statement.executeUpdate();

            sql = "drop table if exists vehicle cascade ";
            statement = this.conn.prepareStatement(sql);
            statement.executeUpdate();

            // create table customer
            sql = "create table if not exists customer (" +
                    "id serial primary key, " +
                    "customer varchar(255)" +
                    ")";
            statement = this.conn.prepareStatement(sql);
            statement.executeUpdate();

            // insert 2 customers
            sql = "insert into customer " +
                    "(id, customer) " +
                    "values (default, 'javier jimenez'), " +
                    "       (default, 'miguel gonzalez')";
            statement = this.conn.prepareStatement(sql);
            statement.executeUpdate();

            // create table employee
            sql = "create table if not exists employee (" +
                    "id serial primary key, " +
                    "employee varchar(255) " +
                    ")";
            statement = this.conn.prepareStatement(sql);
            statement.executeUpdate();

            // insert 3 employees
            sql = "insert into employee " +
                    "(id, employee) " +
                    "values  (default, 'alicia keys'), " +
                    "        (default, 'jose rodriguez'), " +
                    "        (default, 'will smith')";
            statement = this.conn.prepareStatement(sql);
            statement.executeUpdate();


            // create table vehicle
            sql = "create table if not exists vehicle (" +
                    "id serial primary key, " +
                    "vehicle_type varchar(60) not null, " +
                    "price real not null, " +
                    "spot int not null, " +
                    "customerid serial, " +
                    "employeeid int, " +
                    "plate_number varchar(255), " +
                    "time_stamp timestamp, " +
                    "\n" +
                    "constraint fkvehiclecustomer " +
                    "foreign key (customerid) " +
                    "references customer(id), " +
                    "\n" +
                    "constraint fkvehicleemployee " +
                    "foreign key (employeeid) " +
                    "references employee(id) " +
                    ")";
            statement = this.conn.prepareStatement(sql);
            statement.executeUpdate();

            // insert 2 vehicles
            sql = "insert into vehicle " +
                    "values (default, 'car', 3.0, 0, default, 1, 'IKX-1030', now()), " +
                            "(default, 'motorcycle', 1.5,  1, default, 2, 'IKX-1045', now())";
            statement = this.conn.prepareStatement(sql);
            statement.executeUpdate();
            System.out.println("Creation and connection to GarageDB was successful!");
        } catch(SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        } finally {
            try {
                this.closeConnectionDB();
            } catch (SQLException e) {
                System.out.println("Couldn't close connection to database: " + e.getMessage());
            }
        }
    }

    public void openConnectionDB() {
        try {
            conn = DriverManager.getConnection(URL_DB, USER, PASSWORD);
        } catch(SQLException e) {
            System.out.println("Couldn't connect to database " + e.getMessage());
        }
    }

    public void closeConnectionDB() throws SQLException {
        if(conn != null && !conn.isClosed()) conn.close();
    }
}
