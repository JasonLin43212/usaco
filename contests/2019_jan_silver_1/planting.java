import java.io.*;
import java.util.*;

public class planting {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("planting.in"));
		int N = Integer.parseInt(br.readLine());
		
		int[] degrees = new int[N];
		
		for (int i = 0; i < N - 1; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    int a = Integer.parseInt(st.nextToken());
		    int b = Integer.parseInt(st.nextToken());
		    
		    degrees[a-1]++;
		    degrees[b-1]++;
		}
		
		// Number of grass types = max degree of a node + 1 (hopefully)
		
//		System.out.println(Arrays.toString(degrees));
		int maxDegree = 0;
		for (int i = 0; i < N; i++) {
		    maxDegree = Math.max(maxDegree, degrees[i]);
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));
		pw.println(maxDegree + 1);
		pw.close();
	}
}