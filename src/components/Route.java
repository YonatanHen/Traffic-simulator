package components;

import java.util.ArrayList;
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
     * constructor Route
     * @param start
     * @param vehicle
     */

    public Route(RouteParts start, Vehicle vehicle){
        this.RouteParts = new ArrayList<>();
        RouteParts.add(start);
        this.vehicle=vehicle;
    }

    //getters
    public ArrayList<RouteParts> getRouteParts(){return RouteParts;}


    public boolean equals(Object other){
        if(other instanceof Route){
            return RouteParts.equals(((Route)other).RouteParts)&&
                    vehicle.equals(((Route)other).vehicle);
        }
        return false;
    }
}
