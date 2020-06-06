package Builder;

import components.*;

import javax.swing.*;
import java.util.ArrayList;

public class MapB implements mapPlan{
    private ArrayList<Junction> junctions;
    private ArrayList<Road> roads;
    private ArrayList<Vehicle> vehicles;
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
    public ArrayList<Junction> getJuntions() {
        return junctions;
    }

    @Override
    public ArrayList<Road> getRoads() {
        return roads;
    }

    @Override
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }


}
