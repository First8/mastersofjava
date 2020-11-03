import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class TestUtil {
    private static final Random RANDOM = new Random();

    public static Set<Particle> allKnownParticles() {
        Set<Particle> all = quarks();
        all.addAll(bosons());
        all.addAll(leptons());
        return all;
    }

    public static Set<Particle> bosons() {
        Set<Particle> knownParticles = new HashSet<>();
        knownParticles.add(new Gluon());
        knownParticles.add(new Photon());
        knownParticles.add(new ZBoson());
        knownParticles.add(new WBoson());
        return knownParticles;
    }

    public static Set<Particle> quarks() {
        Set<Particle> knownParticles = new HashSet<>();
        knownParticles.add(new Up());
        knownParticles.add(new Down());
        knownParticles.add(new Charm());
        knownParticles.add(new Strange());
        knownParticles.add(new Top());
        knownParticles.add(new Bottom());
        return knownParticles;
    }

    public static Set<Particle> leptons() {
        Set<Particle> knownParticles = new HashSet<>();
        knownParticles.add(new Electron());
        knownParticles.add(new Muon());
        knownParticles.add(new Tau());
        knownParticles.add(new ElectronNeutrino());
        knownParticles.add(new MuonNeutrino());
        knownParticles.add(new TauNeutrino());
        return knownParticles;
    }
    
    public static Particle addError(Particle p) {
        return new UnknownParticle(UUID.randomUUID().toString(), p.getMass() + (RANDOM.nextFloat() - 0.5f) / 10.0f, p.getCharge() + (RANDOM.nextFloat() - 0.5f) / 10.0f, p.getSpin() + (RANDOM.nextFloat() - 0.5f) / 10.0f);
    }

    public static Particle randomParticle() {
        return (Particle) allKnownParticles().toArray()[RANDOM.nextInt(allKnownParticles().size())];
    }

    
    
}
