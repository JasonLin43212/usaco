/*
ID: jasonli7
LANG: JAVA
TASK: prefix
*/
import java.io.*;
import java.util.*;

class prefix {

    static Set<String> primitives = new HashSet<>();
    static int maxPrimLen = 0;
    static String seq = "";
    static int[] maxPrefixAt;

    public static void getMaxPrefixAt(int start) {
        // System.out.println("start: " + start);
        if (start > seq.length() - 1) return; // You are starting too far

        int solution = 0;
        for (int i=1; i<=maxPrimLen; i++) {
            if (start + i > seq.length()) {
                break;
            }
            // System.out.println("i: " + i);
            if (start + i <= seq.length() && primitives.contains(seq.substring(start, start + i))) {
                int nextLength  = 0;
                if (start + i == seq.length()) { // At end
                    nextLength = i;
                } else if (start + i <= seq.length() - 1) {
                    nextLength = maxPrefixAt[start + i] + i;

                }
                solution = Math.max(nextLength, solution);
            }
        }

        // System.out.println("solution found for start: " + start + " which is " + solution );
        maxPrefixAt[start] = solution;
    }

    public static void main(String[] args) throws IOException {
        String fileName = "prefix";
        BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        String curToken = st.nextToken();
        while (!curToken.equals(".")) {
            if (curToken.length() > maxPrimLen) {
                maxPrimLen = curToken.length();
            }
            primitives.add(curToken);
            try {
                curToken = st.nextToken();
            } catch (Exception e) {
                st = new StringTokenizer(br.readLine());
            }
        }

        String nextString = br.readLine();
        while (nextString != null) {
            seq += nextString;
            nextString = br.readLine();
        }

        maxPrefixAt = new int[seq.length()];
        for (int i=0; i<maxPrefixAt.length; i++) {
            maxPrefixAt[i] = -1;
        }

        // System.out.println(primitives + "\n" + seq + "\n" + "max length: " + maxPrimLen + "\n" + Arrays.toString(maxPrefixAt) + "\n");

        for (int i=seq.length()-1; i>=0; i--) {
            getMaxPrefixAt(i);
        }

        pw.println(maxPrefixAt[0]);
        pw.close();
    }
}
