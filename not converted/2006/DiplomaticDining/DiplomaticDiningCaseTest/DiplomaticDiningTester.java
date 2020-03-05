import nl.moj.model.Tester;

public class DiplomaticDiningTester implements Tester.Testable {
	
	private static final String[] NAMES=new String[] {
		"Diplofarcy",
		"Head2Head",
		"Trilateral",
		"Mismatch",
		"Frysian Affairs",
		"Big Negotiations",
		"The Korea Issue"
	};
	
	private static final String[][] DIPLOMATS=new String[][] {
		{
			"FRA F GBR BEL DLD NLD",
		},{
			"FRA FE GBR",
			"GBR EF FRA",
		},{
			"FRA FE GBR DLD",
			"DLD DE FRA GBR",
			"GBR ED FRA DLD"
		},{
			"FRA F DLD GBR",
			"DLD DEF GBR FRA",
			"GBR E FRA DLD"
		},{
			"NLD F",
			"NLD D",
			"NLD F",
			"NLD DF",
			"NLD DF",
		},{
			"USA EF CHN GBR USR FRA FRG JPN ISR POR KOR",
			"CHN CFE USA GBR FRA FRG",
			"GBR ER USA CHN USR FRA FRG JPN ISR POR KOR",
			"USR RF USA GBR FRA FRG",
			"FRA F USA CHN GBR USR FRG JPN ISR POR",
			"FRG ERG USA CHN GBR USR FRA JPN ISR POR",
			"JPN JHG USA GBR FRA FRG JPN ISR POR KOR",
			"ISR HER USA GBR FRA FRG JPN KOR",
			"POR PGE USA GBR FRA FRG JPN",
			"KOR KEC USA GBR USR JPN ISR"
		},{
			"USA EF CHN USR KOR",
			"CHN CE USA USR CRK",
			"USR RC USA CHN CRK",
			"KOR KEC USA CRK",
			"CRK KEC CHN USR KOR"
		}
	};
	
	private static final boolean[] RESULTS=new boolean[] {
		true,true,true,false,true,
		true,true
	};
	
	private static final int[] LEADERS=new int[] {
		0,0,0,0,1,
		1,2
	};
	
	public int getTestCount() {
		return NAMES.length;
	}

	public String getTestName(int nr) {
		return NAMES[nr];
	}
	
	public String getTestDescription(int nr) {
		StringBuffer sb=new StringBuffer();
		TableImpl table=new TableImpl(DIPLOMATS[nr].length);
		Diplomat[] dip=new Diplomat[DIPLOMATS[nr].length];
		for (int t=0;t<dip.length;t++) {
			dip[t]=new DiplomatImpl(DIPLOMATS[nr][t],LEADERS[nr]==t);
		}
		if (DIPLOMATS[nr].length==1) {
			sb.append("The french ambassador wants to try the leaders seat. Please help him.\n");
		} else {
			sb.append(DIPLOMATS[nr].length+" diplomats have gathered to negotiate. Help them into a proper seating arrangement.\n");
			sb.append("Diplomats next to each other should speak at least a common language and have\n");
			sb.append("formal relations with each others countries. The leader diplomat must sit at\n"); 
			sb.append("the head of the table. \n");
		}
		for (int t=0;t<dip.length;t++) {
			sb.append(dip[t].toString());
			sb.append("\n");
		}
		sb.append("\n");
		boolean result=new InternalSolver().seatDiplomats(table, dip);
		if (result) {
			sb.append("The method should return true. ");
			sb.append("A possible solution might be:\n");
			sb.append(table);
		} else {
			sb.append("The method should return false. ");
			sb.append("There is no possible solution.\n");
		}
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
			TableImpl table=new TableImpl(DIPLOMATS[nr].length);
			Diplomat[] dip=new Diplomat[DIPLOMATS[nr].length];
			for (int t=0;t<dip.length;t++) {
				dip[t]=new DiplomatImpl(DIPLOMATS[nr][t],LEADERS[nr]==t);
			}
			TableSeaterImpl instance=new TableSeaterImpl();
			//
			boolean conclusion=instance.seatDiplomats(table, dip);
			//			
			result=conclusion==RESULTS[nr];
			//
			if (!result) {
				System.out.print("Your conclusion was that a solution is "+(conclusion?"possible":"impossible")+", ");
				System.out.println("however a solution is "+(RESULTS[nr]?"possible":"impossible")+".");
			}
			//
			if (RESULTS[nr]) {
				if (!table.check()) {
					result=false;
					System.out.println(table);
				}
			} else {
				if (!result) {
					System.out.println(table);
				}
			}
			//
			if (result) {
				System.out.println("Pass.");
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
