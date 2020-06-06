package Mediator;

import components.Driving;
import components.Vehicle;

import java.io.*;
import java.util.ArrayList;

public class Driver implements Runnable {
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
    public void receiveReport(String report,String directory) throws FileNotFoundException {
        reports.add(report);
        if(br==null) br=new BufferedReader(new FileReader(directory));

    }

    public String sendConfirmation(String report){
            System.out.println(report+ "confirmed");
    }

    @Override
    public void run() {
        while(true) {
            try {
                if (reports.get(reports.size() - 1) != br.readLine()) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(this+" reading report: "+reports.get(reports.size()-1));
            sendConfirmation(reports.get(reports.size()-1));
            reports.remove(reports.size()-1);
        }

        }
    }
}
