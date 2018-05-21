package model;

import java.util.ArrayList;

public class Customer {

    private String name;
    private ArrayList<Vehicle> vehicles;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public ArrayList<Vehicle> getVehicles() { return vehicles; }

    public void setVehicles(Vehicle vehicle) { this.vehicles.add(vehicle); }

}
