import java.util.UUID;

public class UnknownParticle extends Particle {
    public UnknownParticle(String id, float mass, float charge, float spin) {
        super(id, UUID.randomUUID().toString(), mass, charge, spin, null);
    }
}
