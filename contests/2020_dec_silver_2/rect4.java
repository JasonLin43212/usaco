import java.io.*;
import java.util.*;

public class rect {
    public static int N;
    public static int[] X;
    public static int[] Y;
//    public static Set<Set<Integer>> allSet = new HashSet<>();
    
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
		// as well as 0 for numbered, 1 for bottom left, 2 for top right
		// two parents if there are 
		int[][] corners = new int[2*N*N][5];
		for (int i =0; i<2*N*N; i++) {
	        corners[i] = new int[] {-1,-1,-1,-1,-1}
		}
		
		for (int i = 0; i < N; i++) {
		    for (int j = i+1; j < N; j++) {
		        if ((X[i] < X[j] && Y[i] < Y[j]) ||
		            (X[i] > X[j] && Y[i] > Y[j])) { 
		            // they are bottom left or top right
		            List<Integer> corner1 = new ArrayList<>();
		            List<Integer> corner2 = new ArrayList<>();
		            
		            corner1.add(X[i]);
		            corner1.add(Y[i]);
		            corner1.add(0);
		            corner1.add(-1);
		            corner1.add(-1);
		            corner2.add(X[j]);
		            corner2.add(Y[j]);
		            corner2.add(0);
		            corner2.add(-1);
		            corner2.add(-1);
		            corners.add(corner1);
		            corners.add(corner2);
		        } else {
		            // they are top left bottom right, 
		            List<Integer> corner1 = new ArrayList<>();
                    List<Integer> corner2 = new ArrayList<>();
                    corner1.add(X[i]);
                    corner1.add(Y[j]);
                    if (X[i] < X[j]) {
                        corner1.add(1); //bottom left
                        corner1.add(i);
                        corner1.add(j);
                    } else  {
                        corner1.add(2);
                        corner1.add(j);
                        corner1.add(i);
                    }
                    
                    corner2.add(X[j]);
                    corner2.add(Y[i]);
                    if (X[i] < X[j]) {
                        corner2.add(2); //bottom left
                        corner2.add(i);
                        corner2.add(j);
                    } else  {
                        corner2.add(1);
                        corner2.add(j);
                        corner2.add(i);
                    }
                    corners.add(corner1);
                    corners.add(corner2);
		        }
		    }
		}
		
		List<List<Integer>> cornerList = new ArrayList<>(corners);
//		System.out.println(cornerList);
		long totalSubsets = N + 1; // N for single cows
//		Set<Set<Integer>> seen = new HashSet<>();
		for (int i = 0; i < cornerList.size(); i++) {
		    for (int j = 0; j < cornerList.size(); j++) {
		        int x_1 = cornerList.get(i).get(0);
                int x_2 = cornerList.get(j).get(0);
                int y_1 = cornerList.get(i).get(1);
                int y_2 = cornerList.get(j).get(1);
                int c_1 = cornerList.get(i).get(2);
                int c_2 = cornerList.get(j).get(2); 
                int p1_1 = cornerList.get(i).get(3);
                int p1_2 = cornerList.get(j).get(3); 
                int p2_1 = cornerList.get(i).get(4);
                int p2_2 = cornerList.get(j).get(4);
                // We want to only use bottom left top right corners
                if (validCombo(x_1, x_2, y_1, y_2, c_1, c_2, p1_1, p1_2, p2_1, p2_2)) {
//                    System.out.println(cornerList.get(i) + " " + cornerList.get(j));
                    // two corners cannot have same x or y
                    totalSubsets++;
                }
		    }
		}
//		System.out.println(seen);
		
		System.out.println(totalSubsets);
	}
	
	public static boolean validCombo(int x_1, int x_2, int y_1, int y_2, int c_1, int c_2, int p1_1, int p1_2, int p2_1, int p2_2) {
	    if (!(x_1 < x_2 && y_1 < y_2)) { // bottom left and top right only
	        return false;
	    }
	    if (c_1 == 2) { // c_1 should only be 0 or 1
	        return false;
	    }
	    if (c_2 == 1) { // c_2 should only be 0 or 2
	        return false;
	    }
	    
	    if (c_2 == 2) {
	        int parent1_x = X[p1_2];
	        int parent1_y = Y[p1_2];
	        int parent2_x = X[p2_2];
	        int parent2_y = Y[p2_2];
	        if (parent1_x < x_1) {
	            return false;
	        }
	        if (parent1_y < y_1) {
	            return false;
	        }
	        if (parent2_x < x_1) {
	            return false;
	        }
	        if (parent2_y < y_1) {
	            return false;
	        }
 	    }
	    
	    if (c_1 == 1) {
            int parent1_x = X[p1_1];
            int parent1_y = Y[p1_1];
            int parent2_x = X[p2_1];
            int parent2_y = Y[p2_1];
            if (parent1_x > x_2) {
                return false;
            }
            if (parent1_y > y_2) {
                return false;
            }
            if (parent2_x > x_2) {
                return false;
            }
            if (parent2_y > y_2) {
                return false;
            }
        }
	    
//	    Set<Integer> subset = new HashSet<>();
//        for (int i = 0; i < N; i++) {
//            if ((x_1 <= X[i] && X[i] <= x_2 && y_1 <= Y[i] && Y[i] <= y_2) ||
//                (x_2 <= X[i] && X[i] <= x_1 && y_2 <= Y[i] && Y[i] <= y_1)) {
//                subset.add(i);
//            }
//        }
        
//        if (allSet.contains(subset)) {
//            System.out.println("AAAA" + subset);
//        } else {
//            allSet.add(subset);
//        }
	    return true;
	}
	
//	public static Set<Integer> getSubset(int x_1, int y_1, int x_2, int y_2) {
//	    Set<Integer> subset = new HashSet<>();
//	    for (int i = 0; i < N; i++) {
//	        if ((x_1 <= X[i] && X[i] <= x_2 && y_1 <= Y[i] && Y[i] <= y_2) ||
//	            (x_2 <= X[i] && X[i] <= x_1 && y_2 <= Y[i] && Y[i] <= y_1)) {
//	            subset.add(i);
//	        }
//	    }
//	    return subset;
//	}
}