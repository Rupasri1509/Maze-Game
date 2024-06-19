import java.util.Random;
import java.util.Scanner;
public class MazeGame {
    private static final char WALL = '#';
    private static final char PATH = ' ';
    private static final char PLAYER = '@';
    private static final char EXIT = 'E';
    private static char[][] maze;
    private static int rows;
    private static int cols;
    private static int playerRow;
    private static int playerCol;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of rows: ");
        rows = scanner.nextInt();
        System.out.print("Enter the number of columns: ");
        cols = scanner.nextInt();
        scanner.nextLine(); // consume newline
        maze = generateMaze(rows, cols);
        playerRow = 1;
        playerCol = 1;
        maze[playerRow][playerCol] = PLAYER;
        while (true) {
            printMazeWithLines();
            System.out.print("Enter your move (W/A/S/D): ");
            char move = scanner.nextLine().toUpperCase().charAt(0);
            int newPlayerRow = playerRow;
            int newPlayerCol = playerCol;
            switch (move) {
                case 'W':
                    newPlayerRow--;
                    break;
                case 'S':
                    newPlayerRow++;
                    break;
                case 'A':
                    newPlayerCol--;
                    break;
                case 'D':
                    newPlayerCol++;
                    break;
                default:
                    System.out.println("Invalid move! Use W/A/S/D.");
                    continue;
            }
            if (isValidMove(newPlayerRow, newPlayerCol)) {
                if (maze[newPlayerRow][newPlayerCol] == EXIT) {
                    System.out.println("Congratulations! You found the exit!");
                    break;
                }
                maze[playerRow][playerCol] = PATH;
                playerRow = newPlayerRow;
                playerCol = newPlayerCol;
                maze[playerRow][playerCol] = PLAYER;
            } else {
                System.out.println("Invalid move! You can't go there.");
            }
        }
        scanner.close();   }
       private static char[][] generateMaze(int rows, int cols) {
        char[][] maze = new char[rows][cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = random.nextDouble() < 0.3 ? WALL : PATH;
            }
        }
        maze[rows - 2][cols - 2] = EXIT; // Place exit at bottom-right corner
        return maze;
    }
    private static void printMazeWithLines() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("+---");
            }
            System.out.println("+");

            for (int j = 0; j < cols; j++) {
                System.out.print("| " + maze[i][j] + " ");
            }
            System.out.println("|");
        }
        for (int j = 0; j < cols; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }
    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols && maze[row][col] != WALL;
    }}
