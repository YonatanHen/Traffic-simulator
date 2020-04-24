package components;

import org.w3c.dom.ls.LSOutput;
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
    private int objectCount=0; //count of objects
    private int delay=0; //Delay time (in pulses) that passes between each traffic light switch. The lag time is 0 when the traffic light is off and momentarily
    //This time light illuminator is magnified and ranges from 2 to 6
    private int greenLightIndex; //index of the road in entering roads green light is currently on.
    private int id;
    private static int minDelay=2; //min value for delay time =2.
    private static int maxDelay=6; // max value for delay time =6.
    private ArrayList<Road> roads; //entering roads of junction where the traffic light is located.
    private boolean trafficLightsOn; //TODO: its green/red or have traffic light in current junction
    private int workingTime; // count of pulses when the traffic light on.

    /**
     * constructor TrafficLights
     * @param roads
     */

    TrafficLights(ArrayList<Road> roads){
        this.roads = new ArrayList<Road>();
        this.roads.addAll(roads);
        this.delay=0;
        this.objectCount++;
    }
    public abstract void changeIndex();

    /**
     * function that change to next junction to green and make sure that other junction with red light
     */
    public void changeLights(){
        for(int i=0;i<roads.size();i++){
            if(i==greenLightIndex)
                roads.get(i + 1).setGreenlight(true);
            else
                roads.get(i).setGreenlight(false);
        }
        System.out.println();
    }

    public void incrementDrivingTime(){
        greenLightIndex++;//TODO : check if need to change lights

    }
    //setters
    public void setDelay(int delay) { this.delay = delay; }
    public void setGreenLightIndex(int greenLightIndex) { this.greenLightIndex = greenLightIndex; }
    public void setId(int id) { this.id = id; }
    public void setTrafficLightsOn(boolean trafficLightsOn) { this.trafficLightsOn = trafficLightsOn; }
    public void setObjectCount(int objectCount) { this.objectCount = objectCount; }
    public void setRoads(ArrayList<Road> roads) { this.roads = roads; }
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
}
