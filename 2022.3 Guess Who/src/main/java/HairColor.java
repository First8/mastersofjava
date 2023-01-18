public enum HairColor {
    BLACK("black"),
    ORANGE("orange"),
    YELLOW("yellow"),
    WHITE("white"),
    BROWN("brown");

    private String text;

    HairColor(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
