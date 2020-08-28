/*
ID: jasonli7
LANG: JAVA
TASK: preface
*/
import java.io.*;
import java.util.*;

class preface {

    static int N;
    static int[] numbers = new int[]{1000, 900, 500, 400, 100,
         90, 50, 40, 10, 9,
         5, 4, 1};
    static String[] romans = new String[]{"M", "CM", "D", "CD", "C",
     "XC", "L", "XL", "X", "IX",
     "V", "IV", "I"};


    public static String convertToRoman(int n) {
        String romanNum = "";
        int romanIdx = 0;
        while (n > 0) {
            int numRepeat = n / numbers[romanIdx];
            for (int i=0; i<numRepeat; i++) {
                romanNum += romans[romanIdx];
                n -= numbers[romanIdx];
            }
            romanIdx++;
        }
        return romanNum;
    }

    public static void main(String[] args) throws IOException {
        String fileName = "preface";
        BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));

        N = Integer.parseInt(br.readLine());

        // 7 slots for each numeral in the order: I, V, X, L, C, D, M
        int[] solution = new int[7];
        char[] letters = new char[]{'I', 'V', 'X', 'L', 'C', 'D', 'M'};

        for (int n = 1; n < N+1; n++) {
            String romanN = convertToRoman(n);
            for (int i=0; i<romanN.length(); i++) {
                char c = romanN.charAt(i);
                for (int j=0; j<7; j++) {
                    if (c == letters[j]) {
                        solution[j]++;
                        break;
                    }
                }
            }
        }

        for (int i=0; i<7; i++) {
            if (solution[i] != 0) {
                pw.println(letters[i] + " " + solution[i]);
            }
        }
        pw.close();
    }

}
