import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestClassify {

    @Test
    public void testClassify() {
        ParticleAccelerator pa = new ParticleAccelerator(TestUtil.allKnownParticles());
        for (int i = 0; i < 100; i++) {
            Particle p = TestUtil.randomParticle();
            Particle measurement = TestUtil.addError(p);
            ClassifiedParticle classified = pa.classify(measurement);
            assertEquals(p.getName(), classified.getName());
            assertEquals(p.getType(), classified.getType());
        }
    }



}
