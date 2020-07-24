package components;

import utilities.Utilities;


/**
 * @author Yehonatan Hen
 * @author Rotem Librati
 */
public interface RouteParts extends Utilities {
    double calcEstimatedTime(Object obj);
    boolean canLeave(Vehicle vehicle);
    void checkIn(Vehicle vehicle);
    void checkOut(Vehicle vehicle);
    RouteParts findNextPart(Vehicle vehicle);
    void stayOnCurrentPart(Vehicle vehicle);
}
