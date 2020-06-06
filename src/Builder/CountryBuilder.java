package Builder;

import components.Junction;
import components.LightedJunction;
import components.Map;
import components.Road;
import utilities.Utilities;
import java.util.ArrayList;

public class CountryBuilder implements mapBuilder,Utilities{
    private MapB map;
    @Override
    public void buildRoads() {
        if (map.getJuntions() != null) {
            for (int i = 0; i < map.getJuntions().size(); i++)
                for (int j = 0; j < map.getJuntions().size(); j++) {
                    //Add new roads only if junctions aren't the sane and random value equals 1 (in that way not all of the junctions will connect)
                    if (i != j && getRandomInt(0, 2) == 1) {
                        map.getRoads().add(new Road(map.getJuntions().get(i), map.getJuntions().get(j)));
                        if (map.getJuntions().get(j) instanceof LightedJunction) {
                            ((LightedJunction) map.getJuntions().get(j)).getLights().getRoads().add(map.getRoads().get(map.getRoads().size() - 1));
                        }
                    }
                }
        }
    }

    @Override
    public void buildJunctions() {
        ArrayList <Junction> junctions=new ArrayList<>();
        String [] types=new String[]{Junction.class.getName(), LightedJunction.class.getName()};
        for(int i=0;i<6;i++){
            String type=types[getRandomInt(0,types.length)];
            if(type.equals(Junction.class.getName())) junctions.add(new Junction());
            else junctions.add(new LightedJunction());
        }
        map.setJunctions(junctions);
    }

    @Override
    public void buildVehicles() {

    }

    @Override
    public MapB getMap() {
        return map;
    }
}