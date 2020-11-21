/*
ID: jasonli7
LANG: JAVA
TASK: runround
*/
import java.io.*;
import java.util.*;

class runround {

    static int N;
    static int max = 999999999;

    public static boolean isRunAround(int n) {
        String strN = Integer.toString(n);
        int numDigits = strN.length();
        boolean[] seen = new boolean[numDigits];


        int curIdx = 0;
        int curNum = strN.charAt(curIdx) - '0'; // Subtract ascii values to get int value
        for (int i=0; i<numDigits; i++){
            curIdx = (curIdx + curNum) % numDigits;
            curNum = strN.charAt(curIdx) - '0';
            if (seen[curIdx]) return false;
            seen[curIdx] = true;
        }

        return true;
    }

    public static boolean isValid(int n) {
        if (n % 10 == 0) return false; // Not necessary but can speed it up a little (has 0 at end)

        // Check if it repeats any digits and sum digits
        boolean[] occured = new boolean[10];
        int numDigits = 0;
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;

            if (occured[digit] || digit == 0) {
                return false; // A digit occured twice
            }

            sum += digit;
            numDigits++;

            occured[digit] = true;
            n /= 10;
        }

        // No zeros or repeated digits but check if it is possible to end up where it started
        return sum % numDigits == 0;
    }

    public static int solve() {
        for (int i=N+1; i<max; i++) {
            if (isValid(i) && isRunAround(i)) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        String fileName = "runround";
        BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));

        N = Integer.parseInt(br.readLine());

        int solution = solve();
        pw.println(solution);
        pw.close();
    }
}
//NOTE:
/*
    Since all digits must be unique and not have a 0, then we can generate the numbers instead of
    starting at the start point and checking every number.
*/
