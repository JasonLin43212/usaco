import java.io.*;
import java.util.*;

public class divissum {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		PrintWriter pw = new PrintWriter(System.out);
		for (int x = 0; x < t; x++) {
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        int n = Integer.parseInt(st.nextToken());
	        int k = Integer.parseInt(st.nextToken());
            
	        if (n > k) {
	            int mult = n / k;
	            if (n % k != 0) {
	                mult++;
	            }
	            k *= mult;
	        }
	        
	        int max = k / n;
            if (k % n != 0) {
                max++;
            }
            pw.println(max);
		}
		pw.close();
	}
}