import java.io.*;
import java.util.*;

public class cards {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            System.out.println(possible(w, h, n) ? "YES" : "NO");
		}
	}
	
	public static boolean possible(int w, int h, int n) {
	    int pieces = 1;
	    while (w % 2 == 0) {
	        w /= 2;
	        pieces *= 2;
	    }
	    
	    while (h % 2 == 0) {
            h /= 2;
            pieces *= 2;
        }
	    
	    return pieces >= n;
	}
}