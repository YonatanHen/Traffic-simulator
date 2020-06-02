
package utilities;

import java.util.Random;

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
     * @param averageSpeed
     */
    VehicleType(int averageSpeed) {
        Random random = new Random();
        boolean speed = random.nextBoolean();
        if(speed)
            this.averageSpeed=(int)(averageSpeed*1.5);
        else
            this.averageSpeed = averageSpeed;
    }
    public int getAverageSpeed() {
        return averageSpeed/10;
    }
}