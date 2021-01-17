import java.io.*;
import java.util.*;

class rect { 
    public int r1;
    public int c1;
    public int r2;
    public int c2;
    
    public rect (int r1, int c1, int r2, int c2) {
        this.r1 = r1;
        this.c1 = c1;
        this.r2 = r2;
        this.c2 = c2;
    }
    
    public boolean contains(rect other) {
        return this.r1 <= other.r1 && this.r2 >= other.r2 &&
               this.c1 <= other.c1 && this.c2 >= other.c2;
    }
    
    public String toString() {
        return "(" + r1 + ", " + c1 + ") -> (" + r2 + ", " + c2 + ")";
    }
}

class pair {
    public int r;
    public int c;
    
    public pair (int r, int c) {
        this.r = r;
        this.c = c;
    }
    
    public String toString() {
        return "(" + r + ", " + c + ")";
    }
}

public class where {
    
    public static int N;
    public static char[][] colors;
    public static List<rect> pcls = new ArrayList<>();
    public static int[][] deltas = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("where.in"));
		N = Integer.parseInt(br.readLine());
		colors = new char[N][N];
		
		for (int i = 0; i < N; i++) {
		    String line = br.readLine();
	        for (int j = 0; j < N; j++) {
	            colors[i][j] = line.charAt(j);
	        }
		}
		
//		System.out.println(Arrays.deepToString(colors));
		
		for (int r1 = 0; r1 < N; r1++) {
		    for (int c1 = 0; c1 < N; c1++) {
		        for (int r2 = r1; r2 < N; r2++) {
		            for (int c2 = c1; c2 < N; c2++) {
		                if (possiblePCL(r1, c1, r2, c2)) {
		                    pcls.add(new rect(r1, c1, r2, c2));
		                }
		            }
		        }
		    }
		}
		
		int numPCL = 0;
		for (int i = 0; i < pcls.size(); i++) {
		    boolean isPCL = true;
		    for (int j = 0; j < pcls.size(); j++) {
		        if (i != j && pcls.get(j).contains(pcls.get(i))) {
		            isPCL = false;
		            break;
		        }
		    }
		    
		    if (isPCL) {
		        numPCL++;
		    }
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("where.out")));
		pw.println(numPCL);
		pw.close();
	}
	
	public static boolean possiblePCL(int r1, int c1, int r2, int c2) {
	    boolean[][] seen = new boolean[r2-r1+1][c2-c1+1];
	    int[] seenColor = new int[26];
	    int numColors = 0;
	    for (int r = r1; r <= r2; r++) {
	        for (int c = c1; c <= c2; c++) {
	            if (!seen[r-r1][c-c1]) {
	                // DFS
	                char color = colors[r][c];
	                if (seenColor[(int)(color - 'A')] == 0) {
	                    numColors++;
	                    if (numColors > 2) {
	                        return false;
	                    }
	                }
	                seenColor[(int)(color - 'A')]++;
	                
	                Stack<pair> frontier = new Stack<>();
	                frontier.add(new pair(r, c));
	                
	                while (!frontier.isEmpty()) {
	                    pair nextPair = frontier.pop();
	                    int curR = nextPair.r;
	                    int curC = nextPair.c;
	                    
	                    if (seen[curR-r1][curC-c1]) {
	                        continue;
	                    }
	                    
	                    seen[curR-r1][curC-c1] = true;
	                    
	                    for (int[] d : deltas) {
	                        int nextR = curR + d[0];
	                        int nextC = curC + d[1];
	                        
	                        if (nextR >= r1 && nextR <= r2 && nextC >= c1 && nextC <= c2 &&
	                            colors[nextR][nextC] == color) {
	                            frontier.add(new pair(nextR, nextC));
	                        }
	                    }
	                }
	            }
	        }
	    }
	    
	    boolean seenOne = false;
	    boolean seenTwo = false;
	    for (int numComps : seenColor) {
	        if (numComps > 0) {
	            if (numComps == 1) {
	                if (seenOne) {
	                    return false;
	                } else {
	                    seenOne = true;
	                }
	            } else { // > 1
	                if (seenTwo) {
	                    return false;
	                } else {
	                    seenTwo = true;
	                }
	            }
	        }
	    }
	    
	    return seenOne && seenTwo;
	}
}