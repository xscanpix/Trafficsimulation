package classes;

import java.util.Calendar;


/**
 * @author      Firstname Lastname <address @ example.com>
 * @version     1.6                 (current version number of program)
 * @since       2010-03-31          (the version of the package this class was first added to)
 */
public class Car {
    
    /**
    * Description of the variable here.
    */
    private static int nCars = 0;
    private int CarID;
    private int bornTime;
    private int dest;
    
    //private Calendar rightNow = Calendar.getInstance();
    
    
    /**
    * Short one line description.                           (1)
    * <p>
    * Longer description. If there were any, it would be    [2]
    * here.
    * <p>
    * And even more explanations to follow in consecutive
    * paragraphs separated by HTML paragraph breaks.
    *
    */ 
    public Car(){
        this.dest = (int)(Math.random() * 2) + 1;
        Car.nCars++;
        bornTime = Simulation.counter;
        CarID = Car.nCars;
    }
    
    /**
     * 
     * @param _dest
     */
    public Car(int _dest){
        
        this.dest = _dest;
        Car.nCars++;
        CarID = Car.nCars;
    }
    
    /**
     * 
     * @return
     */
    public int getBornTime(){
        return bornTime;
    }
    
    /**
     * 
     * @return
     */
    public int getNumberOfCars(){
        return nCars;
    }
    
    /**
     * 
     * @return
     */
    public int getDest(){
        return dest;
    }
    
    @Override
    public String toString() {
        //return "C";
        return "C(" + this.bornTime + "," + this.dest + ")";
    }
	
}