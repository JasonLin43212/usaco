import java.io.*;
import java.util.*;

public class longsimple {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		PrintWriter pw = new PrintWriter(System.out);
		for (int z = 0; z < t; z++) {
		    int n = Integer.parseInt(br.readLine());
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    long[] lengths = new long[n];
		    long[] top = new long[n];
		    long[] bottom = new long[n];
		    for (int i = 0; i < n; i++) {
		        lengths[i] = Long.parseLong(st.nextToken());
		    }
		    st = new StringTokenizer(br.readLine());
		    for (int i = 0; i < n; i++) {
                top[i] = Long.parseLong(st.nextToken());
            }
		    st = new StringTokenizer(br.readLine());
		    for (int i = 0; i < n; i++) {
                bottom[i] = Long.parseLong(st.nextToken());
            }
		    
		    long next1 = -1;
		    long next2 = -1;
		    long maxCycle = 0;
		    long curCycle = 0;
		    for (int i = n - 1; i > 0; i--) {
		        if (next1 == -1) {
		            curCycle += lengths[i] + 1;
		            next1 = top[i];
		            next2 = bottom[i];
		        } else {
		            if (next1 == next2) {
		                maxCycle = Math.max(curCycle, maxCycle);
		                next1 = top[i];
		                next2 = bottom[i];
		                curCycle = lengths[i] + 1;
		            } else {
		                maxCycle = Math.max(maxCycle, curCycle + Math.abs(next1 - next2));
		                
		                curCycle += Math.min(next1, next2) - 1;
		                curCycle += lengths[i] - Math.max(next1, next2);
		                curCycle += 2;
		                if (lengths[i] + 1 > curCycle) {
                            curCycle = lengths[i] + 1;
                        }
		                next1 = top[i];
		                next2 = bottom[i];
		            }
		        }
		    }
            curCycle += Math.abs(next1 - next2);
            maxCycle = Math.max(maxCycle, curCycle);
		    pw.println(maxCycle);
		}
		
		pw.close();
	}
}