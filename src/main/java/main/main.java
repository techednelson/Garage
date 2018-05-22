package main;

import services.VehicleServices;
import services.VehicleServicesImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String plateNumber;
    private static boolean valid = false;

    public static void main(String[] args) {
        VehicleServices vs = new VehicleServicesImpl();
        boolean exit = false;
        while(!exit) {
            printMenu();
            int option = 0;
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("You must enter integer 1,2,3 or 4, please try again!");
                continue;
            } catch (IOException e) {
                System.out.println("You must enter an integer, please start again!" + e.getMessage());
            }
            if(option != 0)
            {
                switch (option) {
                    case 1:
                        vs.countAvailableSpots();
                        vs.printGarage();
                        break;
                    case 2:
                        if(vs.countAvailableSpots()) validateCredentials();
                        break;
                    case 3:

                        break;
                    case 4:
                        System.out.println("Thanks for using the parking lot. Come back soon!");
                        exit = true;
                        break;
                    default:
                        System.out.println("\n The option you entered is not available or does not exist, please " +
                                "try again!");
                        break;
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nHello Welcome to the Garage. First 30 min are free of charge. After that the cost is 3 euros P/H and 1.5 euros P/H for cars " +
                "and motorcycles respectively. Please select 1, 2, 3 or 4 accordingly.\n");
        System.out.println("1. Check if garage is full and there are spots available");
        System.out.println("2. Start your vehicle registration");
        System.out.println("3. Pay & collect your vehicle");
        System.out.println("4. Exit");
    }


    private static void validateCredentials() {
        VehicleServices vs = new VehicleServicesImpl();
        System.out.println("Start check-in...");
        String driverName = null;
        String vehicleType = null;
        int number;
        valid = false; // This variable remains false while customer contact details are validated
        while(!valid) {
            try {
                System.out.print("\nEnter your name and last name: ");
                driverName = br.readLine();
                if(!driverName.contains(" ") || isNumber(driverName)) {
                    System.out.println("Please enter letters in stead of digits & leave space between your name and last name. You must start the registration again.");
                    continue;
                }
                System.out.println("Is it a Car or Motorcycle? choose 1 or 2 accordingly.\n");
                System.out.println("1. Car");
                System.out.println("2. Motorcycle");
                number = Integer.parseInt(br.readLine());
                if(number != 1 && number !=2) {
                    System.out.println("Please select option 1 or 2 if your Vehicle is a Car or a Motorcycle respectively. You must start the registration again.");
                    continue;
                }
                vehicleType = number == 1 ? "Car" : "Motorcycle";
                System.out.print("Please type your Vehicle plate number: ");
                plateNumber = br.readLine();
                String str = plateNumber.replaceAll("\\s","");
                if(!str.isEmpty() && str.length() == 8) {
                    valid = true;
                } else {
                    System.out.println("Vehicle's plate number can not has white spaces, can not be empty and must have 8 " +
                            "characters");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("You must enter integer 1 or 2, please start again!");
                continue;
            }  catch (IOException e) {
                System.out.println("You must enter one integer, please start again!");
                continue;
            }
        }
        vs.registerVehicle(driverName, vehicleType, plateNumber);
    }

    private static boolean isNumber(String str) {
        return str.matches(".*\\d+.*");
    }

    private static void printGarageDis(String[] distribution) {
        System.out.print("\n                          PARKING LOT                        ");
        for(int i = 0; i < 20; ++i) {
            if(i % 5 == 0) {
                System.out.print("\n=====================================================================\n");
                System.out.print(distribution[i]);
            } else {
                System.out.print(distribution[i]);
            }
        }
        System.out.println("\n=====================================================================\n");
    }

}
