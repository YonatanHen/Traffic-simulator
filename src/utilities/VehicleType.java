
package utilities;

/**
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 */
public enum VehicleType {
    car(90), bus(60), bicycle(40), motorcycle(120), truck(80),
    tram(50), semitrailer(85);
    private int averageSpeed;

    /**
     * Function set to average speed value.
     * @param speed
     */
    VehicleType(int speed) {
        averageSpeed=speed;
    }
    public int getAverageSpeed() {
        return averageSpeed;
    }
}