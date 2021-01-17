import java.io.*;
import java.util.*;

public class forestquery {
    
    public static int[][] forest;
    public static int[][] prefix;
    public static int N;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		prefix = new int[N+1][N+1];
		for (int r = 1; r < N+1; r++) {
		    String line = br.readLine();
		    for (int c = 1; c < N+1; c++) {
		        int numTree = 0;
		        if (line.charAt(c-1) == '*') {
                    numTree = 1;
                }
		        prefix[r][c] = prefix[r-1][c] + prefix[r][c-1] -
		                       prefix[r-1][c-1] + numTree;
		    }
		}
//		System.out.println(Arrays.deepToString(prefix));
		
		for (int i = 0 ; i < Q; i++) {
		    st = new StringTokenizer(br.readLine());
		    int y_1 = Integer.parseInt(st.nextToken());
            int x_1 = Integer.parseInt(st.nextToken());
            int y_2 = Integer.parseInt(st.nextToken());
            int x_2 = Integer.parseInt(st.nextToken());

		    System.out.println(numTrees(y_1, y_2, x_1, x_2));
		}
	}
	
	public static int numTrees(int r_1, int r_2, int c_1, int c_2) {
	    return prefix[r_2][c_2] - prefix[r_1-1][c_2] - prefix[r_2][c_1-1] + prefix[r_1-1][c_1-1];
	}
}