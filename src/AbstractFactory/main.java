package AbstractFactory;

public class main {
    public static void main(String args[]){
        System.out.println(Factory.getFactory(2).getVehicle("fast"));
        System.out.println(Factory.getFactory(4).getVehicle("work"));
        System.out.println(Factory.getFactory(10).getVehicle("public"));
    }
}
