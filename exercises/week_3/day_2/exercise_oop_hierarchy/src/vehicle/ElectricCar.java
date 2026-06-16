package vehicle;

public class ElectricCar extends Vehicle implements AutonomousCapable {
    private double chargeCost;
    private double kilosPerCharge;
    private boolean selfDriveOn;

    public ElectricCar(String make, int modelYear, double chargeCost, double kilosPerCharge, boolean selfDriveOn) {
        super(make, modelYear);
        this.chargeCost = chargeCost;
        this.kilosPerCharge = kilosPerCharge;
        this.selfDriveOn = selfDriveOn;
    }

    @Override
    public double fuelCostPer100Km() {
        return (chargeCost / kilosPerCharge) * 100;
    }

    public void enableSelfDrive() {
        this.selfDriveOn = true;
    }

    public void disableSelfDrive() {
        this.selfDriveOn = false;
    }

    public double getChargeCost() {
        return chargeCost;
    }

    public void setChargeCost(double chargeCost) {
        this.chargeCost = chargeCost;
    }

    public double getKilosPerCharge() {
        return kilosPerCharge;
    }

    public void setKilosPerCharge(double kilosPerCharge) {
        this.kilosPerCharge = kilosPerCharge;
    }

    public boolean supportsSelfDrive() {
        return this.selfDriveOn;
    }
}
