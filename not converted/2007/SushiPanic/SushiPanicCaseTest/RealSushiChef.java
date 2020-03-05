
public class RealSushiChef extends AbstractSushiChef {

	private String[] sushiNames=new String[] {
		"Kani","Tamago","Kappa Maki","Tekka Maki","Kampyo Maki",
		"Ama Ebi Nigiri","Tako Nigiri","Tobiko Maki","Kisu Takiki"
	};
	
	private int current;
	
	public RealSushiChef(SushiBelt belt) {
		super(belt);
	}
	
	@Override
	protected Sushi makeSushi() {
		current++;
		if (current>=sushiNames.length) current=0;
		return new SushiImpl(sushiNames[current]);
	}
	
	@Override
	protected void makeAnotherSushiAndPlaceItOnTheBelt() {
		super.makeAnotherSushiAndPlaceItOnTheBelt();
	}
}
