import java.io.*;
import java.util.*;

public class maxsubarraysum {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		long[] nums = new long[N];
		for (int i = 0 ; i < N; i++) {
		    nums[i] = Integer.parseInt(st.nextToken());
		}
		long[] prefix = new long[N+1];
		for (int i = 1; i <= N; i++) {
            prefix[i] = prefix[i-1] + nums[i-1];   
		}
				
//        System.out.println(Arrays.toString(prefix));
        long minPrefix = Long.MAX_VALUE;
        long maxSum = Long.MIN_VALUE;
        for (int i = 0; i <= N; i++) {
//            System.out.println(prefix[i] + " - " + minPrefix);
            maxSum = Math.max(maxSum, prefix[i] - minPrefix);
            minPrefix = Math.min(minPrefix, prefix[i]);
            
        }
        System.out.println(maxSum);
	}
}