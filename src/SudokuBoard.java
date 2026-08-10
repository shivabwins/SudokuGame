public class SudokuBoard {

    // Instance variables
    private int[][] board;
    private int[][] originalBoard;

    // Constructor
    public SudokuBoard() {
        board = new int[9][9];
        originalBoard = new int[9][9];
    }

    // Getters
    public int[][] getBoard() {
        return board;
    }

    public int[][] getOriginalBoard() {
        return originalBoard;
    }

    // Setters
    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void setOriginalBoard(int[][] originalBoard) {
        this.originalBoard = originalBoard;
    }

    // Display the board
    public void displayBoard() {

        for (int row = 0; row < board.length; row++) {

            for (int col = 0; col < board[row].length; col++) {

                System.out.print(board[row][col] + " ");

            }

            System.out.println();
        }
    }
    //load board, this loads the board with the numbers generaratred before displaying and also saves a copy for the orginal board if reset is needed

    public void loadBoard(int [][] puzzle){
        for (int row = 0; row < board.length; row++) {

            for (int col = 0; col < board[row].length; col++) {

                board[row][col] = puzzle[row][col];
                originalBoard[row][col] = puzzle[row][col];

            }

        }

    }
    // This is for the setting of values when player plays, changes to specific number
    public void setValue(int row, int col, int value){
        board [row][col]= value;
    }
    // Resets the board back to the original puzzle state.
// We loop through every cell and copy the value from originalBoard
// back into board — this undoes any moves the player has made.
    public void resetBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = originalBoard[row][col];
            }
        }
    }

    // Checks whether a cell is allowed to be edited by the player.
// A cell was "pre-filled" by the puzzle if originalBoard has a
// non-zero value there — those cells must stay locked forever.
// If originalBoard is 0 at that position, it was empty to begin
// with, so the player is free to write into it.
    public boolean isCellEditable(int row, int col) {
        return originalBoard[row][col] == 0;
    }
}