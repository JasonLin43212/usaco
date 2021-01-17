import java.io.*;
import java.util.*;

public class cown {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		// farm a has roads to farms edges.get(a)
		List<List<Integer>> edges = new ArrayList<>();
		for (int i = 0; i < N; i++) {
		    edges.add(new ArrayList<>());
		}
		
		for (int i = 0; i < N - 1; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    int a = Integer.parseInt(st.nextToken());
		    int b = Integer.parseInt(st.nextToken());
		    edges.get(a-1).add(b-1);
		    edges.get(b-1).add(a-1);
		}
		
		boolean[] seen = new boolean[N];
		
		List<Integer> frontier = new ArrayList<>();
		frontier.add(0);
		
		int totalDays = 0;
		while(!frontier.isEmpty()) {
		    int farmNum = frontier.remove(0);
		    if (seen[farmNum]) {
		        continue;
		    }
		    seen[farmNum] = true;
		    
		    // need 2^n - 1 > number of outedges
		    List<Integer> outedge = edges.get(farmNum);
		    List<Integer> validOutEdge = new ArrayList<>();
		    for (int i = 0; i < outedge.size(); i++) {
		        if (!seen[outedge.get(i)]) {
		            validOutEdge.add(outedge.get(i));
		        }
		    }
//		    System.out.println(validOutEdge); // come back to this later for bigger graph
		    totalDays += validOutEdge.size();
		    totalDays += daysProduce(validOutEdge.size());
//		    System.out.println(daysProduce(validOutEdge.size()) + " " + validOutEdge.size());
		    for (int otherFarm : validOutEdge) {
		        frontier.add(otherFarm);
		    }
		}
		
		System.out.println(totalDays);
	}
	
	public static int daysProduce(int numFarms) {
	    int numInfected = 1;
	    int days = 0;
	    while (numInfected - 1 < numFarms) {
	        numInfected *= 2;
	        days++;
	    }
	    return days;
	}
}