// Java program to illustrate  
// ThreadPool 
import java.text.SimpleDateFormat;  
import java.util.Date; 
import java.util.concurrent.ExecutorService; 
import java.util.concurrent.Executors; 
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// Task class to be executed (Step 1) 
class Task implements Runnable 
{ 
    private String w1; 
    private String w2; 

    private int len1;
    private int len2;

    static BlockingQueue<Pair<Integer, Integer>> queue = new LinkedBlockingQueue<Pair<Integer, Integer>>();
    //static ArrayList<Pair> outBound = new ArrayList<Pair>();
    static List outBound = Collections.synchronizedList(new ArrayList<Pair>());

    static int[][] grid;
    static ArrayList<Integer> list = new ArrayList<Integer>();
    // remember to subtract 1 when trying to get a pair due to the arraylist starting at 0 and adding into 0 and 1, whereas we start at 0 and add 2
    static int counter = 0;

    char c1;
    char c2;
    static int biggest = -500;

    public Task(String word1, String word2) 
    { 
        w1 = word1;
        w2 = word2;
        len1 = w1.length();
        len2 = w2.length();
    } 

    // public synchronized Pair dequeue(){
    // Pair pair = null;
    // while(queue.isEmpty()){
    // try{
    // wait();
    // }catch(InterruptedException e){
    // System.out.println("Something went wrongin dequeue");
    // }
    // }
    // pair = queue.remove();
    // return pair;

    // }
    // Prints task name and sleeps for 1s 
    // This Whole process is repeated 5 times 
    public void run() 
    { 
        while(!queue.isEmpty()) {
            try
            { 
                int matchScore = 3;
                int mistmatchScore = -2;
                int gapScore = -2;

                int diag = 0;
                int up = 0;
                int left = 0;

                //iterate though, and check last char
                int rightX = 0;
                int rightY = 0;
                int belowX = 0;
                int belowY = 0;
                int currX = 0;
                int currY = 0;

                Pair pair = queue.remove();
                outBound.add(pair);
                int pos1 = Integer.parseInt(pair.first().toString());
                int pos2 = Integer.parseInt(pair.second().toString());
                if (pos1 > len1) {
                    continue;
                }
                if (pos2 > len2) {
                    continue;
                }
                System.out.println("pos1 = "+ pos1);
                System.out.println("pos2 = "+ pos2);
                // for(int i = 0; i < counter; i+=2) {
                // rightX = list.get(i)-1;
                // belowY = list.get(i)-1;
                // currX = list.get(i);
                // currY = list.get(i+1);
                // if (list.size() <= i) {
                // rightY = list.get(i)+1;
                // belowX = list.get(i)+1;
                // }
                // }

                // for (int i = 1; i < list.size(); i+=2) {
                // c1 = w1.charAt(i-1);
                // c2 = w2.charAt(i);
                // }
                c1 = w1.charAt(pos1);
                c2 = w2.charAt(pos2);

                //if last two chars equal
                diag = grid[pos1-1][pos2-1];
                //System.out.println(diag);
                up = grid[pos1-1][pos2];
               // System.out.println(up);
                left = grid[pos1][pos2-1];
               // System.out.println(left);
               
                Pair prevUp = new Pair(pos1 -1, pos2);
                Pair prevLeft = new Pair(pos1, pos2-1);
               for( int i = 0; i < outBound.size(); i++) {
                     //System.out.println("Outbound at pos " + i + ": " + outBound.get(i));
                }
                //System.out.println("Current pair = " + pair);
                //System.out.println("PrevUp pair = " + prevUp);
                //System.out.println("PrevLeft pair = " + prevLeft);
                if(outBound.contains(prevUp) && outBound.contains(prevLeft)){
                    //System.out.println("Inside if statement");
                    int max = 0;
                    System.out.println(c1 + " " + c2);
                    if (c1 == c2) {
                        //update grid value for +1 length
                        // grid[i + 1][j + 1] = grid[i][j];
                        
                        System.out.println("they're equal");
                        if((diag + matchScore) < 0){
                            grid[pos1][pos2] = 0;
                            System.out.println("diag + match < 0");
                        }
                        else {
                            grid[pos1][pos2] = diag + matchScore;
                            System.out.println("diag + match");
                        }
                    } else {
                        max = Math.max(up + gapScore, diag + mistmatchScore);
                        max = Math.max(max,left + gapScore);
                        System.out.println("max: " + max);
                        if(max < 0){
                            grid[pos1][pos2] = 0;
                            System.out.println("max < 0");
                        }
                        else{
                            grid[pos1][pos2] = max;
                        }
                    }

                    if(grid[pos1][pos2] >= biggest){
                        biggest = grid[pos1][pos2];
                        System.out.println("grid[pos1][pos2] >= biggest");
                    }
                    //used.add(i);
                    //used.add(j);

                    //System.out.print("Used the following squares: ");
                    //for(int i = 0; i < used.size(); i+=2){
                    //    System.out.print("["+ used.get(i) + " " + used.get(i+1) + "] ");
                    //}
                    //  System.out.print("\n");
                    // System.out.println("length1, length2 " + length1 + " " + length2);

                    // list.add(rightX);
                    // counter++;
                    // list.add(rightY);
                    // counter++;
                    // list.add(belowX);
                    // counter++;
                    // list.add(belowY);
                    // counter++;
                     Pair right = new Pair(pos1, pos2 + 1);
                     Pair below = new Pair(pos1 + 1, pos2);
                    if (pos1 < len1) {
                        queue.add(right);
                    }
                    if (pos2 < len2) {
                            queue.add(below);
                     }
                } 
            } 
            catch(Exception e){ 
                e.printStackTrace();
            }
        }
        
        // for(int i = 0; i<=w1.length(); i++){
            // for(int j = 0; j<=w2.length();j++){
                // System.out.print(grid[i][j] + " ");
            // }
            // System.out.println();
        // }
    }
    
    public static void initialize(String word1, String word2) {
        
        int length1 = word1.length();
        int length2 = word2.length();
        //ArrayList<Integer> used = new ArrayList<Integer>();
        grid = new int[length1+1][length2+1];
        
        int init = 0;
        // for (int i = 0; i <= length1; i++) {
            // grid[i][0] = init;
            // //System.out.println(init);
        // }

        // for (int j = 0; j <= length2; j++) {
            // grid[0][j] = init;
            // //System.out.print(init);
        // }
        for (int i = 0; i < length1; i++) {
            for (int j = 0; j < length2; j++) {
                grid[i][j] = init;
            }
        }

        for(int i =1; i<length1;i++){
            Pair pair = new Pair(i, 1);
            queue.add(pair);
        }

        for(int j = 1; j < length2; j++){
            Pair pair = new Pair(1, j);
            queue.add(pair);
            //counter++;
        }

        for(int i = 0; i <= length1;i++){
            Pair pair = new Pair(i,0);
            outBound.add(pair);
        }

        for(int i = 0; i <= length2;i++){
            Pair pair = new Pair(0,i);
            outBound.add(pair);
        }
        //list.add(1);
        //counter++;
        System.out.println(queue.toString());
    }

    public static int answer(){
        return biggest;
    }
    
    public static int[][] matrix(){
        return grid;
    }
} 