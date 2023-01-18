import java.util.List;

/**
 * This class contains the logic to play the game of 'Guess Who'
 */
public class GameService {

    /**
     * Determine the best question to ask, to split the list of persons in two as close to equal in size lists.
     */
    public Question determineBestQuestion(List<Person> persons) {
        // TODO implement here
        return new Question(person -> false, "Is this the best question to ask?");
    }

}
