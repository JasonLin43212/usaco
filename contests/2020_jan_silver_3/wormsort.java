import java.io.*;
import java.util.*;

class wormhole implements Comparable<wormhole>{
    public int end;
    public int width;
    
    public wormhole(int end, int width) {
        this.end = end;
        this.width = width;
    }
    
    public int compareTo(wormhole other) {
        return this.width - other.width;
    }
    
    public String toString() {
        return "(To: " + this.end + " Width: " + this.width + ")";
    }
}

public class wormsort {
    
    public static int N;
    public static int M;
    public static int[] cowPos;
    public static List<List<wormhole>> holes = new ArrayList<>();
    public static int[] widths;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("wormsort.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cowPos = new int[N];
		widths = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) { 
		    cowPos[i] = Integer.parseInt(st.nextToken()) - 1;
		    holes.add(new ArrayList<>()); 
		}
		
		for (int i = 0; i < M; i++) {
		    st = new StringTokenizer(br.readLine());
		    int a = Integer.parseInt(st.nextToken()) - 1;
		    int b = Integer.parseInt(st.nextToken()) - 1;
		    int w = Integer.parseInt(st.nextToken());
		    widths[i] = w;
		    holes.get(a).add(new wormhole(b, w));
		    holes.get(b).add(new wormhole(a, w));
		}
		
		boolean sorted = true;
		for (int i = 0; i < N; i++) {
		    if (i != cowPos[i]) {
		        sorted = false;
		        break;
		    }
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("wormsort.out")));
		if (sorted) {
		    pw.println("-1");
		} else {
		    Arrays.sort(widths);
	        
//	        System.out.println(Arrays.toString(widths));
//	        System.out.println(Arrays.toString(cowPos));
//	        System.out.println(holes);
//	        full_dfs(widths[M / 2]);
	        int lo = 0;
	        int hi = M - 1;
	        for (lo--; lo < hi;) {
	            int mid = (lo + hi + 1) / 2;
	            if (full_dfs(widths[mid])) lo = mid; else hi = mid - 1;
	        }
	        
	        pw.println(widths[lo]);
		}
		pw.close();
	}
	
	public static boolean full_dfs(int minWidth) {
	    boolean[] seen = new boolean[N];
	    for (int i = 0; i < N; i++) {
	        if (!seen[i]) {
	            Stack<Integer> stack = new Stack<>();
	            Set<Integer> seenPos = new HashSet<>();
	            stack.add(i);
	            while (!stack.empty()) {
	                int pos = stack.pop();
	                if (seen[pos]) {
	                    continue;
	                }
	                
	                seen[pos] = true;
	                seenPos.add(pos);
	                for (wormhole edge: holes.get(pos)) {
	                    if (edge.width >= minWidth) {
	                        stack.add(edge.end);
	                    }
	                }
	            }
	            
//	            System.out.println("width: " + minWidth + " " + seenPos);
	            for (int pos : seenPos) {
	                if (!seenPos.contains(cowPos[pos])) {
	                    return false;
	                }
	            }
	        }
	    }
	    return true;
	}
}