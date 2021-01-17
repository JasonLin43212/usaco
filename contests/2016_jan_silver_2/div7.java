import java.io.*;
import java.util.*;

public class div7 {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("div7.in"));
		int N = Integer.parseInt(br.readLine());
		long[] prefix = new long[N+1];
		
		for (int i = 1; i < N + 1; i++) {
		    prefix[i] = prefix[i-1]  + Long.parseLong(br.readLine());
		}
		
		int largeGroup = 0;
		for (int i = N; i > 0; i--) {
		    if (largeGroup != 0) {
		        break;
		    }
		    for (int j = 0; j < N - i + 1; j++) {
		        if ((prefix[j + i] - prefix[j]) % 7 == 0) {
		            largeGroup = i;
		            break;
		        }
		    }
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));
		pw.println(largeGroup);
		pw.close();
	}
}