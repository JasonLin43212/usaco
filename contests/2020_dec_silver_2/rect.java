import java.io.*;
import java.util.*;

public class rect {
    public static int N;
    public static int[] X;
    public static int[] Y;
    public static Integer[] cows;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		X = new int[N];
		Y = new int[N];
		cows = new Integer[N];
		
		for (int i = 0; i < N; i++) {
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        X[i] = Integer.parseInt(st.nextToken());
	        Y[i] = Integer.parseInt(st.nextToken());
	        cows[i] = i;
		}
		
		Arrays.sort(cows, (a, b) -> X[a] - X[b]);
		for (int i = 0; i < N; i++) {
		    X[cows[i]] = i;
		}
//		System.out.println("newX: " + Arrays.toString(X));
		
		Arrays.sort(cows, (a, b) -> Y[a] - Y[b]);
        for (int i = 0; i < N; i++) {
            Y[cows[i]] = i;
        }
//        System.out.println("newY: " + Arrays.toString(Y));
		
        long[][] pf = new long[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            pf[X[i-1] + 1][Y[i-1]+1]++;
        }
        
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                pf[i][j] = pf[i-1][j] + pf[i][j-1] - pf[i-1][j-1] + pf[i][j];
            }
        }
        
//        System.out.println(Arrays.deepToString(pf));
        long totalSubsets = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int lowX = Math.min(X[i], X[j])+1;
                int highX = Math.max(X[i], X[j])+1;
                int lowY = Math.min(Y[i], Y[j])+1;
                int highY = Math.max(Y[i], Y[j])+1;
//                System.out.println(lowX + " " + highX + "\n" + lowY + " " + highY);
                long numLeft = pf[lowX][highY] - pf[lowX][lowY-1];
                long numRight = pf[N][highY] - pf[highX-1][highY] -
                                pf[N][lowY-1] + pf[highX-1][lowY-1];
//                System.out.println(numLeft + " <-left right-> " + numRight + "\n");
                totalSubsets += numLeft * numRight;
            }
        }
        
		PrintWriter pw = new PrintWriter(System.out);
		pw.println(totalSubsets+N+1);
		pw.close();
	}
}