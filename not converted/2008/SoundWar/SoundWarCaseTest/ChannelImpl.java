
public class ChannelImpl implements Channel {
    
    private SoundEffect sfx;
    private int nr;
    
    public ChannelImpl(int nr) {
        this.nr=nr;
    }
    
    @Override
    public int getNumber() {
        return nr;
    }
    
    @Override
    public int compareTo(Channel o) {
        if (isPlaying()==o.isPlaying()) {
            // Same, so channel nr decides.
            return getNumber()-o.getNumber();
            //
        } else {
            // different
            // I am playing, other channel is not, so more important.
            if (isPlaying()) return 1;
            // Other os playing, i am  not, so i am more important.
            if (o.isPlaying()) return -1;
            //
            return 0;
        }
    }
    @Override
    public SoundEffect getCurrentEffect() {
        return sfx;
    }
    @Override
    public boolean isPlaying() {
        return sfx!=null;
    }
    @Override
    public void play(SoundEffect sfx) {
        if (isPlaying()) throw new RuntimeException("Cant start a sound on playing "+this+". Please stop() it first.");
        if (sfx==null) throw new NullPointerException("Cannot play a NULL sound.");
        this.sfx=sfx;
        play=true;
    }
    @Override
    public void stop() {
        if (sfx==null) throw new NullPointerException("There is no sound effect to be stopped.");
        sfx=null;       
        stop=true;
    }
    
    @Override
    public String toString() {
        return "channel "+nr+""+(isPlaying()?" - playing "+sfx:" - silent.");
    }
    
    //
    //
    //
    
    boolean play;
    boolean stop;
    
    void reset() {
        play=false;
        stop=false;
    }

    boolean isPlayCalled() {
        return play;
    }
    
    boolean isStopCalled() {
        return stop;
    }
    
}
