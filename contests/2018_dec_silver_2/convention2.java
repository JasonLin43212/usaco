import java.io.*;
import java.util.*;

class cow implements Comparable<cow>{
    public int start;
    public int duration;
    public int seniority; // lower = more senior
    
    public cow (int start, int duration, int senior) {
        this.start = start;
        this.duration = duration;
        this.seniority = senior;
    }
    
    public String toString() {
        return "( start: " + start + " end: " + duration + " sen: " + seniority + ")";
    }
    
    public int compareTo(cow other) {
        return this.start - other.start;
    }
}

public class convention2 {
    
    public static int N;
    public static cow[] cows;
    public static PriorityQueue<cow> pq = new PriorityQueue<>((a, b) -> a.seniority - b.seniority);
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("convention2.in"));
		N = Integer.parseInt(br.readLine());
		cows = new cow[N];
		for (int i = 0; i < N; i++) {
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        int s = Integer.parseInt(st.nextToken());
	        int d = Integer.parseInt(st.nextToken());
	        cows[i] = new cow(s, d, i);
		}
		Arrays.sort(cows);
//		System.out.println(Arrays.toString(cows));
		
		int time = 0;
		int curCowIndex = 0;
		
		int maxWait = 0;
		boolean once  = false;
		while (curCowIndex < N) {
		    time = cows[curCowIndex].start;
            for (int i = curCowIndex; i < N; i++) {
                curCowIndex = i;
                if (cows[i].start <= time) {
                    pq.add(cows[i]);
                } else {
                    break;
                }
            }
            
		    while (!pq.isEmpty()) {
		        cow curCow = pq.poll();
		        maxWait = Math.max(maxWait, time - curCow.start);
		        time += curCow.duration;
		        for (int i = curCowIndex; i <= N; i++) {
		            curCowIndex = i;
		            if (i != N && cows[i].start <= time) {
		                pq.add(cows[i]);
		            } else {
		                break;
		            }
		        }
		        
		    }
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out")));
		pw.println(maxWait);
		pw.close();
	}
}