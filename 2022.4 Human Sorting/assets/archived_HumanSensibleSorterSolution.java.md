/*
 * The Alphanum Algorithm is an improved sorting algorithm for strings
 * containing numbers.  Instead of sorting numbers in ASCII order like
 * a standard sort, this algorithm sorts numbers in numeric order.
 *
 * The Alphanum Algorithm is discussed at http://www.DaveKoelle.com
 *
 * Released under the MIT License - https://opensource.org/licenses/MIT
 *
 * Copyright 2007-2017 David Koelle
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
 * USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is an updated version with enhancements made by Daniel Migowski, Andre
 * Bogus, and David Koelle. Updated by David Koelle in 2017.
 *
 * To use this class: Use the static "sort" method from the
 * java.util.Collections class: Collections.sort(your list, new
 * AlphanumComparator());
 */
public class HumanSensibleSorter implements Comparator<String> {
	// <SOLUTION>
	/**
	 * Length of string is passed in for improved efficiency (only need to calculate
	 * it once)
	 **/
	private final String getChunk(String s, int slength, int marker) {

		StringBuilder chunk = new StringBuilder();
		char c = s.charAt(marker);

		chunk.append(c);
		marker++;
		if (Character.isDigit(c)) {
			while (marker < slength) {
				c = s.charAt(marker);
				if (!Character.isDigit(c))
					break;
				chunk.append(c);
				marker++;
			}
		} else {
			while (marker < slength) {
				c = s.charAt(marker);
				if (Character.isDigit(c))
					break;
				chunk.append(c);
				marker++;
			}
		}
		return chunk.toString();
	}
	// </SOLUTION>

	public int compare(String s1, String s2) {
		int sortIndicator = 0;
		// <SOLUTION>
		if ((s1 == null) || (s2 == null)) {
			return sortIndicator;
		}

		int thisMarker = 0;
		int thatMarker = 0;
		int s1Length = s1.length();
		int s2Length = s2.length();

		while (thisMarker < s1Length && thatMarker < s2Length) {
			String thisChunk = getChunk(s1, s1Length, thisMarker);
			thisMarker += thisChunk.length();

			String thatChunk = getChunk(s2, s2Length, thatMarker);
			thatMarker += thatChunk.length();

			int result = 0;
			// If both chunks contain numeric characters, sort them numerically
			if (Character.isDigit(thisChunk.charAt(0)) && Character.isDigit(thatChunk.charAt(0))) {
				// Simple chunk comparison by length.
				BigDecimal thisN = new BigDecimal(thisChunk);
				BigDecimal thatN = new BigDecimal(thatChunk);
				result = thisN.compareTo(thatN);
			} else {
				// one of the chunks is considered a string, so do a string compare
				result = thisChunk.compareTo(thatChunk);
			}

			// if there is a difference, return it
			if (result != 0) {
				return result;
			}
			// continue with next chunk
		}
		sortIndicator = s1Length - s2Length;
		// <SOLUTION>
		return sortIndicator;
	}


	public static void main(String[] args) {
		List<String> values = Arrays.asList("puzzle2", "puzzle10", "puzzle1", "puzzle2.7", "puzzle2.10", "2", "10", "1", "Photo6", "Photo62", "Photo7");
		System.out.println(values.stream().sorted(new HumanSensibleSorter()).collect(Collectors.joining(" ")));
	}
}
