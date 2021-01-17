import java.io.*;
import java.util.*;

public class balancing {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("balancing.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int[] xs = new int[N];
		int[] ys = new int[N];
		
		for (int n = 0; n < N; n++) {
		    st = new StringTokenizer(br.readLine());
		    xs[n] = Integer.parseInt(st.nextToken());
		    ys[n] = Integer.parseInt(st.nextToken());
		}
		
		int minMax = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
		    for (int j = 0; j < N; j++) {
		        int maxSection = getMaxSection(xs, ys, xs[i] + 1, ys[j] + 1);
		        minMax = Math.min(minMax, maxSection);
		    }
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
		pw.println(minMax);
		pw.close();
	}
	
	public static int getMaxSection(int[] xs, int[] ys, int a, int b) {
	    int[] sections = new int[4];
	    // 0, 1
	    // 2, 3
	    for (int i = 0; i < xs.length; i++) {
	        if (xs[i] < a) {
	            if (ys[i] < b) {
	                sections[2]++;
	            } else {
	                sections[0]++;
	            }
	        } else {
	            if (ys[i] < b) {
                    sections[3]++;
                } else {
                    sections[1]++;
                }
	        }
	    }
	    
	    int maxSection = sections[0];
	    for (int i = 0; i < 4; i++) {
	        maxSection = Math.max(maxSection, sections[i]);
	    }
	    return maxSection;
	}
}