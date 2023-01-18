
public enum EyeColor {
    BROWN("brown"),
    BLUE("blue");

    private String text;

    EyeColor(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
