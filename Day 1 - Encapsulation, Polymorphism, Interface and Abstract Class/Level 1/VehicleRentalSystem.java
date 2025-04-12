import java.util.ArrayList;
import java.util.List;

interface Insurable {
    double calculateInsurance();
    String getInsuranceDetails();
}

abstract class Vehicle {
    private String vehicleNumber;
    private String type;
    private double rentalRate;

    public Vehicle(String vehicleNumber, String type, double rentalRate) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
        this.rentalRate = rentalRate;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getType() {
        return type;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public abstract double calculateRentalCost(int days);

    @Override
    public String toString() {
        return "Vehicle Number: " + vehicleNumber + ", Type: " + type + ", Rental Rate: " + rentalRate;
    }
}

class Car extends Vehicle implements Insurable {
    private String model;
    private String insurancePolicyNumber; // Encapsulated

    public Car(String vehicleNumber, String model, double rentalRate, String insurancePolicyNumber) {
        super(vehicleNumber, "Car", rentalRate);
        this.model = model;
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    public String getModel() {
        return model;
    }

    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days;
    }

    @Override
    public double calculateInsurance() {
        // Example insurance calculation for a car
        return 50.0 * 30; // Assuming a base cost per month
    }

    @Override
    public String getInsuranceDetails() {
        // Restrict access to the full policy number
        return "Insurance Policy Details: Policy Number ends with ****" + insurancePolicyNumber.substring(insurancePolicyNumber.length() - 4);
    }

    @Override
    public String toString() {
        return super.toString() + ", Model: " + model;
    }
}

// Subclass Bike
class Bike extends Vehicle {
    private String brand;

    public Bike(String vehicleNumber, String brand, double rentalRate) {
        super(vehicleNumber, "Bike", rentalRate);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public double calculateRentalCost(int days) {
        // Discount for longer bike rentals
        if (days > 7) {
            return getRentalRate() * days * 0.9; // 10% discount
        }
        return getRentalRate() * days;
    }

    @Override
    public String toString() {
        return super.toString() + ", Brand: " + brand;
    }
}

// Subclass Truck
class Truck extends Vehicle implements Insurable {
    private double loadCapacity;
    private String insurancePolicyNumber; // Encapsulated

    public Truck(String vehicleNumber, double loadCapacity, double rentalRate, String insurancePolicyNumber) {
        super(vehicleNumber, "Truck", rentalRate);
        this.loadCapacity = loadCapacity;
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    @Override
    public double calculateRentalCost(int days) {
        // Higher rate for trucks
        return (getRentalRate() + (loadCapacity * 0.1)) * days;
    }

    @Override
    public double calculateInsurance() {
        // Example insurance calculation for a truck
        return 100.0 * 30 + (loadCapacity * 10); // Base cost + cost based on capacity
    }

    @Override
    public String getInsuranceDetails() {
        // Restrict access to the full policy number
        return "Insurance Policy Details: Policy Number ends with ****" + insurancePolicyNumber.substring(insurancePolicyNumber.length() - 4);
    }

    @Override
    public String toString() {
        return super.toString() + ", Load Capacity: " + loadCapacity + " tons";
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Car("CAR001", "Sedan", 50.0, "ABC123456789"));
        vehicles.add(new Bike("BIKE002", "Mountain Bike", 10.0));
        vehicles.add(new Truck("TRUCK003", 5.0, 100.0, "XYZ987654321"));
        vehicles.add(new Car("CAR004", "SUV", 75.0, "DEF012345678"));
        vehicles.add(new Bike("BIKE005", "City Bike", 15.0));

        int rentalDays = 5;

        System.out.println("Rental Details for " + rentalDays + " days:");
        for (Vehicle vehicle : vehicles) {
            System.out.println("\n" + vehicle);
            double rentalCost = vehicle.calculateRentalCost(rentalDays);
            System.out.println("Rental Cost: $" + rentalCost);

            // Demonstrate polymorphism for Insurable vehicles
            if (vehicle instanceof Insurable) {
                Insurable insurableVehicle = (Insurable) vehicle;
                double insuranceCost = insurableVehicle.calculateInsurance();
                String insuranceDetails = insurableVehicle.getInsuranceDetails();
                System.out.println("Insurance Cost: $" + insuranceCost);
                System.out.println(insuranceDetails);
            }
        }
    }
}