import java.io.*;
import java.util.*;

public class minimizecoin {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] coins = new int[N];
		for (int i = 0; i < N; i++) {
		    coins[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[X+1];
		dp[0] = 0;
		
		for (int i = 1; i <= X; i++) {
		    int curMin = X+1;
		    for (int j = 0; j < N; j++) {
		        if (i - coins[j] >= 0) {
		            curMin = Math.min(curMin, dp[i-coins[j]] + 1);
		        }
		    }
		    dp[i] = curMin;
		}
		
		PrintWriter pw = new PrintWriter(System.out);
//		System.out.println(Arrays.toString(dp));
		if (dp[X] == X + 1) {
		    pw.println("-1");
		} else {
		    pw.println(dp[X]);
		}
		pw.close();
	}
}