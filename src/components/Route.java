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
        this.RouteParts = new ArrayList<>();
        boolean hasNoExits=false;
        RouteParts.add(start);//Add the first route part-following to instructions start is instance of Road.
        //Add new 9 route parts to the RouteParts arrayList if exist.
        for(int i=0;i<9 && RouteParts.get(RouteParts.size()-1).findNextPart(vehicle)!=null;i++){
            //add new part to the route.
            RouteParts.add((RouteParts.get(RouteParts.size()-1)).findNextPart(vehicle));
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
     *Method calculates the estimated time to perform the route for this vehicle
     * @param obj
     * @return the time that the vehicle arrive to over route.
     */
    public double calcEstimatedTime(Object obj){ //calculate the time for this route.
        double temp =0;
        if(obj instanceof Vehicle && ((Vehicle) obj).getCurrentRoute().equals(((Vehicle) obj).getLastRoad())){
            temp = ((Vehicle) obj).getTimeFromStartRoute()+((Vehicle) obj).getTimeOnCurrentPart();
        }
        return temp;
    }

    /**
     * Check if vehicle arrived to his last RoutePart
     * @param vehicle
     * @return true if yes .
     */
    public boolean canLeave(Vehicle vehicle){
        if(RouteParts.get(RouteParts.size()-1).equals(vehicle.getCurrentRoute()))
            return true;
        return false;
    }

    /**
     * write the vehicle in the route (as soon as the vehicle receives the route),
     * updates the relevant fields and prints an appropriate message.
     * @param vehicle
     */
    public void checkIn(Vehicle vehicle){ //TODO: check this function, if not clearly need to ask Sofi.
        vehicle.setStatus("- is starting a new Route from "+ toString());
        // put the vehicle in route
        Route route = new Route(vehicle.getCurrentRoute(), vehicle);
        vehicle.setCurrentRoute(route);
        System.out.println(vehicle.getStatus());
    }

    /**
     * to free the vehicle from the route. ???
     *
     * @param vehicle
     */
    public void checkOut(Vehicle vehicle){
        if(canLeave(vehicle)) {//check if vehicle arrived to last route
            vehicle.setStatus(" has finished the ");
            System.out.println(vehicle.getStatus());
        }
        else
            stayOnCurrentPart(vehicle);
    }

    public RouteParts findNextPart(Vehicle vehicle){
    }

    /**
     * print massage that the vehicle continue in current route.
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
        return "Route from " + vehicle.getLastRoad() + " from " +
        vehicle.getLastRoad().getEndJunction() + " to " + vehicle.getLastRoad().getStartJunction();
    }
    
}
