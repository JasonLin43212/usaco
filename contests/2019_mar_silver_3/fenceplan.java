import java.io.*;
import java.util.*;

public class fenceplan {
    
    public static int N;
    public static int M;
    public static List<List<Integer>> edges = new ArrayList<>();
    public static int[] xs;
    public static int[] ys;
    public static boolean[] seen;
    
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("fenceplan.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		xs = new int[N];
		ys = new int[N];
		seen = new boolean[N];
		
		for (int i = 0; i < N; i++) {
		    edges.add(new ArrayList<>());
		    st = new StringTokenizer(br.readLine());
		    xs[i] = Integer.parseInt(st.nextToken());
		    ys[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < M; i++) {
		    st = new StringTokenizer(br.readLine());
		    int a = Integer.parseInt(st.nextToken())-1;
		    int b = Integer.parseInt(st.nextToken())-1;
		    edges.get(a).add(b);
		    edges.get(b).add(a);
		}
		
//		System.out.println(edges);
		
		int minPerim = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
		    if (!seen[i]) {
		        minPerim = Math.min(minPerim, dfs(i));
		    }
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fenceplan.out")));
		pw.println(minPerim);
		pw.close();
	}
	
	public static int dfs (int start) {
	    int minX = Integer.MAX_VALUE;
	    int maxX = Integer.MIN_VALUE;
	    int minY = Integer.MAX_VALUE;
	    int maxY = Integer.MIN_VALUE;
	    
	    Stack<Integer> frontier = new Stack<>();
	    frontier.add(start);
	    
	    while (!frontier.isEmpty()) {
	        int curCow = frontier.pop();
	        if (seen[curCow]) {
	            continue;
	        }
	        seen[curCow] = true;
	        
            minX = Math.min(xs[curCow], minX);
            maxX = Math.max(xs[curCow], maxX);
            minY = Math.min(ys[curCow], minY);
            maxY = Math.max(ys[curCow], maxY);
	        
	        for (int other : edges.get(curCow)) {
	            frontier.add(other);
	        }
	    }
	    
	    return (maxX - minX) * 2 + (maxY - minY) * 2;
	}
}