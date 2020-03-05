import java.util.Collection;

public interface Recommender {

    float euclidianDistance(String p1, String p2);

    String closestPerson(String person);

    String recommend(String person);
}
