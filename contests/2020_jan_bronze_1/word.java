import java.io.*;
import java.util.*;

public class word {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("word.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("word.out")));
		
		int curLength = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
		    String curWord = st.nextToken();
		    if (curLength == 0) {
		        curLength = curWord.length();
                pw.print(curWord);
		    }
		    else if (curWord.length() + curLength > K) {
		        curLength = curWord.length();
		        pw.print("\n" + curWord);
		    } else {
		        pw.print(" " + curWord);
		        curLength += curWord.length();
		    }
		}
		pw.println("");
		pw.close();
	}
}