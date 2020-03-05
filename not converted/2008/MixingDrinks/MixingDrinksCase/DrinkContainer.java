import java.util.ArrayList;
import java.util.List;

/**
 * The DrinkContainer container contains a limited amount of drinks 
 * and is responsible for mixing the provided Wodka and Sju from the
 * Fridge.
 */
public abstract class DrinkContainer {

    /** maximum size of the container. */
    private int size;
    
    /** holds the drinks. */
    private List<WodkaSju> drinks = new ArrayList<WodkaSju>();

    public DrinkContainer(int size) {
        this.size=size;
    }
    
    /**
     * adds a single drink to the container.
     * If the container overflows an exception is thrown.
     * @param ws the drink to add.
     */
    protected void addDrink(WodkaSju ws) {
        if (drinks.size()>=size) throw new RuntimeException(Thread.currentThread().getName()+" overflows the Container. ("+drinks.size()+") What a mess.");
        drinks.add(ws);
    }
    
    /**
     * Makes some new drinks in the container.
     * @param p the person making the drinks.
     * @param sju the sju to add.
     * @param wodka the wodka to add.
     */
    public void makeDrinks(Person p,List<Sju> sju, List<Wodka> wodka) {
        if (p!=null) {
            System.out.println("# "+p+" makes some drinks.");
        }        
        for (int t=0;t<Math.min(sju.size(),wodka.size());t++) {
            this.addDrink(mix(wodka.get(t),sju.get(t)));
        }        
    }

    /**
     * pours a single drink out of the container.
     * @param p the person to pour the drink for.
     * @return a fresh drink or null if there are no more drinks.
     */
    public WodkaSju pourDrink(Person p) {
        if (drinks.isEmpty()) return null;
        return drinks.remove(0);
    }
    
    /**
     * The actual mixing process is a family secret. 
     */
    protected abstract WodkaSju mix(Wodka wodka,Sju sju);

}
