import java.time.Duration;

public class Timer {
    private final Runnable expiryMethod;

    protected Timer(Runnable expiryMethod) {
        this.expiryMethod = expiryMethod;
    }

    public void callExpiryMethod() {
        expiryMethod.run();
    }

}
