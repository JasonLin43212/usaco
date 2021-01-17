import java.io.*;
import java.util.*;

public class race {
    public static int K;
    
	public static void main (String [] args) throws IOException {
	    /**
	     * Increase at first only when you go above X
	     * then you need to keep track of the distance you travel from slowing
	     * down and speeding up
	     */
		BufferedReader br = new BufferedReader(new FileReader("race.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("race.out")));

		
		for (int i = 0; i < N; i++) {
		    pw.println(minTime(Integer.parseInt(br.readLine())));
		}
		pw.close();
	}
	
	public static int minTime(int X) {
	    int upDist = 0;
	    int downDist = 0;
	    int speed = 1;
	    int time = 0;
	    while (true) {
	        upDist += speed;
	        time++;
	        if (upDist + downDist >= K) {
	            return time;
	        }
            
	        if (speed >= X) {
	            downDist += speed;
	            time++;
	            if (upDist + downDist >= K) {
	                return time;
	            }
	        }
            
	        speed++;
	    }
	}
}