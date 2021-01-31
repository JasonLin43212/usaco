import java.io.*;
import java.util.*;

public class hps {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("hps.in"));
		int N = Integer.parseInt(br.readLine());
		char[] letters = new char[N];
		
		for (int i = 0; i < N; i++) {
		    letters[i] = br.readLine().charAt(0);
		}
		
//		System.out.println(Arrays.toString(letters));
		
//		char[] hands = new char[] {'H', 'P', 'S'};
		char[] beats = new char[] {'S', 'H', 'P'};
		
		int maxWins = 0;
		for (int i = 0; i < 3; i++) {
		    for (int j = 0; j < 3; j++) {
		        int[] prefix = new int[N + 1];
		        int[] suffix = new int[N + 1];
		        for (int k = 1; k <= N; k++) {
		            prefix[k] = prefix[k-1] + 
		                    (letters[k-1] == beats[i] ? 1 : 0);
		            suffix[N - k] = suffix[N - k + 1] + 
                            (letters[N-k] == beats[j] ? 1 : 0);
		        }
//		        System.out.println(i + " " + j);
//                System.out.println("prefix: " + Arrays.toString(prefix));
//                System.out.println("suffix: " + Arrays.toString(suffix));
		        for (int x = 0; x < N + 1; x++) {
		            maxWins = Math.max(maxWins, prefix[x] + suffix[x]);
		        }
//		        System.out.println(maxWins);
		    }
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		pw.println(maxWins);
		pw.close();
	}
}