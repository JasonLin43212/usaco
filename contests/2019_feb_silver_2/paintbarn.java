import java.io.*;
import java.util.*;

public class paintbarn {
    
    public static int[][] barn = new int[1002][1002];
    public static int[][] prefix = new int[1002][1002];
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    int a = Integer.parseInt(st.nextToken())+1;
		    int b = Integer.parseInt(st.nextToken())+1;
		    int c = Integer.parseInt(st.nextToken())+1;
		    int d = Integer.parseInt(st.nextToken())+1;
		    barn[a][b]++;
		    barn[c][d]++;
		    barn[a][d]--;
		    barn[c][b]--;
		}
		
		int numK = 0;
		for (int i = 1; i < 1002; i++) {
		    for (int j = 1; j < 1002; j++) {
		        prefix[i][j] = barn[i][j] + prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1];
		        if (prefix[i][j] == K) {
		            numK++;
		        }
		    }
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("paintbarn.out")));
		pw.println(numK);
		pw.close();
	}
}