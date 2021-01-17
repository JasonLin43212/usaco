import java.io.*;
import java.util.*;

public class angry {
    
    public static int N;
    public static int[] bales;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("angry.in"));
		N = Integer.parseInt(br.readLine());
		int minBale = Integer.MAX_VALUE;
		int maxBale = -1;
		bales = new int[N];
		for (int i = 0; i < N; i++) {
		    bales[i] = Integer.parseInt(br.readLine()) * 2;
		    minBale = Math.min(minBale, bales[i]);
		    maxBale = Math.max(maxBale, bales[i]);
		}
		Arrays.sort(bales);
		int maxGap = 0;
        int prevBale = bales[0];;
        for (int i = 1; i < N; i++) {
            maxGap = Math.max(bales[i] - prevBale, maxGap);
            prevBale = bales[i];
        }
        maxGap /= 2;
        
		System.out.println("MAX GAP: " + maxGap);
		System.out.println(minBale + " " + maxBale);
		System.out.println(Arrays.toString(bales));
		
		// Powers are obtained by dividing by 2
		int loPower = maxGap;
		int hiPower = (maxBale - minBale) / 2;
		for (loPower--; loPower < hiPower;) {
		    int mid = (loPower + hiPower + 1) / 2;
		    boolean canTopple = false;
		    System.out.println("lo: " + loPower + " hi: " + hiPower + " mid: " + mid);
		    for (int i = 0; i < maxBale - minBale; i++) {
		        int start = minBale + i;
		        if (topple(start, mid)) {
		            canTopple = true;
		            break;
		        }
		    }
		    if (canTopple) {
		        hiPower = mid;
		    } else {
		        loPower = mid + 1;
		    }
		    break;
		}
		
		System.out.println(loPower);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
		pw.println("solution");
		pw.close();
	}
	
	public static boolean topple(int start, int power) {
	    int startIndex = Arrays.binarySearch(bales, start);
	    int loIndex;
	    int hiIndex;
	    if (startIndex < 0) {
	        int insertPoint = -(startIndex + 1);
	        if (insertPoint == N) {
	            if (start - bales[N-1] <= power) {
	                loIndex = N-1;
	                hiIndex = N-1;
	            }
	        } else {
	            if ()
	            loIndex = insertPoint - 1;
	            hiIndex = insertPoint - 1;
	        }
	        power --;
	    } else {
	        loIndex = startIndex;
	        hiIndex = startIndex;
	    }
	    
	    System.out.println("start: " + start + " power: " + power);
	    boolean foundChange
	    return loIndex == 0 && hiIndex == N-1;
	}
}