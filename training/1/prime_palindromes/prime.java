/*
ID: jasonli7
LANG: JAVA
TASK: pprime
*/
import java.io.*;
import java.util.*;

class pprime {

    public static boolean isPrime(int i) {
        if (i < 2) {
            return false;
        }
        if (i == 2) {
            return true;
        }

        for (int n=2; n<=(int)Math.sqrt(i); n++) {
            if (i % n == 0) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Integer> getPalimOfSize(int bound) {
        ArrayList<Integer> palims = new ArrayList<Integer>();

        if (bound == 1) {
            palims.add(5);
            palims.add(7);
            return palims;
        }

        if (bound % 2 == 0) { // Even bound
            for (int i=0; i<bound/2; i++) {
                if (i == 0) {
                    for (int n=1; n<=9; n+=2) { // Odd numbers are primes
                        palims.add((int) (n * Math.pow(10, (bound - 1)) + n));
                    }
                } else {
                    ArrayList<Integer> newPalims = new ArrayList<Integer>();

                    for (int n=0; n<=9; n++) {
                        int numToAdd = (int) (n * Math.pow(10, (bound - i - 1)) + n * Math.pow(10, i));
                        for (Integer prevPalim: palims) {
                            newPalims.add(prevPalim + numToAdd);
                        }
                    }
                    palims = newPalims;
                }
            }
        } else { // Odd bound
            for (int i=0; i<bound/2 + 1; i++) {

                if (i == bound / 2) {
                    ArrayList<Integer> newPalims = new ArrayList<Integer>();
                    for (int n=0; n<=9; n++) {
                        int numToAdd = (int) (n * Math.pow(10, (bound / 2)));
                        for (Integer prevPalim: palims) {
                            newPalims.add(prevPalim + numToAdd);
                        }
                    }
                    palims = newPalims;
                } else if (i == 0) {
                    for (int n=1; n<=9; n+=2) { // Odd numbers are primes
                        palims.add((int) (n * Math.pow(10, (bound - 1)) + n));
                    }
                } else {
                    ArrayList<Integer> newPalims = new ArrayList<Integer>();

                    for (int n=0; n<=9; n++) {
                        int numToAdd = (int) (n * Math.pow(10, (bound - i - 1)) + n * Math.pow(10, i));
                        for (Integer prevPalim: palims) {
                            newPalims.add(prevPalim + numToAdd);
                        }
                    }
                    palims = newPalims;
                }
            }
        }
        return palims;
    }

    public static ArrayList<Integer> solve(int a, int b) {
        ArrayList<Integer> solutions = new ArrayList<Integer>();

        int maxSize = Integer.toString(b).length();

        for (int bound=1; bound < maxSize + 1; bound++) {
            for (Integer i: getPalimOfSize(bound)) {
                if (a <= i && b >= i && isPrime(i)) {
                    solutions.add(i);
                }
            }
        }

        return solutions;
    }

    public static void main (String [] args) throws IOException {
        String fileName = "pprime";
        BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        ArrayList<Integer> solution = solve(a, b);
        solution.sort((x, y) -> x - y);

        for (Integer num: solution) {
            pw.println(num);
        }
        pw.close();
    }
}
