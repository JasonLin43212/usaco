import java.io.*;
import java.util.*;

public class rut {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		boolean[] isNorth = new boolean[N];
		int[] X = new int[N];
		int[] Y = new int[N];
		
		for (int i = 0; i < N; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    isNorth[i] = st.nextToken().equals("N") ? true : false;
            X[i] = Integer.parseInt(st.nextToken());
            Y[i] = Integer.parseInt(st.nextToken());
		}
		
//		System.out.println(Arrays.toString(isNorth) + "\n" + 
//		                   Arrays.toString(X) + "\n" + 
//		                   Arrays.toString(Y));
		
		// cow a was stopped by stoppedBy[a]
		// if -1, then cow was not stopped
		int[] stoppedBy = new int[N];
		for (int i = 0; i < N; i++) {
		    stoppedBy[i] = -1;
		}
		
		// Compare two cows and see if cow i is stopped by cow j
		// only if cow j was stopped and hasn't eaten the cow i path
		for (int i = 0; i < N; i++) {
		    int minDist = Integer.MAX_VALUE;
		    int stopCow = -1;
		    for (int j = 0; j < N; j++) {
		        if (i != j) {
		            if (isNorth[i]) { // cow i goes north
		                // cow j goes north and is directly above cow i
		                if (isNorth[j] && X[j] == X[i] && Y[j] > Y[i]) {
		                    int iTraveled = Y[j] - Y[i];
		                    if (iTraveled < minDist) {
		                        minDist = iTraveled;
		                        stopCow = j;
		                    }
		                } else if (!isNorth[j] && X[j] <= X[i] && Y[j] > Y[i]){ 
		                    // cow j is east and is topleft
		                    
		                    //check if cow j is actually blocked by another cow 
		                    // before reaching cow i
		                    boolean jBlocked = false;
		                    for (int a = 0; a < N; a++) {
                                if (a == i || a == j) { // blockiing cow cant be i or j
                                    continue;
                                }
                                if (isNorth[a] && X[a] < X[i] && Y[a] > Y[i] &&
                                    X[a] > X[j] && Y[a] < Y[j] &&
                                    X[a] - X[j] > Y[j] - Y[a] ) {
                                    jBlocked = true;
                                }
                            }
                            if (!jBlocked && X[i] - X[j] < Y[j] - Y[i]) {
                                int iTravelled = Y[j] - Y[i];
                                if (iTravelled < minDist) {
                                    minDist = iTravelled;
                                    stopCow = j;
                                }
                            }
		                }
		            } else { // cow i goes east
                        if (!isNorth[j] && Y[j] == Y[i] && X[j] > X[i]) {
                            int iTraveled = X[j] - X[i];
                            if (iTraveled < minDist) {
                                minDist = iTraveled;
                                stopCow = j;
                            }
                        } else if (isNorth[j] && Y[j] <= Y[i] && X[j] > X[i]){ 
                            
                            boolean jBlocked = false;
                            for (int a = 0; a < N; a++) {
                                if (a == i || a == j) { // blockiing cow cant be i or j
                                    continue;
                                }
                                if (!isNorth[a] && X[a] > X[i] && Y[a] < Y[i] &&
                                    X[a] < X[j] && Y[a] > Y[j] &&
                                    X[j] - X[a] < Y[a] - Y[j] ) {
                                    jBlocked = true;
                                }
                            }
                            if (!jBlocked && X[j] - X[i] > Y[i] - Y[j]) {
                                int iTravelled = X[j] - X[i];
                                if (iTravelled < minDist) {
                                    minDist = iTravelled;
                                    stopCow = j;
                                }
                            }
                        }
		            }
		        }
		    }
		    
		    stoppedBy[i] = stopCow;
		}
		
//		System.out.println(Arrays.toString(stoppedBy));
		
//		int[] stoppedByModified = new int[N];
//		for (int i = 0; i < N; i++) {
//            stoppedByModified[i] = -1;
//        }
//		for (int i = 0; i < N; i++) {
//		    if (stoppedBy[i] != -1) {
//	            stoppedByModified[stoppedBy[i]] = i;
//		    }
//		}
//		
//		System.out.println(Arrays.toString(stoppedByModified));
//		
		int[] totalStops = new int[N];
		for (int i = 0; i < N; i++) {
		    int nextCow = stoppedBy[i];
		    while (nextCow != -1) {
		        totalStops[nextCow]++;
		        nextCow = stoppedBy[nextCow];
		    }
		}
		
//		System.out.println(Arrays.toString(totalStops));
		for (int i = 0; i < N; i++) {
		    System.out.println(totalStops[i]);
		}
	}
	
	public static int getStops(int i, int[] stoppedBy) {
	    int numStops = 0;
	    int nextCow = stoppedBy[i];
	    while (nextCow != -1) {
	        numStops++;
	        nextCow = stoppedBy[nextCow];
	    }
	    return numStops;
	}
}