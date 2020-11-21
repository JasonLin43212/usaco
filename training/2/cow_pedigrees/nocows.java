/*
ID: jasonli7
LANG: JAVA
TASK: nocows
*/
import java.io.*;
import java.util.*;

class nocows {

    static int N;
    static int K;
    static long[][] dp;

    public static long solve(int N, int K) {
        if (dp[N][K] != -1) {
            return dp[N][K];
        }
        if ((N % 2 == 0) || (N < 1) || (K < 1) || (2*K - 1 > N)) {
            return dp[N][K] = 0;
        }
        if (N == 1) {
            if (K == 1) {
                return dp[N][K] = 1;
            }
            return dp[N][K] = 0;
        }

        dp[N][K] = 0;
        for (int i=1; i<N-1; i+=2) { // i is the number of nodes on the left side
            int rightNodes = N - 1 - i;
            for (int j=0; j<K-1; j++) { // j is the height of the subtree that does not go all the way
                dp[N][K] += solve(i, j) * solve(rightNodes, K - 1); // Right subtree goes all the way
                dp[N][K] += solve(i, K - 1) * solve(rightNodes, j); // Left subtree goes all the way
            }
            dp[N][K] += solve(i, K - 1) * solve(rightNodes, K - 1); // Both subtrees goes all the way
        }
        dp[N][K] %= 9901; // Really big integers so you have to do mod
        return dp[N][K];
    }


    public static void main(String[] args) throws IOException {
        String fileName = "nocows";
        BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new long[N+1][K+1];
        for (int i=0; i<N+1; i++){
            Arrays.fill(dp[i], -1);
        }
        // System.out.println(N + " " + K);


        long solution = solve(N, K);
        // System.out.println(Arrays.deepToString(dp));
        //
        // System.out.println(solution);

        pw.println(solution);
        pw.close();
    }
}
