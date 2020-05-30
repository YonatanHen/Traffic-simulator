package components;

public class BigBrother {

    private static volatile BigBrother instance=null;
    private BigBrother(){}
    public static BigBrother getInstance(){
        if(instance==null){
            synchronized (BigBrother.class) {
                instance = new BigBrother();
            }
        }
        return instance;
    }
}
