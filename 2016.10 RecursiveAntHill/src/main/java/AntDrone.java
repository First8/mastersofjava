
/**
 * Drones are the only male ants in a colony. They are born from un-fertilized eggs.

Drones are not like typical ants. They don’t do any work in their nests, and they look more like wasps than ants.

They are reproductive winged ants and so are alates. They take to the skies on the same day as the princesses to mate during the nuptial flight.

Once they have mated, they die. Drones will only survive a few months during the mating season. Their life is short lived, but like all ants, they play a crucial part in the ant life cycle.
 */
public class AntDrone extends Ant{
	public AntDrone(final int generation) {
		super(generation); 
	}
	
	private boolean hasMated; 
	
	@Override
	public boolean hasWings() {
		return super.hasWings();
	}
	public void setHasMated(boolean hasMated) {
		this.hasMated = hasMated;
	}
}
