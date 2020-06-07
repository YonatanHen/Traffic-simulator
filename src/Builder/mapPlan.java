package Builder;

import components.Junction;
import components.Road;
import components.Vehicle;

import java.util.ArrayList;

public interface mapPlan {
    public void setVehicles(ArrayList<Vehicle> vehicles);
    public ArrayList<Vehicle> getVehicles();
}
