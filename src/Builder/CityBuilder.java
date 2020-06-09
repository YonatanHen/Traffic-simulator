package Builder;

import AbstractFactory.Factory;
import AbstractFactory.fourWheelVehicle;
import AbstractFactory.tenWheelVehicle;
import AbstractFactory.twoWheelVehicle;
import components.*;
import utilities.Utilities;

import java.util.ArrayList;
import java.util.Random;

/**
 * City map builder- used to build map that represent a city.
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 * @see MapB
 */
public class CityBuilder implements mapBuilder, Utilities {
    private MapB map;
    public CityBuilder(){
        map = new MapB();
    }

    /**
     * Function build the roads of the map.
     */
    @Override
    public void buildRoads() {
        if(map.getJunctions()!= null) {
            for (int i = 0; i < map.getJunctions().size(); i++)
                for (int j = 0; j < map.getJunctions().size(); j++) {
                    if(i!=j){
                        map.getRoads().add(new Road(map.getJunctions().get(i), map.getJunctions().get(j)));
                        ((LightedJunction) map.getJunctions().get(j)).getLights().getRoads().add(map.getRoads().get(map.getRoads().size() - 1));
                    }
                }
        }

    }

    /**
     * Function build the junctions of the map.
     */
    @Override
    public void buildJunctions() {
        ArrayList <Junction> junctions=new ArrayList<>();
        for(int i=0;i<12;i++){
            junctions.add(JFactory.getJunction("city"));
        }
        map.setJunctions(junctions);
    }
    /**
     * Function build the vehicles by unique types that match the urban area.
     */
    @Override
    public void buildVehicles() {
        ArrayList<Vehicle> allowedVehicles=new ArrayList<>();
        allowedVehicles.add(new Vehicle(((twoWheelVehicle) Factory.getFactory(2)).getVehicle("fast")));
        allowedVehicles.add(new Vehicle(((twoWheelVehicle) Factory.getFactory(2)).getVehicle("slow")));
        allowedVehicles.add(new Vehicle(((fourWheelVehicle)Factory.getFactory(4)).getVehicle("private")));
        allowedVehicles.add(new Vehicle(((fourWheelVehicle)Factory.getFactory(4)).getVehicle("public")));
        allowedVehicles.add(new Vehicle(((tenWheelVehicle)Factory.getFactory(10)).getVehicle("public")));
        ArrayList<Vehicle> vehicles=new ArrayList<>();
        for(int i=0;i<10;i++){
            vehicles.add((Vehicle) allowedVehicles.get(getRandomInt(0,5)).clone());
        }
        for(int i=0;i<vehicles.size();i++) {
            Random random = new Random();
            boolean speed = random.nextBoolean();
            if (speed)
                vehicles.get(i).getVehicleType().setAverageSpeedForCity((vehicles.get(i).getVehicleType().getAverageSpeed()*10));
        }
        map.setVehicles(vehicles);
    }
    @Override
    public MapB getMap() {return map;}
}
