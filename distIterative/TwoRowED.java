
/**
 * Write a description of class TwoRowED here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TwoRowED
{
    public TwoRowED() {
        
    }
    
    public static int minDistance(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();
        //int[][] temp = new int[length1 + 1][length2 + 1];
        int longer = Math.max(length1, length2);
        int[] current = new int[longer + 1];
        int[] previous = new int[longer + 1];

        
        for (int i = 0; i <= longer; i++) {
            previous[i] = 0;
            //System.out.println(i);
        }
        
        System.out.println();
        //iterate though, and check last char
        for (int i = 1; i <= length1; i++) {
            char c1 = word1.charAt(i-1);
            //current[0] = i;
            for (int j = 1; j <= length2; j++) {
                char c2 = word2.charAt(j-1);
                //if last two chars equal
                //System.out.println("x, y, i, j: " + c1 + ", " + c2 + ", " + i + ", " + j);
                
                int min = 0;
                if (c1 == c2) {
                    //update temp value for +1 length
                    current[j] = previous[j-1];
                } else {
                    int left = current[j-1];
                    int top = previous[j];
                    int topLeft = previous[j-1];
                    if (j==1 && i==1) {
                        min = top;
                    }
                    else if (i == 1) {
                        min = left;
                    }
                    else if (j == 1) {
                        min = top;
                    }
                    else{
                        min = Math.min(topLeft, top);
                        min = Math.min(min,left);
                    }
                    min = min + 1;
                    current[j] = min;
                }
                
                
                //System.out.println(current[j]);
            }
     
            previous = current.clone();
        }

        //System.out.println("length1, length2 " + length1 + " " + length2);
        
        //return temp[length1][length2];
        return current[longer];
    }
}
