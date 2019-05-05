import java.math.*;
import java.util.Arrays;
/**
 * Write a description of class EditDistance here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EditDistance
{
    /**
     * Constructor for objects of class EditDistance
     */
    public EditDistance()
    {
        // initialise instance variables
    }

    /**
     * Gets the minimum distance between words at m,n points
     */
    public int minDistance(String word1, String word2) {
        int m=word1.length();
        int n=word2.length();
        int[][] temp = new int[m][n];
        //fill the array arr with -1's for size mn
        for(int[] arr: temp) {
            Arrays.fill(arr, -1);
        }
        return calDistance(word1, word2, temp, m-1, n-1);
    }

    /**
     * iterates through the words at points i and j, then gives the words up until that 
     * point to minDistance where it calculates the minumum distance for that point.
     */
    private int calDistance(String word1, String word2, int[][] temp, int i, int j){
        //check to see if the array still has -1's in it
        if(i < 0) {
            return j + 1;
        }
        else if(j < 0) {
            return i + 1;
        }
        //if it isnt a -1 then return what is really in there
        if(temp[i][j] != -1) {
            return temp[i][j];
        }
        //if the letters equal each other then set the distance of the temp matrix to the distance
        if(word1.charAt(i) == word2.charAt(j)) {
            temp[i][j] = calDistance(word1, word2, temp, i-1, j-1);
        }
        else {
            //if they dont equal each other then find the last place they equaled and then 
            // add one to it; looks j-1, i-1, and i-1 & j-1  to see if the letters matched 
            //recently; upper left corner +1
            int prevMin = Math.min(calDistance(word1, word2, temp, i, j-1), calDistance(word1, word2, temp, i-1, j));
            prevMin = Math.min(prevMin, calDistance(word1, word2, temp, i-1, j-1));
            temp[i][j] = 1 + prevMin;
        }
        return temp[i][j];
    }
}
