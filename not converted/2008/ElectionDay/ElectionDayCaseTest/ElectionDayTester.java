import nl.moj.model.Tester;

public class ElectionDayTester implements Tester.Testable {
	
	private static final String[] NAMES=new String[] {
			"Vote Obama",
			"Vote McCain",
			"Vote Blank",
			"Can't choose",
			"Seeing double",
			"Really small card",
			"Messy business",
			"Who's that guy?",
			"Blank",
			"Total misfit",
			"McWho?"
	};
	
	private static final String[][] CARDS=new String[][] {
	    {
	        "            ",
	        " Obama  [X] ",
	        " McCain [ ] ",
	        "            "	        
	    }, {
            "            ",
            " Obama  [ ] ",
            " McCain [#] ",
            "            "          
	    }, {
            "            ",
            " McCain [ ] ",
            " Obama [ ] ",
            "            "          
	    }, {
            "            ",
            " McCain [X] ",
            " Obama  [X] ",
            "            "          
        }, {
            " Obama[ ]  ",
            " McCain[ ] ",
            " Obama[X]  ",
            " McCain[ ] "
        }, {
            " Obama[ ] McCain [*]"
        }, {
            "           ",
            " Obama     ",
            "        [O]",
            "           ",
            "     McCain",
            " [ ]       ",
        }, {
            "            ",
            " Orbama [ ] ",
            " McCane [X] ",
            "            "
        }, {
            "            ",
            "            ",
        }, {
            "OhMXcbamaCain[ ]OhOhMcXCaiBama[[[]X "
        },{
            "Obama  [ ]",
            "McCain [X]",
            "McCain [ ]",
        }
	};
	
	private static final Result[] RESULTS = new Result[] {
	  Result.OBAMA,Result.MCCAIN,Result.BLANK,Result.INVALID,Result.INVALID,Result.MCCAIN,Result.OBAMA,Result.INVALID,Result.INVALID,Result.INVALID,Result.INVALID  
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
		sb.append("The following ballot card is offered to you for evaluation:\n");
		for (int t=0;t<CARDS[nr].length;t++) {
		    sb.append(CARDS[nr][t]);
		    sb.append("\n");
		}
		sb.append("\nThe expected outcome is: ");
		sb.append(RESULTS[nr]);
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
			BallotCardAnalyzer instance=new BallotCardAnalyzerImpl();
			//
			StringBuilder sb=new StringBuilder();
			for (int t=0;t<CARDS[nr].length;t++) {
                sb.append(CARDS[nr][t]);
                sb.append("\n");
            }
			//
			Result player=instance.analyze(sb.toString());
			//
			if (RESULTS[nr].equals(player)) {
			    System.out.println("V : "+RESULTS[nr]+" Ok!");
			    result=true;
			} else {
			    System.out.println("X : "+player+" is incorrect, the expected result was "+RESULTS[nr]+".");
			    result=false;
			}
			//
		} catch (Exception ex) {
		    System.out.println("Oh dear, this ballot card went totally the wrong way..");
			//
			// Catch the exception, so that other tests may
			// still be executed. Do print the stacktrace. 
			//
			ex.printStackTrace();
			//
			return false;
		}
		//
		return result;
	}
	
}
