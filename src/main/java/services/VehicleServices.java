package services;

import model.Vehicle;

public interface VehicleServices {

    void printGarage();

    boolean countAvailableSpots();

    void registerVehicle(String driverName, String vehicleType, String plateNumber);

    Vehicle searchVehicle(String plateNumber);

    boolean checkDiscount(String driverName);

    void calculateBill(boolean discount, Vehicle vehicle);

    void payVehicleBill(boolean discount, Vehicle vehicle);

    void collectVehicle();

    void checkStaffDetails();

}
