import java.io.*;
import java.util.*;

public class gymnastics {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("gymnastics.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] cowRanks = new int[N][K];
		
		for (int k = 0; k < K; k++) {
		    st = new StringTokenizer(br.readLine());
		    for (int n = 0; n < N; n++) {
		        int cowNum = Integer.parseInt(st.nextToken());
		        cowRanks[cowNum-1][k] = n;
		    }
		}
		
		int numPairs = 0;
		for (int i = 0; i < N; i++) {
		    for (int j = 0; j < N; j++) {
		        if (i != j) {
		            boolean ilessj = true;
	                for (int k = 0; k < K; k++) {
	                    if (cowRanks[i][k] > cowRanks[j][k]) {
	                        ilessj = false;
	                        break;
	                    }
	                }
	                
	                if (ilessj) {
	                    numPairs++;
	                }
		        }
		    }
		}
		
//		System.out.println(numPairs);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gymnastics.out")));
		pw.println(numPairs);
		pw.close();
	}
}