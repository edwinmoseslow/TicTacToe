package game;

import java.util.Scanner;
import java.util.*;

public class TicTacToe{
    
    // Game State
    private static int gameState                = 0; // required be reset
    
    // Player Turn
    private static int turn                     = 0; // required be reset

    // Player Name
    private static String plyOneName            = ""; // required be reset
    private static String plyTwoName            = ""; // required be reset

    // Game Mode
    private static int gameMode                 = 0; // required to reset
    
    // Game Exit Check
    private static boolean exit                 = false;

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
        board = new HashMap<Integer, String>();
        scanner = new Scanner(System.in);
        plyOneMarkers = new ArrayList<Integer>();
        plyTwoMarkers = new ArrayList<Integer>();
        
        gameState = Constant.GAME_DEFAULT;

        while(!exit) {
            printInterface();
            userInput(scanner.nextLine());
        }
    }

    private static void printInterface() {
        switch (gameState) {
            case Constant.GAME_DEFAULT:
                Output.gameBanner(Constant.GAME_DEFAULT);
                gameState = Constant.GAME_ETR_NAME;
                turn = Constant.PLAYER_ONE;
                Output.inputName(turn);
                break;
            case Constant.GAME_ETR_NAME:
                Output.inputName(turn);
                break;
            case Constant.GAME_MENU:
                Output.selectGameMode();
                break;
            case Constant.GAME_START:
                printBoard();
                break;
        } 
    }

    private static void userInput(String input){
        switch (input){
            case Constant.EXIT_GAME:
                if(gameState == Constant.GAME_START 
                    || gameState == Constant.GAME_END 
                    || gameState == Constant.GAME_MENU) {
                    exit = true;
                    return;
                }
                break;
            case Constant.RESET_GAME:
                if(gameState == Constant.GAME_END) {
                    resetGame();
                    return;
                }
                break;
            default:
                if(gameState == Constant.GAME_END) {
                    System.out.println("Enter 'r' to reset game.\nEnter 'n' to exit game.");
                    return;
                }
                break;
        }

        switch (gameState) {
            case Constant.GAME_MENU:
                menuSelection(input);
                break;
            case Constant.GAME_ETR_NAME:
                setPlayerName(input);
                break;
            case Constant.GAME_ETR_N_BOARD:
                setDimension(input);
                break;
            case Constant.GAME_START:
                updateBoard(input);
                break;
        } 
    }

    private static void menuSelection(String input){
        try {
            gameMode = Integer.valueOf(input);
            switch (gameMode){
                case Constant.BASIC_MODE:
                    // Basic Mode
                    setDimension("3");
                    break;
                case Constant.INTERME_MODE:
                    // Intermediate Mode
                    System.out.println("Enter the size of your board :");
                    gameState = Constant.GAME_ETR_N_BOARD;
                    return;
                default:
                    Output.errorMsg(Constant.ERROR_OPTION);
                    gameMode = Constant.DEFAULT_MODE;
                    return;
            }
        } catch(NumberFormatException e) {
            Output.errorMsg(Constant.ERROR_OPTION);
            return;
        }
    }

    private static String setupHorLines() {
        String lines = "";
        int space = getLongestString() + Constant.SPACE_BETWEEN;
        int length = space*dimension + dimension - 1;
        int count = length;
        while(count != 0){
            lines+="-";
            count--;
        }

        return lines;
    }

    private static void printBoard() {
        if(board.size() == 0) {
            return;
        }

        int count = 1;

        String horLines = setupHorLines();

        // breakline
        System.out.println();

        for(Map.Entry<Integer, String> m : board.entrySet()) {
            printValue(String.valueOf(m.getValue()));
            
            if(count%dimension == 0 && count != board.size()) {
                System.out.println("\n" + horLines);
            } else {
                if(count != board.size()){
                    System.out.print(Constant.VER_LINES);
                }
            }
            count++;
        }

        System.out.println("\n\nTo exit game, enter 'n' and follow up by Enter.\n");
        
        if(gameState == Constant.GAME_END) {
            System.out.println("To play another round, enter 'r' and follow up by Enter.");
        } else {
            switch (turn){
                case Constant.PLAYER_ONE:
                    System.out.println("Player " + Constant.PLAYER_ONE + " : " + plyOneName + " turn to choose!");
                    System.out.println("Choose the number shown above to place an 'X' into:");
                    break;
                case Constant.PLAYER_TWO:
                    System.out.println("Player " + Constant.PLAYER_TWO + " : " + plyTwoName + " turn to choose!");
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

    private static void setDimension(String input){
        try {
            dimension = Integer.valueOf(input);
            if(dimension <= 2){
                Output.errorMsg(Constant.ERROR_BOARD_SIZE);
                return;
            } else {
                setupBoard(dimension * dimension);
            }
        } catch(NumberFormatException e) {
            Output.errorMsg(Constant.ERROR_BOARD_SIZE);
            return;
        }

        gameStart();
    }


    private static void gameStart(){
        gameState = Constant.GAME_START;
        Output.gameBanner(Constant.GAME_START);

        determineWhoToStart();
    }

    private static void determineWhoToStart(){
        if(turn == Constant.PLAYER_DEFAULT){
            Random r = new Random();
            turn = ((r.nextInt(10)%2 == 0) ? Constant.PLAYER_TWO : Constant.PLAYER_ONE);
        }
    }

    private static void gameOver(boolean winnerExist){
        Output.gameBanner(Constant.GAME_END);

        printBoard();

        if(winnerExist){
            switch (turn){
                case Constant.PLAYER_ONE:
                    System.out.println("\nCongratulations Player " + Constant.PLAYER_ONE + " : " + plyOneName + "! You have won.");
                    break;
                case Constant.PLAYER_TWO:
                    System.out.println("\nCongratulations Player " + Constant.PLAYER_TWO + " : " + plyTwoName + "! You have won.");
                    break;
            }
        } else {
            System.out.println("\nIt's a Draw!");
        }
    }

    private static void resetGame(){
        board.clear();
        plyOneMarkers.clear();
        plyTwoMarkers.clear();

        gameMode = Constant.DEFAULT_MODE;
        gameState = Constant.GAME_DEFAULT;
    }

    private static void updateBoard(String input){
        boolean winnerExist = false;
        // update board
        try {
            int position = Integer.valueOf(input);

            if(position > board.size() || position <= 0){
                Output.errorMsg(Constant.ERROR_SELECTION);
                return;
            } else if(isOccupied(position)){
                Output.errorMsg(Constant.ERROR_OCCUPIED);
                return;
            }

            switch (turn) {
                case Constant.PLAYER_ONE:
                    board.put(position, "X");
                    plyOneMarkers.add(position);
                    winnerExist = checkBoard(plyOneMarkers);
                    break;
                case Constant.PLAYER_TWO:
                    board.put(position, "O");
                    plyTwoMarkers.add(position);
                    winnerExist = checkBoard(plyTwoMarkers);
                    break;
            }
        } catch(NumberFormatException e) {
            Output.errorMsg(Constant.ERROR_SELECTION);
            return;
        }

        if(getTotalRounds() == board.size() 
            || winnerExist){
            // game over
            gameState = Constant.GAME_END;
            gameOver(winnerExist);
        } else {
            Output.gameBanner(Constant.GAME_IN_PROGRESS);

            if(turn != Constant.PLAYER_DEFAULT){
                turn = (turn == Constant.PLAYER_ONE) ? Constant.PLAYER_TWO : Constant.PLAYER_ONE;
            }
        }
    }

    private static void setupBoard(int boardSize){
        // increment board size by 1 as starting index is 1
        boardSize+=1;
        
        for(int i = 1; i < boardSize; i++){
            board.put(i, String.valueOf(i));
        }
    }

    private static boolean checkBoard(ArrayList<Integer> markers){
        if(getTotalRounds() <= 4) {
            // none of the players have 3 markers on board
            return false;
        }
        
        // iterate markers placed by current player 
        return BoardChecker.checkCombination(markers, dimension);
    }

    private static void setPlayerName(String input) {
        if(input.isEmpty() || input.trim().length() == 0) {
            Output.errorMsg(Constant.ERROR_INVAILD_NAME);
            return;
        }

        switch (turn){
            case Constant.PLAYER_ONE:
                plyOneName = input;
                gameState = Constant.GAME_ETR_NAME;
                turn = Constant.PLAYER_TWO;
                break;
            case Constant.PLAYER_TWO:
                if(plyOneName.equals(input)){
                    Output.errorMsg(Constant.ERROR_SAME_NAME);
                    return;
                }
                
                turn = Constant.PLAYER_DEFAULT;
                plyTwoName = input;
                gameState = Constant.GAME_MENU;
                break;
        }
    }
    
    private static int getLongestString(){
        int maxSize = 0;
        int count = dimension * dimension;

        while(count != 0){
            maxSize = Math.max(maxSize, board.get(count).length());
            count--;
        }
        return maxSize;
    }

    private static int getTotalRounds(){
        int rounds = plyOneMarkers.size() + plyTwoMarkers.size();
        return rounds;
    }

    public static boolean isOccupied(int position){
        return (plyOneMarkers.contains(position) || plyTwoMarkers.contains(position));
    }
}