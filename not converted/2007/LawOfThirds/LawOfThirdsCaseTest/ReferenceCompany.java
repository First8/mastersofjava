public class ReferenceCompany implements LawOfThirds {
	
	public boolean isMakingProfit(int nrEmployees, int nrOfAssignedEmployees) {
		double cost = nrEmployees * 2.0/3.0;
		double revenue = nrOfAssignedEmployees;
		return revenue > cost;
	}

	public double getEstimatedSalary(int hoursAWeek, double tariff) {
		double sum = hoursAWeek * tariff * 4;
		return sum / 3;
	}
}
