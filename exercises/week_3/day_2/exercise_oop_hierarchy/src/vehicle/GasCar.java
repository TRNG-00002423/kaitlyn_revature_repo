package vehicle;

public class GasCar extends Vehicle {

    private double kilosPerLiter;
    private double costPerLiter;

    public GasCar(String make, int modelYear, double kilosPerLiter, double costPerLiter) {
        super(make, modelYear);
        this.kilosPerLiter = kilosPerLiter;
        this.costPerLiter = costPerLiter;
    }

    public double getKilosPerLiter() {
        return kilosPerLiter;
    }

    public void setKilosPerLiter(double kilosPerLiter) {
        this.kilosPerLiter = kilosPerLiter;
    }

    public double getCostPerLiter() {
        return costPerLiter;
    }

    public void setCostPerLiter(double costPerLiter) {
        this.costPerLiter = costPerLiter;
    }

    @Override
    public double fuelCostPer100Km() {
        double costPerKm = costPerLiter / kilosPerLiter;
        return 100 * costPerKm;
    }
}
