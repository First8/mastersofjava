import java.time.LocalDate;
import java.util.function.Predicate;

import static java.time.LocalDate.now;

/**
 * Generates tasks for cows.
 * <p>
 * The score (priority of the task) is determined based on the state of the cow, e.g. ill cows require urgent attention.
 */
public class CowTaskFactory extends TaskFactory<Cow> {
    @Override
    protected Task<Cow> createTask(Cow cow) {
        LocalDate expirationDate = cow.inHeat() ? now().plusDays(3) : null;
        return new Task<>(cow, "Check how " + cow.name() + " is doing", score(cow), expirationDate, false);
    }

    @Override
    protected int score(Cow cow) {
        int value = 0;
        value += cow.ill() ? 10 : 0;
        value += cow.pregnant() ? 5 : 0;
        value += cow.inHeat() ? 7 : 0;
        return value;
    }

    @Override
    protected Predicate<Task<Cow>> getBusinessRules() {
        return task -> task.priority() > 0;
    }
}
