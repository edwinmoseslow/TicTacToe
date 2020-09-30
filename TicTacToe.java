import java.util.Scanner;
import java.util.*;

public class TicTacToe{

    /* 
    ------------------------------------------------------------------
    Requirements
    ------------------------------------------------------------------
    1.  Ask for names of players 1 and 2.
    2.  Alternate between input for player 1 and player 2.
    3.  Stop when one of the players has won.
    ------------------------------------------------------------------
    */
    
    // 
    private static final String HOR_LINES   = "-----------";
    private static final String VER_LINES   = "  |  |  ";
    private static final String DIVIDER     = "----------------------------------------------------";

    // Game State
    private static int gameState              = 0;
    private static final int GAME_DEFAULT     = 0;
    private static final int GAME_ETR_NAME    = 2;
    private static final int GAME_ETR_N_BOARD = 3;
    private static final int GAME_MENU        = 1;
    private static final int GAME_START       = 4;
    private static final int GAME_END         = 5;

    // Player Turn
    private static int turn                 = 0;
    private static final int PLAYER_ONE     = 1;
    private static final int PLAYER_TWO     = 2;

    // Player Name
    private static String plyOneName        = "";
    private static String plyTwoName        = "";


    private static int gameMode             = 0;
    private static final int BASIC_MODE     = 1;
    private static final int INTERME_MODE   = 2;

    // Game Exit Check
    private static boolean exit             = false;

    // Number of Rounds Check
    private static int round                = 1;

    // Scanner
    private static Scanner scanner;

    // Game Board
    private static Map<Integer, String> board;

    private static int dimension            = 0;

    // Markers
    private static ArrayList<Integer> plyOneMarkers;
    private static ArrayList<Integer> plyTwoMarkers;

    public static void main(String []args){
        
        // init a new instance
        board = new HashMap<Integer, String>();
        scanner = new Scanner(System.in);
        plyOneMarkers = new ArrayList<Integer>();
        plyTwoMarkers = new ArrayList<Integer>();
        
        printIntro();

        gameState = GAME_DEFAULT;

        while(!exit){
            printBoard();

            userInput(scanner.nextLine());
        }
    }
     
    private static void printBoard(){
        int count = 0;

        // breakline
        System.out.println();

        for(Map.Entry m : board.entrySet()){
            System.out.print(" " + m.getValue() + " ");
            count++;
            if(count == 3 || count == 6) {
                // breakline
                System.out.println("\n" + HOR_LINES);
            } else {
                if(count != board.size()){
                    System.out.print("|");
                }
            }
        }

        // breakline
        System.out.println("\n");

        System.out.println("To Exit Game, Press 'n' and follow up by Enter.");
        if(gameState == GAME_END){
            System.out.println("To Play Another Round, Press 'r' follow up by Enter.");
        }
        else {
            System.out.println("To Continue Game, Press '1' to '" + board.size() + "' and follow up by Enter.");
        }
    }
    
    private static void printIntro(){
        // Game title
        System.out.println("Tic Tac Toe");
        
        // DIVIDER
        System.out.println(DIVIDER);
        
        // Ask for game mode
        System.out.println("Enter '1' for Basic Mode(Board Size : 3x3)");
        System.out.println("Enter '2' for Intermediate Mode(Board Size : NxN)");
        System.out.println("Enter 'n' to Exit");
        // DIVIDER
        System.out.println(DIVIDER);
    }
    
    private static void userInput(String input){
        switch (input){
            case "n":
                exit = true;
                return;
            case "r":
                if(gameState == GAME_END) {
                    resetGame();
                }
                return;
        }

        switch (gameState) {
            case GAME_DEFAULT:
                System.out.println("Enter name for Player 1 :");
                break;
            case GAME_MENU:
                menuSelection(input);
                break;
            case GAME_ETR_NAME:
                enterName(input);
                break;
            case GAME_ETR_N_BOARD:
                break;
            case GAME_START:
                updateBoard(input);
                break;
        } 
    }

    private static void enterName(String input){
        if(plyOneName == ""){
            System.out.println("Enter name for Player 1 :");
            return;
        } else {
            plyOneName = input;
        }

        if(plyTwoName == ""){
            System.out.println("Enter name for Player 2 :");
            return;
        } else {
            plyTwoName = input;
            gameState = GAME_MENU;
        }
    }

    private static void menuSelection(String input){
        try {
            gameMode = Integer.valueOf(input);
            switch (gameMode){
                case BASIC_MODE:
                    // Basic Mode
                    drawBoard(dimension = 3);
                    break;
                case INTERME_MODE:
                    // Intermediate Mode
                    System.out.println("Enter the size of your board :");
                    userInput(scanner.nextLine());
                    return;
                default:
                    System.out.println(DIVIDER);
                    System.out.println("Invalid input! \n\nPlease input either '1' to '2' or \n'n' to exit game.");
                    System.out.println(DIVIDER);
                    gameMode = 0;
                    return;
            }
        } catch(NumberFormatException e) {
            System.out.println(DIVIDER);
            System.out.println("Invalid input! \n\nPlease input either '1' to '2' or \n'n' to exit game.");
            System.out.println(DIVIDER);
            return;
        }

        gameStart();
    }

