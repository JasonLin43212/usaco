import java.io.*;
import java.util.*;

public class diamond {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("diamond.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] diamonds = new int[N];
		for (int i = 0; i < N; i++) {
		    diamonds[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(diamonds);
		
//		System.out.println(Arrays.toString(diamonds));
		
		int[] leftmostDiamond = new int[N];
		int j = 0;
		for (int i = 0; i < N; i++) {
		    while (j < N && diamonds[i] - diamonds[j] > K) {
		        j++;
		    }
		    leftmostDiamond[i] = j;
		}
		int[] rightmostDiamond = new int[N];
		j = N-1;
		for (int i = N - 1; i >= 0; i--) {
		    while (j >= 0 && diamonds[j] - diamonds[i] > K) {
                j--;
            }
            rightmostDiamond[i] = j;
		}
		
//        System.out.println(Arrays.toString(leftmostDiamond));
//        System.out.println(Arrays.toString(rightmostDiamond));
		int[] leftSize = new int[N];
		int[] rightSize = new int[N];
        for (int i = 0; i < N; i++) {
            leftSize[i] = i - leftmostDiamond[i] + 1;
            if (i > 0) {
                leftSize[i] = Math.max(leftSize[i], leftSize[i-1]);
            }
        }
        for (int i = N-1; i >=0; i--) {
            rightSize[i] = rightmostDiamond[i] - i + 1;
            if (i < N - 1) {
                rightSize[i] = Math.max(rightSize[i], rightSize[i+1]);
            }
        }
        
        int maxDiamonds = 0;
        for (int i = 0; i < N - 1; i++) {
            maxDiamonds = Math.max(maxDiamonds, leftSize[i] + rightSize[i+1]);
        }
        
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));
		pw.println(maxDiamonds);
		pw.close();
	}
}