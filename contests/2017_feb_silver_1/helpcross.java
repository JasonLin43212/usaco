import java.io.*;
import java.util.*;

class cow implements Comparable<cow> {
    public int s;
    public int e;
    
    public cow(int s, int e) {
        this.s = s;
        this.e = e;
    }
    
    public int compareTo(cow other) {
        return this.e - other.e;
    }
    
    public boolean canPair(int c) {
        return s <= c && c <= e;
    }
    
    public String toString() {
        return "(" + s + " " + e + ")";
    }
}

public class helpcross {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("helpcross.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] chicken = new int[C];
		cow[] cows = new cow[N];
		
		for (int i = 0; i < C; i++) {
		    chicken[i] = Integer.parseInt(br.readLine());
		}
		for (int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    int start = Integer.parseInt(st.nextToken());
		    int end = Integer.parseInt(st.nextToken());
		    cows[i] = new cow(start, end);
		}
		
		Arrays.sort(chicken);
		Arrays.sort(cows);
		
//		System.out.println(Arrays.toString(chicken));
//		System.out.println(Arrays.toString(cows));
		
		int numPairs = 0;
		int curChick = 0;
		int curCow = 0;
		
		while (curChick < C && curCow < N) {
		    if (cows[curCow].canPair(chicken[curChick])) {
		        curCow++;
		        curChick++;
		        numPairs++;
		    } else {
		        curCow++;
		    }
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));
		pw.println(numPairs);
		pw.close();
	}
}