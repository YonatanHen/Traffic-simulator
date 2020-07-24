package components;


import java.util.Random;

/**
 * Factory method class to Junction class.
 *  @author Yehonatan Hen
 *  @author Rotem Librati
 *  @see Junction
 *  @see LightedJunction 
 */
public class JFactory{

    /**
     * return lighted junction or regular junction-depends on 'x' value-city/country
     * for illegal value,return null.
     * @param x
     * @return instance of junction
     */
    public static Junction getJunction(String x){
        if(x.equals("city")) return new LightedJunction();
        if(x.equals("country")){
            Random r=new Random();
            int value=r.nextInt(2);
            if(value==0) return new LightedJunction();
            return new Junction();
        }
        return null;
    }
}
