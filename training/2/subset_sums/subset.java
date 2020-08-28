/*
ID: jasonli7
LANG: JAVA
TASK: subset
*/
import java.io.*;
import java.util.*;

class subset {

    static int N;
    static int targetSum;
    static int[][] precomputedSols;

    public static int numSetSumToN(int cur, int target) {
        // Base Cases
        if (target == 0) return 1; // We got it! (Do this one first since you might also be at end)
        if (cur == N) return 0; // One number isn't enough
        if (target < 0) return 0; // Too far, go back
        if (precomputedSols[cur][target] != -1) return precomputedSols[cur][target]; // We already did it

        int answer = numSetSumToN(cur + 1, target - cur) + numSetSumToN(cur + 1, target);
        precomputedSols[cur][target] = answer; // Store answer
        return answer;
    }

    public static void main(String[] args) throws IOException {
        String fileName = "subset";
        BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));

        N = Integer.parseInt(br.readLine());

        int totalSum = (N * (N + 1)) / 2;
        if (totalSum % 2 == 1) {
            pw.println("0");
        } else {
            targetSum = totalSum / 2;
            precomputedSols = new int[N + 1][targetSum + 1];

            for (int i=0; i<N + 1; i++) {
                for (int j=0; j<targetSum + 1; j++) {
                    precomputedSols[i][j] = -1;
                }
            }

            int solution = numSetSumToN(1, targetSum);

            pw.println(solution);
        }

        pw.close();
    }
}

//NOTE:
/*
    The basis of a handful of DP algorithms is the "take-an-old-count,
    add some to it, and carry it forward again".

    Alt Solution: You have one array that starts out as [1, 1, 0, 0, ...]
    where the index is the sum and the value at that index is the number of ways to create that num

    To get the next one, say if we have {1, 2}, what numbers we can create?
    we can take our previous array [1, 1, 0, 0, ...] which is the number of ways to create different
    numbers give just {1} and then say to ourselves, now that we have a 2, what else sums can we make

    We can take what we have already since we can pretend the 2 isn't in our set and this still gives
    us [1, 1, 0, 0, ...]. But now, we have a 2 as well. So since we know that there is 1 way to
    make a 0 from {1}, then if we add a 2 to however we made the 0 the first time, we get a 2. So we also
    have one way to make a 2. We also do the same for numbers of ways to make a 1, so now we also have
    1 way to make a 3. Do this until we get to N.
*/
