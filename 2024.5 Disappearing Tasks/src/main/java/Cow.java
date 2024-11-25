/**
 * An class representing a cow.
 * @param name name of the cow
 * @param inHeat whether the cow is in heat (i.e. ready to be inseminated)
 * @param pregnant whether the cow is pregnant
 * @param ill whether the cow is ill
 */
public record Cow(String name, boolean inHeat, boolean pregnant, boolean ill) implements TaskSubject {
}
