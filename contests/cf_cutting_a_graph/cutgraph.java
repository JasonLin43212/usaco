import java.io.*;
import java.util.*;

public class cutgraph {
    
    static class op {
        public int a;
        public int b;
        public String type;
        
        public op(int a, int b, String type) {
            this.a = a;
            this.b = b;
            this.type = type;
        }
        
        public String toString() {
            return "(" + type + ", " + a + ", " + b + ")";
        }
    }
    
    static class dsu {
        public int[] p;
        public int[] d;
        
        public dsu(int n) {
            p = new int[n];
            d = new int[n];
            
            Arrays.fill(d, 1);
            for (int i = 0; i < n; i++) {
                p[i] = i;
            }
        }
        
        public int getP(int n) {
            if (p[n] == n) {
                return n;
            }
            p[n] = getP(p[n]);
            return p[n];
        }
        
        public void unite(int a, int b) {
            int rootA = getP(a);
            int rootB = getP(b);
            if (rootA == rootB) {
                return;
            }
            
            if (d[rootA] > d[rootB]) {
                p[rootB] = rootA;
                d[rootA] = Math.max(d[rootA], d[rootB] + 1);
            } else {
                p[rootA] = rootB;
                d[rootB] = Math.max(d[rootB], d[rootA] + 1);
            }
        }
    }
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		List<Set<Integer>> edges = new ArrayList<>();
		for (int i = 0; i < N; i++) {
		    edges.add(new HashSet<>());
		}
		
		for (int i = 0; i < M; i++) {
		    st = new StringTokenizer(br.readLine());
		    int a = Integer.parseInt(st.nextToken()) - 1;
		    int b = Integer.parseInt(st.nextToken()) - 1;
		    edges.get(a).add(b);
		    edges.get(b).add(a);
		}
		
		op[] operations = new op[K];
		for (int i = 0; i < K; i++) {
		    st = new StringTokenizer(br.readLine());
		    String opType = st.nextToken();
		    int a = Integer.parseInt(st.nextToken()) - 1;
		    int b = Integer.parseInt(st.nextToken()) - 1;
		    
		    if (opType.equals("cut")) {
		        edges.get(a).remove(b);
		        edges.get(b).remove(a);
		    }
		    operations[i] = new op(a, b, opType);
		}
		
		dsu set = new dsu(N);
		for (int i = 0; i < N; i++) {
		    for (int b : edges.get(i)) {
		        set.unite(i, b);
		    }
		}
		
		List<String> out = new ArrayList<>();
		for (int i = K - 1; i >= 0; i--) {
		    op curOp = operations[i];
		    if (curOp.type.equals("ask")) {
		        out.add(set.getP(curOp.a) == set.getP(curOp.b) ? "YES" : "NO");
		    } else {
		        set.unite(curOp.a, curOp.b);
		    }
		}
		
        PrintWriter pw = new PrintWriter(System.out);
		for (int i = out.size() - 1; i >= 0; i--) {
		    pw.println(out.get(i));
		}
		pw.close();
	}
}