import java.io.*;
import java.util.*;

public class blocks {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("blocks.in"));		
		int N = Integer.parseInt(br.readLine());
		
		int[] letters = new int[26];
		
		for (int i = 0; i < N; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    int[] firstLetters = new int[26]; 
		    String firstWord = st.nextToken();
		    for (int a = 0; a < firstWord.length(); a++) {
		        firstLetters[((int) firstWord.charAt(a)) - 97]++;
		    }
		    
		    int[] secondLetters = new int[26]; 
            String secondWord = st.nextToken();
            for (int a = 0; a < secondWord.length(); a++) {
                secondLetters[((int) secondWord.charAt(a)) - 97]++;
            }
            
            for (int j = 0; j < 26; j++) {
                letters[j] += Math.max(firstLetters[j], secondLetters[j]);
            }
		}
		
//		System.out.println(Arrays.toString(letters));
		
		
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("blocks.out")));
		
		for (int i = 0; i < 26; i++) {
		    pw.println(letters[i]);
		}
		
		pw.close();
	}
}