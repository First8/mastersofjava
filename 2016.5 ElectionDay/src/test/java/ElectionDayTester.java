import nl.moj.model.Tester;

public class ElectionDayTester implements Tester.Testable {
	
	private static final String[] NAMES=new String[] {
			"Vote EXIT",
			"Vote STAY",
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
	
	private static final String[] CARDS=new String[] {
	        "            ; EXIT   [X] ; STAY   [ ] ;            ",	        
            "            ; EXIT   [ ] ; STAY   [#] ;            ",          
            "            ; STAY   [ ] ; EXIT   [ ] ;            ", 
            "            ; STAY   [X] ; EXIT   [X] ;            ",          
            " EXIT  [ ]  ; STAY   [ ] ; EXIT   [X] ; STAY   [ ] ",
            " EXIT   [ ] STAY [*]",
            "           ; EXIT   ;        [O];           ;     STAY   ; [ ]       ",
            "            ; ErrXIT   [ ] ; STAY   [X] ;            ",
            "            ;            ",
            "STEXITAY[ ]EXSTAYIT[[[]X ",
            "EXIT   [ ];STAY   [X];STAY   [ ]"
	};
	
	private static final Result[] RESULTS = new Result[] {
		Result.EXIT,
		Result.STAY,
		Result.BLANK,
		Result.INVALID,
		Result.INVALID,
		Result.STAY,
		Result.EXIT,
		Result.INVALID,
		Result.INVALID,
		Result.INVALID,
		Result.INVALID  
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
		sb.append("The following voting card is offered to you for evaluation:\n");
		for (int t=0;t<CARDS.length;t++) {
			String card = CARDS[nr];
			String[] cardLines = CARDS[nr].split(";");
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
			String[] cardLines = CARDS[nr].split(";"); 
			for (int t=0;t<cardLines.length;t++) {
				
				
				sb.append(cardLines[t]+"\n");
            }
			//
			System.out.println(nr + " : cards " + CARDS.length + " " +CARDS[nr] + " l " + CARDS[nr].split(";").length + "\n"); 
			
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
		    System.out.println("Oh dear, this voting card went totally the wrong way..");
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
