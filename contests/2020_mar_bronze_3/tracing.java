import java.io.*;
import java.util.*;

class interaction {
    public int time;
    public int cow1;
    public int cow2;
    
    public interaction(int t, int a, int b) {
        this.time = t;
        this.cow1 = a;
        this.cow2 = b;
    }
    
    @Override
    public String toString() {
        return time + ": (" + cow1 + ", " + cow2 + ")";
    }
}

public class tracing {
    
    public static boolean[] finalState;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("tracing.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		String healthString = br.readLine();
		
		finalState = new boolean[N];
		for (int i = 0; i < N; i++) { 
		    if (healthString.charAt(i) == '1') {
		        finalState[i] = true;
		    }
		}
		
		List<interaction> infections = new ArrayList<>();
		
		for (int i = 0; i < T; i++) {
		    st = new StringTokenizer(br.readLine());
		    int t = Integer.parseInt(st.nextToken());
		    int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            infections.add(new interaction(t, a-1, b-1));
		}
		
		infections.sort((a, b) -> a.time - b.time);
		
//		System.out.println(infections);
		boolean[] possibleKs = new boolean[252];
		boolean[] possibleZero = new boolean[N];
		
		for (int k = 0; k <= 251; k++) {
		    for (int n = 0; n < N; n++) {
		        if (canBeZero(n, k, infections)) {
		            possibleKs[k] = true;
		            possibleZero[n] = true;
		        }
		    }
		}
		
		int numZero = 0;
		for (int i = 0; i < N; i++) {
		    if (possibleZero[i]) {
		        numZero++;
//		        System.out.println("cow " + (i + 1));
		    }
		}
		
		int minK = 251;
		for (int i = 0; i < 252; i ++) {
		    if (possibleKs[i]) {
		        minK = i;
		        break;
		    }
		}
		
		int maxK = 0;
        for (int i = 251; i >= 0; i --) {
            if (possibleKs[i]) {
                maxK = i;
                break;
            }
        }
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("tracing.out")));
        String output = numZero + " " + minK + " ";
        if (maxK == 251) {
            output += "Infinity";
        } else {
            output += maxK;
        }
		
		pw.println(output);
		pw.close();
	}
	
	public static boolean canBeZero(int n_0, int k, List<interaction> infections) {
	    int[] numShakes = new int[finalState.length];
	    boolean[] curState = new boolean[finalState.length];
	    
	    curState[n_0] = true;
	    
	    for (interaction i : infections) {
	        int cow1 = i.cow1;
	        int cow2 = i.cow2;
	        // ORDER MATTERS BECAUSE YOU ARE INCREMENTING
	        // AFTER THEY GET INFECTED (VERY BAD)
	        // INCREMENT BEFORE
	        
	        if (curState[cow1]) {numShakes[cow1]++;}
            if (curState[cow2]) {numShakes[cow2]++;}
	        if (curState[cow1] && numShakes[cow1] <= k) { // if cow1 infected
                curState[cow2] = true;
	        }
	        if (curState[cow2] && numShakes[cow2] <= k) { // if cow2 infected
                curState[cow1] = true;
            }
	        
	    }
	    
	    for (int i = 0; i < curState.length; i++) {
	        if (curState[i] != finalState[i]) {
	            return false;
	        }
	    }
	    return true;
	}
}