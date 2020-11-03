import java.util.List;

public class FenceUtil {

	public static double computeDistanceBetweenHouses(House p1, House p2) {
		int x2 = (p1.getX() - p2.getX()) * (p1.getX() - p2.getX());
		int y2 = (p1.getY() - p2.getY()) * (p1.getY() - p2.getY());
		return Math.sqrt(x2 + y2);
	}

	public static int computeTotalFenceDistance(List<House> fence) {
		double sum = 0;
		for (int index = 0; index < fence.size(); index++) {
			int nextIndex = (index + 1) % fence.size();
			sum += computeDistanceBetweenHouses(fence.get(index), fence.get(nextIndex));
		}
		System.out.println("sum " + fence + "=" + sum);
		return (int) Math.round(sum);
	}


}
