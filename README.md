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
To run the game type : 

    make run

or

    java TicTacToe.java

---
<br>

## Documentation :
Finding a possible match with NxN with certain assumptions in place,

    Assuming N = 3,
    NxN = 3x3 = 9,

    1 | 2 | 3
    ----------
    4 | 5 | 6
    ----------
    7 | 8 | 9

    Position [1, 2, 3, 4, 6, 7, 8, 9] :
    Possible combination of 3 : 2 - 3 
    
    Position [5] :
    Possible combination of 3 : 4
    Possible combination of 3 increases to 8, if we include the used of finding both the vertical and horizontal combination of the positions for [top, bottom, left, right] of the current position.  
    
    Hence one of the possible solution is to target the most centralized position on the board which is [5] on a 3x3 board.

The logic behind on how we can utilize the central position of the board :

    Find all markers around [5] :
    

    ----------------------------------
    Horizontal : [4, 5, 6]
    ----------------------------------
    Condition to prevent corner position at the extreme left :
    (position % n) != 1

    Condition to prevent corner position at the extreme right : 
    (position % n) != 0

    Condition to prevent corner positions at both extreme left & right : 
    (position % n) > 1

    left of 5 : 4 = 5 - 1
    right of 5 : 3 = 5 + 1

    ----------------------------------
    Vertical : [2, 5, 8]
    ----------------------------------
    Condition to prevent corner position at extreme top : 
    position > n
    
    Condition to prevent corner position at extreme bottom :
    (position + n) <= (n * n)

    Condition to prevent corner positions at both extreme top & bottom :
    position > n && (position + n) <= (n * n)
    top of 5 is 2 = 5(position) - n
    bottom of 5 is 8 = 5(position) + n

    ----------------------------------
    Diagonally : [1, 5, 9], [3, 5, 7]
    ----------------------------------
    To check diagonally, you should use this in relation to horizontal & vertical.

    Condition to prevent all corners positions :
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

Cons about this soultion :

    Though this solution may seems like it does a good job but the complexity increases according to N. When N increases the board increases which also tells us that there will be more centralized positions that will occur on the board. With that this solution would have to iterate through all of the centralized position as it goes.

    To reduce load, instead of iterating through all the position. I created an ArrayList to track only those positions that the user have entered/marked and with that all I needed to iterate through are the marked positions and its surrounding.

Things I noticed :

    There is only this much of space your screen can show you through your terminal hence if you try putting N > 500, I believe that the program will probably still run but the output will definitely be messed up unless you do have a screen size resolution that is able to hold this much of data in your terminal.

    I also believed that this solution is not the best and I am certain there are better solution to optimize or beat this solution in terms of processes.   