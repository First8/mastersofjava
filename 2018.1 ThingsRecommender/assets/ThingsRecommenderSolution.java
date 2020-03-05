import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ThingsRecommender implements Recommender {
    private final Collection<Rating> dataset;

    public ThingsRecommender(Collection<Rating> dataset) {
        this.dataset = dataset;
    }

    @Override
    public String recommend(String person) {
        Collection<String> alreadyRated = getRatings(person).stream().map(r -> r.getThing()).collect(Collectors.toSet());
        return getRatings(closestPerson(person)).stream().filter(r -> !alreadyRated.contains(r.getThing())).sorted(Comparator.comparing(Rating::getScore).reversed()).map(r -> r.getThing()).findFirst().orElse(null);
    }

    @Override
    public String closestPerson(String person) {
        return getPersons().stream().filter(p -> !p.equals(person)).sorted(new Comparator<String>() {
            @Override
            public int compare(String p1, String p2) {
            	float f1 = euclidianDistance(p1, person);
            	float f2 = euclidianDistance(p2, person);
            	if (f1 < f2 ) return -1; else if (f1==f2) return 0; else return 1;
            }
        }).findFirst().orElse(null);
    }

    public float euclidianDistance(String p1, String p2) {
        Collection<String> things = getThings(p1);
        float total2 = 0;
        for (String thing : things) {
            float s1 = getScore(p1, thing);
            Float s2 = getScore(p2, thing);
            if (s2 != null) {
                total2 += (s1 - s2) * (s1 - s2);
            }
        }
        return (float) Math.sqrt(total2);
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
