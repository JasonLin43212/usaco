import java.io.*;
import java.util.*;

public class convention {
    
    public static int N;
    public static int M;
    public static int C;
    public static int[] cows;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("convention.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        
        cows = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cows[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cows);
        
        int lo = 0;
        int hi = (int) 1e9;
        for (hi++; lo < hi;) {
            int mid = (lo + hi) / 2;
            if (f(mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
//        System.out.println(f(3));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));
		pw.println(lo);
		pw.close();
	}
	
	public static boolean f(int x) {
//	    System.out.println(x);
	    int numBus = 0;
	    int firstCow = cows[0];
	    int numCows = 1;
	    for (int i = 1; i < N; i++) {
	        if (numCows == C || cows[i] - firstCow > x) {
	            numBus++;
	            firstCow = cows[i];
	            numCows = 1;
	        } else {
	            numCows++;
	        }
	    }
	    numBus++;
//	    System.out.println(numBus);
	    if (numBus > M) {
	        return false;
	    }
	    return true;
	}
}