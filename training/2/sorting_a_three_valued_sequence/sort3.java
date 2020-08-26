/*
ID: jasonli7
LANG: JAVA
TASK: sort3
*/
import java.io.*;
import java.util.*;

public class sort3 {

    public static void main (String [] args) throws IOException {
        String fileName = "sort3";
        BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));

        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        int[] freq = new int[3];
        for (int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            nums[i] = num;
            freq[num - 1] += 1;
        }

        // System.out.println(N);
        // System.out.println(Arrays.toString(nums));
        // System.out.println(Arrays.toString(freq));

        int[][] numInTerritory = new int[3][3];
        int nextEnd = 0;
        int curTerritory = -1;
        int numMisplaced = 0;
        for (int i=0; i<N; i++){
            if (i == nextEnd) {
                curTerritory++;
                nextEnd += freq[curTerritory];
            }
            if (nums[i] - 1 != curTerritory) {
                numMisplaced++;
            }
            numInTerritory[nums[i]-1][curTerritory] += 1;
        }
        // System.out.println(Arrays.deepToString(numInTerritory));
        // System.out.println(numMisplaced);

        int numSwap = 0;
        for (int i=0; i<3; i++) {
            for (int j=i; j<3; j++) {
                if (i != j) {
                    numSwap += Math.min(numInTerritory[i][j], numInTerritory[j][i]);
                }
            }
        }
        numMisplaced -= numSwap * 2;
        numSwap += numMisplaced / 3 * 2;

        pw.println(numSwap);
        pw.close();
    }
}
