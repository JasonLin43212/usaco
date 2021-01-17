import java.io.*;
import java.util.*;

public class orz {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PrintWriter pw = new PrintWriter(System.out);
		for (int i = 0 ; i < N; i++) {
		    int a = Integer.parseInt(br.readLine());
		    pw.println(solve(a));
		}
		pw.close();
	}
	
	public static String solve(int a) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("9");
	    // always pause second number
	    if (a == 1) {
	        return sb.toString();
	    } 
	    if (a >= 2) {
	        sb.append("8");
	    } 
	    if (a > 2) {
	        int x = 9;
	        for (int i = 2; i < a; i++) {
	            sb.append(x + "");
	            x = (x + 1) % 10;
	        }
	        
	    }
	    return sb.toString();
	    
	}
}