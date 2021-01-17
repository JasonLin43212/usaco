import java.io.*;
import java.util.*;

public class subarraysums1 {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] nums = new int[N];
		for (int i = 0; i < N; i++) {
		    nums[i] = Integer.parseInt(st.nextToken());
		}
		
//		System.out.println(Arrays.toString(nums));
		int numAnswer = 0;
		
		int p1 = 0;
		int p2 = 0;
		int curSum = nums[0];
		while (!((p2 == N-1 && curSum < x) || (p1 == N || p2 == N))) {
		    if (curSum > x) {
		        if (p1 == p2) {
		            p1++;
		            p2++;
		            curSum += nums[p1] - nums[p1-1];
		        } else {
		            p1++;
		            curSum -= nums[p1-1];
		        }
		    } else if (curSum < x) {
		        p2++;
		        curSum += nums[p2];
		    } else {
		        p1++;
		        curSum -= nums[p1-1];
		        numAnswer++;
		    }
		}
		System.out.println(numAnswer);
	}
}