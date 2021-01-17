import java.io.*;
import java.util.*;

public class disjointset2 {
    
    static class dsu {
        public int[] parent;
        public int[] size;
        public int[] max;
        public int[] min;
        public int[] cnt;
        
        public dsu(int n) {
            parent = new int[n];
            size = new int[n];
            max = new int[n];
            min = new int[n];
            cnt = new int[n];
            
            Arrays.fill(size, 1);
            Arrays.fill(cnt, 1);
            
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                max[i] = i;
                min[i] = i;
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
            int aRoot = get(a);
            int bRoot = get(b);
            if (aRoot == bRoot) {
                return;
            }
            
            if (size[aRoot] > size[bRoot]) {
                parent[bRoot] = aRoot;
                size[aRoot] = Math.max(size[aRoot], size[bRoot] + 1);
                cnt[aRoot] += cnt[bRoot];
                min[aRoot] = Math.min(min[aRoot], min[bRoot]);
                max[aRoot] = Math.max(max[aRoot], max[bRoot]); 
            } else {
                parent[aRoot] = bRoot;
                size[bRoot] = Math.max(size[bRoot], size[aRoot] + 1);
                cnt[bRoot] += cnt[aRoot];
                min[bRoot] = Math.min(min[aRoot], min[bRoot]);
                max[bRoot] = Math.max(max[aRoot], max[bRoot]);
            }
        }
        
        public String info(int n) {
            int root = get(n);
            return (min[root] + 1) + " " + (max[root] + 1) + " " + cnt[root];
        }
    }
    
    public static int N;
    public static int M;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		PrintWriter pw = new PrintWriter(System.out);
		
		dsu aSet = new dsu(N);
		for (int i = 0; i < M; i++) {
		    st = new StringTokenizer(br.readLine());
		    String op = st.nextToken();
		    int a = Integer.parseInt(st.nextToken()) - 1;
		    
		    if (op.equals("union")) {
		        int b = Integer.parseInt(st.nextToken()) - 1;
		        aSet.union(a, b);
		    } else if (op.equals("get")) {
		        pw.println(aSet.info(a));
		    }
		}
		
		pw.close();
	}
}