import nl.moj.model.Tester;

public class EnemyOfTheStateTester implements Tester.Testable {

    private static final String[] NAMES = new String[] { "Action Movie", "The Lazy State", "Can Run, Can't Hide", "What happened?" };

    private static final int[] INIT_DISTANCE = new int[] { 4, 6, 2, 2 };

    private static final int[] HIDE_DISTANCE = new int[] { 7, 11, 7, 3 };

    private static final int[] RUN_SPEED = new int[] { 2, 2, 1, 0 };

    private static final int[] RESULT = new int[] { 1, 1, 0, -1 };

    public int getTestCount() {
        return NAMES.length;
    }

    public String getTestName(int nr) {
        return NAMES[nr];
    }

    public String getTestDescription(int nr) {
        StringBuffer sb = new StringBuffer();
        sb.append("The innocent Fugitive is on the run for the Government. He needs to find evidence to prove that\n");
        sb.append("he is really innocent. To do this he has to go unnoticed to an evidence location and search for\n");
        sb.append("the evidence and then use the evidence. If the Goverment finds him though, he has to run and hide\n");
        sb.append("to prevent being caught.\n");
        //
        sb.append("\nThe Fugitive has the following conditional actions at his disposal:\n");
        sb.append("- doRun() : (hopefully) increases the distance between him and the Government agents.\n");
        sb.append("- doHide() : if the distance is >=3 he can hide out of sight from the agents.\n");
        sb.append("- doGoToEvidenceLocation() : When hidden he can go to an evidence location.\n");
        sb.append("- doSearch() : If at an evidence location the he can search for evidence.\n");
        sb.append("- doUse(..) : If hiding or at an Evidence Location he can use the evidence to prove innocence.\n");
        //
        sb.append("\nThe Fugitive needs 3 pieces of evidence before the Government believes him.\n");
        //
        sb.append("\nThe expected outcome of this test is:");
        switch (RESULT[nr]) {
        case -1: sb.append("\nThe Government catches the Fugitive before he can find enough evidence.\n");break;
        case  0: sb.append("\nNeither the Government or Fugitive can win this round.\n");break;
        case  1: sb.append("\nThe Fugitive succesfully proves his innocence.\n");break;
        }
        //
        return sb.toString();
    }

    public boolean performTest(int nr) throws Throwable {
        //
        // Assume the worst
        //
        boolean result = false;
        //
        // Create a new Instance for each test.
        //
        try {
            BraveNewWorld w = new BraveNewWorld(INIT_DISTANCE[nr], HIDE_DISTANCE[nr], RUN_SPEED[nr]);
            Government g = new Government(w);
            Fugitive e = new Fugitive(w);
            w.set(g, e);
            //
            int cycles = 42;
            while ((!w.isFinished()) && (cycles-- > 0)) {
                g.onDo();
            }
            //
            switch (RESULT[nr]) {
            case -1:
                if (!w.isWonByGovernment()) {
                    System.out.println("X: The game should have been won by the Government.");
                } else {
                    result = true;
                }
                break;
            case 0:
                if (w.isFinished()) {
                    System.out.println("X: The game should have been a tie.");
                } else {
                    result = true;
                }
                break;
            case 1:
                if (!w.isWonByFugitive()) {
                    System.out.println("X: The game should have been won by the Fugitive.");
                } else {
                    result = true;
                }
                break;
            }
            //

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
