import java.io.*;
import java.util.*;

public class replaceelement {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		PrintWriter pw = new PrintWriter(System.out);
		for (int x = 0; x < t; x++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    int n = Integer.parseInt(st.nextToken());
		    int d = Integer.parseInt(st.nextToken());
		    st = new StringTokenizer(br.readLine());
		    int[] nums = new int[n];
		    for (int i = 0; i < n; i++) {
		        nums[i] = Integer.parseInt(st.nextToken());
		    }
		    
//		    System.out.println(n + " " + d + " " + Arrays.toString(nums));
		    pw.println(solve(n, d, nums));
		    
		}
		pw.close();
	}
	
	public static String solve(int n, int d, int[] nums) {
	    int min1 = -1;
	    int min2 = -1;
	    int numReplace = 0;
	    for (int i = 0; i < n; i++) {
	        if (nums[i] > d) {
	            numReplace++;
	        }
	        if (min1 == -1 || nums[i] < min1) {
	            min2 = min1;
	            min1 = nums[i];
	        } else if (min2 == -1 || nums[i] < min2) {
	            min2 = nums[i];
	        }
	    }
	    
//	    System.out.println(min1 + " " + min2);
	    if (min1 + min2 <= d || numReplace == 0) {
	        return "YES";
	    } else {
	        return "NO";
	    }
	    
	}
}