import java.io.*;
import java.util.*;

public class treediameter {
    
    public static int N;
    public static List<List<Integer>> edges = new ArrayList<>();
    public static int[] treeHeight;
    public static int[] dist;
    public static boolean[] seen;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		treeHeight = new int[N];
		dist = new int[N];
		seen = new boolean[N];
		for (int i = 0; i < N; i++) { edges.add(new ArrayList<>()); }
		for (int i = 0; i < N-1; i++) {
	        StringTokenizer st = new StringTokenizer(br.readLine());
		    int a = Integer.parseInt(st.nextToken())-1;
		    int b = Integer.parseInt(st.nextToken())-1;
		    edges.get(a).add(b);
		    edges.get(b).add(a);
		}
		
		getHeight(0);
		
//		System.out.println(Arrays.toString(dist));
		int maxDist = 0;
		for (int i = 0; i < N; i++) {
		    maxDist = Math.max(dist[i], maxDist);
		}
		System.out.println(maxDist);
	}
	
	public static int getHeight(int node) {
	    int height = 0;
	    int max1 = -1;
        int max2 = -1;
	    seen[node] = true;
	    for (int child: edges.get(node)) {
	        if (!seen[child]) {
	            int childHeight = getHeight(child);
	            height = Math.max(childHeight + 1, height);
	            if (childHeight > max1) {
	                max2 = max1;
	                max1 = childHeight;
	            } else if (childHeight > max2) {
	                max2 = childHeight;
	            }
	        }
	    }
	    
	    treeHeight[node] = height;
	    dist[node] = max1 + max2 + 2;
	    return height;
	}
}