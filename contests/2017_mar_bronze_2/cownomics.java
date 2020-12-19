import java.io.*;
import java.util.*;

public class cownomics {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cownomics.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		String[] spots = new String[N];
		String[] plain = new String[N];
		
		for (int i = 0; i < N; i++) {
		    spots[i] = br.readLine();
		}
		for (int i = 0; i < N; i++) {
            plain[i] = br.readLine();
        }
		
		int numPositions = 0;
		for (int m = 0; m < M; m++) {
		    // 0:A, 1:T, 2:G, 3:C
		    boolean[] seenLetters = new boolean[4];
		    for (int n = 0; n < N; n++) {
		        seenLetters[getLetterPos(spots[n].charAt(m))] = true;
		    }
		    
		    boolean badPosition = false;
		    for (int n = 0; n < N; n++) {
		        if (seenLetters[getLetterPos(plain[n].charAt(m))]) {
		            badPosition = true;
		            break;
		        }
		    }
		    
		    if (!badPosition) {
		        numPositions++;
		    }
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
		pw.println(numPositions);
		pw.close();
	}
	
	public static int getLetterPos(char c) {
	    if (c == 'A') {return 0;}
	    else if (c == 'T') {return 1;}
	    else if (c == 'G') {return 2;}
	    else if (c == 'C') {return 3;}
	    return -1;
	}
}