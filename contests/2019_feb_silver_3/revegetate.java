import java.io.*;
import java.util.*;

class edge {
    public int dest;
    public boolean isSame;
    
    public edge(int dest, boolean isSame) {
        this.dest = dest;
        this.isSame = isSame;
    }
    
    public String toString() {
        return "( dest: " + dest + ", isSame: " + isSame + ")"; 
    }
}

class pair {
    public int node;
    public int color;
    
    public pair(int node, int color) {
        this.node = node;
        this.color = color;
    }
    
    public String toString() {
        return "( node: " + node + ", color: " + color + ")";
    }
}

public class revegetate {
    
    public static int N;
    public static int M;
    public static List<List<edge>> edges = new ArrayList<>();
    public static int[] colors;
    public static boolean[] seen;
    public static int numComps = 0;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("revegetate.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		colors = new int[N];
		seen = new boolean[N];
		for (int i = 0; i < N; i++) { edges.add(new ArrayList<>()); }
		for (int i = 0; i < M; i++) {
		    st = new StringTokenizer(br.readLine());
		    boolean isSame = st.nextToken().equals("S");
		    int a = Integer.parseInt(st.nextToken()) - 1;
		    int b = Integer.parseInt(st.nextToken()) - 1;
		    edges.get(a).add(new edge(b, isSame));
		    edges.get(b).add(new edge(a, isSame));
		}
		
//		System.out.println(edges);
		boolean isImpossible = checkImpossible();
//		System.out.println(isImpossible);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("revegetate.out")));
		if (isImpossible) {
		    pw.println("0");
		} else {
		    StringBuilder out = new StringBuilder("1");
		    for (int i = 0; i < numComps; i++) {
		        out.append("0");
		    }
		    pw.println(out.toString());
		}
		pw.close();
	}
	
	public static boolean checkImpossible() {
	    for (int i = 0; i < N; i++) {
	        if (!seen[i]) {
	            numComps++;
	            boolean valid = dfs(i, 1);
	            if (!valid) {
	                return true;
	            }
	        }
	    }
	    return false;
	}
	
	public static boolean dfs(int node, int color) {
	    
	    Stack<pair> frontier = new Stack<>();
	    frontier.add(new pair(node, color));
	    while (!frontier.isEmpty()) {
//	        System.out.println(frontier);
	        pair curPair = frontier.pop();
//	        System.out.println("On  "  + curPair);
	        int curNode = curPair.node;
	        int curColor = curPair.color;
	        if (seen[curNode]) {
//	            System.out.println("current: " + curColor + ", has: " + colors[curNode]);
	            if (curColor != colors[curNode]) {
	                return false;
	            }
	            continue;
	        }
	        
	        seen[curNode] = true;
	        colors[curNode] = curColor;
	        
	        for (edge other : edges.get(curNode)) {
	            int nextColor = other.isSame ?
	                            curColor :
	                            3 - curColor;
	            frontier.add(new pair(other.dest, nextColor));
	        }
	    }
	    return true;
	}
}