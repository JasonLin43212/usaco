import java.io.*;
import java.util.*;

class edge {
    public int a;
    public int b;
    
    public edge(int a, int b) {
        this.a = a;
        this.b = b;
    }
    
    public String toString() {
        return "(" + a + ", " + b + ")";
    }
}

public class dancemooves {
    
    public static int N;
    public static int K;
    public static List<edge> edgeOrder = new ArrayList<>();
    public static List<List<Integer>> edges = new ArrayList<>();
    public static int[] comps;
    public static List<Integer> compSize = new ArrayList<>();
    public static boolean[] seen;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[] cows = new int[N];
		comps = new int[N];
		seen = new boolean[N];
		Arrays.fill(comps, -1);
		
		List<Set<Integer>> numSeen = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
		    cows[i] = i;
		    edges.add(new ArrayList<>());
		    numSeen.add(new HashSet<>());
		    numSeen.get(i).add(i);
		}
		
		for (int i = 0; i < K; i++) {
		    st = new StringTokenizer(br.readLine());
		    int a = Integer.parseInt(st.nextToken()) - 1;
		    int b = Integer.parseInt(st.nextToken()) - 1;
		    edges.get(a).add(b);
		    edges.get(b).add(a);
		    edgeOrder.add(new edge(a, b));
		}
		
//		System.out.println(edges + " " + Arrays.toString(cows));
//		System.out.println(edgeOrder);
		
		int compNum = 0;
		for (int i = 0; i < N; i++) {
		    if (!seen[i]) {
		        compSize.add(0);
		        dfs(i, compNum);
		        compNum++;
		    }
		}
		
//		System.out.println("COMPS:" + Arrays.toString(comps) + " " + compSize);
		
		
		for (int i = 0; i < K; i++) {
		    edge swapEdge = edgeOrder.get(i);
		    int a = swapEdge.a;
		    int b = swapEdge.b;
		    int tmp = cows[a];
		    cows[a] = cows[b];
		    cows[b] = tmp;
		    numSeen.get(cows[b]).add(b);
		    numSeen.get(cows[a]).add(a);
//		    System.out.println(Arrays.toString(cows));
		}
//		System.out.println(numSeen);
		
		
		PrintWriter pw = new PrintWriter(System.out);
		for (int i = 0; i < N; i++) {
		    if (cows[i] != i) {
		        pw.println(compSize.get(comps[i]));
		    } else {
		        pw.println(numSeen.get(i).size());
		    }
		}
		pw.close();
	}
	
	public static void dfs(int start, int compNum) {
	    Stack<Integer> frontier = new Stack<>();
	    frontier.add(start);
	    
	    while (!frontier.isEmpty()) {
	        int node = frontier.pop();
	        if (seen[node]) {
	            continue;
	        }
	        
	        seen[node] = true;
	        comps[node] = compNum;
	        compSize.set(compNum, compSize.get(compNum) + 1);
	        
	        for (int next : edges.get(node)) {
	            frontier.add(next);
	        }
	    }
	}
}