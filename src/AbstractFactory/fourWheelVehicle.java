package AbstractFactory;

import utilities.VehicleType;

public class fourWheelVehicle extends vehicle {
    public fourWheelVehicle(){
        super();
    }
    @Override
    VehicleType getVehicle(String y) {
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
