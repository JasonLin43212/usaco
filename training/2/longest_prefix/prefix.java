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
    static StringBuilder seqBuild = new StringBuilder();
    static String seq;
    static boolean[] available;

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
            if (st.hasMoreTokens()) {
                curToken = st.nextToken();
            } else {
                st = new StringTokenizer(br.readLine());
            }
        }

        String nextString = br.readLine();
        while (nextString != null) {
            seqBuild.append(nextString);
            nextString = br.readLine();
        }
        seq = seqBuild.toString();

        available = new boolean[seq.length() + 1];
        available[0] = true;

        int best = 0;
        for (int i=0; i<seq.length(); i++) {
            if (available[i]) {
                for (int j=1; j<=maxPrimLen; j++) {
                    if (i+j <= seq.length() && !available[i+j]) {
                        if (primitives.contains(seq.substring(i, i+j))) {
                            available[i+j] = true;
                            best = Math.max(best, i+j);
                        }
                    }
                }
            }
        }

        if (available[available.length - 1]) {
            pw.println(seq.length());
        } else {
            pw.println(best);
        }
        pw.close();
    }
}
