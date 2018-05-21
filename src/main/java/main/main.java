package main;

import database.GarageDaoImpl;
import services.VehicleServicesImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String vehicleID;
    private static boolean valid = false;

    public static void main(String[] args) throws ClassNotFoundException {
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
                        new VehicleServicesImpl().printGarage();
                        break;
                    case 2:

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

    public static void printMenu() {
        System.out.println("\nHello Welcome to the Garage. First 30 min are free of charge. After that the cost is 3 euros P/H and 1.5 euros P/H for cars " +
                "and motorcycles respectively. Please select 1, 2, 3 or 4 accordingly\n");
        System.out.println("1. Check if garage is full and there are spots available");
        System.out.println("2. Start your vehicle registration");
        System.out.println("3. Pay & collect your vehicle");
        System.out.println("4. Exit");
    }

    public static boolean isNumber(String str) {
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
