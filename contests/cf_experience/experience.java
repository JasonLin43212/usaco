import java.io.*;
import java.util.*;

public class experience {
    
    static class dsu {
        public int[] parent;
        public int[] depth;
        public int[] add;
        
        public dsu(int n) {
            parent = new int[n];
            depth = new int[n];
            add = new int[n];
            Arrays.fill(depth, 1);
            
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int get(int n) {
            if (parent[n] == n) {
                return n;
            }
            return get(parent[n]);
        }
        
        public int getExp(int n) {
            if (parent[n] == n) {
                return add[n];
            }
            return add[n] + getExp(parent[n]);
        }
        
        public void join(int a, int b) {
            int rootA = get(a);
            int rootB = get(b);
            if (rootA == rootB) {
                return;
            }
            
            if (depth[rootA] > depth[rootB]) {
                parent[rootB] = rootA;
                add[rootB] -= add[rootA];
                depth[rootA] = Math.max(depth[rootA], depth[rootB] + 1);
            } else {
                parent[rootA] = rootB;
                add[rootA] -= add[rootB];
                depth[rootB] = Math.max(depth[rootB], depth[rootA] + 1);
            }
        }
        
        public void add(int n, int xp) {
            int root = get(n);
            add[root] += xp;
        }
    }
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		dsu players = new dsu(N);
		
		PrintWriter pw = new PrintWriter(System.out);

		for (int i = 0; i < M; i++) {
		    st = new StringTokenizer(br.readLine());
		    String op = st.nextToken();
		    int a = Integer.parseInt(st.nextToken()) - 1;
		    if (op.equals("add")) {
		        int b = Integer.parseInt(st.nextToken());
		        players.add(a, b);
		    } else if (op.equals("join")) {
		        int b = Integer.parseInt(st.nextToken()) - 1;
		        players.join(a, b);
		    } else if (op.equals("get")) {
		        pw.println(players.getExp(a));
		    }
		    
//		    System.out.println(Arrays.toString(players.parent));
//		    System.out.println(players.children);
//		    System.out.println(Arrays.toString(players.exp) + "\n");
		}
		
		pw.close();
	}
}