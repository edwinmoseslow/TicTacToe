import java.util.Scanner;
import java.util.*;

public class TicTacToe{
    
    // local variables
    private static String horLines          = "-----------";
    private static String verLines          = "  |  |  ";
    private static String divder            = "------------------------------------";
    private static int gameState            = 0;
    
    private static final int GAME_DEFAULT  = 0;
    private static final int GAME_MENU     = 1;
    private static final int GAME_START    = 2;
    private static final int GAME_END      = 3;

    private static int turn                = 0;
    private static final int PLAYER_1      = 1;
    private static final int PLAYER_2      = 2;

    private static int option              = 0;
    private static final int OPTION_1      = 1;
    private static final int OPTION_2      = 2;

    private static boolean exit = false;

    private static Map<Integer, String> board;

    private static int round = 0;

    public static void main(String []args){
        
        board = new HashMap<Integer, String>();
        Scanner scanner = new Scanner(System.in);

        resetBoard();
        
        printIntro();

        gameState = GAME_MENU;
        
        userInput(scanner.nextLine());

        while(!exit){
            printBoard();

            userInput(scanner.nextLine());
        }
    }
     
    private static void drawBoard(){
        
        System.out.println(horLines);
         
        // break line
        System.out.println();
         
        // Top
        System.out.println(verLines);
        System.out.println(horLines);
         
        // Middle
        System.out.println(verLines);
         
        // Bottom
        System.out.println(horLines);
        System.out.println(verLines);
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
                System.out.println("\n" + horLines);
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
            System.out.println("To Continue Game, Press '1' to '9' and follow up by Enter.");
        }
    }
    
    private static void printIntro(){
        // Game title
        System.out.println("Tic Tac Toe");
        
        // Divder
        System.out.println(divder);
        
        // Ask for game mode
        System.out.println("Enter [1] for Player vs Computer");
        System.out.println("Enter [2] for Player Vs Player");
        
        // Divder
        System.out.println(divder);
    }
    
    private static void userInput(String input){
        switch (input){
            case "n":
                exit = true;
                return;
            case "r":
                if(gameState == GAME_END)
                    gameState = GAME_DEFAULT;
                break;
        }

        switch (gameState) {
            case GAME_MENU:
                resetBoard();
                menuSelection(input);
                break;
            case GAME_START:
                updateBoard(input);
                break;
            case GAME_END:
                gameOver(input);
                break;
        } 
    }

    private static void menuSelection(String input){
        try {
            option = Integer.valueOf(input);
            switch (option){
                case OPTION_1:
                    // VS COM
                    break;
                case OPTION_2:
                    // VS PLAYER
                    break;
                default:
                    System.out.println(divder);
                    System.out.println("Invalid input! \n\nPlease input either '1' to '2' or \n'n' to exit game.");
                    System.out.println(divder);
                    return;
            }
        } catch(NumberFormatException e) {
            System.out.println(divder);
            System.out.println("Invalid input! \n\nPlease input either '1' to '2' or \n'n' to exit game.");
            System.out.println(divder);
            return;
        }

        gameState = GAME_START;
        System.out.println(divder);
        System.out.println("Game Start");
        System.out.println(divder);

        if(turn == 0){
            Random r = new Random();
            turn = ((r.nextInt(10)%2 == 0) ? PLAYER_2 : PLAYER_1);  
            System.out.println("Player " + turn + " will start first!");  
        }
    }

    private static void updateBoard(String input){
        // update board
        try {
            int i = Integer.valueOf(input);
            if(turn == PLAYER_1){
                board.put(i, "O");
            } else {
                board.put(i, "X");
            }
        } catch(NumberFormatException e) {
            System.out.println(divder);
            System.out.println("Invalid input! \n\nPlease input either '1' to '9' or \n'n' to exit game.");
            System.out.println(divder);

            System.out.println("Player " + turn + " turn!");
            return;
        }
        
        System.out.println();
        System.out.println(divder);
        System.out.println("Game in Progress");
        System.out.println(divder);

        if(turn != 0){
            turn = (turn == PLAYER_1) ? PLAYER_2 : PLAYER_1;
        }

        System.out.println("Player " + turn + " turn!");

        round++;

        if(round == 9){
            // game over
            gameState = GAME_END;
        }

        // check for winner
    }

    private static void gameOver(String input){
        System.out.println(divder);
        System.out.println("Game Over");
        System.out.println(divder);

        // reset everything 
        option = 0;
        resetBoard();
    }

    private static void resetBoard(){
        System.out.println("\n...resetting board...\n");
        for(int i = 1; i < 10; i++){
            board.put(i, String.valueOf(i));
        }
    }

    private static void checkBoard(){

    }
}