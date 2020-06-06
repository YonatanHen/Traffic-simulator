
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
     * Function set average speed value.
     * @param averageSpeed
     */
    VehicleType(int averageSpeed) {
        this.averageSpeed = averageSpeed;
    }
    public void setAverageSpeedForCity(int averageSpeed){
        Random random = new Random();
        boolean speed = random.nextBoolean();
        if(speed)
            this.averageSpeed=(int)(averageSpeed*1.3);
        else
            this.averageSpeed=averageSpeed;
    }
    public void setAverageSpeedReports(int averageSpeed){
        Random random = new Random();
        boolean speed = random.nextBoolean();
        if(speed)
            this.averageSpeed=(int)(averageSpeed*1.5);
        else
            this.averageSpeed=averageSpeed;
    }

    public int getAverageSpeed() {
        return averageSpeed/10;
    }
    public void setAverageSpeed(int averageSpeed){this.averageSpeed = averageSpeed;}
}