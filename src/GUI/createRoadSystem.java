package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Class implement "create road system" dialog
 */
public class createRoadSystem extends JFrame {
    static final int FPS_MIN = 0;
    static final int FPS_MAX1 = 20;
    static final int FPS_MAX2 = 50;
    static final int FPS_INIT1 = 10;    //initial frames per second
    static final int FPS_INIT2 = 25;    //initial frames per second
    JSlider numOfJuncs,numOfVehicles;
    JPanel bottom;
    public createRoadSystem(String title) {
        super(title);
        numOfJuncs=new JSlider(JSlider.HORIZONTAL,FPS_MIN,FPS_MAX1,FPS_INIT1);
        numOfVehicles=new JSlider(JSlider.HORIZONTAL,FPS_MIN,FPS_MAX2,FPS_INIT2);
        bottom=new JPanel();
        ArrayList<JButton> buttons=new ArrayList<>();
        buttons.add(new JButton("OK"));
        buttons.add(new JButton("Cancel"));
        bottom.add(buttons.get(0));
        bottom.add(Box.createRigidArea(new Dimension(2,0)));
        bottom.add(buttons.get(1));
        add(numOfJuncs);
        add(numOfVehicles);
        add(bottom);
    }
}
