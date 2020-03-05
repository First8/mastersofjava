
public class NoodleImpl implements Noodle {
	
	private boolean eaten;
	
	public NoodleImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public void eat() {
		eaten=true;
		System.out.println(Thread.currentThread().getName()+" eats a Noodle");	
	}

	public boolean isEaten() {
		return eaten;
	}

}
