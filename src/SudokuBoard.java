public class SudokuBoard {
    public static final int SIZE = 9;
    private int [][] board;
    private int[][] originalBoard;

    // constructors
    // This sets the board to a 9*9 grid
    public SudokuBoard(){
        board = new int [SIZE][SIZE];
        originalBoard = new int [SIZE][SIZE];
    }
    // this receives whichever puzzle is loaded
    // it runs the loadboard method and gives it the puzzle
    public SudokuBoard(int [][] puzzle){
        loadBoard(puzzle);
    }

    // getters and setters
    public int [][] getBoard(){
        return board;
    }
    public int [][] getOriginalBoard(){
        return originalBoard;
    }

    public void setBoard(){
        this.board = board;
    }
    public void setOrigalBoard(){
        this.originalBoard = board;
    }

    // methods required
    // it doesnt return a value as it only changes the exisited and orginalboard therefore void
    // it takes parameters becuase it needs information from you
    // return, stops and exits out of the loadboard method (EXIT)
    public void loadBoard(int [][] newBoard){
        if (newBoard == null || newBoard.length != SIZE || newBoard[0].length != SIZE){
            System.out.println("INVALID BOARD. Could not load puzzle");
            return;
        }
        // loads a new 9*9 board
        board = new int [SIZE][SIZE];
        originalBoard = new int[SIZE][SIZE];
        for(int row = 0; row < SIZE; row++){
            for(int col =0; col <SIZE; col++){
                board[row][col]=newBoard[row][col];
                originalBoard[row][col]=newBoard[row][col];
            }
        }
    }
    // this prints the string and divider lines every 3rd row/column
    // +1 is because the array starts from 0
    public void displayBoard(){
        System.out.println("+-------+-------+-------+");
        for(int row =0; row < SIZE; row++){
            StringBuilder line = new StringBuilder("|");
            for(int col =0; col <SIZE ; col++){
                line.append(" ").append(board[row][col]);
                if((col + 1)%3==0){
                    line.append(" |");
                }
            }
            System.out.println(line);
            if((row + 1)%3==0){
                System.out.println("+-------+-------+-------+");
            }
        }
    }

    // updates cell or prints error so its void
    // takes parameters as it requires row col and value from user
    public void setValue(int row, int col, int value) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            System.out.println("Invalid position. Row and column must be between 1 and 9.");
            return;
        }
        if (!isCellEditable(row, col)) {
            System.out.println("This cell is pre-filled and cannot be modified.");
            return;
        }
        board[row][col] = value;
    }

    // return true or false instead of void
    // takes parameters as it requires user input
    public boolean isCellEditable(int row, int col){
        if(row < 0 || row >= SIZE|| col < 0 || col >= SIZE){
            return false;
        }
        if(originalBoard[row][col] == 0) {
            return true;
        } else {
            return false;
        }
    }

    // copies original board back over board
    public void resetBoard(){
        for(int row = 0; row < SIZE; row++){
            for(int col =0; col <SIZE; col++){
                board[row][col]=originalBoard[row][col];
            }
        }
        System.out.println("Board has been reset.");
    }




}