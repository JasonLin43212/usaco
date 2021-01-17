import java.io.*;
import java.util.*;

public class buildingroads {
    
    public static int N;
    public static int M;
    public static List<List<Integer>> edges = new ArrayList<>();
    public static List<List<Integer>> components = new ArrayList<>();
    public static boolean[] seen;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        seen = new boolean[N];
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
        
//        System.out.println(edges);
        
        for (int i = 0; i < N; i++) {
            if (!seen[i]) {
                components.add(new ArrayList<>());
                dfs(i);
            }
            
        }
//        System.out.println(components);
        System.out.println(components.size() - 1);
        for (int i = 1; i < components.size(); i++) {
            System.out.println(components.get(0).get(0) + " " + components.get(i).get(0));
        }
        
	}
	
	public static void dfs(int cityNum) {
	    if (!seen[cityNum]) {
	        components.get(components.size() - 1).add(cityNum + 1);
	        seen[cityNum] = true;
	        for (int other : edges.get(cityNum)) {
	            dfs(other);
	        }
	    }
	}
}