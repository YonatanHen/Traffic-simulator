package components;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class represent Route on the map.
 *
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 * @see RouteParts
 * @see Vehicle
 */
public class Route implements RouteParts {
    private ArrayList<RouteParts> RouteParts;
    private Vehicle vehicle;

    /***
     * constructor Route-Randomal constructor that looking for optional
     * route part from current position until he reach 10 route parts/ Junction without exists.
     * start must be a road, last route part must be a junction.
     * @param start
     * @param vehicle
     */
    public Route(RouteParts start, Vehicle vehicle) {
        this.vehicle=vehicle;
        this.RouteParts = new ArrayList<>();
        /*Add the first route part-following to instructions start is instance of Road.
        Add new 9 route parts to the RouteParts arrayList if exist.*/
        RouteParts.add(start);
        RouteParts rp=RouteParts.get(0).findNextPart(vehicle);
        for(int i=0;i<9;i++){
            //add new part to the route.
            if(rp!=null) {
                RouteParts.add(rp);
                rp = RouteParts.get(RouteParts.size() - 1).findNextPart(vehicle);
            }
            else break;
        }
    }

    //getters
    public ArrayList<RouteParts> getRouteParts(){return RouteParts;}
    public Vehicle getVehicle() { return vehicle; }

    //setters
    public void setRouteParts(ArrayList<components.RouteParts> routeParts) {
        RouteParts = routeParts;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     *Method calculates the estimated time to execute the route for this vehicle
     * @param obj
     * @return Time that take to the vehicle to finish the route.
     */
    public double calcEstimatedTime(Object obj){
      double time=0;
      //calculate time from included roads in route Parts
      for (RouteParts rp: RouteParts){
          if(rp instanceof Road) time+= rp.calcEstimatedTime(vehicle);
      }
      return time;
    }

    /**
     * Check if vehicle arrived to his last RoutePart
     * @param vehicle
     * @return true if yes .
     */
    public boolean canLeave(Vehicle vehicle){
        return RouteParts.get(RouteParts.size()-1).equals(vehicle.getCurrentRoutePart());
    }

    /**
     * write the vehicle in the route (as soon as the vehicle receives the route),
     * updates the relevant fields and prints an appropriate message.
     * @param vehicle
     */
    public void checkIn(Vehicle vehicle){
        // put the vehicle in the route
        vehicle.setCurrentRoute(this);
        vehicle.setCurrentRoutePart(RouteParts.get(0));
        vehicle.setStatus("- is starting a new "+ this);
        System.out.println(vehicle.getStatus());
    }

    /**
     * Method release the vehicle from the route.
     *
     * @param vehicle
     */
    public void checkOut(Vehicle vehicle){
        if(canLeave(vehicle)) {//check if vehicle arrived to last route
            vehicle.setStatus("- has finished " + this + " Time spent on the route: " + vehicle.getTimeFromStartRoute());
            System.out.println(vehicle.getStatus());
        }
        else
            stayOnCurrentPart(vehicle);
    }

    /**
     * Check if vehicle reach the end of the route and make new route like that:
     * if there aren't exit roads that match to the current situation, the new route will
     * be create randomly from the beginning of the former route.
     * else- there are exit roads; the new route will start from the last road which car drove.
     * if car hasn't reach the end of the route, the method return next route part after the part
     * which car locating.
     * @param vehicle
     * @return next route part
     */
    public RouteParts findNextPart(Vehicle vehicle){
        if (canLeave(vehicle)) {
            for (Road r : ((Junction) vehicle.getCurrentRoutePart()).getExitingRoads()) {
                for (int i = 0; i < r.getVehicleTypes().length; i++) {
                    if (r.getVehicleTypes()[i].equals(vehicle.getVehicleType())) {
                        Road lastRoad = vehicle.getLastRoad();
                        vehicle.setCurrentRoute(new Route(lastRoad, vehicle));
                        return vehicle.getCurrentRoutePart();
                    }
                }
            }
            vehicle.setCurrentRoute(new Route(vehicle.getCurrentRoute().getRouteParts().get(0),vehicle));
            return vehicle.getCurrentRoutePart();
        }
        else return vehicle.getCurrentRoutePart().findNextPart(vehicle);
    }

     /** print a message that the vehicle continue in current route.
     * @param vehicle
     */
    public void stayOnCurrentPart(Vehicle vehicle){
       vehicle.getCurrentRoutePart().stayOnCurrentPart(vehicle);
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Route){
            return RouteParts.equals(((Route)other).RouteParts)&&
                    vehicle.equals(((Route)other).vehicle);
        }
        return false;
    }

    @Override
    public String toString(){
        //Search for the road with max speed and save the max speed value with end junction value.
        int maxSpeed=0;
        Junction endJunc=((Road)RouteParts.get(0)).getEndJunction();
        for (RouteParts r:RouteParts){
            if( r instanceof Road){
                if(((Road) r).getMaxSpeed()>maxSpeed){
                    maxSpeed=((Road)r).getMaxSpeed();
                    endJunc=((Road) r).getEndJunction();
                }
            }
        }
        return "Route from Road from " + vehicle.getLastRoad().getStartJunction()+ " to "+
                vehicle.getLastRoad().getEndJunction() +", length: "+ (int)vehicle.getLastRoad().getLength() + ", max speed "+ maxSpeed +" to "
                + endJunc+", estimated time for route: "+ calcEstimatedTime(this) + ".";
    }
    
}
