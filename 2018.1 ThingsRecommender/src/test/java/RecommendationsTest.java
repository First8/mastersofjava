import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class RecommendationsTest {

    
    @Test
    public void recommendationForHaileyIsPhoenix() {
        Recommender r = new ThingsRecommender(Dataset.set1());
        assertEquals(Dataset.PHOENIX, r.recommend(Dataset.HAILEY));
    }

    @Test
    public void recommendationForChanIsTheStrokes() {
        Recommender r = new ThingsRecommender(Dataset.set1());
        assertEquals(Dataset.THE_STROKES, r.recommend(Dataset.CHAN));
    }

    @Test
    public void recommendationForAngelicaIsEmpty() {
        Recommender r = new ThingsRecommender(Dataset.set1());
        assertNull(r.recommend(Dataset.ANGELICA));
    }

}
