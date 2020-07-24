package AbstractFactory;

import components.Vehicle;
import utilities.VehicleType;


/**
 * Vehicle abstract class. part of the design pattern implementation.
 * @author Yehonatan Hen
 * @author Rotem Librati
 */
abstract class vehicle {
    VehicleType type=null;
    vehicle(){}
    abstract public VehicleType getVehicle(String y);
}
