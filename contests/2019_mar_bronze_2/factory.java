import java.io.*;
import java.util.*;

public class factory {
    
    public static List<List<Integer>> reverseEdges;
    public static int N;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("factory.in"));
		N = Integer.parseInt(br.readLine());
		
		reverseEdges = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
		    reverseEdges.add(new ArrayList<>()); // re.get(a) gets all points that bring you to a
		}
		
		for (int i = 0; i < N-1; i++) {
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        int a = Integer.parseInt(st.nextToken());
	        int b = Integer.parseInt(st.nextToken());
	        reverseEdges.get(b-1).add(a-1);
		}
		
		// Remember to add one to the output!!!
		int solution = -1;
		for (int i = 0; i < N; i++) {
		    if (sinkPoint(i)) {
		        solution = i + 1;
		        break;
		    }
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("factory.out")));
		pw.println(solution);
		pw.close();
	}
	
	/**
	 * check if, when you start at nodeNum, if you can reach every other
	 * point through reverse edges
	 */
	public static boolean sinkPoint(int nodeNum) {
	    boolean[] seen = new boolean[N];
	    List<Integer> frontier = new ArrayList<>();
	    frontier.add(nodeNum);
	    
	    while (!frontier.isEmpty()) {
	        int curNode = frontier.remove(0);
	        if (seen[curNode]) { // Already seen this node
	            continue;
	        }
	        
	        // Haven't seen this node
	        seen[curNode] = true;
	        // Add all the edges that can get you to this node
	        frontier.addAll(reverseEdges.get(curNode));
	    }
	    
	    for (int i = 0; i < N; i++) {
	        if (!seen[i]) {
	            return false;
	        }
	    }
	    return true;
	}
}