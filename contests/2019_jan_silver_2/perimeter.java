import java.io.*;
import java.util.*;

class blob implements Comparable<blob> {
    public int area;
    public int perim;
    
    public blob(int area, int perim) {
        this.area = area;
        this.perim = perim;
    }
    
    public int compareTo(blob other) {
        if (this.area == other.area) {
            return other.perim - this.area;
        }
        return this.area - other.area;
    }
    
    @Override
    public String toString() {
        return "(Area: " + area + ", Perim: " + perim + ")";  
    }
}

class pair {
    public int r;
    public int c;
    
    public pair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class perimeter {
    
    public static int N;
    public static boolean[][] cream;
    public static boolean[][] seen;
    public static List<blob> blobs = new ArrayList<>();
    public static int[][] deltas = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("perimeter.in"));
		N = Integer.parseInt(br.readLine());
		cream = new boolean[N][N];
		seen = new boolean[N][N];
		
		for (int r = 0; r < N; r++) {
		    String line = br.readLine();
		    for (int c = 0; c < N; c++) {
		        if (line.charAt(c) == '#') {
		            cream[r][c] = true;
		        }
		    }
		}
		
//		System.out.println(Arrays.deepToString(cream));
		
		for (int r = 0; r < N; r++) {
		    for (int c = 0; c < N; c++) {
		        if (cream[r][c] && !seen[r][c]) {
		            fill(r, c);
		        }
		    }
		}
		
		
		blobs.sort(null);
//		System.out.println(blobs);
		blob outBlob = blobs.get(blobs.size() - 1);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
		pw.println(outBlob.area + " " + outBlob.perim);
		pw.close();
	}
	
	public static void fill(int r, int c) {
	    if (seen[r][c]) {
	        return;
	    }
	    blob curBlob = new blob(0, 0);
	    List<pair> frontier = new LinkedList<>();
	    frontier.add(new pair(r, c));
	    
	    while (!frontier.isEmpty()) {
	        pair curPair = frontier.remove(0);
	        int curR = curPair.r;
	        int curC = curPair.c;
	        if (seen[curR][curC]) {
	            continue;
	        }
	        seen[curR][curC] = true;
	        
	        curBlob.area++;
	        for (int i = 0; i < 4; i++) {
	            int newR = curR + deltas[i][0];
	            int newC = curC + deltas[i][1];
	            if (newR < 0 || newR >= N || newC < 0 || newC >= N 
	                    || !cream[newR][newC]) {
	                curBlob.perim++;
	            } else if (!seen[newR][newC]){
	                frontier.add(new pair(newR, newC));
	            }
	        }
	    }
	    
	    blobs.add(curBlob);
	}
}