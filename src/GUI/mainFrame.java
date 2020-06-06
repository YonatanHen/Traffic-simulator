package GUI;



import components.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.lang.management.ThreadInfo;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.*;

/**
 * implementation of main frame
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 * @see panel
 */
public class mainFrame extends JFrame implements ActionListener {
    JMenuBar menuBar;
    JMenu file, background, vehicleColor, help,buildMap,cloneCar,reports;
    JMenuItem exit, blueBackGround, noneBackground, blueVehicle, magentaVehicle, orangeVehicle, randomVehicle, helpItem, reportItem,clone, cityMap, countryMap;
    JPanel container;
    panel mainPanel;
    JSplitPane splitPane;
    JButton[] btns;
    createRoadSystem createRoadSys;
    reportsFrame rf;
    Driving driving;
    int countPressInfo=0;
    boolean isCreated=false;
    JTable table;
    cloneCarInput cloneCarInput;

    /**
     * Costructor of the main frame
     * @param title
     */
    public mainFrame(String title) {
        super(title);
        splitPane=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(0.2);
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
        buildMap=new JMenu("Build a map");
        buildMap.addActionListener(this);
        cityMap=new JMenuItem("City map");
        cityMap.addActionListener(this);
        countryMap = new JMenuItem("Country Map");
        countryMap.addActionListener(this);
        cloneCar=new JMenu("Clone a car");
        cloneCar.addActionListener(this);
        clone=new JMenuItem("clone");
        clone.addActionListener(this);
        reports=new JMenu("Reports");
        reports.addActionListener(this);
        reportItem=new JMenuItem("Print Reports");
        reportItem.addActionListener(this);
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
        buildMap.add(cityMap);
        buildMap.add(countryMap);
        help.add(helpItem);
        reports.add(reportItem);
        menuBar.add(file);
        cloneCar.add(clone);
        menuBar.add(background);
        menuBar.add(vehicleColor);
        menuBar.add(help);
        menuBar.add(buildMap);
        menuBar.add(cloneCar);
        menuBar.add(reports);
        setJMenuBar(menuBar);
        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
        for (int i = 0; i < btns.length; i++) {
            btns[i].addActionListener(this);
            btns[i].setBorder(border);
            container.add(btns[i]);
        }
        container.setLayout(new GridLayout(1, 0));
        add(container,BorderLayout.SOUTH);
    }

    /**
     * Perform actions listeners
     *
     * @param e
     */
    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == exit) {
            System.exit(0);
        }
        if (e.getSource() == blueBackGround && mainPanel!=null) {
            mainPanel.setBackground(Color.BLUE);
            revalidate();
        }
        if (e.getSource() == noneBackground && mainPanel!=null) {
            mainPanel.setBackground(Color.LIGHT_GRAY);
            revalidate();
        }
        if (e.getSource() == helpItem) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                    "Home Work 3\n" + "GUI @ Threads");
        }
        if(e.getSource() == blueVehicle && mainPanel!=null){
            mainPanel.setVehiclesColor("blue");
        }
        if(e.getSource() == magentaVehicle && mainPanel!=null){
            mainPanel.setVehiclesColor("magenta");
        }
        if(e.getSource() == orangeVehicle && mainPanel!=null){
            mainPanel.setVehiclesColor("orange");
        }
        if(e.getSource() == randomVehicle && mainPanel!=null){
            mainPanel.setVehiclesColor("random");
        }
        if(e.getSource()==buildMap){
            //TODO
        }
        if(e.getSource()==clone && mainPanel!=null){
             cloneCarInput=new cloneCarInput(mainPanel.getDriving());
             repaint();
        }
        if(e.getSource()==reportItem && isCreated){
            rf=new reportsFrame(driving.getMoked().readAllReport());
            rf.pack();
            rf.setVisible(true);
        }
        for (int i = 0; i < btns.length; i++) {
            if (e.getSource() == btns[i]) {
                switch (i) {
                    case 0: {
                        if(isCreated) mainPanel.getDriving().setRunning(false);
                        createRoadSys = new createRoadSystem("Create road system", mainPanel, this);
                        createRoadSys.pack();
                        createRoadSys.setSize(600, 300);
                        createRoadSys.setVisible(true);
                        isCreated=true;
                    }
                    break;
                        case 1: {
                                mainPanel.getDriving().drive(100);
                        }
                        break;
                        case 2: {
                                mainPanel.getDriving().Stop();
                        }
                        break;
                        case 3: {
                                mainPanel.getDriving().Continue();
                        }
                    break;
                    case 4:
                        countPressInfo++;
                        if(countPressInfo%2==0) {
                           mainPanel.repaint();
                            }
                        else {
                            driving = mainPanel.getDriving();
                            String[] col = new String[]{"Vehicle #", "Type", "Location", "Time on loc", "Speed"};
                            String[][] data = new String[driving.getVehicles().size()][5];
                            for (int j = 0; j < driving.getVehicles().size(); j++) {
                                data[j][0] = String.valueOf(driving.getVehicles().get(j).getid());
                                data[j][1] = driving.getVehicles().get(j).getVehicleType().toString();
                                if (driving.getVehicles().get(j).getCurrentRoutePart() instanceof Road)
                                    data[j][2] = "Road " + driving.getVehicles().get(j).getLastRoad().getStartJunction().getJunctionName()
                                            +"-"+driving.getVehicles().get(j).getLastRoad().getEndJunction().getJunctionName();
                                else if (driving.getVehicles().get(j).getCurrentRoutePart() instanceof Junction)
                                    data[j][2] = "Junction"+ ((Junction)driving.getVehicles().get(j).getCurrentRoutePart()).getJunctionName();
                                data[j][3] = String.valueOf(driving.getVehicles().get(j).getTimeOnCurrentPart());
                                data[j][4] = String.valueOf(driving.getVehicles().get(j).getVehicleType().getAverageSpeed()*10);
                            }
                            table = new JTable(data, col);
                            table.setMinimumSize(new Dimension(200, 200));
                            JScrollPane pane = new JScrollPane(table);
                            pane.setMinimumSize(new Dimension(150, 23));
                            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                            add(pane);
                            revalidate();
                        }
                }
                break;
            }
        }
    }


    /**
     * setting the main panel in the main frame
     * @param mainPanel
     */
    public void setMainPanel(panel mainPanel) {
        this.mainPanel = mainPanel;
        add(mainPanel,0);
    }
    public panel getMainPanel(){
        return mainPanel;
    }

    /**
     * run the GUI repaint every 100 millis
     */
    public void run() {
        try {
            sleep(100);
        } catch (InterruptedException e) {
        }
        revalidate();
        mainPanel.repaint();
    }
}



