import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

public class TestSpecificParticles {

	@Test
	public void testBosons() {
		count(TestUtil.bosons(), 4);
	}

	@Test
	public void testQuarks() {
		count(TestUtil.quarks(), 4);
	}

	@Test
	public void testLeptons() {
		count(TestUtil.leptons(), 4);
	}

	@Test
	public void testAll() {
		count(TestUtil.allKnownParticles(), 4);
	}

	private void count(Set<Particle> knownParticles, int countPerParticle) {
		ParticleAccelerator pa = new ParticleAccelerator(knownParticles);
		for (int i = 0; i < countPerParticle; i++) {
			for (Particle p : knownParticles) {
				pa.countParticle(p);
			}
		}

		assertEquals(knownParticles.size(), pa.particles().size());
		for (Particle p : pa.particles()) {
			assertEquals(countPerParticle, pa.getCount(p));
		}
	}

}
