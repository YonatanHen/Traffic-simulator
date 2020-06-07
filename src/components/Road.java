package components;

import utilities.Utilities;
import utilities.VehicleType;
import java.util.ArrayList;
import java.util.Arrays;

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
        this.enable=getRandomBoolean();
        this.startJunction=start;
        this.endJunction=end;
        this.greenlight=getRandomBoolean();
        this.length=calcLength();
        this.maxSpeed= allowedSpeedOptions[getRandomInt(0,allowedSpeedOptions.length)];
        //Make vehicleTypes array based on randomal values-array size and types both.
        vehicleTypes=new VehicleType[getRandomInt(1,VehicleType.values().length)];
        for(int i=0;i<vehicleTypes.length;i++){
            vehicleTypes[i]=VehicleType.values()[getRandomInt(0,VehicleType.values().length)];
        }
        startJunction.addExitingRoad(this);
        endJunction.addEnteringRoad(this);
        this.waitingVehicles = new ArrayList<>();
        successMessage(toString());
    }
    //getters
    public boolean getGreenLight(){return greenlight;}
    public boolean getEnable(){return enable;}
    public int getMaxSpeed() {return maxSpeed/10;}
    public double getLength() { return length;}
    public Junction getEndJunction() { return endJunction; }
    public Junction getStartJunction() {return startJunction; }
    public ArrayList<Vehicle> getWaitingVehicles() {return waitingVehicles; }
    public VehicleType[] getVehicleTypes() {return vehicleTypes; }
    public static int[] getAllowedSpeedOptions() {return allowedSpeedOptions; }

    //setters
    public void setLength(final double length) {this.length = length; }
    public void setEnable(final boolean enable) { this.enable = enable; }
    public void setMaxSpeed(final int maxSpeed) { this.maxSpeed = maxSpeed; }
    public void setEndJunction(Junction endJunction) { this.endJunction = endJunction; }
    public void setGreenlight(final boolean greenlight) {this.greenlight = greenlight; }
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
        if(obj instanceof Vehicle)
            return Math.round(Math.abs(length)/ Math.min(maxSpeed,((Vehicle) obj).getVehicleType().getAverageSpeed()));
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
        addVehicleToWaitingVehicles(vehicle);
        vehicle.setStatus("- is starting to move on " +  toString() + ", time to finish: "+ calcEstimatedTime(vehicle)) ;
        System.out.println(vehicle.getStatus());
    }

    /**
     * Method "release" the car fron current road. upadte all relevant data fields.
     *
     * @param vehicle
     */
    public void checkOut(Vehicle vehicle){
        removeVehicleFromWaitingVehicles(vehicle);
        vehicle.setStatus("- has finished " + toString() + ", time spent on the road: "+ vehicle.getTimeOnCurrentPart() + ".");
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
        vehicle.setStatus("- is still moving on " + toString() + ", " + "time to arrive: " + Math.abs(calcEstimatedTime(vehicle)-vehicle.getTimeFromStartRoute()));
        System.out.println(vehicle.getStatus());
        vehicle.run();
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

    @Override
    public String toString(){
        //Assume that implementation is correct,maybe need to fix this later.
         /*
        - is starting to move on Road from Junction 4 (Lighted) to Junction 3 (Lighted), length: 447, max speed 60, time to finish: 11.0.
         */
        return "Road from "+ startJunction + " to " + endJunction +", length: "+ (int)calcLength()+", max speed "+ maxSpeed;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Road){
            return  ((Road) obj).enable==enable &&
                    ((Road) obj).startJunction.equals(startJunction) &&
                    ((Road) obj).endJunction.equals(endJunction) &&
                    ((Road) obj).greenlight==greenlight &&
                    ((Road) obj).length==length &&
                    ((Road) obj).maxSpeed == maxSpeed &&
                    Arrays.equals(((Road) obj).vehicleTypes, vehicleTypes) &&
                    ((Road) obj).waitingVehicles.equals(waitingVehicles);
        }
        return  false;
    }

}
