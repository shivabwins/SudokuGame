
import java.util.Scanner;
public class SudokuGame {

    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("================================");
        System.out.println("Welcome to Sudoku Game");
        System.out.println("================================");

        //Output options
        System.out.println("1. Predefined Puzzle Mode \n" +
                "2. Random Generated Mode \n" +
                "3. Replay Previous Puzzle \n" +
                "4. Exit \n" +
                "Select an option: ");

        //Use string to collect options
        String option = input.nextLine();
        switch (option.trim().toLowerCase()) {
        }
    }
}
