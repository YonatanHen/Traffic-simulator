package AbstractFactory;

import utilities.VehicleType;

/**
 * Class represent a vehicle with 10 wheels, part of Abstract factory design pattern.
 * @author Yehonatan Hen
 * @author Rotem Librati
 * @see components.Vehicle
 * @see VehicleType
 */
public class tenWheelVehicle extends vehicle {
    /**
     * Constructor
     */
    public tenWheelVehicle(){
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
            case "public": return VehicleType.tram;
            case "work": return VehicleType.semitrailer;
            default:return null;
        }
    }

    @Override
    public String toString() {
        return "10 wheel vehicle.";
    }
}
