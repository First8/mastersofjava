
public class IntegerWalk {

	boolean evenOdd(int a, int b) {

		boolean isEven = false;
		System.out.println("a " + a + " b " + b + " a&b " + (a & b) + " isEven " + isEven);
		return isEven;
	}

	public static void main(String[] args) {
		IntegerWalk reader = new IntegerWalk();

		for (int x = 1; x < 10; x++) {
			for (int y = 1; y < 10; y++) {
				reader.evenOdd(x, y);
			}
		}
	}
}
