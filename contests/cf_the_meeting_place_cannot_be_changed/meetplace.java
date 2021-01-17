import java.io.*;
import java.util.*;

public class meetplace {
    
    public static int N;
    public static int[] locs;
    public static int[] speed;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		locs = new int[N];
		speed = new int[N];
		for (int i = 0; i < N; i++) {
		    locs[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
		    speed[i] = Integer.parseInt(st.nextToken());
		}
		
//		System.out.println(Arrays.toString(locs) + " "  + Arrays.toString(speed));
		
		long lo = 0;
		long hi = 1000000000 / 2;
		for (hi++; lo < hi;) {
		    long mid = (lo + hi) / 2;
		    if (canMeet((double) mid)) {
		        hi = mid;
		    } else {
		        lo = mid + 1;
		    }
		}
		long smallLo = 0;
		long smallHi = 2000000000;
		for (smallHi++; smallLo < smallHi;) {
            long smallMid = (smallLo + smallHi) / 2;
            if (canMeet((double) lo + (double) (smallMid - 1000000000d) / 1000000000d )) {
                smallHi = smallMid;
            } else {
                smallLo = smallMid + 1;
            }
        }
		System.out.println(((double) lo + (double)(smallLo - 1000000000d) / 1000000000d));
	}
	
	public static boolean canMeet(double time) {
//	    System.out.println(time);
	    double topBound = Double.MAX_VALUE;
	    double bottomBound = Double.MIN_VALUE;
	    for (int i = 0; i < N; i++) {
	        topBound = Math.min(locs[i] + time * speed[i], topBound);
	        bottomBound = Math.max(locs[i] - time * speed[i], bottomBound);
	    }
	    return topBound >= bottomBound;
	}
}