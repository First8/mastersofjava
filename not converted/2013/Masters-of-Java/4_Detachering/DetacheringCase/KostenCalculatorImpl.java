import java.util.List;

public class KostenCalculatorImpl {

	private KostenPerPeriodeCalculatorImpl calculator = new KostenPerPeriodeCalculatorImpl();
	
	/**
	 * Berekent de kosten van een gegeven oplossing/bezetting.
	 */
	public double berekenKosten(List<Integer> bezetting, int[] minimaleBezettingPerPeriode) {
		if(bezetting == null || bezetting.size() != minimaleBezettingPerPeriode.length) {
			throw new IllegalArgumentException("Opgegeven antwoord is niet van de juiste lengte. Antwoord moet " + minimaleBezettingPerPeriode.length + " periodes bevatten.");
		}
		
		int[] arr = new int[bezetting.size()];
		for(int i = 0; i < bezetting.size(); i++) {
			arr[i] = bezetting.get(i);
		}
		
		return berekenKosten(arr, minimaleBezettingPerPeriode);
	}
	
	/**
	 * Berekent de kosten van een gegeven oplossing/bezetting.
	 */
	public double berekenKosten(int[] bezetting, int[] minimaleBezettingPerPeriode) {
		if(bezetting.length != minimaleBezettingPerPeriode.length) {
			throw new IllegalArgumentException("Opgegeven antwoord is niet van de juiste lengte. Antwoord moet " + minimaleBezettingPerPeriode.length + " periodes bevatten.");
		}

		double totaleKosten = 0.0;
		
		for(int p = 0; p < bezetting.length; p++) {
			
			if(p < bezetting.length-1) {
				totaleKosten += calculator.getKostenOntslaanAannemen(bezetting[p], bezetting[p+1]);
			}
			
			totaleKosten += calculator.getKostenOvertalligPersoneel(minimaleBezettingPerPeriode[p], bezetting[p]);
		}
		
		return totaleKosten;
	}
}