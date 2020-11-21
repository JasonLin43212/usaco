import java.io.*;
import java.util.*;

class cownomics {

    static int N;
    static int M;
    static ArrayList<String> genes = new ArrayList<>();

    public static boolean canBeUsed(int seqLength) {
        for (int i=0; i < M+1-seqLength; i++) {
            Set<String> spotGene = new HashSet<>();

            for (int n=0; n<N; n++) {
                spotGene.add(genes.get(n).substring(i, i + seqLength));
            }

            boolean hasCommon = false;
            for (int n=N; n<2*N; n++) {
                if (spotGene.contains(genes.get(n).substring(i,i+seqLength))) {
                    hasCommon = true;
                    break;
                }
            }

            if (!hasCommon) {
                return true;
            }
        }
        return false;
    }

    public static int solve() {
        int minLength = 1;
        int maxLength = M;
        int seqLength = (minLength + maxLength) / 2;
        while (minLength != seqLength) {
            // System.out.println("Seq length: " + seqLength);
            // long beforeCheck = System.currentTimeMillis();
            if (canBeUsed(seqLength)) {
                maxLength = seqLength;
            } else {
                minLength = seqLength;
            }
            seqLength = (minLength + maxLength) / 2;
            // long afterCheck = System.currentTimeMillis();
            // System.out.println("checking time: " + (afterCheck - beforeCheck));
        }
        if (canBeUsed(minLength)) {
            return minLength;
        } else if (canBeUsed(maxLength)) {
            return maxLength;
        }
        return M;
    }

    public static void main(String[] args) throws IOException {
        String fileName = "cownomics";
        BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // long beforeRead = System.currentTimeMillis();
        for (int i = 0; i < 2*N; i++) {
            genes.add(br.readLine());
        }
        // long afterRead = System.currentTimeMillis();
        // System.out.println("reading time: " + (afterRead - beforeRead));

        int solution = solve();
        // long afterSolve = System.currentTimeMillis();
        // System.out.println("solving time: " + (afterSolve - afterRead));
        // System.out.println("solution: " + solution);
        pw.println(solution);
        pw.close();
    }
}
