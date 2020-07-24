package components;


import java.util.ArrayList;

/**
* Class represent RandomTrafficLight in the junction.
* @author Yehonatan Hen
* @author Rotem Librati
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
    }

    /**
     * Method change new index for green light.
     */
    public void changeIndex(){
        setGreenLightIndex(increment);
        increment++;
        if(getRoads().size()>=increment)
            increment=0;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(final int increment) {
        this.increment = increment;
    }

    @Override
    public String toString() {
        return "Sequential " + super.toString();
    }

    //Equals,getters&setters are the exact from inherited class. Implementation not needed in this case.
}
