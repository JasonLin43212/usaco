import java.io.*;
import java.util.*;

public class rect3 {
    public static int N;
    public static int[] X;
    public static int[] Y;
    
	public static void main (String [] args) throws IOException {
	    // n^4 algo. not gonna pass all cases tho....
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		X = new int[N];
		Y = new int[N];
		
		for (int i = 0; i < N; i++) {
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        X[i] = Integer.parseInt(st.nextToken());
	        Y[i] = Integer.parseInt(st.nextToken());
		}
		
		// only make bottom left or top right corners
		Set<List<Integer>> corners = new HashSet<>();
		for (int i = 0; i < N; i++) {
		    for (int j = i+1; j < N; j++) {
		        if ((X[i] < X[j] && Y[i] < Y[j]) ||
		            (X[i] > X[j] && Y[i] > Y[j])) { 
		            // they are bottom left or top right
		            List<Integer> corner1 = new ArrayList<>();
		            List<Integer> corner2 = new ArrayList<>();
		            
		            corner1.add(X[i]);
		            corner1.add(Y[i]);
		            corner2.add(X[j]);
		            corner2.add(Y[j]);
		            corners.add(corner1);
		            corners.add(corner2);
		        } else {
		            // they are top left bottom right, so we need all 4
		            List<Integer> corner1 = new ArrayList<>();
                    List<Integer> corner2 = new ArrayList<>();
                    List<Integer> corner3 = new ArrayList<>();
                    List<Integer> corner4 = new ArrayList<>();
                    corner1.add(X[i]);
                    corner1.add(Y[i]);
                    corner2.add(X[j]);
                    corner2.add(Y[j]);
                    corner3.add(X[i]);
                    corner3.add(Y[j]);
                    corner4.add(X[j]);
                    corner4.add(Y[i]);
                    corners.add(corner1);
                    corners.add(corner2);
                    corners.add(corner3);
                    corners.add(corner4);
		        }
		    }
		}
		
		List<List<Integer>> cornerList = new ArrayList<>(corners);
//		System.out.println(cornerList);
		int totalSubsets = N + 1; // N for single cows
		Set<Set<Integer>> seen = new HashSet<>();
		for (int i = 0; i < cornerList.size(); i++) {
		    for (int j = i + 1; j < cornerList.size(); j++) {
		        int x_1 = cornerList.get(i).get(0);
		        int x_2 = cornerList.get(j).get(0);
		        int y_1 = cornerList.get(i).get(1);
		        int y_2 = cornerList.get(j).get(1);
		        // We want to only use bottom left top right corners
		        if ((x_1 > x_2  && y_1 > y_2 ) || (x_2 > x_1 && y_2 > y_1)) {
		            // two corners cannot have same x or y
		            Set<Integer> aSubset = getSubset(x_1, y_1, x_2, y_2);
		            if (!seen.contains(aSubset) && aSubset.size() != 1) {
		                seen.add(aSubset);
		                totalSubsets++;
		            }
		        }
		    }
		}
//		System.out.println(seen);
		
		System.out.println(totalSubsets);
	}
	
	public static Set<Integer> getSubset(int x_1, int y_1, int x_2, int y_2) {
	    Set<Integer> subset = new HashSet<>();
	    for (int i = 0; i < N; i++) {
	        if ((x_1 <= X[i] && X[i] <= x_2 && y_1 <= Y[i] && Y[i] <= y_2) ||
	            (x_2 <= X[i] && X[i] <= x_1 && y_2 <= Y[i] && Y[i] <= y_1)) {
	            subset.add(i);
	        }
	    }
	    return subset;
	}
}