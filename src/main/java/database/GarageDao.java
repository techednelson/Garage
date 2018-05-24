package database;

import model.Vehicle;

import java.util.ArrayList;

public interface GarageDao {

    void registerVehicle(Vehicle vehicle);
    ArrayList<Vehicle> getAllVehicles();
    Vehicle searchVehicle(String plateNumber);
    boolean checkDiscount(String driverName);
    void deleteVehicle(Vehicle vehicle);

}
