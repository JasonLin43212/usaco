/*
ID: jasonli7
LANG: JAVA
TASK: ttwo
*/
import java.io.*;
import java.util.*;

public class ttwo {
    
    public static int[][] grid = new int[10][10]; // 0 empty, 1 obstacle
    public static int Fr;
    public static int Fc;
    public static int Fd = 0;
    
    public static int Cr;
    public static int Cc;
    public static int Cd = 0;
    
    public static int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static Set<List<Integer>> seen = new HashSet<>();
    
    public static int minutes = 0;
    
    public static boolean outOfBounds(int r, int c) {
        return r < 0 || r >= 10 || c < 0 || c >= 10;
    }
    
    public static int[] getNextLoc(int r, int c, int d) {
        int newR = r + dirs[d][0];
        int newC = c + dirs[d][1];
        if (outOfBounds(newR, newC) || grid[newR][newC] == 1) {
            return new int[] {r, c, (d+1) % 4};
        } else {
            return new int[] {newR, newC, d};
        }
    }
    
    public static void solve() {
        while (!(Fr == Cr && Fc == Cc)) {
            if (seen.contains(Arrays.asList(Fr, Fc, Fd, Cr, Cc, Cd))) {
                minutes = 0;
                return;
            }
            minutes++;
            seen.add(Arrays.asList(Fr, Fc, Fd, Cr, Cc, Cd));
            // Update locations
            int[] farmerLoc = getNextLoc(Fr, Fc, Fd);
            int[] cowLoc = getNextLoc(Cr, Cc, Cd);
            Fr = farmerLoc[0];
            Fc = farmerLoc[1];
            Fd = farmerLoc[2];
            Cr = cowLoc[0];
            Cc = cowLoc[1];
            Cd = cowLoc[2];
//            System.out.println(Arrays.asList(Fr, Fc, Fd, Cr, Cc, Cd));
//            System.out.println(boardPrint(Fr, Fc, Cr, Cc));
        }
        return;
    }
    
    public static String boardPrint(int Fr, int Fc, int Cr, int Cc) {
        String out = "";
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if (r == Fr && c == Fc) {
                    out += "F";
                }
                else if (r == Cr && c == Cc) {
                    out += "C";
                }
                else if (grid[r][c] == 1){
                    out += "*";
                } else {
                    out += ".";
                }
            }
            out += "\n";
        }
        return out + "\n\n";
    }
    
	public static void main (String [] args) throws IOException {
		String fileName = "ttwo";
		BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
		
		for (int i = 0; i < 10; i++) {
		    String line = br.readLine();
		    for (int j = 0; j < 10; j++) {
		        char curChar = line.charAt(j);
		        if (curChar == '*') {
		            grid[i][j] = 1;
		        }
		        if (curChar == 'F') {
		            Fr = i;
		            Fc = j;
		        }
		        if (curChar == 'C') {
                    Cr = i;
                    Cc = j;
                }
		    }
		}
		
		solve();
		
		pw.println(minutes);
		pw.close();
	}
}