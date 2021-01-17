import java.io.*;
import java.util.*;

public class gridpath {
    
    public static long MOD = (long) 1e9 + 7;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[][] isTrap = new boolean[N][N];
		for (int r = 0; r < N; r++) {
		    String line = br.readLine();
		    for (int c = 0; c < N; c++) {
		        if (line.charAt(c) == '*') {
		            isTrap[r][c] = true;
		        }
		    }
		}
		
//		System.out.println(Arrays.deepToString(isTrap));
		long[][] dp = new long[N][N];
		dp[0][0] = isTrap[0][0] ? 0 : 1;
		
		for (int r = 0; r < N; r++) {
		    for (int c = 0; c < N; c++) {
		        if (isTrap[r][c]) {
		            continue;
		        }
		        
		        if (r > 0) {
		            dp[r][c] += dp[r-1][c];
		        }
		        dp[r][c] %= MOD;
		        if (c > 0) {
		            dp[r][c] += dp[r][c-1] ;
		        }
		        dp[r][c] %= MOD;
		    }
		}
		
		PrintWriter pw = new PrintWriter(System.out);
		pw.println(dp[N-1][N-1]);
		pw.close();
	}
}