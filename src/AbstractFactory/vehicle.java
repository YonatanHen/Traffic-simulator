package AbstractFactory;

import utilities.VehicleType;

abstract class vehicle {
    VehicleType type=null;
    vehicle(){}
    abstract public VehicleType getVehicle(String y);
}
