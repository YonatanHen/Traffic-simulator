package components;

import utilities.Utilities;
import utilities.VehicleType;
import utilities.Timer;

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
public class Vehicle extends Thread implements Utilities,Timer{
    private int id;
    private VehicleType vehicleType;
    private Route currentRoute;
    private RouteParts currentRoutePart;// The current type of route part when vehicle locating.
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
        vehicleType=VehicleType.values()[getRandomInt(0,VehicleType.values().length)];//Randomise car type
        timeFromStartRoute=0;
        timeOnCurrentPart=0;
        lastRoad=road;
        lastRoad.addVehicleToWaitingVehicles(this);
        currentRoute=new Route(road,this);
        currentRoute.checkIn(this);
        currentRoutePart=currentRoute.getRouteParts().get(0);
        successMessage(toString());
        System.out.println(status);
        currentRoutePart.checkIn(this);
        objectCount++;
    }

    //getters
    public int getid(){return id;}
    public VehicleType getVehicleType(){return vehicleType;}
    public Route getCurrentRoute(){return currentRoute;}
    public RouteParts getCurrentRoutePart(){return currentRoutePart;}
    public int getTimeFromStartRoute(){return timeFromStartRoute;}
    public int getTimeOnCurrentPart(){return timeOnCurrentPart;}
    public int getObjectCount(){return objectCount;}
    public Road getLastRoad(){return lastRoad;}
    public String getStatus(){return status;}

    //setters
    public void setId(final int id){this.id=id;}
    public void setVehicleType(VehicleType v){vehicleType=v;}
    public void setCurrentRoute(Route current){currentRoute=current;}
    public void setCurrentRoutePart(RouteParts rp){currentRoutePart=rp;}
    public void setTimeFromStartRoute(final int time){timeFromStartRoute=time;}
    public void setTimeOnCurrentPart(final int time){timeOnCurrentPart=time;}
    public void setObjectCount(final int oc){objectCount=oc;}
    public void setLastRoad(Road r){lastRoad=r;}
    public void setStatus(final String s){status=s;}

    /**
     * Made check out if car can finish the current part and then checkIn to the next part.
     * else-stay at the part
     */
    public void move(){
        try {
            sleep(vehicleType.getAverageSpeed() * 10);
        }catch (InterruptedException err){}
        if (currentRoutePart.canLeave(this)) {
            currentRoutePart.checkOut(this);
            if (currentRoutePart.findNextPart(this) != null) {
                currentRoutePart = currentRoutePart.findNextPart(this);
                currentRoutePart.checkIn(this);
            }
        } else currentRoute.stayOnCurrentPart(this);
        if (currentRoute.canLeave(this)) {
            currentRoute.checkOut(this);
            currentRoutePart = currentRoute.findNextPart(this);
            currentRoute.checkIn(this);
            currentRoutePart.checkIn(this);
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
        return "Vehicle " + objectCount+ ": "+  vehicleType.name() + ", average speed: "+ vehicleType.getAverageSpeed();
    }

    public boolean equals(Object o) {
        if(o instanceof Vehicle){
            return vehicleType.equals(((Vehicle) o).vehicleType) &&
                    currentRoutePart.equals(((Vehicle) o).currentRoutePart) &&
                    timeOnCurrentPart==((Vehicle) o).timeOnCurrentPart &&
                    timeFromStartRoute==((Vehicle) o).timeFromStartRoute &&
                    lastRoad.equals(((Vehicle) o).lastRoad) &&
                    status.equals(((Vehicle) o).status);
        }
        return false;
    }


}
