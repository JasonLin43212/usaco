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
		
		int numTriples = 0;
		for (int m1 = 0; m1 < M; m1++) {
		    for (int m2 = m1 + 1; m2 < M; m2++) {
		        for (int m3 = m2 + 1; m3 < M; m3++) {
		            // A:0, T:1, G:2, C:3
		            boolean[][][] seen = new boolean[4][4][4];
		            for (int n = 0; n < N; n++) {
		                String gene = spots[n];
		                seen[getIndex(gene.charAt(m1))][getIndex(gene.charAt(m2))][getIndex(gene.charAt(m3))] = true;
		            }
		            
		            boolean badPos = false;
		            for (int n = 0; n < N; n++) {
		                String gene = plain[n];
                        if (seen[getIndex(gene.charAt(m1))][getIndex(gene.charAt(m2))][getIndex(gene.charAt(m3))]) {
                            badPos = true;
                            break;
                        }
		            }
		            
		            if (!badPos) {
		                numTriples++;
		            }
		        }
		    }
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
		pw.println(numTriples);
		pw.close();
	}
	
	public static int getIndex(char c) {
	    if (c == 'A') return 0;
	    else if (c == 'T') return 1;
	    else if (c == 'G') return 2;
	    else if (c == 'C') return 3;
	    return -1;
	}
}