class panel extends JPanel {

    Driving driving;
    final int RADIUS = 10;
    String vehiclesColor = "blue";
    mainFrame mainFrame;
    boolean isRandomalColor=false;
    Color c;
    int countRandomalColoredVehicels=0;


    /**
     * Class implement the main panel of the frame
     * @param mainFrame
     */
    public panel(mainFrame mainFrame) {
        setSize(new Dimension(800, 600));
        setVisible(true);
        validate();
        this.mainFrame = mainFrame;
    }

    /**
     * Function draw the system
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        //drawing roads
        for (Road r : driving.getMap().getRoads()) {
            g2d.setColor(Color.BLACK);
            g2d.drawLine((int) r.getStartJunction().getX(),
                    (int) r.getStartJunction().getY(),
                    (int) r.getEndJunction().getX(),
                    (int) r.getEndJunction().getY());
        }
        //drawing green arrows
        drawArrows(driving.getMap().getRoads(),g2d);
        //drawing junctions
        for (Junction j : driving.getMap().getJunctions()) {
            if (j instanceof LightedJunction) {
                if (((LightedJunction) j).getLights().getTrafficLightsOn()) g2d.setColor(Color.GREEN);
                else g2d.setColor(Color.RED);
            } else g2d.setColor(Color.BLACK);
            g2d.fillOval((int) j.getX()-10, (int) j.getY()-10, RADIUS * 2, RADIUS * 2);
        }
        //drawing vehicles
        for (int i = 0; i < driving.getVehicles().size(); i++) {
            if(driving.getVehicles().get(i).getCurrentRoutePart() instanceof Junction){
                drawRotetedVehicle(g2d,
                        (int) driving.getVehicles().get(i).getX(),
                        (int) driving.getVehicles().get(i).getY(),
                        (int) ((Junction) driving.getVehicles().get(i).getCurrentRoutePart()).getX(),
                        (int) ((Junction) driving.getVehicles().get(i).getCurrentRoutePart()).getY(), 8, 4);
            }if(driving.getVehicles().get(i).getCurrentRoutePart() instanceof Road) {
                drawRotetedVehicle(g2d,
                        (int) driving.getVehicles().get(i).getX(),
                        (int) driving.getVehicles().get(i).getY(),
                        (int) ((Road)driving.getVehicles().get(i).getCurrentRoutePart()).getEndJunction().getX(),
                        (int) ((Road)driving.getVehicles().get(i).getCurrentRoutePart()).getEndJunction().getY(), 10, 4);
            }
        }

    }

    /**
     * function set the color of the vehicles.
     *
     * @param g
     */
    public void paintVehicels(Graphics g) {
        switch (vehiclesColor) {
            case "magenta": {
                isRandomalColor=false;
                g.setColor(Color.MAGENTA);
            }
                break;
            case "orange": {
                isRandomalColor=false;
                g.setColor(Color.ORANGE);
            }
                break;
            case "random": {
                if(getDriving().getVehicles().size()>countRandomalColoredVehicels) {
                    Random r = new Random();
                    Color color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
                    if(!isRandomalColor) g.setColor(color);
                    c = color;
                    countRandomalColoredVehicels++;
                }
                else isRandomalColor=true;
            }
            break;
            default: {
                isRandomalColor=false;
                g.setColor(Color.BLUE);
            }
        }

    }

