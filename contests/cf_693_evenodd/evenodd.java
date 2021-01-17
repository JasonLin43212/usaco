import java.io.*;
import java.util.*;

public class evenodd {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
		    int n = Integer.parseInt(br.readLine());
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    Long[] nums = new Long[n];
		    for (int j = 0; j < n; j++) {
		        nums[j] = Long.parseLong(st.nextToken());
		    }
		    Arrays.sort(nums, (x, y) -> Long.compare(y, x));
		    System.out.println(winner(nums));
		}
	}
	
	public static String winner(Long[] nums) {
	    long[] scores = new long[2]; // 0 = alice, 1 = bob
	    for (int i = 0; i < nums.length; i++) {
	        if (i % 2 == 0) { // alice
	            if (nums[i] % 2 == 0) {
	                scores[0] += nums[i];
	            }
	        } else { // bob
	            if (nums[i] % 2 == 1) {
                    scores[1] += nums[i];
                }
	        }
	    }
	    if (scores[0] > scores[1]) {
	        return "Alice";
	    } else if (scores[0] < scores[1]) {
	        return "Bob";
	    } else {
	        return "Tie";
	    }
	}
}