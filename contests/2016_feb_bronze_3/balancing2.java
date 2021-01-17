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
//		    System.out.println("(" + xs[n] + "," + ys[n] + ")");
		}
		
		int medianX = median(xs);
		int medianY = median(ys);
		
		int[] as;
		int[] bs;
		
		if (medianX % 2 == 0) {
		    as = new int[] {medianX};
		} else {
		    as = new int[] {medianX - 1, medianX + 1};
		}
		
		if (medianY % 2 == 0) {
            bs = new int[] {medianY};
        } else {
            bs = new int[] {medianY - 1, medianY + 1};
        }
		
		System.out.println(Arrays.toString(as) + " " + Arrays.toString(bs));
		int minMax = Integer.MAX_VALUE;
		for (int i_a = 0; i_a < as.length; i_a++) {
		    for (int i_b = 0; i_b < bs.length; i_b++) {
		        int maxSection = getMaxSection(xs, ys, as[i_a], bs[i_b]);
		        minMax = Math.min(minMax, maxSection);
		    }
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
		pw.println(minMax);
		pw.close();
	}
	
	public static int median(int[] nums) {
	    int[] numCopy = Arrays.copyOf(nums, nums.length);
	    Arrays.sort(numCopy);
//	    System.out.println(Arrays.toString(numCopy));
	    if (numCopy.length % 2 == 0) {
	        return (numCopy[(numCopy.length / 2) - 1] + 
	               numCopy[numCopy.length / 2]) / 2;
	    } else {
	        return numCopy[numCopy.length / 2];
	    }
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