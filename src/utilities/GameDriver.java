
package utilities;
import javax.swing.*;
import java.awt.*;

import GUI.*;

/**
 * class main.
 *
 * @author Yehonatan Hen
 * @author Rotem Librati
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


