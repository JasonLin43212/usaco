import java.io.*;
import java.util.*;

public class dicecombo {
    
    public static long MOD = (long) (1e9 + 7);
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[] ways = new long[N+1];
		// ways[i] is the number of ways to construct sum i
		for (int i = 1; i <= N; i++) {
		    long curWays = 0;
		    for (int j = 1; j <= 6; j++) {
		        if (i - j < 0) {
		            break;
		        } else {
		            if (i == j) {
		                curWays++;
		            }
		            curWays += ways[i - j];
		            curWays %= MOD;
		        }
		    }
		    ways[i] = curWays % MOD;
		}
//		System.out.println(Arrays.toString(ways));
		PrintWriter pw = new PrintWriter(System.out);
		pw.println(ways[N]);
		pw.close();
	}
}