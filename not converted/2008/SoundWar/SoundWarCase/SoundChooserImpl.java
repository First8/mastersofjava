import java.util.*;

/**
 * The SoundChooser chooses which sound effects are played based upon the available channels 
 * and the priority of the sound. The rules for playing sound effects are as follows:
 * - If there are unused channels, then the effect is played on that channel with the lowest 
 *   channel number.
 * - If there are no unused channels but there are channels playing lower priority effects then
 *   the effect on the channel playing the lowest priority sound is replaced by the higher priority 
 *   effect. If there are channels playing the same priority effect, the channel with the lowest
 *   channel number is chosen.  
 * - In all other cases the sound effect is skipped. 
 */
public class SoundChooserImpl implements SoundChooser {
    
    /** available channels. */
    private Channel[] channels;
    
    /**
     * Creates a new sound chooser with its available channels. 
     * @param channels the channels to play sound effects on.
     */
    public SoundChooserImpl(Channel[] channels) {
        this.channels=channels;
    }
    
    /**
     * plays the sound effect on the appropriate channel.
     * If no appropriate channel is available the sound effect is skipped.
     * @param sfx the sound effect to play.
     */
	public void play(SoundEffect sfx) {
	    //
	    // TODO : Implement
		//
	    channels[0].play(sfx);
	    //
	}

}
