import java.util.ArrayList;
import java.util.Collection;

public class Dataset {

    static final String VAMPIRE_WEEKEND = "Vampire Weekend";
    static final String THE_STROKES = "The Strokes";
    static final String SLIGHTLY_STOOPID = "Slightly Stoopid";
    static final String PHOENIX = "Phoenix";
    static final String NORAH_JONES = "Norah Jones";
    static final String DEADMAU5 = "Deadmau5";
    static final String BROKEN_BELLS = "Broken Bells";
    static final String BLUES_TRAVELER = "Blues Traveler";
    static final String JORDYN = "Jordyn";
    static final String HAILEY = "Hailey";
    static final String VERONICA = "Veronica";
    static final String SAM = "Sam";
    static final String DAN = "Dan";
    static final String CHAN = "Chan";
    static final String BILL = "Bill";
    static final String ANGELICA = "Angelica";

    public static Collection<Rating> set1() {
        Collection<Rating> ratings = new ArrayList<>();

        ratings.add(new Rating(BLUES_TRAVELER, ANGELICA, 3.5f));
        ratings.add(new Rating(BLUES_TRAVELER, BILL, 2f));
        ratings.add(new Rating(BLUES_TRAVELER, CHAN, 5f));
        ratings.add(new Rating(BLUES_TRAVELER, DAN, 3f));
        ratings.add(new Rating(BLUES_TRAVELER, SAM, 5f));
        ratings.add(new Rating(BLUES_TRAVELER, VERONICA, 3f));

        ratings.add(new Rating(BROKEN_BELLS, ANGELICA, 2f));
        ratings.add(new Rating(BROKEN_BELLS, BILL, 3.5f));
        ratings.add(new Rating(BROKEN_BELLS, CHAN, 1f));
        ratings.add(new Rating(BROKEN_BELLS, DAN, 4f));
        ratings.add(new Rating(BROKEN_BELLS, HAILEY, 4f));
        ratings.add(new Rating(BROKEN_BELLS, JORDYN, 4.5f));
        ratings.add(new Rating(BROKEN_BELLS, SAM, 2f));

        ratings.add(new Rating(DEADMAU5, BILL, 4f));
        ratings.add(new Rating(DEADMAU5, CHAN, 1f));
        ratings.add(new Rating(DEADMAU5, DAN, 4.5f));
        ratings.add(new Rating(DEADMAU5, HAILEY, 1f));
        ratings.add(new Rating(DEADMAU5, JORDYN, 4f));

        ratings.add(new Rating(NORAH_JONES, ANGELICA, 4.5f));
        ratings.add(new Rating(NORAH_JONES, CHAN, 3f));
        ratings.add(new Rating(NORAH_JONES, HAILEY, 4f));
        ratings.add(new Rating(NORAH_JONES, JORDYN, 5f));
        ratings.add(new Rating(NORAH_JONES, SAM, 3f));
        ratings.add(new Rating(NORAH_JONES, VERONICA, 5f));

        ratings.add(new Rating(PHOENIX, ANGELICA, 5f));
        ratings.add(new Rating(PHOENIX, BILL, 2f));
        ratings.add(new Rating(PHOENIX, CHAN, 5f));
        ratings.add(new Rating(PHOENIX, DAN, 3f));
        ratings.add(new Rating(PHOENIX, JORDYN, 5f));
        ratings.add(new Rating(PHOENIX, SAM, 5f));
        ratings.add(new Rating(PHOENIX, VERONICA, 4f));

        ratings.add(new Rating(SLIGHTLY_STOOPID, ANGELICA, 1.5f));
        ratings.add(new Rating(SLIGHTLY_STOOPID, BILL, 3.5f));
        ratings.add(new Rating(SLIGHTLY_STOOPID, CHAN, 1f));
        ratings.add(new Rating(SLIGHTLY_STOOPID, DAN, 4.5f));
        ratings.add(new Rating(SLIGHTLY_STOOPID, JORDYN, 4.5f));
        ratings.add(new Rating(SLIGHTLY_STOOPID, SAM, 4f));
        ratings.add(new Rating(SLIGHTLY_STOOPID, VERONICA, 2.5f));

        ratings.add(new Rating(THE_STROKES, ANGELICA, 2.5f));
        ratings.add(new Rating(THE_STROKES, DAN, 4f));
        ratings.add(new Rating(THE_STROKES, HAILEY, 4f));
        ratings.add(new Rating(THE_STROKES, JORDYN, 4f));
        ratings.add(new Rating(THE_STROKES, SAM, 5f));
        ratings.add(new Rating(THE_STROKES, VERONICA, 3f));

        ratings.add(new Rating(VAMPIRE_WEEKEND, ANGELICA, 2f));
        ratings.add(new Rating(VAMPIRE_WEEKEND, BILL, 3f));
        ratings.add(new Rating(VAMPIRE_WEEKEND, DAN, 2f));
        ratings.add(new Rating(VAMPIRE_WEEKEND, HAILEY, 1f));
        ratings.add(new Rating(VAMPIRE_WEEKEND, JORDYN, 4f));

        return ratings;
    }
}
