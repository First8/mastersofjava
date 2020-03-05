import java.util.function.BiConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;

/**
 * A utility class that can be used in conjunction with implementations of
 * AnagramChecker. This utility class assumes the implementation will use the
 * following overloaded Stream.collect method to determine the result.
 */
public final class AnagramCheckerHelper {

	private AnagramCheckerHelper() {

	}

	public static Supplier<Long[]> supplier() {
		throw new UnsupportedOperationException("AnagramCheckerHelper.supplier not implemented yet");
	}

	public static ObjIntConsumer<Long[]> accumulator() {
		throw new UnsupportedOperationException("AnagramCheckerHelper.accumulator not implemented yet");
	}

}
