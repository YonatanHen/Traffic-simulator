package Builder;

import components.*;

import javax.crypto.AEADBadTagException;
import javax.swing.*;
import java.util.ArrayList;

public class MapB extends Map implements mapPlan{
    private ArrayList<Vehicle> vehicles;
    public MapB() {
        super();
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
        this.vehicles.addAll(vehicles);
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
