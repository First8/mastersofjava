import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.junit.Test;

public class HiddenTests {

    private void assertEqualMeasurement(ParticleAccelerator pa1, ParticleAccelerator pa2) {
        System.out.println("Detected particles: " + pa1.particles().size() + " vs " + pa2.particles().size());
        for (Particle p : pa1.particles()) {
            System.out.println(p.getName() + ": " + pa1.getCount(p) + " vs " + pa2.getCount(p));
        }
        System.out.println("Wrong particles: ");
        for (Particle p : pa2.particles()) {
            if (!pa1.particles().contains(p)) {
                System.out.println(p.getName() + ": " + pa1.getCount(p) + " vs " + pa2.getCount(p));
            }
        }
        System.out.println("End of analysis.");
        assertEquals(pa1.particles().size(), pa2.particles().size());

        for (Particle p : pa1.particles()) {
            assertEquals(pa1.getCount(p), pa2.getCount(p));
        }
    }

    @Test
    public void testDetectionOnProductionRandomSet() {
        Collection<Particle> testSet = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            testSet.add(TestUtil.randomParticle());
        }

        ParticleAccelerator paNoError = new ParticleAccelerator(TestUtil.allKnownParticles());
        testSet.forEach(p -> paNoError.countParticle(p));

        ParticleAccelerator paWithError = new ParticleAccelerator(TestUtil.allKnownParticles());
        testSet.forEach(p -> {
            Particle particle = TestUtil.addError(p);
            Particle detected = paWithError.classify(particle);
            paWithError.countParticle(detected);
        });

        assertEqualMeasurement(paNoError, paWithError);
    }

    
    @Test
    public void testDetectionOnProductionStaticSet() {
        Collection<Particle> testSet = new ArrayList<>();
        testSet.add( new UnknownParticle(UUID.randomUUID().toString(), 0.010040688f, 0.004777527f, 0.95863956f));
        testSet.add( new UnknownParticle(UUID.randomUUID().toString(), 1.3245068f, 0.027948236f, -0.0072148084f));
        testSet.add( new UnknownParticle(UUID.randomUUID().toString(), 80.43408f, 1.009112f, 1.0475681f));
        testSet.add( new UnknownParticle(UUID.randomUUID().toString(), 2.3903158f, 0.0023306669f, -0.00814836f));

        testSet.add( new UnknownParticle(UUID.randomUUID().toString(), 4.787874f, 0.049905956f, -0.015664255f));
        testSet.add( new UnknownParticle(UUID.randomUUID().toString(), 1.283515f, -0.024623448f, -0.024976766f));
        testSet.add( new UnknownParticle(UUID.randomUUID().toString(), 15.54428f, 0.02447179f, 0.020963024f));
        testSet.add( new UnknownParticle(UUID.randomUUID().toString(), 172.43364f, 0.0075990916f, -0.011657762f));

        testSet.add( new UnknownParticle(UUID.randomUUID().toString(), 172.39998f, -0.017554145f, -0.0073985634f));
        testSet.add( new UnknownParticle(UUID.randomUUID().toString(), -0.02903002f, -0.03108411f, 0.009432274f));
        
        ParticleAccelerator pa = new ParticleAccelerator(TestUtil.allKnownParticles());
        testSet.forEach(p -> {
            Particle detected = pa.classify(p);
            pa.countParticle(detected);
        });
        
        assertTrue(pa.particles().contains(new Gluon()));
        assertTrue(pa.particles().contains(new Charm()));
        assertTrue(pa.particles().contains(new WBoson()));
        assertTrue(pa.particles().contains(new Up()));
        assertTrue(pa.particles().contains(new Down()));
        assertTrue(pa.particles().contains(new TauNeutrino()));
        assertTrue(pa.particles().contains(new Top()));
        assertTrue(pa.particles().contains(new Photon()));
        
        assertEquals( 1, pa.getCount(new Gluon()));
        assertEquals( 2, pa.getCount(new Charm()));
        assertEquals( 1, pa.getCount(new WBoson()));
        assertEquals( 1, pa.getCount(new Up()));
        assertEquals( 1, pa.getCount(new Down()));
        assertEquals( 1, pa.getCount(new TauNeutrino()));
        assertEquals( 2, pa.getCount(new Top()));
        assertEquals( 1, pa.getCount(new Photon()));
    }

    @Test
    public void testDetectionOnProductionStaticSet2() {
        Collection<Particle> testSet = new ArrayList<>();
        testSet.add( new UnknownParticle(UUID.randomUUID().toString(), 80.36689f,0.96595734f,1.0012347f));
        testSet.add( new UnknownParticle(UUID.randomUUID().toString(), 80.411995f,0.9859776f,0.95990527f));
        testSet.add( new UnknownParticle(UUID.randomUUID().toString(), 1.653081f,-0.010588246f,-0.018033039f));
        testSet.add( new UnknownParticle(UUID.randomUUID().toString(), 80.39559f,0.96995527f,0.9690619f));
        testSet.add( new UnknownParticle(UUID.randomUUID().toString(), 1.7096714f,-0.030055862f,0.0023885667f));
        testSet.add( new UnknownParticle(UUID.randomUUID().toString(), 2.2248638f,-0.049461443f,0.030973768f));
        testSet.add( new UnknownParticle(UUID.randomUUID().toString(), 4.143866f,-0.008457834f,-0.039338f));
        testSet.add( new UnknownParticle(UUID.randomUUID().toString(), 2.1848345f,0.03993134f,0.008366639f));
        testSet.add( new UnknownParticle(UUID.randomUUID().toString(), 15.479516f,-0.044422615f,-0.011428165f));
        testSet.add( new UnknownParticle(UUID.randomUUID().toString(), 2.1548078f,-0.012183189f,0.017748332f));
        
        ParticleAccelerator pa = new ParticleAccelerator(TestUtil.allKnownParticles());
        testSet.forEach(p -> {
            Particle detected = pa.classify(p);
            pa.countParticle(detected);
        });
        
        for( Particle p : pa.particles()) {
            System.out.println( p.getName() + ": " + pa.getCount(p));
        }
        
        assertTrue(pa.particles().contains(new WBoson()));
        assertTrue(pa.particles().contains(new MuonNeutrino()));
        assertTrue(pa.particles().contains(new ElectronNeutrino()));
        assertTrue(pa.particles().contains(new Bottom()));
        assertTrue(pa.particles().contains(new TauNeutrino()));
        
        assertEquals( 3, pa.getCount(new WBoson()));
        assertEquals( 2, pa.getCount(new MuonNeutrino()));
        assertEquals( 3, pa.getCount(new ElectronNeutrino()));
        assertEquals( 1, pa.getCount(new Bottom()));
        assertEquals( 1, pa.getCount(new TauNeutrino()));
    }

    
}
