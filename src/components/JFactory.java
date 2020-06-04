package components;


import java.util.Random;

/**
 * Factory method class to Junction class.
 *  @author Yehonatan Hen-207630112
 *  @author Rotem Librati-307903732
 *  @see Junction
 *  @see LightedJunction 
 */
public class JFactory extends Junction {
    /**
     * JFactory constructor
     */
    public JFactory(){
        super();
    }

    /**
     * return lighted junction or regular junction-depends on 'x' value-city/country
     * for legal value,return null.
     * @param x
     * @return
     */
    public Junction getJunction(String x){
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
