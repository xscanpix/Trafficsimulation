package classes;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Simulation {

    static TrafficSystem ts;
    static Runnable runnable;
    static Calendar rightNow;
    static Calendar rightThen;
    
    static int counter;
  

    final static int simulationTime = 500;
    
    public static void main(String[] args) throws Exception{
        
        final int updateIntervalMilliseconds = 1;

        ts = new TrafficSystem();
        
        
        
//        runnable = new Runnable() {
//
//            @Override 
//            public void run() {
                for (int i = 0; i < ts.getNumberOfRuns(); i++) {
                    counter = 0;
                    ts.readParameters();
                    System.out.println("Run" + ts.getRuns());
                while(counter < simulationTime){
                    
                    ts.step();
                    
                    counter++;

                } 
                ts.printStatistics();
                }
//            }
            
//        };
        
//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//        executor.scheduleAtFixedRate(runnable, 0, updateIntervalMilliseconds, TimeUnit.MILLISECONDS);
        
    }  
}