package AbstractFactory;

import components.Vehicle;
import utilities.VehicleType;


/**
 * Vehicle abstract class. part of the design pattern implementation.
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 */
abstract class vehicle {
    VehicleType type=null;
    vehicle(){}
    abstract public VehicleType getVehicle(String y);
}
