import nl.moj.model.Tester;

public class PaperScissorsStoneTester implements Tester.Testable {
	
	private static final String[] NAMES=new String[] {
			"Paper defeats Stone",
			"Stone defeats Scissors",
			"Scissors defeats Paper",
			"Paper Ties",
			"Stone Ties",
			"Scissors Ties",
	};
	
	private static final String SCISSORS="SCISSORS";
	private static final String PAPER="PAPER";
	private static final String STONE="STONE";
	
	private static final String[][] CASES=new String[][] {
	    {PAPER,STONE},
	    {STONE,SCISSORS},
	    {SCISSORS,PAPER},
	    {PAPER,PAPER},
	    {STONE,STONE},
	    {SCISSORS,SCISSORS},
	};
	
	public int getTestCount() {
		return NAMES.length;
	}

	public String getTestName(int nr) {
		return NAMES[nr];
	}
	
	public String getTestDescription(int nr) {
		StringBuffer sb=new StringBuffer();
		sb.append("Tests if "+CASES[nr][0]+" defeats "+CASES[nr][1]+".\n");
		//
		Outcome o1=null;
		Outcome o2=null;
		if (nr<3) {
		    o1=Outcome.WIN;
		    o2=Outcome.LOSE;
		} else {
            o1=Outcome.TIE;
            o2=Outcome.TIE;
		}
		//
		sb.append(CASES[nr][0]+".battles("+CASES[nr][1]+") must return "+o1+" \n");
		sb.append(CASES[nr][1]+".battles("+CASES[nr][0]+") must return "+o2+" \n");
		//
		return sb.toString();
	}
	
	public boolean performTest(int nr) throws Throwable {
		//
		// Assume the worst
		//
		boolean result=true;
		//
		try {
			//
		    PaperScissorsStoneImpl a=PaperScissorsStoneImpl.valueOf(CASES[nr][0]);
		    PaperScissorsStoneImpl b=PaperScissorsStoneImpl.valueOf(CASES[nr][1]);
		    //
            //
            Outcome o1=a.battles(b);
            Outcome o2=b.battles(a);
            //
            Outcome w1=(nr<3?Outcome.WIN:Outcome.TIE);
            Outcome w2=(nr<3?Outcome.LOSE:Outcome.TIE);
            //
            if ((o2==null)||(!o1.equals(w1))) {
                System.out.println("Fail: "+a+".battles("+b+") should result in "+w1+" instead of "+o1);
                result=false;
            }
            if ((o2==null)||(!o2.equals(w2))) {
                System.out.println("Fail: "+b+".battles("+a+") should result in "+w2+" instead of "+o2);
                result=false;
            }
			//
		} catch (Exception ex) {
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
