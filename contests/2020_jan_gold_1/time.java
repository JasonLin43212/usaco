import java.io.*;
import java.util.*;

public class time {
    
    public static int N;
    public static int M;
    public static long C;
    public static List<List<Integer>> edges = new ArrayList<>();
    public static long[] money;
    public static long[][] dp;
    public static int MAX_TIME = 1001;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("time.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Long.parseLong(st.nextToken());
        
        money = new long[N];
        dp = new long[N][MAX_TIME];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            money[i] = Long.parseLong(st.nextToken());
            edges.add(new ArrayList<>());
            Arrays.fill(dp[i], -1);
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            edges.get(b).add(a);
        }
        
//        System.out.println(edges);
        dp[0][0] = 0;
        long maxMoney = 0;
        for (int t = 1; t < MAX_TIME; t++) {
//            System.out.println(Arrays.deepToString(dp));
            for (int c = 0; c < N; c++) {
                for (int nextNode : edges.get(c)) {
                    if (dp[nextNode][t-1] >= 0) {
                        dp[c][t] = Math.max(dp[c][t], dp[nextNode][t-1] + money[c]);
                    }
                }
            }
            maxMoney = Math.max(maxMoney, dp[0][t] - C * t * t);
        }
        
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("time.out")));
		pw.println(maxMoney);
		pw.close();
	}
}