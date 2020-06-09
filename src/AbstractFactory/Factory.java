package AbstractFactory;


/**
 * Class Factory part of abstract factory implementation.
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 */
public class Factory {

    /**
     * Factory private empty constructor.
     */
    private Factory(){}

    /**
     * Function return an object by x value it receives
     * @param x
     * @return object of x wheels vehicle. (not specific)
     */
    public static vehicle getFactory(int x){
        switch (x){
            case 2: return new twoWheelVehicle();
            case 4: return new fourWheelVehicle();
            case 10: return new tenWheelVehicle();
            default: return null;
        }
    }
}
