package components;

import utilities.Utilities;
import utilities.VehicleType;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class make the map
 *
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 * @see VehicleType
 * @see Route
 * @see RouteParts
 * @see Road
 * @see Junction
 * @see TrafficLights
 * @see Vehicle
 */
public class Map implements Utilities {
    private ArrayList<Junction> junctions;
    private ArrayList<Road> roads;
    private ArrayList<TrafficLights> lights;


    /**
     * The constructor of the map.
     * Constructor make given number of junctions. then, make roads between any junction to
     * other junctions. The constructor randomise the type of the junction that would be create,
     * create the roads and in part of the lighted junctions-turn them on.
     *
     * @param numOfJunctions
     */
    public Map(int numOfJunctions){
        Random rand=new Random();
        junctions = new ArrayList<Junction>();
        roads = new ArrayList<Road>();
        lights = new ArrayList<TrafficLights>();
        //Array of types.
        String [] types=new String[]{Junction.class.getName(),LightedJunction.class.getName()};
        //Create Junctions
        for(int i=0;i<numOfJunctions;i++){
            //Randomise one of junction types and make the type who type variable it receives.
            String type=types[rand.nextInt(types.length)];
            if(type.equals(Junction.class.getName())) junctions.add(new Junction());
            else junctions.add(new LightedJunction());
        }
        //Create roads
        setAllRoads();
        //turn lights on (randomly-executes inside the junction)
        turnLightsOn();
    }

    /**
     * Method create the roads for the constructor.
     */
    public void setAllRoads(){
        for(int i=0;i<junctions.size();i++){
            for(int j=0;j<junctions.size();j++){
                //connect between all junctions apart of junction to itself.
                if(i!=j) roads.add(new Road(junctions.get(i),junctions.get(j)));
            }
        }
    }

    /**
     * Method turn the lights on for the constructor.
     */
    public void turnLightsOn(){
        Random rand=new Random();
        for(Junction junc:junctions){
            //Following to constructor instructions, lights turn on randomly.
            //Check if junction is lights, change lights if it is.
            if (junc instanceof LightedJunction && rand.nextBoolean()) {
                ((LightedJunction) junc).getLights().changeLights();
            }
        }
    }

    //getters
    public ArrayList<Junction> getJunctions() {
        return junctions;
    }

    public ArrayList<Road> getRoads() {
        return roads;
    }

    public ArrayList<TrafficLights> getLights() {
        return lights;
    }

    //setters
    public void setLights(ArrayList<TrafficLights> lights) {
        this.lights = lights;
    }

    public void setJunctions(ArrayList<Junction> junctions) {
        this.junctions = junctions;
    }

    public void setRoads(ArrayList<Road> roads) {
        this.roads = roads;
    }

    public String toString(){
        return "GAME MAP";
    }

    public boolean equals(Object o){
        if( o instanceof Map){
            return ((Map) o).junctions.equals(junctions) &&
            ((Map) o).lights.equals(lights) &&
            ((Map) o).roads.equals(roads);
        }
        return false;
    }
}

