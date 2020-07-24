package components;


/**
 * Singleton class of big brohter
 * @author Yehonatan Hen
 * @author Rotem Librati
 */
public class BigBrother {
    private static volatile BigBrother instance=null;
    private Moked moked;
    private BigBrother(){
        moked=new Moked();
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

    public Moked getMoked() {
        return moked;
    }

    /**
     * check if the vehicle drove in the allowed speed
     * @param vehicle
     * @return
     */
    public void isSpeedLegal(Vehicle vehicle){
        if(vehicle.getLastRoad().getMaxSpeed() < vehicle.getVehicleType().getAverageSpeed()){
            moked.put(vehicle);
        }
    }
}
