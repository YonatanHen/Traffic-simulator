package components;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class represent RandomTrafficLight in the junction.
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 * @see TrafficLights
 */
public class RandomTrafficLights extends TrafficLights {
    /**
     * Random constructor of RandomTrafficLights
     * @param roads
     */
    public RandomTrafficLights(ArrayList<Road>roads){
        super(roads);
        Random random = new Random();
        if(roads.size()>0) super.getRoads().get(random.nextInt(super.getRoads().size())).setGreenlight(true);
}

    /**
     * Change the index for road that suppose to get green light.
     * NOTE:This method override abstract trafficLight method.
     */
    public void changeIndex(){
        for(Road r:getRoads()){
            if(r.getGreenLight()){
                setGreenLightIndex(getRoads().indexOf(r));
                break;
            }
        }
    }


    public String toString() {
        return "Random " + super.toString();
    }

    //Equals,getters&setters are the exact from inherited class. Implementation not needed in this case.
}
