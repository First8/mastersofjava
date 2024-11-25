import java.util.function.Predicate;

import static java.time.LocalDate.now;

/**
 * Abstract class for creating tasks for subjects.
 * @param <S> the type of the subject (e.g. a cow)
 */
public abstract class TaskFactory<S extends TaskSubject> {
    protected abstract Task<S> createTask(S taskSubject);

    protected abstract int score(S taskSubject);

    protected Predicate<Task<S>> getAllBusinessRules() {
        return getBusinessRules()
                .and(task -> !task.completed())
                .and(task -> task.expirationDate() == null || !task.expirationDate().isBefore(now()));
    }

    protected abstract Predicate<Task<S>> getBusinessRules();
}
