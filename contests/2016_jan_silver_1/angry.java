import java.io.*;
import java.util.*;

public class angry {
    
    public static int N;
    public static int K;
    public static int[] bales;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("angry.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		bales = new int[N];
		
		int minBale = Integer.MAX_VALUE;
		int maxBale = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
		    bales[i] = Integer.parseInt(br.readLine());
		    minBale = Math.min(minBale, bales[i]);
		    maxBale = Math.max(maxBale, bales[i]);
		}
		Arrays.sort(bales);
//		System.out.println(Arrays.toString(bales) + " " + minBale + " " + maxBale);
		
		int lo = 0;
		int hi = ((maxBale - minBale) / 2) + 1;
		for (hi++; lo < hi;) {
		    int mid = (lo + hi) / 2;
		    if (canDestroy(mid)) {
		        hi = mid;
		    } else {
		        lo = mid + 1;
		    }
//		    System.out.println(lo);
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
		pw.println(lo);
		pw.close();
	}
	
	public static boolean canDestroy(int power) {
	    int curIndex = 0;
	    int curEnd = bales[0] + 2 * power;
	    int numCows = 1;
	    for (int i = 0; i < N; i++) {
//	        System.out.println(i + " " + curIndex + " " + curEnd + " " + power);
	        if (bales[i] > curEnd) {
	            numCows++;
	            curIndex = i;
	            curEnd = bales[i] + 2 * power;
	        }
	        if (numCows > K) {
	            break;
	        }
	    }   
//	    System.out.println(numCows);
	    return numCows <= K;
	}
}