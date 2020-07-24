package Mediator;

import components.Vehicle;

import java.io.*;
import java.util.ArrayList;


/**
 * Implementation of Mediator design pattern by Driver class, the driver serves as mediator between his vehicle and the moked.
 * @author Yehonatan Hen
 * @author Rotem Librati
 * @see Vehicle
 * @see components.Moked
 */
public class Driver {
    private Vehicle vehicle;
    boolean reportReaded=false;
    BufferedReader br;
    ArrayList<String> reports;
    String reportDir=null;
    public Driver(Vehicle v){
        vehicle=v;
        reports=new ArrayList<>();
        br=null;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Function receive directory of txt file and initialize buffered reader object.
     * @param directory
     * @throws FileNotFoundException
     */
    public void receiveReport(String directory) throws FileNotFoundException {
        if(br==null) br=new BufferedReader(new FileReader(directory));
    }

    /**
     * function read the report.
     * @throws IOException
     */
    public void readReport() throws IOException {
        String line=br.readLine();
        while (line!=null){
            if(line.contains("ID: "+ vehicle.getid()+".") && reports.indexOf(line)==-1) {
                System.out.println(line + " confirmed by the driver.");
                reports.add(line);
            }
            line=br.readLine();
        }
    }
}

