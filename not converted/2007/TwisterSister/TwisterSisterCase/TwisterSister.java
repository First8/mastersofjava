import java.util.Collection;

/**
 * TwisterSister can help your sister to dance. 
 */
public interface TwisterSister {

	/**
	 * Instructs your little sister how to do her dance moves.
	 * @param littleSister your little sister.
	 * @param danceMoves the dance moves to do.
	 */
	public void dance(LittleSister littleSister,Collection<Move> danceMoves);
	
}
