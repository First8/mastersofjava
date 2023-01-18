import java.util.function.Predicate;

/**
 * Wrapper around a Predicate to make it human-readable;
 */
public class Question implements Predicate<Person> {
    /**
     * The question for the code to apply to a list of Persons.
     */
    private Predicate<Person> predicate;

    /**
     * The question for humans to read. This will not be tested upon, but will be outputted for debugging.
     */
    private String text;


    public Question(Predicate<Person> predicate, String text) {
        this.predicate = predicate;
        this.text = text;
    }

    @Override
    public boolean test(Person person) {
        return predicate.test(person);
    }

    @Override
    public String toString() {
        return text;
    }
}
