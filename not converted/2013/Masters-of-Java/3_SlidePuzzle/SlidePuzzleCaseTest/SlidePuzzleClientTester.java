/**
 * Test class for the client.
 * 
 * @author Ron Bierman
 */
public class SlidePuzzleClientTester extends SlidePuzzleTester
{
	private static final Integer[][] puzzle1 = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,null,15}};
	private static final Integer[][] puzzle2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,null,14,15}};
	private static final Integer[][] puzzle3 = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{null,13,14,15}};
	private static final Integer[][] puzzle4 = {{1,2,3,4},{5,6,7,8},{null,10,11,12},{9,13,14,15}};
	private static final Integer[][] puzzle5 = {{1,2,3,4},{null,6,7,8},{5,10,11,12},{9,13,14,15}};
	private static final Integer[][] puzzle6 = {{1,2,3,4},{6,null,7,8},{5,10,11,12},{9,13,14,15}};
	private static final Integer[][] puzzle7 = {{1,2,3,4},{6,7,null,8},{5,10,11,12},{9,13,14,15}};
	private static final Integer[][] puzzle8 = {{1,2,3,4},{6,7,11,8},{5,10,null,12},{9,13,14,15}};
	
   /** Test cases. */
   private static final TestCase[] CASES = new TestCase[] {
	   new TestCase(puzzle1),new TestCase(puzzle2),
	   new TestCase(puzzle3),new TestCase(puzzle4),
	   new TestCase(puzzle5),new TestCase(puzzle6),new TestCase(puzzle7),new TestCase(puzzle8)
   };
   


   /** {@inheritDoc} */
   @Override
   protected TestCase[] getTestCases()
   {
      return CASES;
   }

}
