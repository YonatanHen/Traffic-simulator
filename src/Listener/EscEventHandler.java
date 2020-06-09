package Listener;

import components.Moked;

/**
 * Listener class (Listener design pattern implementation) to exit button.
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 */
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

    /**
     * When exit has been pressed, "Trying to exit" output will appear.
     */
    @Override
    public void myEventOccured() {
        System.out.println("Trying to exit...");
    }
}
