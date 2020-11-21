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
    static boolean[] canReachBeg;

    public static void checkReachBeg(int end) {
        // System.out.println("end: " + end);

        for (int i=0; i<maxPrimLen; i++) {
            if (end - i < 0) {
                return;
            }

            // System.out.println("i: " + i);
            // System.out.println(seq.substring(end-i, end));
            if (primitives.contains(seq.substring(end - i, end + 1))) {
                if (end - i == 0) { // Can reach start
                    canReachBeg[end] = true;
                    return;
                } else if (canReachBeg[end - i - 1]) {
                    canReachBeg[end] = true;
                    return;
                }
            }
        }
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

        canReachBeg = new boolean[seq.length()];

        for (int i=0; i < seq.length(); i++) {
            checkReachBeg(i);
        }


        // System.out.println(Arrays.toString(canReachBeg));
        boolean foundAnswer = false;
        for (int i=seq.length()-1; i>=0; i--) {
            if (canReachBeg[i]) {
                pw.println((i + 1));
                foundAnswer = true;
                break;
            }
        }
        if (!foundAnswer) {
            pw.println("0");
        }

        pw.close();
    }
}
