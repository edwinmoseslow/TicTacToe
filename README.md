# Tic Tac Toe - CLI(Java)

This is a simple game of tic tac toe.

### Game Mode :

    Basic Mode : 3x3
    Intermediate : NxN

### Game Rules :

    Just like any other Tic Tac Toe game, players are required to get a 3 matching combination horizontally, diagonally or vertically in order to win the game.

### Game Manual :

    As a user all you need to do is to read the instructions given to you via the terminal and follow through by entering the specific options.

---
<br>

## How to run the game :
First, compile the game by typing :

    javac game/*.java

Then run the game by typing : 

    java game/TicTacToe

    or

    java game.TicTacToe

---
<br>

## Documentation :

### To get the project :
    
    Clone via termianl with HTTPS
    $ git clone https://github.com/edwinmoseslow/tic-tac-toe.git

    Clone via github CLI :
    $ gh repo clone edwinmoseslow/tic-tac-toe

    Download the project as a ZIP file. 


Finding a possible match with NxN with certain assumptions in place,

    Assuming N = 3,
    NxN = 3x3 = 9,

    1 | 2 | 3
    ----------
    4 | 5 | 6
    ----------
    7 | 8 | 9

    Position [1, 2, 3, 4, 6, 7, 8, 9] :
    Are able to form 2 - 3 possible combinations.
    
    Position [5] :
    Is able to form 4 possible combinations.

    The possible combinations increases to 8, if we include the used of finding both the vertical and horizontal combination of the positions for [top, bottom, left, right] which is the corners of the current position [5].  
    
    Hence one of the possible solution is to target the most centralized position on the board which is [5] on a 3x3 board to make the best of it.

The logic behind on how we can utilize the central position of the board :

    Find all markers around [5] :
    
    ----------------------------------
    Horizontal : [4, 5, 6]
    ----------------------------------
    Condition to prevent selecting position at the extreme left :
    (position % n) != 1

    Condition to prevent selecting position at the extreme right : 
    (position % n) != 0

    Condition to prevent selecting positions at both extreme left & right : 
    (position % n) > 1

    left of 5 : 4 = 5 - 1
    right of 5 : 3 = 5 + 1

    ----------------------------------
    Vertical : [2, 5, 8]
    ----------------------------------
    Condition to prevent selecting position at extreme top : 
    position > n
    
    Condition to prevent selecting position at extreme bottom :
    (position + n) <= (n * n)

    Condition to prevent selecting positions at both extreme top & bottom :
    position > n && (position + n) <= (n * n)
    top of 5 is 2 = 5(position) - n
    bottom of 5 is 8 = 5(position) + n

    ----------------------------------
    Diagonally : [1, 5, 9], [3, 5, 7]
    ----------------------------------
    To check diagonally, I the same concept for horizontal & vertical and extend to the diagonal condition.

    Condition to prevent selecting positions from all 4 corners :
    (position % n) > 1 && position > n && (position + n) <= (n * n)

    top-left of 5 is 1 = (get top position) - 1
    bottom-right of 5 is 9 = (get bottom position) + 1
    
    top-right of 5 is 3 = (get top position) + 1
    bottom-left of 5 is 7 = (get bottom position) - 1

    ----------------------------------
    What about other 4 possible match?
    ----------------------------------

     1 |[2]| 3
    -----------
    [4]| 5 |[6]
    -----------
     7 |[8]| 9

    The same can be apply to :
    top position    - [2], find horizontal combination
    bottom position - [8], find horizontal combination
    left position   - [4], find vertical combination
    right position  - [6], find vertical combination

Cons about this solution :

    Though this solution may seems like it does a good job but the complexity increases according to N. When N increases the board increases which also tells us that there will be more centralized positions that will occur on the board. With that this solution would have to iterate through all of the centralized position as it goes.

    To reduce load, instead of iterating through all the position. I created an ArrayList to track only those positions that the user have entered/marked and with that all I needed to iterate through are the marked positions and its surrounding.

Things I noticed :

    There is only this much of space your screen can show you through your terminal hence if you try putting N > 500, I believe that the program will probably still run but the output will definitely be messed up unless you do have a screen size resolution that is able to hold this much of data in your terminal.

    I also believed that this solution is not the best and I am certain there are better solution to optimize or beat this solution in terms of processes.   