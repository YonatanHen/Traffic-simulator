package components;

import utilities.Point;

import java.util.ArrayList;

/**
 * Class represent Junction on the map.
 *
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-
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
     * In case of junction,check if a vehicle can cross the junction and leave her
     *
     * @param vehicle
     * @return
     */
    public boolean canLeave(Vehicle vehicle){
    //TODO:
    }
}
