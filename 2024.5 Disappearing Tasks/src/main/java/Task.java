import java.time.LocalDate;

/**
 * A generic task.
 * @param subject the subject this task relates to.
 * @param description the description of the task.
 * @param priority the priority of the task.
 * @param expirationDate when it is due.
 * @param completed whether it is completed
 * @param <S> the type of the subject this task relates to.
 */
public record Task<S extends TaskSubject> (S subject, String description, int priority, LocalDate expirationDate, boolean completed) {
}
