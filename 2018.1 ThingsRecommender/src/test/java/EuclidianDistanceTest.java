import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EuclidianDistanceTest {
    
    @Test
    public void angelicaBillEuclidianDistance() {
        Recommender r = new ThingsRecommender(Dataset.set1());
        assertEquals(4.3, r.euclidianDistance(Dataset.ANGELICA, Dataset.BILL), 0.01);
    }

    @Test
    public void faileyVeronicaEuclidianDistance() {
        Recommender r = new ThingsRecommender(Dataset.set1());
        assertEquals(1.414, r.euclidianDistance(Dataset.HAILEY, Dataset.VERONICA), 0.01);
    }

    @Test
    public void haileyJordynEuclidianDistance() {
        Recommender r = new ThingsRecommender(Dataset.set1());
        assertEquals(4.387, r.euclidianDistance(Dataset.HAILEY, Dataset.JORDYN), 0.01);
    }
    
}
