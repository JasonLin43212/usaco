/*
ID: jasonli7
LANG: JAVA
TASK: hamming
*/
import java.io.*;
import java.util.*;

class hamming {

    static int N;
    static int B;
    static int D;
    static int max;
    static ArrayList<Integer> solution = new ArrayList<>();

    public static int hammingDist(int a, int b) {
        int dist = 0;
        for (int i=0; i<B; i++){
            /*
                The operation (a >> i) & 1 either gives you a 0 or 1
                It tells you what is at the i-th position from the right
                So if you had 1011, then for values of i=0, 1, and 3, you will get 1
                while for i=2, you get 0.

                We are comparing if the corresponding i-th bit for each number is different
                and if it is, then we add one to the Hamming Distance
            */
            if (((a >> i) & 1) != ((b >> i) & 1)) {
                dist += 1;
            }
        }
        return dist;
    }

    public static int hammingDist2(int a, int b) {
        /*
            Integer.bitCount will count the number of 1 bits in the binary representation of the digit
            The ^ is the XOR operator and will give you a 1 in the places where a and b have different bits.

            So what I am doing here is first getting a new binary number that shows 1's where there
            is a difference and counting how many 1's there are.
        */
        return Integer.bitCount(a ^ b);
    }

    public static boolean isFarEnough(int num) {
        for (Integer solNum: solution) {
            if (hammingDist2(num, solNum.intValue()) < D) {
                return false;
            }
        }
        return true;
    }

    public static void solve() {
        int num = 0;
        while (num <= max && solution.size() < N) {
            if (solution.size() == 0 || isFarEnough(num)) {
                solution.add(num);
            }
            num++;
        }
    }

    public static void main(String[] args) throws IOException {
        String fileName = "hamming";
        BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        max = (int) Math.pow(2, B) - 1;
        // System.out.println(N + " " + B + " " + D + " max: " + max);

        solve();
        // System.out.println(solution.toString());
        for (int i=0; i<N; i++) {
            if (i % 10 == 9 || i == N - 1) {
                pw.print(solution.get(i) + "\n");
            } else {
                pw.print(solution.get(i) + " ");
            }
        }
        pw.close();
    }

}
