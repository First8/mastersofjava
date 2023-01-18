/**
 * A guessable character in the game of guess who
 */
public class Person {
    private final String name;
    private HairColor hairColor;
    private EyeColor eyeColor;
    private boolean hasGlasses;
    private boolean hasHeadgear;
    private boolean hasMustache;
    private boolean hasBeard;

    public Person(String name) {
        this.name = name;
    }

    public HairColor getHairColor() {
        return hairColor;
    }

    public EyeColor getEyeColor() {
        return eyeColor;
    }

    public boolean hasGlasses() {
        return hasGlasses;
    }

    public boolean hasHeadgear() {
        return hasHeadgear;
    }

    public boolean hasMustache() {
        return hasMustache;
    }

    public boolean hasBeard() {
        return hasBeard;
    }

    public Person hairColor(HairColor hairColor) {
        this.hairColor = hairColor;
        return this;
    }

    public Person eyeColor(EyeColor eyeColor) {
        this.eyeColor = eyeColor;
        return this;
    }

    public Person glasses() {
        this.hasGlasses = true;
        return this;
    }

    public Person headgear() {
        this.hasHeadgear = true;
        return this;
    }

    public Person mustache() {
        this.hasMustache = true;
        return this;
    }

    public Person beard() {
        this.hasBeard = true;
        return this;
    }

    @Override
    public String toString() {
        return name;
    }
}
