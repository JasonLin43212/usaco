import java.io.*;
import java.util.*;

public class shell {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("shell.in"));
		int N = Integer.parseInt(br.readLine());
		
		int[] shellSpots = new int[] {1, 2, 3};
		
		int[] correctGuesses = new int[] {0, 0, 0};

		for (int i = 0; i < N; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    int swapA = Integer.parseInt(st.nextToken());
		    int swapB = Integer.parseInt(st.nextToken());
		    int guess = Integer.parseInt(st.nextToken());
		    
		    for (int j = 0; j < 3; j++) {
		        if (shellSpots[j] == swapA) {
		            shellSpots[j] = swapB;
		        } else if (shellSpots[j] == swapB) {
		            shellSpots[j] = swapA;
		        }
		        
		        if (shellSpots[j] == guess) {
		            correctGuesses[j]++;
		        }
		    }
		}
		
		int maxCorrect = 0;
		for (int i = 0; i < 3; i++) {
		    maxCorrect = Math.max(maxCorrect, correctGuesses[i]);
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shell.out")));
		pw.println(maxCorrect);
		pw.close();
	}
}