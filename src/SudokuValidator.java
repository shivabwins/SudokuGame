public class SudokuValidator {

    // Does 'value' already exist somewhere else in this row?
    public static boolean checkRow(int[][] board, int row, int value) {
        for (int col = 0; col < 9; col++) {
            if (board[row][col] == value) {
                return true; // duplicate found
            }
        }
        return false;
    }

    // Does 'value' already exist somewhere else in this column?
    public static boolean checkColumn(int[][] board, int col, int value) {
        for (int row = 0; row < 9; row++) {
            if (board[row][col] == value) {
                return true;
            }
        }
        return false;
    }

    // Does 'value' already exist in this cell's 3x3 subgrid?
    // The trick: integer division rounds down, so (row / 3) * 3 gives
    // the TOP-LEFT row of the subgrid this cell belongs to.
    // e.g. row=4 -> 4/3=1 -> 1*3=3 (rows 3,4,5 = the middle band).
    public static boolean checkSubGrid(int[][] board, int row, int col, int value) {
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (board[r][c] == value) {
                    return true;
                }
            }
        }
        return false;
    }

    // Combines all three rules. A move is valid only if NONE of the
    // three checks find a duplicate.
    public static boolean isValidMove(int[][] board, int row, int col, int value) {
        if (checkRow(board, row, value)) return false;
        if (checkColumn(board, col, value)) return false;
        if (checkSubGrid(board, row, col, value)) return false;
        return true;
    }
}