import java.io.*;
import java.util.*;

public class moocast {
    
    public static int N;
    public static List<List<Integer>> edges = new ArrayList<>();
    public static int[] xs;
    public static int[] ys;
    public static int[] powers;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("moocast.in"));
		N = Integer.parseInt(br.readLine());
		xs = new int[N];
		ys = new int[N];
		powers = new int[N];
		
		for (int i = 0; i < N; i++) {
		    edges.add(new ArrayList<>());
		}
		for (int i = 0; i < N; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    xs[i] = Integer.parseInt(st.nextToken());
		    ys[i] = Integer.parseInt(st.nextToken());
		    powers[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
		    for (int j = 0; j < N; j++) {
		        if (i != j) {
		            int dx = xs[i] - xs[j];
		            int dy = ys[i] - ys[j];
		            if (dx*dx + dy*dy <= powers[i] * powers[i]) {
		                edges.get(i).add(j);
		            }
		        }
		    }
		}
		
		int maxCows = 0;
		for (int i = 0; i < N; i++) {
		    maxCows = Math.max(maxCows, getCows(i));
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
		pw.println(maxCows);
		pw.close();
	}
	
	public static int getCows(int startCow) {
	    int numCows = 0;
	    boolean[] seen = new boolean[N];
	    Stack<Integer> frontier = new Stack<>();
	    frontier.add(startCow);
	    while(!frontier.isEmpty()) {
	        int curCow = frontier.pop();
	        if (seen[curCow]) {
	            continue;
	        }
	        
	        seen[curCow] = true;
	        numCows++;
	        
	        for (int otherCow : edges.get(curCow)) {
	            frontier.add(otherCow);
	        }
	    }
	    return numCows;    
	}
}