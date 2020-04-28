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
        for(int i=0;i<9 && findNextPart(vehicle)!=null;i++){
            //add new part to the route.
            RouteParts.add(findNextPart(vehicle));
        }
    }
/*        if (vehicle.getCurrentRouteParts() instanceof Junction && vehicle.getLastRoad().getStartJunction().getExitingRoads().size()!=0) {
            if (start instanceof Road) {
                RouteParts.add(start);
                vehicle.setCurrentRouteParts(start);
            }
        }}
        else if (vehicle.getCurrentRouteParts() instanceof Road && vehicle.getLastRoad().getStartJunction().getExitingRoads().size()!=0){
            if(start instanceof Junction){
                RouteParts.add(start);
                vehicle.setCurrentRouteParts(start);
            }
        }
    }*/

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
        return RouteParts.get(RouteParts.size()-1).equals(vehicle.getCurrentRoute());
    }

    /**
     * write the vehicle in the route (as soon as the vehicle receives the route),
     * updates the relevant fields and prints an appropriate message.
     * @param vehicle
     */
    public void checkIn(Vehicle vehicle){ //TODO: check this function, if not clearly need to ask Sofi.
        // put the vehicle in the route
        Route route = new Route(vehicle.getCurrentRoute(), vehicle);
        vehicle.setCurrentRoute(route);
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
            vehicle.setStatus(" has finished " + this);
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
        //Check if car reach the last part of the route
        if(canLeave(vehicle)){
            //last part of the route must be a Junction
            //Check if Junction hasn't exit roads
            if(((Junction)vehicle.getCurrentRoutePart()).getExitingRoads().size()==0){
                //Make new route from beginning of former route.
                vehicle.setCurrentRoute(new Route(this.getRouteParts().get(0),vehicle));
            }
            else{
                //Make new route part from last road which car drove
                vehicle.setCurrentRoute(new Route(vehicle.getLastRoad(),vehicle));
                //return the next route part.
            }
            return this.RouteParts.get(0);
        }
        else return this.vehicle.getCurrentRoutePart();
    }

    /**
     * print a message that the vehicle continue in current route.
     * @param vehicle
     */
    public void stayOnCurrentPart(Vehicle vehicle){
       vehicle.getLastRoad().stayOnCurrentPart(vehicle);
    }

    public boolean equals(Object other){
        if(other instanceof Route){
            return RouteParts.equals(((Route)other).RouteParts)&&
                    vehicle.equals(((Route)other).vehicle);
        }
        return false;
    }
    public String toString(){
        double length=0;
                for(int i=0;i<RouteParts.size();i++){
                    if(RouteParts.get(i) instanceof Road)
                    length+=((Road) RouteParts.get(i)).getLength();
                }
        return "Route from " + vehicle.getLastRoad() + ", length: " + (int)length + ", estimated time for route: "+ calcEstimatedTime(this) + ".";
    }
    
}
