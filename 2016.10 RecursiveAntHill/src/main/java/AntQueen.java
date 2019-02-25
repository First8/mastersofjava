
/**
 * Queen ants are the founders of all colonies. Once mated, she can stay fertilized for many years laying millions of eggs.

Some queens can lay thousands of eggs each day. They really are egg laying machines.

Queen ants also have the record for the longest living of all insects, ranging from one to up to thirty years! 
 */
public class AntQueen extends Ant{
	public AntQueen(final int generation) {
		super(generation); 
	}
	private int childAmount;
	private int productivity=1000;
	
	
	public int createEggs() {
		childAmount += productivity; 
		return productivity; 
	}
	
	
	public int getChildAmount() {
		return childAmount;
	}
	
	@Override
	public boolean hasWings() {
		return true;
	}
}
