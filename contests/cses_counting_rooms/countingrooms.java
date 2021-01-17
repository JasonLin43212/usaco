import java.io.*;
import java.util.*;

public class countingrooms {
    
    public static int N;
    public static int M;
    public static boolean[][] isWall;
    public static boolean[][] seen;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		isWall = new boolean[N][M];
		seen = new boolean[N][M];
		
		for (int r = 0; r < N; r++) {
		    String line = br.readLine();
		    for (int c = 0; c < M; c++) {
		        if (line.charAt(c) == '#') {
		            isWall[r][c] = true;
		        }
		    }
		}
		
//		System.out.println(Arrays.deepToString(isWall));
		int numRooms = 0;
		for (int r = 0; r < N; r++) {
		    for (int c = 0; c < M; c++) {
		        if (!isWall[r][c] && !seen[r][c]) {
		            numRooms++;
		            fill(r, c);
		        }
		    }
		}
		
		System.out.println(numRooms);
	}
	
	public static void fill(int r, int c) {
	    if (r < 0 || r >= N || c < 0 || c >= M) {
	        return;
	    }
	    if (!isWall[r][c] && !seen[r][c]) {
	        seen[r][c] = true;
	        fill(r + 1, c);
	        fill(r - 1, c);
	        fill(r, c + 1);
	        fill(r, c - 1);
	    }
	}
}