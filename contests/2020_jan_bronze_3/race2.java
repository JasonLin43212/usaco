import java.io.*;
import java.util.*;

public class race {
    public static int K;
    
	public static void main (String [] args) throws IOException {
	    /**
	     * Some idea:
	     * Increase at first, then switch to steady
	     * - never want to increase, steady/decrease and then increase again?
	     * - after steady doesnt work, try decrease
	     * - never want to steady after decrease???
	     */
		BufferedReader br = new BufferedReader(new FileReader("race.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("race.out")));

		
		for (int i = 0; i < N; i++) {
		    pw.println(minTime(Integer.parseInt(br.readLine())));
		}
		pw.close();
	}
	
	public static int minTime(int X) {
	    return minHelper(0, 0, 0, X, 1);
	}
	
	// state 0: increase, 1: steady, -1: decreasing
	public static int minHelper(int curDist, int curSpeed, int time, int X, int state) {
	    System.out.println(curSpeed +  " "+ curDist);
	    if (curSpeed < 0) {
	        return Integer.MAX_VALUE;
	    }
	    if (curDist >= K) {
	        if (curSpeed <= X) {
	            return time; // Found one
	        } else {
	            return Integer.MAX_VALUE; // Didn't find one
	        }
	    }
	    
	    int min = minHelper(curDist + curSpeed + state, curSpeed + state, time + 1, X, state);
	    if (Integer.MAX_VALUE == min && state >= 0) {
	        min = minHelper(curDist + curSpeed + state - 1, curSpeed + state -1, time + 1, X, state - 1);
	        if (Integer.MAX_VALUE == min && state == 1) {
	            min = minHelper(curDist + curSpeed + state - 2, curSpeed + state -2, time + 1, X, state - 2);
	        }
	    }
	    return min;
	    
	}
}