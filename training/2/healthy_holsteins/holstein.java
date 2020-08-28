/*
ID: jasonli7
LANG: JAVA
TASK: holstein
*/
import java.io.*;
import java.util.*;

public class holstein {
    public static int feedComp(ArrayList<Integer> a, ArrayList<Integer> b) {
        for (int i=0; i<a.size(); i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) - b.get(i);
            }
        }
        return 0;
    }

    public static boolean satisfyVitamins(int[] curVitamins, int[] vitaminReqs) {
        for (int i=0; i<curVitamins.length; i++){
            if (curVitamins[i] < vitaminReqs[i]) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<ArrayList<Integer>> getCombos(int numFeed) {
        ArrayList<ArrayList<Integer>> combos = new ArrayList<>();

        ArrayList<Integer> empty = new ArrayList<>();
        combos.add(empty);

        for (int i=0; i<numFeed; i++) {
            ArrayList<ArrayList<Integer>> newCombos = new ArrayList<>();
            for (ArrayList<Integer> combo: combos) {
                newCombos.add(combo);
                ArrayList<Integer> addCombo = new ArrayList<>(combo);
                addCombo.add(i);
                newCombos.add(addCombo);
            }
            combos = newCombos;
        }
        combos.sort((a, b) -> a.size() - b.size());
        combos.remove(0);
        // System.out.println(combos.toString());
        return combos;
    }

    public static ArrayList<Integer> solve(int V, int G, int[] vitaminReqs, int[][] feedVitamins) {
        /*
            Generate subsets of the feeds to test and see if they fulfill the requirement
            We will generate subsets of size 1 and work our way up
            If there exists a solution at a particular size, we don't need to keep on checking
            We need to keep all solutions from a particular size (maybe not) for sorting later
        */
        int numCombos = (int) Math.pow(2, G);

        ArrayList<ArrayList<Integer>> allCombos = getCombos(G);

        int minComboSize = G + 1;
        ArrayList<ArrayList<Integer>> goodCombos = new ArrayList<>();
        for (ArrayList<Integer> combo: allCombos) {
            if (combo.size() <= minComboSize) {
                int[] curVitaminSum = new int[V];
                for (Integer feedNum: combo) {
                    for (int v=0; v<V; v++) {
                        curVitaminSum[v] += feedVitamins[feedNum][v];
                    }
                }

                // System.out.println("combo: " + combo.toString());
                // System.out.println("sum: " + Arrays.toString(curVitaminSum));
                if (satisfyVitamins(curVitaminSum, vitaminReqs)) {
                    goodCombos.add(combo);
                    minComboSize = combo.size();
                }
            }
        }

        goodCombos.sort((a, b) -> feedComp(a, b));
        // System.out.println(goodCombos.toString());

        return goodCombos.get(0);
    }

    public static void main (String [] args) throws IOException {
        String fileName = "holstein";
        BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));

        int V = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] vitaminReqs = new int[V];
        for (int v=0; v<vitaminReqs.length; v++){
            vitaminReqs[v] = Integer.parseInt(st.nextToken());
        }

        // System.out.println(V + "\n" + Arrays.toString(vitaminReqs));
        int G = Integer.parseInt(br.readLine());
        int[][] feedVitamins = new int[G][V];
        for (int g=0; g<feedVitamins.length; g++) {
            int[] curVitamins = new int[V];
            st = new StringTokenizer(br.readLine());
            for (int v=0; v<curVitamins.length; v++) {
                curVitamins[v] = Integer.parseInt(st.nextToken());
            }
            feedVitamins[g] = curVitamins;
        }

        // System.out.println(G + "\n" + Arrays.deepToString(feedVitamins));

        ArrayList<Integer> solution = solve(V, G, vitaminReqs, feedVitamins);
        // System.out.println(solution.toString());
        pw.print(solution.size() + " ");
        for (int i=0; i<solution.size()-1; i++) {
            pw.print((solution.get(i) + 1) + " ");
        }
        pw.print((solution.get(solution.size() - 1) + 1) + "\n");
        pw.close();
    }
}
