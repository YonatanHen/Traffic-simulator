package Builder;

import components.*;

import javax.swing.*;
import java.util.ArrayList;

public class MapB extends Map implements mapPlan{
    private ArrayList<Vehicle> vehicles;
    public MapB() {
        super();
    }

    public void setJunctions(ArrayList<Junction> junctions){
        this.junctions=junctions;
    }
    public void setRoads(ArrayList<Road> roads){
        this.roads=roads;
    }
    public void setVehicles(ArrayList<Vehicle> vehicles){
        this.vehicles=vehicles;
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
