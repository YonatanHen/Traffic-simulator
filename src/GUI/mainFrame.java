package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class mainFrame extends JFrame {
    JMenuBar menuBar;
    JMenu file, background, vehicleColor, help;
    JMenuItem exit, blueBackGround, noneBackground, blueVehicle, magentaVehicle, orangeVehicle, randomVehicle, helpItem;
    JPanel container;

    public mainFrame(String title) {
        super(title);
        menuBar = new JMenuBar();
        file = new JMenu("File");
        exit = new JMenuItem(new AbstractAction("Exit") {
            @Override
            /**
             * exit the main frame when pressed.
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        background = new JMenu("Background");
        blueBackGround = new JMenuItem("Blue");
        noneBackground = new JMenuItem("None");
        vehicleColor = new JMenu("Vehicle color");
        blueVehicle = new JMenuItem("Blue");
        magentaVehicle = new JMenuItem("Magenta");
        orangeVehicle = new JMenuItem("Orange");
        randomVehicle = new JMenuItem("Random");
        help = new JMenu("Help");
        helpItem = new JMenuItem(new AbstractAction("Help") {
            @Override
            /**
             *  show message dialog when "help" clicked.
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                        "Home Work 3\n" + "GUI @ Threads");
            }
        });
        container=new JPanel();
        file.add(exit);
        background.add(blueBackGround);
        background.add(noneBackground);
        vehicleColor.add(blueVehicle);
        vehicleColor.add(magentaVehicle);
        vehicleColor.add(orangeVehicle);
        vehicleColor.add(randomVehicle);
        help.add(helpItem);
        menuBar.add(file);
        menuBar.add(background);
        menuBar.add(vehicleColor);
        menuBar.add(help);
        setJMenuBar(menuBar);
        ArrayList<JLabel> components=new ArrayList<>();
        components.add(new JLabel("Create road system"));
        components.add(new JLabel("Start"));
        components.add(new JLabel("Stop"));
        components.add(new JLabel("Resume"));
        components.add(new JLabel("Info"));
        Border border=BorderFactory.createLineBorder(Color.BLUE,1);
        for(JLabel label:components){
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setBorder(border);
            container.add(label);
        }
        container.setLayout(new GridLayout(1,0));
        add(container,BorderLayout.SOUTH);

    }
}