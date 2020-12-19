import java.io.*;
import java.util.*;

public class mixmilk {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("mixmilk.in"));
		
		int[] capacities = new int[3];
		int[] amounts = new int[3];
		
		for (int i = 0; i < 3; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    capacities[i] = Integer.parseInt(st.nextToken());
		    amounts[i] = Integer.parseInt(st.nextToken());
		}
		
		// System.out.println(Arrays.toString(capacities) + " " + Arrays.toString(amounts));
		
		for (int i = 0; i < 100; i++) {
		    int delta = i % 3;
		    int pourBucket = delta;
		    int fillBucket = (delta + 1) % 3;
		    
	        int change = Math.min(amounts[pourBucket], capacities[fillBucket] - amounts[fillBucket]);
	        amounts[pourBucket] -= change;
	        amounts[fillBucket] += change;
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mixmilk.out")));
		for (int i = 0; i < 3; i++) {
		    pw.println(amounts[i]);
		}
		pw.close();
	}
}