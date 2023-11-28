import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * This class represents the current state of a sudoku
 */
public class Sudoku {
    private List<List<List<Integer>>> currentState;

    /**
     * Creates a Sudoku, with all possible values (1 to 9) for all empty cells.
     * @param initial The initial values of a Sudoku, empty cells are null.
     */
    public Sudoku(Integer[][] initial) {
        this.currentState = Stream.of(initial)
                .map(row -> Stream.of(row)
                        .map(value -> value == null ? asList(1, 2, 3, 4, 5, 6, 7, 8, 9) : asList(value))
                        .collect(toList()))
                .collect(toList());
    }

    /**
     * Gets one row of the sudoku
     *
     * @param rowNumber number from 1 to 9
     * @return the row with the current possibilities
     */
    public List<List<Integer>> getRow(Integer rowNumber) {
        return currentState.get(rowNumber - 1);
    }

    /**
     * Replaces one row of the sudoku
     *
     * @param rowNumber rowNumber number from 1 to 9
     * @param newRow    The new contents of that row
     */
    public void setRow(Integer rowNumber, List<List<Integer>> newRow) {
        currentState.set(rowNumber - 1, new ArrayList<>(newRow)); // creating a new list just in case newRow was unmodifiable
    }

    /**
     * Gets one column of the sudoku
     *
     * @param columnNumber number from 1 to 9
     * @return the column with the current possibilities
     */
    public List<List<Integer>> getColumn(Integer columnNumber) {
        return currentState.stream().map(row -> row.get(columnNumber - 1)).toList();
    }

    /**
     * Replaces one column of the sudoku
     *
     * @param columnNumber number from 1 to 9
     * @param newColumn    The new contents of that column
     */
    public void setColumn(Integer columnNumber, List<List<Integer>> newColumn) {
        IntStream.rangeClosed(1, 9)
                .forEach(rowNumber -> getRow(rowNumber).set(columnNumber - 1, newColumn.get(rowNumber - 1)));
    }

    /**
     * Gets one block of the sudoku
     * Blocks are numbered:
     * <pre>
     *  1 | 2 | 3
     * -----------
     *  4 | 5 | 6
     * -----------
     *  7 | 8 | 9
     * </pre>
     *
     * @param blockNumber number from 1 to 9
     * @return the block with the current possibilities
     */
    public List<List<Integer>> getBlock(Integer blockNumber) {
        return IntStream.rangeClosed(1, 3)
                .mapToObj(blockRow -> IntStream.rangeClosed(1, 3)
                        .mapToObj(blockColumn -> getCel((blockNumber - 1) / 3 * 3 + blockRow, (blockNumber - 1) % 3 * 3 + blockColumn)))
                .flatMap(Function.identity())
                .toList();
    }

    /**
     * Replaces one block of the sudoku
     *
     * @param blockNumber number from 1 to 9
     * @param newBlock    The new contents of that block
     */
    public void setBlock(Integer blockNumber, List<List<Integer>> newBlock) {
        IntStream.rangeClosed(1, 3)
                .forEach(blockRow -> IntStream.rangeClosed(1, 3)
                        .forEach(blockColumn -> getRow((blockNumber - 1) / 3 * 3 + blockRow)
                                .set((blockNumber - 1) % 3 * 3 + blockColumn - 1, newBlock.get((blockRow - 1) * 3 + blockColumn - 1))));
    }

    private List<Integer> getCel(Integer rowNumber, Integer columnNumber) {
        return getRow(rowNumber).get(columnNumber - 1);
    }

    public boolean isSolved() {
        return currentState.stream().flatMap(rows -> rows.stream()).allMatch(cel -> cel.size() == 1)
                && IntStream.rangeClosed(1, 9).mapToObj(this::getRow).allMatch(this::isSolved)
                && IntStream.rangeClosed(1, 9).mapToObj(this::getColumn).allMatch(this::isSolved)
                && IntStream.rangeClosed(1, 9).mapToObj(this::getBlock).allMatch(this::isSolved);
    }

    private boolean isSolved(List<List<Integer>> region) {
        return region.stream().flatMap(cel -> cel.stream()).sorted().toList().equals(asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    }

    @Override
    public String toString() {
        return "Sudoku currentState:\n"
                + "╔═════════╤═════════╤═════════╦═════════╤═════════╤═════════╦═════════╤═════════╤═════════╗\n"
                + IntStream.rangeClosed(1, 3)
                .mapToObj(this::toString)
                .collect(joining("╟─────────┼─────────┼─────────╫─────────┼─────────┼─────────╫─────────┼─────────┼─────────╢\n"))
                + "╠═════════╪═════════╪═════════╬═════════╪═════════╪═════════╬═════════╪═════════╪═════════╣\n"
                + IntStream.rangeClosed(4, 6)
                .mapToObj(this::toString)
                .collect(joining("╟─────────┼─────────┼─────────╫─────────┼─────────┼─────────╫─────────┼─────────┼─────────╢\n"))
                + "╠═════════╪═════════╪═════════╬═════════╪═════════╪═════════╬═════════╪═════════╪═════════╣\n"
                + IntStream.rangeClosed(7, 9)
                .mapToObj(this::toString)
                .collect(joining("╟─────────┼─────────┼─────────╫─────────┼─────────┼─────────╫─────────┼─────────┼─────────╢\n"))
                + "╚═════════╧═════════╧═════════╩═════════╧═════════╧═════════╩═════════╧═════════╧═════════╝\n";

    }

    private String toString(Integer row) {
        return toString(row, 0)
                + toString(row, 1)
                + toString(row, 2);
    }

    private String toString(Integer row, Integer subRow) {
        return "║"
                + IntStream.rangeClosed(1, 3)
                .mapToObj(column -> toString(getCel(row, column), subRow))
                .collect(joining("│"))
                + "║"
                + IntStream.rangeClosed(4, 6)
                .mapToObj(column -> toString(getCel(row, column), subRow))
                .collect(joining("│"))
                + "║"
                + IntStream.rangeClosed(7, 9)
                .mapToObj(column -> toString(getCel(row, column), subRow))
                .collect(joining("│"))
                + "║\n";
    }

    private String toString(List<Integer> cel, Integer subRow) {
        return digitToString(cel, subRow * 3 + 1) + digitToString(cel, subRow * 3 + 2) + digitToString(cel, subRow * 3 + 3);
    }

    private String digitToString(List<Integer> cel, Integer value) {
        return cel.contains(value) ? format(" %1$d ", value) : "   ";
    }
}
