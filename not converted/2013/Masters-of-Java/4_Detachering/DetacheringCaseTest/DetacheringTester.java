
import java.util.List;

import nl.moj.model.Tester;

public class DetacheringTester implements Tester.Testable {
	
	private static final String[] MAANDEN = new String[] {
		"december",
		"januari",
		"februari",
		"maart",
		"april",
		"mei",
		"juni",
		"juli",
		"augustus",
		"september",
		"oktober",
		"november"
	};
	
	private static final String[] NAMES=new String[] {
			"Duke's onderneming",
			"Sogeti",
			"The big enterprise"
	};
	
	private static final int[][] VRAAG = new int[][] {
		{255, 228, 235, 210},
		{255, 210, 241, 210, 235},
		{300, 220, 240, 200, 230, 255, 220, 240, 200, 230},
	};
	
	private static final int[] MAXIMUM_MEDEWERKERS = new int[] {
		255, 255, 300
	};
	
	private static final double[] AWNSERS = new double[] {
		116600.0, 150400.0, 623600.0
	};
	
	public int getTestCount() {
		return NAMES.length;
	}

	public String getTestName(int nr) {
		return NAMES[nr];
	}
	
	public String getTestDescription(int nr) {
		StringBuffer sb=new StringBuffer();
		
		sb.append("Vraag voor ");
		int i = 0;
		for(int v : VRAAG[nr]) {
			sb.append(MAANDEN[(i % 12)]);
			sb.append(" ");
			sb.append(v);
			if(i < VRAAG[nr].length -1) { sb.append(", "); }
			i++;
		}
		sb.append(" aantal medewerkers.");
		
		sb.append("\n");
		sb.append("Het maximum aantal medewerkers is ");
		sb.append(MAXIMUM_MEDEWERKERS[nr]);
		sb.append(".");
		
		return sb.toString();
	}
	
	public boolean performTest(int nr) throws Throwable {

		boolean result=false;
		
		try {
			DetacheringImpl instance=new DetacheringImpl();

			KostenCalculatorImpl calc = new KostenCalculatorImpl();

			List<Integer> antwoord = instance.berekenOptimaleBezetting(VRAAG[nr].clone(), MAXIMUM_MEDEWERKERS[nr]);
			double kostenAntwoord = calc.berekenKosten(antwoord, VRAAG[nr]);
			
			result = kostenAntwoord == AWNSERS[nr];
			
			if(!result)
				System.out.println("Jou antwoord " + antwoord + " kost " + kostenAntwoord + " euro en is "
								+ (kostenAntwoord > AWNSERS[nr]?"te hoog!":"te laag!"));
			else
				System.out.println("Jou antwoord " + antwoord + " kost " + kostenAntwoord + " euro en is goed.");
			
		} catch (Exception ex) {
			
			System.out.println("Jouw implementatie heeft een " + ex.getClass().getName() + " veroorzaakt :");
			ex.printStackTrace();
			return false;
		} finally {
			//
		}

		return result;
	}
}
