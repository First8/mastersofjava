import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetacheringImpl {

	// Utility classes om de kosten berekeningen uit te voeren
	private KostenCalculatorImpl calc = new KostenCalculatorImpl();
	private KostenPerPeriodeCalculatorImpl calcPerPeriode = new KostenPerPeriodeCalculatorImpl();
	
	// Opdracht parameters
	private int[] minimaleBezettingPerPeriode;
	private int maximumMedewerkers;
	private int aantalPeriodes;
	
	// Tabellen voor deel oplossingen
	private double[][] kostenTabel;
	private List<Integer>[][] oplossingTabel;
	
	public List<Integer> berekenOptimaleBezetting(int[] minimaleBezettingPerPeriode, int maximumMedewerkers) {
		init(minimaleBezettingPerPeriode, maximumMedewerkers);

		//
		// Los de opdracht op middels recursief top-down.
		// Verbeter dit algoritme middels het concept Dynamisch Programmeren.
		//
		
		Oplossing oplossing = berekenOptimaleBezettingRecursiefTopDown(1, maximumMedewerkers);
		return oplossing.bezetting;
		
		//
		//    OF
		//
		
		//
		// Los de opdracht op middels recursief bottom-up.
		// Maak de implementatie van dit algoritme af.
		//
		
		//berekenOptimaleBezettingRecursiefBottomUp(aantalPeriodes);
		//return oplossingTabel[0][maximumMedewerkers-1];
	}
	
	/**
	 * Recursieve top-down methode voor het berekenen van de optimale bezetting.
	 * 
	 * * Optie 1: Refactor deze methode met behulp van concept dynamisch programmeren
	 * 
	 */
	private Oplossing berekenOptimaleBezettingRecursiefTopDown(int periode, int aantalMedewerkers) {
		int periodeIdx = convertPeriodeToArrayIndex(periode);
		
		double minimaleKosten = Double.MAX_VALUE;
		List<Integer> optimaleOplossing = new ArrayList<>();
		
		if(periode < aantalPeriodes) {
			for(int aantalMedewerkersVolgendePeriode = minimaleBezettingPerPeriode[periodeIdx+1];
					aantalMedewerkersVolgendePeriode <= maximumMedewerkers; aantalMedewerkersVolgendePeriode++) {
				
				double kosten = calcPerPeriode.getKostenOvertalligPersoneel(minimaleBezettingPerPeriode[periodeIdx], aantalMedewerkers);
				List<Integer> eenOplossing = new ArrayList<>();

				Oplossing recursieveDeelOplossing = berekenOptimaleBezettingRecursiefTopDown(periode + 1, aantalMedewerkersVolgendePeriode);
				eenOplossing.addAll(recursieveDeelOplossing.bezetting);
				eenOplossing.add(0, aantalMedewerkers);
				kosten += recursieveDeelOplossing.kosten;
				kosten += calcPerPeriode.getKostenOntslaanAannemen(aantalMedewerkers, aantalMedewerkersVolgendePeriode);
				
				if(kosten < minimaleKosten) {
					minimaleKosten = kosten;
					optimaleOplossing = eenOplossing;
				}
			}
		} else {
			optimaleOplossing.add(aantalMedewerkers);
			minimaleKosten = calcPerPeriode.getKostenOvertalligPersoneel(minimaleBezettingPerPeriode[periodeIdx], aantalMedewerkers);
		}
		
		return new Oplossing(optimaleOplossing, minimaleKosten);
	}
	
	/**
	 * Recursieve bottom-up methode voor het berekenen van de optimale bezetting.
	 * 
	 * * Optie 2: Maak de implementatie van deze methode af.
	 * 
	 */
/* 	private void berekenOptimaleBezettingRecursiefBottomUp(int periode) {
		int periodeIdx = convertPeriodeToArrayIndex(periode);
		
		for(int aantalMedewerkers = // Duke is clueless // ) {
			
			double kosten = calcPerPeriode.getKostenOvertalligPersoneel(minimaleBezettingPerPeriode[periodeIdx], aantalMedewerkers);
			List<Integer> deelOplossing = new ArrayList<>();
			
			if( // Duke is clueless // ) {
				double minRecKosten = Double.MAX_VALUE;
				for(int aantalMedewerkersVolgendePeriode = // Duke is clueless // ) {
					
					double recursieveKosten = calcPerPeriode.getKostenOntslaanAannemen(aantalMedewerkers, aantalMedewerkersVolgendePeriode)
							+ kostenTabel[periodeIdx + 1][aantalMedewerkersVolgendePeriode - 1];
					
					if( // Duke is clueless // ) {
						deelOplossing = oplossingTabel[periodeIdx + 1][aantalMedewerkersVolgendePeriode - 1];
						minRecKosten = recursieveKosten;
					}
				}
				kosten += minRecKosten;
			}
			
			List<Integer> kopieOplossing = new ArrayList<Integer>();
            kopieOplossing.add(i);
            kopieOplossing.addAll(deelOplossing);
			kostenTabel[periodeIdx][aantalMedewerkers - 1] = kosten;
			oplossingTabel[periodeIdx][aantalMedewerkers - 1] = kopieOplossing;
		}
		
		if( // Duke is clueless // ) {
			berekenOptimaleBezettingRecursiefBottomUp(periode - 1);
		}
	}
*/
	
	/**
	 * Initialisatie methode voor het setten van de class variabelen
	 * en voor het initialiseren van de kosten en oplossingen tabellen.
	 */
	private void init(int[] minimaleBezettingPerPeriode, int maximumMedewerkers) {
		this.minimaleBezettingPerPeriode = minimaleBezettingPerPeriode;
		this.maximumMedewerkers = maximumMedewerkers;
		this.aantalPeriodes = minimaleBezettingPerPeriode.length;
		
		int maxDif = 0;
		for(int i = 0; i < minimaleBezettingPerPeriode.length; i++) {
			maxDif = Math.max(minimaleBezettingPerPeriode[i], maximumMedewerkers);
		}
		kostenTabel = new double[minimaleBezettingPerPeriode.length][maxDif];
		oplossingTabel = new List[minimaleBezettingPerPeriode.length][maxDif];
	}
	
	/**
	 * Handige methode om je oplossingTabel te printen.
	 */
	private void printOplossingTabel() {
		for(int i = 0; i < oplossingTabel.length; i++) {
			System.out.print("[");
			for(int j = 0; j < oplossingTabel[i].length; j++) {
				if(oplossingTabel[i][j] != null) {
					System.out.print(oplossingTabel[i][j] + ",");
				}
			}
			System.out.println("]");
		}
	}
	
	private int convertPeriodeToArrayIndex(int periode) {
		return periode-1;
	}
}