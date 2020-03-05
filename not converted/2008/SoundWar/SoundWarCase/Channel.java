
/**
 * Describes an audio Channel on which a SoundEffect can be played.
 * 
 * The natural order for a channel is first decided by 
 * its playing property and secondly by its channel number.
 * First come the non playing channels in channel order 
 * and then the playing channels in channel order. 
 */

public interface Channel extends Comparable<Channel> {

    /**
     * @return the channel number.
     */
    int getNumber();    
    
    /**
     * @param sfx plays the given sound effect.
     */
    void play(SoundEffect sfx);
    
    /**
     * @return the current (playing) sound effect or null if none.
     */
    SoundEffect getCurrentEffect();
    
    /**
     * @return true if there is a sound effect playing.
     */
    boolean isPlaying();
    
    /**
     * stops playing the current sound effect. 
     */
    void stop();
    
}
