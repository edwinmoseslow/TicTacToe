package game;

import java.util.*;

public class BoardChecker {
    public static boolean checkCombination(ArrayList<Integer> markers, int dimension){
        for(int i : markers){
            if(checkHorizontal(i, markers, dimension) 
                || checkDiagonally(i, markers, dimension) 
                || checkVertical(i, markers, dimension)){
                return true;
            }
        }
        return false;
    }
    

    private static boolean checkHorizontal(int position, ArrayList<Integer> markers, int dimension){
        // ignore markers in first column and last column
        if((position % dimension) > 1){
            // check if markers exist on the left and right of the current marker
            return (markers.contains(position - 1) 
                && markers.contains(position + 1));
        }
        return false;
    }

    private static boolean checkVertical(int position, ArrayList<Integer> markers, int dimension){
        // ignore markers in first row and last row
        if(position > dimension 
            && (position + dimension) <= (dimension * dimension)){
            // check if markers exist on the top and bottom of the current marker
            return (markers.contains(position - dimension) 
                && markers.contains(position + dimension));
        }
        return false;
    }

    private static boolean checkDiagonally(int position, ArrayList<Integer> markers, int dimension){
        // ignore markers in first row + column and last row + column
        if((position % dimension) > 1 
            && position > dimension 
            && (position + dimension) < (dimension * dimension)) {
            int top = position - dimension;
            int bottom = position + dimension;

            // check if markers exist on the top-left/bottom-right and top-right/bottom-left of the current marker
            return ((markers.contains(top - 1) 
                && markers.contains(bottom + 1)) 
                || ((markers.contains(top + 1) 
                && markers.contains(bottom - 1))));
        }
        return false;
    }
}
