package components;


/**
 * Singleton class of big brohter
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 */
public class BigBrother {
  //  private Vehicle vehicle;
    private static volatile BigBrother instance=null;
    private BigBrother(){
    }
    public static BigBrother getInstance(){
        if(instance==null){
            synchronized (BigBrother.class) {
                if(instance==null)
                instance = new BigBrother();
            }
        }
        return instance;
    }
    public static void setInstance(BigBrother instance) {
        BigBrother.instance = instance;
    }

    /**
     * check if the vehicle drove in the allowed speed
     * @param vehicle
     * @return
     */
    public void isSpeedLegal(Vehicle vehicle){
        if(vehicle.getLastRoad().getMaxSpeed() < vehicle.getVehicleType().getAverageSpeed()){
            Moked.WriteReport(vehicle);
        }
    }
}
