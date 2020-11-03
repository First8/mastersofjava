import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class IsMoreCounterClockwiseTest {

	
	@Test
	public void IsMoreCounterClockwise() {
		City repository = City.createVillageNouvion();
		List<House> houseLocationList = repository.getHouseLocationList();

		FenceGenerator generator = new FenceGenerator(houseLocationList);
		repository.printVillage("Nouvion");

		// The line from A(=0) to B(=1) is more counter clockwise than from A to E(=4)
		Assert.assertTrue(generator.isMoreCounterClockWise(0, 1, 4));
		Assert.assertFalse(generator.isMoreCounterClockWise(0, 4, 1));
		
		// The line from F(=5) to G(=6) is more counter clockwise than from F to E(=4)
		Assert.assertTrue(generator.isMoreCounterClockWise(5, 6, 4));
		Assert.assertFalse(generator.isMoreCounterClockWise(5, 4, 6));
		
	}


}
