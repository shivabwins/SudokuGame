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
}