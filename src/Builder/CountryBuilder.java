package Builder;

import AbstractFactory.*;
import components.*;
import utilities.Utilities;

import java.util.ArrayList;

/**
 * Country map builder- used to build map that represent a interurban area.
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 * @see MapB
 */
public class CountryBuilder implements mapBuilder,Utilities{
    private MapB map;

    /**
     * Country map constructor
     */
    public CountryBuilder(){
        map = new MapB();
    }

    /**
     * Function build the roads of the map.
     */
    @Override
    public void buildRoads() {
        ArrayList<Road> roads=new ArrayList<>();
        if (map.getJunctions() != null) {
            for (int i = 0; i < map.getJunctions().size(); i++)
                for (int j = 0; j < map.getJunctions().size(); j++) {
                    //Add new roads only if junctions aren't the sane and random value equals 1 (in that way not all of the junctions will connect)
                    if (i != j && getRandomInt(0, 2) == 1) {
                        roads.add(new Road(map.getJunctions().get(i), map.getJunctions().get(j)));
                        if (map.getJunctions().get(j) instanceof LightedJunction) {
                            ((LightedJunction) map.getJunctions().get(j)).getLights().getRoads().add(roads.get(roads.size() - 1));
                        }
                    }
                }
            map.setRoads(roads);
        }
    }

    /**
     * Function build the junctions of the map.
     */
    @Override
    public void buildJunctions() {
        ArrayList <Junction> junctions=new ArrayList<>();
        for(int i=0;i<6;i++){
            junctions.add(JFactory.getJunction("country"));
        }
        map.setJunctions(junctions);
    }

    /**
     * Function build the vehicles by unique types that match the interurban area.
     */
    @Override
    public void buildVehicles() {
        ArrayList<Vehicle> allowedVehicles=new ArrayList<>();
        allowedVehicles.add(new Vehicle(((twoWheelVehicle)Factory.getFactory(2)).getVehicle("fast")));
        allowedVehicles.add(new Vehicle(((fourWheelVehicle)Factory.getFactory(4)).getVehicle("private")));
        allowedVehicles.add(new Vehicle(((fourWheelVehicle)Factory.getFactory(4)).getVehicle("work")));
        allowedVehicles.add(new Vehicle(((fourWheelVehicle)Factory.getFactory(4)).getVehicle("public")));
        allowedVehicles.add(new Vehicle(((tenWheelVehicle)Factory.getFactory(10)).getVehicle("work")));
        ArrayList<Vehicle> vehicles=new ArrayList<>();
        for(int i=0;i<10;i++){
            vehicles.add((Vehicle) allowedVehicles.get(getRandomInt(0,5)).clone());
        }
        map.setVehicles(vehicles);
    }

    @Override
    public MapB getMap() {
        return map;
    }
}