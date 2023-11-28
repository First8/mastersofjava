import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;
import static java.util.stream.Stream.concat;

/**
 * This class helps to solve a sudoku by reducing the possibilities in each region.
 */
public class RegionReducer {

    /**
     * @param region A region is one row, column or block in a sudoku
     * @return A region with reduced possibilities
     */
    public List<List<Integer>> reduce(List<List<Integer>> region) {
        List<Integer> hiddenSingles = findHiddenSingles(region);
        List<List<Integer>> cleanedItems = region.stream().map(cell -> keepSingleOccurrences(cell, hiddenSingles)).toList();
        for (int i = 2; i <= 8; i++) {
            List<List<Integer>> hiddenSets = findHiddenSets(cleanedItems,i);
            cleanedItems = cleanSets(cleanedItems, hiddenSets);
        }
        return cleanedItems.equals(region) ? cleanedItems : reduce(cleanedItems);
    }

    /**
     * Find a cell that has a candidates not occurring in any other cells. This cell can be cleaned from all other candidates.
     */
    private List<Integer> findHiddenSingles(List<List<Integer>> region) {
        return region.stream()
                .flatMap(cell -> cell.stream())
                .collect(groupingBy(value -> value, counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .toList();
    }

    /**
     * Find N cells that have N candidates not occurring in any other cells. These cells can be cleaned from all other candidates.
     */
    private List<List<Integer>> findHiddenSets(List<List<Integer>> region, int numberOfCandidates) {
        List<Integer> occursMaxNTimes = region.stream()
                .flatMap(cell -> cell.stream())
                .collect(groupingBy(value -> value, counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() <= numberOfCandidates)
                .map(Map.Entry::getKey)
                .toList();
        if (occursMaxNTimes.size() < numberOfCandidates) {
            return Collections.emptyList();
        }
        Map<Integer, List<Integer>> indexMap = IntStream.rangeClosed(0, 8).boxed().collect(toMap(identity(), index -> region.get(index)));
        Map<Integer, List<Integer>> valueIndexMap = occursMaxNTimes.stream()
                .collect(toMap(identity(), value ->
                        indexMap.entrySet().stream()
                                .filter(entry -> entry.getValue().contains(value))
                                .map(Map.Entry::getKey)
                                .toList()));

        return createCandidates(occursMaxNTimes, numberOfCandidates)
                .filter(candidate -> isHiddenSet(candidate, valueIndexMap, numberOfCandidates))
                .toList();
    }

    private Stream<List<Integer>> createCandidates(List<Integer> values, int numberOfCandidates) {
        if (numberOfCandidates == 1) {
            return values.stream().map(value -> List.of(value));
        } else if (values.size() == numberOfCandidates) {
            return Stream.of(values);
        } else {
            Stream<List<Integer>> candidatesWithFirstElement = createCandidates(values.subList(1, values.size()), numberOfCandidates - 1)
                    .map(previousCandidates -> concat(Stream.of(values.get(0)), previousCandidates.stream()).toList());
            Stream<List<Integer>> candidatesWithoutFirstElement = createCandidates(values.subList(1, values.size()),numberOfCandidates);
            return concat(candidatesWithFirstElement, candidatesWithoutFirstElement);
        }
    }

    private boolean isHiddenSet(List<Integer> nCandidates, Map<Integer, List<Integer>> valueIndexMap, int numberOfCandidates) {
        return nCandidates.stream()
                .flatMap(value -> valueIndexMap.get(value).stream())
                .distinct()
                .count() == numberOfCandidates;
    }

    private List<Integer> keepSingleOccurrences(List<Integer> cell, List<Integer> singleOccurrences) {
        for (Integer singleOccurrence : singleOccurrences) {
            if (cell.contains(singleOccurrence)) {
                return asList(singleOccurrence);
            }
        }
        return cell;
    }

    private List<List<Integer>> cleanSets(List<List<Integer>> region, List<List<Integer>> sets){
        List<List<Integer>> result = region;
        for (List<Integer> set : sets){
            result = cleanSet(result, set);
        }
        return result;
    }

    private List<List<Integer>> cleanSet(List<List<Integer>> region, List<Integer> set){
        return region.stream().map(cell -> cleanCell(cell, set)).toList();
    }

    private List<Integer> cleanCell(List<Integer> cell, List<Integer> set) {
        if (cell.stream().anyMatch(set::contains)){
            return cell.stream().filter(set::contains).toList();
        } else {
            return cell;
        }
    }
}
