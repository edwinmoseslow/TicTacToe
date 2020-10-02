# Tic Tac Toe - CLI(Java)

This is a simple game of tic tac toe.

### Game Mode :

- Basic Mode : 3x3
- Intermediate Mode : NxN

### Game Rules :

Just like any other Tic Tac Toe game, players are required to get a 3 matching combination horizontally, diagonally or vertically in order to win the game.

### Game Manual :

As a user all you need to do is to read the instructions given to you via the terminal and follow through by entering the specific options.

---
<br>

## How to run the game :
First, compile the game by typing :
    
    javac game/*.java

Then run the game by typing either of the two command below : 
    
    java game/TicTacToe

    or

    java game.TicTacToe

---
<br>

## Documentation :

### To get the project :
---

Clone via termianl with HTTPS
    
    $ git clone https://github.com/edwinmoseslow/tic-tac-toe.git


Clone via github CLI :
    
    $ gh repo clone edwinmoseslow/tic-tac-toe


Or you can download the project as a ZIP file. 

### Logic behind finding if a match/combination has been found :
---
What you see below is the explaination of the logic that is applied in `BoardChecker.java`.

Finding a match with NxN with certain assumptions in place,

    Assuming N = 3,
    NxN = 3x3 = 9,

    1 | 2 | 3
    ----------
    4 | 5 | 6
    ----------
    7 | 8 | 9

Position - `1, 2, 3, 4, 6, 7, 8, 9` :<br>
Are able to form **`2 - 3`** possible combinations.
    
Position - `5` :<br>
Is able to form **`4`** possible combinations.

The possible combinations increases to **`8`**, if we include the used of finding both the vertical and horizontal combination of the positions for `top, bottom, left, right` which is the corners of the current position `5`.  

Hence one of the possible solution is to target the most centralized position on the board which is `5` on a 3x3 board to make the best of it.

The logic behind on how we can utilize the central position of the board and find if there is a match.

Assuming the current position is `5`, find all markers around :

### Find Horizontal Match : [4, 5, 6]
    
     1 | 2 | 3
    -----------
    [4]|[5]|[6]
    -----------
     7 | 8 | 9

    Assuming position = 5

    Condition to prevent selecting position at the extreme left :
    (position % n) != 1

    Condition to prevent selecting position at the extreme right : 
    (position % n) != 0

    Condition to prevent selecting positions at both extreme left & right : 
    (position % n) > 1

    left of 5 : 4 = position - 1
    right of 5 : 3 = position + 1


### Find Vertical Match : [2, 5, 8]

     1 |[2]| 3
    -----------
     4 |[5]| 6 
    -----------
     7 |[8]| 9

    Assuming position = 5

    Condition to prevent selecting position at extreme top : 
    position > n
    
    Condition to prevent selecting position at extreme bottom :
    (position + n) <= (n * n)

    Condition to prevent selecting positions at both extreme top & bottom :
    position > n && (position + n) <= (n * n)
    
    top of 5 is 2 = position - n
    bottom of 5 is 8 = position + n

### Find Diagonally Match : [1, 5, 9], [3, 5, 7]

    [1]| 2 |[3]
    -----------
     4 |[5]| 6 
    -----------
    [7]| 8 |[9]

    To check diagonally, it possible to reuse the same concept for horizontal & vertical and extend to the diagonal condition.

    Condition to prevent selecting positions from all 4 corners :
    (position % n) > 1 && position > n && (position + n) <= (n * n)

    top-left of 5 is 1 = (get top position) - 1
    bottom-right of 5 is 9 = (get bottom position) + 1
    
    top-right of 5 is 3 = (get top position) + 1
    bottom-left of 5 is 7 = (get bottom position) - 1

### What about the other 4 possible match?

    [1]|[2]|[3]
    -----------
    [4]| 5 |[6]
    -----------
    [7]|[8]|[9]

    The same can be applied to :
    top position    - [2], find horizontal combination
    bottom position - [8], find horizontal combination
    left position   - [4], find vertical combination
    right position  - [6], find vertical combination

## Cons about this solution :
---
One of the main con is that the complexity increases according to N. Hence when N is increased the board increases in size which also tells us that there will be more centralized positions to check for. With that this solution would have to iterate through all of the centralized positions as it goes.

## Reducing the number of check needed :
---
To reduce load, instead of iterating through all the centralized positions. I created an ArrayList to track only those positions that the user have entered/marked. Now all that is left to do is to iterate through the marked centralized positions.

## Things I learnt :
---
Initally while doing a 3x3 board, I actually thought of just checking through the each of the blocks against the combinations in order to find a winner. It was only when I thought about the NxN board which led me to consider of making my codes much more dynamic to cater to NxN board. While also considering the amount of possible block to iterate. It is definitely not efficient to search throught the whole board. Hence I thought of searching through the block with the highest possibility of finding a match and only the blocks that the user have selected.

## Things I noticed :
---
There is only this much of space your screen can show you through your terminal hence if you try putting N > 500, I believe that the program will probably still run but the output will definitely be messed up unless you do have a screen size resolution that is able to hold this much of data in your terminal.

I also believed that this solution is not the best and I am certain there are better solution to optimize or beat this solution in terms of processes.