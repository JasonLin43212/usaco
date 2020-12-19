import java.io.*;
import java.util.*;

public class billboard {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("billboard.in"));
		
		int[] lawnCoord = new int[4];
		int[] feedCoord = new int[4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
		    lawnCoord[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
		    feedCoord[i] = Integer.parseInt(st.nextToken());
		}
		
		int lawnHeight = lawnCoord[3] - lawnCoord[1];
	    int lawnWidth = lawnCoord[2] - lawnCoord[0];
	    
		// check if y coords are within
        boolean yWithin = within(lawnCoord[1], lawnCoord[3], feedCoord[1], feedCoord[3]);
        boolean xWithin = within(lawnCoord[0], lawnCoord[2], feedCoord[0], feedCoord[2]);
        int solution = 0;
        
        if (xWithin && yWithin) {
            solution = 0;
        } else if (yWithin && !within(feedCoord[0], feedCoord[2], lawnCoord[0], lawnCoord[2])) {
            // check x intersect
            int widthIntersect = intersect(lawnCoord[0], lawnCoord[2], feedCoord[0], feedCoord[2]);
            solution = lawnHeight * lawnWidth - (widthIntersect * lawnHeight);
        } else if (xWithin && !within(feedCoord[1], feedCoord[3], lawnCoord[1], lawnCoord[3])) {
            // check y intersect
            int heightIntersect = intersect(lawnCoord[1], lawnCoord[3], feedCoord[1], feedCoord[3]);
            solution = lawnHeight * lawnWidth - (heightIntersect * lawnWidth);
        } else {
            solution = lawnHeight * lawnWidth;
        }
        
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("billboard.out")));
		pw.println(solution);
		pw.close();
	}
	
	public static int intersect(int a, int b, int c, int d) {
	    return Math.max(0, Math.min(b, d) - Math.max(a, c));
	}
	
	// if [a, b] is within [c, d]
	public static boolean within(int a, int b, int c, int d) {
	    return c <= a && b <= d;
	}
}