package model;

import java.sql.Timestamp;

public class Vehicle {

    private double price;
    private String type;
    private int spot;
    private String customer;
    private String employee;
    private String plateNumber;
    private Timestamp timestamp;

    public Vehicle() {}

    public Vehicle(double price, String type, String customer, String employee, String plateNumber, int spot) {
        this.price = price;
        this.type = type;
        this.customer = customer;
        this.customer = employee;
        this.plateNumber = plateNumber;
        this.spot = spot;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public int getSpot() { return spot; }

    public void setSpot(int spot) { this.spot = spot; }

    public String getCustomer() { return customer; }

    public void setCustomer(String customer) { this.customer = customer; }

    public void setEmployee(String employee) { this.employee = employee; }

    public String  getEmployee() { return employee; }

    public String getPlateNumber() { return plateNumber; }

    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }

    public Timestamp getTimestamp() { return timestamp; }

    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }
}
