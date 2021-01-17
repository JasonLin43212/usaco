import java.io.*;
import java.util.*;

public class homework {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("homework.in"));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] grades = new int[N];
		
		for (int i = 0; i < N; i++) {
		    grades[N-i-1] = Integer.parseInt(st.nextToken());
		}
		
//		System.out.println(Arrays.toString(grades));
		int[] reverseSum = new int[N+1];
		int[] pMin = new int[N+1];
		pMin[0] = Integer.MAX_VALUE;
		
		for (int i = 1; i < N + 1; i++) {
		    reverseSum[i] = reverseSum[i-1] + grades[i-1];
		    pMin[i] = Math.min(pMin[i-1], grades[i-1]);
		}
		
//		System.out.println(Arrays.toString(reverseSum) + "\n" + Arrays.toString(pMin));
		double maxScore = 0;
		List<Integer> kValues = new ArrayList<>();
		for (int i = 2; i < N; i++) {
		    double score = (reverseSum[i] - pMin[i]) / (double) (i - 1);
		    if (score > maxScore) {
		        maxScore = score;
		        kValues.clear();
		        kValues.add(N - i);
		    } else if (score == maxScore) {
		        kValues.add(N - i);
		    }
		}
		
//		System.out.println(maxScore + " " + kValues);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));
		kValues.sort((a, b) -> a - b);
		for (int k : kValues) {
		    pw.println(k);
		}
		pw.close();
	}
}