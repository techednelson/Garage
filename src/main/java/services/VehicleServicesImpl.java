package services;

import database.GarageDao;
import database.*;
import model.*;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class VehicleServicesImpl implements VehicleServices {

    private static Timestamp finalEndTime;
    private static double finalTotalCost = 0;
    private static int finalTotalTime = 0;

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

    private String findEmployeeOnDuty() {
        int random = (int)(Math.random() * 3 + 1);
        return random == 1 ? "Alicia Keys" : random == 2 ? "Jose Rodriguez" : "Will Smith";
    }

    private void printTicket(double price, String vehicleType, String driverName, String employee, String plateNumber, Timestamp timestamp) {
        System.out.println("\n                               TICKET                               ");
        System.out.println("\n=====================================================================");
        System.out.println(" Timestamp: " + timestamp);
        System.out.println(" Vehicle: " + vehicleType + "   Vehicle_Number_ID: " + plateNumber + "\n " +
                "Price/Hour: " +
                price +
                " Euros\n " +
                "Driver: "
                + driverName + "\n Employee on duty: " + employee);
        System.out.println("=====================================================================");
    }

    @Override
    public void registerVehicle(String driverName, String vehicleType, String plateNumber) {
        int spot = garageDao.getAllVehicles().size();
        double price = vehicleType.equals("Car") ? 3.0 : 1.5;
        String employee = findEmployeeOnDuty();
        int employeeID = employee.equals("Alicia Keys") ? 1 : employee.equals("Jose Rodriguez") ? 2 : 3;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Vehicle vehicle = new Vehicle(price, vehicleType, driverName, employee, employeeID, plateNumber, spot, timestamp);
        garageDao.registerVehicle(vehicle);
        printTicket(price, vehicleType, driverName, employee, plateNumber, timestamp);
    }


    @Override
    public Vehicle searchVehicle(String plateNumber) {
        return garageDao.getVehicle(plateNumber);
    }

    @Override
    public boolean checkDiscount(String driverName) { return garageDao.checkDiscount(driverName); }

    private int generateTimeInGarageInMinutes() {
        int totalTime = 0; // Total time to be charged in minutes
        int maxMinutesInParking = 24 * 60;// this is a business domain constraint that a vehicle cannot be in the garage for more than 24 hours

        try {
            // Minutes spent in parking lot. The maximum time spent in the parking lot is 24 hours.
            totalTime = (int)(Math.random() * maxMinutesInParking) ; // random time simulates milliseconds
            }
        catch (Exception e)
        { totalTime = -1; }
        return totalTime;

    }


    @Override
    public void calculateBill (boolean discount, Vehicle vehicle) {
        double totalCost = 0;
        int totalTimeInMins = generateTimeInGarageInMinutes();
        int totalTimeInHours = totalTimeInMins / 60;

        if (totalTimeInMins == -1 ) return;

        // Minutes spent in parking lot. The maximum time spent in the parking lot is 24 hours.
        Long startTime = vehicle.getTimestamp().getTime(); // time in milliseconds
        Timestamp endTime =  new Timestamp(startTime + (totalTimeInMins * 60 * 1000));

        // Total time to be charged in minutes
        if(totalTimeInMins <= 30)
            totalCost = 0;
        else
            totalCost = totalTimeInHours * vehicle.getPrice();

        // Total money to pay for parking depending if discount applies. Discount can apply if customer has at least 2 vehicles in a limit time of 24 hours.
        if(discount)
            totalCost *= 0.70; // 30% discount applied

        printVehicleBill(discount, endTime, vehicle, totalCost, totalTimeInMins);
        finalEndTime = endTime;
        finalTotalCost = totalCost;
        finalTotalTime = totalTimeInMins;
    }

    private void printVehicleBill(boolean discount, Timestamp endTime, Vehicle vehicle, double totalCost, int totalTime) {
        System.out.println("\n                           VEHICLE BILL                              ");
        System.out.println("\n=====================================================================");
        System.out.println(" Timestamp: " + endTime);
        System.out.println(" Vehicle: " + vehicle.getType() + "   Vehicle_Number_ID: " + vehicle.getPlateNumber() + "\n " +
                "Price/Hour: " +
                vehicle.getPrice() +
                " Euros\n " +
                "Driver: "
                + vehicle.getCustomer() + "\n Employee on duty: " + vehicle.getEmployee());
        System.out.println(" Total_Time: " + new DecimalFormat("#.##").format(totalTime) + " Hours");
        if(discount) System.out.println(" Total_Cost: " + new DecimalFormat("#.##").format(totalCost) + " " +
                "Euros " +
                "  (30% discount)");
        else System.out.println(" Total_Cost: " + new DecimalFormat("#.##").format(totalCost) + " Euros");
        System.out.println("=====================================================================");
    }

    private void parkingDisMethod() {

        for(int i = 0; i < garage.length; i++) {
            if(garage[i].contains(this.getVehicleID())) {
                garage[i-5] = "     { }     ";
                garage[i] = "ID:          ";
                break;
            }
        }
        for(int i = StaticDB.getGarageVehiclesDB().size() - 1; i >= 0; i--) {
            if(StaticDB.getGarageVehiclesDB().get(i).getVehicleID().equals(this.getVehicleID())) {
                StaticDB.removeVehicleGarageDB(StaticDB.getGarageVehiclesDB().get(i));
            }
        }

        System.out.print("\n                          PARKING LOT                        ");
        for(int i = 0; i < 20; ++i) {
            if(i % 5 == 0) {
                System.out.print("\n=====================================================================\n");
                System.out.print(StaticDB.getGarage()[i]);
            } else {
                System.out.print(StaticDB.getGarage()[i]);
            }
        }
        System.out.println("\n=====================================================================\n");
    }

    @Override
    public void payVehicleBill(boolean discount, Vehicle vehicle) {

        if(finalTotalTime == 0) calculateBill(discount, vehicle);

        else printVehicleBill(discount, finalEndTime, vehicle, finalTotalCost, finalTotalTime);


        System.out.println(" BILL PAID: Thanks for using our parking lot. Come back soon.\n");
        parkingDisMethod();
    }

    @Override
    public void collectVehicle() {

    }

    @Override
    public void checkStaffDetails() {

    }

}
