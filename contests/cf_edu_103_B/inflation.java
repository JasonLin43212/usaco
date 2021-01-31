import java.io.*;
import java.util.*;

public class inflation {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);
		for (int z = 0; z < t; z++) {
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        int n = Integer.parseInt(st.nextToken());
	        int k = Integer.parseInt(st.nextToken());
	        long[] prices = new long[n];
	        long[] prefix = new long[n+1];
	        
	        st = new StringTokenizer(br.readLine());
	        for (int i = 0; i < n; i++) {
	            prices[i] = Long.parseLong(st.nextToken());
	            prefix[i + 1] = prefix[i] + prices[i];
	        }
	        
//	        System.out.println(Arrays.toString(prices) + " " + Arrays.toString(prefix));
	        
	        long maxAdd = 0;
	        for (int i = 1; i < n; i++) {
	            maxAdd = (long) Math.max(maxAdd, (long) Math.ceil((double)(100 * prices[i]) - k * prefix[i]) / (double) k);
	        }
	        
	        pw.println(maxAdd);
		}
		
		pw.close();
	}
}