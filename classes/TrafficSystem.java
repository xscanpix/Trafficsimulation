package classes;

import java.io.FileReader;
import java.util.Properties;

public class TrafficSystem {
    private Lane  r0;
    private Lane  r1;
    private Lane  r2;
    private Light s1;
    private Light s2;
    
    private int randomizer;
    private int totalTime;
    private int totalPassedCars;
    private int maxTime;
    private float averageTime;
    private int run;
    private int numberOfRuns;
    
    Properties defaultProperties;

    public TrafficSystem() {
        defaultProperties = new Properties();
        
        try{
            FileReader in = new FileReader("/home/svante/NetBeansProjects/Trafiksimulering/src/classes/properties.properties");
            defaultProperties.load(in);
            in.close();
            numberOfRuns = defaultProperties.size();
        } catch (Exception e){
        }
        run = 1;
        
        
        
        r0 = null;
        r1 = null;
        r2 = null;
        s1 = null;
        s2 = null;
    }
    
    public int getNumberOfRuns(){
        return this.numberOfRuns;
    }
    
    public int getRuns(){
        return run;
    }

    public void readParameters() {
        
        totalTime = 0;
        totalPassedCars = 0;
        maxTime = 0;
        averageTime = 0;
   
        
        String s = defaultProperties.getProperty("run" + run);
        
        String[] parameters = s.split(",");
        
        r0 = new Lane(Integer.parseInt(parameters[0]));
        r1 = new Lane(Integer.parseInt(parameters[1]));
        r2 = new Lane(Integer.parseInt(parameters[1]));
        s1 = new Light(Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]));
        s2 = new Light(Integer.parseInt(parameters[2]), Integer.parseInt(parameters[4]));
        
        randomizer = Integer.parseInt(parameters[5]);
        
        run++;
        
        //defaultProperties.list(System.out);
        
	// L�ser in parametrar f�r simuleringen
	// Metoden kan l�sa fr�n terminalf�nster, dialogrutor
	// eller fr�n en parameterfil. Det sista alternativet
	// �r att f�redra vid uttestning av programmet eftersom
	// man inte d� beh�ver mata in v�rdena vid varje k�rning.
        // Standardklassen Properties �r anv�ndbar f�r detta. 
    }

    public void step(){
        //Random the interval between the cars
        
        if(((Math.random() * 10) + 1) < randomizer) r0.putLast(new Car());      
        
        
        //Remove car at light s1 if green signal
        if(s1.isGreen()) {
            if(r1.firstCar() != null){
                int tempTime = (Simulation.counter - r1.firstCar().getBornTime());
                totalTime += tempTime;
                if(tempTime > maxTime)
                    maxTime = tempTime;
                
                totalPassedCars++;
                averageTime = (totalTime / totalPassedCars);
            }
            r1.getFirst();
        }                                         
        
        // Remove car at light s2 if green signal
        if(s2.isGreen()) {
            if(r2.firstCar() != null){
                int tempTime = (Simulation.counter - r2.firstCar().getBornTime());
                totalTime += tempTime;
                if(tempTime > maxTime)
                    maxTime = tempTime;
                
                totalPassedCars++;
                averageTime = ((float)totalTime / (float)totalPassedCars);
                
            }
            r2.getFirst();
        }                                         
        
        
        
        r1.step();
        r2.step();
        
        // Check is possible to move from r0 to r1/r2. If possible, do it
        if(r0.firstCar() != null) {
            if(r0.firstCar().getDest() == 1 && r1.lastFree()){
                r1.putLast(r0.getFirst());
            }else if(r0.firstCar().getDest() == 2 && r2.lastFree()) {
                r2.putLast(r0.getFirst());
            }
        }
        
        // Lane stepping
        

        r0.step();
        s1.step();
        s2.step();
    }

    public void printStatistics() {
        System.out.println("Maxtime: " + maxTime + " Average time: " + averageTime + "\n");
            
        
    }

    public void print() {
	// TODO
    }
    
    @Override
    public String toString(){
        String s = "";
        return s1.toString() + " " +  
               "r1(" + r1.toString() + ")" + 
               "r0(" + r0.toString() + ")\n" + 
               s2.toString() + " " +
               "r2(" + r2.toString() + ")\n";
//               "r0(" + r0.toString() + ")\n" + 
//               "s1(" + s1.toString() + ")\n" +
//               "r1(" + r1.toString() + ")\n" + 
//               "s2(" + s2.toString() + ")\n" + 
//               "r2(" + r2.toString() + ")\n";
    }

}
