/*
ID: jasonli7
LANG: JAVA
TASK: money
*/
import java.io.*;
import java.util.*;

public class money {

    public static int V;
    public static int N;
    public static long[][] memo;
    public static List<Integer> coins;

    public static long subproblem(int i, int sum) {
        if (memo[i][sum] != -1) {
            return memo[i][sum];
        }

        // Base Case
        if (sum == 0) {
            return 1;
        }
        if (i == 0) {
            return sum % coins.get(i) == 0 ? 1 : 0;
        }

        // Recursive Case
        long total = 0;
        for (int d = 0; d <= sum / coins.get(i); d++) {
            total += subproblem(i-1, sum - coins.get(i) * d);
        }
        memo[i][sum] = total;
        return total;
    }

    public static void main (String [] args) throws IOException {
        String fileName = "money";
        BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        memo = new long[V][N+1];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < N+1; j++) {
                memo[i][j] = -1;
            }
        }

        coins = new ArrayList<>();

        while (coins.size() < V) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                coins.add(Integer.parseInt(st.nextToken()));
            }
        }
        long solution = subproblem(V-1, N);
        // System.out.println(solution);
        // System.out.println(Arrays.deepToString(memo));
        pw.println(solution);
        pw.close();
    }
}
