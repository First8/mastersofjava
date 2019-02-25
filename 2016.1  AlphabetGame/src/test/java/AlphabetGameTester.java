import nl.moj.model.Tester;

public class AlphabetGameTester implements Tester.Testable {
	
	private static final String[] NAMES=new String[] {
	    "Cheap Game",
	    "Cheap Game Again",
	    "Really Cheap Game",
	    "Too Cheap Game",
	    "Tomato Game",
	    "Letter Game",
		"ISO9002 Game",
		"More ISO9002 Game",
		"Production Fault",
		"Favorite Book",
		"Lost for Words",
		"Strange Word",
		"Letters Fall Out",
		"AlphaNumeric Game"
	};
	
	private static final String[] COMPOSITION=new String[] {
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
	    "?EN",
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
		sb.append("The composition contains the following letters: '"+COMPOSITION[nr]+"'.\n");
		sb.append("Check if the word '"+WORD[nr]+"' can be made out of the letters.\n");
		sb.append("Each letter from the composition can only be used once.\n\n");
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
			AlphabetGameImpl instance=new AlphabetGameImpl();
			//
			// TODO: Perform the specified test.
			//
			boolean user=instance.canMakeWord(COMPOSITION[nr],WORD[nr]);
			//
			if (user!=EXPECTED[nr]) {
			    System.out.println("X: Your result was '"+user+"' but '"+EXPECTED[nr]+"' was expected.");
			    System.out.println("   It is "+(EXPECTED[nr]?"possible":"impossible")+" to make the word '"+WORD[nr]+"' out of");
			    System.out.println("   the following letters '"+COMPOSITION[nr]+"'");
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
		    System.out.println("ERROR test instance nr " +nr );
			ex.printStackTrace();
			//
			return false;
		}
		//
		return result;
	}
	
}
