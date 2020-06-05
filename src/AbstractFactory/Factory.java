package AbstractFactory;

public class Factory {
    private Factory(){}
    public static vehicle getFactory(int x){
        switch (x){
            case 2: return new twoWheelVehicle();
            case 4: return new fourWheelVehicle();
            case 10: return new tenWheelVehicle();
            default: return null;
        }
    }
}
