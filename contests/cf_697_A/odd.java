import java.io.*;
import java.util.*;

public class odd {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine());
		PrintWriter pw = new PrintWriter(System.out);
		for (int i = 0; i < n; i++) {
		    long num = Long.parseLong(br.readLine());
		    while (num > 1) {
		        if (num % 2l == 0) {
		            num /= 2l;
		        } else {
		            break;
		        }
		    }
		    pw.println(num == 1 ? "NO" : "YES");
		}
		pw.close();
	}
}