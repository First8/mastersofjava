import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ThingsRecommender implements Recommender {
    private final Collection<Rating> dataset;

    public ThingsRecommender(Collection<Rating> dataset) {
        this.dataset = dataset;
    }

    /**
     * Calculate the euclidian distance between p1 and p2
     * @param p1 the first person
     * @param p2 the second person
     */
    public float euclidianDistance(String p1, String p2) {
    	// TODO implement
        return 0f;
    }

    /**
     * Find the person with the most similar taste for a given person.
     * @param person the person to find a recommended person for
     */
    @Override
    public String closestPerson(String person) {
    	// TODO implement
        return null;
    }

    /**
     * Make a final recommendation for that person.
     * @param person the person to recommend a thing for
     * @return a thing that this person will probably like, based on the provided dataset.
     */
    @Override
    public String recommend(String person) {
    	// TODO implement
        return null;
    }

    private Collection<Rating> getRatings(String person) {
        return dataset.stream().filter(r -> r.getPerson().equals(person)).collect(Collectors.toSet());
    }

    private Collection<String> getPersons() {
        return dataset.stream().map(r -> r.getPerson()).collect(Collectors.toSet());
    }

    private Collection<String> getThings(String person) {
        return dataset.stream().filter(r -> r.getPerson().equals(person)).map(r -> r.getThing()).collect(Collectors.toSet());
    }

    private Float getScore(String person, String thing) {
        return dataset.stream().filter(r -> r.getPerson().equals(person) && r.getThing().equals(thing)).map(r -> r.getScore()).findFirst().orElse(null);
    }

}
