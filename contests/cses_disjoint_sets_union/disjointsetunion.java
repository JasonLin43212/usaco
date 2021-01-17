import java.io.*;
import java.util.*;


public class disjointsetunion {
    
    static class dsu {
        public int[] parent;
        public int[] size;
        
        public dsu (int n) {
            parent = new int[n];
            size = new int[n];
            Arrays.fill(size, 1);
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int get(int n) {
            if (parent[n] == n) {
                return n;
            }
            parent[n] = get(parent[n]);
            return parent[n];
        }
        
        public void union(int a, int b) {
            int aParent = get(a);
            int bParent = get(b);
            if (aParent == bParent) {
                return;
            }
            
            if (size[aParent] > size[bParent]) {
                parent[bParent] = aParent;
                size[aParent] = Math.max(size[aParent], size[bParent] + 1);
            } else {
                parent[aParent] = bParent;
                size[bParent] = Math.max(size[bParent], size[aParent] + 1);
            }
        }
    }
    
    public static int N;
    public static int M;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dsu aSet = new dsu(N);
		
		PrintWriter pw = new PrintWriter(System.out);
		for (int i = 0 ; i < M; i++) {
		    st = new StringTokenizer(br.readLine());
		    String op = st.nextToken();
		    int a = Integer.parseInt(st.nextToken()) - 1;
		    int b = Integer.parseInt(st.nextToken()) - 1;
		    if (op.equals("union")) {
		        aSet.union(a, b);
		    } else if (op.equals("get")) {
		        pw.println(aSet.get(a) == aSet.get(b) ? "YES" : "NO");
		    }
		}
		
		pw.close();
	}
}