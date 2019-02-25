
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class LambdaPuzzleSolution extends LambdaPuzzleImpl{
	

	public List<Integer> primes(final int fromNumber, final int count) {
		return Stream
				.iterate(primeAfter(fromNumber - 1),
						LambdaPuzzleSolution::primeAfter).limit(count)
				.collect(Collectors.<Integer> toList());
	}


}

