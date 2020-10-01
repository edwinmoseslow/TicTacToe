import java.util.Scanner;
import java.util.*;
import java.lang.*;

public class TicTacToe{
    
    // Commandline Interface  
    private static String horLines              = ""; // required be reset
    private static final int SPACE_BETWEEN      = 2;
    private static final String VER_LINES       = "|";
    private static final String DIVIDER         = "----------------------------------------------------";

    // Game State
    private static int gameState                = 0; // required be reset
    private static final int GAME_DEFAULT       = 0;
    private static final int GAME_ETR_NAME      = 1;
    private static final int GAME_ETR_N_BOARD   = 2;
    private static final int GAME_MENU          = 3;
    private static final int GAME_START         = 4;
    private static final int GAME_END           = 5;

    private static final int ERROR_OPTION       = 1;
    private static final int ERROR_SELECTION    = 2;
    private static final int ERROR_BOARD_SIZE   = 3;

    // Player Turn
    private static int turn                     = 0; // required be reset
    private static final int PLAYER_DEFAULT     = 0;
    private static final int PLAYER_ONE         = 1;
    private static final int PLAYER_TWO         = 2;

    // Player Name
    private static String plyOneName            = ""; // required be reset
    private static String plyTwoName            = ""; // required be reset

    // Game Mode
    private static int gameMode                 = 0; // required to reset
    private static final int DEFAULT_MODE       = 0;
    private static final int BASIC_MODE         = 1;
    private static final int INTERME_MODE       = 2;

    // Game Exit Check
    private static boolean exit                 = false;

    // Number of Rounds Check
    private static int round                    = 1; // required be reset
    private static final int STARTING_ROUND     = 1;

    // Board Dimension
    private static int dimension                = 0; // required be reset

    // Scanner
    private static Scanner scanner;

    // Game Board
    private static Map<Integer, String> board; // required be reset

    // Markers
    private static ArrayList<Integer> plyOneMarkers; // required be reset
    private static ArrayList<Integer> plyTwoMarkers; // required be reset

    public static void main(String []args) {
        // init a new instance
        board = new HashMap<Integer, String>();
        scanner = new Scanner(System.in);
        plyOneMarkers = new ArrayList<Integer>();
        plyTwoMarkers = new ArrayList<Integer>();
        
        gameState = GAME_DEFAULT;

        while(!exit) {
            printInterface();
            userInput(scanner.nextLine());
        }
    }

    private static void printInterface() {
        switch (gameState) {
            case GAME_DEFAULT:
                printIntro();
                turn = PLAYER_ONE;
                printAskName();
                break;
            case GAME_ETR_NAME:
                printAskName();
                break;
            case GAME_MENU:
                printMenuOption();
                break;
            case GAME_START:
                printBoard();
                break;
        } 
    }

    private static void setupHorLines() {
        horLines = "";
        int space = getLongestString() + SPACE_BETWEEN;
        int length = space*dimension + dimension - 1;
        int count = length;
        while(count != 0){
            horLines+="-";
            count--;
        }
    }

    private static void printBoard() {
        if(board.size() == 0) {
            return;
        }

        int count = 1;

        setupHorLines();

        // breakline
        System.out.println();

        for(Map.Entry m : board.entrySet()) {
            printValue(String.valueOf(m.getValue()));
            
            if(count%dimension == 0 && count != board.size()) {
                System.out.println("\n" + horLines);
            } else {
                if(count != board.size()){
                    System.out.print(VER_LINES);
                }
            }
            count++;
        }

        System.out.println("\n\nTo exit game, enter 'n' and follow up by Enter.\n");
        
        if(gameState == GAME_END) {
            System.out.println("To play another round, enter 'r' and follow up by Enter.");
        } else {
            switch (turn){
                case PLAYER_ONE:
                    System.out.println("Player " + PLAYER_ONE + " : " + plyOneName + " turn to choose!");
                    System.out.println("Choose the number shown above to place an 'X' into:");
                    break;
                case PLAYER_TWO:
                    System.out.println("Player " + PLAYER_TWO + " : " + plyTwoName + " turn to choose!");
                    System.out.println("Choose the number shown above to place an 'O' into:");
                    break;
            }
        }
    }

    private static void printValue(String value){
        int space = getLongestString() - value.length();
        String padding = "";

        for(int i = 0; i < space; i++){
            padding+=" ";
        }

        System.out.print(" " + padding + value + " ");
    }

