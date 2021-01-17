import java.io.*;
import java.util.*;

public class outofplace {
    public static int N;
    
	public static void main (String [] args) throws IOException {
	    /**
	     * Store forward and reversed version of cows.
	     * Continuously loop through the list and swap when there are irregularities
	     * keeping track of same cows to do big swaps
	     * Do this for both forward and reverse and take min.
	     */
		BufferedReader br = new BufferedReader(new FileReader("outofplace.in"));
		N = Integer.parseInt(br.readLine());
		int[] cows = new int[N];
		int[] rcows = new int[N];
		for (int i = 0; i < N; i++) {
		    int height = Integer.parseInt(br.readLine());
		    cows[i] = height;
		    rcows[N-1-i] = -height;
		}
		
		int solution = Math.min(minSwaps(cows), minSwaps(rcows));
		
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("outofplace.out")));
		pw.println(solution);
		pw.close();
	}
	
	public static int minSwaps(int[] cowList) {
	    int numSwaps = 0;
	    int swapPos = 0;
	    for (int i = 0; i < N - 1; i++) { // Do swaps n-1 times at most
	        boolean hasSwapped = false;
	        for (int j = 0; j < N - 1; j++) {
	            if (cowList[j] > cowList[j+1]) {
	                int temp = cowList[j+1];
	                cowList[j+1] = cowList[swapPos];
	                cowList[swapPos] = temp;
	                numSwaps++;
	                hasSwapped = true;
	                break;
	            } else if (cowList[j] < cowList[j+1]) {
	                swapPos = j+1;
	            }
	        }
	        
	        if (!hasSwapped) {
	            break;
	        }
	    }
	    return numSwaps;
	}
}