import java.util.Scanner;
import java.util.*;
import java.lang.*;

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
    private static String horLines          = "";
    private static final int SPACE_BETWEEN  = 2;
    private static final String VER_LINES   = "|";
    private static final String DIVIDER     = "----------------------------------------------------";

    // Game State
    private static int gameState              = 0;
    private static final int GAME_DEFAULT     = 0;
    private static final int GAME_ETR_NAME    = 1;
    private static final int GAME_ETR_N_BOARD = 2;
    private static final int GAME_MENU        = 3;
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

    public static void main(String []args) {
        // init a new instance
        board = new HashMap<Integer, String>();
        scanner = new Scanner(System.in);
        plyOneMarkers = new ArrayList<Integer>();
        plyTwoMarkers = new ArrayList<Integer>();
        
        gameState = GAME_DEFAULT;

        while(!exit) {
            printOptions();
            userInput(scanner.nextLine());
        }
    }

    private static void printOptions() {
        switch (gameState) {
            case GAME_DEFAULT:
                printIntro();
                turn = 1;
                printAskName();
                break;
            case GAME_ETR_NAME:
                printAskName();
                break;
            // case GAME_ETR_N_BOARD:
            //     break;
            case GAME_MENU:
                System.out.println(DIVIDER);
                System.out.println("Enter '1' for Basic Mode(Board Size : 3x3)");
                System.out.println("Enter '2' for Intermediate Mode(Board Size : NxN)");
                System.out.println("Enter 'n' to Exit");
                System.out.println(DIVIDER);
                break;
            case GAME_START:
                printBoard();
                break;
        } 
    }

    // only need to set once
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

        // breakline
        System.out.println("\n");

        System.out.println("To exit game, enter 'n' and follow up by Enter.");
        
        if(gameState == GAME_END) {
            System.out.println("To play another round, enter 'r' and follow up by Enter.");
        }
        else {
            switch (turn){
                case 1:
                    System.out.println(plyOneName + ", choose the number shown above to place an 'X' into:");
                    break;
                case 2:
                    System.out.println(plyTwoName + ", choose the number shown above to place an 'O' into:");
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
    
    private static void printIntro() {
        System.out.println(DIVIDER);
        System.out.println("Tic Tac Toe");
        System.out.println(DIVIDER);
        gameState = GAME_ETR_NAME;
    }

    private static void printAskName(){
        switch(turn){
            case 1:
                System.out.println("Enter name for Player 1 :");
            break;
            case 2:
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
                setBoard(input);
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
                    setBoard("3");
                    break;
                case INTERME_MODE:
                    // Intermediate Mode
                    System.out.println("Enter the size of your board :");
                    gameState = GAME_ETR_N_BOARD;
                    return;
                default:
                    System.out.println(DIVIDER);
                    System.out.println("Invalid input! \n- Please input either '1' to '2' or \n'n' to exit game.");
                    System.out.println(DIVIDER);
                    gameMode = 0;
                    return;
            }
        } catch(NumberFormatException e) {
            System.out.println(DIVIDER);
            System.out.println("Invalid input! \n- Please input either '1' to '2' or \n'n' to exit game.");
            System.out.println(DIVIDER);
            return;
        }
    }

    private static void setBoard(String input){
        try {
            dimension = Integer.valueOf(input);
            if(dimension <= 2){
                System.out.println(DIVIDER);
                System.out.println("Input number smaller than 3! \n- Please input any number of your choice or \n'n' to exit game.");
                System.out.println(DIVIDER);
                return;
            } else {
                drawBoard(dimension);
                gameState = GAME_START;
            }
        } catch(NumberFormatException e) {
            System.out.println(DIVIDER);
            System.out.println("Invalid input! \n- Please input any number of your choice that is higher than '2' or \n'n' to exit game.");
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

        printBoard();
    }

    private static void resetGame(){
        // reset everything 
        clearData();
        gameMode = 0;
        round = 1;
        gameState = GAME_DEFAULT;
    }

    private static void updateBoard(String input){
        boolean winnerExist = false;
        // update board
        try {
            int i = Integer.valueOf(input);

            if(i > board.size() || i <= 0){
                System.out.println(DIVIDER);
                System.out.println("Invalid input! \n- Please input either '1' to '" + board.size() + "' or 'n' to exit game.");
                System.out.println(DIVIDER);

                System.out.println("Player " + turn + " turn!");
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
            System.out.println(DIVIDER);
            System.out.println("Invalid input! \n- Please input either '1' to '" + board.size() + "' or 'n' to exit game.");
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
        }

        round++;
    }

    private static void drawBoard(int boardSize){
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

        setupHorLines();
    }

    private static void clearData(){
        board.clear();
        plyOneMarkers.clear();
        plyTwoMarkers.clear();
        horLines = "";
    }

    private static boolean checkBoard(ArrayList<Integer> markers){
        System.out.println("round : " + round);
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
            return (markers.contains(position - 1) && markers.contains(position + 1));
        }

        return false;
    }

    private static boolean checkVertical(int position, ArrayList<Integer> markers){
        // check for other possible markers beside the current one
        if(position > dimension && (position + dimension) <= (dimension * dimension)){
            // ignore the first row and last row
            return (markers.contains(position - dimension) && markers.contains(position + dimension));
        }
        
        return false;
    }

    private static boolean checkDiagonally(int position, ArrayList<Integer> markers){
        // check for other possible markers beside the current one
        if((position % dimension) > 1 && position > dimension && (position + dimension) < (dimension * dimension) {
            int top = position - dimension;
            int bottom = position + dimension;
            int left = position - 1;
            int right = position + 1;

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
            case 1:
                plyOneName = input;
                gameState = GAME_ETR_NAME;
                turn = 2;
                break;
            case 2:
                if(plyOneName.equals(input)){
                    System.out.println(DIVIDER);
                    System.out.println("Same name detected! \n- Please input another name.");
                    System.out.println(DIVIDER);
                    return;
                }
                
                turn = 0;
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