import java.io.*;
import java.util.*;

public class divisors {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		PrintWriter pw = new PrintWriter(System.out);
		for (int x = 0; x < t; x++) {
		    long d = Long.parseLong(br.readLine());
		    pw.println(solve(d));
		}
		pw.close();
	}
	
	public static long solve(long d) {
	    long prime1 = getNextPrime(1 + d);
	    long prime2 = getNextPrime(prime1 + d);
	    return prime1 * prime2;
	}
	
	public static boolean isPrime(long x) {
	    for (int i = 2; i * i <= x; i++) {
	        if (x % i == 0) {
	            return false;
	        }
	    }
	    return true;
	}
	
	public static long getNextPrime(long x) {
	    boolean curPrime = isPrime(x);
	    while (!curPrime) {
	        x++;
	        curPrime = isPrime(x);
	    }
	    return x;
	}
}