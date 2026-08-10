import java.util.Scanner;

public class SudokuGame {

    private Scanner input;
    private SudokuBoard board;
    private SudokuGenerator generator;
    private SudokuValidator validator;
    private GameUtils utils;

    // Stores the previous puzzle
    private int[][] lastPuzzle;

    // Constructor
    public SudokuGame() {
        input = new Scanner(System.in);
        board = new SudokuBoard();
        generator = new SudokuGenerator();
        validator = new SudokuValidator();
        utils = new GameUtils();
        lastPuzzle = null;
    }

    // Starts the program
    public static void main(String[] args) {
        SudokuGame game = new SudokuGame();
        game.startGame();
    }

    // Controls the main game loop
    public void startGame() {

        boolean running = true;

        while (running) {

            int mode = selectMode();

            if (mode == 1) {
                loadPredefinedPuzzle();
                playGame();

            } else if (mode == 2) {
                loadRandomPuzzle();
                playGame();

            } else if (mode == 3) {
                replayPuzzle();

            } else if (mode == 4) {
                System.out.println("Thank you for playing Sudoku!");
                running = false;
            }
        }
    }

    // Shows the main menu
    public int selectMode() {

        while (true) {

            System.out.println("\n==============================");
            System.out.println("        SUDOKU GAME");
            System.out.println("==============================");
            System.out.println("1. Predefined Puzzle");
            System.out.println("2. Random Generated Puzzle");
            System.out.println("3. Replay Previous Puzzle");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            try {

                int choice = Integer.parseInt(input.nextLine());

                if (choice >= 1 && choice <= 4) {
                    return choice;
                }

                System.out.println("Please enter a number from 1 to 4.");

            } catch (NumberFormatException e) {

                // Handles invalid input
                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }

    // Selects the difficulty
    private String selectDifficulty() {

        while (true) {

            System.out.println("\n--- Select Difficulty ---");
            System.out.println("1. Easy");
            System.out.println("2. Medium");
            System.out.println("3. Hard");
            System.out.print("Enter choice: ");

            try {

                int choice = Integer.parseInt(input.nextLine());

                if (choice == 1) {
                    return "Easy";

                } else if (choice == 2) {
                    return "Medium";

                } else if (choice == 3) {
                    return "Hard";
                }

                System.out.println("Please enter 1, 2, or 3.");

            } catch (NumberFormatException e) {

                // Handles invalid input
                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }

    // Loads a predefined puzzle
    private void loadPredefinedPuzzle() {

        String difficulty = selectDifficulty();

        int[][] puzzle;

        if (difficulty.equals("Easy")) {

            // Easy puzzle
            puzzle = new int[][] {
                    {5, 3, 4, 6, 7, 0, 0, 0, 0},
                    {6, 7, 0, 1, 9, 5, 0, 0, 0},
                    {0, 9, 8, 3, 4, 0, 5, 6, 0},
                    {8, 5, 9, 7, 6, 0, 0, 0, 3},
                    {4, 2, 6, 8, 5, 3, 0, 0, 1},
                    {7, 1, 3, 9, 2, 0, 8, 5, 6},
                    {0, 6, 0, 5, 3, 7, 2, 8, 4},
                    {0, 8, 7, 4, 1, 9, 6, 3, 5},
                    {3, 4, 5, 0, 8, 6, 1, 7, 9}
            };

        } else if (difficulty.equals("Medium")) {

            // Medium puzzle
            puzzle = new int[][] {
                    {5, 3, 0, 0, 7, 0, 0, 0, 0},
                    {6, 0, 0, 1, 9, 5, 0, 0, 0},
                    {0, 9, 8, 0, 0, 0, 0, 6, 0},
                    {8, 0, 0, 0, 6, 0, 0, 0, 3},
                    {4, 0, 0, 8, 0, 3, 0, 0, 1},
                    {7, 0, 0, 0, 2, 0, 0, 0, 6},
                    {0, 6, 0, 0, 0, 0, 2, 8, 0},
                    {0, 0, 0, 4, 1, 9, 0, 0, 5},
                    {0, 0, 0, 0, 8, 0, 0, 7, 9}
            };

        } else {

            // Hard puzzle
            puzzle = new int[][] {
                    {5, 3, 0, 0, 7, 0, 0, 0, 0},
                    {6, 0, 0, 1, 0, 0, 0, 0, 0},
                    {0, 9, 8, 0, 0, 0, 0, 0, 0},
                    {8, 0, 0, 0, 6, 0, 0, 0, 3},
                    {0, 0, 0, 8, 0, 3, 0, 0, 1},
                    {7, 0, 0, 0, 2, 0, 0, 0, 0},
                    {0, 6, 0, 0, 0, 0, 2, 0, 0},
                    {0, 0, 0, 4, 1, 0, 0, 0, 5},
                    {0, 0, 0, 0, 8, 0, 0, 7, 0}
            };
        }

        board.loadBoard(puzzle);

        // Saves the puzzle for replay
        lastPuzzle = utils.copyBoard(puzzle);

        System.out.println(
                difficulty + " puzzle loaded successfully."
        );
    }

    // Generates a random puzzle
    private void loadRandomPuzzle() {

        String difficulty = selectDifficulty();

        int[][] puzzle = generator.generatePuzzle(difficulty);

        board.loadBoard(puzzle);

        // Saves the puzzle for replay
        lastPuzzle = utils.copyBoard(puzzle);

        System.out.println(
                difficulty + " random puzzle generated."
        );
    }

    // Loads the previous puzzle
    private void replayPuzzle() {

        if (lastPuzzle == null) {

            System.out.println(
                    "No previous puzzle available."
            );

            return;
        }

        board.loadBoard(utils.copyBoard(lastPuzzle));

        System.out.println(
                "Previous puzzle restarted."
        );

        playGame();
    }

    // Controls the game menu
    private void playGame() {

        boolean playing = true;

        while (playing) {

            board.displayBoard();

            System.out.println("\n--- Game Menu ---");
            System.out.println("1. Enter Number");
            System.out.println("2. Reset Puzzle");
            System.out.println("3. Check Board");
            System.out.println("4. Back to Main Menu");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            try {

                int choice = Integer.parseInt(input.nextLine());

                if (choice == 1) {

                    enterNumber();

                } else if (choice == 2) {

                    board.resetBoard();
                    System.out.println("Puzzle reset.");

                } else if (choice == 3) {

                    checkBoard();

                } else if (choice == 4) {

                    playing = false;

                } else if (choice == 5) {

                    System.out.println("Goodbye!");
                    System.exit(0);

                } else {

                    System.out.println(
                            "Please enter a number from 1 to 5."
                    );
                }

            } catch (NumberFormatException e) {

                // Handles invalid input
                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }

    // Lets the player enter a number
    private void enterNumber() {

        try {

            System.out.print("Enter row (1-9): ");
            int row = Integer.parseInt(input.nextLine()) - 1;

            System.out.print("Enter column (1-9): ");
            int col = Integer.parseInt(input.nextLine()) - 1;

            System.out.print("Enter value (1-9): ");
            int value = Integer.parseInt(input.nextLine());

            // Checks row and column
            if (row < 0 || row > 8 ||
                    col < 0 || col > 8) {

                System.out.println(
                        "Invalid row or column."
                );

                return;
            }

            // Checks the value
            if (!utils.isValidInput(value)) {

                System.out.println(
                        "Value must be between 1 and 9."
                );

                return;
            }

            // Checks if the cell can be changed
            if (!board.isCellEditable(row, col)) {

                System.out.println(
                        "This cell cannot be changed."
                );

                return;
            }

            // Checks the Sudoku rules
            if (validator.isValidMove(
                    board.getBoard(),
                    row,
                    col,
                    value)) {

                board.setValue(row, col, value);

                System.out.println(
                        "Move accepted."
                );

            } else {

                System.out.println(
                        "Invalid move."
                );
            }

        } catch (NumberFormatException e) {

            // Handles invalid input
            System.out.println(
                    "Please enter numbers only."
            );
        }
    }

    // Checks if the board is complete
    private void checkBoard() {

        if (utils.isBoardComplete(board.getBoard())) {

            System.out.println(
                    "Congratulations! You completed the Sudoku!"
            );

        } else {

            System.out.println(
                    "The board is not complete yet."
            );
        }
    }
}
