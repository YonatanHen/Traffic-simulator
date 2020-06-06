package Listener;

import components.Moked;

import java.awt.*;
import java.awt.event.KeyEvent;

public class EscEventHandler implements MyListener{
    public EscEventHandler(){}

    /**
     * exit program
     */
    public synchronized void fireEvent(){
        if(Moked.getState())
            System.exit(0);
        else System.out.println("There are reports that not confirmed it.");
    }

    @Override
    public void myEventOccured() {
        System.out.println("Trying to exit...");
    }
}
