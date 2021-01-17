import java.io.*;
import java.util.*;

public class fair {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
		    int n = Integer.parseInt(br.readLine());
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    
		    int a = 0;
		    int b = 0;
		    Integer[] candies = new Integer[n];
		    for (int j = 0; j < n; j++) {
		        int weight = Integer.parseInt(st.nextToken());
		        candies[j] = weight;
		    }
		    
		    Arrays.sort(candies, (x, y) -> y - x);
		    for (int weight : candies) {
        	    if (a < b) {
                    a += weight;
                } else {
                    b += weight;
                }
		    }
		    
		    System.out.println(a == b ? "YES" : "NO");
		}
		
	}
}