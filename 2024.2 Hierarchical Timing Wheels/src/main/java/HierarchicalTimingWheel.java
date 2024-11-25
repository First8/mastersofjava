import java.util.List;
import java.util.function.Function;

/**
 * Multiple {@link TimingWheel}s in a hierarchy
 */
public class HierarchicalTimingWheel {

    private final TimingWheel timingWheel;

    private HierarchicalTimingWheel nextSmallerWheel;
    private HierarchicalTimingWheel nextBiggerWheel;

    private final Function<Timer, Integer> nrOfSlotsMethod;

    /**
     * Constructs the smallest wheel of a TimingWheel hierarchy, to add more wheels, use the {@link #addBiggerWheel(int, Function)} method.
     * @param quantity The nr of slots this wheel is based upon.
     * @param nrOfSlotsMethod A function to get the quantity of the time unit of this wheel from the initial duration of a {@link Timer}. E.g the number of hours.
     */
    public HierarchicalTimingWheel(int quantity, Function<Timer, Integer> nrOfSlotsMethod) {
        this.timingWheel = new TimingWheel(quantity);
        this.nrOfSlotsMethod = nrOfSlotsMethod;
    }

    public HierarchicalTimingWheel addBiggerWheel(int quantity, Function<Timer, Integer> quantityMethod) {
        this.nextBiggerWheel = new HierarchicalTimingWheel(quantity, quantityMethod);
        this.nextBiggerWheel.setSmallerWheel(this);
        return this.nextBiggerWheel;
    }

    private void setSmallerWheel(HierarchicalTimingWheel nextSmallerWheel) {
        this.nextSmallerWheel = nextSmallerWheel;
    }

    /**
     * Adds a {@link Timer} to the hierarchy of timingWheels. Call this method on the smallest wheel in the hierarchy,
     * the method will determine the correct wheel to insert the Timer initially.
     * @param timer The timer to add
     * @return Whether the Timer was added successfully. Will return false if the duration of the timer is smaller than one slot of this wheel.
     */
    public boolean add(Timer timer) {
        if (nextBiggerWheel != null && nextBiggerWheel.add(timer)) {
            return true;
        } else {
            int nrOfSlots = nrOfSlotsMethod.apply(timer);
            if (nrOfSlots == 0) {
                return false;
            } else {
                timingWheel.insert(timer, nrOfSlots);
                return true;
            }
        }
    }

    /**
     * Inserts the timer into the current wheel or propagates to a smaller wheel if the quantity for this wheel is zero.
     * @param timer The {@link Timer} to insert.
     */
    private void insert(Timer timer) {
        int quantity = nrOfSlotsMethod.apply(timer);
        if (quantity == 0) {
            nextSmallerWheel.insert(timer);
        } else {
            timingWheel.insert(timer, quantity);
        }
    }

    /**
     * Advances the TimingWheel one slot.
     * Every time one complete revolution is made, the next biggest wheel is advanced one slot (e.g. if the 'seconds' wheel reaches 0, the 'minutes' wheel is advanced.)
     * All timers are taken from the slot of the bigger wheel and inserted into this wheel.
     * @return The list of timers that expire in the current time unit.
     */
    public List<Timer> advance() {
        // TODO implement here
        return List.of();
    }
}
