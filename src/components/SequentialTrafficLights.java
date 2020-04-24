package components;


import java.util.ArrayList;

/**
* Class represent RandomTrafficLight in the junction.
* @author Yehonatan Hen-207630112
* @author Rotem Librati-307903732
* @see TrafficLights
*/
public class SequentialTrafficLights extends TrafficLights {
    private int increment=1;//Sequential increment-this value will move to road
    //that locating by size of this value. PAY ATTENTION! We will not use this option in current work.

    /**
     * SequentialTrafficLights constructor.
     * @param roads
     */
    public SequentialTrafficLights(ArrayList<Road> roads) {
        super(roads);
        //Suppose to be here implementation of changing the traffic light - based on increment variable.
    }

    /**
     * Method change new index for green light.
     */
    public void changeIndex(){
        setGreenLightIndex(increment);
    }
}
