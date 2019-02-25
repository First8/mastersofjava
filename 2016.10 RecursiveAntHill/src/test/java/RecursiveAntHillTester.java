import nl.moj.model.Tester;

public class RecursiveAntHillTester implements Tester.Testable,Tester.HiddenTestable{
	
	private Integer[] x_width = new Integer[] {1, 3,5 ,7, 9, 11, 21};
	
	private Integer[] y_width = new Integer[] {1, 8,3 ,6, 9, 14, 16};
	
	
	private static final String[] HILLS =new String[] {
			"Empty anthill",
		"Hill 1",
		"Hill 2",
		"Hill 3",
		"Hill 4",
		"Hill 5",
		"Hill 6"
	};
	
	public int getTestCount() {
		return HILLS.length-2;
	}
	
	@Override
	public int getTestCountIncludeHidden() {
		return HILLS.length;
	}

	public String getTestName(int nr) {
		return HILLS[nr];
	}
	
	public String getTestDescription(int nr) {
		StringBuffer sb=new StringBuffer();
		AntHill provenSolution = new Solution();

		sb.append("Hill [" +x_width[nr] + "," + y_width[nr] + "] should give " +provenSolution.calculate(x_width[nr], y_width[nr])); 
		return sb.toString();
	}
	private AntHill solution = new RecursiveAntHillImpl();
	
	public void setSolution(AntHill solution) {
		this.solution = solution;
	}
	
	public boolean performTest(int nr) throws Throwable {
		//
		// Assume the worst
		//
		boolean result = false;
		//
		// Create a new Instance for each test.
		//
		try {
			AntHill provenSolution = new Solution();
			
			
			
			for (int index =0; index<10;index++) {
				int totalProof = provenSolution.calculate(x_width[nr], y_width[nr]);
				int totalClient = solution.calculate(x_width[nr], y_width[nr]);
				if (totalProof==totalClient) {
					result = true;
				}
				if (!result) {
					System.out.println("incorrect solution: [" +x_width[nr] + "," + y_width[nr] + "] should give " + totalProof + " instead of " + totalClient);
					break; 
				}
			}
			
			
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		
			return false;
		}
		//
		return result;
	}
	
}
