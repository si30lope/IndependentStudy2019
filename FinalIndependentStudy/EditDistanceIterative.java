
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
        }
        
        for (int j = 0; j <= length2; j++) {
            temp[0][j] = j;
        }
        //iterate though, and check last char
        for (int i = 0; i < length1; i++) {
            char c1 = word1.charAt(i);
            for (int j = 0; j < length2; j++) {
                char c2 = word2.charAt(j);
                //if last two chars equal
                if (c1 == c2) {
                    //update temp value for +1 length
                    temp[i + 1][j + 1] = temp[i][j];
                } else {
                    
                    int replace = temp[i][j] + 1;
                    int insert = temp[i][j + 1] + 1;
                    int delete = temp[i + 1][j] + 1;
                    int min = 0;
                    if (replace > insert) {
                        min = insert;
                    }
                    else min = replace;
                    if (delete < min) {
                        min = delete;
                    }
                    temp[i + 1][j + 1] = min;
                }
            }
       }
        return temp[length1][length2];
    }
}
