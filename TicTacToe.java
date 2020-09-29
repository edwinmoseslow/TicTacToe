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

    private static boolean exit = false;

     public static void main(String []args){
        

        Map<Integer, String> board = new HashMap<Integer, String>();
        
        board = resetBoard(board);

        Scanner scanner = new Scanner(System.in);
        
        printIntro();
        
        userInput(scanner.nextLine());

        while(!exit){
            printBoard(board);

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
     
    private static void printBoard(Map<Integer, String> board){
        int count = 0;

        // breakline
        System.out.println();

        for(Map.Entry m : board.entrySet()){
            System.out.print(" " + m.getKey() + " " + m.getValue());
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
        System.out.println("To Continue Game, Press '1 - 9' and follow up by Enter.");
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
        if(input.equals("n")){
            exit = true;
            return;
        }

        switch (gameState) {
            case 2:
                gameStarted(input);
                break;
            case 3:
                gameOver(input);
                break;
            default:
                menuSelection(input);
                break;
        } 
    }

    private static void menuSelection(String input){
        // System.out.println("User select : " + input);
        gameState = GAME_START;
        gameStarted(input);
    }

    private static void gameStarted(String input){
        System.out.println(divder);
        System.out.println("Game in Progress");
        System.out.println(divder);

        if(turn == 0){
            Random r = new Random();
            turn = ((r.nextInt(10)%2 == 0) ? PLAYER_2 : PLAYER_1);    
        }
        else {
            turn = (turn == PLAYER_1) ? PLAYER_2 : PLAYER_1;
        }

        System.out.println("Player " + turn + " turn!");
    }

    private static void gameOver(String input){
        System.out.println(divder);
        System.out.println("Game Over");
        System.out.println(divder);
        System.out.println("User select : " + input);
    }

    private static Map<Integer, String> resetBoard(Map<Integer, String> board){
        System.out.println("\n...resetting board...\n");
        for(int i = 1; i < 10; i++){
            board.put(i, "");
        }

        return board;
    }
}