    private void drawRotetedVehicle(Graphics2D g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1, dy = y2 - y1, delta = 10;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = delta, xn = xm, ym = h, yn = -h, x;
        double xm1 = delta + d, xn1 = xm1, ym1 = h, yn1 = -h, xx;
        double sin = dy / D, cos = dx / D;
        x = xm * cos - ym * sin + x1;
        xx = xm1 * cos - ym1 * sin + x1;
        ym = xm * sin + ym * cos + y1;
        ym1 = xm1 * sin + ym1 * cos + y1;
        xm = x;
        xm1 = xx;
        x = xn * cos - yn * sin + x1;
        xx = xn1 * cos - yn1 * sin + x1;
        yn = xn * sin + yn * cos + y1;
        yn1 = xn1 * sin + yn1 * cos + y1;
        xn = x;
        xn1 = xx;
        int[] xpoints = {(int) xm1, (int) xn1, (int) xn, (int) xm};
        int[] ypoints = {(int) ym1, (int) yn1, (int) yn, (int) ym};
        paintVehicels(g);
        if(isRandomalColor) g.setColor(c);
        g.fillPolygon(xpoints, ypoints, 4);
        g.setColor(Color.BLACK);
        g.fillOval((int) xm1 - 2, (int) ym1 - 2, 4, 4);
        g.fillOval((int) xn1 - 2, (int) yn1 - 2, 4, 4);
        g.fillOval((int) xm - 2, (int) ym - 2, 4, 4);
        g.fillOval((int) xn - 2, (int) yn - 2, 4, 4);
    }

    public void drawArrows(ArrayList<Road> roads,Graphics2D g2d) {
        for (Road r : roads) {
            if (r.getEnable() && r.getEndJunction() instanceof LightedJunction) {
                double x1 = r.getEndJunction().getX(), y1 = r.getEndJunction().getY(), h = 4, d = 14;
                int dx = (int) (r.getStartJunction().getX() - r.getEndJunction().getX()), dy = (int) (r.getStartJunction().getY() - r.getEndJunction().getY()), delta = 10;
                double D = Math.sqrt(dx * dx + dy * dy);
                double xm = delta, xn = xm, ym = h, yn = -h, x;
                double xm1 = delta + d, xn1 = xm1, ym1 = h, yn1 = -h, xx;
                double sin = dy / D, cos = dx / D;
                x = xm * cos - ym * sin + x1;
                xx = xm1 * cos - ym1 * sin + x1;
                ym = xm * sin + ym * cos + y1;
                ym1 = xm1 * sin + ym1 * cos + y1;
                xm = x;
                xm1 = xx;
                x = xn * cos - yn * sin + x1;
                xx = xn1 * cos - yn1 * sin + x1;
                yn = xn * sin + yn * cos + y1;
                yn1 = xn1 * sin + yn1 * cos + y1;
                xn = x;
                xn1 = xx;
                int[] xpoints = {(int) xm1, (int) xn1, (int) (xn + xm) / 2};
                int[] ypoints = {(int) ym1, (int) yn1, (int) (yn + ym) / 2};
                g2d.setColor(Color.GREEN);
                g2d.fillPolygon(xpoints, ypoints, 3);
            }
        }
    }

    /**
     * set vehicles color and repaint
     * @param vehiclesColor
     */
    public void setVehiclesColor(String vehiclesColor) {
        this.vehiclesColor = vehiclesColor;
        repaint();
    }

    public void setDriving(Driving d) {
        driving = d;
    }

    public Driving getDriving() {
        return driving;
    }
    public mainFrame getMainFrame(){
        return mainFrame;
    }

}
