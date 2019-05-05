import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.concurrent.TimeUnit.*;
/**
 * Write a description of class Waterman here.
 *
 * Sara, Jon, Arjol
 * @version (a version number or a date)
 */
public class ParallelSW
{
    static final int MAX_THREADS = 16;

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();

        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
        
        Scanner in = new Scanner(System.in);

        int cores = Runtime.getRuntime().availableProcessors();

        //ThreadPoolExecutor pool = new ThreadPoolExecutor(cores, cores, 60, TimeUnit.SECONDS, queue);
        
        String word1;
        String word2;

        System.out.print("First word is: ");
        word1 = in.next();
        System.out.print("Second word is: ");
        word2 = in.next();

        Task.initialize(word1, word2);
        for (int i =0; i <= cores; i++) {
            Runnable r = new Task(word1, word2); 
            pool.execute(r);
            //System.out.println("Thread: " + pool.toString());
        }
        pool.shutdown();
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }

        System.out.println("The global alignment is: " + Task.answer());
        int[][] grid = Task.matrix();
        System.out.println("Printing matrix: ");
        for(int i = 0; i<=word1.length(); i++){
            for(int j = 0; j<=word2.length();j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

}
