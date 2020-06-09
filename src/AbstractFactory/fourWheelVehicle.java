package AbstractFactory;

import Builder.MapB;
import utilities.VehicleType;

/**
 * Class represent a vehicle with 4 wheels, part of Abstract factory design pattern.
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 * @see components.Vehicle
 * @see VehicleType
 */
public class fourWheelVehicle extends vehicle {
    /**
     * Constructor
     */
    public fourWheelVehicle(){
        super();
    }

    /**
     * Function return vehicle type by string calue it receives
     * @param y
     * @return VehicleType
     */
    @Override
    public VehicleType getVehicle(String y) {
        switch (y){
            case "private": return VehicleType.car;
            case "work": return VehicleType.truck;
            case "public": return VehicleType.bus;
            default:return null;
        }
    }
    @Override
    public String toString() {
        return "4 wheel vehicle.";
    }
}
