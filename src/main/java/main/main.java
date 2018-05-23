package main;

import services.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String plateNumber;
    private static int option;
    private static boolean valid = false;
    private static boolean exit = false;

    public static void main(String[] args) {
        VehicleServices vs = new VehicleServicesImpl();
        CustomerServices cs = new CustomerServicesImpl();
        exit = false;
        while(!exit) {
            printMenu();
            option = 0;
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
                        if(vs.countAvailableSpots()) {
                            String driverName = validateName();
                            String vehicleType = validateVehicleType();
                            plateNumber = validatePlateNumber();
                            if(vs.searchVehicle(plateNumber) != null) {
                                vs.registerVehicle(driverName, vehicleType, plateNumber);
                            } else {
                                System.out.println("There was an error, your vehicle already exist in our system, please try again!");
                                continue;
                            }
                        }
                        break;
                    case 3:
                        plateNumber = validatePlateNumber();
                        if(vs.searchVehicle(plateNumber) != null) {
                            option = selectFromSubmenu();
                            switch (option) {
                                case 1:
                                    boolean discount = cs.checkDiscount();
                                    vs.calculateBalance(discount);
                                    break;
                                case 2:
                                    vs.payVehicleBill();
                                    vs.collectVehicle();
                                    break;
                                case 3:
                                    vs.checkStaffDetails();
                                    break;
                                case 4:
                                    System.out.println("Thanks for using the parking lot. Come back soon!");
                                    exit = true;
                                    break;
                            }
                        } else {
                            System.out.println("There was an error, your vehicle already exist in our system, please try again!");
                            continue;
                        }
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
                "and motorcycles respectively. Please select 1, 2, 3 or 4.\n");
        System.out.println("1. Check if garage is full and there are spots available");
        System.out.println("2. Start your vehicle registration");
        System.out.println("3. Pay & collect your vehicle");
        System.out.println("4. Exit");
    }

    private static int selectFromSubmenu() {
        System.out.println("\nPlease select 1, 2 3 or 4: \n");
        System.out.println("1. Check total time and money spent until now.");
        System.out.println("2. Pay and collect your vehicle.");
        System.out.println("3. Staff details.");
        System.out.println("4. <= Go back.");
        exit = false;
        while(!exit) {
            option = 0;
            try {
                option = Integer.parseInt(br.readLine());
                if(option >= 1 && option <= 4) exit = true;
                else System.out.println("You must select a number between 1 and 4.\n");
            } catch (NumberFormatException e) {
                System.out.println("You must enter integer 1,2,3 or 4, please try again!");
                continue;
            } catch (IOException e) {
                System.out.println("You must enter an integer, please start again!" + e.getMessage());
                continue;
            }
        }
        return option;
    }

    private static String validateName() {
        System.out.println("Start check-in...");
        String driverName = null;
        valid = false;
        while(!valid) {
            try {
                System.out.print("\nEnter your name and last name: ");
                driverName = br.readLine();
                if(!driverName.contains(" ") || isNumber(driverName)) {
                    System.out.println("Please enter letters in stead of digits & leave space between your name and last name. You must start the registration again.");
                    continue;
                }
            }  catch (IOException e) {
                System.out.println("You must enter your name correctly, please start again!");
                continue;
            }
        }
        return driverName;
    }

    private static boolean isNumber(String str) {
        return str.matches(".*\\d+.*");
    }

    private static String validateVehicleType() {
        String vehicleType = null;
        valid = false;
        while(!valid) {
            try {
                System.out.println("Is it a Car or Motorcycle? choose 1 or 2 accordingly.\n");
                System.out.println("1. Car");
                System.out.println("2. Motorcycle");
                int number = Integer.parseInt(br.readLine());
                if (number != 1 && number != 2) {
                    System.out.println("Please select option 1 or 2 if your Vehicle is a Car or a Motorcycle respectively. You must start the registration again.");
                    continue;
                }
                vehicleType = number == 1 ? "Car" : "Motorcycle";
            } catch (NumberFormatException e) {
                System.out.println("You must enter integer 1 or 2, please start again!");
                continue;
            } catch (IOException e) {
                System.out.println("You must enter your name correctly, please start again!");
                continue;
            }
        }
        return vehicleType;
    }

    private  static  String validatePlateNumber() {
        System.out.print("Please type your Vehicle plate number: ");
        valid = false;
        while(!valid) {
            try {
                plateNumber = br.readLine();
                String str = plateNumber.replaceAll("\\s","");
                if(!str.isEmpty() && str.length() == 8) {
                    valid = true;
                } else {
                    System.out.println("Vehicle's plate number can not has white spaces, can not be empty and must have 8 " +
                            "characters");
                    continue;
                }
            } catch (IOException e) {
                System.out.println("You must enter your plate number correctly, please start again!");
                continue;
            }
        }
        return plateNumber;
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
