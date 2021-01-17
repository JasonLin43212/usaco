import java.io.*;
import java.util.*;

public class lemonade {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("lemonade.in"));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Integer[] cows = new Integer[N];
		for (int i = 0; i < N; i++) {
		    cows[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(cows, (a, b) -> b - a);
//		System.out.println(Arrays.toString(cows));
		
		int numCows = 0;
		for (int i = 0; i < N; i++) {
		    if (cows[i] >= numCows) {
		        numCows++;
		    }
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lemonade.out")));
		pw.println(numCows);
		pw.close();
	}
}