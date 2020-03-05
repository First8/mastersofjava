import java.util.ArrayList;
import java.util.List;


public class FridgeImpl implements Fridge {
    
    private int size;
    
    public FridgeImpl(int size) {
        this.size=size;
    }
    
    @Override
    public List<Sju> getSomeSju() {
        delay(175);
        ArrayList<Sju> s=new ArrayList<Sju>();
        for (int t=0;t<size;t++) s.add(new SjuImpl());
        return s;
    }
    
    @Override
    public List<Wodka> getSomeWodka() {
        delay(25);
        ArrayList<Wodka> w=new ArrayList<Wodka>();
        for (int t=0;t<size;t++) w.add(new WodkaImpl());
        return w;
    }
    
    private void delay(long l) {
        try {
            Thread.sleep(l);
        } catch (InterruptedException ex) {
            // ignore.
        }
    }
}
