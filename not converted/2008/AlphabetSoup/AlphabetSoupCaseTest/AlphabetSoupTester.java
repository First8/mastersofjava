import nl.moj.model.Tester;

public class AlphabetSoupTester implements Tester.Testable {
	
	private static final String[] NAMES=new String[] {
	    "Cheap Soup",
	    "Cheap Soup Again",
	    "Really Cheap Soup",
	    "Too Cheap Soup",
	    "Tomato Soup",
	    "Letter Soup",
		"ISO9002 Soup",
		"More ISO9002 Soup",
		"Production Fault",
		"Favorite Book",
		"Lost for Words",
		"Strange Word",
		"Letters Fall Out",
		"AlphaNumeric Soup"
	};
	
	private static final String[] SOUP=new String[] {
	    "AEOIU",
	    "AEOIU",
	    "EE",
	    "EE",
	    "",
	    "SDPFOKTOMWYTVEADASDIFJRTSDIOFCVN",
	    "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
	    "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
        "AAADEEEHIIILMNOOORSTUUUXYZ",
        "AAACDEEEGGHHHIIIKLLOUUTTTSRYX",
        "AAACDEEEGGHHHIIIKLLOUUTTTSRYX",
        "BADCAFEHGJILKNMPORQTSUVWXYZE",
        "POWQREDAZBNXVTRUHEZGDRPOSKDCPODKHHGDIOPO",
        "01OPO2SOID3ZXB4FS5HHR6SD7GFS8SAD9XXA"        
	};
	
	private static final String[] WORD=new String[] {
	    "E",
	    "M",
	    "EE",
	    "EEE",
	    "TOMATO",
	    "TOMATO",
	    "WODKASJU",
	    "PINDAKAAS",
	    "HITCHIKERSGUIDETOTHEGALAXY",
	    "HITCHIKERSGUIDETOTHEGALAXY",
	    "",
	    "ÉEN",
	    "WATERCHIP",
	    "H4XX0R"
	};
	private static final boolean[] EXPECTED=new boolean[] {
	    true,
	    false,
	    true,
	    false,
	    false,
	    true,
	    true,
	    false,
	    false,
	    true,
	    true,
	    false,
	    true,
	    true,
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
		sb.append("The soup contains the following letters: '"+SOUP[nr]+"'.\n");
		sb.append("Check if the word '"+WORD[nr]+"' can be made out of the soup letters.\n");
		sb.append("Each letter from the soup can only be used once.\n\n");
		sb.append("The expected result is that the word "+(EXPECTED[nr]?"CAN":"CAN'T")+" be made.");
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
			AlphabetSoupImpl instance=new AlphabetSoupImpl();
			//
			// TODO: Perform the specified test.
			//
			boolean user=instance.canMakeWord(SOUP[nr],WORD[nr]);
			//
			if (user!=EXPECTED[nr]) {
			    System.out.println("X: Your result was '"+user+"' but '"+EXPECTED[nr]+"' was expected.");
			    System.out.println("   It is "+(EXPECTED[nr]?"possible":"impossible")+" to make the word '"+WORD[nr]+"' out of");
			    System.out.println("   the following soup letters '"+SOUP[nr]+"'");
			    result=false;
			} else {
			    System.out.println("V: Good!");
			    result=true;
			}
			//
		} catch (Exception ex) {
			//
			// Catch the exception, so that other tests may
			// still be executed. Do print the stacktrace. 
			//
		    System.out.println("I guess this soup was too hot..");
			ex.printStackTrace();
			//
			return false;
		}
		//
		return result;
	}
	
}
