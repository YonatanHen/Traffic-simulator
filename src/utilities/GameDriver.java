
package utilities;
import components.Driving;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * class main.
 *
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 */

public class GameDriver extends Component {
    public static void main(String[] args) {
        JFrame mainFrame=new JFrame("Road system");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JMenuBar menuBar;
        JMenu file, backGround, vehicleColor, help;
        JMenuItem exit, blueBackGround, noneBackGround, blueVehicle, magentaVehicle, orangeVehicle, randomVehicle, helpItem;
        menuBar = new JMenuBar();
        file = new JMenu("File");
        exit = new JMenuItem("Exit");
        backGround = new JMenu("Background");
        blueBackGround = new JMenuItem("Blue");
        noneBackGround = new JMenuItem("None");
        vehicleColor = new JMenu("Vehicle color");
        blueVehicle = new JMenuItem("Blue");
        magentaVehicle = new JMenuItem("Magenta");
        orangeVehicle = new JMenuItem("Orange");
        randomVehicle = new JMenuItem("Random");
        help = new JMenu("Help");
        helpItem = new JMenuItem("Help");
        file.add(exit);
        backGround.add(blueBackGround);
        backGround.add(noneBackGround);
        vehicleColor.add(blueVehicle);
        vehicleColor.add(magentaVehicle);
        vehicleColor.add(orangeVehicle);
        vehicleColor.add(randomVehicle);
        help.add(helpItem);
        menuBar.add(file);
        menuBar.add(backGround);
        menuBar.add(vehicleColor);
        menuBar.add(help);
        mainFrame.setJMenuBar(menuBar);
        exit.addActionListener(new exitApp()); // press on "exit" button - the window closed
        helpItem.addActionListener(new DialogMessage()); // press on "help" button - show message

        Driving driving=new Driving(10, 20);
        driving.drive(20);
        mainFrame.pack();
        mainFrame.setSize(500,500);//TODO: set the size of the frame respectivly to length of system. the current size choosen arbitrary.
        mainFrame.setVisible(true);
    }
    // class for exit from Road System
    static class exitApp implements ActionListener
    { public void actionPerformed(ActionEvent e)
        { System.exit(0); }}
    static class DialogMessage implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                    "Home Work 3\n" +
                            "GUI @ Threads");
        }
    }
}
