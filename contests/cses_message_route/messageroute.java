import java.io.*;
import java.util.*;

class pair {
    public int node;
    public int parent;
    
    public pair (int node, int parent) {
        this.node = node;
        this.parent = parent;
    }
    
    public String toString() {
        return "( node: " + node + ", parent: " + parent + ")"; 
    }
}

public class messageroute {
    
    public static int N;
    public static int M;
    public static List<List<Integer>> edges = new ArrayList<>();
    public static int[] parents;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N];
		Arrays.fill(parents, -1);
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
		
		bfs();
//		System.out.println(Arrays.toString(parents));
		
		List<Integer> path = new ArrayList<>();
		int curNode = N - 1;
		while (curNode != -1) {
		    path.add(curNode);
		    curNode = parents[curNode];
		}
		
		PrintWriter pw = new PrintWriter(System.out);
		if (path.get(path.size() - 1) == 0) {
		    StringBuilder sb = new StringBuilder();
		    for (int i = path.size() - 1; i > 0; i--) {
		        sb.append((path.get(i) + 1) + " ");
		    }
		    sb.append(path.get(0) + 1);
		    pw.println(path.size());
		    pw.println(sb.toString());
		} else {
		    pw.println("IMPOSSIBLE");
		}
		
		
		
		pw.close();
	}
	
	public static void bfs() {
	    Queue<pair> frontier = new LinkedList<>();
	    boolean[] seen = new boolean[N];
	    frontier.add(new pair(0, -1));
	    
	    while (!frontier.isEmpty()) {
	        pair curPair = frontier.poll();
	        int node = curPair.node;
	        int parent = curPair.parent;
	        
	        if (seen[node]) {
	            continue;
	        }
	        
	        seen[node] = true;
	        if (parents[node] == -1) {
	            parents[node] = parent;
	        }
	        
	        for (int nextNode : edges.get(node)) {
	            frontier.add(new pair(nextNode, node));
	        }
	    }
	}
}