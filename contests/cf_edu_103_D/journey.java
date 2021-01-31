import java.io.*;
import java.util.*;

public class journey {
    
    public static int n;
    public static ArrayList<Integer>[][] edges;
    public static int[] numVisits;
    public static boolean[] seen;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

		for (int z = 0; z < t; z++) {
		    n = Integer.parseInt(br.readLine());
		    edges = new ArrayList[2][n + 1];
		    numVisits = new int[n + 1];
		    seen = new boolean[n + 1];
		    
		    for (int i = 0; i < 2; i++) {
		        for (int j = 0; j < n + 1; j++) {
		            edges[i][j] = new ArrayList<>();
		        }
		    }
		    String roads = br.readLine();

		    for (int i = 0; i < n; i++) {
		        if (roads.charAt(i) == 'L' ) {
		            edges[0][i+1].add(i);
		            edges[1][i].add(i+1);
		        } else {
		            edges[0][i].add(i+1);
		            edges[1][i+1].add(i);
		        }
		    }
//            System.out.println(Arrays.deepToString(edges) + " " + roads);
            for (int i = 0; i < n + 1; i++) {
                if (!seen[i]) {
                    dfs(i);
                }
            }
            
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(numVisits[i] + " ");
            }
            sb.append(numVisits[n]);
            pw.println(sb.toString());
		}
		pw.close();
	}
	
	static class pair {
	    public int node;
	    public int layer;
	    
	    public pair(int node, int layer) {
	        this.node = node;
	        this.layer = layer;
	    }
	}
	
	public static void dfs(int start) {
	    Stack<pair> frontier = new Stack<>();
	    frontier.add(new pair(start, 0));
	    int visits = 0;
	    boolean[] thisSeen = new boolean[n+1]; 
	    List<Integer> cityStart = new ArrayList<>();
	    
	    while (!frontier.isEmpty()) {
	        pair curPair = frontier.pop();
	        int node = curPair.node;
	        int layer = curPair.layer;
	        if (thisSeen[node]) {
	            continue;
	        }
	        
	        thisSeen[node] = true;
	        
	        visits++;
	        if (layer == 0) {
	            cityStart.add(node);
	            seen[node] = true;
	        }
	        
	        for (int nextNode : edges[layer][node]) {
	            frontier.add(new pair(nextNode, 1 - layer));
	        }
	    }
	    
	    for (int city : cityStart) {
	        numVisits[city] = visits;
	    }
	}
}