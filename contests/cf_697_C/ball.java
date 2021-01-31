import java.io.*;
import java.util.*;

public class ball {
    
    static class edge {
        public int a;
        public int b;
        
        public edge (int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		PrintWriter pw = new PrintWriter(System.out);
		for (int x = 0; x < t; x++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int[] boys = new int[a];
            int[] girls = new int[b];
            edge[] edges = new edge[k];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < k; i++) {
                int boy = Integer.parseInt(st.nextToken()) - 1;
                edges[i] = new edge(boy, -1);
                boys[boy]++;
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < k; i++) {
                int girl = Integer.parseInt(st.nextToken()) - 1;
                edges[i].b = girl;
                girls[girl]++;
            }
//            System.out.println(Arrays.toString(boys) + " " + Arrays.toString(girls));
            
            int numPairs = 0;
            for (int i = 0; i < k; i++) {
                edge curEdge = edges[i];
                numPairs += k - boys[curEdge.a] - girls[curEdge.b] + 1;
            }
            pw.println(numPairs / 2);
		}
		pw.close();
	}
}