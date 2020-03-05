
public class SoundEffectImpl implements SoundEffect {

    private String name;
    private int prio;
    
    public SoundEffectImpl(String name,int prio) {
        this.name=name;
        this.prio=prio;
    }
    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(SoundEffect o) {
        if (o==null) return 1;
        return this.getPriority()-o.getPriority();
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public int getPriority() {
        return prio;
    }
    
    @Override
    public String toString() {
        return "SFX("+prio+","+name+")";
    }
}
