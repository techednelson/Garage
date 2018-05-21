package database;

import model.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GarageDao {
    void registerVehicle(Vehicle vehicle);
    ArrayList<Vehicle> vehicleList();
    void updateVehicle () throws SQLException;
    void deleteVehicle() throws SQLException;
}
