package database;

import model.Vehicle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GarageDaoImpl extends ConnectDB implements GarageDao {

    @Override
    public void registerVehicle(Vehicle vehicle) {
        String sql;
        try {
            this.open();

            sql =   "insert into vehicle (id, price, vehicle_type, spot, " +
                    "customerid, employeeid, plate_number, time_stamp) " +
                    "values (default, ?, ?, ?, default , default , ?, now())";
            PreparedStatement statement = this.conn.prepareStatement(sql);
            statement.setDouble(1, vehicle.getPrice());
            statement.setString(2, vehicle.getType());
            statement.setInt(3, vehicle.getSpot());
            statement.setString(4, vehicle.getPlateNumber());
            statement.executeUpdate();


            sql = "insert into customer (id, customer) "
                    + "values (default, ?) ";
            statement = this.conn.prepareStatement(sql);
            statement.setString(1, vehicle.getCustomer());
            statement.executeUpdate();


            sql = "insert into employee (id, employee) "
                    + "values (default, ?) ";
            statement = this.conn.prepareStatement(sql);
            statement.setString(1, vehicle.getEmployee());
            statement.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        } finally {
            try {
                this.close();
            } catch (SQLException e) {
                System.out.println("Couldn't close connection to database: " + e.getMessage());
            }
        }
    }

    @Override
    public ArrayList<Vehicle> getAllVehicles() {
        ArrayList<Vehicle> vehiclesList = null;
        String sql = "select * from vehicle v " +
                        "inner join customer c " +
                        "on v.customerid = c.id " +
                        "inner join employee e " +
                        "on v.employeeid = e.id";
        try {
            this.open();
            PreparedStatement statement = this.conn.prepareStatement(sql);
            statement.executeUpdate();
            vehiclesList = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setPrice(resultSet.getDouble("price"));
                vehicle.setType(resultSet.getString("vehicle_type"));
                vehicle.setSpot(resultSet.getInt("spot"));
                vehicle.setCustomer(resultSet.getString("customer"));
                vehicle.setEmployee(resultSet.getString("employee"));
                vehicle.setPlateNumber(resultSet.getString("plate_number"));
                vehicle.setTimestamp(resultSet.getTimestamp("time_stamp"));
                vehiclesList.add(vehicle);
            }
        } catch(SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        } finally {
            try {
                this.close();
            } catch (SQLException e) {
                System.out.println("Couldn't close connection to database: " + e.getMessage());
            }
        }
        return vehiclesList;
    }

    @Override
    public void updateVehicle() throws SQLException {

    }

    @Override
    public void deleteVehicle() throws SQLException {

    }
}
