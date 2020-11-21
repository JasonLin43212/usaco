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
    static int[][] dp;

    public static boolean isValid(int nodes, int height) {
        return ((int) (Math.pow(2, height))) > nodes &&
                (height * 2) - 1 <= nodes;
    }

    public static int getNumConfigs(int nodes, int height) {
        if (height == 0 || nodes % 2 == 0) {
            return 0;
        }
        if (dp[nodes][height] != -1) {
            return dp[nodes][height];
        }
        if (nodes == 1 && height == 1) { // Base Case
            dp[nodes][height] = 1;
            return 1;
        }

        int numConfigs = 0;
        for (int i=nodes-2; i>=nodes/2; i-=2) {
            if (isValid(i, height - 1)) {
                int otherConfigs = 0;
                for (int j=(int) (Math.log(nodes-1-i) / Math.log(2)) + 1; j<=Math.min((nodes-i)/2, height-1); j++) {
                    otherConfigs += getNumConfigs(nodes-1-i, j);
                }
                if (i != nodes/2) {
                    otherConfigs *= 2;
                }
                int toAdd = getNumConfigs(i, height - 1) * otherConfigs;
                numConfigs += toAdd;
            }
        }
        dp[nodes][height] = numConfigs;
        return numConfigs;
    }

    public static void main(String[] args) throws IOException {
        String fileName = "nocows";
        BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[N+1][K+1];
        for (int i=0; i<N+1; i++) {
            for (int j=0; j<K+1; j++) {
                dp[i][j] = -1;
            }
        }
        // System.out.println(N + " " + K);


        int solution = getNumConfigs(N, K);
        // System.out.println(solution);
        // System.out.println(Arrays.deepToString(dp));
        for (int i = 0; i<N+1; i++) {
            for (int j=0; j<K+1; j++){
                if (dp[i][j] != -1) {
                    System.out.println("node: " + i + " height: "+ j + " = " + dp[i][j]);
                }
            }
        }

        solution = solution % 9901;

        pw.println(solution);
        pw.close();
    }
}
