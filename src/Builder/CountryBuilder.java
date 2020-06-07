package Builder;

import AbstractFactory.*;
import components.*;
import utilities.Utilities;
import utilities.VehicleType;

import java.util.ArrayList;
import java.util.Random;

public class CountryBuilder implements mapBuilder,Utilities{
    private MapB map;
    public CountryBuilder(){
        map = new MapB();
    }
    @Override
    public void buildRoads() {
        if (map.getJunctions() != null) {
            for (int i = 0; i < map.getJunctions().size(); i++)
                for (int j = 0; j < map.getJunctions().size(); j++) {
                    //Add new roads only if junctions aren't the sane and random value equals 1 (in that way not all of the junctions will connect)
                    if (i != j && getRandomInt(0, 2) == 1) {
                        map.getRoads().add(new Road(map.getJunctions().get(i), map.getJunctions().get(j)));
                        if (map.getJunctions().get(j) instanceof LightedJunction) {
                            ((LightedJunction) map.getJunctions().get(j)).getLights().getRoads().add(map.getRoads().get(map.getRoads().size() - 1));
                        }
                    }
                }
        }
    }

    @Override
    public void buildJunctions() {
        ArrayList <Junction> junctions=new ArrayList<>();
        String [] types=new String[]{Junction.class.getName(), LightedJunction.class.getName()};
        for(int i=0;i<6;i++){
            junctions.add(JFactory.getJunction("country"));
        }
        map.setJunctions(junctions);
    }

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