package Builder;

import components.*;

import java.util.ArrayList;

/**
 * Used expansion of Map. part of Builder DP.
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 * @see Map
 * @see Vehicle
 * @see Road
 * @see Junction
 */
public class MapB extends Map implements mapPlan{
    private ArrayList<Vehicle> vehicles;

    /**
     * MapB constructor
     */
    public MapB() {
        super();
        vehicles=new ArrayList<>();
    }

    public void setJunctions(ArrayList<Junction> junctions){
        this.junctions=new ArrayList<>();
        this.junctions.addAll(junctions);
    }
    public void setRoads(ArrayList<Road> roads){
        this.roads=new ArrayList<>();
        this.roads.addAll(roads);
    }
    public void setVehicles(ArrayList<Vehicle> vehicles){
        this.vehicles=new ArrayList<>();
        vehicles.get(0).setObjectCount(0);
        this.vehicles.addAll(vehicles);
    }

    @Override
    public ArrayList<Road> getRoads() {
        return super.getRoads();
    }

    @Override
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    @Override
    public ArrayList<Junction> getJunctions() {
        return super.getJunctions();
    }
}
