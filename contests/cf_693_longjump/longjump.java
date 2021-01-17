import java.io.*;
import java.util.*;

public class longjump {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
		    int n = Integer.parseInt(br.readLine());
		    long[] nums = new long[n];
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    for (int j = 0; j < n; j++) {
		        nums[j] = Long.parseLong(st.nextToken());
		    }
		    System.out.println(maxScore(n, nums));
		}
	}
	
	public static long maxScore(int n, long[] nums) {
	    long[] scores = new long[n];
	    long maxScore = 0;
	    for (int i = n-1; i >= 0; i--) {
	        long curScore = nums[i];
	        long nextIndex = curScore + (long) i;
	        if (nextIndex < n) {
	            curScore += scores[(int)nextIndex];
	        }
	        scores[i] = curScore;
	        maxScore = Math.max(curScore, maxScore);
	    }
	    return maxScore;
	}
}