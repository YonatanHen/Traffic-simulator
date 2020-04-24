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
     * random constructor RandomTrafficLights
     * @param roads
     */

    public RandomTrafficLights(ArrayList<Road>roads){
        super(roads);
        Random random = new Random();
        int i= random.nextInt(roads.size());
        roads.get(i).setGreenlight(true);
    }

    public void changeIndex(){ // method return index of road that get green light
    }//TODO : the method need to return but it void
}
