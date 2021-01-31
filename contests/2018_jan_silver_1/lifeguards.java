import java.io.*;
import java.util.*;

class guard implements Comparable<guard> {
    public int start;
    public int end;
    public int index;
    
    public guard(int start, int end, int index) {
        this.start = start;
        this.end = end;
        this.index = index;
    }
    
    public int compareTo(guard other) {
        return this.start - other.start;
    }
    
    public String toString() {
        return "(" + start + ", " + end + ")";
    }
}

public class lifeguards {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("lifeguards.in"));
		int n = Integer.parseInt(br.readLine());
		guard[] guards = new guard[n];
		guard[] backwards = new guard[n];
		int[] times = new int[2*n];
		for (int i = 0; i < n; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            times[2*i] = a;
            times[2*i + 1] = b;
            guards[i] = new guard(a, b, i);
            backwards[i] = guards[i];
		}
		Arrays.sort(guards);
		for (int i = 0; i < n; i++) {
		    guards[i].index = i;
		}
		Arrays.sort(backwards, (a, b) -> a.end - b.end);
		Arrays.sort(times);
//		System.out.println(Arrays.toString(guards) + " " + Arrays.toString(backwards) + " " + Arrays.toString(times));
		
		Set<Integer> curGuards = new HashSet<>();
		int guardIndex = 0;
		int backIndex = 0;
		int totalGuardTime = 0;
		int[] timesLonely = new int[n];
		for (int t = 0; t < 2 * n; t++) {
		    int curTime = times[t];
		    
		    if (curGuards.size() >= 1 && t > 0) {
		        totalGuardTime += curTime - times[t - 1];
		    }
		    
		    if (curGuards.size() == 1) {
		        for (int guardNum : curGuards) {
		            timesLonely[guardNum] += curTime - times[t - 1];
		        }
		    }
		    if (guardIndex < n) {
		        if (guards[guardIndex].start == curTime) {
		            curGuards.add(guardIndex);
		            guardIndex++;
		        }
		    }
		    
	        if (backIndex < n && backwards[backIndex].end == curTime) {
	            curGuards.remove(backwards[backIndex].index);
	            backIndex++;
	        }
//	        System.out.println("TIME: " + curTime + " " + curGuards);
		}
//		System.out.println(Arrays.toString(timesLonely));
//		System.out.println(totalGuardTime);
		int minRemove = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
		    minRemove = Math.min(timesLonely[i], minRemove);
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));
		pw.println(totalGuardTime - minRemove);
		pw.close();
	}
}