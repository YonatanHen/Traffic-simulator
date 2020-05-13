package GUI;

import components.Driving;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class mainFrame extends JFrame implements ActionListener {
    JMenuBar menuBar;
    JMenu file, background, vehicleColor, help;
    JMenuItem exit, blueBackGround, noneBackground, blueVehicle, magentaVehicle, orangeVehicle, randomVehicle, helpItem;
    JPanel container;
    JButton btns[];
    Driving driving;
    createRoadSystem createRoadSys;

    public mainFrame(String title) {
        super(title);
        menuBar = new JMenuBar();
        file = new JMenu("File");
        file.addActionListener(this);
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        background = new JMenu("Background");
        background.addActionListener(this);
        blueBackGround = new JMenuItem("Blue");
        blueBackGround.addActionListener(this);
        noneBackground = new JMenuItem("None");
        noneBackground.addActionListener(this);
        vehicleColor = new JMenu("Vehicle color");
        blueVehicle = new JMenuItem("Blue");
        magentaVehicle = new JMenuItem("Magenta");
        orangeVehicle = new JMenuItem("Orange");
        randomVehicle = new JMenuItem("Random");
        help = new JMenu("Help");
        helpItem = new JMenuItem("Help");
        helpItem.addActionListener(this);
        createRoadSys = new createRoadSystem("Create road system");
        container = new JPanel();
        btns=new JButton[5];
        btns[0]=new JButton("Create road system");
        btns[1]=new JButton("Start");
        btns[2]=new JButton("Stop");
        btns[3]=new JButton("Resume");
        btns[4]=new JButton("Info");
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
        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
        for(int i=0;i<btns.length;i++) {
            btns[i].addActionListener(this);
            btns[i].setBorder(border);
            container.add(btns[i]);
        }
        container.setLayout(new GridLayout(1, 0));
        add(container, BorderLayout.SOUTH);
    }

    /**
     * Perform actions listeners
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
            if (e.getSource()==exit){
                System.exit(0);
            }
            if (e.getSource()==blueBackGround){
                getContentPane().setBackground(Color.BLUE);
            }
            if (e.getSource()==noneBackground){
                getContentPane().setBackground(Color.WHITE);
            }
            if (e.getSource()==helpItem){
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                        "Home Work 3\n" + "GUI @ Threads");
            }
        for(int i=0;i<btns.length;i++) {
            if (e.getSource() == btns[i]) {
                switch (i) {
                    case 0: {
                        createRoadSys.pack();
                        createRoadSys.setSize(600, 300);
                        createRoadSys.setVisible(true);
                        setDriving(createRoadSys.getD());
                    }
                    break;
                    case 1:createRoadSys.getD().drive(20);
                    break;
                    case 2:
                        System.out.println("resume");
                        break;
                    case 3:
                        System.out.println("stop");
                        break;
                    case 4:
                        System.out.println("info");
                        break;
                }
            }
        }
    }
    public void setDriving(Driving d){driving=d;}
    public void paint(Graphics g) {
        super.paint(g);
    }
}