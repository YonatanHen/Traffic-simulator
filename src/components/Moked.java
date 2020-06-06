package components;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * Moked class- ReadWriteLock design pattern
 * @author Yehonatan Hen-207630112
 *  @author Rotem Librati-307903732
 */
public class Moked {
    private final Map<String, Data> m = new TreeMap<String, Data>();
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();
    private static File file;
    private FileReader fr;
    private FileWriter fw;
    private static int counter=0;
    private String state; //state dp
    String fileName="report.txt";
    public Moked(){
        file=new File(fileName);
        try {
            fr=new FileReader(file);
            fw=new FileWriter(file);
        }catch (IOException f) {
            System.out.println(f);
        }
        state="write";
    }

    /**
     * Read from file
     * @param vehicle
     */
    public void get(Vehicle vehicle) {
        r.lock();
        try {
            fr.read();

        } catch (IOException e) {
            e.printStackTrace();
        } finally { r.unlock(); }
    }
    public String[] allKeys() {
        r.lock();
        try { return (String[]) m.keySet().toArray(); }
        finally { r.unlock(); }
    }

    /**
     * Write data to a file
     * @param vehicle
     */
    public void put(Vehicle vehicle) {
        if(state!=null) changeState();//change state from write to read
        w.lock();
        try {
            fw.write("Report #"+(counter++)+"; Time from start route: "+vehicle.getTimeFromStartRoute()+ ", Vehicle ID: " +vehicle.getid()+".\n");
            fw.flush();
        }catch (IOException e){ System.out.println(e);}
        finally { w.unlock(); }
    }
    public void clear() {
        w.lock();
        try { m.clear(); }
        finally { w.unlock(); }
    }

    /**
     * Function read the whole report and return it as a string variable
     * @return
     */
    public String readAllReport(){
        //changeState();// change state from read to write
        r.lock();
        String str="";
        try {
            int data=fr.read();
            while(data!=-1){
                str+=(char)data;
                data=fr.read();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            r.unlock();
            return str;
        }
    }

    /**
     * Function change the state - read/write
     */
    public void changeState(){
        if(state=="read") state="write";
        else state="read";
    }
}
