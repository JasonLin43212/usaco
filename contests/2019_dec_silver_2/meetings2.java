import java.io.*;
import java.util.*;


class cow implements Comparable<cow>{
    public int loc;
    public int weight;
    public int velocity;
    
    public cow(int loc, int weight, int velocity) {
        this.loc = loc;
        this.weight = weight;
        this.velocity = velocity;
    }
    
    public int compareTo(cow other) {
        return this.loc - other.loc;
    }
    
    public String toString() {
        return "(x: " + loc + " w: " + weight + " v: " + velocity + ")"; 
    }
}

class meet implements Comparable<meet>{
    public int dist;
    public boolean canMeet;
    public int c1;
    public int c2;
    
    public meet(int dist, boolean canMeet, int c1, int c2) {
        this.dist = dist;
        this.canMeet = canMeet;
        this.c1 = c1;
        this.c2 = c2;
    }
    
    public int compareTo(meet other) {
        if (this.canMeet && other.canMeet) {
            return this.dist - other.dist;
        } else if (!this.canMeet && !other.canMeet) {
            return 0;
        } else if (!this.canMeet) {
            return 1;
        } else {
            return -1;
        }
    }
    
    public String toString() {
        return "(d: " + dist + ", meet?: " + canMeet + " c1: " + c1 + " c2: " + c2 + ")";
    }
}

public class meetings {
    
    public static int N;
    public static int L;
    public static cow[] cows;
    public static int start = 0;
    public static int end;
    public static int totalWeight = 0;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("meetings.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		cows = new cow[N];
		end = N - 1;
		
		for (int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            totalWeight += w;
		    cows[i] = new cow(2 * x, w, d);
		}
		
		if (totalWeight % 2 == 0) {
		    totalWeight /= 2;
		} else {
		    totalWeight /= 2;
		    totalWeight++;
		}
		
		Arrays.sort(cows);
//		System.out.println(Arrays.toString(cows));
		
		int curWeight = 0;
		int numMeets = 0;
		while (curWeight < totalWeight) {
		    PriorityQueue<meet> pq = new PriorityQueue<>();
	        
	        for (int i = start; i < end; i++) {
	            meet newMeet = new meet(cows[i+1].loc - cows[i].loc,
	                                    cows[i].velocity == 1 && cows[i+1].velocity == -1,
	                                    i, i + 1);
	            pq.add(newMeet);
	        }
	        System.out.println(pq);
	        
	        meet curMeet = pq.peek();
	        int time = 0;
	        if (curMeet.canMeet) {
	            time = curMeet.dist / 2;
	        } else {
	            time = Math.min(cows[start].loc, (2*L+1) - cows[end].loc);
	        }
	        
//	        System.out.println("TIME: " + time);
	        
	        int newStart = start;
            int newEnd = end;
            for (int i = start; i <= end; i++) {
                cows[i].loc += time * cows[i].velocity;
                if (i > start && cows[i].loc == cows[i-1].loc) {
                    cows[curMeet.c1].velocity *= -1;
                    cows[curMeet.c2].velocity *= -1;
                    numMeets++;
                }
//                System.out.println("cow: " + cows[i]);
                if (cows[i].loc <= 0) {
                    curWeight += cows[i].weight;
                    newStart++;
                }
                if (cows[i].loc >= (2 * L) + 1) {
                    curWeight += cows[i].weight;
                    newEnd--;
                }                
            }
            start = newStart;
            end = newEnd;
            
            
	        System.out.println("START: " + start + " END: " + end);
//            System.out.println(Arrays.toString(cows) + "\n");
            
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("meetings.out")));
		pw.println(numMeets);
		pw.close();
	}
}