package AbstractFactory;

import utilities.VehicleType;

abstract class vehicle {
    VehicleType type=null;
    vehicle(){}
    abstract VehicleType getVehicle(String y);
}
