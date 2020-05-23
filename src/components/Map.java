package components;

import utilities.Utilities;
import utilities.VehicleType;

import java.util.ArrayList;

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
        junctions = new ArrayList<>();
        roads = new ArrayList<>();
        lights = new ArrayList<>();
        //Array of types.
        String [] types=new String[]{Junction.class.getName(),LightedJunction.class.getName()};
        //Create Junctions
        System.out.println("================= CREATING JUNCTIONS=================");
        for(int i=0;i<numOfJunctions;i++){
            //Randomise one of junction types and make a junction of type variable it receives.
            String type=types[getRandomInt(0,types.length)];
            if(type.equals(Junction.class.getName())) junctions.add(new Junction());
            else junctions.add(new LightedJunction());
        }
        //Create roads
        System.out.println("================= CREATING ROADS=================");
        setAllRoads();
        //turn lights on (randomly-executes inside the junction)
        System.out.println("================= TRAFFIC LIGHTS TURN ON =================");
        turnLightsOn();
        System.out.println("================= GAME MAP HAS BEEN CREATED =================");
        System.out.println();
    }

    /**
     * Method create the roads for the constructor.
     */
    public void setAllRoads() {
        for (int i = 0; i < junctions.size(); i++) {
            for (int j = 0; j < junctions.size(); j++) {
                //connect between all junctions apart of junction to itself.
                if (i != j) {
                    roads.add(new Road(junctions.get(i), junctions.get(j)));
                    if (junctions.get(j) instanceof LightedJunction) {
                        ((LightedJunction) junctions.get(j)).getLights().getRoads().add(roads.get(roads.size() - 1));
                    }
                }
            }
        }
    }

    /**
     * Method turn the lights on for the constructor.
     */
    public void turnLightsOn(){
        ArrayList<Integer> lightedJuncs= new ArrayList<>();
        for(Road r:roads){
           if (r.getEndJunction() instanceof LightedJunction){
               //Turn lights on when traffic lights on lightedJunction aren't lightening/lightening yet and random value is true.
               if(!((LightedJunction)r.getEndJunction()).getLights().getTrafficLightsOn() &&
                       !lightedJuncs.contains(((LightedJunction)r.getEndJunction()).getLights().getid()) &&getRandomBoolean()){
                   System.out.println(((LightedJunction)r.getEndJunction()).getLights()+
                           " turned ON, delay time: "+((LightedJunction)r.getEndJunction()).getLights().getDelay());
                   ((LightedJunction) r.getEndJunction()).getLights().changeLights();
                   lightedJuncs.add(((LightedJunction)r.getEndJunction()).getLights().getid());
               }
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

    @Override
    public String toString(){
        return "GAME MAP";
    }
    @Override
    public boolean equals(Object o){
        if( o instanceof Map){
            return ((Map) o).junctions.equals(junctions) &&
            ((Map) o).lights.equals(lights) &&
            ((Map) o).roads.equals(roads);
        }
        return false;
    }
}

