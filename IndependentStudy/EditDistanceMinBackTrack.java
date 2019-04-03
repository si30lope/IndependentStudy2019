
/**
 * Only using 2 rows!!
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EditDistanceMinBackTrack
{
    //static int temp[][];
    public EditDistanceMinBackTrack() {

    }
    
    
    public static int minDistance(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();
        int numRow = 2;
        //int[][] temp = new int[length1 + 1][length2 + 1];
        int longer = Math.max(length1,length2);
        int[] current = new int[longer + 1];
        int[] previous = new int[longer + 1];
        
        for (int i = 0; i < length1; i++) {
            char c1 = word1.charAt(i);
            
            //System.out.println("C1 at " + i + " is: " + c1);
            for (int j = 0; j < length2; j++) {
                char c2 = word2.charAt(j);
                //if last two chars equal
                //System.out.println("C2 at " + j + " is: " + c2);
                if (i == 0 && j == 0 && c2 == c1) current[0] = 0;
                else if (i == 0 && j == 0 && c2 != c1) current[0] = 1;
                else if (i == 0 && c2 == c1) current[j] = current[j-1];
                else if (i == 0 && c2 != c1) current[j] = current[j-1] + 1;
                else{
                    if (i > 0 && j > 0){
                        for(int k = 0; k < length2; k++) {
                            previous[k] = current[k];
                            //current[k] = temp[i][k];
                        }
                        int min = 0;
                        if (c1 == c2) {
                            //update temp value for +1 length
                            //temp[i + 1][j + 1] = temp[i][j];
                            //System.out.println("temp[i][j]: " + temp[i][j]);
                            current[j] = current[j-1];
                        } 
                        else {
                            //min = Math.min(temp[i-1][j-1], temp[i-1][j]);
                            min = Math.min(previous[j-1], previous[j]);
                            min = Math.min(min, current[j-1]);
                            min = min + 1;
                            //temp[i][j] = min;
                            current[j] = min;
                        }
                    }
                }                
            }
            
        }
        //return temp[length1][length2];
        return current[length2-1];
    }

}
