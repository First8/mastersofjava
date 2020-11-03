import java.lang.Character.UnicodeBlock;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CountingUnicode {

	private static final int ZWIDGE = 0x200D;


	public static int countCharacters(String str) {
		return countCharactersModernUnicode(str);
	}
	
	private static int countCharactersAscii(String str) {
		Set<Character> chars = new HashSet<Character>();

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			chars.add(ch);
		}
		return chars.size();
	}

	private static int countCharactersEarlyUnicode(String str) {
		return str.codePoints().boxed().collect(Collectors.toSet()).size();
	}

	public static void print(String str) {
		int[] codepoints = str.codePoints().toArray();
		for (int i = 0; i < codepoints.length; i++) {
			printCodepoint(codepoints[i]);
		}

	}
	public static void printCodepoint(int codepoint) {
		System.out.printf("0x%04X ", codepoint);
	}


	private static int countCharactersModernUnicode(String str) {
		Set<String> chars = new HashSet<String>();
		StringBuilder currentSymbol = new StringBuilder();
		int[] codepoints = str.codePoints().toArray();
		int i=1;
		currentSymbol.appendCodePoint(codepoints[0]);
		while( i<codepoints.length ) {

			/**
			 * assume prev char is already in the string
			 *
			 * if current char = zwj, then append and also append  next char
			 * if current char = variation selector, append
			 * if current char = normal, then return symbol and start new one with this
			 */

			int c = codepoints[i];

			if (c==ZWIDGE) { //zwj?
				currentSymbol.appendCodePoint(c);
				i++;
				c = codepoints[i];
				currentSymbol.appendCodePoint(c); // add the next codepoint as well
				i++;
				continue;
			} else if (UnicodeBlock.of(c).equals(UnicodeBlock.VARIATION_SELECTORS)) {
				currentSymbol.appendCodePoint(c);
				i++;
			} else {
				chars.add(currentSymbol.toString());
				currentSymbol = new StringBuilder();
				currentSymbol.appendCodePoint(c);
			}
			i++;
		}
		chars.add(currentSymbol.toString());
		return chars.size();
	}

}
