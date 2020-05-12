
package utilities;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.locks.Lock;

import GUI.*;
import components.Driving;

/**
 * class main.
 *
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 */

public class GameDriver{
    public static void main(String[] args) {
        Driving driving;
        createRoadSystem crs=new createRoadSystem("Create road system");
        mainFrame main = new mainFrame("Road system",crs);
        //System.out.println(crs.getD().getDrivingTime());
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.pack();
        main.setVisible(true);
        driving=main.getCreateRoadSys().getD();
        }
    }


