package vehicle;

public abstract class Vehicle {
    private String make;
    private int modelYear;

    public Vehicle(String make, int modelYear) {
        this.make = make;
        this.modelYear = modelYear;
    }

    public abstract double fuelCostPer100Km();
}
