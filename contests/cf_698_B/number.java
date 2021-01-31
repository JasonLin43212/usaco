import java.io.*;
import java.util.*;

public class number {
    
    public static int[] ends;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		PrintWriter pw = new PrintWriter(System.out);
		for (int z = 0; z < t; z++) {
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        int n = Integer.parseInt(st.nextToken());
	        ends = new int[11];
	        Arrays.fill(ends, -1);
	        int d = Integer.parseInt(st.nextToken());
	        for (int i = 0; i < 11; i++) {
	            int ind = (i * 7) % 10;
	            if (ends[ind] != -1) {
	                ends[ind] = i;
	            }
	        }
	        for (int i = 0; i < n; i++) {
	            pw.println(solve(Integer.parseInt(st.nextToken()), d));
	        }
		}
		pw.close();
	}
	
	public static String solve(int num, int d) {
	    
	}
}