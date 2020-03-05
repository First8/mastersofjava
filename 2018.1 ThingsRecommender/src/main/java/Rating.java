
public class Rating {
    private final String thing;
    private final String person;
    private final float score;

    public Rating(String thing, String person, float score) {
        this.thing = thing;
        this.person = person;
        this.score = score;

    }

    public String getThing() {
        return thing;
    }

    public String getPerson() {
        return person;
    }

    public float getScore() {
        return score;
    }

    public String toString() {
        return thing + ":" + score;
    }

}
