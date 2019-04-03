import java.util.*;
/**
 * Write a description of class tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tester
{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String word1;
        String word2;
      
        System.out.print("First word is: ");
        word1 = in.next();
        System.out.print("Second word is: ");
        word2 = in.next();
        int temp[][] = new int[2][word2.length()+1]; 
        
        EditDistanceIterative EDMTB = new EditDistanceIterative();
        int TBdist = EDMTB.minDistance(word1, word2);

        System.out.println("Track Back Edit Distance: " + TBdist);
        
        TwoRowED TRED = new TwoRowED();
        int twoRow = TRED.minDistance(word1, word2);
       
        System.out.println("Two Row Edit Distance: " + twoRow);
        
        testingLinear TL = new testingLinear();
        int linear = TL.minDistance(word1, word2);
       
        System.out.println("Two Row Edit Distance: " + linear);
    }
}