    private static void printMenuOption() {
        System.out.println(DIVIDER);
        System.out.println("Enter '1' for Basic Mode(Board Size : 3x3)");
        System.out.println("Enter '2' for Intermediate Mode(Board Size : NxN)");
        System.out.println("Enter 'n' to Exit");
        System.out.println(DIVIDER);
    }
    
    private static void printIntro() {
        System.out.println(DIVIDER);
        System.out.println("Tic Tac Toe");
        System.out.println(DIVIDER);
        gameState = GAME_ETR_NAME;
    }

    private static void printAskName(){
        switch(turn){
            case PLAYER_ONE:
                System.out.println("Enter name for Player 1 :");
            break;
            case PLAYER_TWO:
                System.out.println("Enter name for Player 2 :");
            break;
        }
    }
    
    private static void userInput(String input){
        switch (input){
            case "n":
                if(gameState == GAME_START || gameState == GAME_END || gameState == GAME_MENU) {
                    exit = true;
                }
                return;
            case "r":
                if(gameState == GAME_END) {
                    resetGame();
                }
                return;
        }

        switch (gameState) {
            case GAME_MENU:
                menuSelection(input);
                break;
            case GAME_ETR_NAME:
                setPlayerName(input);
                break;
            case GAME_ETR_N_BOARD:
                setDimension(input);
                break;
            case GAME_START:
                updateBoard(input);
                break;
        } 
    }

    private static void menuSelection(String input){
        try {
            gameMode = Integer.valueOf(input);
            switch (gameMode){
                case BASIC_MODE:
                    // Basic Mode
                    setDimension("3");
                    break;
                case INTERME_MODE:
                    // Intermediate Mode
                    System.out.println("Enter the size of your board :");
                    gameState = GAME_ETR_N_BOARD;
                    return;
                default:
                    printErrorMsg(ERROR_OPTION);
                    gameMode = DEFAULT_MODE;
                    return;
            }
        } catch(NumberFormatException e) {
            printErrorMsg(ERROR_OPTION);
            return;
        }
    }

    private static void printErrorMsg(int errorCode){
        switch(errorCode){
            case ERROR_OPTION:
                System.out.println(DIVIDER);
                System.out.println("Invalid input! \n- Please input either '1' to '2' or \n'n' to exit game.");
                System.out.println(DIVIDER);
                break;
            case ERROR_SELECTION:
                System.out.println(DIVIDER);
                System.out.println("Invalid input! \n- Please input either '1' to '" + board.size() + "' or 'n' to exit game.");
                System.out.println(DIVIDER);
                break;
            case ERROR_BOARD_SIZE:
                System.out.println(DIVIDER);
                System.out.println("Invalid input! \n- Please input any number of your choice that is higher than '2' or \n'n' to exit game.");
                System.out.println(DIVIDER);
                break;
            
        }
    }

    private static void setDimension(String input){
        try {
            dimension = Integer.valueOf(input);
            if(dimension <= 2){
                printErrorMsg(ERROR_BOARD_SIZE);
                return;
            } else {
                setupBoard(dimension);
                gameState = GAME_START;
            }
        } catch(NumberFormatException e) {
            printErrorMsg(ERROR_BOARD_SIZE);
            return;
        }

        gameStart();
    }


    private static void gameStart(){
        gameState = GAME_START;
        System.out.println(DIVIDER);
        System.out.println("Game Start");
        System.out.println(DIVIDER);

        determineWhoToStart();
    }

    private static void determineWhoToStart(){
        if(turn == PLAYER_DEFAULT){
            Random r = new Random();
            turn = ((r.nextInt(10)%2 == 0) ? PLAYER_TWO : PLAYER_ONE);  
            // System.out.println("Player " + turn + " will start first!");  
        }
    }

    private static void gameOver(boolean winnerExist){
        System.out.println(DIVIDER);
        System.out.println("Game Over");
        System.out.println(DIVIDER);

        printBoard();

        if(winnerExist){
            switch (turn){
                case PLAYER_ONE:
                    System.out.println("\nCongratulations Player " + PLAYER_ONE + " : " + plyOneName + "! You have won.");
                    break;
                case PLAYER_TWO:
                    System.out.println("\nCongratulations Player " + PLAYER_TWO + " : " + plyTwoName + "! You have won.");
                    break;
            }
        } else {
            System.out.println("\nIt's a Draw!");
        }
    }

