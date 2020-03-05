import java.util.ArrayList;
import java.util.List;

import nl.ctrlaltdev.io.OutputRedirector;
import nl.moj.model.Tester;

public class MixingDrinksTester implements Tester.Testable {

    private static final String[] NAMES = new String[] {
        "Just one",
        "Erik drinks alone",
        "Couch Potatoes",
        "Dinner party",
        "Auch..."
    };

    private static final int[] INITIAL_AMOUNT = new int[] { 1,0,4,2,0 };
    private static final int[] CONTAINER_SIZE = new int[] { 4,4,4,4,1 };

    private static final String[][] DRINKERS = new String[][] {
        { "Erik" },
        { "Erik" },
        { "Erik","Marco" },
        { "Erik","Marco","Imar","Miriam" },
        { "Erik","Anhony","Robert","Jo","Jet" },
    };

    private static final int[][] DRINKS = new int[][] { 
        { 1 },
        { 8 },
        { 4,4 },
        { 4,4,8,4 },
        { 4,4,4,4,4 },
    };
    
    private static final int[] FRIDGE_SIZE=new int[] {
        4,4,4,4,1
    };

    public int getTestCount() {
        return NAMES.length;
    }

    public String getTestName(int nr) {
        return NAMES[nr];
    }

    public String getTestDescription(int nr) {
        StringBuffer sb = new StringBuffer();
        //
        for (int t=0;t<DRINKERS[nr].length;t++) {
            if (t>0) {
                if (t>=DRINKERS[nr].length-1) {
                    sb.append(" and ");
                } else {
                    sb.append(", ");
                }
            }
            sb.append(DRINKERS[nr][t]);
        }
        if (nr>1) {
            sb.append(" are drinking WodkaSju ");
        } else {
            sb.append(" is drinking WodkaSju ");
        }
        if (nr!=4) sb.append("at Eriks home.");
              else sb.append("at some strange nightclub."); 
        //
        sb.append("\n" );
        sb.append("To get drunk "+(nr>1?"they":"he")+" will drink the specified amount of WodkaSju as fast as possible:\n");        
        for (int t=0;t<DRINKERS[nr].length;t++) {
            sb.append(DRINKERS[nr][t]+": "+DRINKS[nr][t]+" drink"+(DRINKS[nr][t]==1?"":"s")+".\n");
        }
        sb.append("The mixing beaker has room for "+CONTAINER_SIZE[nr]+" drink"+(CONTAINER_SIZE[nr]==1?"":"s")+".\n\n");
        sb.append("Make sure that for every call to Person.drink(..):\n");
        sb.append("- A single drink is poured and sipped from by that person.\n");
        sb.append("- If the mixing container is empty it is refilled by the person that first discovered it was empty.\n");
        sb.append("- Everybody gets their specified amount of drinks.\n");
        //
        // TODO: Generate useful test description.
        //
        return sb.toString();
    }

    private List<Sju> createSju(int amount) {
        List<Sju> result = new ArrayList<Sju>();
        for (int t = 0; t < amount; t++) {
            result.add(new SjuImpl());
        }
        return result;
    }

    private List<Wodka> createWodka(int amount) {
        List<Wodka> result = new ArrayList<Wodka>();
        for (int t = 0; t < amount; t++) {
            result.add(new WodkaImpl());
        }
        return result;
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
            ThirstyPerson[] runners = new ThirstyPerson[DRINKERS[nr].length];
            Person[] person = new Person[runners.length];
            Thread[] tr = new Thread[runners.length];
            //            
            Fridge fridge=new FridgeImpl(FRIDGE_SIZE[nr]);
            DrinkContainerImpl container=new DrinkContainerImpl(CONTAINER_SIZE[nr]);
            container.makeDrinks(null,createSju(INITIAL_AMOUNT[nr]), createWodka(INITIAL_AMOUNT[nr]));
            //
            long worst = 0;
            try {
                for (int t = 0; t < runners.length; t++) {
                    person[t] = new Person(DRINKERS[nr][t]);
                    runners[t] = new ThirstyPerson(container, person[t],fridge, DRINKS[nr][t]);
                    tr[t] = new Thread(Thread.currentThread().getThreadGroup(), runners[t]);
                    tr[t].setDaemon(true);
                    tr[t].setName(person[t].toString());
                    tr[t].setPriority(Thread.MIN_PRIORITY);
                    // Redirect output of the new thread to the console of the player.
                    OutputRedirector.getSingleton().redirectToSame(tr[t], Thread.currentThread());
                    tr[t].start();
                    if (runners[t].getRuntime() > worst) {
                        worst = runners[t].getRuntime();
                    }
                }
                //
                boolean goOn;
                do {
                    goOn = false;
                    try {
                        worst -= 100L;
                        Thread.sleep(100L);
                    } catch (InterruptedException ex) {
                        goOn = false;
                    }
                    for (int t = 0; t < runners.length; t++) {
                        if (runners[t].isRunning()) {
                            goOn = true;
                        }
                    }
                } while ((goOn) && (worst > 0));
                //
                result=true;
                //
                for (int t = 0; t < runners.length; t++) {
                    if (runners[t].isSuccess()) {
                        System.out.println("V "+runners[t] + " got drunk succesfully.");
                    } else {
                        if (runners[t].getFailure()!=null) {
                            if (runners[t].getFailure().getMessage()==null) {
                                System.out.println("X "+runners[t] + " failed to get drunk because of a missing (null) drink.");
                            } else {
                                System.out.println("X "+runners[t] + " failed to get drunk because of "+runners[t].getFailure().getMessage());
                            }
                        } else {
                            System.out.println("X "+runners[t] + " failed to get drunk in time.");
                        }
                        result=false;
                    }
                }
                //
                if (result) {
                    if (container.getFailure()!=null) {
                        result=false;
                        System.out.println("X Somebody made a mess and tried to clean it up, but failed.");
                    }
                }
                //
            } finally {
                //
                for (Thread t : tr) {
                    if ((t!=null)&&(t.isAlive())) {
                        System.out.println("! " + t.getName() + " is deadlocked.");
                        result = false;
                        t.stop();
                    }
                    // Cancel the redirection.
                    OutputRedirector.getSingleton().cancel(t);
                }
                //
            }
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
