import nl.moj.model.Tester;

public class LawOfThirdsTester implements Tester.Testable {

    private static final String[] NAMES = new String[] { "DisasterArea IT", "Sojgo", "Sojeti", "Xabie", "PacMinige", "UPruffs", "WhitePorches", "Float Inc",
            "Titanic BV" };

    private static int[][] EMPLOYEES = new int[][] { { 25, 9 }, { 80, 68 }, { 296, 256 }, { 50, 45 }, { 1542, 1321 }, { 102, 87 }, { 77, 64 }, { 45, 30 },
            { 42, 12 } };

    private int[] HRSAWEEK = new int[] { 40, 40, 40, 40, 36, 32, 28, 32, 8 };

    private double[] TARIFFS = new double[] { 80.0, 82.5, 75.0, 110.0, 99.99, 80.0, 65.0, 98.0, 45.0 };

    public int getTestCount() {
        return NAMES.length;
    }

    public String getTestName(int nr) {
        return NAMES[nr];
    }

    public String getTestDescription(int nr) {
        StringBuffer sb = new StringBuffer();
        sb.append(NAMES[nr] + " looks like an interesting company of " + EMPLOYEES[nr][0] + " employees.\n");
        sb.append("Rumor has it that " + EMPLOYEES[nr][1] + " are currently assigned to a project. You are offered \n");
        sb.append("a contract of " + HRSAWEEK[nr] + " hours a week. Your wage will be " + TARIFFS[nr] + " euros an hour.\n");
        sb.append("\n");
        sb.append("Determine your approximate salary and if the company is profitable.\n\n");
        ReferenceCompany ref = new ReferenceCompany();
        sb.append("Expected results :\n");
        sb.append("Your salary : " + ref.getEstimatedSalary(HRSAWEEK[nr], TARIFFS[nr]) + " euro's per month.\n");
        sb.append("Is the company profitable : " + (ref.isMakingProfit(EMPLOYEES[nr][0], EMPLOYEES[nr][1]) ? "Ye$!" : "It will sink like a brick."));
        //
        // TODO: Generate useful test description.
        //
        return sb.toString();
    }

    public boolean performTest(int nr) throws Throwable {
        //
        // Assume the best
        //
        boolean result = true;
        //
        // Create a new Instance for each test.
        //
        LawOfThirdsCompany instance = new LawOfThirdsCompany();
        ReferenceCompany reference = new ReferenceCompany();
        try {
            double sal1 = instance.getEstimatedSalary(HRSAWEEK[nr], TARIFFS[nr]);
            double sal2 = reference.getEstimatedSalary(HRSAWEEK[nr], TARIFFS[nr]);
            if (Math.abs(sal1 - sal2) > 0.01) {
                System.out.println("You reported that the estimated salary is " + sal1);
                System.out.println("when in fact it should be " + sal2 + ".");
                result = false;
            } else {
                System.out.println("Estimated salary for " + HRSAWEEK[nr] + "*" + TARIFFS[nr] + ": " + sal1);
            }
            boolean pr1 = instance.isMakingProfit(EMPLOYEES[nr][0], EMPLOYEES[nr][1]);
            boolean pr2 = reference.isMakingProfit(EMPLOYEES[nr][0], EMPLOYEES[nr][1]);
            if (pr1 != pr2) {
                System.out.println("You reported that " + NAMES[nr] + " is " + (!pr1 ? "not " : "") + "making a profit,");
                System.out.println("when in fact they are " + (!pr2 ? "not " : "") + "making a profit.");
                result = false;
            } else {
                System.out.println(NAMES[nr] + " makes a profit: " + pr1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        //
        // TODO: Perform the specified test.
        //
        return result;
    }

}
