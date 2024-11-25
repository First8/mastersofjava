import java.time.Duration;

/**
 * A timer described by days, hours, minutes and seconds, constructed with a Duration.
 */
public class DhmsTimer extends Timer  {
    private final Duration initialDuration;

    public DhmsTimer (Duration initialDuration, Runnable expiryMethod){
        super(expiryMethod);
        this.initialDuration = initialDuration;
    }

    public int getSeconds(){
        return initialDuration.toSecondsPart();
    }

    public int getMinutes(){
        return initialDuration.toMinutesPart();
    }

    public int getHours(){
        return initialDuration.toHoursPart();
    }

    public int getDays(){
        return (int) initialDuration.toDaysPart();
    }

}
