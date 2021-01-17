import java.io.*;
import java.util.*;

public class maximummedian {
    
    public static int N;
    public static int K;
    public static int[] nums;
    
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
        
//        System.out.println(Arrays.toString(nums));
        long lo = 1;
        long hi = 10000000000l;
        
        for (lo --; lo < hi; ) {
            long mid = (lo + hi + 1) / 2;
            if (f(mid)) lo = mid; else hi = mid - 1;
        }
        
        System.out.println(lo);
	}
	
	public static boolean f(long x) {
	    long numOps = 0;
	    for (int i = (N-1)/2; i < N; i++) {
	        numOps += Math.max(0, x - nums[i]);
	    }
	    return numOps <= K;
	}
}