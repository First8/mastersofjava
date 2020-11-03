import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class LeftMostHouseTest {

	
	@Test
	public void testLeftMostHouseForNouvion() {
		City repository = City.createVillageNouvion();
		List<House> houseLocationList = repository.getHouseLocationList();
		repository.printVillage("Nouvion");

		FenceGenerator generator = new FenceGenerator(houseLocationList);
		
		int leftMost = generator.startWithLeftMostHouse(houseLocationList);
		Assert.assertEquals("A", houseLocationList.get(leftMost).getId() );
	}

	@Test
	public void testLeftMostHouseForCastleNearbyNouvion() {
		City repository = City.createCastleNearbyNouvion();
		List<House> houseLocationList = repository.getHouseLocationList();
		repository.printVillage("Castle nearby Nouvion");
		
		FenceGenerator generator = new FenceGenerator(houseLocationList);
		
		int leftMost = generator.startWithLeftMostHouse(houseLocationList);
		String house = houseLocationList.get(leftMost).getId();
		Assert.assertTrue(house.equals("A") || house.equals("B")  );
	}

}
