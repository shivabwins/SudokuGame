import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {

    private static final Random random = new Random();

    // Public entry point: builds a full, valid board using backtracking.
    public int[][] generateCompleteBoard() {
        int[][] board = GameUtils.generateEmptyBoard();
        fillBoard(board, 0, 0);
        return board;
    }

    // Recursive backtracking filler.
    // We move cell-by-cell left-to-right, top-to-bottom.
    // At each cell we try digits 1-9 IN RANDOM ORDER (so boards differ
    // each run), place the first one that's valid, then recurse to
    // the next cell. If no digit works, we return false so the
    // PREVIOUS call knows to undo its choice and try another number.
    private boolean fillBoard(int[][] board, int row, int col) {
        if (row == 9) {
            return true; // filled all 9 rows -> done
        }
        // work out the next cell to move to after this one
        int nextRow = (col == 8) ? row + 1 : row;
        int nextCol = (col == 8) ? 0 : col + 1;

        List<Integer> numbers = shuffledDigits();
        for (int value : numbers) {
            if (SudokuValidator.isValidMove(board, row, col, value)) {
                board[row][col] = value;
                if (fillBoard(board, nextRow, nextCol)) {
                    return true; // rest of the board filled successfully
                }
                board[row][col] = 0; // backtrack: undo and try next value
            }
        }
        return false; // no digit worked here -> tell caller to backtrack
    }

    // Helper: returns [1..9] shuffled into random order.
    private List<Integer> shuffledDigits() {
        List<Integer> digits = new ArrayList<>();
        for (int i = 1; i <= 9; i++) digits.add(i);
        Collections.shuffle(digits, random);
        return digits;
    }

    // Removes 'count' cells (sets them to 0) from a complete board
    // to create a puzzle. We pick random distinct positions so the
    // holes are spread unpredictably across the board.
    public int[][] removeCells(int[][] board, int count) {
        int[][] puzzle = GameUtils.copyBoard(board);
        int removed = 0;
        while (removed < count) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            if (puzzle[row][col] != 0) {
                puzzle[row][col] = 0;
                removed++;
            }
        }
        return puzzle;
    }

    // Ties it together: full board -> remove cells based on difficulty.
    // The number of cells to remove = 81 - (pre-filled cells wanted),
    // using the ranges from the brief (Easy 40-45 filled, etc.)
    public int[][] generatePuzzle(String difficulty) {
        int[][] complete = generateCompleteBoard();
        int prefilled;

        switch (difficulty.toLowerCase()) {
            case "easy":
                prefilled = 40 + random.nextInt(6); // 40-45
                break;
            case "medium":
                prefilled = 28 + random.nextInt(8); // 28-35
                break;
            case "hard":
                prefilled = 20 + random.nextInt(6); // 20-25
                break;
            default:
                prefilled = 35; // safe fallback
        }

        int cellsToRemove = 81 - prefilled;
        return removeCells(complete, cellsToRemove);
    }
}