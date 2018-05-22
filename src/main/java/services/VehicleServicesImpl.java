package services;

import database.GarageDao;
import database.GarageDaoImpl;
import model.Employee;
import model.Vehicle;

import java.sql.Timestamp;

public class VehicleServicesImpl implements VehicleServices {
    private GarageDao garageDao = new GarageDaoImpl();
    private String[] garage = {
            "        {M}     ","      { }     " ,"     {C}     ","     { }     ","     { }     ",
            "    ID:IKX-1030 ","ID:          "," ID:IKX-1045 ","ID:          ","ID:            ",
            "        { }     ","      { }     " ,"     { }     ","     { }     ","     { }     ",
            "   ID:          ","ID:          ","ID:          ","ID:          ","ID:            "
    };

    @Override
    public void printGarage() {
        System.out.print("\n                          PARKING LOT                        ");
        for(int i = 0; i < 20; ++i) {
            if (i % 5 != 0) {
                System.out.print(garage[i]);
            } else {
                System.out.print("\n=====================================================================\n");
                System.out.print(garage[i]);
            }

        }
        System.out.println("\n=====================================================================\n");
    }

    @Override
    public boolean countAvailableSpots() {
        int spots = garageDao.getAllVehicles().size();
        if(spots >= 10) {
            System.out.println("\nThere are not available spots in the garage at this moment, please come back later");
            return false;
        } else {
            System.out.println("\nRight now there are " + (10 - spots) + " spots available.");
            return true;
        }
    }


    @Override
    public void registerVehicle(String driverName, String vehicleType, String plateNumber) {
        int spot = garageDao.getAllVehicles().size();
        double price = vehicleType.equals("Car") ? 3.0 : 1.5;
        String employee = findEmployeeInShift();
        Vehicle vehicle = new Vehicle(price, vehicleType, driverName, employee, plateNumber, spot);
        garageDao.registerVehicle(vehicle);
    }

    private String findEmployeeInShift() {
        int random = (int)(Math.random() * 3 + 1);
        String employee = null;
        switch (random) {
            case 1:
                employee = Employee.Alicia_keys.toString().replace("^[a-zA-Z]+_\\d_\\d_\\d{1,2}$", " ");
                break;
            case 2:
                employee = Employee.Jose_Rodriguez.toString().replace("^[a-zA-Z]+_\\d_\\d_\\d{1,2}$", " ");
            break;
            case 3:
                employee = Employee.Will_Smith.toString().replace("^[a-zA-Z]+_\\d_\\d_\\d{1,2}$", " ");
            break;
        }
        return  employee;
    }
}
