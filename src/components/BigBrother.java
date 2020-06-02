package components;

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
    public boolean calcSpeed(Vehicle vehicle){
        if(vehicle.getLastRoad().getMaxSpeed() < vehicle.getVehicleType().getAverageSpeed())
            return true;
        return false;
    }
}
