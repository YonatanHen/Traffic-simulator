package Builder;

import components.Junction;
import components.Road;
import components.Vehicle;

import java.util.ArrayList;

public interface mapPlan {
    public void setJunctions(ArrayList<Junction> junctions);
    public void setRoads(ArrayList<Road> roads);
    public void setVehicles(ArrayList<Vehicle> vehicles);
    public ArrayList<Junction> getJuntions();
    public ArrayList<Road> getRoads();
    public ArrayList<Vehicle> getVehicles();
}
