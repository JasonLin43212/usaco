import java.io.*;
import java.util.*;

public class countingdivisors {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
		    pw.println(countDivisors(Integer.parseInt(br.readLine())));
		}
		pw.close();
	}
	
	public static int countDivisors(int n) {
	    int numDivisors = 0;
	    for (int i = 1; i * i <= n; i++) {
	        if (n % i == 0) {
	            if (i * i == n) {
	                numDivisors++;
	            } else {
	                numDivisors += 2;
	            }
	        }
	    }
	    return numDivisors;
	}
}