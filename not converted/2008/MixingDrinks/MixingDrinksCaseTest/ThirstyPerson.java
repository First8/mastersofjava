
public class ThirstyPerson implements Runnable {

    private Person p;
    private long drinks;
    private DrinkContainerImpl dc;
    private Fridge fridge;
    private boolean success;
    private boolean running;
    private Throwable failure;
    
    public ThirstyPerson(DrinkContainerImpl dc,Person p,Fridge f,int drinks) {
        this.p=p;
        this.drinks=drinks;
        this.dc=dc;
        this.fridge=f;
    }
    
    @Override
    public void run() {
        running=true;
        success=false;
        try {
            while (drinks>0) {
                System.out.println(". "+p+" wants a drink.");
                p.drink(dc,fridge);
                //
                WodkaSju poured=dc.getLastDrinkPoured();
                WodkaSju sipped=WodkaSjuImpl.getLastDrinkSipped();
                //
                if (sipped==null) throw new NullPointerException(p+" didn't sip from a drink.");
                if (poured==null) throw new NullPointerException(p+" didn't drink a real WodkaSju from the container.");
                if (!poured.equals(sipped)) throw new NullPointerException(p+" drank a different WodkaSju than the one that was poured.");
                //
                drinks--;
            }
            success=true;
        } catch (Exception ex) {
            success=false;
            failure=ex;
            ex.printStackTrace(System.out);
        } finally {
            running=false;
        }        
    }
    
    public boolean isRunning() {
        return running;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public Throwable getFailure() {
        return failure;
    }
    
    public long getRuntime() {
        return drinks*2000L; // max!
    }
    
    @Override
    public String toString() {
        return p.toString();
    }
    
}
