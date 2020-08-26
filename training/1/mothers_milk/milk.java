/*
ID: jasonli7
LANG: JAVA
TASK: milk3
*/
import java.io.*;
import java.util.*;

class milk3 {

    public static int getSeenIndex(Integer[] state) {
        return 441 * state[0] + 21 * state[1] + state[2];
    }

    public static ArrayList<Integer[]> getNextStates(Integer[] curState, int[] max) {
        ArrayList<Integer[]> nextStates = new ArrayList<Integer[]>();

        int[][] pourSeq = new int[][] {
            {0, 1}, {1, 0}, {0, 2}, {2, 0}, {1, 2}, {2, 1}
        };
        for (int[] pourDir: pourSeq) {
            int pourer = pourDir[0];
            int filler = pourDir[1];
            if (curState[pourer] > 0 && curState[filler] < max[filler]) {
                int amtPoured = Math.min(curState[pourer], max[filler] - curState[filler]);
                Integer[] newState = curState.clone();
                newState[pourer] -= amtPoured;
                newState[filler] += amtPoured;
                nextStates.add(newState);
            }
        }

        return nextStates;
    }

    public static Set<Integer> solve(int A, int B, int C) {
        int[] seen = new int[9261];
        // Subtract 1 from a, b and c and then do a * 400 + b * 20 + c to get index in seen array

        ArrayList<Integer[]> frontier = new ArrayList<Integer[]>();
        Set<Integer> solutions = new HashSet<Integer>();

        frontier.add(new Integer[]{0, 0, C});
        while (frontier.size() > 0) {
            Integer[] curState = frontier.remove(0);
            if (curState[0] == 0) {
                solutions.add(curState[2]);
            }
            // System.out.println(Arrays.toString(curState));

            seen[getSeenIndex(curState)] = 1;

            ArrayList<Integer[]> nextStates = getNextStates(curState, new int[]{A, B, C});
            for (Integer[] state: nextStates) {
                if (seen[getSeenIndex(state)] == 0) {
                    frontier.add(state);
                }
            }
        }
        return solutions;
    }

    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("milk3.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        Set<Integer> sol = solve(A, B, C);
        ArrayList<Integer> sorted_sol = new ArrayList<Integer>(sol);
        sorted_sol.sort((a, b) -> a - b);
        
        for (Integer i: sorted_sol.subList(0, sorted_sol.size() - 1)) {
            pw.print(i+ " ");
        }
        pw.print(sorted_sol.get(sorted_sol.size() - 1) + "\n");

        pw.close();
    }
}
