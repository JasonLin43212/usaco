/*
ID: jasonli7
LANG: JAVA
TASK: sprime
*/
import java.io.*;
import java.util.*;

class sprime {

    public static boolean isPrime(int i) {
        if (i < 2) {
            return false;
        }
        if (i == 2) {
            return true;
        }

        for (int n=2; n*n<=i; n++) {
            if (i % n == 0) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Integer> solve(int N) {
        ArrayList<Integer> solutions = new ArrayList<Integer>();

        int[] primes = new int[]{1, 3, 5, 7, 9};

        for (int i=0; i<N; i++) {
            if (i == 0) {
                solutions.add(2);
                solutions.add(3);
                solutions.add(5);
                solutions.add(7);
            } else {
                ArrayList<Integer> newSolutions = new ArrayList<Integer>();
                for (Integer num: solutions) {
                    for (int prime: primes) {
                        int testNum = num * 10 + prime;
                        if (isPrime(testNum)) {
                            newSolutions.add(testNum);
                        }
                    }
                }
                solutions = newSolutions;
            }
        }

        return solutions;
    }

    public static void main (String [] args) throws IOException {
        String fileName = "sprime";
        BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));

        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> solution = solve(N);
        solution.sort((a, b) -> a - b);

        for (Integer num: solution) {
            pw.println(num);
        }
        pw.close();
    }
}
