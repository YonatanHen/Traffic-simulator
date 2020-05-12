package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Class implement "create road system" dialog
 */
public class createRoadSystem extends JFrame {
    static final int FPS_MIN1 = 3;
    static final int FPS_MIN2 = 0;
    static final int FPS_MAX1 = 20;
    static final int FPS_MAX2 = 50;
    static final int FPS_INIT1 = 10;    //initial frames per second
    static final int FPS_INIT2 = 25;    //initial frames per second
    JSlider numOfJuncs,numOfVehicles;
    JPanel frame;
    JLabel text1,text2;
    public createRoadSystem(String title) {
        super(title);
        numOfJuncs=new JSlider(JSlider.HORIZONTAL,FPS_MIN1,FPS_MAX1,FPS_INIT1);
        numOfJuncs.setMajorTickSpacing(1);
        numOfJuncs.setPaintTicks(true);
        numOfJuncs.setPaintLabels(true);
        numOfVehicles=new JSlider(JSlider.HORIZONTAL,FPS_MIN2,FPS_MAX2,FPS_INIT2);
        numOfVehicles.setMajorTickSpacing(5);
        numOfVehicles.setMinorTickSpacing(1);
        numOfVehicles.setPaintTicks(true);
        numOfVehicles.setPaintLabels(true);
        frame =new JPanel();
        frame.setLayout(new GridLayout(5,1));
        ArrayList<JButton> buttons=new ArrayList<>();
        buttons.add(new JButton("OK"));
        buttons.add(new JButton("Cancel"));
        JPanel bottom=new JPanel();
        bottom.setLayout( new GridLayout(1,2,10,0));
        text1=new JLabel("Number of junctions",SwingConstants.CENTER);
        text2=new JLabel("Number of vehicles",SwingConstants.CENTER);
        frame.add(text1);
        frame.add(numOfJuncs);
        frame.add(text2);
        frame.add(numOfVehicles);
        bottom.add(buttons.get(0));
        bottom.add(buttons.get(1));
        frame.add(bottom);
        add(frame);
    }
}
