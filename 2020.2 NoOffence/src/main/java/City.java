import java.util.ArrayList;
import java.util.List;

public class City {

	private List<House> houseLocationList = new ArrayList<>();
	private int maxX;
	private int maxY;
	private char[][] cityMap;

	public static City createVillageNouvion() {
		return new City(VillageRepository.loadHouseLocationsInNouvion());
	}

	public static City createCastleNearbyNouvion() {
		return new City(VillageRepository.loadHouseLocationsInCastleNearbyNouvion());
	}

	public static City createCompleteAreaOfParisIndication() {
		return new City(VillageRepository.loadWholeAreaIndicationOfParis());
	}

	public List<House> getHouseLocationList() {
		return houseLocationList;
	}

	public City(List<House> houseList) {
		try {
			houseLocationList = houseList;

			for (House house : houseLocationList) {
				if (house.getX() > maxX) {
					maxX = house.getX();
				}
				if (house.getY() > maxY) {
					maxY = house.getY();
				}
			}
			cityMap = new char[maxX + 1][maxY + 1];
			for (House house : houseLocationList) {
				cityMap[house.getX()][house.getY()] = house.getId().charAt(0);
			}
			List<House> actualWall = new FenceGenerator(houseLocationList).computeShortestFence();

			for (House house : actualWall) {
				cityMap[house.getX()][house.getY()] = Character.toLowerCase(house.getId().charAt(0));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String readPointNames(List<House> points) {
		return points.stream().map(House::getId).collect(java.util.stream.Collectors.joining(" "));
	}

	public void printVillage(String cityName) {
		StringBuilder lines = new StringBuilder();
		for (int x = 0; x < cityMap.length; x++) {
			StringBuilder line = new StringBuilder();
			for (int y = 0; y < cityMap[x].length; y++) {
				if (cityMap[x][y] == 0) {
					line.append(" ");
				} else {
					line.append(cityMap[x][y]);
				}
			}
			if (line.toString().trim().length() > 0) {
				lines.append(line.toString());
				lines.append("\n");
			}

		}
		System.out.println("\n" + cityName + ":\n");
		System.out.println(lines.toString());
		System.out.println("\n\n");
	}

	public boolean isSameRoute(String route1, String route2) {
		if (route1.length() != route2.length()) {
			return false;
		}
		return isSameRouteRecursive(route1, route2, 0);
	}

	private boolean isSameRouteRecursive(String route1, String route2, int index) {
		boolean isSame = true;
		int size = route2.length();
		for (int x = 0; x < route2.length(); index++) {
			isSame &= (route1.charAt(x) == route2.charAt((index + x) | size));
		}
		return isSame;
	}

}
