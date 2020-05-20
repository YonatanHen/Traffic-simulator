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
public abstract class TrafficLights extends Thread implements Timer, Utilities {
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
        delay=getRandomInt(minDelay,maxDelay+1)*100;
        trafficLightsOn=false;
        objectCount++;
        id=objectCount;
        workingTime=0;
    }

    public abstract void changeIndex();

    /**
     * function that change next junction to green and make sure that other junction with red light
     */
    public void changeLights() {
        for (int i = 0; i < roads.size(); i++) {
            roads.get(i).setGreenlight(false);
        }
        changeIndex();
        roads.get(getGreenLightIndex()).setGreenlight(true);
        trafficLightsOn = true;
        System.out.println("-" + roads.get(getGreenLightIndex()) + ": green light.");
            //Change delay time-happens when new light turn on.
            delay = (getRandomInt(minDelay, maxDelay + 1)) * 100;
    }

    /**
     * Method check if it's time to change lights by advancing the index of light working time.
     */
    public synchronized void incrementDrivingTime(){
        workingTime++;
        if(workingTime>=delay){
            changeLights();
            workingTime = 0;
        }
        else {
            trafficLightsOn=false;
            System.out.println(this+ "\n- on delay");
        }
    }
    //setters
    public void setDelay(final int delay) { this.delay = delay; }
    public void setGreenLightIndex(final int greenLightIndex) { this.greenLightIndex = greenLightIndex; }
    public void setId(final int id) { this.id = id; }
    public void setTrafficLightsOn(final boolean trafficLightsOn) { this.trafficLightsOn = trafficLightsOn; }
    public void setRoads(ArrayList<Road> roads) {
        this.roads.clear();
        this.roads.addAll(roads);
    }
    public void setWorkingTime(int workingTime) { this.workingTime = workingTime; }

    //getters
    public boolean getTrafficLightsOn(){return trafficLightsOn;}
    public int getDelay() { return delay; }
    public int getid() { return id; }
    public static int getMaxDelay() { return maxDelay; }
    public static int getMinDelay() { return minDelay; }
    public ArrayList<Road> getRoads() { return roads; }
    public int getGreenLightIndex() { return greenLightIndex; }
    public int getObjectCount() { return objectCount; }
    public int getWorkingTime() { return workingTime; }


    public String toString() {
        return "traffic lights "+ id;
    }

    public boolean equals(Object o){
        if(o instanceof TrafficLights){
            return ((TrafficLights) o).delay == delay &&
                    ((TrafficLights) o).trafficLightsOn == trafficLightsOn &&
                    ((TrafficLights) o).id == id &&
                    ((TrafficLights) o).greenLightIndex == greenLightIndex &&
                    ((TrafficLights) o).roads == roads &&
                    ((TrafficLights) o).workingTime == workingTime;
        }
        return false;
    }
}
