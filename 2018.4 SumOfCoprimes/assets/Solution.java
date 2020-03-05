import java.util.ArrayList;
import java.util.List;

public class SumOfCoprimes {

	// efficient way (https://github.com/jalman/JavaCodeGolf/tree/master/SumOfCoprimes)
	int execute(int M) {
		int sum = 0;
		
		for (int prime = 1; ++prime < M;) {
			int currentPrime = prime;
			int bMax = M;

			while ( bMax > 0) {
				int nextMax = currentPrime % bMax;
				currentPrime = bMax;
				bMax = nextMax;
			}
			if (currentPrime == 1) {
				sum = (sum + prime) % 201201;
			}
		}
		return sum;
	}
// easy way
	
	int execute(int m) {

		List<Integer> coprimes = new ArrayList<Integer>();
		
		List<Integer> ms = factor(m);
		for (int i=2; i<m; i++) {
			List<Integer> is = factor(i);
			
			System.out.println("non shared factors between " + m + " and " + i + ": " + is);
			if( !is.removeAll(ms)) {
				coprimes.add(i);
			}
		}
		
		System.out.println("Coprimes of " + m + ": " + coprimes);
		
		int sum = 0;
		for (int i :coprimes) {
			sum += i;
		}
 		return sum % 201201;
	}

	private List<Integer> factor(int m) {
		List<Integer> ms = new ArrayList<>();
		for (int i=2; i<=m; i++) {
			if ( m % i == 0) {
				ms.add(i);
			}
		}
		
		System.out.println("factor " + m + " = " + ms);
		return ms;
	}
	
	
	
}
