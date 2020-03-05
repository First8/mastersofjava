/**
 * Determines which sound effects to play on which channel. 
 */
public interface SoundChooser {

    /**
     * plays the sound effect on the appropriate channel.
     * If no appropriate channel is available the sound effect is skipped.
     * @param sfx the sound effect to play.
     */
    public void play(SoundEffect sfx);
    
}
