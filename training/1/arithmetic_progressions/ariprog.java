/*
ID: jasonli7
LANG: JAVA
TASK: ariprog
*/
import java.io.*;
import java.util.*;

class ariprog {

    public static boolean isValid(boolean[] isBisquares, int a, int b, int N) {
        for (int n=1; n<N; n++){
            // System.out.println(n + " is n used: " + (a+b*n));
            if (!(isBisquares[a + b*n])) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Integer[]> solve(int N, int M) {
        int maxSize = M * M * 2 + 1;
        boolean[] isBisquares = new boolean[maxSize];
        ArrayList<Integer[]> solution = new ArrayList<Integer[]>();

        for (int p=0; p < M+1; p++) {
            for (int q=0; q < M+1; q++) {
                isBisquares[p*p+q*q] = true;
            }
        }
        //
        // for (int i=0; i < isBisquares.length; i++){
        //     if (isBisquares[i]) {
        //         System.out.println(i);
        //     }
        // }
        // System.out.println("MAXSIZE: " + maxSize);
        for (int a=0; a < maxSize; a++) {
            if (isBisquares[a]) {
                // System.out.println(a + " is good start");
                for (int b=1; b < maxSize / (N - 1) +1; b++) {
                    if (a + b * (N-1) < maxSize) {
                        // System.out.println(b + " is good increment: "+ (a + b*(N-1)));
                        if (isValid(isBisquares, a, b, N)) {
                            solution.add(new Integer[]{a, b});
                        }
                    }
                }
                // System.out.println("");
            }
        }

        return solution;
    }

    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("ariprog.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());    // first integer
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());    // second integer

        ArrayList<Integer[]> solution = solve(N, M);
        solution.sort((o1, o2) -> o1[1] - o2[1]);
        if (solution.size() == 0) {
            pw.println("NONE");
        }
        else {
            for (Integer[] sol: solution){
                // System.out.println(Arrays.toString(sol));
                pw.println(sol[0] + " " + sol[1]);
            }
        }

        pw.close();                                  // close the output file
    }
}
