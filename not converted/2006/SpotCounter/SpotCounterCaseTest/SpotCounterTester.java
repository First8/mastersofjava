import nl.moj.model.Tester;

public class SpotCounterTester implements Tester.Testable {
	
	private static final String[] NAMES=new String[] {
		"One Hit,No Spots",
		"One Hit,One Spot",
		"Lotsa Hits,No Spots",
		"Lotsa Hits,One Spot",
		"Lotsa Hits,Lotsa Spots",
		"All Spots!"
	};
	
	private static final boolean[][] HITS=new boolean[][] {
		{ false },
		{ true },
		{ false,false,false,false,false,false, false },
		{ false,false,true,false,false,false },
		{ false,true,false,true,false,true,false,false,false,false,true },
		{ true,true,true,true,true,true,true,true }
	};
	
	public int getTestCount() {
		return NAMES.length;
	}

	public String getTestName(int nr) {
		return NAMES[nr];
	}
	
	public String getTestDescription(int nr) {
		StringBuffer sb=new StringBuffer();
		//
		if (HITS[nr].length==1) {
			sb.append("The Radar has "+HITS[nr].length+" hit.\n");
		} else {
			sb.append("The Radar has "+HITS[nr].length+" hits.\n");
		}
		int spot=0;
		int nspot=0;
		for(boolean h:HITS[nr]) if (h) spot++; else nspot++;
		sb.append("Of those there are "+spot+" identifyable as SPOTs\n");
		sb.append("Of those there are "+nspot+" identifyable as something else.\n");
		//
		sb.append("The expected result is : "+spot+" SPOTs\n");
		//
		return sb.toString();
	}
	
	public boolean performTest(int nr) throws Throwable {
		//
		// Assume the worst
		//
		boolean result=false;
		//
		// Create a new Instance for each test.
		//
		SpotCounterImpl instance=new SpotCounterImpl();
		RadarImpl r=new RadarImpl(HITS[nr]);
		try {
			int scanResult=instance.scan(r);
			if (scanResult==r.expectedResult()) {
				result=true;
			} else {
				result=false;
				System.out.println("Expected "+r.expectedResult()+" instead of "+scanResult);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//
		return result;
	}
	
}
