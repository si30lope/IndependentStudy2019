import java.util.ArrayList;
import java.util.*;
/**
 * Write a description of class EditDistanceIterative here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EditDistanceIterative
{

    public static int minDistance(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();
        int[][] temp = new int[length1 + 1][length2 + 1];

        for (int i = 0; i <= length1; i++) {
            temp[i][0] = i;
            //System.out.println(i);
        }

        for (int j = 0; j <= length2; j++) {
            temp[0][j] = j;
            //System.out.print(j);
        }
        //System.out.println();
        //iterate though, and check last char
        for (int i = 1; i <= length1; i++) {
            char c1 = word1.charAt(i-1);
            for (int j = 1; j <= length2; j++) {
                char c2 = word2.charAt(j-1);
                //if last two chars equal
                int min = 0;

                if (c1 == c2) {
                    //update temp value for +1 length
                    // temp[i + 1][j + 1] = temp[i][j];
                    temp[i][j] = temp[i-1][j-1];
                } else {
                    min = Math.min(temp[i-1][j], temp[i-1][j-1]);
                    min = Math.min(min,temp[i][j-1]);

                    temp[i][j] = min + 1;
                }
                //System.out.println("x, y, val, c1, c2 " + i + " " + j + " " + temp[i][j] + " " + c1 + " " + c2);
                //System.out.println(temp[i][j]);
            }
        }

        //System.out.println("length1, length2 " + length1 + " " + length2);
        backtrack(temp, length1, length2, word1, word2);
        return temp[length1][length2];
    }

    public static void backtrack(int back[][], int row, int col, String word1, String word2){
        ArrayList<Integer> path = new ArrayList<Integer>();
        int counter = 0;
        path.add(back[row][col]);
        boolean flag1a = false;
        boolean flag1b = false;
        boolean flag2 = false;
        boolean flag3 = false;
        System.out.println();
        char test1 = ' ';
        char test2 = ' ';

        int checkX = row;
        int checkY = col;
        for(int x = row; x>0; x--){

            for(int y = col; y>0; y--){
                if (x == checkX && y == checkY) {
                    test1 = word1.charAt(x-1);
                    test2 = word2.charAt(y-1);

                    if(test1 == test2){
                        path.add(back[x - 1][y - 1]);
                        checkX = x - 1;
                        checkY = y - 1;
                    }
                    else{
                        int topLeft = back[x - 1][y - 1];
                        int top = back[x - 1][y];
                        int left = back[x][y - 1];
                        if(topLeft <= left && topLeft <= top){
                            path.add(topLeft);
                            checkX = x - 1;
                            checkY = y - 1;
                        }
                        else if(left <= topLeft && left <= top){
                            path.add(left);
                            checkY = y - 1;
                        }
                        else{
                            path.add(top);
                            checkX = x - 1;
                        }
                    }
                }
                //System.out.println("x, y" + x + " " + y);
            }
        } 
        System.out.println("Empty String" + back[0][1]);
        System.out.println(path);
    }
    //figure out backtracking 
    //figure out memory save, use two linear arrays
    //a general method applicable to the search for similarities in the amino acid sequence of two proteins
    //needleman and wansch
}
