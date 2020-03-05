import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class HiddenTests {
    @Test
    public void samHasVeronicaClosest() {
        Recommender r = new ThingsRecommender(Dataset.set1());
        assertEquals(Dataset.HAILEY, r.closestPerson(Dataset.SAM));
    }

	
    @Test
    public void jordynSamEuclidianDistance() {
        Recommender r = new ThingsRecommender(Dataset.set1());
        assertEquals(3.39, r.euclidianDistance(Dataset.SAM, Dataset.JORDYN), 0.01);
    }

    @Test
    public void chanSamEuclidianDistance() {
        Recommender r = new ThingsRecommender(Dataset.set1());
        assertEquals(3.16, r.euclidianDistance(Dataset.CHAN, Dataset.SAM), 0.01);
    }

    @Test
    public void veronicaJordynEuclidianDistance() {
        Recommender r = new ThingsRecommender(Dataset.set1());
        assertEquals(2.45, r.euclidianDistance(Dataset.JORDYN, Dataset.VERONICA), 0.01);
    }

    
    
    @Test
    public void recommendationForJordynIsBluesTraveler() {
        Recommender r = new ThingsRecommender(Dataset.set1());
        assertEquals(Dataset.BLUES_TRAVELER, r.recommend(Dataset.JORDYN));
    }

    @Test
    public void recommendationForVeronicaIsBrokenBells() {
        Recommender r = new ThingsRecommender(Dataset.set1());
        assertEquals(Dataset.BROKEN_BELLS, r.recommend(Dataset.VERONICA));
    }

    @Test
    public void recommendationForAngelicaIsEmpty() {
        Recommender r = new ThingsRecommender(Dataset.set1());
        assertNull(r.recommend(Dataset.ANGELICA));
    }

    
    
}
