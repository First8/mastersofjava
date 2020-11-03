/**
 * Represents a measured or defined particle.
 * A particle is identified by its name, two particles are equal if their name is equal.
 * The id is a random uuid used during detection. The measured mass,charge and spin are simply measurements with some error margin.
 */
public abstract class Particle {
    private final String id;
    private final String name;
    private final float mass;
    private final float charge;
    private final float spin;
    private final Type type;

    protected Particle(String name, float mass, float charge, float spin, Type type) {
        // for prototype particles, simply use name for the id
        this(name, name, mass, charge, spin, type);
    }

    protected Particle(String id, String name, float mass, float charge, float spin, Type type) {
        this.id = id;
        this.name = name;
        this.mass = mass;
        this.charge = charge;
        this.spin = spin;
        this.type = type;
    }

    public float distance(float mass, float charge, float spin) {
        return Math.abs(this.mass - mass) + Math.abs(this.charge - charge) + Math.abs(this.spin - spin);
    }

    public String getName() {
        return name;
    }

    public float getMass() {
        return mass;
    }

    public float getCharge() {
        return charge;
    }

    public float getSpin() {
        return spin;
    }

    public Type getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String toString() {
        return id + ": " + (name == null ? "unknown" : name) + "/ " + (type == null ? "unknown" : type) + " " + mass + "f," + charge + "f," + spin + "f";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
//        result += prime * result + ((id == null) ? 0 : id.hashCode());
        result += prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        Particle other = (Particle) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        }

        return name.equals(other.name);
    }

    
}
