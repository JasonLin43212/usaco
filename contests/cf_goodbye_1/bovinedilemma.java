import java.io.*;
import java.util.*;

public class bovinedilemma {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
		    int n = Integer.parseInt(br.readLine());
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    int[] trees = new int[n];
		    for (int j = 0; j < n; j++) {
		        trees[j] = Integer.parseInt(st.nextToken());
		    }
		    System.out.println(numTri(trees));
		}
		
		
	}
	
	public static int numTri (int[] trees) {
	    int[] areas = new int[51]; // zero area shouldnt count
	    for (int i = 0; i < trees.length; i++) {
	        for (int j = i + 1; j < trees.length; j++) {
	            areas[trees[j] - trees[i]]++;
	        }
	    }
	    
	    int numTris = 0;
	    for (int i = 0; i < 51; i++) {
	        if (areas[i] > 0) {
	            numTris++;
	        }
	    }
	    return numTris;
	}
}