/**
 * Represents a thirsty person with a fondness for WodkaSju! 
 */
public final class Person {

    private String name;

    public Person(String name) {
        this.name = name;
    }

    /**
     * Instructs the person to sip from a new drink. When this
     * method returns the person must have poured and sipped from
     * a single drink.
     * @param drinkContainer the container pour the drink from.
     * @param fridge the fridge to get fresh ingredients from.
     */
    public void drink(DrinkContainer drinkContainer, Fridge fridge) {
        //
        // TODO: make this work for more than one person for more than one drink. 
        //
        drinkContainer.pourDrink(this).sip(this);
        //
        //      
    }

    public String toString() {
        return name;
    }

}
