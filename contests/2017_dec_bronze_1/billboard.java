import java.io.*;
import java.util.*;

public class billboard {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("billboard.in"));
		
		int[][] coords = new int[3][4];
		
		for (int i = 0; i < 3; i++) {
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        for (int j = 0; j < 4; j++) {
	            coords[i][j] = Integer.parseInt(st.nextToken());
	        }
		}
		
//		System.out.println(Arrays.deepToString(coords));
		int solution = area(coords[0]) + area(coords[1])
		               - intersectArea(coords[0], coords[2])
		               - intersectArea(coords[1], coords[2]);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("billboard.out")));
		pw.println(solution);
		pw.close();
//		System.out.println(solution);
	}
	
	public static int area(int[] oneCoord) {
	    return Math.abs(oneCoord[2] - oneCoord[0]) * Math.abs(oneCoord[3] - oneCoord[1]);
	}
	
	// a < b, c < d
	public static int intersect(int a, int b, int c, int d) {
	    return Math.max(0, Math.min(b, d) - Math.max(a, c)); 
	}
	
	public static int intersectArea(int[] coordA, int[] coordB) {
	    int xIntersect = intersect(coordA[0],
	                               coordA[2],
	                               coordB[0],
	                               coordB[2]);
	    int yIntersect = intersect(coordA[1],
                                   coordA[3],
                                   coordB[1],
                                   coordB[3]);
	    return xIntersect * yIntersect;
	}
}