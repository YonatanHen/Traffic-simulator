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
}

    /**
     * Change the index for road that suppose to get green light.
     * NOTE:This method override abstract trafficLight method.
     */
    public void changeIndex(){
        //TODO: getRoads isn't postive check why, and remove implementation of the if condition.
        /*if(getRoads().size()>0)*/ setGreenLightIndex(getRandomInt(0,getRoads().size()));
    }


    public String toString() {
        return "Random " + super.toString();
    }

    //Equals,getters&setters are the exact from inherited class. Implementation not needed in this case.
}
