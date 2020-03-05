/**
 * Represents a sound effect.
 * 
 * The natural order of a sound effect is determined by its priority.
 * High priority sounds come first, followed by the lower priority sounds. 
 */
public interface SoundEffect extends Comparable<SoundEffect> {

    /**
     * @return the name of the sound effect.
     */
    String getName();
   
    /**
     * @return the priority of the sound on a scale of 0..4 (0=important, 4=not important).
     */
    int getPriority();
    
}
