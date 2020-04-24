package components;

import utilities.Utilities;
import utilities.VehicleType;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Random;
/**
 * Class represent Road on the map.
 *
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 * @see Junction
 * @see RouteParts
 * @see Utilities
 * @see VehicleType
 * @see Vehicle
 */

public class Road implements RouteParts, Utilities {
    private static int [] allowedSpeedOptions = {30,40,50,55,60,70,80,90};//array of allowed speed in road
    private boolean enable; //if true, the road appear on the map.
    private Junction startJunction; //The junction from which the road comes out
    private Junction endJunction; //The junction which the road enters
    private boolean greenlight; //variable fro green light to this road, update when the light is change.
    private double length; //length of road, calculate as distance between 2 junction with distance formula between two points.
    private int maxSpeed; // allowed max speed. random from list of allowed values.
    private VehicleType[] vehicleTypes;//array kinds of cars that allowed in this road.
    private ArrayList<Vehicle> waitingVehicles; //list of car that finish the driving in this road and wait to pass the junction

    /**
     * constructor Road
     * @param start
     * @param end
     */
    public Road(Junction start, Junction end){
        Random random = new Random();
        this.enable=random.nextBoolean();
        this.startJunction=start;
        this.endJunction=end;
        this.greenlight=random.nextBoolean();
        this.length=calcLength();
        this.maxSpeed= allowedSpeedOptions[random.nextInt(allowedSpeedOptions.length)];
        VehicleType [] arr = {};//TODO: complete
        this.waitingVehicles = new ArrayList<>();
    }
    //getters
    public boolean getEnable(){return enable;}
    public int getMaxSpeed() {return getMaxSpeed();}
    public double getLength() { return length;}
    public Junction getEndJunction() { return endJunction; }
    public Junction getStartJunction() {return startJunction; }
    public ArrayList<Vehicle> getWaitingVehicles() {return waitingVehicles; }
    public VehicleType[] getVehicleTypes() {return vehicleTypes; }
    public static int[] getAllowedSpeedOptions() {return allowedSpeedOptions; }

    //setters
    public void setLength(double length) {this.length = length; }
    public void setEnable(boolean enable) { this.enable = enable; }
    public void setMaxSpeed(int maxSpeed) { this.maxSpeed = maxSpeed; }
    public void setEndJunction(Junction endJunction) { this.endJunction = endJunction; }
    public void setGreenlight(boolean greenlight) {this.greenlight = greenlight; }
    public static void setAllowedSpeedOptions(int[] allowedSpeedOptions) { Road.allowedSpeedOptions = allowedSpeedOptions; }
    public void setStartJunction(Junction startJunction) { this.startJunction = startJunction; }
    public void setVehicleTypes(VehicleType[] vehicleTypes) { this.vehicleTypes = vehicleTypes; }
    public void setWaitingVehicles(ArrayList<Vehicle> waitingVehicles) { this.waitingVehicles = waitingVehicles; }


    /**
     * function that add car to list of waiting
     * @param vehicle
     */
    public void addVehicleToWaitingVehicles(Vehicle vehicle){
        waitingVehicles.add(vehicle);
    }

    /**
     * function that calculate the time that car pass the current road.
     * @param obj
     * @return calculate value round to up.
     */
    public double calcEstimatedTime(Object obj){
        if(obj instanceof VehicleType)
            return Math.round(length/ Math.min(maxSpeed,((VehicleType) obj).getAverageSpeed()));
        return 0;
    }

    /**
     *function that calculate the length between two points with formula.
     * @return distance .
     */
    public double calcLength(){ //calc the length between two points
        return Math.sqrt(Math.pow(startJunction.getX()-endJunction.getX(),2)
                +Math.pow(startJunction.getY()-endJunction.getY(),2));

    }

    /**
     *
     * @param vehicle
     * @return
     */
    public boolean canLeave(Vehicle vehicle){ //TODO:
        return false;
    }

    /**
     *
     * @param vehicle
     */
    public void checkIn(Vehicle vehicle){ //TODO : i dont know if this true.
        vehicle = new Vehicle(new Road(startJunction,endJunction));

        System.out.println(" has arrived to" + startJunction);
    }

    /**
     *
     * @param vehicle
     */
    public void checkOut(Vehicle vehicle){
        removeVehicleFromWaitingVehicles(vehicle);
        System.out.println("The vehicle " + vehicle.getId() + " pass the junction");
    }

    public RouteParts findNextPart(Vehicle vehicle){// return last junction of this road.
        return vehicle.getLastRoad();
    }

    @Override
    public void stayOnCurrentPart(Vehicle vehicle) {

    }

    /**
     * remove car from the list of waiting car in junction
     * @param vehicle
     */
    public void removeVehicleFromWaitingVehicles(Vehicle vehicle){
        for(int i=0;i<waitingVehicles.size();i++){
            if(waitingVehicles.get(i).equals(vehicle))
                waitingVehicles.remove(i);
        }
        //TODO: print
    }

    /**
     * print if in current pulse the car stays in this road
     * @param vehicle
     */
    public void setOnCurrentPart(Vehicle vehicle){
        if(vehicle.getLastRoad().getEndJunction()!=endJunction)
            System.out.println(" is still moving on ");

    }
}
