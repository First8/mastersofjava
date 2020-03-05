import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ClosestPersonTest {

    
    @Test
    public void haileyHasVeronicaClosest() {
        Recommender r = new ThingsRecommender(Dataset.set1());
        assertEquals(Dataset.VERONICA, r.closestPerson(Dataset.HAILEY));
    }
    
    @Test
    public void jordynhasVeronicaClosest() {
        Recommender r = new ThingsRecommender(Dataset.set1());
        assertEquals(Dataset.VERONICA, r.closestPerson(Dataset.JORDYN));
    }

}
