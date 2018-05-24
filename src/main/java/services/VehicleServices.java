package services;

import model.Vehicle;

public interface VehicleServices {

    void printGarage();

    boolean countAvailableSpots();

    void registerVehicle(String driverName, String vehicleType, String plateNumber);

    void parkVehicle(String vehicleType, String plateNumber);

    Vehicle searchVehicle(String plateNumber);

    void checkDiscount(String driverName);

    void calculateBill(Vehicle vehicle);

    void payVehicleBill(Vehicle vehicle);

    void collectVehicleFromGarage(Vehicle vehicle);

    void checkStaffDetails(Vehicle vehicle);

}
