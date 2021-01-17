import java.io.*;
import java.util.*;

class triple {
    public int r;
    public int c;
    public int depth;
    
    public triple (int r, int c, int depth) {
        this.r = r;
        this.c = c;
        this.depth = depth;
    }
    
    public String toString() {
        return "(" + r + ", " + c + ", depth: " + depth + ")"; 
    }
}

public class tracksinsnow {
    
    public static int H;
    public static int W;
    public static char[][] snow;
    public static int[][] deltas = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		snow =  new char[H][W];
		
		for (int r = 0; r < H; r++) {
		    String line = br.readLine();
		    for (int c = 0; c < W; c++) {
		        snow[r][c] = line.charAt(c);
		    }
		}
		
//		System.out.println(Arrays.deepToString(snow));
		
		PrintWriter pw = new PrintWriter(System.out);
		pw.println(bfs());
		pw.close();
	}
	
	public static int bfs() {
	    int numAnimals = 1;
	    boolean[][] seen = new boolean[H][W];
	    Deque<triple> frontier = new ArrayDeque<>();
	    frontier.addFirst(new triple(0, 0, numAnimals));
	    
	    while (!frontier.isEmpty()) {
	        triple curTriple = frontier.removeFirst();
	        int r = curTriple.r;
	        int c = curTriple.c;
	        int depth = curTriple.depth;
	        
	        if (seen[r][c]) {
	            continue;
	        }
	        
	        seen[r][c] = true;
	        numAnimals = Math.max(numAnimals, depth);
	        
	        char curAnimal = snow[r][c];
	        for (int[] delt : deltas) {
	            int newR = r + delt[0];
	            int newC = c + delt[1];
	            if (newR >= 0 && newR < H && newC >= 0 && newC < W) {
	                if (seen[newR][newC]) {
	                    continue;
	                }
	                char newAnimal = snow[newR][newC];
	                if (newAnimal == 'R' || newAnimal == 'F') {
	                    if (newAnimal == curAnimal) {
	                        frontier.addFirst(new triple(newR, newC, depth));
	                    } else {
	                        frontier.addLast(new triple(newR, newC, depth + 1));
	                    }
	                }
	            }
	        }
	    }
	    return numAnimals;
	}
}