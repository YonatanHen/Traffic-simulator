package AbstractFactory;

import components.Vehicle;
import utilities.VehicleType;

/**
 * Class represent a vehicle with 2 wheels, part of Abstract factory design pattern.
 * @author Yehonatan Hen
 * @author Rotem Librati
 * @see components.Vehicle
 * @see VehicleType
 */
public class twoWheelVehicle extends vehicle {

    /**
     * Constructor
     */
    public twoWheelVehicle(){
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
            case "fast": return VehicleType.motorcycle;
            case "slow": return VehicleType.bicycle;
            default:return null;
        }
    }
    @Override
    public String toString() {
        return "2 wheel vehicle.";
    }
}
