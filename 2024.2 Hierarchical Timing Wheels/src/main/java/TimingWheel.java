import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A circular buffer with a number of slots each representing one unit of time. The current time is represented by the {@code currentSlot}.
 * Each slot contains a list of timers that expire at the time represented by that slot.
 */
public class TimingWheel {
    private final int maxSlotNumber;
    private final List<Timer>[] slots;

    private int currentSlot;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public TimingWheel(int numberOfSlots) {
        this.maxSlotNumber = numberOfSlots - 1;
        this.slots = new List[numberOfSlots];
        Arrays.setAll(this.slots, i -> new ArrayList<>());
        this.currentSlot = 0;
    }

    public boolean isAtFirstSlot() {
        return currentSlot == 0;
    }

    /**
     * Insert a {@link Timer} into this TimingWheel {@code quantity} time units in the future
     * @param timer The timer to insert
     * @param nrOfSlots The number of time units into the future this timer is about to expire
     */
    public void insert(Timer timer, int nrOfSlots) {
        slots[currentSlot + nrOfSlots].add(timer);
    }

    /**
     * Each unit of time, this method has to be called. It moves the current time index by one slot around the ring.
     * @return A list of timers that expire in the current time unit.
     */
    public List<Timer> advance() {
        currentSlot = currentSlot == maxSlotNumber ? 0 : currentSlot + 1;
        List<Timer> expiredTimers = slots[currentSlot];
        slots[currentSlot] = new ArrayList<>();
        return expiredTimers;
    }

    @Override
    public String toString() {
        return "[wheel of " + slots.length + " at " + currentSlot + "]";
    }
}
