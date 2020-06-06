package Mediator;

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
    public void receiveReport(String directory) throws FileNotFoundException {
        //TODO: Add specific report to array list
        if(br==null) br=new BufferedReader(new FileReader(directory));

    }

    public void sendConfirmation(String report){
            System.out.println(report+ "confirmed");
    }

    @Override
    public void run() {
        String line= null;
        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(line!=null) {
            try {
                line=br.readLine();
                if (reports.get(reports.size() - 1).equals(line)) {
                    System.out.println("AAAAAAAAAAAAAAAAAA");
                    System.out.println(this+" reading report: "+reports.get(reports.size()-1));
                    sendConfirmation(reports.get(reports.size()-1));
                    reports.remove(reports.size()-1);
                }
                else reportReaded=false;
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(line==null) reportReaded=true;
        }
    }
}
