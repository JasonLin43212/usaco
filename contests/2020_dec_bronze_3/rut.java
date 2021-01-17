import java.io.*;
import java.util.*;

public class rut {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		boolean[] isNorth = new boolean[N];
		int[] X = new int[N];
		int[] Y = new int[N];
		
		for (int n = 0; n < N; n++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    isNorth[n] = st.nextToken().equals("N") ? true : false;
		    X[n] = Integer.parseInt(st.nextToken());
		    Y[n] = Integer.parseInt(st.nextToken());
		}
		
//		System.out.println(Arrays.toString(isNorth) + "\n" + 
//		                   Arrays.toString(X) + "\n" + 
//		                   Arrays.toString(Y));
		// some conditions before checking
        /* i != j
         * if you are a north cow:
         * - the other cow must be in top left quadrant inclusive
         *   to matter
         * - check whether it is an east cow or north cow and do appropriate calcs
         * 
         * if east cow:
         * - other cows must be bottom right quadrant
         * 
         * urgent matter
         * - sometimes a cow may be blocked and their future paths won't matter
         * 
         * maybe sort it by x+y reversed
         */
		int[] grassEaten = new int[N];
		for (int i = 0; i < N; i++) {
		    int minGrass = Integer.MAX_VALUE; // might have problems here -1?
		    for (int j = 0; j < N; j++) {
		        if (i != j) {
		            if (isNorth[i]) { // this is north cow
		                // other cow is north cow and is directly above this cow
                        if (isNorth[j] && X[i] == X[j] && Y[i] <= Y[j]) { 
                            int thisGrass = Y[j] - Y[i];
                            minGrass = Math.min(thisGrass, minGrass);
                        } else if (!isNorth[j] && X[i] >= X[j] && Y[i] <= Y[j]){ 
                            // other cow is east cow and is in topleft
                            
                            boolean isBlocked = false;
                            //we want to know whether the other cow is blocked
                            // before even crossing paths with this cow
                            for (int a = 0; a < N; a++) {
                                if (a == i || a == j) {
                                    continue;
                                }
                                // blocked
                                if (isNorth[a] && X[a] < X[i] && Y[a] > Y[i] &&
                                    X[a] > X[j] && Y[a] < Y[j] &&
                                    X[a] - X[j] > Y[j] - Y[a] ) {
                                    isBlocked = true;
                                }
                            }
                            if (!isBlocked && X[i] - X[j] < Y[j] - Y[i]) {
                                // east cow beats this cow
                                int thisGrass = Y[j] - Y[i];
                                minGrass = Math.min(thisGrass, minGrass);
                            }
                        }
		            } else { // this is east cow
		                // other cow is east cow and is directly to the right of this cow
                        if (!isNorth[j] && Y[i] == Y[j] && X[i] <= X[j]) { 
                            int thisGrass = X[j] - X[i];
                            minGrass = Math.min(thisGrass, minGrass);
                        } else if (isNorth[j] && X[i] <= X[j] && Y[i] >= Y[j]){ 
                            // other cow is north cow and is in bottomright
                            
                            boolean isBlocked = false;
                            //we want to know whether the other cow is blocked
                            // before even crossing paths with this cow
                            for (int a = 0; a < N; a++) {
                                if (a == i || a == j) {
                                    continue;
                                }
                                // blocked
                                if (!isNorth[a] && X[a] > X[i] && Y[a] < Y[i] &&
                                    X[a] < X[j] && Y[a] > Y[j] &&
                                    X[j] - X[a] < Y[a] - Y[j] ) {
                                    isBlocked = true;
                                }
                            }
                            
                            if (!isBlocked && X[j] - X[i] > Y[i] - Y[j]) {
                                // north cow beats this cow
                                int thisGrass = X[j] - X[i];
                                minGrass = Math.min(thisGrass, minGrass);
                            }
                        }
		            }
		        }
		    }
		    
		    if (minGrass == Integer.MAX_VALUE) {
		        minGrass = -1;
		    }
		    grassEaten[i] = minGrass;
		}
		
		for (int i = 0; i < N; i++) {
		    if (grassEaten[i] == -1) {
		        System.out.println("Infinity");
		    } else {
		        System.out.println(grassEaten[i]);
		    }
		    
		}
		
	}
}