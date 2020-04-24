package components;

import java.util.Random;

/**
 * Class represent a junction with traffic lights.
 *
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 * @see Junction
 * @see TrafficLights
 */
public class LightedJunction extends Junction{
    private TrafficLights lights;

    /**
     * Randomal constructor. randomise the type of the traffic light.
     */
    public LightedJunction(){
        super();
        //Randomise the type of the light
        String types[]=new String[]{"Randomal","Sequential"};
        Random rand=new Random();
        String selectedType=types[rand.nextInt(types.length)];
        //Randomal junction randomise green light from entering roads of the junction.
        if(selectedType=="Randomal") lights=new RandomTrafficLights(getEnteringRoads());
        else lights=new SequentialTrafficLights();
    }

    /**
     * Concrete constructor, receive as arguments the traffic light details.
     * @param name
     * @param x
     * @param y
     * @param sequential
     * @param lightsOn
     */
    public LightedJunction(String name, double x, double y, boolean sequential, boolean lightsOn){
        //Call Junction constructor
        super(name,x,y);
        if(sequential) lights=new SequentialTrafficLights();
        else lights=new RandomTrafficLights(getEnteringRoads());
        lights.setTrafficLightsOn(lightsOn);
    }

    /**
     *Interface method,calculate the estimated delay time in that junction.
     * estimated time at junction calculates by traffic light delay duplicate by amount of
     * entering roads minus one. at the end add 1 for the crossing of the junction.
     *
     * @param obj
     * @return delay time in this type of junction.
     */
    public double calcEstimatedTime(Object obj){
        int sum=1;
        if(obj instanceof LightedJunction){
            //PAY ATTENTION! sum initialized by the value 1.
            sum+=(lights.getDelay()*((LightedJunction)obj).getEnteringRoads().size()-1);
        }
        return sum;
    }

    /**
     * Vehicle can leave the junction if green light is on.
     * @param vehicle
     * @return true if the car can leave the junction else false.
     */
    public boolean canLeave(Vehicle vehicle){
        return lights.getTrafficLightsOn();
    }
}
