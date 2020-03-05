/**
 * Abstract implementation of the person responsible for
 * creating all those delicious Sushi's. 
 */
public abstract class AbstractSushiChef {

	private SushiBelt belt;
	
	public AbstractSushiChef(SushiBelt belt) {
		this.belt=belt;
	}
	
	protected abstract Sushi makeSushi();
	
	protected void makeAnotherSushiAndPlaceItOnTheBelt() {
		Sushi sushi=makeSushi();
		sushi.getReservationLock().lock();
		try {
			System.out.println("The chef puts a nice "+sushi+" on the belt.");
			belt.place(sushi);
		} finally {
			sushi.getReservationLock().unlock();
		}
	}
}
