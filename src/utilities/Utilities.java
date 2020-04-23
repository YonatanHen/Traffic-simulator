
package utilities;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-
 */
public interface Utilities {
    /**
     * function receive a value and check if it is inside the legal range.
     *
     * @param val
     * @param min
     * @param max
     * @return true if value is legal,else false
     */
    default boolean checkValue(double val,double min,double max) {
       return val<=max && val>=min;
    }

    /**
     * Function print message of changing variable value to the correct one.
     *
     * @param wrongVal
     * @param correctVal
     * @param varName
     */
    default void correctingMessage(double wrongVal, double correctVal, String varName){
        System.out.println("The Value of "+ wrongVal +" is illegal for "+ varName+", therefore has been replaced with "+correctVal);
    }

    /**
     * Function print a message that shows if the value is illegal
     *
     * @param wrongVal
     * @param varName
     */
    default  void errorMessage(double wrongVal, String varName){
        System.out.println("Value "+ wrongVal+ " of " + varName+"is illegal.");
    }


    /**
     * function randomise a boolean value and return him.
     *
     * @return randomal boolean value
     */
    default boolean getRandomBoolean(){
        Random rand=new Random();
        return rand.nextBoolean();
    }

    /**
     * Function return random value in the range between min and max.
     *
     * @param min
     * @param max
     * @return randomal double
     */
    default double getRandomDouble(double min, double max){
        Random rand=new Random();
        return (rand.nextDouble()+rand.nextInt((int)max)+min);
    }

    /**
     * Function randomise an integer value in the range of min-max
     * and return him.
     *
     * @param min
     * @param max
     * @return Integer value in the range of min-max
     */
    default int getRandomInt(int min, int max){
        //TODO:Why double in the instructions? it suppose to be int

        Random rand=new Random();
        return rand.nextInt(max)+min;
    }

    /**
     * Function return an array list of randomal integers between min and max
     *
     * @param min
     * @param max
     * @param arraySize
     * @retrn Randomal array list of integers
     */
    default ArrayList<Integer> getRandomIntArray(int min, int max, int arraySize){

        ArrayList<Integer> arrList=new ArrayList<>();
        for(int i = 0; i<arraySize; i++){
            arrList.add(getRandomInt(min,max));
        }
        return arrList;
    }

    /**
     * Function prints success message when an object created successfully.
     *
     * @param objName
     */
    default void successMessage(String objName){
        System.out.println(objName+" has been created.");
    }
}
