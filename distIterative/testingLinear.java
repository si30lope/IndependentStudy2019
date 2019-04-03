
/**
 * Write a description of class testingLinear here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class testingLinear
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class testingLinear
     */
    public testingLinear()
    {
        // initialise instance variables
        x = 0;
    }

    public static int minDistance(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();
        //int[][] temp = new int[length1 + 1][length2 + 1];
        int longer = Math.max(length1, length2);
        int[] current = new int[longer + 1];
        int[] previous = new int[longer + 1];

        
        for (int i = 1; i <= longer; i++) {
            //temp[i][0] = i;
            previous[i-1] = i;
            //System.out.println(i);
        }
        
        System.out.println();
        //iterate though, and check last char
        for (int i = 1; i <= length1; i++) {
            char c1 = word1.charAt(i-1);
            current[0] = i;
            for (int j = 1; j <= length2; j++) {
                char c2 = word2.charAt(j-1);
                //if last two chars equal
                System.out.println("x, y, i, j: " + c1 + ", " + c2 + ", " + i + ", " + j);
                
                int min = 0;
                if (c1 == c2) {
                    //update temp value for +1 length
                    current[j] = previous[j-1];
                } else {
                    int left = current[j-1];
                    int top = previous[j];
                    int topLeft = previous[j-1];
                    min = Math.min(topLeft, top);
                    min = Math.min(min,left);
                    min = min + 1;
                    //temp[i][j] = min;
                    current[j] = min;
                }
                //System.out.println("x, y, val, c1, c2 " + i + " " + j + " " + temp[i][j] + " " + c1 + " " + c2);
                //System.out.println(temp[i][j]);
                System.out.println(current[j]);
                int[] temp = current;
                current = previous;
                previous = temp;
            }
     
            //previous = current.clone();
        }

        //System.out.println("length1, length2 " + length1 + " " + length2);
        return current[longer];
    }
}
