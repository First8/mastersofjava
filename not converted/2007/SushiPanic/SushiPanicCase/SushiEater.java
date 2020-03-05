/**
 * The SushiEater first selects a Sushi from the Sushi Belt,
 * then it takes the Sushi from the belt and finally it eats
 * the Sushi. 
 */
public interface SushiEater {

	/** 
	 * Selects a sushi to take from the belt.
	 * @param belt the belt to select a sushi from.
	 * @return the picked sushi. 
	 */
	Sushi select();
	/** 
	 * Takes a sushi from the belt. 
	 * @param belt the belt to take the sushi from.
	 * @param the previously picked sushi.
	 */
	void take(SushiBelt belt,Sushi s);
	/** 
	 * Eats the sushi (finally !).
	 * @param s the previously taken sushi. 
	 */
	void eat(Sushi s);
	
}
