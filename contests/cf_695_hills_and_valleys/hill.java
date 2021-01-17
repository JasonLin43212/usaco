import java.io.*;
import java.util.*;

public class hill {
    
    public static int n;
    public static long[] nums;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		int t = Integer.parseInt(br.readLine());
		for (int x = 0; x < t; x++) {
		    n = Integer.parseInt(br.readLine());
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    nums = new long[n];
		    for (int i = 0; i < n; i++) {
		        nums[i] = Long.parseLong(st.nextToken());
		    }
		    pw.println(solve());
		}
		
		pw.close();
		
	}
	
	public static int isHill(int i) {
	    return (i > 0 && i < n - 1 && nums[i] > nums[i+1] && nums[i] > nums[i-1]) ?
	            1 : 0;
	}
	
	public static int isValley(int i) {
        return (i > 0 && i < n - 1 && nums[i] < nums[i+1] && nums[i] < nums[i-1]) ?
                1 : 0;
    }
	
	public static long solve() {
	    int numPeaks = 0;
	    int[] peak = new int[n];
	    for (int i = 1; i < n - 1; i++) {
	        if (isHill(i) + isValley(i) > 0) {
	            numPeaks++;
	            peak[i] = 1;
	        }
	    }
	    
	    long numRemove = 0;
	    for (int i = 1; i < n - 1; i++) {
	        int prevPeak = peak[i-1] + peak[i] + peak[i+1];
	        long temp = nums[i];
	        nums[i] = nums[i-1];
	        int toRemove = isHill(i-1) + isHill(i) +
                    isHill(i+1) + isValley(i-1) + isValley(i) + isValley(i+1);
	        numRemove = Math.max(numRemove, prevPeak - toRemove);
	        nums[i] = nums[i+1];
	        toRemove = isHill(i-1) + isHill(i) +
                    isHill(i+1) + isValley(i-1) + isValley(i) + isValley(i+1);
	        numRemove = Math.max(numRemove, prevPeak - toRemove);
            nums[i] = temp;
            
	    }
	    return numPeaks - numRemove;
	}
}