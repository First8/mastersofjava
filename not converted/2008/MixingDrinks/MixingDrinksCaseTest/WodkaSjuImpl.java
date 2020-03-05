
public class WodkaSjuImpl implements WodkaSju {

    private static ThreadLocal<WodkaSju> lastDrinkSipped=new ThreadLocal<WodkaSju>();

    private Wodka w;
    private Sju s;
    private boolean finished;
    
    public WodkaSjuImpl(Wodka w,Sju s) {
        this.w=w;
        this.s=s;
    }
    
    @Override
    public String toString() {
        return w.toString()+s.toString();
    }
    
    public void sip(Person p) {
        if (finished) throw new NullPointerException("Empty Drink!");
        System.out.println(". "+p+" drinks a "+this);
        lastDrinkSipped.set(this);
        finished=true;
    }
    
    public boolean isFinished() {
        return finished;
    }
    
    public static WodkaSju getLastDrinkSipped() {
        return lastDrinkSipped.get();
    }
    
}