/*
ID: jasonli7
LANG: JAVA
TASK: maze1
*/
import java.io.*;
import java.util.*;

public class maze1 {
    
    public static int W;
    public static int H;
    public static char[][] maze;
    public static int[][] dist1;
    public static int[][] dist2;
    public static List<List<Integer>> exits = new ArrayList<>();
    
    public static int[][] delts = {{-2, 0}, {0, 2}, {2, 0}, {0, -2}};
    
    public static boolean cannotGoTo(int r, int c) {
        return r <= 0 || r >= H*2 || c <= 0 || c >= W*2 ||
               maze[r][c] != ' ';
    }
    
    public static void bfs(List<Integer> start, int[][] dist) {
        List<List<Integer>> queue = new ArrayList<>();
        queue.add(start);
        dist[start.get(0)][start.get(1)] = 1;
        
        while (queue.size() != 0) {
            List<Integer> cell = queue.remove(0);
            int curRow = cell.get(0);
            int curCol = cell.get(1);
            
            for (int[] delt : delts) {
                int nextRow = curRow + delt[0];
                int nextCol = curCol + delt[1];
                List<Integer> nextCell = Arrays.asList(nextRow, nextCol);
                if (!cannotGoTo(curRow + delt[0]/2, curCol + delt[1]/2) &&
                    dist[nextRow][nextCol] == -1) {
                    queue.add(nextCell);
                    dist[nextRow][nextCol] = dist[curRow][curCol] + 1;
                }
            }
        }
    }
    
    public static int solve() {
        bfs(exits.get(0), dist1);
        if (!exits.get(0).equals(exits.get(1))) {
            bfs(exits.get(1), dist2);
        } else {
            dist2 = dist1;
        }
        
        
//        for (int i = 0; i < 2*H + 1; i++) {
//            System.out.println(Arrays.toString(dist1[i]));
//        }
        int max = 0;
        for (int r = 1; r < 2*H; r++) {
            for (int c = 1; c < 2*W; c++) {
                if (dist1[r][c] != -1) {
                    max = Math.max(max, Math.min(dist1[r][c], dist2[r][c]));
                }
            }
        }
        return max;
    }
    
	public static void main (String [] args) throws IOException {
		String fileName = "maze1";
		BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		maze = new char[2*H+1][2*W+1];
		dist1 = new int[2*H+1][2*W+1];
		dist2 = new int[2*H+1][2*W+1];
		
		for (int r = 0; r < 2*H + 1; r++) {
		    String line = br.readLine();
		    for (int c = 0; c < 2*W + 1; c++) {
		        dist1[r][c] = -1;
		        dist2[r][c] = -1;
		        maze[r][c] = line.charAt(c);
		        
		        // Find exits
		        if (maze[r][c] == ' ') {
		            if (r == 0) { // Top
		                exits.add(Arrays.asList(r+1, c));
		            } else if (r == 2*H) { // Bottom
		                exits.add(Arrays.asList(r-1, c));
		            } else if (c == 0) { // Left
		                exits.add(Arrays.asList(r, c+1));
		            } else if (c == 2*W) {
		                exits.add(Arrays.asList(r, c-1));
		            }
		        }
		    }
		}
		
		int solution = solve();
		
		pw.println(solution);
		pw.close();
	}
}