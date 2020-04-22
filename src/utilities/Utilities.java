//Yehonatan Hen-207630112
//Rotem Librati-

package utilities;

import java.util.ArrayList;
import java.util.Random;

public interface Utilities {
    default boolean checkValue(double val,double min,double max) {
    //Function return true if val value is legal
       return val<=max && val>=min;
    }

    default void correctingMessage(double wrongVal, double correctVal, String varName){
    //Function print message of changing variable value to the correct one.
        //TODO:Find if this implementation match the test output as demand.
        System.out.println("Value of "+ varName+" has been changed from "+ wrongVal+" to "+correctVal+".");
    }

    default  void errorMessage(double wrongVal, String varName){
    //Function print a message that shows if the value is illegal
        System.out.println("Value "+ wrongVal+ " of " + varName+"is illegal.");
    }

    default boolean getRandomBoolean(){
    //Function return randomal boolean
        Random rand=new Random();
        return rand.nextBoolean();
    }

    default double getRandomDouble(double min, double max){
    //Function return random value in the range between min and max
        Random rand=new Random();
        return (rand.nextDouble()+rand.nextInt((int)max)+min);
    }

    default int getRandomInt(int min, int max){
        //TODO:Why double in the instructions? it suppose to be int
    //Function return integer value in the range of min-max
        Random rand=new Random();
        return rand.nextInt(max)+min;
    }

    default ArrayList<Integer> getRandomIntArray(int min, int max, int arraySize){
    //Function return an array list of randomal integers between min and max
        ArrayList<Integer> arrList=new ArrayList<>();
        for(int i = 0; i<arraySize; i++){
            arrList.add(getRandomInt(min,max));
        }
        return arrList;
    }

    default void successMessage(String objName){
    //Function prints a message of successful creation of object
        System.out.println(objName+" created successfully.");
    }
}
