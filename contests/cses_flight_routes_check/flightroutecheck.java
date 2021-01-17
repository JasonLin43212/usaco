import java.io.*;
import java.util.*;

public class flightroutecheck {
    
    public static int N;
    public static int M;
    public static List<List<Integer>> edges = new ArrayList<>();
    public static List<List<Integer>> rEdges = new ArrayList<>();
    public static boolean[] zeroReach;
    public static boolean[] reachZero;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
		    edges.add(new ArrayList<>());
		    rEdges.add(new ArrayList<>());
		}
		zeroReach = new boolean[N];
		reachZero = new boolean[N];
		
		for (int i = 0; i < M; i++) {
		    st = new StringTokenizer(br.readLine());
		    int a = Integer.parseInt(st.nextToken()) - 1;
		    int b = Integer.parseInt(st.nextToken()) - 1;
		    edges.get(a).add(b);
		    rEdges.get(b).add(a);
		}
		
		dfs(edges, zeroReach);
		dfs(rEdges, reachZero);
		
		boolean canReach = true;
		for (int i = 0; i < N; i++) {
		    if (!zeroReach[i]) {
		        System.out.println("NO\n1 " + (i+1));
		        canReach = false;
		        break;
		    } else if (!reachZero[i]) {
                System.out.println("NO\n" + (i+1) + " 1");	
                canReach = false;
                break;
		    }
		}
		
		if (canReach) {
		    System.out.println("YES");
		}
	}
	
	public static void dfs(List<List<Integer>> edge, boolean[] reach) {
	    Stack<Integer> frontier = new Stack<>();
	    frontier.add(0);
	    
	    while (!frontier.isEmpty()) {
	        int node = frontier.pop();
	        if (reach[node]) {
	            continue;
	        }
	        
	        reach[node] = true;
	        for (int nextNode : edge.get(node)) {
	            frontier.add(nextNode);
	        }
	    }
	}
}