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
        String z;
        
        System.out.print("First word is: ");
        word1 = in.next();
        System.out.print("Second word is: ");
        word2 = in.next();
        int temp[][] = new int[2][word2.length()+1]; 
        
        //String word1 = args[0];
        //String word2 = args[1];
        EditDistance ED = new EditDistance();
        int dist = ED.minDistance(word1, word2);
        
        System.out.println("Edit Distance: " + dist);
        
        EditDistanceIterative IED = new EditDistanceIterative();
        int Itdist = IED.minDistance(word1, word2);
        
        System.out.println("Iterative Edit Distance: " + Itdist);
        
        EditDistanceMinBackTrack EDMTB = new EditDistanceMinBackTrack();
        int TBdist = EDMTB.minDistance(word1, word2);

        System.out.println("2 row Edit Distance: " + TBdist);
    }
}
