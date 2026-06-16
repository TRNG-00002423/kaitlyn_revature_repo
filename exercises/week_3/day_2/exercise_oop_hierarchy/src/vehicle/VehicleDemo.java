package vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleDemo {
    public static void main(String[] args) {
        GasCar toyotaCorolla = new GasCar("Toyota", 2012, 12, 5);
        ElectricCar chevroletEquinox = new ElectricCar("Chevy", 2025, 7, 463, false);
        ElectricCar teslaModel3 = new ElectricCar("Tesla", 2026, 5, 300, true);
        List<Vehicle> fleet = new ArrayList<>();
        fleet.add(toyotaCorolla);
        fleet.add(chevroletEquinox);
        fleet.add(teslaModel3);
        for (Vehicle car : fleet) {
            System.out.println(car.fuelCostPer100Km());
            if (car instanceof AutonomousCapable) {
                System.out.println(((AutonomousCapable) car).supportsSelfDrive());
            }
        }
    }
}
