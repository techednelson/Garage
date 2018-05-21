package model;

import java.sql.Timestamp;

public class Vehicle {
    public enum Employee { George_Mendez, Alicia_Keys, Marc_Lopez }

    private double price;
    private String type;
    private int spot;
    private String customer;
    private String employee;
    private String plateNumber;
    private Timestamp timestamp;

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
