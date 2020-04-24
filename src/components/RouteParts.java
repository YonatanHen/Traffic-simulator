package components;

import utilities.Utilities;


/**
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 */
public interface RouteParts extends Utilities {
    double calcEstimatedTime(Object obj);
    boolean canLeave(Vehicle vehicle);
    void checkIn(Vehicle vehicle);
    void checkOut(Vehicle vehicle);
    RouteParts findNextPart(Vehicle vehicle);
    void stayOnCurrentPart(Vehicle vehicle);
}
