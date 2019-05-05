
/**
 * Write a description of class Waterman here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmithWaterman
{
    public static int waterMan(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();
        //ArrayList<Integer> used = new ArrayList<Integer>();
        int[][] temp = new int[length1 + 1][length2 + 1];
        
        int matchScore = 3;
        int mistmatchScore = -2;
        int gapScore = -2;
        
        int init = 0;
        for (int i = 0; i <= length1; i++) {
            temp[i][0] = init;
            //System.out.println(init);
        }
        
        for (int j = 0; j <= length2; j++) {
            temp[0][j] = init;
            //System.out.print(init);
        }
        
        int diag = 0;
        int up = 0;
        int left = 0;
        
        int biggest = 0;
        //iterate though, and check last char
        for (int i = 1; i <= length1; i++) {
            char c1 = word1.charAt(i-1);
            for (int j = 1; j <= length2; j++) {
                char c2 = word2.charAt(j-1);
                //if last two chars equal
                diag = temp[i-1][j-1];
                up = temp[i-1][j];
                left = temp[i][j-1];
                
                int max = 0;
                if (c1 == c2) {
                    //update temp value for +1 length
                    // temp[i + 1][j + 1] = temp[i][j];
                    if((diag + matchScore) < 0){
                        temp[i][j] = 0;
                    }
                    else {
                        temp[i][j] = diag + matchScore;
                    }
                } else {
                    max = Math.max(up + gapScore, diag + mistmatchScore);
                    max = Math.max(max,left + gapScore);
                    
                    if(max < 0){
                        temp[i][j] = 0;
                    }
                    else{
                        temp[i][j] = max;
                    }
                }
                
                if(temp[i][j] >= biggest){
                    biggest = temp[i][j];
                }
                //used.add(i);
                //used.add(j);
            }
        }

        //System.out.print("Used the following squares: ");
        //for(int i = 0; i < used.size(); i+=2){
        //    System.out.print("["+ used.get(i) + " " + used.get(i+1) + "] ");
        //}
        //  System.out.print("\n");
        // System.out.println("length1, length2 " + length1 + " " + length2);
        
        return biggest;
    }
}
