import java.io.*;
import java.util.*;

public class magicship {
    
    public static String directions = "URDL";
    public static int[][] deltas = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static int startX;
    public static int startY;
    public static int endX;
    public static int endY;
    public static int N;
    public static int[][] totalChange;
    
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		startX = Integer.parseInt(st.nextToken());
		startY = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		endX = Integer.parseInt(st.nextToken());
		endY = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(br.readLine());
		
		totalChange = new int[N+1][2];
		totalChange[0][0] = 0;
		totalChange[0][1] = 0;
		String wind = br.readLine();
		for (int i = 0; i < N; i++) {
		    int[] delt = deltas[directions.indexOf(wind.charAt(i))];
		    totalChange[i+1][0] = totalChange[i][0] + delt[0];
		    totalChange[i+1][1] = totalChange[i][1] + delt[1];
		}
//		System.out.println(Arrays.deepToString(totalChange));
		
		long lo = 0;
		long hi = 200000000000001L;
		for (hi++; lo < hi; ) {
		    long mid = (lo + hi) / 2;
		    if (canReach(mid)) {
		        hi = mid;
		    } else {
		        lo = mid + 1;
		    }
		}
		if (lo >= 200000000000001L) {
		    System.out.println(-1);
		} else {
		    System.out.println(lo);
		}
	}
	
	public static boolean canReach (long numDays) {
//	    System.out.println("num days:" + numDays);
	    long numCycles = numDays / N;
	    int numRemain = (int) (numDays % N);
	    long windX = startX + totalChange[N][0] * numCycles + totalChange[numRemain][0];
	    long windY = startY + totalChange[N][1] * numCycles + totalChange[numRemain][1];
//	    System.out.println(windX + " " + windY);
	    return Math.abs(windX - endX) + Math.abs(windY - endY) <= numDays;
	    
	}
}