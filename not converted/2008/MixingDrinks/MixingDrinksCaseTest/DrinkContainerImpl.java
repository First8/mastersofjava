
public class DrinkContainerImpl extends DrinkContainer {
    
    private RuntimeException failure;
    private ThreadLocal<WodkaSju> lastDrinkPoured=new ThreadLocal<WodkaSju>();

    public DrinkContainerImpl(int size) {
        super(size);
    }
    
    @Override
    protected WodkaSju mix(Wodka wodka, Sju sju) {        
        return new WodkaSjuImpl(wodka,sju);
    }
    
    @Override
    public void addDrink(WodkaSju ws) {        
        try {
            super.addDrink(ws);
        } catch (RuntimeException ex) {
            failure=ex;
            throw ex;
        }
    }
    
    @Override
    public WodkaSju pourDrink(Person p) {        
        //
        try {Thread.sleep(10);} catch (InterruptedException ex) { }
        //
        WodkaSju ws=super.pourDrink(p);
        lastDrinkPoured.set(ws);
        return ws;
    }
    
    public WodkaSju getLastDrinkPoured() {
        return lastDrinkPoured.get();
    }    
    
    public RuntimeException getFailure() {
        return failure;
    }
}
