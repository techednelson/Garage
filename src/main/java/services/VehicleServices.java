package services;

import model.Vehicle;

public interface VehicleServices {

    void printGarage();

    boolean countAvailableSpots();

    void registerVehicle(String driverName, String vehicleType, String plateNumber);

    Vehicle searchVehicle(String plateNumber);

    void calculateBalance(boolean discount);

    void payVehicleBill();

    void collectVehicle();

    void checkStaffDetails();

}
