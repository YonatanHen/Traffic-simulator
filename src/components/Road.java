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
    private static final int [] allowedSpeedOptions = {30,40,50,55,60,70,80,90};//array of allowed speed in road
    private boolean enable; //if true, the road appear on the map.
    private Junction startJunction; //The junction from which the road comes out
    private Junction endJunction; //The junction which the road enters
    private boolean greenlight; //variable for green light to this road, update when the light is change.
    private double length; //length of road, calculate as distance between 2 junction with distance formula between two points.
    private int maxSpeed; // allowed max speed. random from list of allowed values.
    private VehicleType[] vehicleTypes;//array types of cars that allowed in this road.
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
        //Make vehicleTypes array based on randomal values-array size and types both.
        VehicleType [] vehicleTypes=new VehicleType[random.nextInt(VehicleType.values().length)];
        for(int i=0;i<vehicleTypes.length;i++){
            VehicleType newVal=VehicleType.values()[random.nextInt(vehicleTypes.length)];
            for (int j=0;j<i;){
                //Iterate over the same j index if the randomised value equals to composed value in the array.
                //Randomise new value
                if (newVal.equals(vehicleTypes[j])) newVal = VehicleType.values()[random.nextInt(vehicleTypes.length)];
                //Advance j when value isn't equal
                else j++;
            }
            //Applying of the new value in the array if he does'nt exist already.
            vehicleTypes[i]=newVal;
        }
        startJunction.addExitingRoad(this);
        endJunction.addEnteringRoad(this);
        this.waitingVehicles = new ArrayList<>();
        successMessage(toString());
    }
    //getters
    public boolean getGreenLight(){return greenlight;}
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
    public void setStartJunction(Junction startJunction) { this.startJunction = startJunction; }
    public void setVehicleTypes(VehicleType[] vehicleTypes) { this.vehicleTypes = vehicleTypes; }
    public void setWaitingVehicles(ArrayList<Vehicle> waitingVehicles) { this.waitingVehicles = waitingVehicles; }


    /**
     * function that add car to waiting list.
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
     *Method check if a vehicle can finish his drive in current road.
     * @param vehicle
     * @return true when delay time at this part equal/bigger then estimated time that vehicle suppose to pass this part.
     */
    public boolean canLeave(Vehicle vehicle){
        return vehicle.getTimeOnCurrentPart()>=calcEstimatedTime(vehicle);
    }

    /**
     *  Method add the vehicle to this road and update all relevant data fields.
     *
     * @param vehicle
     */
    public void checkIn(Vehicle vehicle){
        vehicle.setCurrentRoutePart(this);
        vehicle.setLastRoad(this);
        vehicle.setStatus("- is starting to move on " +  toString() + ", " + vehicle.toString());
        System.out.println(vehicle.getStatus());
    }

    /**
     * Method "release" the car fron current road. upadte all relevant data fields.
     *
     * @param vehicle
     */
    public void checkOut(Vehicle vehicle){
        removeVehicleFromWaitingVehicles(vehicle);
        vehicle.setStatus("- has finished " + toString() + ", " + vehicle.toString());
        System.out.println(vehicle.getStatus());
    }

    /**
     * return last junction of this road.
     *
     * @param vehicle
     * @return the junction when road finish as RoutePart! (not Junction).
     */
    public RouteParts findNextPart(Vehicle vehicle){
        return endJunction;
    }

    public void stayOnCurrentPart(Vehicle vehicle) {
        //- is still moving on Road from Junction 10 to Junction 5 (Lighted), length: 526, max speed 30, time to arrive: 13.0
        vehicle.setStatus("- is still moving on" + toString() + ", " + vehicle.toString());
        System.out.println(vehicle.getStatus());
    }

    /**
     * remove car from the list of waiting car in junction
     * @param vehicle
     */
    public void removeVehicleFromWaitingVehicles(Vehicle vehicle) {
        for (int i = 0; i < waitingVehicles.size(); i++) {
            if (waitingVehicles.get(i).equals(vehicle))
                waitingVehicles.remove(i);
        }
    }

    public String toString(){
        //Assume that implementation is correct,maybe need to fix this later.
        return "Road from "+ startJunction + " to " + endJunction;
    }

    public boolean equals(Object obj){
        if(obj instanceof Road){
            return ((Road)obj).allowedSpeedOptions.equals(allowedSpeedOptions) &&
                    ((Road) obj).enable==enable &&
                    ((Road) obj).startJunction.equals(startJunction) &&
                    ((Road) obj).endJunction.equals(endJunction) &&
                    ((Road) obj).greenlight==greenlight &&
                    ((Road) obj).length==length &&
                    ((Road) obj).maxSpeed == maxSpeed &&
                    ((Road) obj).vehicleTypes.equals(vehicleTypes) &&
                    ((Road) obj).waitingVehicles.equals(waitingVehicles);
        }
        return  false;
    }

}
