package classes;

public class Lane {

    public static class OverflowException extends RuntimeException {
        public OverflowException(String message){
            super(message);
        }
    }

    private Car[] theLane;

    public Lane(int n) {
        theLane = new Car[n];
        for (int i = 0; i < n; i++) {
            theLane[i] = null;
        }
    }

    public void step() {
        for (int i = 0; i < theLane.length - 1; i++) {
            if(theLane[i] == null) {
              
                theLane[i] = theLane[i+1];
                theLane[i+1] = null;
            } else
                continue;
        }
    }

    public Car getFirst() {
        if(theLane[0] == null)
            return null;
        
	Car tempCar = theLane[0];
        theLane[0] = null;
        return tempCar;
    }

    public Car firstCar() {
        return theLane[0];
    }


    public boolean lastFree() {
        if(theLane[theLane.length - 1] != null)
        {
            return false;
        }
        else
            return true;
    }

    public void putLast(Car c) throws OverflowException {
        try{
        if(theLane[theLane.length - 1] == null)
            theLane[theLane.length - 1] = c;
        } catch(OverflowException e){
            throw new OverflowException("Can't add a new car last");
            
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < (theLane.length); i++) {
            if(theLane[i] != null) {
                s += " | " + (theLane[i].toString());
            }
            else
                s += " |        ";
        }
        System.out.println();
        return s;
    }

}
