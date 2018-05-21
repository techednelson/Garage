package database;

import model.Vehicle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GarageDaoImpl extends ConnectDB implements GarageDao {

    public void registerVehicle(Vehicle vehicle) {
        try {
            this.open();
            PreparedStatement statement = this.conn.prepareStatement("insert into vehicle (vehicleid, price, " +
                    "vehicle_type, spot, customer, employee, platenumber, time_stamp) " +
                    "values (default, ?, ?, ?, default, default, ?, now()");
            statement.setDouble(2, vehicle.getPrice());
            statement.executeUpdate();
            statement.setString(3, vehicle.getType());
            statement.executeUpdate();
            statement.setInt(4, vehicle.getSpot());
            statement.executeUpdate();
            statement.setString(7, vehicle.getPlateNumber());
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

    public ArrayList<Vehicle> vehicleList() {
        ArrayList<Vehicle> vehicleList;
        try {
            this.open();
            PreparedStatement statement = this.conn.prepareStatement("select * from vehicle v " +
                                                                        "inner join customer c " +
                                                                        "on v.customerid = c.id " +
                                                                        "inner join employee e " +
                                                                        "on v.employeeid = e.id");
            vehicleList = new ArrayList<Vehicle>();
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
                vehicleList.add(vehicle);
                System.out.println(vehicle.getType() + " " + vehicle.getPlateNumber() + " " + vehicle.getCustomer() + vehicle.getEmployee() + vehicle.getTimestamp());
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
        return null;
    }

    public void updateVehicle() throws SQLException {

    }

    public void deleteVehicle() throws SQLException {

    }
}
