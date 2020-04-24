package components;

import utilities.Timer;
import utilities.Utilities;

import java.util.ArrayList;

/**
 * Class simulate driving of number of vehicles based on the map.
 *
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 * @see Timer
 * @see Utilities
 * @see Map
 * @see Vehicle
 */
public class Driving implements Utilities, Timer {
    private Map map; //Map for current running
    private ArrayList<Vehicle> vehicles; //The vehicles who part of the running
    private int drivingTime; // Accumulate the time/number of pulses from the start
    private ArrayList<Timer> allTimedElements; //Keep the whole elements who affected by the time pulses.

    /**
     * Driving constructor: receive number of junctions and
     * number of vehicles.
     * The constructor make new map with existed number of junctions,make number of
     * vehicles as demanded and initialize all of the fields.
     *
     * @param numOfJunctions
     * @param numOfVehicles
     */
    Driving(int numOfJunctions,int numOfVehicles){
        vehicles=new ArrayList<Vehicle>(numOfVehicles);
        map=new Map(numOfJunctions); //TODO:class Map
        drivingTime=0;
        ArrayList<Timer> allTimedElements=new ArrayList<>();
    }

    /**
     * Receive number of turns and run incrementDrivingTime function for each.
     *
     * @param numOfTurns
     */
    public void drive(int numOfTurns){
    //TODO:Maybe need wider implementation
        for(int i=0;i<numOfTurns;i++){
            incrementDrivingTime();
        }
    }

    /**
     * Advance the pulses for Objects who affect by that.
     */
    public void incrementDrivingTime(){
        //TODO:Implement
    }

    /*public String toString(){
        //TODO:check if do we need this or not
        return "=====================START DRIVING====================";
    }*/


    public boolean equals(Object o){
        if(o instanceof Driving){
            return map.equals((Driving)o).map &&
            vehicles.equals(((Driving) o).vehicles) &&
            drivingTime==((Driving) o).drivingTime &&
            allTimedElements==((Driving) o).allTimedElements;
        }
        return false;
    }
}
