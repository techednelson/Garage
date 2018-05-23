package database;

import model.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GarageDao {
    void registerVehicle(Vehicle vehicle);
    ArrayList<Vehicle> getAllVehicles();
    Vehicle getVehicle(String plateNumber);
    void updateVehicle () throws SQLException;
    void deleteVehicle() throws SQLException;
}
