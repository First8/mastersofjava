import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * This class helps to solve a sudoku by reducing the possibilities in each region.
 */
public class RegionReducer {

    /**
     * @param region A region is one row, column or block in a sudoku
     * @return A region with reduced possibilities
     */
    public List<List<Integer>> reduce(List<List<Integer>> region) {
        List<List<Integer>> singleOccurrences = reduceSingleOccurrences(region);
        List<List<Integer>> singlesReduced = reduceSingles(singleOccurrences);
        List<List<Integer>> pairsReduced = reducePairs(singlesReduced);
        List<List<Integer>> triplesReduced = reduceTriples(pairsReduced);
        List<List<Integer>> quadruplesReduced = reduceQuadruples(triplesReduced);
        return quadruplesReduced.equals(singleOccurrences) ? quadruplesReduced : reduce(quadruplesReduced);
    }

    private List<List<Integer>> reduceSingleOccurrences(List<List<Integer>> region) {
        List<Integer> singleOccurrences = region.stream()
                .flatMap(cell -> cell.stream())
                .collect(groupingBy(value -> value, counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .toList();
        return region.stream().map(cell -> keepSingleOccurrences(cell, singleOccurrences)).toList();
    }

    private List<Integer> keepSingleOccurrences(List<Integer> cell, List<Integer> singleOccurrences) {
        for (Integer singleOccurrence : singleOccurrences) {
            if (cell.contains(singleOccurrence)) {
                return asList(singleOccurrence);
            }
        }
        return cell;
    }

    private List<List<Integer>> reduceSingles(List<List<Integer>> region) {
        List<List<Integer>> singles = region.stream().filter(cell -> cell.size() == 1).toList();
        return reduceItems(region, singles);
    }

    private List<List<Integer>> reducePairs(List<List<Integer>> region) {
        List<List<Integer>> pairs = findPairs(region);
        return reduceItems(region, pairs);
    }

    private List<List<Integer>> reduceTriples(List<List<Integer>> region) {
        List<List<Integer>> triples = findTriples(region);
        return reduceItems(region, triples);
    }

    private List<List<Integer>> reduceQuadruples(List<List<Integer>> region) {
        List<List<Integer>> triples = findQuadruples(region);
        return reduceItems(region, triples);
    }

    private List<List<Integer>> findPairs(List<List<Integer>> region) {
        Predicate<List<Integer>> occursTwice = pair -> region.stream().filter(cell -> cell.equals(pair)).count() == 2;
        return region.stream()
                .filter(cell -> cell.size() == 2)
                .filter(occursTwice)
                .distinct().toList();
    }

    private List<List<Integer>> findTriples(List<List<Integer>> region) {
        Predicate<List<Integer>> occursThreeTimes = triple -> region.stream().filter(cell -> cell.equals(triple)).count() == 3;
        return region.stream()
                .filter(cell -> cell.size() == 3)
                .filter(occursThreeTimes)
                .distinct().toList();
    }

    private List<List<Integer>> findQuadruples(List<List<Integer>> region) {
        Predicate<List<Integer>> occursFourTimes = quadruple -> region.stream().filter(cell -> cell.equals(quadruple)).count() == 4;
        return region.stream()
                .filter(cell -> cell.size() == 4)
                .filter(occursFourTimes)
                .distinct().toList();
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
        if (cell.equals(itemToReduce)) {
            return cell;
        } else {
            return cell.stream().filter(digit -> !itemToReduce.contains(digit)).toList();
        }
    }
}
