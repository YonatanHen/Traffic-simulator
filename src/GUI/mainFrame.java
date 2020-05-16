package GUI;



import components.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;

public class mainFrame extends JFrame implements ActionListener {
    JMenuBar menuBar;
    JMenu file, background, vehicleColor, help;
    JMenuItem exit, blueBackGround, noneBackground, blueVehicle, magentaVehicle, orangeVehicle, randomVehicle, helpItem;
    JPanel container;
    panel mainPanel;
    JSplitPane splitPane;
    JButton[] btns;
    Timer timer;

    public mainFrame(String title) {
        super(title);
        splitPane=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(0.2);
        mainPanel=new panel();
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
        blueVehicle.addActionListener(this);
        magentaVehicle = new JMenuItem("Magenta");
        magentaVehicle.addActionListener(this);
        orangeVehicle = new JMenuItem("Orange");
        orangeVehicle.addActionListener(this);
        randomVehicle = new JMenuItem("Random");
        randomVehicle.addActionListener(this);
        help = new JMenu("Help");
        helpItem = new JMenuItem("Help");
        helpItem.addActionListener(this);
        container = new JPanel();
        btns = new JButton[5];
        btns[0] = new JButton("Create road system");
        btns[1] = new JButton("Start");
        btns[2] = new JButton("Stop");
        btns[3] = new JButton("Resume");
        btns[4] = new JButton("Info");
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
        for (int i = 0; i < btns.length; i++) {
            btns[i].addActionListener(this);
            btns[i].setBorder(border);
            container.add(btns[i]);
        }
        container.setLayout(new GridLayout(1, 0));
        add(mainPanel);
        add(container,BorderLayout.SOUTH);
    }

    /**
     * Perform actions listeners
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            System.exit(0);
        }
        if (e.getSource() == blueBackGround) {
            mainPanel.setBackground(Color.BLUE);
        }
        if (e.getSource() == noneBackground) {
            mainPanel.setBackground(Color.WHITE);
        }
        if (e.getSource() == helpItem) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                    "Home Work 3\n" + "GUI @ Threads");
        }
        if(e.getSource() == blueVehicle){
            mainPanel.setVehiclesColor("blue");
        }
        if(e.getSource() == magentaVehicle){
            mainPanel.setVehiclesColor("magenta");
        }
        if(e.getSource() == orangeVehicle){
            mainPanel.setVehiclesColor("orange");
        }
        if(e.getSource() == randomVehicle){
            mainPanel.setVehiclesColor("random");
        }
        for (int i = 0; i < btns.length; i++) {
            if (e.getSource() == btns[i]) {
                switch (i) {
                    case 0: {
                        mainPanel.actionPerformed(e);
                        timer=new Timer(100,(ae)->repaint());
                        timer.start();
                    }
                    break;
                    case 1: {
                        mainPanel.getCreateRoadSys().getD().drive(20);
                    }
                    case 2: {
                        System.out.println("stop");
                    }
                        break;
                    case 3: {
                        }
                        break;
                    case 4:
                    }
                        break;
                }
            }
        }
    }






class panel extends JPanel implements ActionListener,Runnable{
    createRoadSystem createRoadSys;
    Driving driving;
    final int RADIUS = 10;
    boolean isMapCreated;
    String vehiclesColor="blue";
    public panel(){
        setSize(new Dimension(800,600));
        setVisible(true);
        validate();
    }

    public void actionPerformed(ActionEvent e){
        createRoadSys = new createRoadSystem("Create road system");
        createRoadSys.pack();
        createRoadSys.setSize(600, 300);
        createRoadSys.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
            if (createRoadSys != null) {
                if (createRoadSys.getFlag()) {
                    for (Road r : createRoadSys.getD().getMap().getRoads()) {
                        if (r.getEnable()) {
                            g.drawLine((int) r.getStartJunction().getX() + 4, (int) r.getStartJunction().getY() - 3,
                                    (int) r.getEndJunction().getX() + 4, (int) r.getEndJunction().getY() - 2);
                        }

                    }
                    for (Junction j : createRoadSys.getD().getMap().getJunctions()) {
                        if (j instanceof LightedJunction) {
                            if (((LightedJunction) j).getLights().getTrafficLightsOn()) g.setColor(Color.GREEN);
                            else g.setColor(Color.RED);
                        } else g.setColor(Color.BLACK);
                        g.fillOval((int) j.getX(), (int) j.getY(), RADIUS * 2, RADIUS * 2);
                    }
                    driving = createRoadSys.getD();
                    isMapCreated = true;
                    for (int i = 0; i < driving.getVehicles().size(); i++) {
                        drawRotetedVehicle(g, (int) driving.getVehicles().get(i).getLastRoad().getStartJunction().getX(), (int) driving.getVehicles().get(i).getLastRoad().getStartJunction().getY(),
                                (int) driving.getVehicles().get(i).getLastRoad().getEndJunction().getX(), (int) driving.getVehicles().get(i).getLastRoad().getEndJunction().getY(), 10, 8);
                    }
                }
            }
    }

    /**
     * function set the color of the vehicles.
     * @param g
     */
    public void paintVehicels(Graphics g){
        switch (vehiclesColor) {
            case "magenta":
                g.setColor(Color.MAGENTA);
                break;
            case "orange":
                g.setColor(Color.ORANGE);
                break;
            case "random":
            {
                Random r=new Random();
                Color color = new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256),r.nextInt(256));
                g.setColor(color);
            }
            break;
            default:g.setColor(Color.BLUE);
        }
        //repaint();
    }
    private void drawRotetedVehicle(Graphics g, int x1, int y1, int x2, int y2, int d, int h){
        int dx = x2 - x1, dy = y2 - y1, delta = 10;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = delta, xn = xm, ym = h, yn = -h, x;
        double xm1 = delta + d, xn1 = xm1, ym1 = h, yn1 = -h, xx;
        double sin = dy / D, cos = dx / D;
        x = xm*cos - ym*sin + x1;
        xx = xm1*cos - ym1*sin + x1;
        ym = xm*sin + ym*cos + y1;
        ym1 = xm1*sin + ym1*cos + y1;
        xm = x;
        xm1 = xx;
        x = xn*cos - yn*sin + x1;
        xx = xn1*cos - yn1*sin + x1;
        yn = xn*sin + yn*cos + y1;
        yn1 = xn1*sin + yn1*cos + y1;
        xn = x;
        xn1 = xx;
        int[] xpoints = {(int) xm1, (int) xn1,  (int) xn, (int) xm};
        int[] ypoints = {(int) ym1, (int) yn1, (int) yn, (int) ym};
        g.fillPolygon(xpoints, ypoints, 4);
        paintVehicels(g);
        g.fillOval((int) xm1-2,(int) ym1-2,4,4);
        g.fillOval((int) xn1-2,(int) yn1-2,4,4);
        g.fillOval((int) xm-2,(int) ym-2,4,4);
        g.fillOval((int) xn-2,(int) yn-2,4,4);

    }

    public createRoadSystem getCreateRoadSys() {
        return createRoadSys;
    }

    public void setVehiclesColor(String vehiclesColor) {
        this.vehiclesColor = vehiclesColor;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(100);
        }catch (InterruptedException err){}
    }
}