package services;

public interface VehicleServices {

    void printGarage();

    boolean countAvailableSpots();

    void registerVehicle(String driverName, String vehicleType, String plateNumber);
}
