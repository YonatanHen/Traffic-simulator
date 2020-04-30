package components;

import utilities.Timer;
import utilities.Utilities;
import java.util.ArrayList;

/**
 * Class represent TrafficLights in the junction.
 *
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 * @see Road
 */
public abstract class TrafficLights implements Timer, Utilities {
    private static int objectCount=0; //count the objects
    private int delay=0; //Delay time (in pulses) that passes between each traffic light switch.
    // The delay time is 0 when the traffic light is off and momentarily
    //This time light illuminator is magnified and ranges from 2 to 6
    private int greenLightIndex; //index of the road in entering roads when green light is currently on.
    private int id;
    private static final int minDelay=2; //min value for delay time =2.
    private static final int maxDelay=6; // max value for delay time =6.
    private ArrayList<Road> roads; //entering roads of junction where the traffic light is located.
    private boolean trafficLightsOn; //Traffic light on/off
    private int workingTime; // count of pulses when the traffic light on.

    /**
     * constructor TrafficLights
     * @param roads
     */

    TrafficLights(ArrayList<Road> roads){
        this.roads = new ArrayList<>();
        this.roads.addAll(roads);
        //Initialize green light index
        for(Road r:roads){
            if(r.getGreenLight()){
                greenLightIndex=roads.indexOf(r);
                break;
            }
        }
        //Make random value to traffic light delay.
        delay=getRandomInt(minDelay,maxDelay+1);
        trafficLightsOn=false;
        objectCount++;
        id=objectCount;
        workingTime=0;
    }

    public abstract void changeIndex();

    /**
     * function that change next junction to green and make sure that other junction with red light
     */
    public void changeLights(){
        changeIndex();
        for(int i=0;i<roads.size();i++){
            if(i==greenLightIndex){
                roads.get(i).setGreenlight(true);
                //Keep the index of the green light to implement it on green light index variable later
                System.out.println(roads.get(i)+": green light.");
            }
            else roads.get(i).setGreenlight(false);
        }
        //Change delay time-happens when new light turn on.
        delay=getRandomInt(minDelay,maxDelay+1);
        //Initialize the working time of the new light who turns on to 0.
        workingTime=0;
    }

    /**
     * Method check if it's time to change lights by advancing the index of light working time.
     */
    public void incrementDrivingTime(){
        workingTime++;
        if(workingTime>=delay) changeLights();
    }
    //setters
    public void setDelay(int delay) { this.delay = delay; }
    public void setGreenLightIndex(int greenLightIndex) { this.greenLightIndex = greenLightIndex; }
    public void setId(int id) { this.id = id; }
    public void setTrafficLightsOn(boolean trafficLightsOn) { this.trafficLightsOn = trafficLightsOn; }
    public void setObjectCount(int objectCount) { this.objectCount = objectCount; }
    public void setRoads(ArrayList<Road> roads) {
        this.roads.clear();
        this.roads.addAll(roads);
    }
    public void setWorkingTime(int workingTime) { this.workingTime = workingTime; }

    //getters
    public boolean getTrafficLightsOn(){return trafficLightsOn;}
    public int getDelay() { return delay; }
    public int getId() { return id; }
    public static int getMaxDelay() { return maxDelay; }
    public static int getMinDelay() { return minDelay; }
    public ArrayList<Road> getRoads() { return roads; }
    public int getGreenLightIndex() { return greenLightIndex; }
    public int getObjectCount() { return objectCount; }
    public int getWorkingTime() { return workingTime; }


    public String toString() {
        return "traffic lights"+ objectCount;
    }

    public boolean equals(Object o){
        if(o instanceof TrafficLights){
            return ((TrafficLights) o).delay == delay &&
                    ((TrafficLights) o).trafficLightsOn == trafficLightsOn &&
                    ((TrafficLights) o).id == id &&
                    ((TrafficLights) o).objectCount == objectCount &&
                    ((TrafficLights) o).greenLightIndex == greenLightIndex &&
                    ((TrafficLights) o).roads == roads &&
                    ((TrafficLights) o).workingTime == workingTime;
        }
        return false;
    }
}
