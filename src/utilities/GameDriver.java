
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
            mainFrame main = new mainFrame("Road system");
            main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            main.pack();
            JPanel j=new JPanel();
            j.setBackground(Color.WHITE);
            main.add(j);
            main.setSize(new Dimension(850, 750));
            main.setLocationRelativeTo(null);
            main.setVisible(true);
        }
    }


