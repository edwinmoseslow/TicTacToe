import java.util.Scanner;

public class TicTacToe{
    
    // Global variables
    public static String horLines = "---------";
    public static String verLines = "  |  |  ";
    public static String divder = "------------------------------------";
    
     public static void main(String []args){
        Scanner scanner = new Scanner(System.in);
        
        printIntro();
        
        userInput(scanner.nextLine());
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
        // Top row
        System.out.println();
        
        // Middle row
        System.out.println();
        
        // Bottom row
        System.out.println();
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
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
        } 
    }
     
}