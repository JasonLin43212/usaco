import java.io.*;
import java.util.*;

public class socdist {
    
    public static int N;
    public static int M;
    public static Long[][] intervals;
    
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("socdist.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		intervals = new Long[M][2];
		
		long maxPoint = 0;
		for (int i = 0; i < M; i++) {
		    st = new StringTokenizer(br.readLine());
		    intervals[i][0] = Long.parseLong(st.nextToken());
		    intervals[i][1] = Long.parseLong(st.nextToken());
		    maxPoint = Math.max(maxPoint, Math.max(intervals[i][0], intervals[i][1]));
		}
		Arrays.sort(intervals, (a, b) -> Long.compare(a[0], b[0]));
//		System.out.println(Arrays.deepToString(intervals));
		
		long lo = 0;
		long hi = maxPoint;
		for (lo--; lo < hi;) {
		    long mid = (lo + hi + 1) / 2;
		    if (f(mid)) {
		        lo = mid;
		    } else {
		        hi = mid - 1;
		    }
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("socdist.out")));
		pw.println(lo);
		pw.close();
	}
	
	public static boolean f(long d) {
	    int numCows = 1;
	    
	    int intNum = 0;
	    long start = intervals[intNum][0];
        long end = intervals[intNum][1];
        
        long prevPos = start;
//        System.out.println("NUM: "  + d);
	    for (int i = 1; i < N; i++) {
	       
	        long nextPos = prevPos + d;
//	        System.out.println(start + " " + end + " " +  nextPos + " " + d);
	        if (start <= nextPos && nextPos <= end) {
	            prevPos = nextPos;
	            numCows++;
	        } else {
	            
	            intNum++;
	            if (intNum == M) {
	                break;
	            }
	            start = intervals[intNum][0];
	            end = intervals[intNum][1];
	            prevPos = Math.max(start - d, nextPos - d);
	            i--;
	        }
	    }
//	    System.out.println(numCows == N);
	    return numCows == N;
	}
}