    private static void resetGame(){
        // reset everything 
        clearData();
        gameMode = DEFAULT_MODE;
        round = STARTING_ROUND;
        gameState = GAME_DEFAULT;
    }

    private static void updateBoard(String input){
        boolean winnerExist = false;
        // update board
        try {
            int i = Integer.valueOf(input);

            if(i > board.size() || i <= 0){
                printErrorMsg(ERROR_SELECTION);
                return;
            }

            switch (turn) {
                case PLAYER_ONE:
                    board.put(i, "X");
                    plyOneMarkers.add(i);
                    winnerExist = checkBoard(plyOneMarkers);
                    break;
                case PLAYER_TWO:
                    board.put(i, "O");
                    plyTwoMarkers.add(i);
                    winnerExist = checkBoard(plyTwoMarkers);
                    break;
            }
        } catch(NumberFormatException e) {
            printErrorMsg(ERROR_SELECTION);
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

            if(turn != PLAYER_DEFAULT){
                turn = (turn == PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;
            }
        }

        round++;
    }

    private static void setupBoard(int boardSize){
        if(boardSize == 0) {
            return;
        }

        // get actual size of board
        boardSize *= boardSize;
        
        // increment board size by 1 as starting index is 1
        boardSize+=1;
        
        for(int i = 1; i < boardSize; i++){
            board.put(i, String.valueOf(i));
        }
    }

    private static void clearData(){
        board.clear();
        plyOneMarkers.clear();
        plyTwoMarkers.clear();
        horLines = "";
    }

    private static boolean checkBoard(ArrayList<Integer> markers){
        if(round <= 4) {
            // none of the players have 3 markers on board
            return false;
        }

        // iterate through known markers
        for(int i : markers){
            if(checkHorizontal(i, markers) || checkDiagonally(i, markers) || checkVertical(i, markers)){
                return true;
            }
        }

        return false;
    }

    private static boolean checkHorizontal(int position, ArrayList<Integer> markers){
        // ignore markers in first column and last column
        if((position % dimension) > 1){
            // check if markers exist on the left and right of the current marker
            return (markers.contains(position - 1) && markers.contains(position + 1));
        }

        return false;
    }

    private static boolean checkVertical(int position, ArrayList<Integer> markers){
        // ignore markers in first row and last row
        if(position > dimension && (position + dimension) <= (dimension * dimension)){
            // check if markers exist on the top and bottom of the current marker
            return (markers.contains(position - dimension) && markers.contains(position + dimension));
        }
        
        return false;
    }

    private static boolean checkDiagonally(int position, ArrayList<Integer> markers){
        // ignore markers in first row + column and last row + column
        if((position % dimension) > 1 && position > dimension && (position + dimension) < (dimension * dimension)) {
            int top = position - dimension;
            int bottom = position + dimension;
            int left = position - 1;
            int right = position + 1;

            // check if markers exist on the top-left/bottom-right and top-right/bottom-left of the current marker
            return ((markers.contains(top - 1) && markers.contains(bottom + 1)) || ((markers.contains(top + 1) && markers.contains(bottom - 1))));
        }

        return false;
    }

    private static void setPlayerName(String input) {
        if(input.isEmpty() || input.trim().length() == 0) {
            System.out.println(DIVIDER);
            System.out.println("Invalid input! \n- Please input a name using characters or numbers.");
            System.out.println(DIVIDER);
            return;
        }

        switch (turn){
            case PLAYER_ONE:
                plyOneName = input;
                gameState = GAME_ETR_NAME;
                turn = PLAYER_TWO;
                break;
            case PLAYER_TWO:
                if(plyOneName.equals(input)){
                    System.out.println(DIVIDER);
                    System.out.println("Same name detected! \n- Please input another name.");
                    System.out.println(DIVIDER);
                    return;
                }
                
                turn = PLAYER_DEFAULT;
                plyTwoName = input;
                gameState = GAME_MENU;
                break;
        }
    }

    private static int getLongestString(){
        int maxSize = 0;
        int count = board.size();

        while(count != 0){
            maxSize = Math.max(maxSize, board.get(count).length());
            count--;
        }

        return maxSize;
    }
}