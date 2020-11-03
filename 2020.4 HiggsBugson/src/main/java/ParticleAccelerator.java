import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Contains all measurement logic and accelerator control mechanisms.
 * Main purpose is to classify a measured particle.
 */
public class ParticleAccelerator {

    private static final float THRESHOLD = 5f;
    private final Map<Particle, Integer> particleCount = new HashMap<>();
    private Set<Particle> knownParticles;

    public ParticleAccelerator(Set<Particle> knownParticles) {
        this.knownParticles = new HashSet<>(knownParticles);
    }

	public ClassifiedParticle classify(Particle p) {
        Particle bestFit = measure(p.getMass(), p.getCharge(), p.getSpin());
        return new ClassifiedParticle(p.getId(), bestFit.getName(), p.getMass(), p.getCharge(), p.getSpin(), bestFit.getType());
    }

    private Particle measure(float mass, float charge, float spin) {
        Particle best = null;
        float bestDistance = Float.MAX_VALUE;
        for (Particle particle : knownParticles) {
            float distance = particle.distance(mass, charge, spin);
            if (distance < bestDistance && distance < THRESHOLD) {
                best = particle;
                bestDistance = distance;
            }
        }
        return best;
    }

    public void countParticle(Particle p) {
        Integer count = particleCount.get(p);
        if (count == null) {
            count = 0;
        }
        particleCount.put(p, count + 1);
    }

    public int getCount(Particle p) {
        return particleCount.get(p) == null ? 0 : particleCount.get(p);
    }

    public Set<Particle> particles() {
        return particleCount.keySet();
    }
}