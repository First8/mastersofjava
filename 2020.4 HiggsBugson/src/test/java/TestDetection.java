import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.junit.Test;

// visible, readonly
public class TestDetection {

	private static final Random RANDOM = new Random();
	
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
	public void testDetectionOnStagingStaticSet() {
		Collection<Particle> testSet = new ArrayList<>();
		testSet.add(new UnknownParticle("Gluon", 0.010040688f, 0.004777527f, 0.95863956f));
		testSet.add(new UnknownParticle("Charm", 1.3245068f, 0.027948236f, -0.0072148084f));
		testSet.add(new UnknownParticle("W-Boson", 80.43408f, 1.009112f, 1.0475681f));
		testSet.add(new UnknownParticle("Up", 2.3903158f, 0.0023306669f, -0.00814836f));
		testSet.add(new UnknownParticle("Down", 4.787874f, 0.049905956f, -0.015664255f));
		testSet.add(new UnknownParticle("Charm", 1.283515f, -0.024623448f, -0.024976766f));
		testSet.add(new UnknownParticle("Tau Neutrino", 15.54428f, 0.02447179f, 0.020963024f));
		testSet.add(new UnknownParticle("Top", 172.43364f, 0.0075990916f, -0.011657762f));
		testSet.add(new UnknownParticle("Top", 172.39998f, -0.017554145f, -0.0073985634f));
		testSet.add(new UnknownParticle("Photon", -0.02903002f, -0.03108411f, 0.009432274f));

		ParticleAccelerator pa = new ParticleAccelerator(TestUtil.allKnownParticles());
		testSet.forEach(p -> {
			Particle detected = pa.classify(p);
			System.out.println(detected.getName());
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

		assertEquals(1, pa.getCount(new Gluon()));
		assertEquals(2, pa.getCount(new Charm()));
		assertEquals(1, pa.getCount(new WBoson()));
		assertEquals(1, pa.getCount(new Up()));
		assertEquals(1, pa.getCount(new Down()));
		assertEquals(1, pa.getCount(new TauNeutrino()));
		assertEquals(2, pa.getCount(new Top()));
		assertEquals(1, pa.getCount(new Photon()));

	}

	@Test
	public void testDetectionWithoutError() {
		Collection<Particle> testSet = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			testSet.add(TestUtil.randomParticle());
		}

		ParticleAccelerator paNoError = new ParticleAccelerator(TestUtil.allKnownParticles());
		testSet.forEach(p -> paNoError.countParticle(p));

		ParticleAccelerator paWithError = new ParticleAccelerator(TestUtil.allKnownParticles());
		testSet.forEach(p -> {
			Particle detected = paWithError.classify(p);
			paWithError.countParticle(detected);
		});

		assertEqualMeasurement(paNoError, paWithError);
	}

	@Test
	public void testDetectionWithRandomMeasurementError() {
		Collection<Particle> testSet = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			testSet.add(TestUtil.randomParticle());
		}

		ParticleAccelerator paNoError = new ParticleAccelerator(TestUtil.allKnownParticles());
		testSet.forEach(p -> paNoError.countParticle(p));

		ParticleAccelerator paWithError = new ParticleAccelerator(TestUtil.allKnownParticles());
		testSet.forEach(p -> {
			if (RANDOM.nextInt(10) == 0) {
				p = TestUtil.addError(p);
			}

			Particle detected = paWithError.classify(p);

			System.out.println(detected.toString());
			paWithError.countParticle(detected);
		});

		assertEqualMeasurement(paNoError, paWithError);
	}

}
