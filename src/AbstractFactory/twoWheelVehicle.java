package AbstractFactory;

import components.Vehicle;
import utilities.VehicleType;

public class twoWheelVehicle extends vehicle {
    public twoWheelVehicle(){
        super();
    }

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
