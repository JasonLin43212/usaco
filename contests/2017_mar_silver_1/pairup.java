import java.io.*;
import java.util.*;

class cow implements Comparable<cow> {
    public int num;
    public int milk;
    
    public cow(int num, int milk) {
        this.num = num;
        this.milk = milk;
    }
    
    public String toString() {
        return "(num: " + num + ", milk: " + milk + ")";
    }
    
    public int compareTo(cow other) {
        return this.milk - other.milk;
    }
}

public class pairup {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("pairup.in"));
		int N = Integer.parseInt(br.readLine());
		int M = 0;
		cow[] cows = new cow[N];
		for (int i = 0; i < N; i++) {
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        int num = Integer.parseInt(st.nextToken());
	        int time = Integer.parseInt(st.nextToken());
	        cows[i] = new cow(num, time);
		}
		
		Arrays.sort(cows);
		
		int p1 = 0;
		int p2 = N - 1;
		
		int maxTime = 0;
		while (p1 <= p2 && p1 < N && p2 >= 0) {
//		    System.out.println(Arrays.toString(cows));
//		    System.out.println(p1 + " " + p2 + " " + (cows[p1].milk + cows[p2].milk));
		    maxTime = Math.max(maxTime, cows[p1].milk + cows[p2].milk);
		    cows[p1].num--;
		    cows[p2].num--;
		    if (cows[p1].num <= 0) {
		        p1++;
		    }
		    if (cows[p2].num <= 0) {
		        p2--;
		    }
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pairup.out")));
		pw.println(maxTime);
		pw.close();
	}
}