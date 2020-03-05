/**
 * @author MoJ 2013
 */
public class KostenPerPeriodeCalculatorImpl {
	
	/**
	 * Constante in de berekening van de kosten voor ontslaan/aannemen van een medewerker.
	 */
	private static final int KOSTEN_ONTSLAAN_AANNEMEN = 200;
	
	/**
	 * Kosten per medewerker
	 */
	private static final int KOSTEN_BESCHIKBAARHEID = 2000;
	
	/**
	 * Kosten per medewerker voor het ontslaan dan wel inhuren.
	 * 
	 * @param oudAantal Aantal medewerkers van voorgaande periode.
	 * @param nieuwAantal Aantal medewerkers van nieuwe periode.
	 * @return Het verschil in medewerkers gekwadrateerd * kosten voor ontslaan/aannemen (200).
	 */
	public double getKostenOntslaanAannemen(int oudAantal, int nieuwAantal) {
		return getKostenOntslaanAannemen(Math.abs(nieuwAantal - oudAantal));
	}
	
	/**
	 * Kosten voor ontslaan/aannemen
	 * @param aantal Aantal ontslagen/nieuwe medewerkers.
	 * @return Kosten voor ontslaan/aannemen.
	 */
	public double getKostenOntslaanAannemen(int aantal) {
		return Math.pow(aantal,2) * KOSTEN_ONTSLAAN_AANNEMEN;
	}
	
	/**
	 * Kosten voor personeel dat niet ingezet kan worden kost 2.000 euro per medewerker.
	 * 
	 * @param minimaleBezetting Aantal medewerkers dat ingezet kan worden.
	 * @param aantal Aantal medewerkers in dienst
	 * @return Kosten door de overtallige medewerkers
	 */
	public double getKostenOvertalligPersoneel(int minimaleBezetting, int aantal) {
		if(aantal > minimaleBezetting) {
			return (aantal - minimaleBezetting) * KOSTEN_BESCHIKBAARHEID;
		} else if(aantal < minimaleBezetting) {
			throw new IllegalArgumentException("Aantal medewerkers mag niet lager zijn dan aantal in te zetten medewerkers.");
		} else {
			return 0.0;
		}
	}
}
