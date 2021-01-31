import java.io.*;
import java.util.*;

public class closing {
    
    static class dsu {
        public int[] p;
        public int[] size;
        
        public dsu(int n) {
            p = new int[n];
            size = new int[n];
            Arrays.fill(size, 1);
            for (int i = 0; i < n; i++) {
                p[i] = i;
            }
        }
        
        public int get(int n) {
            if (p[n] == n) {
                return n;
            }
            p[n] = get(p[n]);
            return p[n];
        }
        
        public boolean union(int a, int b ) {
            int rootA = get(a);
            int rootB = get(b);
            if (rootA == rootB) {
                return false;
            }
            
            if (size[rootA] > size[rootB]) {
                p[rootB] = rootA;
                size[rootA] = Math.max(size[rootA], size[rootB] + 1);
            } else {
                p[rootA] = rootB;
                size[rootB] = Math.max(size[rootB], size[rootA] + 1);
            }
            return true;
        }
    }
    
    public static int N;
    public static int M;
    public static List<List<Integer>> edges = new ArrayList<>();
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("closing.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
		    edges.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
		    st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            edges.get(a).add(b);
            edges.get(b).add(a);
		}
		dsu set = new dsu(N);
		int[] closings = new int[N];
		for (int i = 0; i < N; i++) {
		    closings[i] = Integer.parseInt(br.readLine()) - 1;
		}
		
//		System.out.println(Arrays.toString(closings) + " " + edges);
		String[] out = new String[N];
		boolean[] isOpen = new boolean[N];
		int comps = 0;
		for (int i = N - 1; i >= 0; i--) {
		    int start = closings[i];
		    isOpen[start] = true;
		    comps++;
		    
		    for (int node : edges.get(start)) {
                if (isOpen[node]) {
                    boolean merged = set.union(start, node);
                    if (merged) {
                        comps--;
                    }
                }
            }
//		    System.out.println(opened);
//		    System.out.println("DSU: " + Arrays.toString(set.p)); 
            out[i] = comps <= 1 ? "YES" : "NO";
		}
//		System.out.println(Arrays.toString(out));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
		for (int i = 0; i < N; i++) {
		    pw.println(out[i]);
		}
		pw.close();
	}
}