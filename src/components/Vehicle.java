package components;

import utilities.Utilities;
import utilities.VehicleType;
import utilities.Timer;
import java.util.Random;

/**
 * Class represent Vehicle on the map.
 *
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 * @see VehicleType
 * @see Route
 * @see RouteParts
 * @see Road
 */
public class Vehicle implements Utilities,Timer {
    private int id;
    private VehicleType vehicleType;
    private Route currentRoute;
    private RouteParts currentRouteParts;// The current type of route part when vehicle locating.
    private int timeFromStartRoute; //time in pulses from the start
    private int timeOnCurrentPart; //time in pulses from the checkIn at this part
    private static int objectCount=1; //Counter to num of objects
    private Road lastRoad; //Last road which car drove in or driving currently
    private String status=null; //Keep the status,will be in use for prints.

    /**
     * Vehicle randomal constructor.
     *
     * @param road
     */
    public Vehicle(Road road){
        id=objectCount;
        objectCount++;
        Random rand=new Random();
        vehicleType=VehicleType.values()[rand.nextInt(VehicleType.values().length)];//Randomise car type
        timeFromStartRoute=0;
        timeOnCurrentPart=0;
        lastRoad=road;
        currentRoute=new Route(road,this);
    }

    //getters
    public int getId(){return id;}
    public VehicleType getVehicleType(){return vehicleType;}
    public Route getCurrentRoute(){return currentRoute;}
    public RouteParts getCurrentRouteParts(){return currentRouteParts;}
    public int getTimeFromStartRoute(){return timeFromStartRoute;}
    public int getTimeOnCurrentPart(){return timeOnCurrentPart;}
    public int getObjectCount(){return objectCount;}
    public Road getLastRoad(){return lastRoad;}
    public String getStatus(){return status;}

    //setters
    public void setId(int id){this.id=id;}
    public void setVehicleType(VehicleType v){vehicleType=v;}
    public void setCurrentRoute(Route current){currentRoute=current;}
    public void setCurrentRouteParts(RouteParts rp){currentRouteParts=rp;}
    public void setTimeFromStartRoute(int time){timeFromStartRoute=time;}
    public void setTimeOnCurrentPart(int time){timeOnCurrentPart=time;}
    public void setObjectCount(int oc){objectCount=oc;}
    public void setLastRoad(Road r){lastRoad=r;}
    public void setStatus(String s){status=s;}

    /**
     * Made check out if car can finish the current part and then checkIn to the next part.
     * else-stay at the part
     */
    public void move(){
        if(currentRouteParts.canLeave(this)){
            currentRouteParts.checkOut(this);
            //TODO:Something with route parts depend on the route probably.
            currentRouteParts.checkIn(this);
        }
    }

    /**
     * Advance values of timeFromStartRoute and timeOnCurrentPart
     * call move() function.
     */
    public void incrementDrivingTime(){
        //Advance time in the route
        timeFromStartRoute+=1;
        //Advance time in current route part
        timeOnCurrentPart+=1;
        //Execute movement
        move();
    }

    public String toString(){
        return "Vehicle " +  vehicleType.name() + ", average speed: "+ vehicleType.getAverageSpeed();
    }

    public boolean equals(Object o) {
        if(o instanceof Vehicle){
            return vehicleType.equals(((Vehicle) o).vehicleType) &&
                    currentRoute.equals(((Vehicle) o).currentRoute) &&
                    currentRouteParts.equals(((Vehicle) o).currentRouteParts) &&
                    timeOnCurrentPart==((Vehicle) o).timeOnCurrentPart &&
                    timeFromStartRoute==((Vehicle) o).timeFromStartRoute &&
                    lastRoad.equals(((Vehicle) o).lastRoad) &&
                    status.equals(((Vehicle) o).status);
        }
        return false;
    }


}
