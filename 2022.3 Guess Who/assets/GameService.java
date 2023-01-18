import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Math.abs;
import static java.util.Comparator.comparingLong;

/**
 * This class contains the logic to play the game of 'Guess Who'
 */
public class GameService {

    /**
     * Determine the best question to ask, to split the list of persons in two as close to equal in size lists.
     */
    public Question determineBestQuestion(List<Person> persons) {
        return getAllQuestions().stream().min(comparingLong(predicate -> calculateDifference(persons, predicate))).get();
    }

    private long calculateDifference(List<Person> persons, Question question){
        var totalNumberOfPersons = persons.size();
        var numberOfPersonsTrue = persons.stream().filter(question).count();
        var numberOfPersonsFalse = totalNumberOfPersons - numberOfPersonsTrue;
        return abs(numberOfPersonsTrue - numberOfPersonsFalse);
    }

    private List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.addAll(Stream.of(HairColor.values()).map(this::createQuestion).toList());
        questions.addAll(Stream.of(EyeColor.values()).map(this::createQuestion).toList());
        questions.add(new Question(Person::hasMustache, "Does the person have a mustache?"));
        questions.add(new Question(Person::hasBeard, "Does the person have a beard?"));
        questions.add(new Question(Person::hasGlasses, "Does the person have glasses?"));
        questions.add(new Question(Person::hasHeadgear, "Is the person wearing headgear?"));
        return questions;
    }

    private Question createQuestion(HairColor hairColor){
        return new Question(person -> person.getHairColor() == hairColor,"Does the person have " + hairColor + " hair?");
    }

    private Question createQuestion(EyeColor eyeColor){
        return new Question(person -> person.getEyeColor() == eyeColor,"Does the person have " + eyeColor + " eyes?");
    }
}
