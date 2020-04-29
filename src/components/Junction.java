package components;

import utilities.Point;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class represent Junction on the map.
 *
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 * @see Road
 */
public class Junction extends Point implements RouteParts {
    private static int objectsCount=1; //Count the number of created objects,initialized by the value of 1.
    private ArrayList<Road>enteringRoads; //list of entering Roads to junction
    private ArrayList<Road> exitingRoads; //list of exiting Roads to junction
    private String junctionName; //Name of the object.


    /**
     * Randomal constructor for Junction
     */
    Junction(){
        super(); //Call the Point default constructor
        junctionName=String.valueOf(objectsCount);
        objectsCount++;//Advance the counter after using his value
        enteringRoads=new ArrayList<Road>();
        exitingRoads=new ArrayList<Road>();
        super.successMessage(toString());
    }

    /**
     * Constructor of Junction with existing parameters.
     * @param junctionName
     * @param x
     * @param y
     */
    Junction(String junctionName,double x,double y){
        super(x,y);//Call point constructor
        this.junctionName=junctionName;
        objectsCount++;//Advance the counter but not using his value this time.
        enteringRoads=new ArrayList<Road>();
        exitingRoads=new ArrayList<Road>();
    }

    //getters
    public int getObjectsCount(){return objectsCount;}
    public ArrayList<Road> getEnteringRoads(){return enteringRoads;}
    public ArrayList<Road> getExitingRoads(){return exitingRoads;}
    public String getJunctionName(){return junctionName;}

    //setters
    public void setEnteringRoads(ArrayList<Road> er){
        enteringRoads.clear();
        enteringRoads.addAll(er);
    }
    public void setExitingRoads(ArrayList<Road> er){
        exitingRoads.clear();
        exitingRoads.addAll(er);
    }
    public void setJunctionName(String name){junctionName=name;}

    /**
     * Add new entering road to array list
     * @param road
     */
    public void addEnteringRoad(Road road){
        enteringRoads.add(road);
    }

    /**
     * Add new exiting road to array list
     * @param road
     */
    public void addExitingRoad(Road road){
        exitingRoads.add(road);
    }

    /**
     * Function receive as an argument, instance of Vehicle.
     * Function calculate and return the time that Vehicle needs to wait until
     * he will pass the junction. estimated time calculated by amount of roads with higher
     * priority relatively to the road that vehicle arrived from. higher priority depends on
     * the index of the road in the entering roads array list. as the index smaller,the priority is higher.
     *
     * @param obj
     * @return amount of entering roads with higher priorty then the road that car came from plus 1.
     */
    public double calcEstimatedTime(Object obj){
        double counter=1;
        if(obj instanceof Vehicle) {
            //Advance the counter while the road isn't appears in the entering roads array list.
            for (int i = 0; i < enteringRoads.size(); i++) {
                if(((Vehicle) obj).getLastRoad().equals(enteringRoads.get(i))) break;
                else counter++;
            }
        }
        return counter;
    }

    /**
     * In case of junction,check if a vehicle can cross the junction and leave
     * @param vehicle
     * @return true if car can leave the junction without interference, else false.
     */
    public boolean canLeave(Vehicle vehicle){
        return checkAvailability(vehicle);
    }

    /**
     * Utility method for canLeave, check if exist exiting roads to the junction and check
     * the last road that vehicle came from in that way: if vehicle isn't the first in the waiting list,
     * he would have to wait until cars before him will exit the junction.
     *
     * @param vehicle
     * @return true when vehicle is first in the entering roads list,else false.
     */
    public boolean checkAvailability(Vehicle vehicle){
     if(exitingRoads.size()>0 &&
     vehicle.getCurrentRoutePart().findNextPart(vehicle)!=null &&
     ((Road) vehicle.getCurrentRoutePart().findNextPart(vehicle)).getWaitingVehicles().size() > 0) return true;
     return false;
    }

    /**
     * Method writes the car in the junction and update all relevant data fields.
     * Finally,prints a message.
     *
     * @param vehicle
     */
    public void checkIn(Vehicle vehicle) {
        //The last road suppose to be the road from the last end junction and to the current junction
        for(Road r:enteringRoads){
            if (r.getEndJunction().equals(this) && r.getStartJunction().equals(vehicle.getLastRoad().getEndJunction())){
                vehicle.setLastRoad(r);
            }
        }
        vehicle.setCurrentRoutePart(this);
        vehicle.setStatus("- has arrived to "+ toString());
        System.out.println(vehicle.getStatus());
    }

    /**
     * Method executed when car allow to cross and leave the junction,
     * update in relevant data fields would change.
     *
     * @param vehicle
     */
    public void checkOut(Vehicle vehicle){
        if(canLeave(vehicle)) {
            vehicle.setStatus("- has left " + toString());
            System.out.println(vehicle.getStatus());
        }
    }

    /**
     * Searching for the next part in the route, check if there are existing exit roads that allow
     * driving in the specific type of car and return randomal road from resulted roads.
     * if road not found,return null
     *
     * @param vehicle
     * @return randomal road that match to the type of car or null
     */
    public RouteParts findNextPart(Vehicle vehicle){
        ArrayList<Road> enabledAndAllowed=new ArrayList<>();
       //Next part must to be a Road
        for(Road r:exitingRoads){
            if(r.getEnable()){
                for(int i=0;i<r.getVehicleTypes().length;i++) {
                    if (vehicle.getVehicleType().equals(r.getVehicleTypes()[i])) {
                        enabledAndAllowed.add(r);
                        break;
                    }
                }
            }
        }
        if(enabledAndAllowed.size()==0) return null;
        Random rand=new Random();
        return enabledAndAllowed.get(rand.nextInt(enabledAndAllowed.size()));
    }

    /**
     * Executed when car can't move to the next part of the route.
     *
     * @param vehicle
     */
    public void stayOnCurrentPart(Vehicle vehicle){
        vehicle.setStatus("- is waiting at "+ toString()+"- there are previous cars on the same road.");
        System.out.println(vehicle.getStatus());
    }

    public String toString(){
        return  "Junction " + junctionName;
    }

    public boolean equals(Object o){
        if(o instanceof Junction){
            return ((Junction) o).exitingRoads==exitingRoads &&
                    ((Junction) o).enteringRoads==enteringRoads &&
                    ((Junction)o).junctionName.equals(junctionName);
        }
        return false;
    }
}
