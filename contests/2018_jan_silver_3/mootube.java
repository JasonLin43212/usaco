import java.io.*;
import java.util.*;

class edge {
    public int dest;
    public int rel;
    
    public edge(int dest, int rel) {
        this.dest = dest;
        this.rel = rel;
    }
    
    public String toString() {
        return "( Dest: " + dest + ", rel: " + rel + ")";
    }
}

public class mootube {
    
    public static int N;
    public static int Q;
    public static List<List<edge>> edges = new ArrayList<>();
    
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("mootube.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) { edges.add(new ArrayList<>()); }
		for (int i = 0; i < N - 1; i++) {
		    st = new StringTokenizer(br.readLine());
		    int a = Integer.parseInt(st.nextToken()) - 1;
		    int b = Integer.parseInt(st.nextToken()) - 1;
		    int rel = Integer.parseInt(st.nextToken());
		    edges.get(a).add(new edge(b, rel));
		    edges.get(b).add(new edge(a, rel));
		}
		
//		System.out.println(Arrays.deepToString(relevance));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		for (int i = 0; i < Q; i++) {
		    st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken()) - 1;
            pw.println(dfs(v, k));
		}
		
		pw.close();
	}
	
	public static int dfs(int start, int k) {
	    Stack<edge> frontier = new Stack<>();
	    frontier.add(new edge(start, -1));
	    boolean[] seen = new boolean[N];
	    int numVids = -1;
	    while (!frontier.isEmpty()) {
	        edge nextEdge = frontier.pop();
	        int nextNode = nextEdge.dest;
	        int minRel = nextEdge.rel;
	        
	        if (seen[nextNode]) {
	            continue;
	        }
	        numVids++;
	        seen[nextNode] = true;
	        
	        for (edge child : edges.get(nextNode)) {
	            if (child.rel < k) {
	                continue;
	            }
	            if (minRel == -1) {
	                frontier.add(new edge(child.dest, child.rel));
	            } else {
	                frontier.add(new edge(child.dest, Math.min(child.rel, minRel)));
	            }
	        }
	    }
	    return numVids;
	}
}