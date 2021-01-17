import java.io.*;
import java.util.*;




public class capital {
    static class pair {
        public int node;
        public int dist;
        
        public pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
        
        public String toString() {
            return "(" + node + ", " + dist + ")";
        }
    }
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
		    br.readLine();
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    int n = Integer.parseInt(st.nextToken());
		    int m = Integer.parseInt(st.nextToken());
		    List<List<Integer>> edges = new ArrayList<>();
		    for (int j = 0; j < n; j++) {
		        edges.add(new ArrayList<>());
		    }
		    for (int j = 0; j < m; j++) {
		        st = new StringTokenizer(br.readLine());
		        int a = Integer.parseInt(st.nextToken())-1;
		        int b = Integer.parseInt(st.nextToken())-1;
		        edges.get(a).add(b);
		    }
		    int[] d = getDs(edges);
//		    System.out.println("EDGES" + edges);
//		    System.out.println(Arrays.toString(d));
		    
		    int[] out = new int[n];
		    Arrays.fill(out,-1);
		    for (int k = n-1; k >= 0; k--) {
		        if (out[k] == -1) {
		            dfs(k, edges, d, out);
		        }
		        
		    }
		    
		    StringBuilder sb = new StringBuilder();
		    for (int k = 0; k < n -1; k++) {
	            sb.append(out[k] + " ");		        
		    }
		    sb.append(out[n-1]);
		    System.out.println(sb.toString());
		}
		
	}
	
	public static int[] getDs(List<List<Integer>> edges) {
	    int[] ds = new int[edges.size()];
	    Deque<pair> frontier = new ArrayDeque<>();
	    frontier.add(new pair(0, 0));
	    boolean[] seen = new boolean[edges.size()];
	    
	    while (!frontier.isEmpty()) {
	        pair nextPair = frontier.poll();
	        int nextNode = nextPair.node;
	        int dist = nextPair.dist;
	        if (seen[nextNode]) {
	            continue;
	        }
	        seen[nextNode] = true;
	        ds[nextNode] = dist;
	        
	        for (int neighbor : edges.get(nextNode)) {
	            frontier.add(new pair(neighbor, dist+1));
	        }
	    }
	    return ds;
	}
	
	public static void dfs(int start, List<List<Integer>> edges, int[] d, int[] out) {
	    Stack<pair> frontier = new Stack<>();
	    frontier.add(new pair(start, 0));
	    int minD = Integer.MAX_VALUE;
	    boolean[][] seen = new boolean[2][edges.size()];
	    boolean[] seenNode = new boolean[edges.size()];
	    
	    while (!frontier.isEmpty()) {
	        pair aPair = frontier.pop();
	        int node = aPair.node;
	        int hasReversed = aPair.dist;
	        if (seen[hasReversed][node]) {
	            continue;
	        }
	        seen[hasReversed][node] = true;
	        seenNode[node] = true;
	        minD = Math.min(minD, d[node]);
	        
	        for (int next : edges.get(node)) {
	            if (hasReversed == 0) {
	                if (d[node] >= d[next]) {
	                    frontier.add(new pair(next, 1));
	                } else {
	                    frontier.add(new pair(next, 0));
	                }
	            } else {
	                if (d[node] < d[next]) {
	                    frontier.add(new pair(next, 1));
	                }
	            }
	        }
	    }
	    
	    for (int i = 0; i < edges.size(); i++) {
	        if (seenNode[i] && out[i] == -1) {
	            out[i] = minD;
	        }
	    }
	}
}