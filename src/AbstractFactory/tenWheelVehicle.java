package AbstractFactory;

import utilities.VehicleType;

public class tenWheelVehicle extends vehicle {
    public tenWheelVehicle(){
       super();
    }
    @Override
    VehicleType getVehicle(String y) {
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
