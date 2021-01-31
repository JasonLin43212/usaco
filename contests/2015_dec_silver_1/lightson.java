import java.io.*;
import java.util.*;

class cell {
    public int x;
    public int y;
    
    public cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

public class lightson {
    
    public static int N;
    public static int M;
    public static ArrayList<cell>[][] lights;
    public static int[][] deltas = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static boolean[][] seen;
    public static boolean[][] on;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("lightson.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lights = new ArrayList[N][N];
		seen = new boolean[N][N];
		on = new boolean[N][N];
		for (int i = 0; i < N; i++) {
		    for (int j = 0; j < N; j++) {
		        lights[i][j] = new ArrayList<>();
		    }
		}
		
		
		for (int i = 0; i < M; i++) {
		    st = new StringTokenizer(br.readLine());
		    int x = Integer.parseInt(st.nextToken())-1;
		    int y = Integer.parseInt(st.nextToken())-1;
		    int a = Integer.parseInt(st.nextToken())-1;
		    int b = Integer.parseInt(st.nextToken())-1;
		    
		    lights[x][y].add(new cell(a, b));
		}
		
		Set<cell> frontier = new HashSet<>();
		frontier.add(new cell(0, 0));
		on[0][0] = true;
		
		int numRooms = 1;
		while (!frontier.isEmpty()) {
		    cell nextCell = null;
		    for (cell aCell : frontier) {
		        if (on[aCell.x][aCell.y]) {
		            nextCell = aCell;
		            break;
		        }
		    }
		    
		    if (nextCell == null) {
		        break;
		    }
		    seen[nextCell.x][nextCell.y] = true;
		    frontier.remove(nextCell);
		    for (cell lightCell : lights[nextCell.x][nextCell.y]) {
		        if (!on[lightCell.x][lightCell.y]) {
		           on[lightCell.x][lightCell.y] = true;
		           numRooms++;
		        }
		    }
		    for (int[] det : deltas) {
		        int nextX = nextCell.x + det[0];
		        int nextY = nextCell.y + det[1];
		        if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && !seen[nextX][nextY]) {
		            frontier.add(new cell(nextX, nextY));
		        }
		    }
		}
		
//		System.out.println(Arrays.deepToString(lights));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));
		pw.println(numRooms);
		pw.close();
	}
}