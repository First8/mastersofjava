import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
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
        List<List<Integer>> reducedItems = region;
        for (int i = 1; i <= 8; i++) {
            List<List<Integer>> nakedSets = findNakedSets(reducedItems, i);
            reducedItems = reduceItems(reducedItems, nakedSets);
        }
        return reducedItems.equals(region) ? reducedItems : reduce(reducedItems);
    }

    /**
     * Find N cells that only have the same N candidates. These candidates can be removed from all other cells in that region.
     */
    private List<List<Integer>> findNakedSets(List<List<Integer>> region, int numberOfCandidates) {
        List<List<Integer>> candidates = region.stream()
                .filter(cell -> cell.size() <= numberOfCandidates)
                .toList();
        if (candidates.size() < numberOfCandidates) {
            return emptyList();
        } else {
            return createSubsetsOfSize(candidates, numberOfCandidates)
                    .map(candidate -> candidate.flatMap(List::stream).distinct().toList())
                    .filter(set -> set.size() == numberOfCandidates)
                    .toList();
        }
    }

    private Stream<Stream<List<Integer>>> createSubsetsOfSize(List<List<Integer>> candidates, int size) {
        if (size == 1) {
            return candidates.stream().map(Stream::of);
        }
        if (candidates.size() == size) {
            return Stream.of(candidates.stream());
        } else {
            Stream<Stream<List<Integer>>> candidatesWithFirstElement = createSubsetsOfSize(candidates.subList(1, candidates.size()), size - 1)
                    .map(previousCandidates -> concat(Stream.of(candidates.get(0)), previousCandidates));
            Stream<Stream<List<Integer>>> candidatesWithoutFirstElement = createSubsetsOfSize(candidates.subList(1, candidates.size()), size);
            return concat(candidatesWithFirstElement, candidatesWithoutFirstElement);
        }
    }

    private List<List<Integer>> reduceItems(List<List<Integer>> region, List<List<Integer>> itemsToReduce) {
        List<List<Integer>> reducedRegion = region;
        for (List<Integer> item : itemsToReduce) {
            reducedRegion = reduceItem(reducedRegion, item);
        }
        return reducedRegion;
    }

    private List<List<Integer>> reduceItem(List<List<Integer>> region, List<Integer> itemToReduce) {
        return region.stream()
                .map(cell -> reduceCell(cell, itemToReduce))
                .toList();
    }

    private List<Integer> reduceCell(List<Integer> cell, List<Integer> itemToReduce) {
        if (itemToReduce.containsAll(cell)) {
            return cell;
        } else {
            return cell.stream().filter(digit -> !itemToReduce.contains(digit)).toList();
        }
    }
}
