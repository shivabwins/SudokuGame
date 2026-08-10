public class GameUtils {

    // Checks if a value is a legal Sudoku digit (1-9).
    // Kept separate from Validator's row/col/subgrid checks because
    // this is about the RAW input being sane, not whether it fits the board.
    public static boolean isValidInput(int value) {
        return value >= 1 && value <= 9;
    }

    // Returns a brand new 9x9 board filled with 0s (0 = empty cell).
    // Java int[][] arrays default to 0 automatically, so we just need
    // to allocate the array — no manual filling loop required.
    public static int[][] generateEmptyBoard() {
        return new int[9][9];
    }

    // Deep-copies a board. This matters a LOT: if you just do
    // int[][] copy = source, both variables point to the SAME array,
    // so editing one edits the other. We need independent arrays,
    // e.g. so "originalBoard" in SudokuBoard doesn't change when
    // the player edits "board".
    public static int[][] copyBoard(int[][] source) {
        int[][] copy = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                copy[row][col] = source[row][col];
            }
        }
        return copy;
    }

    // Board is "complete" when every cell is non-zero.
    // Note: this does NOT check correctness, just fullness —
    // pairing this with Validator elsewhere tells you if it's
    // both full AND legal.
    public static boolean isBoardComplete(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    return false; // found an empty cell -> not complete
                }
            }
        }
        return true;
    }
}