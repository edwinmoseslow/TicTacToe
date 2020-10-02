package game;

public class Output {

    public static void printMenuOption() {
        System.out.println();
        System.out.println(Constant.DIVIDER);
        System.out.println("Enter '1' for Basic Mode(Board Size : 3x3)");
        System.out.println("Enter '2' for Intermediate Mode(Board Size : NxN)");
        System.out.println("Enter 'n' to Exit");
        System.out.println(Constant.DIVIDER);
    }

    public static void printTitle(int gameState) {
        switch(gameState){
            case Constant.GAME_DEFAULT:
                System.out.println(Constant.DIVIDER);
                System.out.println("Tic Tac Toe");
                System.out.println(Constant.DIVIDER);
                break;
            case Constant.GAME_START:
                System.out.println(Constant.DIVIDER);
                System.out.println("Game Start");
                System.out.println(Constant.DIVIDER);
                break;
            case Constant.GAME_IN_PROGRESS:
                System.out.println();
                System.out.println(Constant.DIVIDER);
                System.out.println("Game in Progress");
                System.out.println(Constant.DIVIDER);
                break;
            case Constant.GAME_END:
                System.out.println(Constant.DIVIDER);
                System.out.println("Game Over");
                System.out.println(Constant.DIVIDER);
                break;
        }
    }

    public static void printAskName(int turn){
        switch(turn){
            case Constant.PLAYER_ONE:
                System.out.println("Enter name for Player 1 :");
            break;
            case Constant.PLAYER_TWO:
                System.out.println("Enter name for Player 2 :");
            break;
        }
    }

    public static void errorMsg(int errorCode){
        switch(errorCode){
            case Constant.ERROR_OPTION:
                System.out.println(Constant.DIVIDER);
                System.out.println("Invalid input! \nPlease input either '1' to '2' or \n'n' to exit game.");
                System.out.println(Constant.DIVIDER);
                break;
            case Constant.ERROR_SELECTION:
                System.out.println(Constant.DIVIDER);
                System.out.println("Invalid input! \nPlease input either one of the number shown on the board or 'n' to exit game.");
                System.out.println(Constant.DIVIDER);
                break;
            case Constant.ERROR_BOARD_SIZE:
                System.out.println(Constant.DIVIDER);
                System.out.println("Invalid input! \nPlease input any number of your choice that is higher than '2' or \n'n' to exit game.");
                System.out.println(Constant.DIVIDER);
                break;
            case Constant.ERROR_INVAILD_NAME:
                System.out.println(Constant.DIVIDER);
                System.out.println("Invalid input! \nPlease input a name using characters or numbers.");
                System.out.println(Constant.DIVIDER);
                break;
            case Constant.ERROR_SAME_NAME:
                System.out.println(Constant.DIVIDER);
                System.out.println("Same name detected! \nPlease input another name.");
                System.out.println(Constant.DIVIDER);
                break;
            case Constant.ERROR_OCCUPIED:
                System.out.println(Constant.DIVIDER);
                System.out.println("Invalid selection! \nPlease input numbers that are shown.");
                System.out.println(Constant.DIVIDER);
                break;
        }
    }
}