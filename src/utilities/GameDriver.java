//Yehonatan Hen-207630112
//Rotem Librati-

package utilities;
import components.Driving;

import javax.swing.*;

/**
 * class main.
 *
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 */

public class GameDriver {
    public static void main(String[] args) {
        JFrame mainFrame=new JFrame("Road system");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Driving driving=new Driving(10, 20);
        driving.drive(20);
        mainFrame.pack();
        mainFrame.setSize(250,250);//TODO: set the size of the frame respectivly to length of system. the current size choosen arbitrary.
        mainFrame.setVisible(true);
    }

}
