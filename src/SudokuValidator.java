public class SudokuValidator {
    private static final int SIZE =9;
    private static final int SUBGRID =3;

    //constructor
    // it is empty because it simply performs checks
    /* sudoku rules that a move can break 1) duplicate in row
    2)duplicate in column
    3)duplicate in 3*3 box
     */
    public SudokuValidator(){
    }

    // boolean because it returns true/false
    // Checks for duplicate within the row straight
    public boolean checkRow(int[][] board, int row, int value){
        // loop through each column
        for(int col=0; col<SIZE; col++){
            if(board[row][col]==value){
                return false;
            }
        }
        return true;
    }

    //checks every column down
    public boolean checkColumn(int[][] board, int col, int value){
        for (int row =0; row < SIZE; row++){
            if(board[row][col] == value){
                return false;
            }
        }
        return true;
    }

    //checks specific row and col
    public boolean checkSubGrid(int[][] board, int row, int col, int value) {
        int startRow = (row / SUBGRID) * SUBGRID;
        int startCol = (col / SUBGRID) * SUBGRID;

        for (int r = startRow; r < startRow + SUBGRID; r++) {
            for (int c = startCol; c < startCol + SUBGRID; c++) {
                if (board[r][c] == value) {
                    return false;
                }
            }
        }
        return true;
    }

    //method everyone calls
    //important
    public boolean isValidMove(int[][] board, int row, int col, int value){
        //checks if board is passed
        if(board==null){
            return false;
        }
        //bounds check
        if (row <0 || row >= SIZE || col <0|| col >=SIZE){
            return false;
        }
        //outside range of 1 to 9
        if(value < 1 || value > 9){
            return false;
        }

        // checks if all 3 checks return true so all checks must pass
        return checkRow(board, row, value)
                && checkColumn(board, col, value)
                && checkSubGrid(board, row, col, value);
    }

}