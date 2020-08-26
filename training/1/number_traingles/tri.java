/*
ID: jasonli7
LANG: JAVA
TASK: numtri
*/
import java.io.*;
import java.util.*;

class numtri {

    public static int solve(int R, int[][] numTriangle) {
        ArrayList<Integer> curSum = new ArrayList<Integer>();
        for (int i: numTriangle[R - 1]) {
            curSum.add(i);
        }

        for (int i = R-2; i >= 0; i--) {
            ArrayList<Integer> newSum = new ArrayList<Integer>();
            for (int j=0; j < i + 1; j ++) {
                int curNum = numTriangle[i][j];
                newSum.add(Math.max(curNum + curSum.get(j), curNum + curSum.get(j+1)));
            }
            curSum = newSum;
        }
        return curSum.get(0);
    }

    public static void main (String [] args) throws IOException {
        String fileName = "numtri";
        BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));

        int R = Integer.parseInt(br.readLine());

        int[][] numTriangle = new int[R][R];
        for (int i=1; i<R+1; i++) {
            int[] row = new int[i];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j=0; j<i; j++) {
                row[j] = Integer.parseInt(st.nextToken());
            }
            numTriangle[i-1] = row;
        }

        // System.out.println(Arrays.deepToString(numTriangle));

        int sol = solve(R, numTriangle);

        pw.print(sol + "\n");
        pw.close();
    }
}

//NOTE:
/*
    You can solve from the top down by keeping an array of the best and previous best
    The way that I did it uses more memory and probably more time.
*/
