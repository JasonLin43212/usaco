import java.io.*;
import java.util.*;

public class milkvisits {
    
    public static int N;
    public static int M;
    public static List<List<Integer>> edges = new ArrayList<>();
    public static int[] comps;
    public static boolean[] isG;
    public static boolean[] seen;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("milkvisits.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		comps = new int[N];
		isG = new boolean[N];
		seen = new boolean[N];
		
		for (int i = 0; i < N; i++) {
		    edges.add(new ArrayList<>());
		}
		
		String cows = br.readLine();
		for (int i = 0; i < N; i++) {
		    isG[i] = cows.charAt(i) == 'G';
		}
		
		for (int i = 0; i < N - 1; i++) {
		    st = new StringTokenizer(br.readLine());
		    int a = Integer.parseInt(st.nextToken()) - 1;
		    int b = Integer.parseInt(st.nextToken()) - 1;
		    edges.get(a).add(b);
		    edges.get(b).add(a);
		}
		
//		System.out.println(edges);
		int numComps = 0;
		for (int i = 0; i < N; i++) {
		    if (!seen[i]) {
		        dfs(i, isG[i], numComps);
		        numComps++;
		    }
		}
		
		
//		System.out.println(Arrays.toString(comps));
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
		    st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            boolean g = st.nextToken().equals("G");
		    if (comps[a] != comps[b]) {
		        sb.append("1");
		    } else {
		        if (isG[a] == g) {
		            sb.append("1");
		        } else {
		            sb.append("0");
		        }
		    }
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));
		pw.println(sb.toString());
		pw.close();
	}
	
	public static void dfs(int start, boolean isGCow, int compNum) {
	    Stack<Integer> frontier = new Stack<>();
	    frontier.add(start);
	    
	    while(!frontier.isEmpty()) {
	        int node = frontier.pop();
	        if (seen[node]) {
	            continue;
	        }
	        
	        seen[node] = true;
	        comps[node] = compNum;
	        for (int next : edges.get(node)) {
	            if (isG[next] == isGCow) {
	                frontier.add(next);
	            }
	        }
	    }
	}
	
	
}