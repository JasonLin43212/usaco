import java.io.*;
import java.util.*;

public class stringlcm {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		PrintWriter pw = new PrintWriter(System.out);
		for (int x = 0; x < t; x++) {
		    String s1 = br.readLine();
		    String s2 = br.readLine();
		    
		    pw.println(lcm(s1, s2));
		}
		pw.close();
	}
	
	public static String lcm (String s1, String s2) {
	    int stringGcd = gcd(s1.length(), s2.length());
	    int stringLcm = (s1.length() / stringGcd) * s2.length();
	    
	    StringBuilder sb1 = new StringBuilder();
	    StringBuilder sb2 = new StringBuilder();
	    
	    for (int i = 0; i < stringLcm; i++) {
	        sb1.append(s1.charAt(i % s1.length()));
	        sb2.append(s2.charAt(i % s2.length()));
	    }
	    
	    if (sb1.toString().equals(sb2.toString())) {
	        return sb1.toString();
	    }
	    return "-1";
	}
	
	public static int gcd(int a, int b) {
	    if (b == 0) {
	        return a;
	    } else {
	        return gcd(b, a % b);
	    }
	}
}