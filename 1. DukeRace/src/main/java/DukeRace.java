import java.util.Arrays;

public class DukeRace {
	Integer[] accel = new Integer[] { 2, 2 };
	Integer[] spd = new Integer[] { 2, 2 };

	public void setAccel(Integer[] accel) {
		this.accel = accel;
	}

	public void setSpd(Integer[] spd) {
		this.spd = spd;
	}

	Integer[] racers(int d) {
		return racers(d, accel, spd);
	}

	Integer[] racers(int d, Integer[] a, Integer[] v) {
		int n = a.length;
		Integer[] result = new Integer[n];
		return result;
	}

	public boolean equalsAndPrint(Integer[] races, Integer[] output) {
		System.out.println("races " + Arrays.asList(races) + " output " + Arrays.asList(output));
		return Arrays.equals(races, output);
	}
}