    private static void gameStart(){
        gameState = GAME_START;
        System.out.println(DIVIDER);
        System.out.println("Game Start");
        System.out.println(DIVIDER);

        if(turn == 0){
            Random r = new Random();
            turn = ((r.nextInt(10)%2 == 0) ? PLAYER_TWO : PLAYER_ONE);  
            System.out.println("Player " + turn + " will start first!");  
        }
    }

    private static void gameOver(boolean winnerExist){
        System.out.println(DIVIDER);
        System.out.println("Game Over");
        System.out.println(DIVIDER);

        if(winnerExist){
            System.out.println("Player " + turn + " : Won!");
        } else {
            System.out.println("It's a Draw!");
        }
    }

    private static void resetGame(){
         // reset everything 
         gameMode = 0;
         drawBoard(dimension = 0);
         clearMarkers();
         round = 1;

         printIntro();

        gameState = GAME_MENU;
    }

    private static void updateBoard(String input){
        boolean winnerExist = false;
        // update board
        try {
            int i = Integer.valueOf(input);
            if(turn == PLAYER_ONE){
                board.put(i, "X");
                plyOneMarkers.add(i);
                winnerExist = checkBoard(plyOneMarkers);
            } else {
                board.put(i, "O");
                plyTwoMarkers.add(i);
                winnerExist = checkBoard(plyTwoMarkers);
            }
        } catch(NumberFormatException e) {
            System.out.println(DIVIDER);
            System.out.println("Invalid input! \n\nPlease input either '1' to '" + board.size() + "' or \n'n' to exit game.");
            System.out.println(DIVIDER);

            System.out.println("Player " + turn + " turn!");
            return;
        }

        if(round == board.size() || winnerExist){
            // game over
            gameState = GAME_END;
            gameOver(winnerExist);
        } else {
            System.out.println();
            System.out.println(DIVIDER);
            System.out.println("Game in Progress");
            System.out.println(DIVIDER);

            if(turn != 0){
                turn = (turn == PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;
            }

            System.out.println("Player " + turn + " turn!");

            round++;
        }
    }

    private static void drawBoard(int boardSize){
        if(boardSize == 0) {
            return;
        }

        // get total size of board
        boardSize *= boardSize;
        
        // increment board size by 1 as starting index is 1
        boardSize+=1;
        
        System.out.println("\n...drawing board...\n");
        
        for(int i = 1; i < boardSize; i++){
            board.put(i, String.valueOf(i));
        }
    }

    private static void clearMarkers(){
        plyOneMarkers.clear();
        plyTwoMarkers.clear();
    }

    /* 
    ------------------------------------------------------------------
    Finding a possible match with NxN which can be applied on 3x3
    ------------------------------------------------------------------
    1   2   3

    4   5   6

    7   8   9

    let take 5 as an example and assume n = 3,
    find all markers beside 5 :
    
    Horizontal : [4, 5, 6]
    Condition : (position % n) != 1
    left of 5 is 4 = 5(position) - 1

    Condition : (position % n) != 0
    right of 5 is 3 = 5(position) + 1

    Vertical : [2, 5, 8]
    Condition : position > n
    top of 5 is 2 = 5(position) - n
    
    Condition : (position + n) < (n * n)
    bottom of 5 is 8 = 5(position) + n

    Diagonally : [1, 5, 9], [3, 5, 7]
    Condition : (position % n) != 1
    top-left of 5 is 1 = (get top position) - 1
    
    Condition : (position % n) != 0
    bottom-right of 5 is 9 = (get bottom position) + 1

    Condition : (position % n) != 1
    top-right of 5 is 3 = (get top position) + 1

    Condition : (position % n) != 0
    bottom-left of 5 is 7 = (get bottom position) - 1

    Find the possiable route before going ahead :
    Break if a blocking marker exist

    ------------------------------------------------------------------
    */

    private static boolean checkBoard(ArrayList<Integer> markers){
        if(round <= 4) {
            // none of the players have 3 markers on board
            return false;
        }

        for(int i : markers){
            if(checkHorizontal(i, markers) || checkDiagonally(i, markers) || checkVertical(i, markers)){
                return true;
            }
        }

        return false;
    }

    private static boolean checkHorizontal(int position, ArrayList<Integer> markers){
        // check for other possible markers beside the current one
        if((position % dimension) > 1){
            // ignore first column and last column
            return (markers.contains(position - 1) && markers.contains(position - 1));
        }

        return false;
    }

    private static boolean checkVertical(int position, ArrayList<Integer> markers){
        // check for other possible markers beside the current one
        if(position > dimension && (position + dimension) < board.size()){
            // ignore the first row and last row
            return (markers.contains(position - 1) && markers.contains(position - 1));
        }
        
        return false;
    }

    private static boolean checkDiagonally(int position, ArrayList<Integer> markers){
        // check for other possible markers beside the current one

        // iterate through
        
        return false;
    }
}