import java.io.*;
import java.util.*;

public class promote {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("promote.in"));
		
		int[] before = new int[4];
		int[] after = new int[4];
		int beforeTotal = 0;
		int afterTotal = 0;
		
		for (int i = 0; i < 4; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    before[i] = Integer.parseInt(st.nextToken());
		    beforeTotal += before[i];
		    after[i] = Integer.parseInt(st.nextToken());
		    afterTotal += after[i];
		}
//		System.out.println(Arrays.toString(before));
//		System.out.println(Arrays.toString(after));
		
		before[0] += afterTotal - beforeTotal;
		
		int[] solution = new int[3];
		
		for (int i = 3; i > 0; i--) {
		    int promotion = after[i] - before[i];
		    solution[i-1] = promotion;
		    before[i-1] -= promotion;
		}
		
//		System.out.println(Arrays.toString(solution));
		
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("promote.out")));
		for (int i : solution) {
		    pw.println(i);
		}
		pw.close();
	}
}