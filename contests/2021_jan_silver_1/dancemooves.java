import java.io.*;
import java.util.*;

public class dancemooves {
    
    public static int N;
    public static int K;
    public static int[] numVisited;
    public static int[] cows;
    public static boolean[] seen;
    public static Set<Integer>[] visited;
    
    public static int[] edges;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		cows = new int[N];
		numVisited = new int[N];
		seen = new boolean[N];
		
		visited = new HashSet[N];
		for (int i = 0; i < N; i++) {
		    cows[i] = i;
		    visited[i] = new HashSet<>();
		    visited[i].add(i);
		}
		
		for (int i = 0; i < K; i++) {
		    st = new StringTokenizer(br.readLine());
		    int a = Integer.parseInt(st.nextToken()) - 1;
		    int b = Integer.parseInt(st.nextToken()) - 1;
		    int cowA = cows[a];
		    int cowB = cows[b];
		    
		    int temp = cowA;
		    cows[a] = cowB;
		    cows[b] = temp;
		    visited[cowA].add(b);
            visited[cowB].add(a);
		}
		
//		System.out.println(Arrays.toString(cows));
//		System.out.println(Arrays.toString(visited));
		
		edges = new int[N];
		for (int i = 0; i < N; i++) {
		    edges[cows[i]] = i;
		}
//		System.out.println(Arrays.toString(edges));
		
		
		for (int i = 0; i < N; i++) {
		    if (!seen[i]) {
		        dfs(i);
		    }
		}
		
		PrintWriter pw = new PrintWriter(System.out);
		for (int i = 0; i < N; i++) {
		    pw.println(numVisited[i]);
		}
		pw.close();
	}
	
	public static void dfs(int start) {
	    Stack<Integer> frontier = new Stack<>();
	    frontier.add(start);
	    
	    ArrayList<Integer> comp = new ArrayList<>();
	    Set<Integer> collectiveVisit = new HashSet<>();
	    
	    while (!frontier.isEmpty()) {
	        int node = frontier.pop();
	        if (seen[node]) {
	            continue;
	        }
	        
	        seen[node] = true;
	        comp.add(node);
	        collectiveVisit.addAll(visited[node]);
	        
	        frontier.add(edges[node]);
	    }
	    
	    for (int node : comp) {
	        numVisited[node] = collectiveVisit.size();
	    }
	}
}