
package utilities;
import components.Driving;

import javax.swing.*;
import java.awt.*;
import GUI.*;

/**
 * class main.
 *
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 */

public class GameDriver extends Component {
    public static void main(String[] args) {
        mainFrame main = new mainFrame("Road system");
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.pack();
        main.setVisible(true);
        Driving driving = new Driving(10, 20);
        driving.drive(20);
    }
}


