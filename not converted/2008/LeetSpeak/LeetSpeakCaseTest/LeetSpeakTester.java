import nl.moj.model.Tester;

public class LeetSpeakTester implements Tester.Testable {
	
	private static final String[] NAMES=new String[] {
	        "0 -> O","1 -> I","2 -> Z","3 -> E","4 -> A","& -> AND",
	        "X -> CK","X0R -> ER","T3H -> THE",
			"Hacker",
	        "Childrens song",
	};
	
	private static final String[] IN=new String[] {
	    "0",
	    "1",
	    "2",
	    "3",
	    "4",
	    "&",
	    "X",
	    "X0R",
	    "T3H",
	    "H4XX0R",
	    "L33T 1S N0T 4 C0MM0N 1NT3RN3T SP34K 4M0NG R34L H4XX0R",
	    "H1X0RY D1X0RY D0X, T3H M0USE R4N R0UND T3H CL0X"
	};
	
	private static final String[] OUT=new String[] {
	    "O","I","Z","E","A","AND","CK","ER","THE",
	    "HACKER",
	    "LEET IS NOT A COMMON INTERNET SPEAK AMONG REAL HACKER",
	    "HICKORY DICKORY DOCK, THE MOUSE RAN ROUND THE CLOCK"
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
		sb.append("Translate the following Leet speak into plain English:\n'");
		sb.append(IN[nr]);
		sb.append("'\n\n");
		sb.append("The expected result is:\n'");
        sb.append(OUT[nr]);
        sb.append("'\n");
		//
		// TODO: Generate useful test description.
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
		try {
			LeetSpeakImpl instance=new LeetSpeakImpl();
			//
			String player=instance.translate(IN[nr]);
			//
			if (OUT[nr].equals(player)) {
			    System.out.println("V: '"+player+"' Ok.");
			    result=true;
			} else {
                System.out.println("X: '"+player+"' is incorrect.");
                System.out.println("   '"+OUT[nr]+"' was expected.");
                if (player!=null) {
                    if (OUT[nr].length()==player.length()) {
                        System.out.print("    ");
                        for (int t=0;t<OUT[nr].length();t++) {
                            if (OUT[nr].charAt(t)!=player.charAt(t)) {
                                System.out.print("^");
                            } else {
                                System.out.print(" ");
                            }
                        }
                        System.out.println();
                    }
                }
                result=false;
			}
			//
		} catch (Exception ex) {
			//
			// Catch the exception, so that other tests may
			// still be executed. Do print the stacktrace. 
			//
		    System.out.println("I think the H4XX0R made an error somewhere.");
			ex.printStackTrace();
			//
			return false;
		}
		//
		return result;
	}
	
}
